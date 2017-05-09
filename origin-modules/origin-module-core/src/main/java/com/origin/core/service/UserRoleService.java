package com.origin.core.service;

import java.util.List;

import com.origin.data.entity.IUserRole;

public interface UserRoleService {
	void save(IUserRole userRole);
	void saveBatch(List<IUserRole> userRoles);
	void deleteBatch(List<IUserRole> userRoles);
	void delete(IUserRole userRole);
}
