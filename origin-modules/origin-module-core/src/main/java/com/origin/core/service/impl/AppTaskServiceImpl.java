package com.origin.core.service.impl;


import com.origin.core.service.AppTaskService;
import com.origin.data.dao.IAppTaskDao;
import com.origin.data.entity.IAppTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppTaskServiceImpl  implements AppTaskService {

    @Autowired
    private IAppTaskDao<IAppTask,Integer> appTaskDao;

    @Override
    public void save(IAppTask appTask) {
        appTaskDao.save(appTask);
    }

    @Override
    public void delete(Integer id) {
        appTaskDao.delete(id);
    }

    @Override
    public void update(IAppTask appTask) {
        appTask.setUpdateDate(new Date());
        appTaskDao.update(appTask);
    }

    @Override
    public IAppTask findById(Integer id) {
        return appTaskDao.findByPK(id);
    }

    @Override
    public List<IAppTask> find(IAppTask appTask) {
        return appTaskDao.find(appTask);
    }

    @Override
    public List<IAppTask> findByName(IAppTask appTask) {
        return appTaskDao.findByName(appTask);
    }
}
