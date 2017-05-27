package com.origin.data.entity;

<#if containDate>
import java.util.Date;
</#if>
import java.io.Serializable;

<#if tableComment??>
/**
* ${tableComment}
*/
</#if>
public interface I${model} extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用



<#list fieldList as field>

	${field.fieldType} get${field.upperFieldName}();

	void set${field.upperFieldName}(${field.fieldType} ${field.fieldName});
</#list>

<#if containEnable>
	@Transient
	public String getIsEnableStr() {
		return (isEnable != null && isEnable) ? TrueFalseEnum.TRUE.getLabel() : TrueFalseEnum.FALSE.getLabel();
	}
</#if>
}
