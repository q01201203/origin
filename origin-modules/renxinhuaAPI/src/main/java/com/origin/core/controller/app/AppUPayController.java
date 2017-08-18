package com.origin.core.controller.app;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserBankDTO;
import com.origin.core.service.AppUserBankService;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserBank;
import com.umpay.api.exception.VerifyException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import util.UPay;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/app/upay")
@Api(value = "/app/upay" ,description = "app用户支付")
public class AppUPayController {

	Logger log = LoggerFactory.getLogger(AppUPayController.class);

	@Autowired
	public AppUserService appUserService;

	@Autowired
	public AppUserBankService appUserBankService;

	@RequestMapping(value = "/reqBindVerifyShortcut" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "API签约请求", httpMethod = "GET", response = Result.class, notes = "" +
			"卡号【card_id】  媒介标识【media_id】银行预留手机号码   证件号【identity_code】  持卡人姓名【card_holder】")
	public Object reqBindVerifyShortcut(@RequestHeader(value = "Authorization" ) String token,
						  				@RequestParam(value = "cardId") String cardId,
						  				@RequestParam(value = "mediaId") String mediaId,
										@RequestParam(value = "identityCode") String identityCode,
										@RequestParam(value = "cardHolder") String cardHolder) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(cardId)||StringUtil.isNullOrBlank(mediaId)
				||StringUtil.isNullOrBlank(identityCode)||StringUtil.isNullOrBlank(cardHolder)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		UPay uPay = new UPay();
		Map res = uPay.reqBindVerifyShortcut(cardId,mediaId,identityCode,cardHolder);
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				Map result = new HashMap();
				result.put("bindNo",res.get("bind_no"));
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}

	@RequestMapping(value = "/reqBindConfirmShortcut" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "API签约确认", httpMethod = "GET", response = Result.class, notes = "" +
			"注册流水号【bind_no】 验证码【verify_code】 商户用户标识【mer_cust_id】 卡号【card_id】" +
			"媒介标识【media_id】银行预留手机号码   证件号【identity_code】  持卡人姓名【card_holder】")
	public Object reqBindConfirmShortcut(@RequestHeader(value = "Authorization" ) String token,
										 @RequestParam(value = "bindNo") String bindNo ,
										 @RequestParam(value = "verifyCode") String verifyCode ,
										 @RequestParam(value = "cardId") String cardId ,
										 @RequestParam(value = "mediaId") String mediaId,
										 @RequestParam(value = "identityCode") String identityCode,
										 @RequestParam(value = "cardHolder") String cardHolder) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(bindNo)||StringUtil.isNullOrBlank(verifyCode)|| StringUtil.isNullOrBlank(cardId)||
				StringUtil.isNullOrBlank(mediaId)||StringUtil.isNullOrBlank(identityCode)||StringUtil.isNullOrBlank(cardHolder)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = appUserService.findById(uId);
		String merCustId = "rxh"+appUser.getMobile();

		UPay uPay = new UPay();
		Map res = uPay.reqBindConfirmShortcut(bindNo,verifyCode,merCustId,cardId,mediaId,identityCode,cardHolder);
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				Map result = new HashMap();
				result.put("usrBusiAgreementId",res.get("usr_busi_agreement_id"));
				result.put("usrPayAgreementId",res.get("usr_pay_agreement_id"));
				result.put("gateId",res.get("gate_id"));
				result.put("bankCardType",res.get("bank_card_type"));
				//保存到数据库
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}

	@RequestMapping(value = "/payReqShortcut" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "商户API后台下单", httpMethod = "GET", response = Result.class, notes = "" +
			"付款金额【amount】以分为单位")
	public Object payReqShortcut(@RequestHeader(value = "Authorization" ) String token ,
										 @RequestParam(value = "amount") String amount ) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(amount)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		//生成订单号
		String orderId=""+(Math.round(Math.random()*800000)+100000)+"";
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		String date = format.format(new Date());
		orderId = date + orderId;

		UPay uPay = new UPay();
		Map res = uPay.payReqShortcut(orderId,date,amount,token);
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				Map result = new HashMap();
				result.put("tradeNo",res.get("trade_no"));
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}


	@RequestMapping(value = "/agreeMentPayConfirmShortCut" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "商户API确认支付", httpMethod = "GET", response = Result.class, notes = "" +
			"联动交易号【trade_no】  商户用户标识【mer_cust_id】  用户业务协议号【usr_busi_agreement_id】 " +
			"支付协议号【usr_pay_agreement_id】")
	public Object agreeMentPayConfirmShortCut(@RequestHeader(value = "Authorization" ) String token,
										 @RequestParam(value = "tradeNo") String tradeNo) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();
		IAppUserBank appUserBankDTO = new AppUserBankDTO();
		appUserBankDTO.setUid(uId);
		List<IAppUserBank> appUserBanks = appUserBankService.find(appUserBankDTO);
		if (appUserBanks!=null&&appUserBanks.size()>0){
			appUserBankDTO = appUserBanks.get(0);
		}
		String merCustId = appUserBankDTO.getMerCustId();
		String usrBusiAgreementId = appUserBankDTO.getUsrBusiAgreementId();
		String usrPayAgreementId = appUserBankDTO.getUsrPayAgreementId();

		if (StringUtil.isNullOrBlank(tradeNo)||StringUtil.isNullOrBlank(merCustId)||StringUtil.isNullOrBlank(usrBusiAgreementId)||
				StringUtil.isNullOrBlank(usrPayAgreementId)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		UPay uPay = new UPay();
		Map res = uPay.agreementPayConfirmShortcut(tradeNo,merCustId,usrBusiAgreementId,usrPayAgreementId);
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				Map result = new HashMap();
				result.put("tradeNo",res.get(tradeNo));
				result.put("orderId",res.get("order_id"));
				result.put("merDate",res.get("mer_date"));
				result.put("payDate",res.get("pay_date"));
				result.put("settleDate",res.get("settle_date"));
				result.put("amount",res.get("amount"));
				result.put("tradeState",res.get("trade_state"));
				result.put("retMsg",res.get("ret_msg"));
				result.put("retCode",res.get("ret_code"));
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}

	@RequestMapping(value = "/queryMerBankShortCut" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户查看商户支持的银行列表", httpMethod = "GET", response = Result.class, notes = "")
	public Object queryMerBankShortCut(@RequestHeader(value = "Authorization" ) String token) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		UPay uPay = new UPay();
		Map res = uPay.queryMerBankShortCut();
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				Map result = new HashMap();
				result.put("merBankList",res.get("mer_bank_list"));
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}


	@RequestMapping(value = "/merOrderInfoQuery" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "订单查询", httpMethod = "GET", response = Result.class, notes = "")
	public Object merOrderInfoQuery(@RequestParam(value = "order_id") String order_id,
			@RequestParam(value = "mer_date") String mer_date) throws Exception {
		log.debug("getPlatNotifyData order_id = "+order_id+" mer_date = "+mer_date);

		UPay uPay = new UPay();
		Map res = uPay.merOrderInfoQuery(order_id,mer_date);
		if (res!=null){
			if("0000".equals(res.get("ret_code"))){
				String trade_no = String.valueOf(res.get("trade_no"));
				String pay_date = String.valueOf(res.get("pay_date"));
				String settle_date = String.valueOf(res.get("settle_date"));
				String amount = String.valueOf(res.get("amount"));
				String trade_state = String.valueOf(res.get("trade_state"));
				Map<String,String> result = new HashMap();
				result.put("tradeNo",trade_no);
				result.put("payDate",pay_date);
				result.put("settleDate",settle_date);
				result.put("amount",amount);
				result.put("tradeState",trade_state);
				return Result.createSuccessResult(result, ""+res.get("ret_msg"));
			}else{
				log.debug("merOrderInfoQuery fail "+res.get("ret_msg"));
				return Result.createErrorResult().setMessage("查询失败:" + res.get("ret_msg"));
			}
		}else{
			log.debug("merOrderInfoQuery error ");
			return Result.createErrorResult().setMessage("UPay平台响应状态异常");
		}
	}


	@RequestMapping(value = "/getPlatNotifyData" , method = RequestMethod.GET)
	public void getPlatNotifyData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap resData = new HashMap();
		HashMap data = new HashMap();
		String resString = "";
		String mer_id = request.getParameter("mer_id");
		log.debug("getPlatNotifyData mer_id = "+mer_id);
		if(!"".equals(mer_id) && null != mer_id) {
			try {
				//验签,不抛异常表示验签成功
				data = (HashMap) com.umpay.api.paygate.v40.Plat2Mer_v40.getPlatNotifyData(request);

				//验签成功 ，
				resData.put("ret_code","0000");
				resData.put("mer_id", request.getParameter("mer_id")); //数据可以从data里边取，也可以从request里边取。
				resData.put("sign_type", request.getParameter("sign_type"));
				resData.put("version", request.getParameter("version"));
				resData.put("order_id", request.getParameter("order_id"));
				resData.put("mer_date", request.getParameter("mer_date"));

				resString = com.umpay.api.paygate.v40.Mer2Plat_v40.merNotifyResData(resData);

				//商户可加入自己的处理逻辑
			} catch (VerifyException e) {
				//如果验签失败，则抛出异常，返回ret_code=1111
				System.out.println("验证签名发生异常" + e);
				resData.put("ret_code","1111");
				resString = com.umpay.api.paygate.v40.Mer2Plat_v40.merNotifyResData(resData);
			}
		}
		log.debug("getPlatNotifyData resString = "+resString);
		PrintWriter write = response.getWriter();
		resString = "<html><META NAME=\"MobilePayPlatform\" CONTENT=\"" + resString + "\" /></html>" ;
		write.print(resString);
		write.flush();
	}
}