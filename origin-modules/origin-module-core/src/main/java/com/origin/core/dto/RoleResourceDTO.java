package com.origin.core.dto;

import com.origin.data.entity.IRoleResource;

public class RoleResourceDTO implements IRoleResource{
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
