package com.origin.tool.generate;



import com.origin.tool.system.FreemarkerUtils;
import com.origin.tool.system.Generator;
import com.origin.tool.system.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * ServiceImpl
 * 
 * @author Joe
 */
public class ServiceImpl {

	private Map<String, Object> dataMap;

	public ServiceImpl(String project, String module, String model) {
		dataMap = new HashMap<String, Object>();

		/** 项目 **/
		dataMap.put("project", project);
		/** 模块 **/
		if (StringUtils.isNotBlank(module))
			dataMap.put("module", module);
		/** 模型 **/
		dataMap.put("model", model);
		/** 小写开头模型 **/
		dataMap.put("_model", Generator.getLowerStr(model));
	}
	
	public String getHtml(){
		return FreemarkerUtils.getText("serviceImpl.ftl", dataMap);
	}
}
