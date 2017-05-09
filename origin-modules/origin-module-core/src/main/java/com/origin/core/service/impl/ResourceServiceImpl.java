package com.origin.core.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.dto.ResourceDTO;
import com.origin.core.service.ResourceService;
import com.origin.data.dao.IResourceDao;
import com.origin.data.dao.IRoleDao;
import com.origin.data.dao.IUserDao;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;
import com.origin.data.entity.IUser;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private IResourceDao<IResource,Integer> resourceDao;

	@Autowired
	private IRoleDao<IRole,Integer> roleDao;

	@Autowired
	private IUserDao<IUser,Integer> userDao;

	public List<Map<String,Object>> findResourceMap(){
		List<IResource> rs = resourceDao.findEnabledResources();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(IResource r:rs){
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("id", r.getId());
			m.put("name", r.getName());
			m.put("parentId", r.getParentId());
			m.put("type", r.getType());
			m.put("url", r.getUrl());
			m.put("icon", r.getIcon());
			m.put("orderNo", r.getOrderNo());
			result.add(m);
		}
		return result;
	}
	public Map<Integer, Object> findResourceMap(Integer userId){
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		List<Integer> roleIds=userDao.findRoleIds(userId);//.getRoleIdsByUser(userId);
		for(Integer roleId:roleIds) {
			List<IResource> resources = roleDao.findResources(roleId);//roleResourceDao.findResourceIdsByRole(roleId);
			if (resources != null && !resources.isEmpty()) {
				for (IResource r : resources) {
					map.put(r.getId(), r.getName());
				}
			}
		}

		return map;
	}


	public List<IResource> findAllMenu(){
		List<IResource> resources = new ArrayList<IResource>();
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("deleteFlag", IResource.DELETE_FLAG_NORMAL);
		List<IResource> list = resourceDao.findEnabledResources();
		if(list != null && !list.isEmpty()){
			Map<Integer, IResource> map = new HashMap<Integer, IResource>();
			for(IResource r : list){
				map.put(r.getId(), r);
			}

			for(IResource r : list){
				if(r.getParentId() == null){
					resources.add(r);
				}else{
					IResource parentResource = map.get(r.getParentId());
					if(parentResource == null){
						resources.add(r);
					}else{
						if(parentResource.getChildren() == null){
							parentResource.setChildren(new ArrayList<IResource>());
						}
						parentResource.getChildren().add(r);
					}
				}
			}

		}

		return resources;
	}

	public List<IResource> findChildren(Integer parentId) {
		return resourceDao.findChildren(parentId);
	}


	@Override
	public IResource findById(Integer id) {
		return resourceDao.findByPK(id);
	}


	@Override
	public void save(IResource resource) {
		resourceDao.save(resource);
	}


	@Override
	public void delete(Integer id) {
		resourceDao.delete(id);
	}


	@Override
	public void update(IResource resource) {
		resourceDao.update(resource);
	}
	@Override
	public void updateDeleteFlag(Integer[] ids, String deleteFlag) {
		List<IResource> updates = new ArrayList<IResource>();
		for(Integer id:ids){
			IResource r = new ResourceDTO();
			r.setId(id);
			r.setDeleteFlag(deleteFlag);
			updates.add(r);
		}
		resourceDao.updateBatch(updates);
	}
	@Override
	public List<IResource> getRootResourceList() {
		IResource params = new ResourceDTO();
		params.setType(IResource.RESOURCE_TYPE_MODULE);
		List<IResource> list = resourceDao.find(params);
		return list;
	}
	@Override
	public List<IResource> findByParams(ResourceDTO params) {
		List<IResource> list = resourceDao.find(params);
		if(list != null && !list.isEmpty()){
			Map<Integer, IResource> map = new HashMap<Integer, IResource>();
			for(IResource r : list){
				map.put(r.getId(), r);
			}
			for(IResource r:list){
				if(r.getParentId() != null){
					IResource parent = map.get(r.getParentId());
					r.setParent(parent);
				}
			}
		}
		return list;
	}

}
