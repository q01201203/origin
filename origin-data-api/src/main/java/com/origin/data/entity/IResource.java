package com.origin.data.entity;

import java.io.Serializable;
import java.util.List;

public interface IResource extends Serializable,IBasePermission{

	//模块资源
	public final static String RESOURCE_TYPE_MODULE = "module";
	
	//页面资源
	public final static String RESOURCE_TYPE_PAGE = "page";
	
	//按钮资源
	public final static String RESOURCE_TYPE_BUTTON = "button";
	Integer getId();

	void setId(Integer id);

	String getName();

	void setName(String name);

	Integer getParentId();

	void setParentId(Integer parentId);
	IResource getParent();
	
	void setParent(IResource parent);

	String getType();

	void setType(String type);

	String getUrl();

	void setUrl(String url);

	String getIcon();

	void setIcon(String icon);

	Integer getOrderNo();

	void setOrderNo(Integer orderNo);
	
	List<IResource> getChildren();
	
	void setChildren(List<IResource> children);

}