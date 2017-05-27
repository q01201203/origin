package com.origin.core.dto;

import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppUser;

import java.util.Date;
import java.util.List;


/**
* 
*/
public class AppUserDTO implements IAppUser {


    /**  */
	private Integer id;//;
    /**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
	private String mobile;//;
    /**  */
	private String pwd;//;
    /**  */
	private String payPwd;//;
    /**  */
	private Double balance;// = 0D;
    /**  */
	private Integer authority;// = Integer.valueOf(100);
    /**  */
	private Double moneyMax;// = 2000D;
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
	private String nickname;//;
    /**  */
	private Integer category;//;
    /**  */
	private Integer deleteFlag;// = Integer.valueOf(0);
	//add lic 170527
	private List<IAppMoneyDetail> appMoneyDetails;

	public List<IAppMoneyDetail> getAppMoneyDetails() {
		return appMoneyDetails;
	}

	public void setAppMoneyDetails(List<IAppMoneyDetail> appMoneyDetails) {
		this.appMoneyDetails = appMoneyDetails;
	}
	public AppUserDTO(){
	}

	public AppUserDTO(Integer id){
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

	public Double getBalance() {
		return this.balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getAuthority() {
		return this.authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public Double getMoneyMax() {
		return this.moneyMax;
	}

	public void setMoneyMax(Double moneyMax) {
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

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getCategory() {
		return this.category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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
		+",createDate="+this.getCreateDate()
		+",updateDate="+this.getUpdateDate()
		+",mobile="+this.getMobile()
		+",pwd="+this.getPwd()
		+",payPwd="+this.getPayPwd()
		+",balance="+this.getBalance()
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
		+",nickname="+this.getNickname()
		+",category="+this.getCategory()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
