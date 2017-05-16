package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppValidcode extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 String getMobile();

  void setMobile(String mobile);

 String getValidcode();

  void setValidcode(String validcode);

 Integer getType();

  void setType(Integer type);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Integer getStatus();

  void setStatus(Integer status);

}
