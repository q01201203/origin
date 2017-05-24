package com.origin.data.mybatis.dao;

import java.io.Serializable;
import com.origin.data.dao.ITestDao;

public interface TestDao<T, PK extends Serializable> extends ITestDao<T, PK> {
}
