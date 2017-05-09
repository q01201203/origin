package com.origin.data.mybatis.dao;

import java.io.Serializable;

import com.origin.data.dao.IUserDao;

/**
 * 管理员持久化接口
 */
public interface UserDao<T, PK extends Serializable> extends IUserDao<T, PK>{
	
}
