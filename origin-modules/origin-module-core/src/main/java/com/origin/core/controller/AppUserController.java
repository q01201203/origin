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
public class AppUserController {

	Logger log = LoggerFactory.getLogger(AppUserController.class);
	@Autowired
	private AppUserService appUserService;

	@RequestMapping(value = "/user/login")
	@ResponseBody
	public Object login(HttpServletRequest request) throws Exception {
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
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
	public Object register(HttpServletRequest request) throws Exception {
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
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

	@RequestMapping(value = "/user/resetPwd")
	@ResponseBody
	public Object resetPwd(HttpServletRequest request) throws Exception {
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
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
		return Result.createSuccessResult();
	}

	@RequestMapping(value = "/user/updateMaxMoney")
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
	}

	@RequestMapping(value = "/user/addAlipayAccount")
	@ResponseBody
	public Object addAlipayAccout(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String alipayUsername = request.getParameter("alipayUsername");
		String alipayUseraccout = request.getParameter("alipayUseraccout");
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
		return Result.createSuccessResult();
	}

	@RequestMapping(value = "/user/addFace")
	@ResponseBody
	public Object addFace(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String face = request.getParameter("face");
		System.out.println("请求的face为" + face);
		if (StringUtil.isNullOrBlank(face)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgFace(face);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult();
	}

	@RequestMapping(value = "/user/addIdInfo")
	@ResponseBody
	public Object addIdInfo(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String idFrontImg = request.getParameter("idFrontImg");
		String idBackImg = request.getParameter("idBackImg");
		String idName = request.getParameter("idName");
		String idNumber = request.getParameter("idNumber");
		String category = request.getParameter("category");
		System.out.println("请求的idFrontImg为" + idFrontImg + "\n请求的idBackImg为" +
				idBackImg+ "\n请求的idName为" +
				idName+ "\n请求的idNumber为" +
				idNumber+ "\n请求的category为" +
				category);
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

	@RequestMapping(value = "/user/userInfo")
	@ResponseBody
	public Object userInfo(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		return Result.createSuccessResult(appUser,"返回信息成功");
	}
}