package com.origin.tool.generate;

import com.origin.tool.system.FreemarkerUtils;
import com.origin.tool.system.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjjmac on 17/4/9.
 */
public class IDao {
    private Map<String, Object> dataMap;

    public IDao(String project, String module, String model) {
        dataMap = new HashMap<String, Object>();

        /** 项目 **/
        dataMap.put("project", project);
        /** 模块 **/
        if (StringUtils.isNotBlank(module))
            dataMap.put("module", module);
        /** 模型 **/
        dataMap.put("model", model);
    }

    public String getHtml(){
        return FreemarkerUtils.getText("idao.ftl", dataMap);
    }
}
