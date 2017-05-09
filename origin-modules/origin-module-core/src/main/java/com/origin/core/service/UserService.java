package com.origin.core.service;

import java.util.List;

import com.origin.core.dto.UserDTO;
import com.origin.data.entity.IUser;

/**
 * Created by hjjmac on 17/4/4.
 */
public interface UserService {
	
	void save(IUser user);
	void delete(Integer id);
	void update(IUser user);
	void updateStatus(Integer[] ids,Integer status);
	/**
	 * 通过id查询用户
	 * @param id 用户id
	 * @return
	 */
	IUser findById(Integer id);
	/**
	 * 通过用户名查找用户
	 * @param userName 用户名
	 * @return
	 */
	IUser findUserByName(String userName);
	/**
	 * 通过参数表查询用户
	 * @param params 参数实体
	 * @return
	 */
    List<IUser> findUsers(UserDTO params);
}
