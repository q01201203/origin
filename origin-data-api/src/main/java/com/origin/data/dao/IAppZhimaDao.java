package com.origin.data.dao;

import java.io.Serializable;
import java.util.List;

public interface IAppZhimaDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    public List<T> findZhimaInfoByUid(PK pk);
}