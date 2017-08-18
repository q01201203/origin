package com.origin.core.service.impl;


import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppValidcodeDTO;
import com.origin.core.service.AppValidcodeService;
import com.origin.data.dao.IAppValidcodeDao;
import com.origin.data.entity.IAppValidcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        appValidcode.setUpdateDate(new Date());
        appValidcodeDao.update(appValidcode);
    }

    @Override
    public IAppValidcode findById(Integer id) {
        return appValidcodeDao.findByPK(id);
    }

    @Override
    public List<IAppValidcode> find(IAppValidcode appValidcode) {
        return appValidcodeDao.find(appValidcode);
    }

    @Override
    public Result updateValidate(String mobile, String type, String validcode) {
        IAppValidcode appValidcode = new AppValidcodeDTO();
        appValidcode.setMobile(mobile);
        appValidcode.setValidcode(validcode);
        appValidcode.setType(Integer.parseInt(type));
        appValidcode.setStatus(IAppValidcode.STATUS_YES);
        IAppValidcode appValidcodeResult;
        List<IAppValidcode> appValidcodeResults = appValidcodeDao.find(appValidcode);
        if (appValidcodeResults!=null&&appValidcodeResults.size()>0){
            appValidcodeResult = appValidcodeResults.get(0);
        }else{
            appValidcodeResult = null;
        }
        if (appValidcodeResult!=null){
            appValidcodeResult.setStatus(IAppValidcode.STATUS_NO);
            update(appValidcodeResult);
            return Result.createSuccessResult().setMessage("验证码验证成功");
        }else {
            return Result.createErrorResult().setMessage("验证码错误");
        }
    }
}
