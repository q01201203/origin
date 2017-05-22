package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppFeedbackService;
import com.origin.data.dao.IAppFeedbackDao;
import com.origin.data.entity.IAppFeedback;

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
appFeedbackDao.update(appFeedback);
}

@Override
public IAppFeedback findById(Integer id) {
return appFeedbackDao.findByPK(id);
}

@Override
public List<IAppFeedback> find(IAppFeedback appFeedback) {
    return appFeedbackDao.find(appFeedback);
    }
}
