package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.*;
import com.origin.core.service.*;
import com.origin.core.util.Constants;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/**
 * Created by lc on 2017/5/23.
 */
@Controller
@RequestMapping("/admin/appUser")
@ApiIgnore
public class AdminAppUserController {

    Logger log = LoggerFactory.getLogger(AdminAppUserController.class);
	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppStuDetailService appStuDetailService;

	@Autowired
	private AppPersonDetailService appPersonDetailService;

	@Autowired
	private AppUserBankService appUserBankService;

	@Autowired
	private AppMoneyDetailService appMoneyDetailService;

	@Autowired
	private AppZhimaService appZhimaService;

	@Autowired
	private AppConstantsService appConstantsService;

	@RequestMapping("/user/list")
	public String userList(HttpServletRequest request, Model model) throws ParseException {
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		String payPwd = request.getParameter("payPwd");
		String balance = request.getParameter("balance");
		String authority = request.getParameter("authority");
		String moneyMax = request.getParameter("moneyMax");
		String zhimaCertName = request.getParameter("zhimaCertName");
		String zhimaCertNo = request.getParameter("zhimaCertNo");
		String zhimaOpenid = request.getParameter("zhimaOpenid");
		String zhimaScore = request.getParameter("zhimaScore");
		String imgFace = request.getParameter("imgFace");
		String imgIdFront = request.getParameter("imgIdFront");
		String imgIdBack = request.getParameter("imgIdBack");
		String userIdName = request.getParameter("userIdName");
		String userIdNumber = request.getParameter("userIdNumber");
		String imgPortrait = request.getParameter("imgPortrait");
		String nickname = request.getParameter("nickname");
		String category = request.getParameter("category");
		String deleteFlag = request.getParameter("deleteFlag");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");

		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		IAppUser params = new AppUserDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(StringUtil.parseDateTime(createDate));
		}
		if(StringUtils.isNotBlank(updateDate)){
			params.setUpdateDate(StringUtil.parseDateTime(updateDate));
		}
		if(StringUtils.isNotBlank(mobile)){
			params.setMobile(mobile);
		}
		if(StringUtils.isNotBlank(pwd)){
			params.setPwd(pwd);
		}
		if(StringUtils.isNotBlank(payPwd)){
			params.setPayPwd(payPwd);
		}
		if(StringUtils.isNotBlank(balance)){
			params.setBalance(Double.parseDouble(balance));
		}
		if(StringUtils.isNotBlank(authority)){
			params.setAuthority( Integer.parseInt(authority));
		}
		if(StringUtils.isNotBlank(moneyMax)){
			params.setMoneyMax( Double.parseDouble(moneyMax));
		}
		if(StringUtils.isNotBlank(zhimaCertName)){
			params.setZhimaCertName(zhimaCertName);
		}
		if(StringUtils.isNotBlank(zhimaCertNo)){
			params.setZhimaCertNo(zhimaCertNo);
		}
		if(StringUtils.isNotBlank(zhimaOpenid)){
			params.setZhimaOpenid(zhimaOpenid);
		}
		if(StringUtils.isNotBlank(zhimaScore)){
			params.setZhimaScore(zhimaScore);
		}
		if(StringUtils.isNotBlank(imgFace)){
			params.setImgFace(imgFace);
		}
		if(StringUtils.isNotBlank(imgIdFront)){
			params.setImgIdFront(imgIdFront);
		}
		if(StringUtils.isNotBlank(imgIdBack)){
			params.setImgIdBack(imgIdBack);
		}
		if(StringUtils.isNotBlank(userIdName)){
			params.setUserIdName(userIdName);
		}
		if(StringUtils.isNotBlank(userIdNumber)){
			params.setUserIdNumber(userIdNumber);
		}
		if(StringUtils.isNotBlank(imgPortrait)){
			params.setImgPortrait(imgPortrait);
		}
		if(StringUtils.isNotBlank(nickname)){
			params.setNickname(nickname);
		}
		if(StringUtils.isNotBlank(category)){
			params.setCategory( Integer.parseInt(category));
		}
		if(StringUtils.isNotBlank(deleteFlag)){
			params.setDeleteFlag( Integer.parseInt(deleteFlag));
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IAppUser> appUsers = appUserService.find(params);
		PageInfo<IAppUser> page = new PageInfo(appUsers);
		model.addAttribute("page", page);
    	model.addAttribute("appUsers", appUsers);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "App用户列表");

