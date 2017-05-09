package com.origin.core.dto;

import java.util.List;

import com.origin.data.mybatis.entity.Resource;
import com.origin.data.entity.BasePermission;
import com.origin.data.entity.IResource;

public class ResourceDTO extends BasePermission implements IResource{
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

}
