package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IAppGuideDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    public List<T> findOrderBy(T t);
}