package com.origin.data.entity;

import java.io.Serializable;
import java.util.List;

public interface IRole extends Serializable,IBasePermission{

	Integer getId();

	void setId(Integer id);

	String getName();

	void setName(String name);

	Integer getStatus();

	void setDescription(String description);

	void setStatus(Integer status);

	Integer getNo();

	void setNo(Integer no);

	void setCode(String code);

	String getRoleName();

	void setRoleName(String roleName);
	
	List<IResource> getResources();
	void setResources(List<IResource> resources);

}