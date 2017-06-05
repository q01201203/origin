package com.origin.core.service.impl;


import com.origin.core.service.AppPersonDetailService;
import com.origin.data.dao.IAppPersonDetailDao;
import com.origin.data.entity.IAppPersonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    appPersonDetail.setUpdateDate(new Date());
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

    @Override
    public IAppPersonDetail findFirst(IAppPersonDetail appPersonDetail) {
        return appPersonDetailDao.findFirst(appPersonDetail);
    }
}
