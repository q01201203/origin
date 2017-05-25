package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppUserBank extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Date getUpdateDate();

  void setUpdateDate(Date updateDate);

 String getBankName();

  void setBankName(String bankName);

 Integer getBankNumber();

  void setBankNumber(Integer bankNumber);

 Integer getBankType();

  void setBankType(Integer bankType);

 String getBankMobile();

  void setBankMobile(String bankMobile);

 Integer getUid();

  void setUid(Integer uid);

 Integer getDeleteFlag();

  void setDeleteFlag(Integer deleteFlag);

}
