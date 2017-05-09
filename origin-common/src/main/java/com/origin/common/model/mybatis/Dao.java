package com.origin.common.model.mybatis;


import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Dao接口
 * 
 * @author Joe
 */
public interface Dao<T, ID extends Serializable> {

	/**
	 * 如果实体未持久化过则新建实体，否则更新实体
	 * 
	 * @param
	 *            t
	 */
	public int insert(T t);

	/**
	 * 更新实体
	 * 
	 * @param
	 *
	 */
	public int update(T t);

	/**
	 * 删除实体
	 * 
	 * @param
	 *
	 */
	public int deleteById(ID id);
	
	/**
	 * 删除实体
	 * 
	 * @param
	 *
	 */
	public int deleteById(Collection<ID> idList);

	/**
	 * 通过主键加载实体
	 * 
	 * @param
	 *
	 * @return
	 */
	public T get(ID pk);
	
	/**
	 * 查所有分页
	 * 
	 * @param
	 * @return
	 */
	public List<T> findByAll(Pagination<T> p);
}
