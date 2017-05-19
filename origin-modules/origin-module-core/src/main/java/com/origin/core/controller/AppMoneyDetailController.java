package com.origin.core.controller;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppMoneyDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/app")
public class AppMoneyDetailController {

	Logger log = LoggerFactory.getLogger(AppMoneyDetailController.class);
	@Autowired
	private AppMoneyDetailService appMoneyDetailService;

	@RequestMapping("/user/saveMoney")
	@ResponseBody
	public Object borrowMoney(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request, Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String money = request.getParameter("money");
		String type = request.getParameter("type");
		System.out.println("请求的money为" + money+ "\n请求的type为" + type);
		if (StringUtil.isNullOrBlank(money)||StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("有参数为null");
		}
		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			String repayTime = request.getParameter("repayTime");
			System.out.println("请求的repayTime为" + repayTime);
			if (StringUtil.isNullOrBlank(repayTime)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("有参数为null");
			}
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			appMoneyDetail.setCreateDate(new Date());
			appMoneyDetail.setMoney(Integer.parseInt(money));
			appMoneyDetail.setRepayTime(Integer.parseInt(repayTime));
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
			appMoneyDetail.setUid(uId);
			appMoneyDetailService.save(appMoneyDetail);
			return Result.createSuccessResult().setMessage("借款成功");
		}else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			String repayWay = request.getParameter("repayWay");
			System.out.println("请求的repayTime为" + repayWay);
			if (StringUtil.isNullOrBlank(repayWay)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("有参数为null");
			}
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			appMoneyDetail.setCreateDate(new Date());
			appMoneyDetail.setMoney(Integer.parseInt(money));
			appMoneyDetail.setRepayWay(Integer.parseInt(repayWay));
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
			appMoneyDetail.setUid(uId);
			appMoneyDetailService.save(appMoneyDetail);
			return Result.createSuccessResult().setMessage("还款成功");
		}else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("type类型错误");
		}
	}

	@RequestMapping("/user/getMoney")
	@ResponseBody
	public Object getBorrowMoney(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request, Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String type = request.getParameter("type");
		System.out.println("请求的type为" + type);
		if (StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("有参数为null");
		}

		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
			appMoneyDetail.setUid(uId);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("借款信息查询成功");
		} else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
			appMoneyDetail.setUid(uId);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("还款信息查询成功");
		} else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("type类型错误");
		}
	}
}