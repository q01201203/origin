package com.origin.core.controller.app;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.*;
import com.origin.core.service.*;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
	private AppTaskService appTaskService;

	@Autowired
	private AppUserTaskService appUserTaskService;

	@Autowired
	private AppFeedbackService appFeedbackService;

	//query
	@RequestMapping(value = "/login" , method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户登录", httpMethod = "POST", response = Result.class, notes = "登录返回一个token")
	public Object login(@RequestParam(value = "mobile") String mobile ,
						@RequestParam(value = "pwd") String pwd) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
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


	//insert
	@RequestMapping(value = "/register")
	@ResponseBody
	@ApiOperation(value = "app用户注册", httpMethod = "POST", response = Result.class, notes = "注册")
	public Object register(@RequestParam(value = "mobile") String mobile ,
						   @RequestParam(value = "pwd") String pwd,
						   @RequestParam(value = "alipayUsername") String alipayUsername ,
						   @RequestParam(value = "alipayUseraccout") String alipayUseraccout) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd +"请求的alipayusername为"
				+ alipayUsername + "\n请求的alipayuseraccout为" + alipayUseraccout);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)||StringUtil.isNullOrBlank(alipayUsername)
				||StringUtil.isNullOrBlank(alipayUseraccout)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		IAppUser appUser = new AppUserDTO();
		appUser.setMobile(mobile);
		appUser = appUserService.findFirst(appUser);
		if (appUser!=null){
			return Result.createErrorResult().setMessage("用户已存在");
		}else {
			IAppUser appUser1 = new AppUserDTO();
			appUser1.setMobile(mobile);
			appUser1.setPwd(Md5Util.generatePassword(pwd));
			appUser1.setAlipayUsername(alipayUsername);
			appUser1.setAlipayUseraccout(alipayUseraccout);
			appUserService.save(appUser1);
			return Result.createSuccessResult().setMessage("注册成功");
		}
	}

	//update
	@RequestMapping(value = "/resetPwd")
	@ResponseBody
	@ApiOperation(value = "app用户重置密码", httpMethod = "POST", response = Result.class, notes = "重置密码")
	public Object resetPwd(@RequestParam(value = "mobile") String mobile ,
						   @RequestParam(value = "pwd") String pwd) throws Exception {
		System.out.println("请求的mobile为" + mobile + "\n请求的passWord为" + pwd);
		if (StringUtil.isNullOrBlank(mobile)||StringUtil.isNullOrBlank(pwd)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
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

	@RequestMapping(value = "/addPayPwd" ,method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "app用户添加支付密码", httpMethod = "POST", response = Result.class, notes = "添加支付密码")
	public Object addPayPwd(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "payPwd") String payPwd) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的payPwd为" + payPwd);
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
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
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

	/*@RequestMapping(value = "/addAlipayAccount" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加支付宝账号", httpMethod = "GET", response = Result.class, notes = "addAlipayAccout")
	public Object addAlipayAccout(@RequestHeader(value = "Authorization" ) String token,
								  @RequestParam(value = "alipayUsername") String alipayUsername ,
								  @RequestParam(value = "alipayUseraccout") String alipayUseraccout) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的alipayusername为" + alipayUsername + "\n请求的alipayuseraccout为" +
				alipayUseraccout);
		if (StringUtil.isNullOrBlank(alipayUsername)||StringUtil.isNullOrBlank(alipayUseraccout)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setAlipayUsername(alipayUsername);
		appUser.setAlipayUseraccout(alipayUseraccout);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加成功");
	}*/

	@RequestMapping(value = "/addFace" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加人脸图片", httpMethod = "GET", response = Result.class ,
			notes = "添加人脸照片返回一个更高权限的 token")
	public Object addFace(@RequestHeader(value = "Authorization" ) String token,
						  @RequestParam(value = "face") String face) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的face为" + face);
		if (StringUtil.isNullOrBlank(face)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgFace(face);
		appUser.setAuthority(Constants.AHORITY_MEDIUM);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult(CustomToken.generate(new SimpleToken(uId, Constants.AHORITY_MEDIUM))
				,"认证成功");
	}

	@RequestMapping(value = "/addIdInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加身份证信息", httpMethod = "GET", response = Result.class, notes = "添加身份证信息")
	public Object addIdInfo(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "idFrontImg") String idFrontImg,
							@RequestParam(value = "idBackImg") String idBackImg,
							@RequestParam(value = "idName") String idName,
							@RequestParam(value = "idNumber") String idNumber,
							@RequestParam(value = "category") String category) throws Exception{
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
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgIdFront(idFrontImg);
		appUser.setImgIdBack(idBackImg);
		appUser.setUserIdName(idName);
		appUser.setUserIdNumber(idNumber);
		appUser.setCategory(Integer.parseInt(category));
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加成功");
	}

	@RequestMapping(value = "/addPortrait" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加头像", httpMethod = "GET", response = Result.class, notes = "添加头像")
	public Object addPortrait(@RequestHeader(value = "Authorization" ) String token,
							@RequestParam(value = "portrait") String portrait) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的portrait为" + portrait);
		if (StringUtil.isNullOrBlank(portrait)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setImgPortrait(portrait);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加头像成功");
	}

	@RequestMapping(value = "/addNickname" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户添加用户昵称", httpMethod = "GET", response = Result.class, notes = "添加昵称")
	public Object addNickname(@RequestHeader(value = "Authorization" ) String token,
							  @RequestParam(value = "nickname") String nickname) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的nickname为" + nickname);
		if (StringUtil.isNullOrBlank(nickname)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppUser appUser = new AppUserDTO();
		appUser.setId(uId);
		appUser.setNickname(nickname);
		appUser.setUpdateDate(new Date());
		appUserService.update(appUser);
		return Result.createSuccessResult().setMessage("添加昵称成功");
	}

	//other operation
	@RequestMapping(value = "/savePersonInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存个人信息", httpMethod = "GET", response = Result.class,
			notes = "保存用户详细信息 category(1:学生(infoSchool,infoDepartment,infoClass,infoRoomNumber) " +
					"2:社会人群(infoCompanyName,infoCompanyAddress,infoQq,infoWeixin,infoHome))")
	@ApiImplicitParams({@ApiImplicitParam(name = "infoMobile" ,value = "infoMobile",paramType = "query"
			,dataType = "string") ,
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
	public Object addIdInfo(@RequestHeader(value = "Authorization") String token,
							@RequestParam(value = "category") String category,
							HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的category为" + category);
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
		String infoMobile = request.getParameter("infoMobile");
		String infoCompanyName = request.getParameter("infoCompanyName");
		String infoCompanyAddress = request.getParameter("infoCompanyAddress");
		String infoQq = request.getParameter("infoQq");
		String infoWeixin = request.getParameter("infoWeixin");
		String infoHome = request.getParameter("infoHome");
		String infoEmycontactRelation = request.getParameter("infoEmycontactRelation");
		String infoEmycontactMobile = request.getParameter("infoEmycontactMobile");
		String infoContactRelation = request.getParameter("infoContactRelation");
		String infoContactMobile = request.getParameter("infoContactMobile");
		System.out.println("请求的infoMobile为" + infoMobile + "\n请求的infoCompanyAddress为" +
				infoCompanyAddress +"\n请求的infoQq为" + infoQq+ "\n请求的infoWeixin为" +
				infoWeixin+ "\n请求的infoHome为" + infoHome+ "\n请求的infoEmycontactRelation为" +
				infoEmycontactRelation+ "\n请求的infoEmycontactMobile为" + infoEmycontactMobile+
				"\n请求的infoContactRelation为" + infoContactRelation+ "\n请求的infoContactMobile为" +
				infoContactMobile+ "\n请求的infoCompanyName为" + infoCompanyName);
		if (StringUtil.isNullOrBlank(infoMobile)||StringUtil.isNullOrBlank(infoCompanyAddress)
				||StringUtil.isNullOrBlank(infoQq)||StringUtil.isNullOrBlank(infoWeixin)
				||StringUtil.isNullOrBlank(infoHome)||StringUtil.isNullOrBlank(infoEmycontactRelation)
				||StringUtil.isNullOrBlank(infoEmycontactMobile)||StringUtil.isNullOrBlank(infoContactRelation)
				||StringUtil.isNullOrBlank(infoContactMobile)||StringUtil.isNullOrBlank(infoCompanyName)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		boolean save = false;
		IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
		appPersonDetail.setUid(uId);
		appPersonDetail = appPersonDetailService.findFirst(appPersonDetail);
		if (appPersonDetail == null){
			save = true;
			appPersonDetail = new AppPersonDetailDTO();
		}
		appPersonDetail.setInfoMobile(infoMobile);
		appPersonDetail.setInfoCompanyAddress(infoCompanyAddress);
		appPersonDetail.setInfoCompanyName(infoCompanyName);
		appPersonDetail.setInfoQq(infoQq);
		appPersonDetail.setInfoWeixin(infoWeixin);
		appPersonDetail.setInfoHome(infoHome);
		appPersonDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
		appPersonDetail.setInfoEmycontactMobile(infoEmycontactMobile);
		appPersonDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
		appPersonDetail.setInfoContactMobile(infoContactMobile);
		appPersonDetail.setUid(uId);
		if (save){
			appPersonDetailService.save(appPersonDetail);
		}else {
			appPersonDetailService.update(appPersonDetail);
		}
		return Result.createSuccessResult().setMessage("社会人群信息保存成功");
	}

	private Object saveStudentInfo(HttpServletRequest request, Integer uId) {
		String infoMobile = request.getParameter("infoMobile");
		String infoSchool = request.getParameter("infoSchool");
		String infoDepartment = request.getParameter("infoDepartment");
		String infoClass = request.getParameter("infoClass");
		String infoRoomNumber = request.getParameter("infoRoomNumber");
		String infoEmycontactRelation = request.getParameter("infoEmycontactRelation");
		String infoEmycontactMobile = request.getParameter("infoEmycontactMobile");
		String infoContactRelation = request.getParameter("infoContactRelation");
		String infoContactMobile = request.getParameter("infoContactMobile");
		System.out.println("请求的infoMobile为" + infoMobile + "\n请求的infoSchool为" +
				infoSchool+ "\n请求的infoDepartment为" + infoDepartment+ "\n请求的infoClass为" +
				infoClass+ "\n请求的infoRoomNumber为" + infoRoomNumber+ "\n请求的infoEmycontactRelation为" +
				infoEmycontactRelation+ "\n请求的infoEmycontactMobile为" + infoEmycontactMobile+
				"\n请求的infoContactRelation为" + infoContactRelation+ "\n请求的infoContactMobile为" +
				infoContactMobile);
		if (StringUtil.isNullOrBlank(infoMobile)||StringUtil.isNullOrBlank(infoSchool)
				||StringUtil.isNullOrBlank(infoDepartment)||StringUtil.isNullOrBlank(infoClass)
				||StringUtil.isNullOrBlank(infoRoomNumber)||StringUtil.isNullOrBlank(infoEmycontactRelation)
				||StringUtil.isNullOrBlank(infoEmycontactMobile)||StringUtil.isNullOrBlank(infoContactRelation)
				||StringUtil.isNullOrBlank(infoContactMobile)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		boolean save = false;
		IAppStuDetail appStuDetail = new AppStuDetailDTO();
		appStuDetail.setUid(uId);
		appStuDetail = appStuDetailService.findFirst(appStuDetail);
		if (appStuDetail == null){
			save = true;
			appStuDetail = new AppStuDetailDTO();
			System.out.println("licheng null");
		}

		appStuDetail.setInfoMobile(infoMobile);
		appStuDetail.setInfoSchool(infoSchool);
		appStuDetail.setInfoDepartment(infoDepartment);
		appStuDetail.setInfoClass(infoClass);
		appStuDetail.setInfoRoomnumber(infoRoomNumber);
		appStuDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
		appStuDetail.setInfoEmycontactMobile(infoEmycontactMobile);
		appStuDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
		appStuDetail.setInfoContactMobile(infoContactMobile);
		appStuDetail.setUid(uId);
		if (save){
			appStuDetailService.save(appStuDetail);
		}else {
			appStuDetailService.update(appStuDetail);
		}
		return Result.createSuccessResult().setMessage("学生信息保存成功");
	}

	@RequestMapping(value = "/saveBankInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存银行信息", httpMethod = "GET", response = Result.class, notes = "保存用户银行信息")
	@ApiImplicitParams({@ApiImplicitParam(name = "bankName" ,value = "bankName",paramType = "query"
			,dataType = "string") ,@ApiImplicitParam(name = "bankNumber" ,value = "bankNumber",paramType = "query"
			,dataType = "string"),@ApiImplicitParam(name = "bankType" ,value = "bankType",paramType = "query"
			,dataType = "int"),@ApiImplicitParam(name = "bankMobile" ,value = "bankMobile",paramType = "query"
			,dataType = "string")})
	public Object addBankInfo(@RequestHeader(value = "Authorization" ) String token,
							   @ApiIgnore AppUserBankDTO appUserBank) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String bankName = appUserBank.getBankName();
		String bankNumber = appUserBank.getBankNumber();
		String bankMobile = appUserBank.getBankMobile();
		Integer bankType = appUserBank.getBankType();
		System.out.println("请求的bankName为" + bankName + "\n请求的bankNumber为" +
				bankNumber+ "\n请求的bankMobile为" + bankMobile+"\n请求的bankType为" + bankType);
		if (StringUtil.isNullOrBlank(bankName)||StringUtil.isNullOrBlank(bankNumber)
				||StringUtil.isNullOrBlank(bankMobile) ||bankType ==null){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
		boolean save = false;
		IAppUserBank appUserBankDTO = new AppUserBankDTO();
		appUserBankDTO.setUid(uId);
		appUserBankDTO = appUserBankService.findFirst(appUserBankDTO);
		if (appUserBankDTO == null){
			save = true;
			appUserBankDTO = new AppUserBankDTO();
			System.out.println("licheng null");
		}

		appUserBankDTO.setBankNumber(bankNumber);
		appUserBankDTO.setBankName(bankName);
		appUserBankDTO.setBankMobile(bankMobile);
		appUserBankDTO.setBankType(bankType);
		appUserBankDTO.setUid(uId);
		if (save){
			appUserBankService.save(appUserBankDTO);
		}else {
			appUserBankService.update(appUserBankDTO);
		}
		return Result.createSuccessResult().setMessage("银行卡信息保存成功");
	}

	@RequestMapping(value = "/submitMoney" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存钱信息", httpMethod = "GET", response = Result.class,
			notes = "提交借钱请求 借钱(askMoney、type=1、repayTimeType(1:7天 2:15天)) 还钱(askMoney、type=2、" +
					"repayWay(1:支付宝 2:银行卡))  提现(askMoney、type=3)  收入(type=4、taskId（任务ID）、" +
					"taskUserName(完成任务的用户名)、taskMobile(完成任务的手机号)))")
	public Object submitMoney(@RequestHeader(value = "Authorization" ) String token,
							  @RequestParam(value = "askMoney",required = false) String askMoney,
							  @RequestParam(value = "type") String type,
							  @RequestParam(value = "repayTimeType",required = false) String repayTimeType,
							  @RequestParam(value = "repayWay",required = false) String repayWay,
							  @RequestParam(value = "taskId",required = false) String taskId,
							  @RequestParam(value = "taskUserName",required = false) String taskUserName,
							  @RequestParam(value = "taskMobile",required = false) String taskMobile) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的money为" + askMoney + "\n请求的type为" + type);
		if (StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
		appMoneyDetail.setUid(uId);
		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			System.out.println("请求的repayTimeType为" + repayTimeType);
			if (StringUtil.isNullOrBlank(askMoney)||StringUtil.isNullOrBlank(repayTimeType)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
			}
			//借钱不能大于可借额度
			IAppUser appUser = appUserService.findById(uId);
			Double moneyMax = appUser.getMoneyMax();
			Double borrow = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
			Double repay = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
			Double needRepay = borrow - repay;
			Double borrowLine = moneyMax - needRepay;
			if (Double.parseDouble(askMoney)>borrowLine){
				return Result.create(ResultCode.SERVICE_ERROR).setMessage("借钱金额大于可借额度");
			}

			appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
			appMoneyDetail.setRepayTimeType(Integer.parseInt(repayTimeType));
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
			appMoneyDetailService.save(appMoneyDetail);
			return Result.createSuccessResult().setMessage("借款申请成功");
		}else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			System.out.println("请求的repayWay为" + repayWay);
			if (StringUtil.isNullOrBlank(askMoney)||StringUtil.isNullOrBlank(repayWay)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
			}
			//还钱不能大于需还金额
			Double borrow = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
			Double repay = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
			Double needRepay = borrow - repay;
			if (Double.parseDouble(askMoney)>needRepay){
				return Result.create(ResultCode.SERVICE_ERROR).setMessage("还钱金额大于需还金额");
			}

			appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
			appMoneyDetail.setRepayWay(Integer.parseInt(repayWay));
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
			appMoneyDetailService.save(appMoneyDetail);
			return Result.createSuccessResult().setMessage("还款申请成功");
		}else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(Integer.parseInt(type))){
			if (StringUtil.isNullOrBlank(askMoney)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
			}
			IAppUser appUser = appUserService.findById(uId);
			Double balance = appUser.getBalance();
			if (Double.parseDouble(askMoney)>balance){
				return Result.create(ResultCode.SERVICE_ERROR).setMessage("提现金额大于余额");
			}

			appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_WITHDRAW);
			appMoneyDetailService.save(appMoneyDetail);
			return Result.createSuccessResult().setMessage("提现申请成功");
		}else if (IAppMoneyDetail.TYPE_INCOME.equals(Integer.parseInt(type))){
			if (StringUtil.isNullOrBlank(taskId)||StringUtil.isNullOrBlank(taskUserName)||StringUtil.isNullOrBlank(taskMobile)){
				return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
			}
			appMoneyDetail.setTaskUsername(taskUserName);
			appMoneyDetail.setTaskMobile(taskMobile);
			return appMoneyDetailService.saveIncome(uId,Integer.parseInt(taskId),appMoneyDetail);
		}else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
	}

	@RequestMapping(value = "/getMoney" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户获取钱信息", httpMethod = "GET", response = Result.class,
			notes = "获取钱信息 钱使用money_actual字段 必传type(1:借钱 2:还钱 3:提现 4:收入) 可选status(1:待审核" +
					"2:审核通过 3:审核未通过)")
	public Object getMoney(@RequestHeader(value = "Authorization" ) String token,
						   @RequestParam(value = "type") String type,
						   @RequestParam(value = "status" ,required = false) String status) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的type为" + type);
		if (StringUtil.isNullOrBlank(type)){
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}

		IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
		appMoneyDetail.setUid(uId);
		if (!StringUtil.isNullOrBlank(status)){
			appMoneyDetail.setStatus(Integer.parseInt(status));
		}

		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("借款信息查询成功");
		} else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("还款信息查询成功");
		} else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_WITHDRAW);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("提现信息查询成功");
		} else if (IAppMoneyDetail.TYPE_INCOME.equals(Integer.parseInt(type))){
			appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
			List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.find(appMoneyDetail);
			//List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.findIncomeInfo(appMoneyDetail);
			return Result.createSuccessResult().setData(appMoneyDetails).setMessage("收入信息查询成功");
		} else {
			return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
		}
	}

	@RequestMapping(value = "/saveFeedback" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户保存反馈信息", httpMethod = "GET", response = Result.class, notes = "saveFeedback")
	public Object saveFeedback(@RequestHeader(value = "Authorization" ) String token,
							   @RequestParam(value = "content") String content) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的content为" + content);
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
	@ApiOperation(value = "获取app用户银行信息", httpMethod = "GET", response = Result.class, notes = "getUserBank")
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
				appStuDetail = appStuDetailService.findFirst(appStuDetail);
				return Result.createSuccessResult(appStuDetail,"获取用户学生信息成功");
			}else if (IAppUser.CATEGORY_PERSON.equals(appUser.getCategory())){
				IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
				appPersonDetail.setUid(uId);
				appPersonDetail = appPersonDetailService.findFirst(appPersonDetail);
				return Result.createSuccessResult(appPersonDetail,"获取用户社会人群信息成功");
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

		Double borrow = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
		Double repay = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
		Double withdraw = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_WITHDRAW);
		Double income = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_INCOME);
		Double repayBalance = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_INCOME,IAppMoneyDetail.REPAY_WAY_BALANCE);

		Double needRepay = borrow - repay;
		Double balance = income - withdraw - repayBalance;
		Double borrowLine = moneyMax - needRepay;
		HashMap<String,Double> moneyMap = new HashMap();
		moneyMap.put("borrowTotal",borrow);
		moneyMap.put("repayTotal",repay);
		moneyMap.put("withdrawTotal",withdraw);
		moneyMap.put("incomeTotal",income);
		moneyMap.put("repayBalance",repayBalance);
		moneyMap.put("needRepay",needRepay);
		moneyMap.put("balance",balance);
		moneyMap.put("moneyMax",moneyMax);
		moneyMap.put("borrowLine",borrowLine);
		return Result.createSuccessResult(moneyMap,"获取app用户钱信息");
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
}