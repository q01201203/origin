package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.AppMessageDTO;
import com.origin.core.service.AppMessageService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IAppMessage;
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
@RequestMapping("/admin/appMessage")
@ApiIgnore
public class AdminAppMessageController {

	Logger log = LoggerFactory.getLogger(AdminAppMessageController.class);
	@Autowired
	private AppMessageService appMessageService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String content = request.getParameter("content");
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
		IAppMessage params = new AppMessageDTO();
		if(StringUtils.isNotBlank(content)){
			params.setContent(content);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<IAppMessage> appMessages = appMessageService.findSystemMessage(params);
    	PageInfo<IAppMessage> page = new PageInfo(appMessages);
        model.addAttribute("page", page);
    	model.addAttribute("appMessages", appMessages);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "系统消息列表");

    	return "appMessage/appMessage_list";
	}
    @RequestMapping("/dialog/appMessage_edit")
    public String edit(HttpServletRequest request, Model model){
    	return "appMessage/dialog/appMessage_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);
    	try {
    		String content = request.getParameter("content");
    		IAppMessage appMessages = new AppMessageDTO();
			appMessages.setContent(content);
			appMessageService.saveBatchSystemMessage(appMessages);
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
    		String content = request.getParameter("content");
    		IAppMessage appMessage = new AppMessageDTO();
    		appMessage.setContent(content);
    		List<IAppMessage> appMessages = appMessageService.find(appMessage);
			for (IAppMessage message:appMessages) {
				message.setDeleteFlag(1);
			}
			appMessageService.updateBatch(appMessages);
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}