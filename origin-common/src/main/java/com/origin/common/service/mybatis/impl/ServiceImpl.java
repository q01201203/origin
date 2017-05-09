package com.origin.common.service.mybatis.impl;


import com.origin.common.exception.DaoException;
import com.origin.common.model.mybatis.Dao;
import com.origin.common.model.mybatis.Pagination;
import com.origin.common.model.mybatis.PersistentObject;
import com.origin.common.service.mybatis.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service基类，实现了数据的CRUD
 * 
 * @param <DAO>
 * @param <T>
 * @param <ID>
 * @author Joe
 */
public abstract class ServiceImpl<DAO extends Dao<T, ID>, T extends PersistentObject, ID extends Serializable> implements
		Service<T, ID> {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceImpl.class);
	
	/**
	 * 数据访问对象，提供子类使用
	 */
	protected DAO dao;
	
	public abstract void setDao(DAO dao);

	/**
	 * 如果实体未持久化过则新建实体，否则更新实体
	 * 
	 * @param
	 *
	 * 
	 */
	public void save(T t) {
		if (t.getId() == null) {
			dao.insert(t);
		}
		else {
			dao.update(t);
		}
	}

	/**
	 * 如果实体未持久化过则新建实体，否则更新实体
	 * 
	 * @param
	 *            <T> ts
	 */
	public void save(Collection<T> ts) {
		for (T t : ts) {
			save(t);
		}
	}

	/**
	 * 更新实体
	 * 
	 * @param
	 *
	 */
	public void update(T t) {
		verifyRows(dao.update(t), 1, "数据库更新失败");
	}

	/**
	 * 更新实体
	 * 
	 * @param
	 *            <T> ts
	 */
	public void update(Collection<T> ts) {
		for (T t : ts) {
			update(t);
		}
	}

	/**
	 * 删除实体
	 * 
	 * @param
	 *
	 */
	@SuppressWarnings("unchecked")
	public void delete(T t) {
		verifyRows(dao.deleteById((ID) t.getId()), 1, "数据库删除失败");
	}

	/**
	 * 删除实体
	 * 
	 * @param
	 *            <T> ts
	 */
	public void delete(Collection<T> ts) {
		for (T t : ts) {
			delete(t);
		}
	}

	/**
	 * 通过主键加载实体
	 * 
	 * @param
	 *
	 * @return
	 */
	public T get(ID pk) {
		return dao.get(pk);
	}

	/**
	 * 通过主键加载实体
	 * 
	 * @param
	 *            <PK> pks
	 * @return List<T>
	 */
	public List<T> get(Collection<ID> pks) {
		List<T> list = new ArrayList<T>(pks.size());
		for (ID pk : pks) {
			list.add(get(pk));
		}
		return list;
	}

	/**
	 * 通过主键删除实体
	 * 
	 * @param
	 *
	 * @return T
	 */
	public void deleteById(ID id) {
		verifyRows(dao.deleteById(id), 1, "数据库删除失败");
	}

	/**
	 * 通过主键删除实体
	 * 
	 * @param
	 *            <PK> pks
	 * @return List<T>
	 */
	public void deleteById(List<ID> idList) {
		verifyRows(dao.deleteById(idList), idList.size(), "数据库删除失败");
	}
	
	/**
	 * 查所有分页
	 * 
	 * @param
	 * @return
	 */
	public Pagination<T> findByAllPagination(Pagination<T> p){
		dao.findByAll(p);
		return p;
	}
	
	/**
	 * 为高并发环境出现的更新和删除操作，验证更新数据库记录条数
	 * 
	 * @param updateRows
	 * @param rows
	 * @param message
	 */
	protected void verifyRows(int updateRows, int rows, String message) {
		if (updateRows != rows) {
			DaoException e = new DaoException(message);
			LOGGER.error("need update is {}, but real update rows is {}.", rows, updateRows, e);
			throw e;
		}
	}
}
