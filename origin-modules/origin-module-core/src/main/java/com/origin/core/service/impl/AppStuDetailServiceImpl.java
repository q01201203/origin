package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppStuDetailService;
import com.origin.data.dao.IAppStuDetailDao;
import com.origin.data.entity.IAppStuDetail;

@Service
public class AppStuDetailServiceImpl  implements AppStuDetailService {

@Autowired
private IAppStuDetailDao<IAppStuDetail,Integer> appStuDetailDao;

@Override
public void save(IAppStuDetail appStuDetail) {
appStuDetailDao.save(appStuDetail);
}

@Override
public void delete(Integer id) {
appStuDetailDao.delete(id);
}

@Override
public void update(IAppStuDetail appStuDetail) {
appStuDetailDao.update(appStuDetail);
}

@Override
public IAppStuDetail findById(Integer id) {
return appStuDetailDao.findByPK(id);
}

@Override
public List<IAppStuDetail> find(IAppStuDetail appStuDetail) {
    return appStuDetailDao.find(appStuDetail);
    }
}
