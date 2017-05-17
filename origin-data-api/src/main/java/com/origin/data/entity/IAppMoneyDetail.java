package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppMoneyDetail extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用




 Integer getId();

  void setId(Integer id);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Integer getMoney();

  void setMoney(Integer money);

 Integer getType();

  void setType(Integer type);

 Integer getStatus();

  void setStatus(Integer status);

 Integer getPayWay();

  void setPayWay(Integer payWay);

 Integer getPayTime();

  void setPayTime(Integer payTime);

 Integer getUid();

  void setUid(Integer uid);

}
