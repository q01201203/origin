package com.origin.data.mybatis.entity;

import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserTask;

/**
 * 
 */
public class AppUserTask implements IAppUserTask {

	private static final long serialVersionUID = 9257508442645321L;
	
	/**  */
	private Integer id;//;
	/**  */
	private Integer uid;//;
	/**  */
	private Integer tid;//;
	/**  */
	private Integer mid;//;
	//add lic 170525
	private IAppUser appUser;

	//add lic 170526
	private IAppTask appTask;

	//add lic 170601
	private IAppMoneyDetail appMoneyDetail;

	@Override
	public IAppUser getAppUser() {
		return appUser;
	}

	@Override
	public void setAppUser(IAppUser appUser) {
		this.appUser = appUser;
	}

	public IAppTask getAppTask() {
		return appTask;
	}

	public void setAppTask(IAppTask appTask) {
		this.appTask = appTask;
	}

	public IAppMoneyDetail getAppMoneyDetail() {
		return appMoneyDetail;
	}

	public void setAppMoneyDetail(IAppMoneyDetail appMoneyDetail) {
		this.appMoneyDetail = appMoneyDetail;
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
	
	public Integer getMid() {
		return this.mid;
	}
	
	public void setMid(Integer mid) {
		this.mid = mid;
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
		+",mid="+this.getMid()
		+"]";
	}
}
