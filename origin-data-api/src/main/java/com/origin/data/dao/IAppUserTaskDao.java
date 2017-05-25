package com.origin.data.dao;

import com.origin.data.entity.IAppUserTask;

import java.io.Serializable;
import java.util.List;

public interface IAppUserTaskDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    List<IAppUserTask> findTaskUserByTaskId(Integer id);
}