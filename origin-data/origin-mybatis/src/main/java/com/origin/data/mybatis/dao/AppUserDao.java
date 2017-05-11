package com.origin.data.mybatis.dao;

import java.io.Serializable;
import com.origin.data.dao.IAppUserDao;

public interface AppUserDao<T, PK extends Serializable> extends IAppUserDao<T, PK> {
}
