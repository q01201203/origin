package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppGuide extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Date getUpdateDate();

  void setUpdateDate(Date updateDate);

 String getGuideName();

  void setGuideName(String guideName);

 String getGuideContent();

  void setGuideContent(String guideContent);

 Integer getGuideType();

  void setGuideType(Integer guideType);

 Integer getDeleteFlag();

  void setDeleteFlag(Integer deleteFlag);

}
