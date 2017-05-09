package com.origin.<#if module??>${module}.</#if>controller;

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
import com.origin.common.dto.AjaxResult;
import com.origin.<#if module??>${module}.</#if>dto.${model}DTO;
import com.origin.<#if module??>${module}.</#if>service.${model}Service;
import com.origin.<#if module??>${module}.</#if>util.Constants;
import com.origin.data.entity.I${model};

/**
 * ${tableComment}Controller
 * 
 * @author Joe
 */
@Controller
@RequestMapping("/${_model}")
public class ${model}Controller {

	Logger log = LoggerFactory.getLogger(${model}Controller.class);
	@Autowired
	private ${model}Service ${_model}Service;

	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model){
		<#list fieldList as field>
		String ${field.fieldName} = request.getParameter("${field.fieldName}");
		</#list>
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
		I${model} params = new ${model}DTO();
		<#list fieldList as field>
		if(StringUtils.isNotBlank(${field.fieldName})){
			params.set${field.upperFieldName}(<#if field.fieldType == "Integer"> Integer.parseInt(${field.fieldName})<#else>${field.fieldName}</#if>);
		}
		</#list>
		PageHelper.startPage(currentPage, pageSize);
		List<I${model}> ${_model}s = ${_model}Service.find(params);
    	model.addAttribute("${_model}s", ${_model}s);
    	model.addAttribute("queryDTO", params);
    	model.addAttribute(Constants.MENU_NAME, "${_model}列表");

    	return "${_model}/${_model}_list";
	}
    @RequestMapping("/dialog/${_model}_edit")
    public String edit(HttpServletRequest request, Model model){
    	String id = request.getParameter("id");
    	if(StringUtils.isNotBlank(id)){
    		I${model} ${_model} = ${_model}Service.findById(Integer.parseInt(id));
    		model.addAttribute("${_model}", ${_model});

		}
    	return "${_model}/dialog/${_model}_edit";
    }
    @RequestMapping("/ajax/update")
    @ResponseBody
    public AjaxResult ajaxUpdate(HttpServletRequest request){
    	AjaxResult ajaxResult = new AjaxResult();
    	ajaxResult.setSuccess(false);

    	try {
			<#list fieldList as field>
    		String ${field.fieldName} = request.getParameter("${field.fieldName}");
			</#list>
    		I${model} ${_model} = new ${model}DTO();
    		if(StringUtils.isNotBlank(id)){
				${_model} = ${_model}Service.findById(Integer.parseInt(id));
    		}else{
				${_model} = new ${model}DTO();
    		}
			<#list fieldList as field>
			<#if field.fieldName != 'id'>
			${_model}.set${field.upperFieldName}(<#if field.fieldType == "Integer"> Integer.parseInt(${field.fieldName})<#else>${field.fieldName}</#if>);
			</#if>
			</#list>
    		if(StringUtils.isNotBlank(id)){
				${_model}Service.update(${_model});
    		}else{
				${_model}Service.save(${_model});
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
			${_model}Service.delete(Integer.parseInt(id));
    		ajaxResult.setSuccess(true);
    	} catch (Exception e) {
    		e.printStackTrace();
    		ajaxResult.setMsg("操作失败!");
    	}
    	return ajaxResult;
    }

}