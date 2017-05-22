package com.origin.core.dto;


import com.origin.data.entity.IAppUserTask;

/**
* 
*/
public class AppUserTaskDTO implements IAppUserTask {


    /**  */
private Integer id;//;
    /**  */
private Integer uid;//;
    /**  */
private Integer tid;//;
    /**  */
private Integer status;//;
public AppUserTaskDTO(){
}

public AppUserTaskDTO(Integer id){
	this.id = id;
}


public Integer getId() {
return this.id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getUid() {
return this.uid;
}

public void setUid(Integer uid) {
this.uid = uid;
}

public Integer getTid() {
return this.tid;
}

public void setTid(Integer tid) {
this.tid = tid;
}

public Integer getStatus() {
return this.status;
}

public void setStatus(Integer status) {
this.status = status;
}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppUserTask) {
			IAppUserTask baseEntity = (IAppUserTask) object;
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
		+",uid="+this.getUid()
		+",tid="+this.getTid()
		+",status="+this.getStatus()
		+"]";
	}
}
