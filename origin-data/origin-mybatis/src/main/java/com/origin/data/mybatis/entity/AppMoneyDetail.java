package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUser;

import java.util.Date;

/**
 * 
 */
public class AppMoneyDetail implements IAppMoneyDetail {

	private static final long serialVersionUID = 5329989487735057L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Date createDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Date updateDate;// = CURRENT_TIMESTAMP;
	/**  */
	private Double moneyAsk;//;
	/**  */
	private Double moneyActual;//;
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
	private String taskName;//;
	/**  */
	private String taskUsername;//;
	/**  */
	private String taskMobile;//;
	/**  */
	private Integer pid;//;
	/**  */
	private Double delayMoney;//;
	/**  */
	private Date repayDeadline;//;
	/**  */
	private Integer repayStatus;//;
	/**  */
	private String scheduleId;//;
	/**  */
	private String extensionOne;//;
	/**  */
	private String extensionTwo;//;
	/**  */
	private Integer uid;//;
	/**  */
	private Integer deleteFlag;// = Integer.valueOf(0);

	//add lic 170525
	private IAppUser appUser;

	//add lic 170526
	private IAppTask appTask;

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
	
	public Double getMoneyAsk() {
		return this.moneyAsk;
	}
	
	public void setMoneyAsk(Double moneyAsk) {
		this.moneyAsk = moneyAsk;
	}
	
	public Double getMoneyActual() {
		return this.moneyActual;
	}
	
	public void setMoneyActual(Double moneyActual) {
		this.moneyActual = moneyActual;
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
	
	public String getTaskName() {
		return this.taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskUsername() {
		return this.taskUsername;
	}
	
	public void setTaskUsername(String taskUsername) {
		this.taskUsername = taskUsername;
	}
	
	public String getTaskMobile() {
		return this.taskMobile;
	}
	
	public void setTaskMobile(String taskMobile) {
		this.taskMobile = taskMobile;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	
	public Double getDelayMoney() {
		return this.delayMoney;
	}
	
	public void setDelayMoney(Double delayMoney) {
		this.delayMoney = delayMoney;
	}
	
	public Date getRepayDeadline() {
		return this.repayDeadline;
	}
	
	public void setRepayDeadline(Date repayDeadline) {
		this.repayDeadline = repayDeadline;
	}
	
	public Integer getRepayStatus() {
		return this.repayStatus;
	}
	
	public void setRepayStatus(Integer repayStatus) {
		this.repayStatus = repayStatus;
	}
	
	public String getScheduleId() {
		return this.scheduleId;
	}
	
	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}
	
	public String getExtensionOne() {
		return this.extensionOne;
	}
	
	public void setExtensionOne(String extensionOne) {
		this.extensionOne = extensionOne;
	}
	
	public String getExtensionTwo() {
		return this.extensionTwo;
	}
	
	public void setExtensionTwo(String extensionTwo) {
		this.extensionTwo = extensionTwo;
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

	@Override
	public IAppTask getAppTask() {
		return appTask;
	}

	@Override
	public void setAppTask(IAppTask appTask) {
		this.appTask = appTask;
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
		+",moneyAsk="+this.getMoneyAsk()
		+",moneyActual="+this.getMoneyActual()
		+",type="+this.getType()
		+",status="+this.getStatus()
		+",repayWay="+this.getRepayWay()
		+",repayTime="+this.getRepayTime()
		+",repayTimeType="+this.getRepayTimeType()
		+",taskName="+this.getTaskName()
		+",taskUsername="+this.getTaskUsername()
		+",taskMobile="+this.getTaskMobile()
		+",pid="+this.getPid()
		+",delayMoney="+this.getDelayMoney()
		+",repayDeadline="+this.getRepayDeadline()
		+",repayStatus="+this.getRepayStatus()
		+",scheduleId="+this.getScheduleId()
		+",extensionOne="+this.getExtensionOne()
		+",extensionTwo="+this.getExtensionTwo()
		+",uid="+this.getUid()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
