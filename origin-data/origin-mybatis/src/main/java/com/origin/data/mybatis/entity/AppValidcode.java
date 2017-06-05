package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppValidcode;
import java.util.Date;

/**
 * 
 */
public class AppValidcode implements IAppValidcode {

	private static final long serialVersionUID = 44431168368338378L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
	/**  */
	private String mobile;//;
	/**  */
	private String validcode;//;
	/**  */
	private Integer type;// = Integer.valueOf(0);
	/**  */
	private Integer count;// = Integer.valueOf(0);
	/**  */
	private Integer status;// = Integer.valueOf(1);
	/**  */
	private Integer deleteFlag;// = Integer.valueOf(0);
	
	
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
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getValidcode() {
		return this.validcode;
	}
	
	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getCount() {
		return this.count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
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
		if (object instanceof IAppValidcode) {
			IAppValidcode baseEntity = (IAppValidcode) object;
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
		+",mobile="+this.getMobile()
		+",validcode="+this.getValidcode()
		+",type="+this.getType()
		+",count="+this.getCount()
		+",status="+this.getStatus()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
