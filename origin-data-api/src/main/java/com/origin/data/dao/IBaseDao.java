package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T, PK extends Serializable> {
	
	/**
	 * 通过主键查询实体对象
	 * @param pk 对象实体的主键标识
	 * @return 该主键对应的对象实体
	 */
	public T findByPK(PK pk);
	/**
	 * 通过实体参数查询一个对象实体对象
	 * @param t 实体参数,必须确保查询记录返回一条
	 * @return 匹配的对象实体列表
	 */
	public T findFirst(T t);
	/**
	 * 通过实体参数查询对象实体
	 * @param param 实体参数
	 * @return 匹配的对象实体列表
	 */
	public List<T> find(T t);
	/**
	 * 保存对象实体
	 * @param t 需要保存的对象实体
	 */
	public void save(T t);
	/**
	 * 批量保存对象实体
	 * @param ts 需要保存的对象实体列表
	 */
	public void saveBatch(List<T> ts);
	/**
	 * 更新对象实体
	 * @param t 需要更新的对象实体
	 */
	public void update(T t);
	/**
	 * 批量更新实体数据
	 * @param ts 需要批量更新的实体列表
	 */
	public void updateBatch(List<T> ts);
	/**
	 * 通过主键列表批量更新某个字段数据
	 * @param pks 需要更新的主键列表
	 * @param value 需要更新的值
	 */
	public void updatePks(List<Integer> pks,Object param);
	/**
	 * 通过主键或唯一约束删除对象实体
	 * @param pk 对象实体的主键标识(也可以是有唯一约束的字段)
	 */
	public void delete(PK pk);
	public void deleteBatch(List<T> ts);
	public void deletes(PK[] pk);
	public void deleteEntity(T t);
	
}
