package com.origin.data.entity;

import java.util.Date;

public interface IBasePermission {
	//逻辑删除标识位—已删除状态
	public static final String DELETE_FLAG_DELETED = "1";
	
	//逻辑删除标识位—未删除状态
	public static final String DELETE_FLAG_NORMAL = "0";
	
	//审核状态标识位—未审核
	public static final String AUDIT_FLAG_NOT = "0";
	
	//审核状态标识位—审核通过
	public static final String AUDIT_FLAG_PASS = "1";
	
	//审核状态标识位—审核通不过
	public static final String AUDIT_FLAG_FAIL = "2";
	Date getCreateDate();
	void setCreateDate(Date createDate);
	Date getUpdateDate();
	void setUpdateDate(Date updateDate);
	String getDeleteFlag();
	void setDeleteFlag(String deleteFlag);
	String getAuditFlag();
	void setAuditFlag(String auditFlag);
	void preUpdate();
	void prePersist();
}
