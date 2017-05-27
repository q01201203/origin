package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppConstants extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用




	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getType();

	void setType(String type);

	String getKey();

	void setKey(String key);

	String getValue();

	void setValue(String value);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
