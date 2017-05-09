package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.TaskService;
import com.origin.data.dao.ITaskDao;
import com.origin.data.entity.ITask;

@Service
public class TaskServiceImpl  implements TaskService {

@Autowired
private ITaskDao<ITask,Integer> taskDao;

@Override
public void save(ITask task) {
taskDao.save(task);
}

@Override
public void delete(Integer id) {
taskDao.delete(id);
}

@Override
public void update(ITask task) {
taskDao.update(task);
}

@Override
public ITask findById(Integer id) {
return taskDao.findByPK(id);
}

@Override
public List<ITask> find(ITask task) {
    return taskDao.find(task);
    }
}
