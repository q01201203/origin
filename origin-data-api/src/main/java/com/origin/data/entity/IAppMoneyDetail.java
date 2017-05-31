package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppMoneyDetail extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用

	//add lic 170526
	public final static Integer STATUS_AUDIT_FAIL = 3; //审核失败

	public final static Integer STATUS_AUDIT_SUCCESS = 2; //审核通过

	public final static Integer STATUS_AUDIT_WAIT = 1; //待审核

	public final static Integer TYPE_BORROW = 1; //借款

	public final static Integer TYPE_REPAY = 2; //还款

	public final static Integer TYPE_WITHDRAW = 3; //提现

	public final static Integer TYPE_INCOME = 4; //收入

	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	Double getMoneyAsk();

	void setMoneyAsk(Double moneyAsk);

	Double getMoneyActual();

	void setMoneyActual(Double moneyActual);

	Integer getType();

	void setType(Integer type);

	Integer getStatus();

	void setStatus(Integer status);

	Integer getRepayWay();

	void setRepayWay(Integer repayWay);

	Date getRepayTime();

	void setRepayTime(Date repayTime);

	Integer getRepayTimeType();

	void setRepayTimeType(Integer repayTimeType);

	String getExtensionOne();

	void setExtensionOne(String extensionOne);

	String getExtensionTwo();

	void setExtensionTwo(String extensionTwo);

	Integer getUid();

	void setUid(Integer uid);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

	//add lic 170531
	public IAppUser getAppUser() ;

	public void setAppUser(IAppUser appUser) ;

	public IAppTask getAppTask();

	public void setAppTask(IAppTask appTask);
}
