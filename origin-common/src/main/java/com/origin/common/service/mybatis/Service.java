package com.origin.common.service.mybatis;



import com.origin.common.model.mybatis.Pagination;
import com.origin.common.model.mybatis.PersistentObject;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Service接口
 * 
 * @param
 * @param
 * @param
 * @author Joe
 */
public interface Service<T extends PersistentObject, ID extends Serializable> {

	/**
	 * 新建实体
	 * 
	 * @param
	 *
	 */
	public void save(T t);

	/**
	 * 新建实体
	 * 
	 * @param
	 *
	 */
	public void save(Collection<T> ts);

	/**
	 * 更新实体
	 * 
	 * @param
	 *
	 */
	public void update(T t);

	/**
	 * 更新实体
	 * 
	 * @param
	 *
	 */
	public void update(Collection<T> ts);

	/**
	 * 删除实体
	 * 
	 * @param
	 *
	 */
	public void delete(T t);

	/**
	 * 删除实体
	 * 
	 * @param
	 *
	 */
	public void delete(Collection<T> ts);

	/**
	 * 通过主键加载实体
	 * 
	 * @param
	 *
	 * @return
	 */
	public T get(ID pk);

	/**
	 * 通过主键加载实体
	 * 
	 * @param
	 *
	 * @return
	 */
	public List<T> get(Collection<ID> pks);

	/**
	 * 通过主键删除实体
	 * 
	 * @param
	 *
	 * @return
	 */
	public void deleteById(ID id);

	/**
	 * 通过主键删除实体
	 * 注：这里别把List改为Collection，会导致覆盖方法的List<ID>调用不到
	 * 
	 * @param
	 *
	 * @return
	 */
	public void deleteById(List<ID> idList);

	/**
	 * 查所有分页
	 * 
	 * @param
	 * @return
	 */
	public Pagination<T> findByAllPagination(Pagination<T> p);
}
