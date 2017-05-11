package com.origin.data.mybatis.entity;


import com.origin.data.entity.IAppUser;

/**
 * 
 */
public class AppUser implements IAppUser {

	private static final long serialVersionUID = 75644540777195142L;
	
	/**  */
	private Integer id;//;
	/**  */
	private String mobile;//;
	/**  */
	private String pwd;//;
	
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getMobile() {
		return this.mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getPwd() {
		return this.pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof IAppUser) {
			IAppUser baseEntity = (IAppUser) object;
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
		+",mobile="+this.getMobile()
		+",pwd="+this.getPwd()
		+"]";
	}
}
