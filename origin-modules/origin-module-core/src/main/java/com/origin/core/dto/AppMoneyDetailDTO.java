package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppMoneyDetail;

/**
* 
*/
public class AppMoneyDetailDTO implements IAppMoneyDetail {


    /**  */
private Integer id;//;
    /**  */
private Date createDate;//;
    /**  */
private Integer money;//;
    /**  */
private Integer type;//;
    /**  */
private Integer status;//;
    /**  */
private Integer payWay;//;
    /**  */
private Integer payTime;//;
    /**  */
private Integer uid;//;
public AppMoneyDetailDTO(){
}

public AppMoneyDetailDTO(Integer id){
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

public Integer getMoney() {
return this.money;
}

public void setMoney(Integer money) {
this.money = money;
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

public Integer getPayWay() {
return this.payWay;
}

public void setPayWay(Integer payWay) {
this.payWay = payWay;
}

public Integer getPayTime() {
return this.payTime;
}

public void setPayTime(Integer payTime) {
this.payTime = payTime;
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
		if (object instanceof IAppMoneyDetail) {
			IAppMoneyDetail baseEntity = (IAppMoneyDetail) object;
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
		+",money="+this.getMoney()
		+",type="+this.getType()
		+",status="+this.getStatus()
		+",payWay="+this.getPayWay()
		+",payTime="+this.getPayTime()
		+",uid="+this.getUid()
		+"]";
	}
}
