package com.origin.core.controller;

import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppGuideDTO;
import com.origin.core.dto.AppTaskDTO;
import com.origin.core.service.AppGuideService;
import com.origin.core.service.AppTaskService;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppGuide;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/appTask")
@Api(value = "/app" ,description = "app获取信息API")
public class AppInfoController {

	Logger log = LoggerFactory.getLogger(AppInfoController.class);
	@Autowired
	private AppTaskService appTaskService;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppGuideService appGuideService;

	@RequestMapping(value = "/info/getTask" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户获取任务", httpMethod = "GET", response = Result.class, notes = "getTask")
	public Object getTask(@RequestHeader(value = "Authorization" ) String token,
						  @RequestParam(value = "taskType" ,required = false) String taskType ,
						  @RequestParam(value = "taskHot" ,required = false) String taskHot) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("请求的taskType为" + taskType + "\n请求的taskHot为" + taskHot);

		IAppTask appTask = new AppTaskDTO();
		if (!StringUtil.isNullOrBlank(taskType)){
			appTask.setTaskType(Integer.parseInt(taskType));
		}
		if (!StringUtil.isNullOrBlank(taskHot)){
			appTask.setTaskHot(Integer.parseInt(taskHot));
		}
		List<IAppTask> appTasks = appTaskService.find(appTask);
		return Result.createSuccessResult(appTasks,"获取任务成功");
	}

	@RequestMapping(value = "/info/getUserInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户信息", httpMethod = "GET", response = Result.class, notes = "getUserInfo")
	public Object getUserInfo(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		return Result.createSuccessResult(appUser,"获取信息成功");
	}

	@RequestMapping(value = "/info/getGuide" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app安全指南", httpMethod = "GET", response = Result.class, notes = "getGuide")
	public Object getGuide(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppGuide appGuide = new AppGuideDTO();
		List<IAppGuide> appGuides = appGuideService.find(appGuide);
		return Result.createSuccessResult(appGuides,"获取安全指南成功");
	}
}