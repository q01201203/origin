package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

import com.origin.data.entity.IRole;

public interface IUserDao<T, PK extends Serializable> extends IBaseDao<T, PK>{
	/**
	 * 获取用户对应的角色列表
	 * @param pk 用户主键
	 * @return
	 */
	public List<IRole> findRoles(PK pk);
	/**
	 * 获取用户对应角色id列表
	 * @param pk 用户主键
	 * @return
	 */
	public List<Integer> findRoleIds(PK pk);
}
