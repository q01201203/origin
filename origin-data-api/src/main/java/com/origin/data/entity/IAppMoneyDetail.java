package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppMoneyDetail extends Serializable {

public final static Integer STATUS_YES = 1; //可用

public final static Integer STATUS_NO = 0; //不可用

 public final static Integer TYPE_BORROW = 1; //借款

 public final static Integer TYPE_REPAY = 2; //还款


 Integer getId();

  void setId(Integer id);

 Date getCreateDate();

  void setCreateDate(Date createDate);

 Date getUpdateDate();

  void setUpdateDate(Date updateDate);

 Integer getMoney();

  void setMoney(Integer money);

 Integer getType();

  void setType(Integer type);

 Integer getStatus();

  void setStatus(Integer status);

 Integer getRepayWay();

  void setRepayWay(Integer repayWay);

 Integer getRepayTime();

  void setRepayTime(Integer repayTime);

 Integer getUid();

  void setUid(Integer uid);

}
