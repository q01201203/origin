package com.origin.data.entity;

import java.io.Serializable;
import java.util.List;

public interface IUser extends Serializable,IBasePermission{
	public final static Integer STATUS_YES = 1; //可用
	
	public final static Integer STATUS_NO = 0; //不可用
	
	public final static Integer USER_TYPE_INS = 1; //机构用户
	void setId(Integer id);
	Integer getId();
	String getUsername();

	void setUsername(String username);

	String getPassword();

	void setPassword(String password);

	String getRealName();

	void setRealName(String realName);

	String getEmail();

	void setEmail(String email);

	String getMobile();

	void setMobile(String mobile);

	Integer getStatus();

	void setStatus(Integer status);

	Integer getType();

	void setType(Integer type);

	String getCurrentSkin();

	void setCurrentSkin(String currentSkin);
	
	List<IRole> getRoles();
	
	void setRoles(List<IRole> roles);
}