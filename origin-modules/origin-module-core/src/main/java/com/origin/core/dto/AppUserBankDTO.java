package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppUserBank;

/**
* 
*/
public class AppUserBankDTO implements IAppUserBank {


    /**  */
private Integer id;//;
    /**  */
private Date createDate;//;
    /**  */
private Date updateDate;//;
    /**  */
private String bankName;//;
    /**  */
private Integer bankNumber;//;
    /**  */
private Integer bankMobile;//;
    /**  */
private Integer uid;//;
public AppUserBankDTO(){
}

public AppUserBankDTO(Integer id){
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

public String getBankName() {
return this.bankName;
}

public void setBankName(String bankName) {
this.bankName = bankName;
}

public Integer getBankNumber() {
return this.bankNumber;
}

public void setBankNumber(Integer bankNumber) {
this.bankNumber = bankNumber;
}

public Integer getBankMobile() {
return this.bankMobile;
}

public void setBankMobile(Integer bankMobile) {
this.bankMobile = bankMobile;
}

public Integer getUid() {
return this.uid;
}

public void setUid(Integer uid) {
this.uid = uid;
}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppUserBank) {
			IAppUserBank baseEntity = (IAppUserBank) object;
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
		+",bankName="+this.getBankName()
		+",bankNumber="+this.getBankNumber()
		+",bankMobile="+this.getBankMobile()
		+",uid="+this.getUid()
		+"]";
	}
}
