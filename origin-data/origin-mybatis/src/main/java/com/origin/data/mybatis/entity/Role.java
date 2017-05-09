package com.origin.data.mybatis.entity;



import java.util.List;

import com.origin.data.entity.BasePermission;
import com.origin.data.entity.IResource;
import com.origin.data.entity.IRole;

/**
 * 角色管理
 * @author Jianfang Xu
 *
 */
public class Role extends BasePermission implements IRole {
	
private static final long serialVersionUID = 8160747791143322423L;
	private Integer id;
	private Integer no;
	
	private String name;
	
	private String description;
	
	private Integer status;

	private String code;						
	
	private List<IResource> resources;			//拥有资源 
	
	private String roleName;					//shiro中name
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public Integer getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<IResource> getResources() {
		return resources;
	}

	public void setResources(List<IResource> resources) {
		this.resources = resources;
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof User) {
			Role baseEntity = (Role) object;
			if (this.getId() == null || baseEntity.getId() == null) {
				return false;
			} else {
				return (this.getId().equals(baseEntity.getId()));
			}
		}
		return false;
	}
	
	public int hashCode() {
		return id == null ? System.identityHashCode(this) : (this.getClass().getName() + this.getId()).hashCode();
	}
	public String toString() {
		return this.getClass().getName() + "[id=" + this.getId() + ",name="+this.getName()+"]";
	}
}
