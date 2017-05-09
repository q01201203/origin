package com.origin.core.service;

import java.util.List;

import com.origin.data.entity.IRoleResource;

public interface RoleResourceService {
	void save(IRoleResource roleResource);
	void saveBatch(List<IRoleResource> roleResources);
	void deleteBatch(List<IRoleResource> roleResources);
	void delete(IRoleResource roleResource);
}
