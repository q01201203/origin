package com.origin.core.controller.app;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppConstantsDTO;
import com.origin.core.dto.AppGuideDTO;
import com.origin.core.dto.AppTaskDTO;
import com.origin.core.model.DataWithPageModel;
import com.origin.core.service.AppConstantsService;
import com.origin.core.service.AppGuideService;
import com.origin.core.service.AppTaskService;
import com.origin.core.service.AppUserService;
import com.origin.core.util.Constants;
import com.origin.core.util.CustomToken;
import com.origin.core.util.SimpleToken;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/app/info")
@Api(value = "/app/info" ,description = "app获取信息API")
public class AppInfoController {

	Logger log = LoggerFactory.getLogger(AppInfoController.class);
	@Autowired
	private AppTaskService appTaskService;

	@Autowired
	private AppUserService appUserService;

	@Autowired
	private AppGuideService appGuideService;

	@Autowired
	private AppConstantsService appConstantsService;

	@RequestMapping(value = "/getTask" , method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "app用户获取任务", httpMethod = "GET", response = Result.class, notes = "" +
			"传入可选参数taskType任务类型(1、普通任务2、高额任务3、限时任务),可选taskHot任务推荐（0、不推荐1、推荐）" +
			"currentPage当前页（不填默认第一页），pageSize每页条数（不填默认为10）")
	public Object getTask(@RequestHeader(value = "Authorization" ) String token,
						  @RequestParam(value = "taskType" ,required = false) String taskType ,
						  @RequestParam(value = "taskHot" ,required = false) String taskHot,
						  @RequestParam(value = "currentPage" ,required = false) String currentPageStr,
						  @RequestParam(value = "pageSize" ,required = false) String pageSizeStr) throws Exception {
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token), Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		System.out.println("renxinhua 请求的taskType为" + taskType + "\n请求的taskHot为" + taskHot);

		int currentPage = 1;
		int pageSize = 10;
		if(StringUtils.isNotBlank(currentPageStr)){
			currentPage = Integer.parseInt(currentPageStr);
		}
		if(StringUtils.isNotBlank(pageSizeStr)){
			pageSize = Integer.parseInt(pageSizeStr);
		}
		IAppTask appTask = new AppTaskDTO();
		if (!StringUtil.isNullOrBlank(taskType)){
			appTask.setTaskType(Integer.parseInt(taskType));
		}
		if (!StringUtil.isNullOrBlank(taskHot)){
			appTask.setTaskHot(Integer.parseInt(taskHot));
		}
		appTask.setDeleteFlag(0);
		PageHelper.startPage(currentPage, pageSize);
		List<IAppTask> appTasks = appTaskService.find(appTask);

		PageInfo<IAppTask> page = new PageInfo(appTasks);
		DataWithPageModel<IAppTask> dataWithPageModel = new DataWithPageModel<>();
		dataWithPageModel.setData(appTasks);
		dataWithPageModel.setPageInfo(page);
		return Result.createSuccessResult(dataWithPageModel,"获取任务成功");
	}

	@RequestMapping(value = "/getUserInfo" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app用户信息", httpMethod = "GET", response = Result.class, notes = "获取app用户信息")
	public Object getUserInfo(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppUser appUser = appUserService.findById(uId);
		return Result.createSuccessResult(appUser,"获取信息成功");
	}

	@RequestMapping(value = "/getGuide" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app安全指南", httpMethod = "GET", response = Result.class, notes = "获取app安全指南")
	public Object getGuide(@RequestHeader(value = "Authorization" ) String token) throws Exception{
		Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
		if (!(tokenValidResult instanceof SimpleToken)){
			return tokenValidResult;
		}
		Integer uId = ((SimpleToken) tokenValidResult).getId();

		IAppGuide appGuide = new AppGuideDTO();
		appGuide.setDeleteFlag(0);
		List<IAppGuide> appGuides = appGuideService.findOrderBy(appGuide);
		return Result.createSuccessResult(appGuides,"获取安全指南成功");
	}

	@RequestMapping(value = "/getConstants" ,method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "获取app常量", httpMethod = "GET", response = Result.class,
			notes = "获取app常量,可选参数type(1:文字2:图片),可选参数key为键value为值")
	public Object getConstants(@RequestParam(value = "type" ,required = false) String type ,
							   @RequestParam(value = "key" ,required = false) String key ) throws Exception{
		IAppConstants appConstants = new AppConstantsDTO();
		if(StringUtils.isNotBlank(type)){
			appConstants.setType(type);
		}
		if(StringUtils.isNotBlank(key)){
			appConstants.setKey(key);
		}
		appConstants.setDeleteFlag(0);
		List<IAppConstants> appConstantsList = appConstantsService.find(appConstants);
		return Result.createSuccessResult(appConstantsList,"获取常量成功");
	}
}