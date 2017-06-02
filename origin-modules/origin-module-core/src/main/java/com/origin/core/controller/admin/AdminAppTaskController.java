package com.origin.core.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.common.util.DateUtils;
import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppTaskDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppTaskService;
import com.origin.core.service.AppUserService;
import com.origin.core.service.AppUserTaskService;
import com.origin.core.util.Constants;
import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUserTask;
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
@RequestMapping("/admin/appTask")
@ApiIgnore
public class AdminAppTaskController {

	Logger log = LoggerFactory.getLogger(AdminAppTaskController.class);
	@Autowired
	private AppTaskService appTaskService;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppUserTaskService appUserTaskService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String createDate = request.getParameter("createDate");
		String updateDate = request.getParameter("updateDate");
		String taskName = request.getParameter("taskName");
		String taskNumber = request.getParameter("taskNumber");
		String taskType = request.getParameter("taskType");
		String taskMoney = request.getParameter("taskMoney");
		String taskImg = request.getParameter("taskImg");
		String taskHot = request.getParameter("taskHot");
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
		IAppTask params = new AppTaskDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(DateUtils.parseDate(createDate));
		}
		if(StringUtils.isNotBlank(updateDate)){
			params.setUpdateDate(DateUtils.parseDate(updateDate));
		}
		if(StringUtils.isNotBlank(taskName)){
			params.setTaskName(taskName);
		}
		if(StringUtils.isNotBlank(taskNumber)){
			params.setTaskNumber( Integer.parseInt(taskNumber));
		}
		if(StringUtils.isNotBlank(taskType)){
			params.setTaskType( Integer.parseInt(taskType));
		}
		if(StringUtils.isNotBlank(taskMoney)){
			params.setTaskMoney(Double.parseDouble(taskMoney));
		}
		if(StringUtils.isNotBlank(taskImg)){
			params.setTaskImg(taskImg);
		}
		if(StringUtils.isNotBlank(taskHot)){
			params.setTaskHot( Integer.parseInt(taskHot));
		}
		if(StringUtils.isNotBlank(deleteFlag)){
			params.setDeleteFlag( Integer.parseInt(deleteFlag));
		}
		System.out.println("lichengsss " +params.getCreateDate());
		PageHelper.startPage(currentPage, pageSize);
		List<IAppTask> appTasks = appTaskService.find(params);
    	PageInfo<IAppTask> page = new PageInfo(appTasks);
        model.addAttribute("page", page);
    	model.addAttribute("appTasks", appTasks);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "任务列表");

    	return "appTask/appTask_list";
	}
    @RequestMapping("/dialog/appTask_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		IAppTask appTask = appTaskService.findById(Integer.parseInt(id));
    		model.addAttribute("appTask", appTask);

		}
    	return "appTask/dialog/appTask_edit";
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
    		String taskName = request.getParameter("taskName");
    		String taskNumber = request.getParameter("taskNumber");
    		String taskType = request.getParameter("taskType");
    		String taskMoney = request.getParameter("taskMoney");
    		String taskImg = request.getParameter("taskImg");
    		String taskHot = request.getParameter("taskHot");
    		String deleteFlag = request.getParameter("deleteFlag");
    		IAppTask appTask = new AppTaskDTO();
    		if(StringUtils.isNotBlank(id)){
				appTask = appTaskService.findById(Integer.parseInt(id));
    		}else{
				appTask = new AppTaskDTO();
    		}
			appTask.setCreateDate(DateUtils.parseDate(createDate));
			appTask.setUpdateDate(DateUtils.parseDate(updateDate));
			appTask.setTaskName(taskName);
			appTask.setTaskNumber( Integer.parseInt(taskNumber));
			appTask.setTaskType( Integer.parseInt(taskType));
			appTask.setTaskMoney(Double.parseDouble(taskMoney));
			appTask.setTaskImg(taskImg);
			appTask.setTaskHot( Integer.parseInt(taskHot));
			appTask.setDeleteFlag( Integer.parseInt(deleteFlag));
    		if(StringUtils.isNotBlank(id)){
				appTaskService.update(appTask);
    		}else{
				appTaskService.save(appTask);
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
			appTaskService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

	@RequestMapping("/task/detail/appTask_edit")
	public String userDetailEdit(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
			IAppTask appTask = appTaskService.findById(Integer.parseInt(id));
			model.addAttribute("appTask", appTask);
		}
		model.addAttribute(Constants.MENU_NAME, "任务详情");
		return "appTask/appTaskDetail";
	}


    //add lic 20170526 任务记录
    @RequestMapping("/userTask/list")
	public String taskUserList(HttpServletRequest request,Model model){
		String uid = request.getParameter("uid");
		String tid = request.getParameter("tid");
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

		PageHelper.startPage(currentPage, pageSize);
		IAppUserTask queryDTO = new AppUserTaskDTO();
		if(StringUtils.isNotBlank(uid)){
			queryDTO.setUid(Integer.parseInt(uid));
		}
		if(StringUtils.isNotBlank(tid)){
			queryDTO.setTid(Integer.parseInt(tid));
		}
		if(StringUtils.isNotBlank(status)){
			IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
			appMoneyDetail.setStatus(Integer.parseInt(status));
			queryDTO.setAppMoneyDetail(appMoneyDetail);
		}

		List<IAppUserTask> appUserTasks = appUserTaskService.findTaskUserInfo(queryDTO);
		PageInfo<IAppTask> page = new PageInfo(appUserTasks);
		model.addAttribute("queryDTO", queryDTO);
		model.addAttribute("page", page);
		model.addAttribute("appUserTasks",appUserTasks);
		model.addAttribute(Constants.MENU_NAME, "任务审批");

    	return "appTask/appTaskAudit";
	}

	/*@RequestMapping("/userTask/ajax/updateUserTaskStatus")
	@ResponseBody
	public AjaxResult updateUserTaskStatus(HttpServletRequest request){
		AjaxResult ajaxResult = new AjaxResult();
		ajaxResult.setSuccess(false);

		try {
			String id = request.getParameter("id");
			String status = request.getParameter("status");
			IAppUserTask appUserTask = new AppUserTaskDTO();
			if(!StringUtils.isNotBlank(id)||!StringUtils.isNotBlank(status)){
				return ajaxResult;
			}
			appUserTask = appUserTaskService.findById(Integer.parseInt(id));

			if (IAppUserTask.STATUS_AUDIT_SUCCESS.equals(Integer.parseInt(status))){
				appUserTaskService.updateTaskSuccess(appUserTask);
			}else if (IAppUserTask.STATUS_AUDIT_FAIL.equals(Integer.parseInt(status))){
				appUserTask.setStatus(IAppUserTask.STATUS_AUDIT_FAIL);
				appUserTaskService.update(appUserTask);
			}

			ajaxResult.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			ajaxResult.setMsg("操作失败!");
		}

		return ajaxResult;
	}*/

}