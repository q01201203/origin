package com.origin.core.service.impl;


import com.origin.common.model.mybatis.Result;
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
public List<IAppUser> find(IAppUser appUser) {
    return appUserDao.find(appUser);
    }

    @Override
    public Result login(IAppUser appUser) {
        List<IAppUser> appUserList = find(appUser);
        System.out.println("size = "+appUserList.size());
        if (appUserList!=null && appUserList.size()>0){
            return Result.createSuccessResult();
        }else {
            return Result.createErrorResult();
        }
    }
}
