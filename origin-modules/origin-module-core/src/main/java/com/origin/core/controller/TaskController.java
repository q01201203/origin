package com.origin.core.controller;

import com.github.pagehelper.PageHelper;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.TaskDTO;
import com.origin.core.service.TaskService;
import com.origin.core.util.Constants;
import com.origin.data.entity.ITask;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/task")
public class TaskController {

	Logger log = LoggerFactory.getLogger(TaskController.class);
	@Autowired
	private TaskService taskService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String type = request.getParameter("type");
		String state = request.getParameter("state");
		String createDate = request.getParameter("createDate");
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
		ITask params = new TaskDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(name)){
			params.setName(name);
		}
		if(StringUtils.isNotBlank(type)){
			params.setType( Integer.parseInt(type));
		}
		if(StringUtils.isNotBlank(state)){
			params.setState( Integer.parseInt(state));
		}
		if(StringUtils.isNotBlank(createDate)){
			params.setCreateDate(new Date());
		}
		PageHelper.startPage(currentPage, pageSize);
		List<ITask> tasks = taskService.find(params);
    	model.addAttribute("tasks", tasks);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "task列表");

    	return "task/task_list";
	}
    @RequestMapping("/dialog/task_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		ITask task = taskService.findById(Integer.parseInt(id));
    		model.addAttribute("task", task);

		}
    	return "task/dialog/task_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String name = request.getParameter("name");
    		String type = request.getParameter("type");
    		String state = request.getParameter("state");
    		String createDate = request.getParameter("createDate");
    		ITask task = new TaskDTO();
    		if(StringUtils.isNotBlank(id)){
				task = taskService.findById(Integer.parseInt(id));
    		}else{
				task = new TaskDTO();
    		}
			task.setName(name);
			task.setType( Integer.parseInt(type));
			task.setState( Integer.parseInt(state));
			task.setCreateDate(new Date());
    		if(StringUtils.isNotBlank(id)){
				taskService.update(task);
    		}else{
				taskService.save(task);
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
			taskService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}