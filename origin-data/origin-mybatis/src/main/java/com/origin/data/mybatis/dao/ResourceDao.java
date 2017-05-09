package com.origin.data.mybatis.dao;

import java.io.Serializable;

import com.origin.data.dao.IResourceDao;

public interface ResourceDao<T,PK extends Serializable> extends IResourceDao<T, PK> {
}
