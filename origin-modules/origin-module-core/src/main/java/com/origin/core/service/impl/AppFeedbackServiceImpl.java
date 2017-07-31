package com.origin.core.service.impl;


import com.origin.core.service.AppFeedbackService;
import com.origin.data.dao.IAppFeedbackDao;
import com.origin.data.entity.IAppFeedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppFeedbackServiceImpl  implements AppFeedbackService {

    @Autowired
    private IAppFeedbackDao<IAppFeedback,Integer> appFeedbackDao;

    @Override
    public void save(IAppFeedback appFeedback) {
        appFeedbackDao.save(appFeedback);
    }

    @Override
    public void delete(Integer id) {
        appFeedbackDao.delete(id);
    }

    @Override
    public void update(IAppFeedback appFeedback) {
		appFeedback.setUpdateDate(new Date());
        appFeedbackDao.update(appFeedback);
    }

    @Override
    public IAppFeedback findById(Integer id) {
        return appFeedbackDao.findByPK(id);
    }

    @Override
    public List<IAppFeedback> find(IAppFeedback appFeedback) {
        appFeedback.setDeleteFlag(0);
        return appFeedbackDao.find(appFeedback);
    }
}
