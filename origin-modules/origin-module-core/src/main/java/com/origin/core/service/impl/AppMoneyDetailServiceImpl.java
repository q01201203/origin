package com.origin.core.service.impl;


import com.origin.core.service.AppMoneyDetailService;
import com.origin.data.dao.IAppMoneyDetailDao;
import com.origin.data.entity.IAppMoneyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppMoneyDetailServiceImpl  implements AppMoneyDetailService {

@Autowired
private IAppMoneyDetailDao<IAppMoneyDetail,Integer> appMoneyDetailDao;

    @Override
    public void save(IAppMoneyDetail appMoneyDetail) {
        appMoneyDetailDao.save(appMoneyDetail);
    }

    @Override
    public void delete(Integer id) {
        appMoneyDetailDao.delete(id);
    }

    @Override
    public void update(IAppMoneyDetail appMoneyDetail) {
        appMoneyDetailDao.update(appMoneyDetail);
    }

    @Override
    public IAppMoneyDetail findById(Integer id) {
        return appMoneyDetailDao.findByPK(id);
    }

    @Override
    public IAppMoneyDetail findFirst(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findFirst(appMoneyDetail);
    }

    @Override
    public List<IAppMoneyDetail> find(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.find(appMoneyDetail);
    }

    @Override
    public List<IAppMoneyDetail> findMoneyUserInfo(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findMoneyUserInfo(appMoneyDetail);
    }
}
