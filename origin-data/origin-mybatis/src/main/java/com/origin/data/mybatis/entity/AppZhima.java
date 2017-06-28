package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppZhima;
import java.util.Date;

/**
 * 
 */
public class AppZhima implements IAppZhima {

	private static final long serialVersionUID = 23554094670216155L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Integer type;//;
	/**  */
	private String bizNo;//;
	/**  */
	private String score;//;
	/**  */
	private String errorMessage;//;
	/**  */
	private String isMatched;//;
	/**  */
	private String verifyCode;//;
	/**  */
	private String hit;//;
	/**  */
	private String riskCode;//;
	/**  */
	private Integer uid;//;
	/**  */
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
	
	public Integer getType() {
		return this.type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getBizNo() {
		return this.bizNo;
	}
	
	public void setBizNo(String bizNo) {
		this.bizNo = bizNo;
	}
	
	public String getScore() {
		return this.score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getErrorMessage() {
		return this.errorMessage;
	}
	
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String getIsMatched() {
		return this.isMatched;
	}
	
	public void setIsMatched(String isMatched) {
		this.isMatched = isMatched;
	}
	
	public String getVerifyCode() {
		return this.verifyCode;
	}
	
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	public String getHit() {
		return this.hit;
	}
	
	public void setHit(String hit) {
		this.hit = hit;
	}
	
	public String getRiskCode() {
		return this.riskCode;
	}
	
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
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
		if (object instanceof IAppZhima) {
			IAppZhima baseEntity = (IAppZhima) object;
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
		+",type="+this.getType()
		+",bizNo="+this.getBizNo()
		+",score="+this.getScore()
		+",errorMessage="+this.getErrorMessage()
		+",isMatched="+this.getIsMatched()
		+",verifyCode="+this.getVerifyCode()
		+",hit="+this.getHit()
		+",riskCode="+this.getRiskCode()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
