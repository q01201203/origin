package com.origin.tool.system;

import com.origin.tool.generate.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 生成器主类
 * 
 * @author Joe
 */
public class Generator extends BaseFrame {

	private static final long serialVersionUID = 6800734227505322482L;
	
	private static final String ADMIN = "admin";

	public static void main(String[] args) {
		Generator editor = new Generator();
		editor.setDefaultCloseOperation(3);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension ownerSize = new Dimension(680, 590);
		editor.setSize(ownerSize);
		editor.setLocation((screenSize.width - ownerSize.width) / 2, (screenSize.height - ownerSize.height) / 2);
		editor.setVisible(true);
	}

	protected void changeTextValue() {
		this.modelText.setText(config.getDataPath()+ "/" +getPackageName() + config.getDataModuleName() + "/" + "entity"
				+ (analyzer != null ? "/" + analyzer.getModelName() + ".java" : ""));
		this.modelPdoText.setText(config.getServicePath()+ "/" +getPackageName() + config.getServiceModuleName() + "/" + "dto"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "DTO.java" : ""));
		this.serviceText.setText(config.getServicePath()+ "/" +getPackageName() + config.getServiceModuleName() + "/" + "service"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "Service.java" : ""));
		this.ServiceImplText.setText(config.getServicePath()+ "/" +getPackageName() + config.getServiceModuleName() + "/" + "service/impl"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "ServiceImpl.java" : ""));
		this.imodelText.setText(config.getApiPath()+ "/" +getPackageName() + config.getApiModuleName() + "/" + "entity"
				+ (analyzer != null ? "/" + "I" + analyzer.getModelName() + ".java" : ""));
		this.idaoText.setText(config.getApiPath()+ "/" +getPackageName() + config.getApiModuleName() + "/" + "dao"
				+ (analyzer != null ? "/" + "I" + analyzer.getModelName() + "Dao.java" : ""));
		this.daoText.setText(config.getDataPath()+ "/" +getPackageName() + config.getDataModuleName() + "/" + "dao"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "Dao.java" : ""));
		this.modelXmlText.setText(config.getDataPath()+ "/" +getPackageName() + config.getDataModuleName() + "/" + "mapper"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "Dao.xml" : ""));
		
