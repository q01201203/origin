package com.origin.data.mybatis.common;


import java.io.Serializable;

/**
 * 定义实体的基础公共属性，所有实体都会继承.
 * @author Jeff Xu
 * @since 2015-12-09
 */

public class BaseEntity implements Serializable{

	private static final long serialVersionUID = -4498233384948128317L;

	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	
}
