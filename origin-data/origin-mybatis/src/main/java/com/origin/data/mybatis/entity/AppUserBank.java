package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppUserBank;

import java.util.Date;

/**
 * 
 */
public class AppUserBank implements IAppUserBank {

	private static final long serialVersionUID = 23930680313127543L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
	/** 银行名称 */
	private String bankName;//;
	/** 银行卡号 */
	private String bankNumber;//;
	/** 银行卡类型 */
	private Integer bankType;//;
	/** 银行预留手机号 */
	private String bankMobile;//;
	/** 商户用户标识 */
	private String merCustId;//;
	/** 注册流水号 */
	private String bindNo;//;
	/** 银行卡类型 */
	private String bankCardType;//;
	/** 用户业务协议号 */
	private String usrBusiAgreementId;//;
	/** 支付协议号 */
	private String usrPayAgreementId;//;
	/** 支付银行 */
	private String gateId;//;
	/** 证件号 */
	private String identityCode;//;
	/** 持卡人姓名 */
	private String cardHolder;//;
	/** 对应的用户id */
	private Integer uid;//;
	/** 删除标志（0、未删除1、删除） */
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
	
	public String getBankName() {
		return this.bankName;
	}
	
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	public String getBankNumber() {
		return this.bankNumber;
	}
	
	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}
	
	public Integer getBankType() {
		return this.bankType;
	}
	
	public void setBankType(Integer bankType) {
		this.bankType = bankType;
	}
	
	public String getBankMobile() {
		return this.bankMobile;
	}
	
	public void setBankMobile(String bankMobile) {
		this.bankMobile = bankMobile;
	}
	
	public String getMerCustId() {
		return this.merCustId;
	}
	
	public void setMerCustId(String merCustId) {
		this.merCustId = merCustId;
	}
	
	public String getBindNo() {
		return this.bindNo;
	}
	
	public void setBindNo(String bindNo) {
		this.bindNo = bindNo;
	}
	
	public String getBankCardType() {
		return this.bankCardType;
	}
	
	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}
	
	public String getUsrBusiAgreementId() {
		return this.usrBusiAgreementId;
	}
	
	public void setUsrBusiAgreementId(String usrBusiAgreementId) {
		this.usrBusiAgreementId = usrBusiAgreementId;
	}
	
	public String getUsrPayAgreementId() {
		return this.usrPayAgreementId;
	}
	
	public void setUsrPayAgreementId(String usrPayAgreementId) {
		this.usrPayAgreementId = usrPayAgreementId;
	}
	
	public String getGateId() {
		return this.gateId;
	}
	
	public void setGateId(String gateId) {
		this.gateId = gateId;
	}
	
	public String getIdentityCode() {
		return this.identityCode;
	}
	
	public void setIdentityCode(String identityCode) {
		this.identityCode = identityCode;
	}
	
	public String getCardHolder() {
		return this.cardHolder;
	}
	
	public void setCardHolder(String cardHolder) {
		this.cardHolder = cardHolder;
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
		+",bankType="+this.getBankType()
		+",bankMobile="+this.getBankMobile()
		+",merCustId="+this.getMerCustId()
		+",bindNo="+this.getBindNo()
		+",bankCardType="+this.getBankCardType()
		+",usrBusiAgreementId="+this.getUsrBusiAgreementId()
		+",usrPayAgreementId="+this.getUsrPayAgreementId()
		+",gateId="+this.getGateId()
		+",identityCode="+this.getIdentityCode()
		+",cardHolder="+this.getCardHolder()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
