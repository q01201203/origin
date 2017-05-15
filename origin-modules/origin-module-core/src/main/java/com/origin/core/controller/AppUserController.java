package com.origin.core.controller;

import com.alibaba.fastjson.JSON;
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
	public String login(HttpServletRequest req) throws Exception {
		String mobile = (String) req.getParameter("umobile");
		String pwd = (String) req.getParameter("upwd");
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return JSON.toJSONString(Result.create(ResultCode.VALIDATE_ERROR));
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		Result loginResult = appUserService.login(appUser);
		return JSON.toJSONString(loginResult);
	}

	@RequestMapping(value = "/user/register")
	@ResponseBody
	public String register(HttpServletRequest req) throws Exception {
		String mobile = (String) req.getParameter("umobile");
		String pwd = (String) req.getParameter("upwd");
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return JSON.toJSONString(Result.create(ResultCode.VALIDATE_ERROR));
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		Result loginResult = appUserService.saveRegisterInfo(appUser);
		return JSON.toJSONString(loginResult);
	}
}