package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppUserTask extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用

	//add lic 170526
	public final static Integer STATUS_AUDIT_FAIL = 2; //审核失败

	public final static Integer STATUS_AUDIT_SUCCESS = 1; //审核通过

	public final static Integer STATUS_AUDIT_WAIT = 0; //待审核


	Integer getId();

	void setId(Integer id);

	Integer getUid();

	void setUid(Integer uid);

	Integer getTid();

	void setTid(Integer tid);

	Date getAuditTime();

	void setAuditTime(Date auditTime);

	Integer getStatus();

	void setStatus(Integer status);

 	public IAppUser getAppUser();
 	
 	public void setAppUser(IAppUser appUser) ;

 	public IAppTask getAppTask();

	public void setAppTask(IAppTask appTask);
}
