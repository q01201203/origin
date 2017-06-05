package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppValidcode extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用


	public final static String TYPE_REGISTER = "1"; //注册
	public final static String TYPE_RESETPWD = "2"; //找回密码
	public final static String TYPE_BORROW = "3"; //借钱


	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getMobile();

	void setMobile(String mobile);

	String getValidcode();

	void setValidcode(String validcode);

	Integer getType();

	void setType(Integer type);

	Integer getCount();

	void setCount(Integer count);

	Integer getStatus();

	void setStatus(Integer status);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
