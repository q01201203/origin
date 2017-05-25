package com.origin.core.service;

import com.origin.data.entity.IAppTask;

import java.util.List;

public interface AppTaskService {

    void save(IAppTask appTask);
    void delete(Integer id);
    void update(IAppTask appTask);
    IAppTask findById(Integer id);
    IAppTask findFirst(IAppTask appTask);
    List<IAppTask> find(IAppTask appTask);
}