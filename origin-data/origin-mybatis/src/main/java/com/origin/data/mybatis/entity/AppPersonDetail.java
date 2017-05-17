package com.origin.data.mybatis.entity;

import java.util.Date;

import com.origin.data.entity.IAppPersonDetail;

/**
 * 
 */
public class AppPersonDetail implements IAppPersonDetail {

	private static final long serialVersionUID = 14522201099972333L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;//;
	/**  */
	private Date updateDate;//;
	/**  */
	private String infoMobile;//;
	/**  */
	private String infoCompanyAddress;//;
	/**  */
	private String infoQq;//;
	/**  */
	private String infoWeixin;//;
	/**  */
	private String infoHome;//;
	/**  */
	private Integer infoEmycontactRelation;//;
	/**  */
	private Integer infoEmycontactMobile;//;
	/**  */
	private Integer infoContactRelation;//;
	/**  */
	private Integer infoContactMobile;//;
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
	
	public String getInfoMobile() {
		return this.infoMobile;
	}
	
	public void setInfoMobile(String infoMobile) {
		this.infoMobile = infoMobile;
	}
	
	public String getInfoCompanyAddress() {
		return this.infoCompanyAddress;
	}
	
	public void setInfoCompanyAddress(String infoCompanyAddress) {
		this.infoCompanyAddress = infoCompanyAddress;
	}
	
	public String getInfoQq() {
		return this.infoQq;
	}
	
	public void setInfoQq(String infoQq) {
		this.infoQq = infoQq;
	}
	
	public String getInfoWeixin() {
		return this.infoWeixin;
	}
	
	public void setInfoWeixin(String infoWeixin) {
		this.infoWeixin = infoWeixin;
	}
	
	public String getInfoHome() {
		return this.infoHome;
	}
	
	public void setInfoHome(String infoHome) {
		this.infoHome = infoHome;
	}
	
	public Integer getInfoEmycontactRelation() {
		return this.infoEmycontactRelation;
	}
	
	public void setInfoEmycontactRelation(Integer infoEmycontactRelation) {
		this.infoEmycontactRelation = infoEmycontactRelation;
	}
	
	public Integer getInfoEmycontactMobile() {
		return this.infoEmycontactMobile;
	}
	
	public void setInfoEmycontactMobile(Integer infoEmycontactMobile) {
		this.infoEmycontactMobile = infoEmycontactMobile;
	}
	
	public Integer getInfoContactRelation() {
		return this.infoContactRelation;
	}
	
	public void setInfoContactRelation(Integer infoContactRelation) {
		this.infoContactRelation = infoContactRelation;
	}
	
	public Integer getInfoContactMobile() {
		return this.infoContactMobile;
	}
	
	public void setInfoContactMobile(Integer infoContactMobile) {
		this.infoContactMobile = infoContactMobile;
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
		if (object instanceof IAppPersonDetail) {
			IAppPersonDetail baseEntity = (IAppPersonDetail) object;
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
		+",infoMobile="+this.getInfoMobile()
		+",infoCompanyAddress="+this.getInfoCompanyAddress()
		+",infoQq="+this.getInfoQq()
		+",infoWeixin="+this.getInfoWeixin()
		+",infoHome="+this.getInfoHome()
		+",infoEmycontactRelation="+this.getInfoEmycontactRelation()
		+",infoEmycontactMobile="+this.getInfoEmycontactMobile()
		+",infoContactRelation="+this.getInfoContactRelation()
		+",infoContactMobile="+this.getInfoContactMobile()
		+",uid="+this.getUid()
		+"]";
	}
}
