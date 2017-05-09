package com.origin.tool.system;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.util.Map;

/**
 * 通过模板和数据获取Html内容
 * 
 * @author Joe
 */
public class FreemarkerUtils {
	/** 编码 */
	public static final String UNICODE_UTF8 = "UTF-8";

	/** 模板存放路径 */
	public static final String TEMPLATE_URL = "/WEB-INF/common/template";

	/** 创建freemarker配置实例 */
	private static Configuration config = new Configuration(Configuration.VERSION_2_3_23);

	static {
		try {
			config.setDirectoryForTemplateLoading(new File(FreemarkerUtils.class.getResource("/").getPath()+"com/origin/tool/template/"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getText(String templateName, Map<?, ?> dataMap) {
		String htmlText = "";
		try {
			Template temp = config.getTemplate(templateName, UNICODE_UTF8);
			htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(temp, dataMap);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return htmlText;
	}
}
