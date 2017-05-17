package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppPersonDetail extends Serializable {

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

 String getInfoCompanyAddress();

  void setInfoCompanyAddress(String infoCompanyAddress);

 String getInfoQq();

  void setInfoQq(String infoQq);

 String getInfoWeixin();

  void setInfoWeixin(String infoWeixin);

 String getInfoHome();

  void setInfoHome(String infoHome);

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
