package com.origin.core.service.impl;


import com.origin.core.service.AppGuideService;
import com.origin.data.dao.IAppGuideDao;
import com.origin.data.entity.IAppGuide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppGuideServiceImpl  implements AppGuideService {

@Autowired
private IAppGuideDao<IAppGuide,Integer> appGuideDao;

@Override
public void save(IAppGuide appGuide) {
appGuideDao.save(appGuide);
}

@Override
public void delete(Integer id) {
appGuideDao.delete(id);
}

@Override
public void update(IAppGuide appGuide) {
    appGuide.setUpdateDate(new Date());
    appGuideDao.update(appGuide);
}

@Override
public IAppGuide findById(Integer id) {
return appGuideDao.findByPK(id);
}

@Override
public List<IAppGuide> find(IAppGuide appGuide) {
    return appGuideDao.find(appGuide);
    }
}
