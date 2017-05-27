package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;
/**
* 
*/
public interface IAppUser extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用
	public final static Integer CATEGORY_STU = 1; //学生
	public final static Integer CATEGORY_PERSON = 2; //社会人群




	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getMobile();

	void setMobile(String mobile);

	String getPwd();

	void setPwd(String pwd);

	String getPayPwd();

	void setPayPwd(String payPwd);

	Double getBalance();

	void setBalance(Double balance);

	Integer getAuthority();

	void setAuthority(Integer authority);

	Double getMoneyMax();

	void setMoneyMax(Double moneyMax);

	String getAlipayUsername();

	void setAlipayUsername(String alipayUsername);

	String getAlipayUseraccout();

	void setAlipayUseraccout(String alipayUseraccout);

	String getImgFace();

	void setImgFace(String imgFace);

	String getImgIdFront();

	void setImgIdFront(String imgIdFront);

	String getImgIdBack();

	void setImgIdBack(String imgIdBack);

	String getUserIdName();

	void setUserIdName(String userIdName);

	String getUserIdNumber();

	void setUserIdNumber(String userIdNumber);

	String getImgPortrait();

	void setImgPortrait(String imgPortrait);

	String getNickname();

	void setNickname(String nickname);

	Integer getCategory();

	void setCategory(Integer category);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

	public List<IAppMoneyDetail> getAppMoneyDetails() ;

	public void setAppMoneyDetails(List<IAppMoneyDetail> appMoneyDetails);
}
