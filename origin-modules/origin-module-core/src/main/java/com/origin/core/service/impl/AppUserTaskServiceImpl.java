package com.origin.core.service.impl;


import com.origin.core.service.AppUserTaskService;
import com.origin.data.dao.IAppUserTaskDao;
import com.origin.data.entity.IAppUserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public IAppUserTask findFirst(IAppUserTask appUserTask) {
        return appUserTaskDao.findFirst(appUserTask);
    }

    @Override
    public List<IAppUserTask> find(IAppUserTask appUserTask) {
        return appUserTaskDao.find(appUserTask);
    }
    @Override
    public List<IAppUserTask> findTaskUserInfo(IAppUserTask appUserTask) {
        return appUserTaskDao.findTaskUserInfo(appUserTask);
    }

    @Override
    public void updateTaskSuccess(IAppUserTask appUserTask) {
        appUserTaskDao.updateTaskSuccess(appUserTask);
    }
}
