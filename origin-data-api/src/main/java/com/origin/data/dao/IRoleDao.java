package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

import com.origin.data.entity.IResource;

public interface IRoleDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
	/**
	 * 获取角色对应的资源列表
	 * @param pk 角色主键
	 * @return
	 */
	public List<IResource> findResources(PK pk);
}
