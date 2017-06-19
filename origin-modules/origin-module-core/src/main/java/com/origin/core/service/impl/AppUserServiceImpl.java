package com.origin.core.service.impl;


import com.origin.core.dto.AppUserDTO;
import com.origin.core.dto.AppZhimaDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.JPushUtil;
import com.origin.core.util.StringUtil;
import com.origin.core.util.ZhimaUtil;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.dao.IAppZhimaDao;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppZhima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppUserServiceImpl  implements AppUserService {

    @Autowired
    private IAppUserDao<IAppUser,Integer> appUserDao;

    @Autowired
    private IAppZhimaDao<IAppZhima,Integer> appZhimaDao;

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
        appUser.setUpdateDate(new Date());
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

    @Override
    public List<IAppUser> find(IAppUser appUser) {
        return appUserDao.find(appUser);
    }

    @Override
    public void saveUser(IAppUser appUser) {
        IAppUser user = new AppUserDTO();
        user.setMobile(appUser.getMobile());
        user = appUserDao.findFirst(user);
        if (user==null){
            appUserDao.save(appUser);

            appUser = appUserDao.findFirst(appUser);
            //获取芝麻信用分
            ZhimaUtil zhimaUtil = new ZhimaUtil();
            String[] resultCreditScore = zhimaUtil.zhimaCreditScoreGet(appUser.getZhimaOpenid());
            if (resultCreditScore!=null){
                IAppZhima zhimaDTO = new AppZhimaDTO();
                zhimaDTO.setType(IAppZhima.TYPE_CREDIT_SCORE);
                zhimaDTO.setScore(resultCreditScore[0]);
                zhimaDTO.setBizNo(resultCreditScore[1]);
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);

                appUser.setZhimaScore(resultCreditScore[0]);
                appUserDao.update(appUser);
            }else{
                appUser.setZhimaScore("600");
                appUserDao.update(appUser);
            }
            String alias = appUser.getJpushAlias();
            if (!StringUtil.isNullOrBlank(alias)) {
                JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, "注册成功"));
            }
        }

    }
}
