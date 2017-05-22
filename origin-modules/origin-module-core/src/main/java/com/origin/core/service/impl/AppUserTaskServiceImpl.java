package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppUserTaskService;
import com.origin.data.dao.IAppUserTaskDao;
import com.origin.data.entity.IAppUserTask;

@Service
public class AppUserTaskServiceImpl  implements AppUserTaskService {

@Autowired
private IAppUserTaskDao<IAppUserTask,Integer> appUserTaskDao;

@Override
public void save(IAppUserTask appUserTask) {
appUserTaskDao.save(appUserTask);
}

@Override
public void delete(Integer id) {
appUserTaskDao.delete(id);
}

@Override
public void update(IAppUserTask appUserTask) {
appUserTaskDao.update(appUserTask);
}

@Override
public IAppUserTask findById(Integer id) {
return appUserTaskDao.findByPK(id);
}

@Override
public List<IAppUserTask> find(IAppUserTask appUserTask) {
    return appUserTaskDao.find(appUserTask);
    }
}
