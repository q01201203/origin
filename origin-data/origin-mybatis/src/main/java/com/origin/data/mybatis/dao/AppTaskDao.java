package com.origin.data.mybatis.dao;

import java.io.Serializable;
import com.origin.data.dao.IAppTaskDao;

public interface AppTaskDao<T, PK extends Serializable> extends IAppTaskDao<T, PK> {
}
