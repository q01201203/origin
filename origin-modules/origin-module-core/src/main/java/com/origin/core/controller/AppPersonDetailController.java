package com.origin.core.controller;

import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppPersonDetailDTO;
import com.origin.core.dto.AppStuDetailDTO;
import com.origin.core.dto.AppUserBankDTO;
import com.origin.core.service.AppPersonDetailService;
import com.origin.core.service.AppStuDetailService;
import com.origin.core.service.AppUserBankService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppPersonDetail;
import com.origin.data.entity.IAppStuDetail;
import com.origin.data.entity.IAppUserBank;
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
 * @author lc
 */
@Controller
@RequestMapping("/app")
public class AppPersonDetailController {

	Logger log = LoggerFactory.getLogger(AppPersonDetailController.class);
	@Autowired
	private AppPersonDetailService appPersonDetailService;

	@Autowired
	private AppStuDetailService appStuDetailService;

	@Autowired
	private AppUserBankService appUserBankService;

	@RequestMapping(value = "/user/savePersonInfo")
	@ResponseBody
	public Object addIdInfo(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String category = request.getParameter("category");
		System.out.println("请求的category为" + category);
		if (StringUtil.isNullOrBlank(category)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		//1为学生 2为社会人群
		if ("1".equals(category)){
			return saveStudentInfo(request, uId);
		}else if ("2".equals(category)){
			return savePersonInfo(request, uId);
		}
		return Result.createErrorResult();
	}

	private Object savePersonInfo(HttpServletRequest request, Integer uId) {
		String infoMobile = request.getParameter("infoMobile");
		String infoCompanyAddress = request.getParameter("infoCompanyAddress");
		String infoQq = request.getParameter("infoQq");
		String infoWeixin = request.getParameter("infoWeixin");
		String infoHome = request.getParameter("infoHome");
		String infoEmycontactRelation = request.getParameter("infoEmycontactRelation");
		String infoEmycontactMobile = request.getParameter("infoEmycontactMobile");
		String infoContactRelation = request.getParameter("infoContactRelation");
		String infoContactMobile = request.getParameter("infoContactMobile");
		System.out.println("请求的infoMobile为" + infoMobile + "\n请求的infoCompanyAddress为" +
                infoCompanyAddress+ "\n请求的infoQq为" + infoQq+ "\n请求的infoWeixin为" +
                infoWeixin+ "\n请求的infoHome为" + infoHome+ "\n请求的infoEmycontactRelation为" +
                infoEmycontactRelation+ "\n请求的infoEmycontactMobile为" + infoEmycontactMobile+
                "\n请求的infoContactRelation为" + infoContactRelation+ "\n请求的infoContactMobile为" +
                infoContactMobile);
		if (StringUtil.isNullOrBlank(infoMobile)||StringUtil.isNullOrBlank(infoCompanyAddress)
                ||StringUtil.isNullOrBlank(infoQq)||StringUtil.isNullOrBlank(infoWeixin)
                ||StringUtil.isNullOrBlank(infoHome)||StringUtil.isNullOrBlank(infoEmycontactRelation)
                ||StringUtil.isNullOrBlank(infoEmycontactMobile)||StringUtil.isNullOrBlank(infoContactRelation)
                ||StringUtil.isNullOrBlank(infoContactMobile)){
            return Result.create(ResultCode.VALIDATE_ERROR);
        }

		IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
		appPersonDetail.setCreateDate(new Date());
		appPersonDetail.setInfoMobile(infoMobile);
		appPersonDetail.setInfoCompanyAddress(infoCompanyAddress);
		appPersonDetail.setInfoQq(infoQq);
		appPersonDetail.setInfoWeixin(infoWeixin);
		appPersonDetail.setInfoHome(infoHome);
		appPersonDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
		appPersonDetail.setInfoEmycontactMobile(Integer.parseInt(infoEmycontactMobile));
		appPersonDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
		appPersonDetail.setInfoContactMobile(Integer.parseInt(infoContactMobile));
		appPersonDetail.setUid(uId);
		appPersonDetailService.save(appPersonDetail);
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
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppStuDetail appStuDetail = new AppStuDetailDTO();
		appStuDetail.setCreateDate(new Date());
		appStuDetail.setInfoMobile(infoMobile);
		appStuDetail.setInfoSchool(infoSchool);
		appStuDetail.setInfoDepartment(infoDepartment);
		appStuDetail.setInfoClass(infoClass);
		appStuDetail.setInfoRoomnumber(infoRoomNumber);
		appStuDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
		appStuDetail.setInfoEmycontactMobile(Integer.parseInt(infoEmycontactMobile));
		appStuDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
		appStuDetail.setInfoContactMobile(Integer.parseInt(infoContactMobile));
		appStuDetail.setUid(uId);
		appStuDetailService.save(appStuDetail);
		return Result.createSuccessResult().setMessage("学生信息保存成功");
	}

	@RequestMapping(value = "/user/saveBankInfo")
	@ResponseBody
	public Object addBankInfo(HttpServletRequest request) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(request,Constants.AHORITY_MEDIUM);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		String bankName = request.getParameter("bankName");
		String bankNumber = request.getParameter("bankNumber");
		String bankMobile = request.getParameter("bankMobile");
		System.out.println("请求的bankName为" + bankName + "\n请求的bankNumber为" +
				bankNumber+ "\n请求的bankMobile为" + bankMobile);
		if (StringUtil.isNullOrBlank(bankName)||StringUtil.isNullOrBlank(bankNumber)
				||StringUtil.isNullOrBlank(bankMobile)){
			return Result.create(ResultCode.VALIDATE_ERROR);
		}

		IAppUserBank appUserBank = new AppUserBankDTO();
		appUserBank.setBankName(bankName);
		appUserBank.setBankNumber(Integer.parseInt(bankNumber));
		appUserBank.setBankMobile(Integer.parseInt(bankMobile));
		appUserBank.setCreateDate(new Date());
		appUserBank.setUid(uId);
		appUserBankService.save(appUserBank);
		return Result.createSuccessResult().setMessage("银行卡信息保存成功");
	}
}