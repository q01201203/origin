package com.origin.tool.generate;



import com.origin.tool.system.DummyField;
import com.origin.tool.system.FreemarkerUtils;
import com.origin.tool.system.Generator;
import com.origin.tool.system.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Action
 * 
 * @author Joe
 */
public class Controller {

	private Map<String, Object> dataMap;

	public Controller(String project, String module, String model, List<DummyField> fieldList,
			boolean containEnable, boolean containDate, String tableComment) {
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
		/** 是否包含启用 **/
		dataMap.put("containEnable", containEnable);
		/** 是否包含Date **/
		dataMap.put("containDate", containDate);
		/** 字段list **/
		dataMap.put("fieldList", fieldList);
		/** 表描述 **/
		dataMap.put("tableComment", tableComment);

	}

	public String getHtml() {
		return FreemarkerUtils.getText("controller.ftl", dataMap);
	}
}