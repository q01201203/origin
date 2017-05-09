package com.origin.data.mybatis.entity;

import com.origin.data.entity.IRoleResource;

public class RoleResource implements IRoleResource{
	private Integer roleId;
	private Integer resourcesId;
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getResourcesId() {
		return resourcesId;
	}
	public void setResourcesId(Integer resourcesId) {
		this.resourcesId = resourcesId;
	}
	
}
