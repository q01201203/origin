package com.origin.core.controller;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/app")
public class AppUserController {

	Logger log = LoggerFactory.getLogger(AppUserController.class);
	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/user/login")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		String mobile = (String) request.getParameter("mobile");
		String pwd = (String) request.getParameter("pwd");
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		if (appUserService.findOne(appUser)){
			return Result.createSuccessResult();
		}
		return Result.createErrorResult().setMessage("用户名密码错误");
	}

	@RequestMapping(value = "/user/register")
	@ResponseBody
	public Object register(HttpServletRequest request) throws Exception {
		String mobile = (String) request.getParameter("mobile");
		String pwd = (String) request.getParameter("pwd");
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		appUserService.save(appUser);
		return Result.createSuccessResult();
	}
}