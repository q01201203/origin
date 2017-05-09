package com.origin.data.mybatis.entity;

<#if containDate>
import java.util.Date;
</#if>

import com.origin.data.entity.I${model};

<#if tableComment??>
/**
 * ${tableComment}
 */
</#if>
public class ${model} implements I${model} {

	private static final long serialVersionUID = ${versionId}L;
	
	<#list fieldList as field>
		<#if field.description??>
	/** ${field.description} */
	  	</#if>
	private ${field.fieldType} ${field.fieldName};//<#if field.defaultValue??><#if field.fieldType == "Boolean"> = <#if field.defaultValue == "0">Boolean.FALSE<#else>Boolean.TRUE</#if><#elseif field.fieldType == "Integer"> = Integer.valueOf(${field.defaultValue})<#elseif field.fieldType == "Double"> = ${field.defaultValue}D<#else> = ${field.defaultValue}</#if></#if>;
	</#list>
	
	<#list fieldList as field>
	
	public ${field.fieldType} get${field.upperFieldName}() {
		return this.${field.fieldName};
	}
	
	public void set${field.upperFieldName}(${field.fieldType} ${field.fieldName}) {
		this.${field.fieldName} = ${field.fieldName};
	}
	</#list>
	
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof I${model}) {
			I${model} baseEntity = (I${model}) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}
	public String toString() {
		return this.getClass().getName() + "["
		<#list fieldList as field>
		+",${field.fieldName}="+this.get${field.upperFieldName}()
		</#list>
		+"]";
	}
	<#if containEnable>
	@Transient
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? TrueFalseEnum.TRUE.getLabel() : TrueFalseEnum.FALSE.getLabel();
	}
	</#if>
}