    	return "appUser/appUser_list";
	}

    @RequestMapping("/user/dialog/appUser_edit")
    public String userEdit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
			IAppUser appUser = appUserService.findById(Integer.parseInt(id));
			model.addAttribute("appUser", appUser);
		}
    	return "appUser/dialog/appUser_edit";
    }

	@RequestMapping("/user/detail/appUser_edit")
	public String userDetailEdit(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			Integer uid = Integer.parseInt(id);
			IAppUser appUser = appUserService.findById(uid);
			model.addAttribute("appUser", appUser);
			if (IAppUser.CATEGORY_STU.equals(appUser.getCategory())){
				IAppStuDetail appStuDetail = new AppStuDetailDTO();
				appStuDetail.setUid(uid);
				appStuDetail = appStuDetailService.findFirst(appStuDetail);
				model.addAttribute("appStuDetail", appStuDetail);
			}else if (IAppUser.CATEGORY_PERSON.equals(appUser.getCategory())){
				IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
				appPersonDetail.setUid(uid);
				appPersonDetail = appPersonDetailService.findFirst(appPersonDetail);
				model.addAttribute("appPersonDetail", appPersonDetail);
			}
			IAppUserBank appUserBank = new AppUserBankDTO();
			appUserBank.setUid(uid);
			appUserBank = appUserBankService.findFirst(appUserBank);
			model.addAttribute("appUserBank", appUserBank);

			List<IAppZhima> appZhimas = appZhimaService.findZhimaInfoByUid(uid);
			model.addAttribute("appZhimas", appZhimas);
		}
		model.addAttribute(Constants.MENU_NAME, "用户详情");
		return "appUser/appUserDetail";
	}

    @RequestMapping("/user/ajax/update")
    @ResponseBody
    public AjaxResult userAjaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String createDate = request.getParameter("createDate");
    		String updateDate = request.getParameter("updateDate");
    		String mobile = request.getParameter("mobile");
    		String pwd = request.getParameter("pwd");
    		String payPwd = request.getParameter("payPwd");
    		String balance = request.getParameter("balance");
    		String authority = request.getParameter("authority");
    		String moneyMax = request.getParameter("moneyMax");
    		String zhimaCertName = request.getParameter("zhimaCertName");
    		String zhimaCertNo = request.getParameter("zhimaCertNo");
    		String zhimaOpenid = request.getParameter("zhimaOpenid");
    		String zhimaScore = request.getParameter("zhimaScore");
    		String imgFace = request.getParameter("imgFace");
    		String imgIdFront = request.getParameter("imgIdFront");
    		String imgIdBack = request.getParameter("imgIdBack");
    		String userIdName = request.getParameter("userIdName");
    		String userIdNumber = request.getParameter("userIdNumber");
    		String imgPortrait = request.getParameter("imgPortrait");
    		String nickname = request.getParameter("nickname");
    		String category = request.getParameter("category");
    		String deleteFlag = request.getParameter("deleteFlag");
    		IAppUser appUser = new AppUserDTO();
    		if(StringUtils.isNotBlank(id)){
				appUser = appUserService.findById(Integer.parseInt(id));
    		}else{
				appUser = new AppUserDTO();
    		}
			if(StringUtils.isNotBlank(createDate)){appUser.setCreateDate(StringUtil.parseDateTime(createDate));}
			if(StringUtils.isNotBlank(updateDate)){appUser.setUpdateDate(StringUtil.parseDateTime(updateDate));}
			if(StringUtils.isNotBlank(mobile)){appUser.setMobile(mobile);}
			if(StringUtils.isNotBlank(pwd)){appUser.setPwd(pwd);}
			if(StringUtils.isNotBlank(payPwd)){appUser.setPayPwd(payPwd);}
			if(StringUtils.isNotBlank(balance)){appUser.setBalance(Double.parseDouble(balance));}
			if(StringUtils.isNotBlank(authority)){appUser.setAuthority( Integer.parseInt(authority));}
			if(StringUtils.isNotBlank(moneyMax)){appUser.setMoneyMax( Double.parseDouble(moneyMax));}
			if(StringUtils.isNotBlank(zhimaCertName)){appUser.setZhimaCertName(zhimaCertName);}
			if(StringUtils.isNotBlank(zhimaCertNo)){appUser.setZhimaCertNo(zhimaCertNo);}
			if(StringUtils.isNotBlank(zhimaOpenid)){appUser.setZhimaOpenid(zhimaOpenid);}
			if(StringUtils.isNotBlank(zhimaScore)){appUser.setZhimaScore(zhimaScore);}
			if(StringUtils.isNotBlank(imgFace)){appUser.setImgFace(imgFace);}
			if(StringUtils.isNotBlank(imgIdFront)){appUser.setImgIdFront(imgIdFront);}
			if(StringUtils.isNotBlank(imgIdBack)){appUser.setImgIdBack(imgIdBack);}
			if(StringUtils.isNotBlank(userIdName)){appUser.setUserIdName(userIdName);}
			if(StringUtils.isNotBlank(userIdNumber)){appUser.setUserIdNumber(userIdNumber);}
			if(StringUtils.isNotBlank(imgPortrait)){appUser.setImgPortrait(imgPortrait);}
			if(StringUtils.isNotBlank(nickname)){appUser.setNickname(nickname);}
			if(StringUtils.isNotBlank(category)){appUser.setCategory( Integer.parseInt(category));}
			if(StringUtils.isNotBlank(deleteFlag)){appUser.setDeleteFlag( Integer.parseInt(deleteFlag));}

    		if(StringUtils.isNotBlank(id)){
				appUserService.update(appUser);
    		}else{
				appUserService.save(appUser);
    		}

    		ajaxResult.setSuccess(true);

		} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}

    	return ajaxResult;
    }
    @RequestMapping("/user/ajax/delete")
    @ResponseBody
    public AjaxResult userAjaxDelete(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);
    	try {
    		String id = request.getParameter("id");
			appUserService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

    //add lic 170526 钱记录
	@RequestMapping(value = "/money/list")
	public String getMoney(HttpServletRequest request,Model model) throws Exception{
		String id = request.getParameter("id");
		String uid = request.getParameter("uid");
		String type = request.getParameter("type");
		String status = request.getParameter("status");
		String currentPageStr = request.getParameter("currentPage");
		String pageSizeStr = request.getParameter("pageSize");


		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		IAppMoneyDetail params = new AppMoneyDetailDTO();

		if(StringUtils.isNotBlank(uid)){
			params.setUid( Integer.parseInt(uid));
		}
		if(StringUtils.isNotBlank(status)){
			params.setStatus( Integer.parseInt(status));
		}
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}

		PageHelper.startPage(currentPage, pageSize);
		List<IAppMoneyDetail> appMoneyDetails;
		if(StringUtils.isNotBlank(type)){
			params.setType( Integer.parseInt(type));
		}
		appMoneyDetails = appMoneyDetailService.findMoneyUser(params);
		PageInfo<IAppMoneyDetail> page = new PageInfo(appMoneyDetails);
		model.addAttribute("page", page);
		model.addAttribute("appMoneyDetails", appMoneyDetails);
		model.addAttribute("queryDTO", params);
		if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
			model.addAttribute(Constants.MENU_NAME, "借款记录");
		}else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
			model.addAttribute(Constants.MENU_NAME, "还款记录");
		}else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(Integer.parseInt(type))){
			model.addAttribute(Constants.MENU_NAME, "提现记录");
		}else if (IAppMoneyDetail.TYPE_INCOME.equals(Integer.parseInt(type))){
			model.addAttribute(Constants.MENU_NAME, "用户收入记录");
		}

		return "appUser/appMoneyDetail_list";
	}
	@RequestMapping("/money/dialog/appMoneyDetail_edit")
	public String edit(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			IAppMoneyDetail appMoneyDetail = appMoneyDetailService.findById(Integer.parseInt(id));
			model.addAttribute("appMoneyDetail", appMoneyDetail);
			IAppUser appUser = appUserService.findById(appMoneyDetail.getUid());
			model.addAttribute("appUser", appUser);
			Double borrowLine = 0d;
			String interestRate = "0.0";
			if (IAppMoneyDetail.TYPE_BORROW.equals(appMoneyDetail.getType())){
				borrowLine = getBorrowLine(appMoneyDetail.getUid());
				System.out.println("renxinhua borrowLine = "+borrowLine);
				IAppConstants appConstants = new AppConstantsDTO();
				appConstants.setKey("interestRate");
				appConstants = appConstantsService.findFirst(appConstants);
				interestRate = appConstants.getValue();
			}
			model.addAttribute("borrowLine", borrowLine);
			model.addAttribute("interestRate",interestRate);
		}
		return "appUser/dialog/appMoneyDetail_edit";
	}

	private Double getBorrowLine(int uId){
		IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
		moneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
		moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
		moneyDetail.setUid(uId);
		Double borrow = appMoneyDetailService.findTotalActualMoney(moneyDetail);
		borrow = (borrow == null ?0:borrow);
		moneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
		Double repay = appMoneyDetailService.findTotalActualMoney(moneyDetail);
		repay = (repay == null ?0:repay);
		IAppUser appUser = appUserService.findById(uId);
		Double moneyMax = appUser.getMoneyMax();
		System.out.println("renxinhua borrow = "+borrow+" repay = "+repay+" moneyMax = "+moneyMax);
		return moneyMax - borrow + repay;
	}

	@RequestMapping("/money/ajax/update")
	@ResponseBody
	public AjaxResult ajaxUpdate(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);

		try {
			String id = request.getParameter("id");
			String moneyActual = request.getParameter("moneyActual");
			String status = request.getParameter("status");
			String message = request.getParameter("message");
			System.out.println("renxinhua message = "+message);
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			if(StringUtils.isNotBlank(id)){
				appMoneyDetail = appMoneyDetailService.findById(Integer.parseInt(id));
			}
			if(StringUtils.isNotBlank(moneyActual)){
				appMoneyDetail.setMoneyActual(Double.parseDouble(moneyActual));
			}else {
				appMoneyDetail.setMoneyActual(0d);
			}
			appMoneyDetail.setStatus( Integer.parseInt(status));
			if(StringUtils.isNotBlank(id)){
				appMoneyDetailService.updateAudit(appMoneyDetail,message);
			}
			ajaxResult.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setMsg("操作失败!");
		}
		return ajaxResult;
	}
}
