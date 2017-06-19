package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IAppMessageDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    public List<T> findSystemMessage(T t);
    public void saveBatchSystemMessage(List<T> ts);
    public List<T> findOrderBy(T t);
}