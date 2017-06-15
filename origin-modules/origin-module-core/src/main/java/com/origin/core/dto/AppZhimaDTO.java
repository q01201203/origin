package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppZhima;

/**
* 
*/
public class AppZhimaDTO implements IAppZhima {


    /**  */
	private Integer id;//;
    /**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Integer type;//;
    /**  */
	private String bizNo;//;
    /**  */
	private String score;//;
    /**  */
	private Integer uid;//;
    /**  */
	private Integer deleteFlag;// = Integer.valueOf(0);
	public AppZhimaDTO(){
	}

	public AppZhimaDTO(Integer id){
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

	public String getBizNo() {
		return this.bizNo;
	}

	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}

	public String getScore() {
		return this.score;
	}

	public void setScore(String score) {
		this.score = score;
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
		if (object instanceof IAppZhima) {
			IAppZhima baseEntity = (IAppZhima) object;
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
		+",bizNo="+this.getBizNo()
		+",score="+this.getScore()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
