package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppUser;

import java.util.Date;

/**
 * 
 */
public class AppMoneyDetail implements IAppMoneyDetail {

	private static final long serialVersionUID = 43605370234891028L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Double money;//;
	/**  */
	private Integer type;//;
	/**  */
	private Integer status;// = Integer.valueOf(1);
	/**  */
	private Integer repayWay;//;
	/**  */
	private Date repayTime;//;
	/**  */
	private Integer repayTimeType;//;
	/**  */
	private Integer uid;//;
	/**  */
	private Integer deleteFlag;// = Integer.valueOf(0);

	//add lic 170528
	private IAppUser appUser;

	public IAppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(IAppUser appUser) {
		this.appUser = appUser;
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
	
	public Double getMoney() {
		return this.money;
	}
	
	public void setMoney(Double money) {
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
	
	public Date getRepayTime() {
		return this.repayTime;
	}
	
	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
	
	public Integer getRepayTimeType() {
		return this.repayTimeType;
	}
	
	public void setRepayTimeType(Integer repayTimeType) {
		this.repayTimeType = repayTimeType;
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
		+",repayTimeType="+this.getRepayTimeType()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
