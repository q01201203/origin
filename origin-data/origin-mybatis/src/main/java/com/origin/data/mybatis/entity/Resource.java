package com.origin.data.mybatis.entity;

import java.util.List;

import com.origin.data.entity.BasePermission;
import com.origin.data.entity.IResource;

/**
 * 资源
 *
 */

public class Resource  extends BasePermission implements IResource {

	private static final long serialVersionUID = -8560521657225182602L;
	
	private Integer id;
	private String name;
	
	private IResource parent;
	private Integer parentId;
	
	private String type;
	
	private String url;
	
	private String icon;				//菜单按钮
	
	private Integer orderNo;			//排序

	private List<IResource> children;
	
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

	public IResource getParent() {
		return parent;
	}
	public void setParent(IResource parent) {
		this.parent = parent;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public List<IResource> getChildren() {
		return children;
	}

	public void setChildren(List<IResource> children) {
		this.children = children;
	}

	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if (object instanceof User) {
			Resource baseEntity = (Resource) object;
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

	@Override
	public String toString() {
		return "Resource [id=" + id + ", name=" + name + ", parent=" + parent + ", parentId=" + parentId + ", type="
				+ type + ", url=" + url + ", icon=" + icon + ", orderNo=" + orderNo + ", children=" + children + "]";
	}
	

}
