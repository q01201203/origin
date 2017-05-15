package com.origin.core.dto;

import java.util.Date;

import com.origin.data.entity.ITask;

/**
* 
*/
public class TaskDTO implements ITask {


    /**  */
private Integer id;//;
    /**  */
private String name;//;
    /**  */
private Integer type;//;
    /**  */
private Integer state;//;
    /**  */
private Date createDate;//;
public TaskDTO(){
}

public TaskDTO(Integer id){
	this.id = id;
}


public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return this.name;
}

public void setName(String name) {
this.name = name;
}

public Integer getType() {
return this.type;
}

public void setType(Integer type) {
this.type = type;
}

public Integer getState() {
return this.state;
}

public void setState(Integer state) {
this.state = state;
}

public Date getCreateDate() {
return this.createDate;
}

public void setCreateDate(Date createDate) {
this.createDate = createDate;
}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof ITask) {
			ITask baseEntity = (ITask) object;
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
		+",name="+this.getName()
		+",type="+this.getType()
		+",state="+this.getState()
		+",createDate="+this.getCreateDate()
		+"]";
	}
}
