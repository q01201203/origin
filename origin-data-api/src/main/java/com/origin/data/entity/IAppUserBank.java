package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppUserBank extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用




	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getBankName();

	void setBankName(String bankName);

	String getBankNumber();

	void setBankNumber(String bankNumber);

	Integer getBankType();

	void setBankType(Integer bankType);

	String getBankMobile();

	void setBankMobile(String bankMobile);

	String getMerCustId();

	void setMerCustId(String merCustId);

	String getBindNo();

	void setBindNo(String bindNo);

	String getBankCardType();

	void setBankCardType(String bankCardType);

	String getUsrBusiAgreementId();

	void setUsrBusiAgreementId(String usrBusiAgreementId);

	String getUsrPayAgreementId();

	void setUsrPayAgreementId(String usrPayAgreementId);

	String getGateId();

	void setGateId(String gateId);

	String getIdentityCode();

	void setIdentityCode(String identityCode);

	String getCardHolder();

	void setCardHolder(String cardHolder);

	Integer getUid();

	void setUid(Integer uid);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
