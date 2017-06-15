package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppZhimaService;
import com.origin.data.dao.IAppZhimaDao;
import com.origin.data.entity.IAppZhima;

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
        appZhimaDao.update(appZhima);
    }

    @Override
    public IAppZhima findById(Integer id) {
        return appZhimaDao.findByPK(id);
    }

    @Override
    public IAppZhima findFirst(IAppZhima appZhima) {
        return appZhimaDao.findFirst(appZhima);
    }

    @Override
    public List<IAppZhima> find(IAppZhima appZhima) {
        return appZhimaDao.find(appZhima);
    }
}
