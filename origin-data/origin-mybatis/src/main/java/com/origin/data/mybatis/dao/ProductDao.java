package com.origin.data.mybatis.dao;

import java.io.Serializable;
import com.origin.data.dao.IProductDao;

public interface ProductDao<T, PK extends Serializable> extends IProductDao<T, PK> {
}
