package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppStuDetail extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Date getUpdateDate();

  void setUpdateDate(Date updateDate);

 String getInfoMobile();

  void setInfoMobile(String infoMobile);

 String getInfoSchool();

  void setInfoSchool(String infoSchool);

 String getInfoDepartment();

  void setInfoDepartment(String infoDepartment);

 String getInfoClass();

  void setInfoClass(String infoClass);

 String getInfoRoomnumber();

  void setInfoRoomnumber(String infoRoomnumber);

 Integer getInfoEmycontactRelation();

  void setInfoEmycontactRelation(Integer infoEmycontactRelation);

 Integer getInfoEmycontactMobile();

  void setInfoEmycontactMobile(Integer infoEmycontactMobile);

 Integer getInfoContactRelation();

  void setInfoContactRelation(Integer infoContactRelation);

 Integer getInfoContactMobile();

  void setInfoContactMobile(Integer infoContactMobile);

 Integer getUid();

  void setUid(Integer uid);

}
