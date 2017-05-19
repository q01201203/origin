package com.origin.core.controller;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/app")
@Api(value = "/app" ,description = "app用户操作API")
public class AppUserController {

	Logger log = LoggerFactory.getLogger(AppUserController.class);
	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/user/login" , method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户登录", httpMethod = "POST", response = Result.class, notes = "login")
	public Object login(@RequestParam(value = "mobile",required = true) String mobile ,
						@RequestParam(value = "pwd",required = true) String pwd) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		appUser = appUserService.findFirst(appUser);
		if (appUser!=null){
			return Result.createSuccessResult(CustomToken.generate(new SimpleToken(appUser.getId(),
					appUser.getAuthority())),"登录成功");
		}
		return Result.createErrorResult().setMessage("用户名密码错误");
	}

	@RequestMapping(value = "/user/register")
	@ResponseBody
	@ApiOperation(value = "app用户注册", httpMethod = "POST", response = Result.class, notes = "register")
	public Object register(@RequestParam(value = "mobile",required = true) String mobile ,
						   @RequestParam(value = "pwd",required = true) String pwd) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser.setPwd(Md5Util.generatePassword(pwd));
		appUserService.save(appUser);
		return Result.createSuccessResult().setMessage("注册成功");
	}

	@RequestMapping(value = "/user/resetPwd")
	@ResponseBody
	@ApiOperation(value = "app用户重置密码", httpMethod = "POST", response = Result.class, notes = "resetPwd")
	public Object resetPwd(@RequestParam(value = "mobile",required = true) String mobile ,
						   @RequestParam(value = "pwd",required = true) String pwd) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		IAppUser appUser2 = appUserService.findFirst(appUser);
		appUser.setId(appUser2.getId());
		appUser.setPwd(Md5Util.generatePassword(pwd));
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("重置密码成功");
	}

	/*@RequestMapping(value = "/user/updateMaxMoney")
	@ResponseBody
	public Object updateMaxMoney(HttpServletRequest request) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String zhimaCredit = request.getParameter("zhimaCredit");
		System.out.println("请求的zhimacredit为" + zhimaCredit);
		if (StringUtil.isNullOrBlank(zhimaCredit)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		//lic wait modify
		Integer maxMoney = 2000+Integer.parseInt(zhimaCredit);
		appUser.setMoneyMax(maxMoney);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult();
	}*/

	@RequestMapping(value = "/user/addAlipayAccount" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加支付宝账号", httpMethod = "GET", response = Result.class, notes = "addAlipayAccout")
	public Object addAlipayAccout(@RequestHeader(value = "Authorization" ,required = true) String token,
								  @RequestParam(value = "alipayUsername",required = true) String alipayUsername ,
								  @RequestParam(value = "alipayUseraccout",required = true) String alipayUseraccout) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的alipayusername为" + alipayUsername + "\n请求的alipayuseraccout为" +
				alipayUseraccout);
		if (StringUtil.isNullOrBlank(alipayUsername)||StringUtil.isNullOrBlank(alipayUseraccout)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setAlipayUsername(alipayUsername);
		appUser.setAlipayUseraccout(alipayUseraccout);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加成功");
	}

	@RequestMapping(value = "/user/addFace" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加人脸图片", httpMethod = "GET", response = Result.class, notes = "addFace")
	public Object addFace(@RequestHeader(value = "Authorization" ,required = true) String token,
						  @RequestParam(value = "face",required = true) String face) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的face为" + face);
		if (StringUtil.isNullOrBlank(face)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgFace(face);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加成功");
	}

	@RequestMapping(value = "/user/addIdInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加身份证信息", httpMethod = "GET", response = Result.class, notes = "addIdInfo")
	public Object addIdInfo(@RequestHeader(value = "Authorization" ,required = true) String token,
							@RequestParam(value = "idFrontImg",required = true) String idFrontImg,
							@RequestParam(value = "idBackImg",required = true) String idBackImg,
							@RequestParam(value = "idName",required = true) String idName,
							@RequestParam(value = "idNumber",required = true) String idNumber,
							@RequestParam(value = "category",required = true) String category) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的idFrontImg为" + idFrontImg + "\n请求的idBackImg为" +
				idBackImg+ "\n请求的idName为" + idName+ "\n请求的idNumber为" +
				idNumber+ "\n请求的category为" + category);
		if (StringUtil.isNullOrBlank(idFrontImg)||StringUtil.isNullOrBlank(idBackImg)
				||StringUtil.isNullOrBlank(idName)||StringUtil.isNullOrBlank(idNumber)
				||StringUtil.isNullOrBlank(category)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgIdFront(idFrontImg);
		appUser.setImgIdBack(idBackImg);
		appUser.setUserIdName(idName);
		appUser.setUserIdNumber(idNumber);
		appUser.setCategory(Integer.parseInt(category));
		appUser.setAuthority(Constants.AHORITY_MEDIUM);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult(CustomToken.generate(new SimpleToken(uId,
				Constants.AHORITY_MEDIUM)),"认证成功");
	}

	@RequestMapping(value = "/user/userInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户信息", httpMethod = "GET", response = Result.class, notes = "userInfo")
	public Object userInfo(@RequestHeader(value = "Authorization" ,required = true) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		return Result.createSuccessResult(appUser,"获取信息成功");
	}

	@RequestMapping(value = "/user/addPayPwd" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户添加支付密码", httpMethod = "POST", response = Result.class, notes = "addPayPwd")
	public Object addPayPwd(@RequestHeader(value = "Authorization" ,required = true) String token,
							@RequestParam(value = "payPwd",required = true) String payPwd) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的payPwd为" + payPwd);
		if (StringUtil.isNullOrBlank(payPwd)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setPayPwd(payPwd);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加支付密码成功");
	}
}