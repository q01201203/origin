package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.AppUserBankService;
import com.origin.data.dao.IAppUserBankDao;
import com.origin.data.entity.IAppUserBank;

@Service
public class AppUserBankServiceImpl  implements AppUserBankService {

@Autowired
private IAppUserBankDao<IAppUserBank,Integer> appUserBankDao;

@Override
public void save(IAppUserBank appUserBank) {
appUserBankDao.save(appUserBank);
}

@Override
public void delete(Integer id) {
appUserBankDao.delete(id);
}

@Override
public void update(IAppUserBank appUserBank) {
appUserBankDao.update(appUserBank);
}

@Override
public IAppUserBank findById(Integer id) {
return appUserBankDao.findByPK(id);
}

@Override
public List<IAppUserBank> find(IAppUserBank appUserBank) {
    return appUserBankDao.find(appUserBank);
    }
}