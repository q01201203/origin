package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppZhima extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用

	public final static Integer TYPE_CREDIT_SCORE = 1; //芝麻信用分




	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	Integer getType();

	void setType(Integer type);

	String getBizNo();

	void setBizNo(String bizNo);

	String getScore();

	void setScore(String score);

	Integer getUid();

	void setUid(Integer uid);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
