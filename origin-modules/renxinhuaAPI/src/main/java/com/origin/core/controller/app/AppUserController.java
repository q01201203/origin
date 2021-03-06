package com.origin.core.controller.app;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.*;
import com.origin.core.model.DataWithPageModel;
import com.origin.core.model.FaceThanIdcardResultModel;
import com.origin.core.model.IdCardRecognizeResultModel;
import com.origin.core.service.*;
import com.origin.core.util.*;
import com.origin.data.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import util.YituUtil;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/app/user")
@Api(value = "/app/user" ,description = "app用户操作API")
public class AppUserController {

	Logger log = LoggerFactory.getLogger(AppUserController.class);
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppPersonDetailService appPersonDetailService;

	@Autowired
	private AppStuDetailService appStuDetailService;

	@Autowired
	private AppUserBankService appUserBankService;

	@Autowired
	private AppMoneyDetailService appMoneyDetailService;

	@Autowired
	private AppFeedbackService appFeedbackService;

	@Autowired
	private AppMessageService appMessageService;

	//query
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户登录", httpMethod = "POST", response = Result.class, notes = "传入手机号密码和极光推送的别名" +
			"，返回一个token")
	public Object login(@RequestParam(value = "mobile") String mobile ,
						@RequestParam(value = "pwd") String pwd,
						@RequestParam(value = "alias" ,required = false) String alias) throws Exception {
		log.debug("renxinhua = "+mobile);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		List<IAppUser> appUsers = appUserService.find(appUser);
		if (appUsers!=null&&appUsers.size()>0){
			appUser = appUsers.get(0);
		}else{
			appUser = null;
		}
		if (appUser!=null){
			if (!StringUtil.isNullOrBlank(alias)){
				appUser.setJpushAlias(alias);
				appUserService.update(appUser);
			}
			return Result.createSuccessResult(CustomToken.generate(new SimpleToken(appUser.getId(),
					appUser.getAuthority())),"登录成功");
		}
		return Result.createErrorResult().setMessage("用户名密码错误");
	}

	@RequestMapping(value = "/register" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户注册", httpMethod = "POST", response = Result.class,
			notes = "注册传入手机号，密码")
	public Object register(@RequestParam(value = "mobile") String mobile ,
						   @RequestParam(value = "pwd") String pwd) throws Exception{
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		return appUserService.saveRegisterUser(mobile,pwd);
	}

	//芝麻认证回调接口
	@RequestMapping(value = "/saveRegisterInfo")
	//@ResponseBody
	@ApiIgnore
	public String saveRegisterInfo(HttpServletRequest request) throws Exception{
		return appUserService.saveRegisterInfo(request);
	}

	//update
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	@ApiOperation(value = "app用户重置登录密码", httpMethod = "POST", response = Result.class, notes = "重置登录密码，传入手机号" +
			"密码")
	public Object resetPwd(@RequestParam(value = "mobile") String mobile ,
						   @RequestParam(value = "pwd") String pwd) throws Exception {
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		return appUserService.updateResetPwd(mobile,pwd);
	}

	@RequestMapping(value = "/addPayPwd" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户添加或者更新支付密码", httpMethod = "POST", response = Result.class, notes = "添加或者更新支付密码" +
			"，传入支付密码")
	public Object addPayPwd(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "payPwd") String payPwd) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String regex = "^\\d{6}$"; //验证密码
		if (StringUtil.isNullOrBlank(payPwd) || !payPwd.matches(regex)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setPayPwd(payPwd);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加支付密码成功");
	}

	@RequestMapping(value = "/updateIdinfoAndFace" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加身份证信息和人脸图片和分类", httpMethod = "GET", response = Result.class, notes = "" +
			"添加身份证信息（正面图片地址，背面图片地址，姓名，身份证号）和人脸图片地址和分类（1、学生2、社会人群），返回芝麻认证" +
			"的URL")
	public Object updateIdinfoAndFace(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "idFrontImg") String idFrontImg,
							@RequestParam(value = "idBackImg") String idBackImg,
							@RequestParam(value = "idName") String idName,
							@RequestParam(value = "idNumber") String idNumber,
							@RequestParam(value = "face") String face,
							@RequestParam(value = "category") String category) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);

		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(idFrontImg)||StringUtil.isNullOrBlank(idBackImg)
				||StringUtil.isNullOrBlank(idName)||StringUtil.isNullOrBlank(idNumber)||StringUtil.isNullOrBlank(face)
				||StringUtil.isNullOrBlank(category)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}



		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgIdFront(idFrontImg);
		appUser.setImgIdBack(idBackImg);
		appUser.setUserIdName(idName);
		appUser.setUserIdNumber(idNumber);
		appUser.setImgFace(face);
		appUser.setCategory(Integer.parseInt(category));
		appUserService.update(appUser);

		ZhimaUtil zhimaUtil = new ZhimaUtil();
		String result = zhimaUtil.zhimaAuthInfoAuthorize(idName, idNumber , token);
		if ( result != null && !"error".equals(result)) {
			return Result.createSuccessResult(result, "成功返回芝麻认证url");
		} else {
			return Result.createErrorResult().setMessage("返回芝麻认证url失败");
		}
	}

	@RequestMapping(value = "/updatePortrait" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加头像", httpMethod = "GET", response = Result.class, notes = "添加头像" +
			"，传入头像图片地址")
	public Object updatePortrait(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "portrait") String portrait) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();
		if (StringUtil.isNullOrBlank(portrait)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgPortrait(portrait);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("更新头像成功");
	}

	@RequestMapping(value = "/updateNickname" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户添加用户昵称", httpMethod = "POST", response = Result.class, notes = "添加昵称" +
			"，传入用户昵称")
	public Object updateNickname(@RequestHeader(value = "Authorization" ) String token,
							  @RequestParam(value = "nickname") String nickname) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(nickname)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setNickname(nickname);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("更新昵称成功");
	}

	//other operation
	@RequestMapping(value = "/savePersonInfo" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户保存个人信息", httpMethod = "POST", response = Result.class,
			notes = "保存用户详细信息 category(1:学生需传(infoSchool,infoDepartment,infoClass,infoRoomNumber) " +
					"2:社会人群需传(infoCompanyName,infoCompanyAddress,infoQq,infoWeixin,infoHome))")
	@ApiImplicitParams({
			/*@ApiImplicitParam(name = "infoMobile" ,value = "infoMobile",paramType = "query",dataType = "string") ,*/
			@ApiImplicitParam(name = "infoSchool" ,value = "infoSchool",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoDepartment" ,value = "infoDepartment",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoClass" ,value = "infoClass",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoRoomNumber" ,value = "infoRoomNumber",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoCompanyName" ,value = "infoCompanyName",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoCompanyAddress" ,value = "infoCompanyAddress",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoQq" ,value = "infoQq",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoWeixin" ,value = "infoWeixin",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoHome" ,value = "infoHome",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "infoEmycontactRelation" ,value = "infoEmycontactRelation",paramType = "query"
					,dataType = "string"),
			@ApiImplicitParam(name = "infoEmycontactMobile" ,value = "infoEmycontactMobile",paramType = "query"
					,dataType = "string"),
			@ApiImplicitParam(name = "infoContactRelation" ,value = "infoContactRelation",paramType = "query"
					,dataType = "string"),
			@ApiImplicitParam(name = "infoContactMobile" ,value = "infoContactMobile",paramType = "query"
					,dataType = "string")})
	public Object savePersonInfo(@RequestHeader(value = "Authorization") String token,
							@RequestParam(value = "category") String category,
							HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		log.debug("renxinhua 请求的category为" + category);
		if (StringUtil.isNullOrBlank(category)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		//1为学生 2为社会人群
		if ("1".equals(category)){
			return saveStudentInfo(request, uId);
		}else if ("2".equals(category)){
			return savePersonInfo(request, uId);
		}
		return Result.createErrorResult().setMessage("category类型错误");
	}

	private Object savePersonInfo(HttpServletRequest request, Integer uId) {
		//String infoMobile = request.getParameter("infoMobile");
		String infoCompanyName = request.getParameter("infoCompanyName");
		String infoCompanyAddress = request.getParameter("infoCompanyAddress");
		String infoQq = request.getParameter("infoQq");
		String infoWeixin = request.getParameter("infoWeixin");
		String infoHome = request.getParameter("infoHome");
		String infoEmycontactRelation = request.getParameter("infoEmycontactRelation");
		String infoEmycontactMobile = request.getParameter("infoEmycontactMobile");
		String infoContactRelation = request.getParameter("infoContactRelation");
		String infoContactMobile = request.getParameter("infoContactMobile");
		log.debug(/*"请求的infoMobile为" + infoMobile +*/ "\n请求的infoCompanyAddress为" +
				infoCompanyAddress +"\n请求的infoQq为" + infoQq+ "\n请求的infoWeixin为" +
				infoWeixin+ "\n请求的infoHome为" + infoHome+ "\n请求的infoEmycontactRelation为" +
				infoEmycontactRelation+ "\n请求的infoEmycontactMobile为" + infoEmycontactMobile+
				"\n请求的infoContactRelation为" + infoContactRelation+ "\n请求的infoContactMobile为" +
				infoContactMobile+ "\n请求的infoCompanyName为" + infoCompanyName);
		if (/*StringUtil.isNullOrBlank(infoMobile)||*/StringUtil.isNullOrBlank(infoCompanyAddress)
				||StringUtil.isNullOrBlank(infoQq)||StringUtil.isNullOrBlank(infoWeixin)
				||StringUtil.isNullOrBlank(infoHome)||StringUtil.isNullOrBlank(infoEmycontactRelation)
				||StringUtil.isNullOrBlank(infoEmycontactMobile)||StringUtil.isNullOrBlank(infoContactRelation)
				||StringUtil.isNullOrBlank(infoContactMobile)||StringUtil.isNullOrBlank(infoCompanyName)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		return appPersonDetailService.savePersonInfo(uId,infoCompanyName,infoCompanyAddress,infoQq,infoWeixin,infoHome,
				infoEmycontactRelation,infoEmycontactMobile,infoContactRelation,infoContactMobile);
	}

	private Object saveStudentInfo(HttpServletRequest request, Integer uId) {
		//String infoMobile = request.getParameter("infoMobile");
		String infoSchool = request.getParameter("infoSchool");
		String infoDepartment = request.getParameter("infoDepartment");
		String infoClass = request.getParameter("infoClass");
		String infoRoomNumber = request.getParameter("infoRoomNumber");
		String infoEmycontactRelation = request.getParameter("infoEmycontactRelation");
		String infoEmycontactMobile = request.getParameter("infoEmycontactMobile");
		String infoContactRelation = request.getParameter("infoContactRelation");
		String infoContactMobile = request.getParameter("infoContactMobile");
		log.debug(/*"请求的infoMobile为" + infoMobile + */"\n请求的infoSchool为" +
				infoSchool+ "\n请求的infoDepartment为" + infoDepartment+ "\n请求的infoClass为" +
				infoClass+ "\n请求的infoRoomNumber为" + infoRoomNumber+ "\n请求的infoEmycontactRelation为" +
				infoEmycontactRelation+ "\n请求的infoEmycontactMobile为" + infoEmycontactMobile+
				"\n请求的infoContactRelation为" + infoContactRelation+ "\n请求的infoContactMobile为" +
				infoContactMobile);
		if (/*StringUtil.isNullOrBlank(infoMobile)||*/StringUtil.isNullOrBlank(infoSchool)
				||StringUtil.isNullOrBlank(infoDepartment)||StringUtil.isNullOrBlank(infoClass)
				||StringUtil.isNullOrBlank(infoRoomNumber)||StringUtil.isNullOrBlank(infoEmycontactRelation)
				||StringUtil.isNullOrBlank(infoEmycontactMobile)||StringUtil.isNullOrBlank(infoContactRelation)
				||StringUtil.isNullOrBlank(infoContactMobile)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		return appStuDetailService.saveStudentInfo(uId,infoSchool,infoDepartment,infoClass,infoRoomNumber,
				infoEmycontactRelation, infoEmycontactMobile,infoContactRelation,infoContactMobile);
	}

	@RequestMapping(value = "/saveBankInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存银行信息", httpMethod = "GET", response = Result.class, notes = "保存用户银行信息" +
			"，必传银行名字bankName，银行卡号bankNumber，银行卡类型bankType，银行卡预留手机号bankMobile" +
			"bindNo注册流水号,usrBusiAgreementId用户业务协议号,usrPayAgreementId支付协议号,gateId支付银行,bankCardType银行卡类型(upay返回的)")
	@ApiImplicitParams({@ApiImplicitParam(name = "bankName" ,value = "bankName",paramType = "query",dataType = "string") ,
			@ApiImplicitParam(name = "bankNumber" ,value = "bankNumber",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "bankMobile" ,value = "bankMobile",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "bankType" ,value = "bankType",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "bindNo" ,value = "bindNo",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "usrBusiAgreementId" ,value = "usrBusiAgreementId",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "usrPayAgreementId" ,value = "usrPayAgreementId",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "gateId" ,value = "gateId",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "bankCardType" ,value = "bankCardType",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "identityCode" ,value = "identityCode",paramType = "query",dataType = "string"),
			@ApiImplicitParam(name = "cardHolder" ,value = "cardHolder",paramType = "query",dataType = "string")})
	public Object saveBankInfo(@RequestHeader(value = "Authorization" ) String token,
							   @ApiIgnore AppUserBankDTO appUserBank) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();
		return appUserBankService.saveBankInfo(uId,appUserBank);
	}

	@RequestMapping(value = "/submitMoney" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户提交钱信息(借钱、还钱、提现、收入(完成任务))", httpMethod = "GET", response = Result.class,
			notes = "提交钱信息 借钱(askMoney、type=1、repayTimeType(1:7天 2:15天)) 还钱(askMoney、type=2、" +
					"repayWay(1:支付宝 2:银行卡)、pid借钱的id、delayMoney滞纳金、orderId订单号UPay)  提现(askMoney、type=3)  收入(type=4、taskId（任务ID）、" +
					"taskUserName(完成任务的用户名)、taskMobile(完成任务的手机号)))")
	public Object submitMoney(@RequestHeader(value = "Authorization" ) String token,
							  @RequestParam(value = "askMoney",required = false) String askMoney,
							  @RequestParam(value = "type") String type,
							  @RequestParam(value = "repayTimeType",required = false) String repayTimeType,
							  @RequestParam(value = "repayWay",required = false) String repayWay,
							  @RequestParam(value = "taskId",required = false) String taskId,
							  @RequestParam(value = "taskUserName",required = false) String taskUserName,
							  @RequestParam(value = "taskMobile",required = false) String taskMobile,
							  @RequestParam(value = "pid",required = false) String pid,
							  @RequestParam(value = "delayMoney",required = false) String delayMoney,
							  @RequestParam(value = "orderId",required = false) String orderId) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		log.debug("renxinhua 请求的money为" + askMoney + "\n请求的type为" + type);
		if (StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		return appMoneyDetailService.saveSubmitMoney(uId,askMoney,type,repayTimeType,repayWay,taskId,taskUserName,taskMobile,
				pid,delayMoney,orderId);
	}

	@RequestMapping(value = "/getMoney" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户获取钱信息", httpMethod = "GET", response = Result.class,
			notes = "获取钱信息 钱使用money_actual字段 必传type(1:借钱 2:还钱 3:提现 4:收入) 可选status(1:待审核" +
					"2:审核通过 3:审核未通过) 借钱可选repayStatus还款状态(0、未还1、已还2、待审核) currentPage当前页（不填默认第一页），pageSize每页条数（不填默认为10）")
	public Object getMoney(@RequestHeader(value = "Authorization" ) String token,
						   @RequestParam(value = "type") String type,
						   @RequestParam(value = "status" ,required = false) String status,
						   @RequestParam(value = "repayStatus",required = false) String repayStatus,
						   @RequestParam(value = "currentPage" ,required = false) String currentPageStr,
						   @RequestParam(value = "pageSize" ,required = false) String pageSizeStr) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		log.debug("renxinhua 请求的type为" + type);
		if (StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
		appMoneyDetail.setUid(uId);
		if (!StringUtil.isNullOrBlank(status)){
			appMoneyDetail.setStatus(Integer.parseInt(status));
		}

		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
			if (!StringUtil.isNullOrBlank(repayStatus)){
				appMoneyDetail.setRepayStatus(Integer.parseInt(repayStatus));
			}
			PageHelper.startPage(currentPage, pageSize);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			PageInfo<IAppMoneyDetail> page = new PageInfo(appMoneyDetails);
			DataWithPageModel<IAppMoneyDetail> dataWithPageModel = new DataWithPageModel<>();
			dataWithPageModel.setData(appMoneyDetails);
			dataWithPageModel.setPageInfo(page);
			return Result.createSuccessResult().setData(dataWithPageModel).setMessage("借款信息查询成功");
		} else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
			PageHelper.startPage(currentPage, pageSize);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			PageInfo<IAppMoneyDetail> page = new PageInfo(appMoneyDetails);
			DataWithPageModel<IAppMoneyDetail> dataWithPageModel = new DataWithPageModel<>();
			dataWithPageModel.setData(appMoneyDetails);
			dataWithPageModel.setPageInfo(page);
			return Result.createSuccessResult().setData(dataWithPageModel).setMessage("还款信息查询成功");
		} else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_WITHDRAW);
			PageHelper.startPage(currentPage, pageSize);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			PageInfo<IAppMoneyDetail> page = new PageInfo(appMoneyDetails);
			DataWithPageModel<IAppMoneyDetail> dataWithPageModel = new DataWithPageModel<>();
			dataWithPageModel.setData(appMoneyDetails);
			dataWithPageModel.setPageInfo(page);
			return Result.createSuccessResult().setData(dataWithPageModel).setMessage("提现信息查询成功");
		} else if (IAppMoneyDetail.TYPE_INCOME.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
			PageHelper.startPage(currentPage, pageSize);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			//List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.findIncomeInfo(appMoneyDetail);
			PageInfo<IAppMoneyDetail> page = new PageInfo(appMoneyDetails);
			DataWithPageModel<IAppMoneyDetail> dataWithPageModel = new DataWithPageModel<>();
			dataWithPageModel.setData(appMoneyDetails);
			dataWithPageModel.setPageInfo(page);
			return Result.createSuccessResult().setData(dataWithPageModel).setMessage("收入信息查询成功");
		} else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
	}

	@RequestMapping(value = "/saveFeedback" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存反馈信息", httpMethod = "GET", response = Result.class, notes = "保存反馈信息，" +
			"传入内容content")
	public Object saveFeedback(@RequestHeader(value = "Authorization" ) String token,
							   @RequestParam(value = "content") String content) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		log.debug("renxinhua 请求的content为" + content);
		if (StringUtil.isNullOrBlank(content)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppFeedback feedback = new AppFeedbackDTO();
		feedback.setContent(content);
		feedback.setUid(uId);
		appFeedbackService.save(feedback);
		return Result.createSuccessResult().setMessage("保存反馈信息成功");
	}

	@RequestMapping(value = "/getUserBank" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户银行信息", httpMethod = "GET", response = Result.class, notes = "获取用户银行卡信息")
	public Object getUserBank(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUserBank appUserBank = new AppUserBankDTO();
		appUserBank.setUid(uId);
		List<IAppUserBank> appUserBanks = appUserBankService.find(appUserBank);
		return Result.createSuccessResult(appUserBanks,"获取用户银行卡信息成功");
	}

	@RequestMapping(value = "/getUserDetail" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户详细信息", httpMethod = "GET", response = Result.class, notes = "获取用户详细信息")
	public Object getUserDetail(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);

		if (appUser.getCategory()!=null){
			if (IAppUser.CATEGORY_STU.equals(appUser.getCategory())){
				IAppStuDetail appStuDetail = new AppStuDetailDTO();
				appStuDetail.setUid(uId);
				List<IAppStuDetail> appStuDetails = appStuDetailService.find(appStuDetail);
				if (appStuDetails!=null&&appStuDetails.size()>0){
					appStuDetail = appStuDetails.get(0);
					return Result.createSuccessResult(appStuDetail,"获取用户学生信息成功");
				}else{
					return Result.createErrorResult(appStuDetail,"获取用户学生信息失败");
				}
			}else if (IAppUser.CATEGORY_PERSON.equals(appUser.getCategory())){
				IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
				appPersonDetail.setUid(uId);
				List<IAppPersonDetail> appPersonDetails = appPersonDetailService.find(appPersonDetail);
				if (appPersonDetails!=null&&appPersonDetails.size()>0){
					appPersonDetail = appPersonDetails.get(0);
					return Result.createSuccessResult(appPersonDetail,"获取用户社会人群信息成功");
				}else{
					return Result.createErrorResult(appPersonDetail,"获取用户社会人群信息失败");
				}
			}
		}
		return Result.create(ResultCode.SERVICE_ERROR).setMessage("用户详细信息不存在");
	}

	@RequestMapping(value = "/getMoneyTotal" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户钱信息", httpMethod = "GET", response = Result.class, notes = "获取用户总共借钱borrowTotal" +
			"总共需要还的钱repayTotal 总提现withdrawTotal 总收入incomeTotal 用余额还的款repayBalance 需要还的钱needRepay" +
			"余额balance 额度moneyMax 可借额度borrowLine")
	public Object getMoneyTotal(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		Double moneyMax = appUser.getMoneyMax();
		String zhimaScore = appUser.getZhimaScore();

		//审核通过实际的钱
		Double borrowActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
		Double repayActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
		Double withdrawActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_WITHDRAW);
		Double incomeActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_INCOME);
		Double repayBalanceActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_INCOME,IAppMoneyDetail.REPAY_WAY_BALANCE);
		//审核中申请的钱
		Double borrowAsk = getTotalAskMoney(uId,IAppMoneyDetail.TYPE_BORROW);
		Double repayAsk = getTotalAskMoney(uId,IAppMoneyDetail.TYPE_REPAY);
		Double withdrawAsk = getTotalAskMoney(uId,IAppMoneyDetail.TYPE_WITHDRAW);
		Double repayBalanceAsk = getTotalAskMoney(uId,IAppMoneyDetail.TYPE_INCOME,IAppMoneyDetail.REPAY_WAY_BALANCE);

		Double needRepay = borrowActual - repayActual - repayAsk;
		Double balance = incomeActual - withdrawActual - withdrawAsk - repayBalanceActual - repayBalanceAsk;
		Double balance2 = appUser.getBalance();
		log.debug("renxinhua b1 = "+balance+" b2 = "+balance2);
		Double borrowLine = moneyMax - borrowActual - borrowAsk + repayActual;

		Map<String,Object> moneyMap = new HashMap();
		moneyMap.put("borrowTotal",borrowActual);
		moneyMap.put("repayTotal",repayActual);
		moneyMap.put("withdrawTotal",withdrawActual);
		moneyMap.put("incomeTotal",incomeActual);
		moneyMap.put("repayBalance",repayBalanceActual);
		moneyMap.put("needRepay",needRepay);
		moneyMap.put("balance",balance);
		moneyMap.put("moneyMax",moneyMax);
		moneyMap.put("borrowLine",borrowLine);
		moneyMap.put("zhimaScore",zhimaScore);
		return Result.createSuccessResult(moneyMap,"获取app用户钱信息");
	}

	private Double getTotalAskMoney(Integer uId,Integer type){
		return getTotalAskMoney(uId,type,null);
	}

	private Double getTotalAskMoney(Integer uId,Integer type,Integer repayWay){
		IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
		moneyDetail.setType(type);
		moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
		if (repayWay!=null){
			moneyDetail.setRepayWay(repayWay);
		}
		moneyDetail.setUid(uId);
		Double totalAskMoney = appMoneyDetailService.findTotalAskMoney(moneyDetail);
		return totalAskMoney==null?0:totalAskMoney;
	}

	private Double getTotalActualMoney(Integer uId,Integer type){
		return getTotalActualMoney(uId,type,null);
	}

	private Double getTotalActualMoney(Integer uId,Integer type,Integer repayWay){
		IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
		moneyDetail.setType(type);
		moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
		if (repayWay!=null){
			moneyDetail.setRepayWay(repayWay);
		}
		moneyDetail.setUid(uId);
		Double totalActualMoney = appMoneyDetailService.findTotalActualMoney(moneyDetail);
		return totalActualMoney==null?0:totalActualMoney;
	}

	@RequestMapping(value = "/getUserMessage" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取用户单条消息或者列表", httpMethod = "GET", response = Result.class, notes = "如果传入消息ID则获取单条消息" +
			"否则获取列表,传入可选参数type(1、个人消息2、系统消息),可选参数status状态(0已读1未读),可选参数isGetTotal(为1的时候只获取总条数否则获取所有记录)" +
			" currentPage当前页（不填默认第一页），pageSize每页条数（不填默认为10）")
	public Object getUserMessage(@RequestHeader(value = "Authorization") String token,
								 @RequestParam(value = "mid" ,required = false) String mid,
								 @RequestParam(value = "type" ,required = false) String type,
								 @RequestParam(value = "status" ,required = false) String status,
								 @RequestParam(value = "isGetTotal" ,required = false) String isGetTotal,
								 @RequestParam(value = "currentPage" ,required = false) String currentPageStr,
								 @RequestParam(value = "pageSize" ,required = false) String pageSizeStr) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtils.isNotBlank(mid)){
			IAppMessage appMessage = appMessageService.findById(Integer.parseInt(mid));
			return Result.createSuccessResult(appMessage,"获取用户消息成功");
		}else{
			int currentPage = 1;
			int pageSize = 10;
			if(StringUtils.isNotBlank(currentPageStr)){
				currentPage = Integer.parseInt(currentPageStr);
			}
			if(StringUtils.isNotBlank(pageSizeStr)){
				pageSize = Integer.parseInt(pageSizeStr);
			}
			IAppMessage appMessage = new AppMessageDTO();
			appMessage.setUid(uId);
			if (!StringUtil.isNullOrBlank(type)){
				appMessage.setType(Integer.parseInt(type));
			}
			if (!StringUtil.isNullOrBlank(status)){
				appMessage.setStatus(Integer.parseInt(status));
			}
			PageHelper.startPage(currentPage, pageSize);
			List<IAppMessage> appMessages = appMessageService.findOrderBy(appMessage);
			PageInfo<IAppMessage> page = new PageInfo(appMessages);
			if (StringUtil.isNullOrBlank(isGetTotal)|| !"1".equals(isGetTotal)){
				DataWithPageModel<IAppMessage> dataWithPageModel = new DataWithPageModel<>();
				dataWithPageModel.setData(appMessages);
				dataWithPageModel.setPageInfo(page);
				return Result.createSuccessResult(dataWithPageModel,"获取用户消息列表成功");
			}else {
				Map messageNumber = new HashMap();
				messageNumber.put("total",page.getTotal());
				return Result.createSuccessResult(messageNumber,"获取用户消息数量成功");
			}

		}
	}

	@RequestMapping(value = "/updateUserMessage" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "更新用户消息列表状态", httpMethod = "GET", response = Result.class, notes = "传入参数消息id")
	public Object updateUserMessage(@RequestHeader(value = "Authorization") String token,
								 @RequestParam(value = "mid") String mid) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(mid)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		return appMessageService.updateUserMessage(uId,mid);
	}

	@RequestMapping(value = "/getIsCertification" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取用户是否认证完成", httpMethod = "GET", response = Result.class, notes = "获取用户是否认证完成")
	public Object getIsCertification(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		Integer authority = appUser.getAuthority();
		if (authority !=null && authority > Constants.AHORITY_LOW){
			return Result.createSuccessResult(true,"该用户认证完成");
		}else{
			return Result.createSuccessResult(false,"该用户认证未完成");
		}
	}

	@RequestMapping(value = "/getIsSecondaryLoan" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取用户是否是二次借款", httpMethod = "GET", response = Result.class, notes = "获取用户是否是二次借款")
	public Object getIsSecondaryLoan(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		//如果有借款记录则不能再次借款
		IAppMoneyDetail borrowMoneyDetail = new AppMoneyDetailDTO();
		borrowMoneyDetail.setUid(uId);
		borrowMoneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
		borrowMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
		List<IAppMoneyDetail> appMoneyDetails1 = appMoneyDetailService.find(borrowMoneyDetail);
		if (appMoneyDetails1!=null && appMoneyDetails1.size()>0){
			return Result.create(ResultCode.SERVICE_ERROR).setMessage("当前已有借款申请在处理审核中");
		}

		IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
		appMoneyDetail.setUid(uId);
		appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
		appMoneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
		List<IAppMoneyDetail> appMoneyDetails2 = appMoneyDetailService.find(appMoneyDetail);
		if (appMoneyDetails2!=null&&appMoneyDetails2.size()>0){
			return Result.createSuccessResult(true,"该用户为二次借款");
		}else{
			return Result.createSuccessResult(false,"该用户还未借款成功");
		}
	}

	@RequestMapping(value = "/getIdCardRecognizeInfo" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取人脸识别结果", httpMethod = "POST", response = Result.class,
			notes = "传入身份证base64,返回识别结果")
	public Object getIdCardRecognizeInfo(@RequestHeader(value = "Authorization") String token,
										 @RequestParam(value = "idFrontImgBase64") String idFrontImgBase64,
										 @RequestParam(value = "idBackImgBase64") String idBackImgBase64) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(idBackImgBase64)||StringUtil.isNullOrBlank(idFrontImgBase64)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		YituUtil yituUtil = new YituUtil();
		String identityCardFrontInfo = yituUtil.idCardRecognize(idFrontImgBase64);
		String identityCardBackInfo = yituUtil.idCardRecognize(idBackImgBase64);
		if (!"error".equals(identityCardFrontInfo)&& !"error".equals(identityCardBackInfo)) {
			IdCardRecognizeResultModel frontInfo = JsonUtil.json2Object(identityCardFrontInfo, IdCardRecognizeResultModel.class);
			IdCardRecognizeResultModel backInfo = JsonUtil.json2Object(identityCardBackInfo, IdCardRecognizeResultModel.class);
			if (frontInfo.getRtn()!=0){
				return Result.createErrorResult().setMessage("获取身份证正面识别结果失败:"+frontInfo.getMessage());
			}else if (backInfo.getRtn()!=0){
				return Result.createErrorResult().setMessage("获取身份证背面识别结果失败:"+backInfo.getMessage());
			}else {
				//身份证只能添加一次
				String citizenId = frontInfo.getIdcard_ocr_result().getCitizen_id();
				IAppUser appUser = new AppUserDTO();
				appUser.setUserIdNumber(citizenId);
				List<IAppUser> appUsers = appUserService.find(appUser);
				if (appUsers!=null && appUsers.size()>0){
					return Result.createErrorResult().setMessage("无法通过认证，该身份证已经被使用");
				}

				Map<String,Object> resultMap = new HashMap<>();
				resultMap.put("citizenId",citizenId);
				resultMap.put("name",frontInfo.getIdcard_ocr_result().getName());
				resultMap.put("idcardTypeFront",frontInfo.getIdcard_ocr_result().getIdcard_type());
				resultMap.put("idcardTypeBack",backInfo.getIdcard_ocr_result().getIdcard_type());
				return Result.createSuccessResult(resultMap, "获取身份证识别结果成功");
			}
		}else {
			return Result.createErrorResult().setMessage("识别接口调用失败");
		}
	}

	@RequestMapping(value = "/getFaceThanIdcardInfo" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "获取人脸身份证比对结果", httpMethod = "POST", response = Result.class,
			notes = "传入人脸大礼包base64，身份证base64返回识别结果")
	public Object getFaceThanIdcardInfo(@RequestHeader(value = "Authorization" ) String token,
										   @RequestParam(value = "faceBase64") String faceBase64,
										   @RequestParam(value = "idcardBase64") String idcardBase64 ) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(faceBase64)||StringUtil.isNullOrBlank(idcardBase64)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		YituUtil yituUtil = new YituUtil();
		String faceThanIdcardInfo = yituUtil.faceThanIdcard(idcardBase64,faceBase64);
		if (!"error".equals(faceThanIdcardInfo)) {
			FaceThanIdcardResultModel faceThanIdcardResultModel = JsonUtil.json2Object(faceThanIdcardInfo, FaceThanIdcardResultModel.class);
			if (faceThanIdcardResultModel.getRtn()==0){
				Map<String,Object> resultMap = new HashMap<>();
				resultMap.put("pairVerifyResult",faceThanIdcardResultModel.getPair_verify_result());
				resultMap.put("pairVerifySimilarity",faceThanIdcardResultModel.getPair_verify_similarity());
				resultMap.put("message",faceThanIdcardResultModel.getMessage());
				resultMap.put("queryImageContents",faceThanIdcardResultModel.getQuery_image_package_result().getQuery_image_contents());
				return Result.createSuccessResult(resultMap, "获取比对结果成功");
			}else {
				return Result.createErrorResult().setMessage("获取比对结果失败:"+faceThanIdcardResultModel.getMessage());
			}
		}else {
			return Result.createErrorResult().setMessage("比对接口调用失败");
		}
	}

	@RequestMapping(value = "/test")
	@ApiIgnore
	public String test(){
		return "zhimasuccess";
	}

	/*@RequestMapping(value = "/getZhimaCertificationUrl" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取芝麻认证Url和身份证名字和号码", httpMethod = "GET", response = Result.class,
			notes = "传入身份证带人脸的那面图片的base64,返回芝麻认证Url和身份证名字和号码")
	public Object getZhimaCertificationUrl(
			@RequestHeader(value = "Authorization" ) String token,
			@RequestParam(value = "idFrontImgBase64") String idFrontImgBase64,
			@RequestParam(value = "idBackImgBase64") String idBackImgBase64 ) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		if (StringUtil.isNullOrBlank(idBackImgBase64)||StringUtil.isNullOrBlank(idFrontImgBase64)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		String identityCardFrontInfo = IdCardRecognitionUtil.recognize(idFrontImgBase64,IdCardRecognitionUtil.TYPE_FRONT);
		if (!"error".equals(identityCardFrontInfo)) {
			String identityCardBackInfo = IdCardRecognitionUtil.recognize(idBackImgBase64, IdCardRecognitionUtil.TYPE_BACK);
			if (!"error".equals(identityCardBackInfo)) {
				IdCardRecognitionResultModel idCardRecognitionResultModel = JsonUtil.json2Object(identityCardBackInfo, IdCardRecognitionResultModel.class);
				String name = idCardRecognitionResultModel.getResult().getName();
				String identityNumber = idCardRecognitionResultModel.getResult().getNumber();
				log.debug("name = " + name + " identityNumber = " + identityNumber);
				if (!StringUtil.isNullOrEmpty(name) && !StringUtil.isNullOrEmpty(identityNumber)) {
					ZhimaUtil zhimaUtil = new ZhimaUtil();
					String bizNo = zhimaUtil.zhimaCustomerCertificationInitialize(name, identityNumber);
					if (!"error".equals(bizNo) && bizNo != null) {
						String url = zhimaUtil.zhimaCustomerCertificationCertify(token, bizNo);
						if (!"error".equals(url) && url != null) {
							ZhimaInfoModel zhimaInfoModel = new ZhimaInfoModel();
							zhimaInfoModel.setUrl(url);
							zhimaInfoModel.setIdName(name);
							zhimaInfoModel.setIdNumber(identityNumber);
							return Result.createSuccessResult(zhimaInfoModel, "获取芝麻认证Url成功");
						} else {
							return Result.createErrorResult().setMessage("获取芝麻认证Url失败");
						}
					} else {
						return Result.createErrorResult().setMessage("获取BizNo失败");
					}
				} else {
					return Result.createErrorResult().setMessage("身份证名字或号码未识别出来");
				}
			} else {
				return Result.createErrorResult().setMessage("身份证背面识别失败");
			}
		}else {
			return Result.createErrorResult().setMessage("身份证正面识别失败");
		}
	}

	//芝麻人脸识别回调接口
	@RequestMapping(value = "/zhimaCertificationCallback" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiIgnore
	public String zhimaCertificationCallback(HttpServletRequest request) throws Exception{
		String token = request.getParameter("token");
		String params = request.getParameter("params");
		String sign = request.getParameter("sign");

		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return "token error";
		}
		Integer uid = ((SimpleToken) tokenValidResult).getId();

		ZhimaUtil zhimaUtil = new ZhimaUtil();
		String result = zhimaUtil.getResult(params,sign);
		log.debug("renxinhua uid = "+uid +" result = "+result);
		Map map = StringUtil.urlSplit(result);
		String passed = "";
		if (map.get("passed")!=null){
			passed = map.get("passed").toString();
		}
		String failed_reason = "";
		if (map.get("failed_reason")!=null){
			failed_reason = map.get("failed_reason").toString();
		}

		IAppUser appUser = appUserService.findById(uid);
		if (appUser!=null){
			String alias = appUser.getJpushAlias();
			if (Boolean.parseBoolean(passed)){
				appUser.setImgFace("true");
				appUser.setAuthority(Constants.AHORITY_MEDIUM);
				appUserService.update(appUser);
				if (!StringUtil.isNullOrBlank(alias)) {
					JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, "认证成功token="+
							CustomToken.generate(new SimpleToken(uid, Constants.AHORITY_MEDIUM))));
				}
				return "success";
			}else {
				appUser.setImgFace("false");
				appUserService.update(appUser);
				if (!StringUtil.isNullOrBlank(alias)) {
					JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, "认证失败"));
				}
				return "error "+failed_reason;
			}
		}else {
			return "无此用户";
		}
	}*/
}