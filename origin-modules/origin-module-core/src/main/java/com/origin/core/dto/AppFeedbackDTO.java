package com.origin.core.dto;

import com.origin.data.entity.IAppFeedback;
import com.origin.data.entity.IAppUser;

import java.util.Date;

/**
* 
*/
public class AppFeedbackDTO implements IAppFeedback {


    /**  */
	private Integer id;//;
    /**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
	private String content;//;
    /**  */
	private Integer uid;//;
    /**  */
	private Integer deleteFlag;// = Integer.valueOf(0);

	private IAppUser appUser;

	public AppFeedbackDTO(){
	}

	public AppFeedbackDTO(Integer id){
		this.id = id;
	}


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUid() {
		return this.uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public IAppUser getAppUser() {
		return appUser;
	}

	@Override
	public void setAppUser(IAppUser appUser) {
		this.appUser = appUser;
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppFeedback) {
			IAppFeedback baseEntity = (IAppFeedback) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}
	public String toString() {
		return this.getClass().getName() + "["
		+",id="+this.getId()
		+",createDate="+this.getCreateDate()
		+",updateDate="+this.getUpdateDate()
		+",content="+this.getContent()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
