package com.origin.core.controller;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.dto.AppValidcodeDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.service.AppValidcodeService;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppValidcode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/app")
public class AppValidcodeController {

	Logger log = LoggerFactory.getLogger(AppValidcodeController.class);
	@Autowired
	private AppValidcodeService appValidcodeService;

	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/validcode/send")
	@ResponseBody
	public Object send(HttpServletRequest request) throws Exception{
		String mobile = request.getParameter("mobile");
		String type = request.getParameter("type");
		if (StringUtil.isNullOrBlank(mobile) || StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		switch (type){
			case "1":
				if (!appUserService.findOne(appUser)){
					IAppValidcode appValidcode = new AppValidcodeDTO();
					appValidcode.setMobile(mobile);
					String validcode = "123456";
					appValidcode.setValidcode(validcode);
					appValidcode.setType(Integer.parseInt(type));
					appValidcode.setCreateDate(new Date());
					appValidcodeService.save(appValidcode);
					System.out.println("lic i = "+appValidcode.getId());
					return Result.createSuccessResult(validcode,"获取验证码成功");
				}else {
					return Result.createErrorResult().setMessage("用户已存在");
				}
		}
		return Result.createErrorResult();
	}

	@RequestMapping(value = "/validcode/validate")
	@ResponseBody
	public Object validate(HttpServletRequest request) throws Exception{
		String mobile = request.getParameter("mobile");
		String type = request.getParameter("type");
		String validcode = request.getParameter("validcode");
		if (StringUtil.isNullOrBlank(mobile) || StringUtil.isNullOrBlank(type) || StringUtil.isNullOrBlank(validcode)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppValidcode appValidcode = new AppValidcodeDTO();
		appValidcode.setMobile(mobile);
		appValidcode.setValidcode(validcode);
		appValidcode.setType(Integer.parseInt(type));
		appValidcode.setStatus(IAppValidcode.STATUS_YES);
		IAppValidcode appValidcodeResult = appValidcodeService.findFirst(appValidcode);
		if (appValidcodeResult!=null){
			IAppValidcode appValidcode1 = new AppValidcodeDTO();
			appValidcode1.setId(appValidcodeResult.getId());
			appValidcode1.setStatus(IAppValidcode.STATUS_NO);
			appValidcodeService.update(appValidcode1);
			return Result.createSuccessResult();
		}else {
			return Result.createErrorResult().setMessage("验证码错误");
		}
	}
}