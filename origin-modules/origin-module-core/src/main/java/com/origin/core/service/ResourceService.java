package com.origin.core.service;

import java.util.List;
import java.util.Map;

import com.origin.core.dto.ResourceDTO;
import com.origin.data.entity.IResource;

/**
 */
public interface ResourceService {
	
	void save(IResource resource);
	void delete(Integer id);
	void updateDeleteFlag(Integer[] ids,String deleteFlag);
	void update(IResource resource);
	/**
	 * 通过id查找资源
	 * @param id 资源id
	 * @return
	 */
	IResource findById(Integer id);
	/**
	 * 查询所有启用的资源
	 * @return
	 */
    List<IResource>  findAllMenu();
    List<Map<String,Object>> findResourceMap();
    List<IResource> getRootResourceList();
    /**
     * 查询id与资源实体映射
     * @param userId 用户id
     * @return
     */
    Map<Integer, Object> findResourceMap(Integer userId);
    /**
     * 查询指定资源的子集资源
     * @param parentId 父级资源id
     * @return
     */
    List<IResource> findChildren(Integer parentId);
    /**
     * 通过参数映射查询资源列表
     * @param params 参数映射
     * @return
     */
    List<IResource> findByParams(ResourceDTO params);
}
