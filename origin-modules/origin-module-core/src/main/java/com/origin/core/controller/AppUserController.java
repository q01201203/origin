package com.origin.core.controller;

import com.alibaba.fastjson.JSON;
import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
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
		int uid = (int) req.getAttribute("uid");
		int authority = (int) req.getAttribute("authority");
		System.out.println("lic uid = "+ uid +" authority = "+authority);
		if (!CustomToken.check(authority, Constants.AHORITY_LOW)){
			return JSON.toJSONString(Result.create(ResultCode.SSO_PERMISSION_ERROR));
		}
		if (req.getAttribute("uid")!=null && req.getAttribute("authority")!=null){

		}else {
			return JSON.toJSONString(Result.create(ResultCode.VALIDATE_ERROR));
		}

		String mobile = (String) req.getParameter("umobile");
		String pwd = (String) req.getParameter("upwd");
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return JSON.toJSONString(Result.createErrorResult());
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(pwd);
		Result loginResult = appUserService.login(appUser);
		return JSON.toJSONString(loginResult);
	}
}