package com.origin.core.dto;

import java.util.List;

import com.origin.data.entity.BasePermission;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;

public class RoleDTO extends BasePermission implements IRole{
	private Integer id;
	private Integer no;
	
	private String name;
	
	private String description;
	
	private Integer status;

	private String code;						
	
	private List<IResource> resources;			//拥有资源 
	
	private String roleName;					//shiro中name

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<IResource> getResources() {
		return resources;
	}

	public void setResources(List<IResource> resources) {
		this.resources = resources;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
