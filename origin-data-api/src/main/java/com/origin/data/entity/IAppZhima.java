package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppZhima extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用

	public final static Integer TYPE_CREDIT_SCORE = 1; //芝麻信用分
	public final static Integer TYPE_CREDIT_WATCHLISTII = 2; //芝麻行业关注名单
	public final static Integer TYPE_CREDIT_ANTIFRAUD_SCORE = 3; //芝麻欺诈评分
	public final static Integer TYPE_CREDIT_ANTIFRAUDVERIFY = 4; //欺诈信息验证
	public final static Integer TYPE_CREDIT_ANTIFRAUD_RISKLIST = 5; //芝麻欺诈关注清单


	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	Integer getType();

	void setType(Integer type);

	String getBizNo();

	void setBizNo(String bizNo);

	String getScore();

	void setScore(String score);

	String getErrorMessage();

	void setErrorMessage(String errorMessage);

	String getIsMatched();

	void setIsMatched(String isMatched);

	String getVerifyCode();

	void setVerifyCode(String verifyCode);

	String getHit();

	void setHit(String hit);

	String getRiskCode();

	void setRiskCode(String riskCode);

	Integer getUid();

	void setUid(Integer uid);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
