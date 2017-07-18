package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.common.util.DateUtils;
import com.origin.core.dto.AppGuideDTO;
import com.origin.core.service.AppGuideService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IAppGuide;
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
@RequestMapping("/admin/appGuide")
@ApiIgnore
public class AdminGuideController {

	Logger log = LoggerFactory.getLogger(AdminGuideController.class);
	@Autowired
	private AppGuideService appGuideService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String guideName = request.getParameter("guideName");
		String guideContent = request.getParameter("guideContent");
		String guideType = request.getParameter("guideType");
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
		IAppGuide params = new AppGuideDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(DateUtils.parseDateTime(createDate));
		}
		if(StringUtils.isNotBlank(updateDate)){
			params.setUpdateDate(DateUtils.parseDateTime(updateDate));
		}
		if(StringUtils.isNotBlank(guideName)){
			params.setGuideName(guideName);
		}
		if(StringUtils.isNotBlank(guideContent)){
			params.setGuideContent(guideContent);
		}
		if(StringUtils.isNotBlank(guideType)){
			params.setGuideType( Integer.parseInt(guideType));
		}
		if(StringUtils.isNotBlank(deleteFlag)){
			params.setDeleteFlag( Integer.parseInt(deleteFlag));
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IAppGuide> appGuides = appGuideService.findOrderBy(params);
    	PageInfo<IAppGuide> page = new PageInfo(appGuides);
        model.addAttribute("page", page);
    	model.addAttribute("appGuides", appGuides);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "安全指南列表");

    	return "appGuide/appGuide_list";
	}
    @RequestMapping("/dialog/appGuide_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		IAppGuide appGuide = appGuideService.findById(Integer.parseInt(id));
    		model.addAttribute("appGuide", appGuide);

		}
    	return "appGuide/dialog/appGuide_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String guideName = request.getParameter("guideName");
    		String guideContent = request.getParameter("guideContent");
    		String guideType = request.getParameter("guideType");
    		IAppGuide appGuide = new AppGuideDTO();
    		if(StringUtils.isNotBlank(id)){
				appGuide = appGuideService.findById(Integer.parseInt(id));
    		}else{
				appGuide = new AppGuideDTO();
    		}
			appGuide.setGuideName(guideName);
			appGuide.setGuideContent(guideContent);
			if(StringUtils.isNotBlank(guideType)) {
				appGuide.setGuideType(Integer.parseInt(guideType));
			}
    		if(StringUtils.isNotBlank(id)){
				appGuideService.update(appGuide);
    		}else{
				appGuideService.save(appGuide);
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
			//appGuideService.delete(Integer.parseInt(id));
			IAppGuide appGuide = new AppGuideDTO();
			appGuide.setId(Integer.parseInt(id));
			appGuide.setDeleteFlag(Integer.parseInt(deleteFlag));
			appGuideService.update(appGuide);
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}