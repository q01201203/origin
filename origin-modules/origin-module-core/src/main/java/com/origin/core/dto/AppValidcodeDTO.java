package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppValidcode;

/**
* 
*/
public class AppValidcodeDTO implements IAppValidcode {


    /**  */
private Integer id;//;
    /**  */
private String mobile;//;
    /**  */
private String validcode;//;
    /**  */
private Integer type;//;
    /**  */
private Date createDate;//;
    /**  */
private Integer status;//;
public AppValidcodeDTO(){
}

public AppValidcodeDTO(Integer id){
	this.id = id;
}


public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
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

public Date getCreateDate() {
return this.createDate;
}

public void setCreateDate(Date createDate) {
this.createDate = createDate;
}

public Integer getStatus() {
return this.status;
}

public void setStatus(Integer status) {
this.status = status;
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
		+",mobile="+this.getMobile()
		+",validcode="+this.getValidcode()
		+",type="+this.getType()
		+",createDate="+this.getCreateDate()
		+",status="+this.getStatus()
		+"]";
	}
}
