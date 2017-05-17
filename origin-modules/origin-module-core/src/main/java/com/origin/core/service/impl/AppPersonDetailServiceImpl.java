package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppPersonDetailService;
import com.origin.data.dao.IAppPersonDetailDao;
import com.origin.data.entity.IAppPersonDetail;

@Service
public class AppPersonDetailServiceImpl  implements AppPersonDetailService {

@Autowired
private IAppPersonDetailDao<IAppPersonDetail,Integer> appPersonDetailDao;

@Override
public void save(IAppPersonDetail appPersonDetail) {
appPersonDetailDao.save(appPersonDetail);
}

@Override
public void delete(Integer id) {
appPersonDetailDao.delete(id);
}

@Override
public void update(IAppPersonDetail appPersonDetail) {
appPersonDetailDao.update(appPersonDetail);
}

@Override
public IAppPersonDetail findById(Integer id) {
return appPersonDetailDao.findByPK(id);
}

@Override
public List<IAppPersonDetail> find(IAppPersonDetail appPersonDetail) {
    return appPersonDetailDao.find(appPersonDetail);
    }
}
