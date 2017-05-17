package com.origin.data.mybatis.entity;

import java.util.Date;

import com.origin.data.entity.IAppStuDetail;

/**
 * 
 */
public class AppStuDetail implements IAppStuDetail {

	private static final long serialVersionUID = 29522566924183337L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;//;
	/**  */
	private Date updateDate;//;
	/**  */
	private String infoMobile;//;
	/**  */
	private String infoSchool;//;
	/**  */
	private String infoDepartment;//;
	/**  */
	private String infoClass;//;
	/**  */
	private String infoRoomnumber;//;
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
	
	public String getInfoSchool() {
		return this.infoSchool;
	}
	
	public void setInfoSchool(String infoSchool) {
		this.infoSchool = infoSchool;
	}
	
	public String getInfoDepartment() {
		return this.infoDepartment;
	}
	
	public void setInfoDepartment(String infoDepartment) {
		this.infoDepartment = infoDepartment;
	}
	
	public String getInfoClass() {
		return this.infoClass;
	}
	
	public void setInfoClass(String infoClass) {
		this.infoClass = infoClass;
	}
	
	public String getInfoRoomnumber() {
		return this.infoRoomnumber;
	}
	
	public void setInfoRoomnumber(String infoRoomnumber) {
		this.infoRoomnumber = infoRoomnumber;
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
		if (object instanceof IAppStuDetail) {
			IAppStuDetail baseEntity = (IAppStuDetail) object;
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
		+",infoSchool="+this.getInfoSchool()
		+",infoDepartment="+this.getInfoDepartment()
		+",infoClass="+this.getInfoClass()
		+",infoRoomnumber="+this.getInfoRoomnumber()
		+",infoEmycontactRelation="+this.getInfoEmycontactRelation()
		+",infoEmycontactMobile="+this.getInfoEmycontactMobile()
		+",infoContactRelation="+this.getInfoContactRelation()
		+",infoContactMobile="+this.getInfoContactMobile()
		+",uid="+this.getUid()
		+"]";
	}
}
