package com.origin.data.mybatis.dao;

import java.io.Serializable;
import com.origin.data.dao.ITaskDao;

public interface TaskDao<T, PK extends Serializable> extends ITaskDao<T, PK> {
}
