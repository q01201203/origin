package com.origin.core.controller.app;

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
import util.HttpSender;

import java.util.List;

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
			notes = "必填参数mobile，type(1:注册 2:重置密码 3:借钱) ")
	public Object send(@RequestParam(value = "mobile") String mobile ,
					   @RequestParam(value = "type") String type) throws Exception{
		if (StringUtil.isNullOrBlank(mobile) || StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		switch (type){
			case IAppValidcode.TYPE_REGISTER:
				List<IAppUser> appUsers = appUserService.find(appUser);
				if (appUsers!=null&&appUsers.size()>0){
					appUser = appUsers.get(0);
				}else{
					appUser = null;
				}
				if (appUser==null){
					if (saveValidcode(mobile, type)){
						return Result.createSuccessResult().setMessage("发送验证码成功");
					}else {
						return Result.createErrorResult().setMessage("短信平台出错");
					}
				}else {
					return Result.createErrorResult().setMessage("用户已存在");
				}
			case IAppValidcode.TYPE_RESETPWD:
				appUsers = appUserService.find(appUser);
				if (appUsers!=null&&appUsers.size()>0){
					appUser = appUsers.get(0);
				}else{
					appUser = null;
				}
				if (appUser!=null){
					if (saveValidcode(mobile, type)){
						return Result.createSuccessResult().setMessage("发送验证码成功");
					}else {
						return Result.createErrorResult().setMessage("短信平台出错");
					}
				}else {
					return Result.createErrorResult().setMessage("用户不存在");
				}
			case IAppValidcode.TYPE_BORROW:
				if (saveValidcode(mobile, type)){
					return Result.createSuccessResult().setMessage("发送验证码成功");
				}else {
					return Result.createErrorResult().setMessage("短信平台出错");
				}
		}
		return Result.createErrorResult().setMessage("type类型不存在");
	}

	private boolean saveValidcode(String mobile, String type) {
		//int validcode = 110110;
		int validcode = HttpSender.send(mobile);
		if (validcode < 0){
			return false;
		}
		IAppValidcode appValidcode = new AppValidcodeDTO();
		appValidcode.setMobile(mobile);
		appValidcode.setValidcode(String.valueOf(validcode));
		appValidcode.setType(Integer.parseInt(type));
		appValidcodeService.save(appValidcode);
		return true;
	}

	@RequestMapping(value = "/validate",method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户验证验证码", httpMethod = "GET", response = Result.class, notes = "必填参数" +
			"mobile,type(1:注册 2:重置密码 3:借钱),validcode")
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
		IAppValidcode appValidcodeResult;
		List<IAppValidcode> appValidcodeResults = appValidcodeService.find(appValidcode);
		if (appValidcodeResults!=null&&appValidcodeResults.size()>0){
			appValidcodeResult = appValidcodeResults.get(0);
		}else{
			appValidcodeResult = null;
		}
		if (appValidcodeResult!=null){
			appValidcodeResult.setStatus(IAppValidcode.STATUS_NO);
			appValidcodeService.update(appValidcodeResult);
			return Result.createSuccessResult().setMessage("验证码验证成功");
		}else {
			return Result.createErrorResult().setMessage("验证码错误");
		}
	}
}