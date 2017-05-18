package com.origin.data.mybatis.entity;

import java.util.Date;

import com.origin.data.entity.IAppMoneyDetail;

/**
 * 
 */
public class AppMoneyDetail implements IAppMoneyDetail {

	private static final long serialVersionUID = 9328559145576475L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;//;
	/**  */
	private Date updateDate;//;
	/**  */
	private Integer money;//;
	/**  */
	private Integer type;//;
	/**  */
	private Integer status;// = Integer.valueOf(1);
	/**  */
	private Integer repayWay;//;
	/**  */
	private Integer repayTime;//;
	/**  */
	private Integer uid;//;
	
	
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
	
	public Integer getRepayWay() {
		return this.repayWay;
	}
	
	public void setRepayWay(Integer repayWay) {
		this.repayWay = repayWay;
	}
	
	public Integer getRepayTime() {
		return this.repayTime;
	}
	
	public void setRepayTime(Integer repayTime) {
		this.repayTime = repayTime;
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
		+",updateDate="+this.getUpdateDate()
		+",money="+this.getMoney()
		+",type="+this.getType()
		+",status="+this.getStatus()
		+",repayWay="+this.getRepayWay()
		+",repayTime="+this.getRepayTime()
		+",uid="+this.getUid()
		+"]";
	}
}
