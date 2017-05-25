package com.origin.data.entity;

import java.io.Serializable;

/**
* 
*/
public interface IAppUserTask extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 Integer getUid();

  void setUid(Integer uid);

 Integer getTid();

  void setTid(Integer tid);

 Integer getStatus();

  void setStatus(Integer status);

 public IAppUser getAppUser();

 public void setAppUser(IAppUser appUser) ;
}