//		String adminString = (adminCheckBox.isSelected() ? "/" + ADMIN : "");
		this.controllerText.setText(config.getServicePath()+ "/" +getPackageName() + config.getServiceModuleName() + "/" + "controller"
				+ (analyzer != null ? "/" + analyzer.getModelName() + "Controller.java" : ""));

		this.listText.setText(config.getServicePath()+ "/" +getViewBasePath()
				+ (analyzer != null ? "/"+analyzer.getLowerModelName() +"/"+ analyzer.getLowerModelName() + "_list.jsp" : ""));
		this.editText.setText(config.getServicePath()+ "/" +getViewBasePath()
				+ (analyzer != null ? "/"+analyzer.getLowerModelName() +"/" + "dialog/" + analyzer.getLowerModelName() + "_edit.jsp" : ""));
	}

	private String getPackageName() {
		return getClassBasePath() + "/" + "com" + "/"
				+ (StringUtils.isNotBlank(projectText.getText()) ? projectText.getText() + "/" : "");
				//+ (StringUtils.isNotBlank(moduleText.getText()) ? moduleText.getText() + "/" : "");
	}

	private String getClassBasePath() {
		return "src/main/java";
	}

	private String getViewBasePath() {
		return "src/main/webapp/WEB-INF/views";
	}

	@Override
	protected void generateFile(String basePath) {
		generateModelFile(basePath);
		generateModelXmlFile(basePath);
		generateServiceFile(basePath);
		generateServiceImplFile(basePath);
		generateIDaoFile(basePath);
		generateIModelFile(basePath);
		generateModelPdoFile(basePath);
		generateDaoFile(basePath);
		generateControllerFile(basePath);
		generateListFile(basePath);
		generateEditFile(basePath);
	}

	private void generateModelFile(String basePath) {
		List<DummyField> allList = analyzer.getFieldList();
//		Set<String> excludeFieldSet = persistentMap.get(extendsBox.getSelectedItem());
		List<DummyField> fieldList = new ArrayList<DummyField>();
		boolean containDate = false;
		for (DummyField dumField : allList) {
//			if (excludeFieldSet.contains(dumField.getFieldName())) {
//				continue;
//			}
			if ("Date".equals(dumField.getFieldType())) {
				containDate = true;
			}
			fieldList.add(dumField);
		}
		FileUtils.createFile(
				basePath,
				modelText.getText(),
				new Model(projectText.getText(), moduleText.getText(),
						analyzer.getModelName(), fieldList, analyzer.getTableName(), Long.valueOf(StringUtils.getRandom(17)).toString(), analyzer
								.isContainEnable(), containDate, analyzer.getTableComment()).getHtml());
	}

	private void generateModelPdoFile(String basePath) {
		List<DummyField> allList = analyzer.getFieldList();
		List<DummyField> fieldList = new ArrayList<DummyField>();
		boolean containDate = false;
		for (DummyField dumField : allList) {
			if ("Date".equals(dumField.getFieldType())) {
				containDate = true;
			}
			fieldList.add(dumField);
		}
		FileUtils.createFile(
				basePath,
				modelPdoText.getText(),
				new ModelDTO(projectText.getText(), config.getServiceModuleName(),
						analyzer.getModelName(), fieldList, analyzer.getTableName(), Long.valueOf(StringUtils.getRandom(17)).toString(), analyzer
						.isContainEnable(), containDate, analyzer.getTableComment()).getHtml());
	}

	private void generateIModelFile(String basePath) {
		List<DummyField> allList = analyzer.getFieldList();
		List<DummyField> fieldList = new ArrayList<DummyField>();
		boolean containDate = false;
		for (DummyField dumField : allList) {
			if ("Date".equals(dumField.getFieldType())) {
				containDate = true;
			}
			fieldList.add(dumField);
		}
		FileUtils.createFile(
				basePath,
				imodelText.getText(),
				new IModel(projectText.getText(), moduleText.getText(),
						analyzer.getModelName(), fieldList, analyzer.getTableName(), Long.valueOf(StringUtils.getRandom(17)).toString(), analyzer
						.isContainEnable(), containDate, analyzer.getTableComment()).getHtml());
	}

	private void generateModelXmlFile(String basePath) {
		List<DummyField> allList = analyzer.getFieldList();
//		Set<String> excludeFieldSet = persistentMap.get(extendsBox.getSelectedItem());
		List<DummyField> fieldList = new ArrayList<DummyField>();
		boolean containDate = false;
		for (DummyField dumField : allList) {
//			if (excludeFieldSet.contains(dumField.getFieldName())) {
//				continue;
//			}
			if ("Date".equals(dumField.getFieldType())) {
				containDate = true;
			}
			fieldList.add(dumField);
		}
		FileUtils.createFile(
				basePath,
				modelXmlText.getText(),
				new ModelXml(projectText.getText(), moduleText.getText(), analyzer
						.getModelName(), fieldList, analyzer.getTableName(),
						Long.valueOf(StringUtils.getRandom(17)).toString(), analyzer.isContainEnable(), containDate,
						analyzer.getTableComment()).getHtml());
	}

	private void generateServiceFile(String basePath) {
		FileUtils.createFile(
				basePath,
				serviceText.getText(),
				new Service(projectText.getText(), config.getServiceModuleName(), analyzer
						.getModelName()).getHtml());
	}

	private void generateServiceImplFile(String basePath) {
		FileUtils.createFile(
				basePath,
				ServiceImplText.getText(),
				new ServiceImpl(projectText.getText(), config.getServiceModuleName(), analyzer
						.getModelName()).getHtml());
	}

	private void generateIDaoFile(String basePath) {
		FileUtils.createFile(basePath, idaoText.getText(), new IDao(projectText.getText(),
				moduleText.getText(), analyzer.getModelName()).getHtml());
	}

	private void generateDaoFile(String basePath) {
		FileUtils.createFile(basePath, daoText.getText(), new Dao(projectText.getText(),
				moduleText.getText(), analyzer.getModelName()).getHtml());
	}

	private void generateControllerFile(String basePath) {
		List<DummyField> allList = analyzer.getFieldList();
		//Set<String> excludeFieldSet = persistentMap.get(extendsBox.getSelectedItem());
		List<DummyField> fieldList = new ArrayList<DummyField>();
		boolean containDate = false;
		for (DummyField dumField : allList) {
//			if (excludeFieldSet.contains(dumField.getFieldName())) {
//				continue;
//			}
			if ("Date".equals(dumField.getFieldType())) {
				containDate = true;
			}
			fieldList.add(dumField);
		}

		FileUtils
				.createFile(
						basePath,
						controllerText.getText(),
						new Controller(projectText.getText(), config.getServiceModuleName(), analyzer
								.getModelName(), fieldList, analyzer.isContainEnable(), containDate, analyzer
								.getTableComment()).getHtml());
	}

	private void generateListFile(String basePath) {
		if (StringUtils.isNotBlank(this.listText.getText())) {
			List<DummyField> allList = analyzer.getFieldList();
//			Set<String> excludeFieldSet = persistentMap.get(extendsBox.getSelectedItem());
			List<DummyField> fieldList = new ArrayList<DummyField>();
			for (DummyField dumField : allList) {
//				if (excludeFieldSet.contains(dumField.getFieldName())) {
//					continue;
//				}
				fieldList.add(dumField);
			}

			FileUtils.createFile(
					basePath,
					listText.getText(),
					new com.origin.tool.generate.List(analyzer.getTableComment(), analyzer.getModelName(), analyzer
							.isContainEnable(), Analyzer.ENABLE_NAME, fieldList).getHtml());
		}
	}

	private void generateEditFile(String basePath) {
		if (StringUtils.isNotBlank(this.editText.getText())) {
			List<DummyField> allList = analyzer.getFieldList();
//			Set<String> excludeFieldSet = persistentMap.get(extendsBox.getSelectedItem());
			List<DummyField> fieldList = new ArrayList<DummyField>();
			for (DummyField dumField : allList) {
//				if (excludeFieldSet.contains(dumField.getFieldName())) {
//					continue;
//				}
				fieldList.add(dumField);
			}
			FileUtils
					.createFile(basePath, editText.getText(),
							new Edit(analyzer.getTableComment(), analyzer.getModelName(), analyzer.isContainEnable(),
									fieldList).getHtml());
		}
	}

	/**
	 * 首字母大写
	 */
	public static String getUpperStr(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1, str.length());
	}

	/**
	 * 首字母小写
	 */
	public static String getLowerStr(String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1, str.length());
	}
}
