package com.origin.data.entity;

import java.util.Date;
import java.io.Serializable;

/**
* 
*/
public interface IAppTask extends Serializable {

	public final static Integer STATUS_YES = 1; //可用

	public final static Integer STATUS_NO = 0; //不可用




	Integer getId();

	void setId(Integer id);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getTaskName();

	void setTaskName(String taskName);

	Integer getTaskNumber();

	void setTaskNumber(Integer taskNumber);

	Integer getTaskType();

	void setTaskType(Integer taskType);

	Double getTaskMoney();

	void setTaskMoney(Double taskMoney);

	String getTaskImg();

	void setTaskImg(String taskImg);

	Integer getTaskHot();

	void setTaskHot(Integer taskHot);

	Date getTaskStartTime();

	void setTaskStartTime(Date taskStartTime);

	Date getTaskEndTime();

	void setTaskEndTime(Date taskEndTime);

	String getTaskSimpleStep();

	void setTaskSimpleStep(String taskSimpleStep);

	String getTaskDetailedStep();

	void setTaskDetailedStep(String taskDetailedStep);

	String getTaskLink();

	void setTaskLink(String taskLink);

	Integer getDeleteFlag();

	void setDeleteFlag(Integer deleteFlag);

}
