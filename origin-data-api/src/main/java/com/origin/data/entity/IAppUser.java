package com.origin.data.entity;

import java.io.Serializable;

/**
* 
*/
public interface IAppUser extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 String getMobile();

  void setMobile(String mobile);

 String getPwd();

  void setPwd(String pwd);

}
