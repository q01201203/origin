package com.origin.data.mybatis.entity;

import java.util.Date;

import com.origin.data.entity.IAppUser;

/**
 * 
 */
public class AppUser implements IAppUser {

	private static final long serialVersionUID = 11483885360561668L;
	
	/**  */
	private Integer id;//;
	/**  */
	private String mobile;//;
	/**  */
	private String pwd;//;
	/**  */
	private String payPwd;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;//;
	/**  */
	private Integer authority;// = Integer.valueOf(100);
	/**  */
	private Integer moneyMax;// = Integer.valueOf(2000);
	/**  */
	private String alipayUsername;//;
	/**  */
	private String alipayUseraccout;//;
	/**  */
	private String imgFace;//;
	/**  */
	private String imgIdFront;//;
	/**  */
	private String imgIdBack;//;
	/**  */
	private String userIdName;//;
	/**  */
	private String userIdNumber;//;
	/**  */
	private String imgPortrait;//;
	/**  */
	private Integer category;//;
	
	
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
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public String getPayPwd() {
		return this.payPwd;
	}
	
	public void setPayPwd(String payPwd) {
		this.payPwd = payPwd;
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
	
	public Integer getAuthority() {
		return this.authority;
	}
	
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	
	public Integer getMoneyMax() {
		return this.moneyMax;
	}
	
	public void setMoneyMax(Integer moneyMax) {
		this.moneyMax = moneyMax;
	}
	
	public String getAlipayUsername() {
		return this.alipayUsername;
	}
	
	public void setAlipayUsername(String alipayUsername) {
		this.alipayUsername = alipayUsername;
	}
	
	public String getAlipayUseraccout() {
		return this.alipayUseraccout;
	}
	
	public void setAlipayUseraccout(String alipayUseraccout) {
		this.alipayUseraccout = alipayUseraccout;
	}
	
	public String getImgFace() {
		return this.imgFace;
	}
	
	public void setImgFace(String imgFace) {
		this.imgFace = imgFace;
	}
	
	public String getImgIdFront() {
		return this.imgIdFront;
	}
	
	public void setImgIdFront(String imgIdFront) {
		this.imgIdFront = imgIdFront;
	}
	
	public String getImgIdBack() {
		return this.imgIdBack;
	}
	
	public void setImgIdBack(String imgIdBack) {
		this.imgIdBack = imgIdBack;
	}
	
	public String getUserIdName() {
		return this.userIdName;
	}
	
	public void setUserIdName(String userIdName) {
		this.userIdName = userIdName;
	}
	
	public String getUserIdNumber() {
		return this.userIdNumber;
	}
	
	public void setUserIdNumber(String userIdNumber) {
		this.userIdNumber = userIdNumber;
	}
	
	public String getImgPortrait() {
		return this.imgPortrait;
	}
	
	public void setImgPortrait(String imgPortrait) {
		this.imgPortrait = imgPortrait;
	}
	
	public Integer getCategory() {
		return this.category;
	}
	
	public void setCategory(Integer category) {
		this.category = category;
	}
	
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppUser) {
			IAppUser baseEntity = (IAppUser) object;
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
		+",pwd="+this.getPwd()
		+",payPwd="+this.getPayPwd()
		+",createDate="+this.getCreateDate()
		+",updateDate="+this.getUpdateDate()
		+",authority="+this.getAuthority()
		+",moneyMax="+this.getMoneyMax()
		+",alipayUsername="+this.getAlipayUsername()
		+",alipayUseraccout="+this.getAlipayUseraccout()
		+",imgFace="+this.getImgFace()
		+",imgIdFront="+this.getImgIdFront()
		+",imgIdBack="+this.getImgIdBack()
		+",userIdName="+this.getUserIdName()
		+",userIdNumber="+this.getUserIdNumber()
		+",imgPortrait="+this.getImgPortrait()
		+",category="+this.getCategory()
		+"]";
	}
}
