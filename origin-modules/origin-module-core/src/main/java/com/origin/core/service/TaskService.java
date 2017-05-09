package com.origin.core.service;

import com.origin.data.entity.ITask;
import java.util.List;

public interface TaskService {

    void save(ITask task);
    void delete(Integer id);
    void update(ITask task);
    ITask findById(Integer id);
    List<ITask> find(ITask task);
}