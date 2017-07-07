package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IAppTaskDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    public List<T> findByName(T t);
}