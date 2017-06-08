package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.common.util.DateUtils;
import com.origin.core.dto.AppFeedbackDTO;
import com.origin.core.service.AppFeedbackService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IAppFeedback;
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
@RequestMapping("admin/appFeedback")
@ApiIgnore
public class AdminFeedbackController {

	Logger log = LoggerFactory.getLogger(AdminFeedbackController.class);
	@Autowired
	private AppFeedbackService appFeedbackService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String content = request.getParameter("content");
		String uid = request.getParameter("uid");
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
		IAppFeedback params = new AppFeedbackDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(DateUtils.parseDate(createDate));
		}
		if(StringUtils.isNotBlank(updateDate)){
			params.setUpdateDate(DateUtils.parseDate(updateDate));
		}
		if(StringUtils.isNotBlank(content)){
			params.setContent(content);
		}
		if(StringUtils.isNotBlank(uid)){
			params.setUid( Integer.parseInt(uid));
		}
		if(StringUtils.isNotBlank(deleteFlag)){
			params.setDeleteFlag( Integer.parseInt(deleteFlag));
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IAppFeedback> appFeedbacks = appFeedbackService.find(params);
    	PageInfo<IAppFeedback> page = new PageInfo(appFeedbacks);
        model.addAttribute("page", page);
    	model.addAttribute("appFeedbacks", appFeedbacks);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "反馈管理");

    	return "appFeedback/appFeedback_list";
	}
    @RequestMapping("/dialog/appFeedback_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		IAppFeedback appFeedback = appFeedbackService.findById(Integer.parseInt(id));
    		model.addAttribute("appFeedback", appFeedback);

		}
    	return "appFeedback/dialog/appFeedback_edit";
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
    		String content = request.getParameter("content");
    		String uid = request.getParameter("uid");
    		String deleteFlag = request.getParameter("deleteFlag");
    		IAppFeedback appFeedback = new AppFeedbackDTO();
    		if(StringUtils.isNotBlank(id)){
				appFeedback = appFeedbackService.findById(Integer.parseInt(id));
    		}else{
				appFeedback = new AppFeedbackDTO();
    		}
			appFeedback.setCreateDate(DateUtils.parseDate(createDate));
			appFeedback.setUpdateDate(DateUtils.parseDate(updateDate));
			appFeedback.setContent(content);
			appFeedback.setUid( Integer.parseInt(uid));
			appFeedback.setDeleteFlag( Integer.parseInt(deleteFlag));
    		if(StringUtils.isNotBlank(id)){
				appFeedbackService.update(appFeedback);
    		}else{
				appFeedbackService.save(appFeedback);
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
			appFeedbackService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}