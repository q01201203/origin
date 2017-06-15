package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppMessage extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用

	public final static Integer TYPE_PERSONAL = 1; //个人消息

	public final static Integer TYPE_SYSTEM = 2; //系统消息

	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	Integer getType();

	void setType(Integer type);

	Integer getStatus();

	void setStatus(Integer status);

	String getContent();

	void setContent(String content);

	String getContentExtra();

	void setContentExtra(String contentExtra);

	Integer getUid();

	void setUid(Integer uid);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
