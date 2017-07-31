package com.origin.core.service.impl;


import com.origin.core.service.AppZhimaService;
import com.origin.data.dao.IAppZhimaDao;
import com.origin.data.entity.IAppZhima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppZhimaServiceImpl  implements AppZhimaService {

    @Autowired
    private IAppZhimaDao<IAppZhima,Integer> appZhimaDao;

    @Override
    public void save(IAppZhima appZhima) {
        appZhimaDao.save(appZhima);
    }

    @Override
    public void delete(Integer id) {
        appZhimaDao.delete(id);
    }

    @Override
    public void update(IAppZhima appZhima) {
        appZhima.setUpdateDate(new Date());
        appZhimaDao.update(appZhima);
    }

    @Override
    public IAppZhima findById(Integer id) {
        return appZhimaDao.findByPK(id);
    }

    @Override
    public List<IAppZhima> find(IAppZhima appZhima) {
        return appZhimaDao.find(appZhima);
    }

    @Override
    public List<IAppZhima> findZhimaInfoByUid(Integer id){
        return appZhimaDao.findZhimaInfoByUid(id);
    }
}
