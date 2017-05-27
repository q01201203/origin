package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppConstantsService;
import com.origin.data.dao.IAppConstantsDao;
import com.origin.data.entity.IAppConstants;

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
        appConstantsDao.update(appConstants);
    }

    @Override
    public IAppConstants findById(Integer id) {
        return appConstantsDao.findByPK(id);
    }

    @Override
    public IAppConstants findFirst(IAppConstants appConstants) {
        return appConstantsDao.findFirst(appConstants);
    }

    @Override
    public List<IAppConstants> find(IAppConstants appConstants) {
        return appConstantsDao.find(appConstants);
    }
}
