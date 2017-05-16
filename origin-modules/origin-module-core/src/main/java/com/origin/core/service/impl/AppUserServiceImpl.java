package com.origin.core.service.impl;


import com.origin.core.service.AppUserService;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.entity.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl  implements AppUserService {

@Autowired
private IAppUserDao<IAppUser,Integer> appUserDao;

@Override
public void save(IAppUser appUser) {
appUserDao.save(appUser);
}

@Override
public void delete(Integer id) {
appUserDao.delete(id);
}

@Override
public void update(IAppUser appUser) {
appUserDao.update(appUser);
}

@Override
public IAppUser findById(Integer id) {
return appUserDao.findByPK(id);
}

    @Override
    public IAppUser findFirst(IAppUser appUser) {
        return appUserDao.findFirst(appUser);
    }

    public boolean findOne(IAppUser appUser){
        IAppUser appUserResult = findFirst(appUser);
        if (appUserResult!=null){
            System.out.println("id = "+appUserResult.getId());
            return true;
        }
        System.out.println("appUserResult = null");
        return false;
    }

    @Override
public List<IAppUser> find(IAppUser appUser) {
    return appUserDao.find(appUser);
    }

}
