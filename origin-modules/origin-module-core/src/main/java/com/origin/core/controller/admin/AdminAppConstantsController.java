package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.common.util.DateUtils;
import com.origin.core.dto.AppConstantsDTO;
import com.origin.core.service.AppConstantsService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IAppConstants;
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
import java.util.List;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/admin/appConstants")
@ApiIgnore
public class AdminAppConstantsController {

	Logger log = LoggerFactory.getLogger(AdminAppConstantsController.class);
	@Autowired
	private AppConstantsService appConstantsService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String type = request.getParameter("type");
		String key = request.getParameter("key");
		String value = request.getParameter("value");
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
		IAppConstants params = new AppConstantsDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(DateUtils.parseDate(createDate));
		}
		if(StringUtils.isNotBlank(updateDate)){
			params.setUpdateDate(DateUtils.parseDate(updateDate));
		}
		if(StringUtils.isNotBlank(type)){
			params.setType(type);
		}
		if(StringUtils.isNotBlank(key)){
			params.setKey(key);
		}
		if(StringUtils.isNotBlank(value)){
			params.setValue(value);
		}
		if(StringUtils.isNotBlank(deleteFlag)){
			params.setDeleteFlag( Integer.parseInt(deleteFlag));
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IAppConstants> appConstantss = appConstantsService.find(params);
    	PageInfo<IAppConstants> page = new PageInfo(appConstantss);
        model.addAttribute("page", page);
    	model.addAttribute("appConstantss", appConstantss);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "appConstants列表");

    	return "appConstants/appConstants_list";
	}
    @RequestMapping("/dialog/appConstants_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
		String operation = "add";

    	if(StringUtils.isNotBlank(id)){
    		IAppConstants appConstants = appConstantsService.findById(Integer.parseInt(id));
    		model.addAttribute("appConstants", appConstants);
			operation = "update";
		}
		model.addAttribute(Constants.OPERATION,operation);
    	return "appConstants/dialog/appConstants_edit";
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
    		String type = request.getParameter("type");
    		String key = request.getParameter("key");
    		String value = request.getParameter("value");
			String deleteFlag = request.getParameter("deleteFlag");
    		IAppConstants appConstants = new AppConstantsDTO();
    		if(StringUtils.isNotBlank(id)){
				appConstants = appConstantsService.findById(Integer.parseInt(id));
    		}else{
				appConstants = new AppConstantsDTO();
    		}
			appConstants.setType(type);
			appConstants.setKey(key);
			appConstants.setValue(value);
			if(StringUtils.isNotBlank(deleteFlag)) {
				appConstants.setDeleteFlag(Integer.parseInt(deleteFlag));
			}
    		if(StringUtils.isNotBlank(id)){
				appConstantsService.update(appConstants);
    		}else{
				appConstantsService.save(appConstants);
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
			String deleteFlag = request.getParameter("deleteFlag");
			//appConstantsService.delete(Integer.parseInt(id));
			IAppConstants appConstants = new AppConstantsDTO();
			appConstants.setId(Integer.parseInt(id));
			appConstants.setDeleteFlag(Integer.parseInt(deleteFlag));
			appConstantsService.update(appConstants);
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}