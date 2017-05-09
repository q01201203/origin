package com.origin.core.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.dto.RoleDTO;
import com.origin.core.service.RoleService;
import com.origin.data.dao.IRoleDao;
import com.origin.data.dao.IUserDao;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;
import com.origin.data.entity.IUser;


@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private IRoleDao<IRole,Integer> roleDao;
	@Autowired
	private IUserDao<IUser,Integer> userDao;

	public List<IRole> findByUser(Integer userId){
		return userDao.findRoles(userId);
	}

	public List<Integer> getRoleIdsByUser(Integer userId){
		return userDao.findRoleIds(userId);
	}
	
	public List<IResource> getResources(Integer roleId){
		return roleDao.findResources(roleId);
	}

	public List<IRole> findByParams(RoleDTO params) {
		return roleDao.find(params);
	}

	public IRole findById(Integer id) {
		return roleDao.findByPK(id);
	}

	@Override
	public void save(IRole role) {
		roleDao.save(role);
	}

	@Override
	public void delete(Integer id) {
		roleDao.delete(id);
	}

	@Override
	public void update(IRole role) {
		roleDao.update(role);
	}

	@Override
	public void updateDeleteFlag(Integer[] ids, String deleteFlag) {
		List<IRole> ts = new ArrayList<IRole>();
		for(Integer id:ids){
			IRole r = new RoleDTO();
			r.setId(id);
			r.setDeleteFlag(deleteFlag);
			ts.add(r);
		}
		roleDao.updateBatch(ts);
	}
}
