package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.IAppTask;

/**
* 
*/
public class AppTaskDTO implements IAppTask {


    /**  */
private Integer id;//;
    /**  */
private Date createDate;// = CURRENT_TIMESTAMP;
    /**  */
private Date updateDate;// = CURRENT_TIMESTAMP;
    /**  */
private String taskName;//;
    /**  */
private Integer taskNumber;//;
    /**  */
private Integer taskType;//;
    /**  */
private Integer taskMoney;//;
    /**  */
private String taskImg;//;
    /**  */
private Integer taskHot;// = Integer.valueOf(0);
    /**  */
private Integer deleteFlag;// = Integer.valueOf(0);
public AppTaskDTO(){
}

public AppTaskDTO(Integer id){
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

public String getTaskName() {
return this.taskName;
}

public void setTaskName(String taskName) {
this.taskName = taskName;
}

public Integer getTaskNumber() {
return this.taskNumber;
}

public void setTaskNumber(Integer taskNumber) {
this.taskNumber = taskNumber;
}

public Integer getTaskType() {
return this.taskType;
}

public void setTaskType(Integer taskType) {
this.taskType = taskType;
}

public Integer getTaskMoney() {
return this.taskMoney;
}

public void setTaskMoney(Integer taskMoney) {
this.taskMoney = taskMoney;
}

public String getTaskImg() {
return this.taskImg;
}

public void setTaskImg(String taskImg) {
this.taskImg = taskImg;
}

public Integer getTaskHot() {
return this.taskHot;
}

public void setTaskHot(Integer taskHot) {
this.taskHot = taskHot;
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
		if (object instanceof IAppTask) {
			IAppTask baseEntity = (IAppTask) object;
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
		+",taskName="+this.getTaskName()
		+",taskNumber="+this.getTaskNumber()
		+",taskType="+this.getTaskType()
		+",taskMoney="+this.getTaskMoney()
		+",taskImg="+this.getTaskImg()
		+",taskHot="+this.getTaskHot()
		+",deleteFlag="+this.getDeleteFlag()
		+"]";
	}
}
