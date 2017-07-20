package com.origin.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.origin.common.dto.AjaxResult;
import com.origin.core.dto.TestDTO;
import com.origin.core.service.TestService;
import com.origin.core.util.Constants;
import com.origin.data.entity.ITest;

/**
 * Controller
 * 
 * @author lc
 */
@Controller
@RequestMapping("/test")
public class TestController {

	Logger log = LoggerFactory.getLogger(TestController.class);
	@Autowired
	private TestService testService;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		String id = request.getParameter("id");
		String sss = request.getParameter("sss");
		String df = request.getParameter("df");
		String dfs = request.getParameter("dfs");
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
		ITest params = new TestDTO();
		if(StringUtils.isNotBlank(id)){
			params.setId( Integer.parseInt(id));
		}
		if(StringUtils.isNotBlank(sss)){
			params.setSss(sss);
		}
		if(StringUtils.isNotBlank(df)){
			params.setDf(df);
		}
		if(StringUtils.isNotBlank(dfs)){
			params.setDfs(dfs);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<ITest> tests = testService.find(params);
    	PageInfo<ITest> page = new PageInfo(tests);
        model.addAttribute("page", page);
    	model.addAttribute("tests", tests);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "test列表");

    	return "test/test_list";
	}
    @RequestMapping("/dialog/test_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		ITest test = testService.findById(Integer.parseInt(id));
    		model.addAttribute("test", test);

		}
    	return "test/dialog/test_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
    		String id = request.getParameter("id");
    		String sss = request.getParameter("sss");
    		String df = request.getParameter("df");
    		String dfs = request.getParameter("dfs");
    		ITest test = new TestDTO();
    		if(StringUtils.isNotBlank(id)){
				test = testService.findById(Integer.parseInt(id));
    		}else{
				test = new TestDTO();
    		}
			test.setSss(sss);
			test.setDf(df);
			test.setDfs(dfs);
    		if(StringUtils.isNotBlank(id)){
				testService.update(test);
    		}else{
				testService.save(test);
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
			testService.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}