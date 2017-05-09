package com.origin.core.service;

import java.util.List;

import com.origin.core.dto.RoleDTO;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;

/**
 */
public interface RoleService {
	void save(IRole role);
	void delete(Integer id);
	void update(IRole role);
	void updateDeleteFlag(Integer[] ids,String deleteFlag);
	/**
	 * 通过角色id查找角色
	 * @param id 角色id
	 * @return
	 */
	IRole findById(Integer id);
	/**
	 * 获取用户所有的角色
	 * @param userId 用户id
	 * @return
	 */
    List<IRole> findByUser(Integer userId);
    /**
     * 获取用户的所有角色id列表
     * @param userId 用户id
     * @return
     */
    List<Integer> getRoleIdsByUser(Integer userId);
    /**
     * 获取角色所有的资源
     * @param roleId 角色id
     * @return
     */
    List<IResource> getResources(Integer roleId);
    /**
     * 通过参数列表查询角色列表
     * @param params 参数列表
     * @return
     */
    List<IRole> findByParams(RoleDTO params);
}
