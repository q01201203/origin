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
                zhimaDTO.setBizNo(resultCreditScore[2]);
                if (Boolean.parseBoolean(resultCreditScore[0])){
                    zhimaDTO.setScore(resultCreditScore[3]);
                    appUser.setZhimaScore(resultCreditScore[3]);
                    appUser.setUpdateDate(new Date());
                    appUserDao.update(appUser);
                }else{
                    zhimaDTO.setErrorMessage(resultCreditScore[1]);
                }
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);
            }

            String alias = appUser.getJpushAlias();
            if (!StringUtil.isNullOrBlank(alias)) {
                JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, "注册成功"));
            }

            //行业关注清单
            String[] resultCreditWatchlistii = zhimaUtil.zhimaCreditWatchlistiiGet(appUser.getZhimaOpenid());
            if (resultCreditWatchlistii!=null){
                IAppZhima zhimaDTO = new AppZhimaDTO();
                zhimaDTO.setType(IAppZhima.TYPE_CREDIT_WATCHLISTII);
                zhimaDTO.setBizNo(resultCreditWatchlistii[2]);
                if (Boolean.parseBoolean(resultCreditWatchlistii[0])){
                    zhimaDTO.setIsMatched(resultCreditWatchlistii[3]);
                }else{
                    zhimaDTO.setErrorMessage(resultCreditWatchlistii[1]);
                }
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);
            }

            //欺诈评分
            String[] resultCreditAntifraudScore = zhimaUtil.zhimaCreditAntifraudScoreGet(appUser.getZhimaCertNo(),appUser.getZhimaCertName(),appUser.getMobile());
            if (resultCreditAntifraudScore !=null){
                IAppZhima zhimaDTO = new AppZhimaDTO();
                zhimaDTO.setType(IAppZhima.TYPE_CREDIT_ANTIFRAUD_SCORE);
                zhimaDTO.setBizNo(resultCreditAntifraudScore[2]);
                if (Boolean.parseBoolean(resultCreditAntifraudScore[0])){
                    zhimaDTO.setScore(resultCreditAntifraudScore[3]);
                }else{
                    zhimaDTO.setErrorMessage(resultCreditAntifraudScore[1]);
                }
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);
            }

            //欺诈信息验证
            String[] resultCreditAntifraudVerify = zhimaUtil.zhimaCreditAntifraudVerify(appUser.getZhimaCertNo(),appUser.getZhimaCertName(),appUser.getMobile());
            if (resultCreditAntifraudVerify !=null){
                IAppZhima zhimaDTO = new AppZhimaDTO();
                zhimaDTO.setType(IAppZhima.TYPE_CREDIT_ANTIFRAUDVERIFY);
                zhimaDTO.setBizNo(resultCreditAntifraudVerify[2]);
                if (Boolean.parseBoolean(resultCreditAntifraudVerify[0])){
                    zhimaDTO.setVerifyCode(resultCreditAntifraudVerify[3]);
                }else{
                    zhimaDTO.setErrorMessage(resultCreditAntifraudVerify[1]);
                }
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);
            }

            //欺诈关注清单
            String[] resultCreditAntifraudRiskList = zhimaUtil.zhimaCreditAntifraudRiskList(appUser.getZhimaCertNo(),appUser.getZhimaCertName(),appUser.getMobile());
            if (resultCreditAntifraudRiskList !=null){
                IAppZhima zhimaDTO = new AppZhimaDTO();
                zhimaDTO.setType(IAppZhima.TYPE_CREDIT_ANTIFRAUD_RISKLIST);
                zhimaDTO.setBizNo(resultCreditAntifraudRiskList[2]);
                if (Boolean.parseBoolean(resultCreditAntifraudRiskList[0])){
                    zhimaDTO.setHit(resultCreditAntifraudRiskList[3]);
                    zhimaDTO.setRiskCode(resultCreditAntifraudRiskList[4]);
                }else{
                    zhimaDTO.setErrorMessage(resultCreditAntifraudRiskList[1]);
                }
                zhimaDTO.setUid(appUser.getId());
                appZhimaDao.save(zhimaDTO);
            }
        }
    }
}
