package com.origin.core.service.impl;


import com.origin.core.service.AppConstantsService;
import com.origin.data.dao.IAppConstantsDao;
import com.origin.data.entity.IAppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppConstantsServiceImpl  implements AppConstantsService {

@Autowired
private IAppConstantsDao<IAppConstants,Integer> appConstantsDao;

    @Override
    public void save(IAppConstants appConstants) {
        appConstantsDao.save(appConstants);
    }

    @Override
    public void delete(Integer id) {
        appConstantsDao.delete(id);
    }

    @Override
    public void update(IAppConstants appConstants) {
        appConstants.setUpdateDate(new Date());
        appConstantsDao.update(appConstants);
    }

    @Override
    public IAppConstants findById(Integer id) {
        return appConstantsDao.findByPK(id);
    }

    @Override
    public List<IAppConstants> find(IAppConstants appConstants) {
        return appConstantsDao.find(appConstants);
    }

    @Override
    public IAppConstants findByKey(IAppConstants appConstants) {
        return appConstantsDao.findFirst(appConstants);
    }
}
