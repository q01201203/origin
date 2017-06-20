package com.origin.data.entity;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

public class BasePermission implements IBasePermission{
	/**
	 * 创建日期
	 */
	protected Date createDate;
	/**
	 * 修改日期
	 */
	protected Date updateDate;
	
	/**
	 * 删除标志(0-正常，1-删除)
	 * @return
	 */
	protected String deleteFlag;
	
	/**
	 * 审核状态(0-未审核，1-审核通过，2-审核不通过)
	 * @return
	 */
	protected String auditFlag;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getAuditFlag() {
		return auditFlag;
	}
	public void setAuditFlag(String auditFlag) {
		this.auditFlag = auditFlag;
	}

	public void prePersist() {
		System.out.println("renxinhua prePersist");
		if(this.createDate == null){
			this.setCreateDate(new Date());
		}
		this.setUpdateDate(new Date());
		if(StringUtils.isBlank(this.getDeleteFlag())){
			this.setDeleteFlag(BasePermission.DELETE_FLAG_NORMAL);	
		}
    }

    public void preUpdate() {
    	this.setUpdateDate(new Date());
    }

}
