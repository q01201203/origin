package com.origin.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.UserRoleService;
import com.origin.data.dao.IUserRoleDao;
import com.origin.data.entity.IUserRole;
@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private IUserRoleDao<IUserRole,Integer> userRoleDao;
	@Override
	public void save(IUserRole userRole) {
		userRoleDao.save(userRole);
	}

	@Override
	public void saveBatch(List<IUserRole> userRoles) {
		userRoleDao.saveBatch(userRoles);
	}

	@Override
	public void deleteBatch(List<IUserRole> userRole) {
		userRoleDao.deleteBatch(userRole);
	}

	@Override
	public void delete(IUserRole userRole) {
		userRoleDao.deleteEntity(userRole);
	}

}
