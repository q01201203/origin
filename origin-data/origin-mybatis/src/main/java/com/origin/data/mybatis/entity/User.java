package com.origin.data.mybatis.entity;
import java.util.List;

import com.origin.common.util.Md5Util;
import com.origin.data.entity.BasePermission;
import com.origin.data.entity.IRole;
import com.origin.data.entity.IUser;
/**
 * 系统用户
 * @author Jeff Xu
 *
 */
public class User extends BasePermission implements IUser{

	private static final long serialVersionUID = -8821121831372299051L;
	
	private Integer id;                         //主键
	private String username;					//用户名称
	
	private String password;					//密码
	
	private String realName;					//真实姓名
	
	private String email;						//邮箱
	
	private String mobile;						//电话
	
	private Integer status; 					//状态
	
	private Integer type;						//0 为管理员
	
	private String currentSkin;                 //当前皮肤
	
	private List<IRole> roles;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCurrentSkin() {
		return currentSkin;
	}

	public void setCurrentSkin(String currentSkin) {
		this.currentSkin = currentSkin;
	}
	
	public List<IRole> getRoles() {
		return roles;
	}

	public void setRoles(List<IRole> roles) {
		this.roles = roles;
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof User) {
			User baseEntity = (User) object;
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
		return this.getClass().getName() + "[id=" + this.getId() + ",username="+this.getUsername()+",realName="+this.getRealName()+"]";
	}
}