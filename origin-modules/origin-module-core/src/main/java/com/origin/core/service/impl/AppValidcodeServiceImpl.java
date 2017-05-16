package com.origin.core.service.impl;


import com.origin.core.service.AppValidcodeService;
import com.origin.data.dao.IAppValidcodeDao;
import com.origin.data.entity.IAppValidcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppValidcodeServiceImpl  implements AppValidcodeService {

@Autowired
private IAppValidcodeDao<IAppValidcode,Integer> appValidcodeDao;

@Override
public void save(IAppValidcode appValidcode) {
    appValidcodeDao.save(appValidcode);
}

@Override
public void delete(Integer id) {
appValidcodeDao.delete(id);
}

@Override
public void update(IAppValidcode appValidcode) {
appValidcodeDao.update(appValidcode);
}

@Override
public IAppValidcode findById(Integer id) {
return appValidcodeDao.findByPK(id);
}

    @Override
    public IAppValidcode findFirst(IAppValidcode appValidcode) {
        return appValidcodeDao.findFirst(appValidcode);
    }

    public boolean findOne(IAppValidcode appValidcode){
        IAppValidcode validcode = findFirst(appValidcode);
        System.out.println("id = "+validcode.getId());
        if (validcode!=null){
            return true;
        }
        return false;
    }
@Override
public List<IAppValidcode> find(IAppValidcode appValidcode) {
    return appValidcodeDao.find(appValidcode);
    }
}
