package com.origin.common.model.mybatis;

import java.io.Serializable;

/**
 * 持久化基类
 * 
 * @author Joe
 */
public class PersistentObject implements Serializable{

	private static final long serialVersionUID = 1472145516693079043L;

	//逻辑删除标识位—已删除状态
	public static final String DELETE_FLAG_DELETED = "1";

	//逻辑删除标识位—未删除状态
	public static final String DELETE_FLAG_NORMAL = "0";

	//审核状态标识位—未审核
	public static final String AUDIT_FLAG_NOT = "0";

	//审核状态标识位—审核通过
	public static final String AUDIT_FLAG_PASS = "1";

	//审核状态标识位—审核通不过
	public static final String AUDIT_FLAG_FAIL = "2";
	
	/** 主键ID */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 覆盖原equals方法，只要对象类型相同并且主键相同，那么返回true，表示两个对象相等
	 * 
	 * @param
	 *            o
	 * @return boolean
	 */
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || !(o instanceof PersistentObject)) {
			return false;
		}
		PersistentObject other = (PersistentObject) o;
		return id.equals(other.getId());
	}

	/**
	 * 覆盖原hashCode方法，用主键的hashcode代替原来对象的hashcode，可以简化持久化对象的比较
	 * 
	 * @return int
	 */
	public int hashCode() {
		if (id == null) {
			return Integer.valueOf(0);
		}
		return id.hashCode();
	}

	/**
	 * 覆盖原toString方法，打印更详细信息
	 * 
	 * @return String
	 */
	public String toString() {
		return this.getClass().getName() + "[id=" + id + "]";
	}
}