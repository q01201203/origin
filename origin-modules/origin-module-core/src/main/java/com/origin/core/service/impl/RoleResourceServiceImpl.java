package com.origin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.RoleResourceService;
import com.origin.data.dao.IRoleResourceDao;
import com.origin.data.entity.IRoleResource;
@Service
public class RoleResourceServiceImpl implements RoleResourceService{
	@Autowired
	private IRoleResourceDao<IRoleResource,Integer> roleResourceDao;
	@Override
	public void save(IRoleResource roleResource) {
		roleResourceDao.save(roleResource);
	}

	@Override
	public void saveBatch(List<IRoleResource> roleResources) {
		roleResourceDao.saveBatch(roleResources);
	}

	@Override
	public void deleteBatch(List<IRoleResource> roleResources) {
		roleResourceDao.deleteBatch(roleResources);
	}

	@Override
	public void delete(IRoleResource roleResource) {
		roleResourceDao.deleteEntity(roleResource);
	}

}
