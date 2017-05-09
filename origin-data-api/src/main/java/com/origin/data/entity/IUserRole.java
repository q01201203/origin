package com.origin.data.entity;

import java.io.Serializable;

public interface IUserRole extends Serializable{
	Integer getUserId();
	void setUserId(Integer userId);
	Integer getRoleId();
	void setRoleId(Integer roleId);
}
