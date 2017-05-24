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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/app/validcode")
@Api(value = "/app/validcode" ,description = "验证码API")
public class AppValidcodeController {

	Logger log = LoggerFactory.getLogger(AppValidcodeController.class);
	@Autowired
	private AppValidcodeService appValidcodeService;

	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/send" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户发送验证码", httpMethod = "GET", response = Result.class,
			notes = "type(1:注册 2:重置密码) return validcode")
	public Object send(@RequestParam(value = "mobile") String mobile ,
					   @RequestParam(value = "type") String type) throws Exception{
		if (StringUtil.isNullOrBlank(mobile) || StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		switch (type){
			case IAppValidcode.TYPE_REGISTER:
				appUser = appUserService.findFirst(appUser);
				if (appUser==null){
					String validcode = saveValidcode(mobile, type);
					return Result.createSuccessResult(validcode,"发送验证码成功");
				}else {
					return Result.createErrorResult().setMessage("用户已存在");
				}
			case IAppValidcode.TYPE_RESETPWD:
				appUser = appUserService.findFirst(appUser);
				if (appUser!=null){
					String validcode = saveValidcode(mobile, type);
					return Result.createSuccessResult(validcode,"发送验证码成功");
				}else {
					return Result.createErrorResult().setMessage("用户不存在");
				}
		}
		return Result.createErrorResult().setMessage("type类型不存在");
	}

	private String saveValidcode(String mobile, String type) {
		IAppValidcode appValidcode = new AppValidcodeDTO();
		appValidcode.setMobile(mobile);
		String validcode = "110110";
		appValidcode.setValidcode(validcode);
		appValidcode.setType(Integer.parseInt(type));
		appValidcodeService.save(appValidcode);
		System.out.println("lic i = "+appValidcode.getId());
		return validcode;
	}

	@RequestMapping(value = "/validate",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户验证验证码", httpMethod = "GET", response = Result.class, notes = "validate")
	public Object validate(@RequestParam(value = "mobile") String mobile ,
						   @RequestParam(value = "type") String type ,
						   @RequestParam(value = "validcode") String validcode) throws Exception{
		if (StringUtil.isNullOrBlank(mobile) || StringUtil.isNullOrBlank(type) || StringUtil.isNullOrBlank(validcode)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
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
			return Result.createSuccessResult().setMessage("验证码验证成功");
		}else {
			return Result.createErrorResult().setMessage("验证码错误");
		}
	}
}