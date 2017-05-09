package com.origin.core.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.dto.UserDTO;
import com.origin.core.service.UserService;
import com.origin.data.dao.IUserDao;
import com.origin.data.entity.IUser;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private IUserDao<IUser,Integer> userDao;

	public IUser findUserByName(String userName){
		IUser user = new UserDTO();
		user.setUsername(userName);
		return userDao.findFirst(user);
	}
	public List<IUser> findUsers(UserDTO params){
		return userDao.find(params);
	}
	@Override
	public IUser findById(Integer id) {
		return userDao.findByPK(id);
	}
	@Override
	public void save(IUser user) {
		user.prePersist();
		userDao.save(user);
	}
	@Override
	public void delete(Integer id) {
		userDao.delete(id);
	}
	@Override
	public void update(IUser user) {
		userDao.update(user);
	}
	@Override
	public void updateStatus(Integer[] ids, Integer status) {
		List<IUser> updats = new ArrayList<IUser>();
		for(Integer id :ids){
			IUser u = new UserDTO();
			u.setId(id);
			u.setStatus(status);
			updats.add(u);
		}
		userDao.updateBatch(updats);
	}
}
