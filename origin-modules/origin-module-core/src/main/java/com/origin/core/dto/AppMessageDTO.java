package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppMessage;

/**
* 
*/
public class AppMessageDTO implements IAppMessage {


    /**  */
	private Integer id;//;
    /**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Integer type;//;
    /**  */
	private Integer status;// = Integer.valueOf(1);
    /**  */
	private String content;//;
    /**  */
	private String contentExtra;//;
    /**  */
	private Integer uid;//;
    /**  */
	private Integer deleteFlag;// = Integer.valueOf(0);
	public AppMessageDTO(){
	}

	public AppMessageDTO(Integer id){
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

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentExtra() {
		return this.contentExtra;
	}

	public void setContentExtra(String contentExtra) {
		this.contentExtra = contentExtra;
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

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppMessage) {
			IAppMessage baseEntity = (IAppMessage) object;
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
		+",type="+this.getType()
		+",status="+this.getStatus()
		+",content="+this.getContent()
		+",contentExtra="+this.getContentExtra()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
