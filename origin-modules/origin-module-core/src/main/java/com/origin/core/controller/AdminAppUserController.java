package com.origin.core.controller;

import com.github.pagehelper.PageHelper;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppUser;
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

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model) throws ParseException {
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String mobile = request.getParameter("mobile");
		String pwd = request.getParameter("pwd");
		String payPwd = request.getParameter("payPwd");
		String authority = request.getParameter("authority");
		String moneyMax = request.getParameter("moneyMax");
		String alipayUsername = request.getParameter("alipayUsername");
		String alipayUseraccout = request.getParameter("alipayUseraccout");
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
		if(StringUtils.isNotBlank(authority)){
			params.setAuthority( Integer.parseInt(authority));
		}
		if(StringUtils.isNotBlank(moneyMax)){
			params.setMoneyMax( Integer.parseInt(moneyMax));
		}
		if(StringUtils.isNotBlank(alipayUsername)){
			params.setAlipayUsername(alipayUsername);
		}
		if(StringUtils.isNotBlank(alipayUseraccout)){
			params.setAlipayUseraccout(alipayUseraccout);
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
    	model.addAttribute("appUsers", appUsers);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "App用户列表");

    	return "appUser/appUser_list";
	}
    @RequestMapping("/dialog/appUser_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		IAppUser appUser = appUserService.findById(Integer.parseInt(id));
    		model.addAttribute("appUser", appUser);

		}
    	return "appUser/dialog/appUser_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String createDate = request.getParameter("createDate");
    		String updateDate = request.getParameter("updateDate");
    		String mobile = request.getParameter("mobile");
    		String pwd = request.getParameter("pwd");
    		String payPwd = request.getParameter("payPwd");
    		String authority = request.getParameter("authority");
    		String moneyMax = request.getParameter("moneyMax");
    		String alipayUsername = request.getParameter("alipayUsername");
    		String alipayUseraccout = request.getParameter("alipayUseraccout");
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
			appUser.setCreateDate(StringUtil.parseDateTime(createDate));
			appUser.setUpdateDate(StringUtil.parseDateTime(updateDate));
			appUser.setMobile(mobile);
			appUser.setPwd(pwd);
			appUser.setPayPwd(payPwd);
			appUser.setAuthority( Integer.parseInt(authority));
			appUser.setMoneyMax( Integer.parseInt(moneyMax));
			appUser.setAlipayUsername(alipayUsername);
			appUser.setAlipayUseraccout(alipayUseraccout);
			appUser.setImgFace(imgFace);
			appUser.setImgIdFront(imgIdFront);
			appUser.setImgIdBack(imgIdBack);
			appUser.setUserIdName(userIdName);
			appUser.setUserIdNumber(userIdNumber);
			appUser.setImgPortrait(imgPortrait);
			appUser.setNickname(nickname);
			appUser.setCategory( Integer.parseInt(category));
			appUser.setDeleteFlag( Integer.parseInt(deleteFlag));
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
    @RequestMapping("/ajax/delete")
    @ResponseBody
    public AjaxResult ajaxDelete(HttpServletRequest request){
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

}
