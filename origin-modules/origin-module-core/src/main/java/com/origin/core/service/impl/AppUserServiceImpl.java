package com.origin.core.service.impl;


import com.origin.common.model.mybatis.Result;
import com.origin.common.util.Md5Util;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.dto.AppZhimaDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.util.*;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.dao.IAppZhimaDao;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppZhima;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class AppUserServiceImpl  implements AppUserService {

    Logger log = LoggerFactory.getLogger(AppUserServiceImpl.class);

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
    public List<IAppUser> find(IAppUser appUser) {
        return appUserDao.find(appUser);
    }

    @Override
    public void updateUserZhimaInfo(IAppUser appUser) {
        String alias = appUser.getJpushAlias();
        Integer uId = appUser.getId();
        update(appUser);

        //获取芝麻信用分
        ZhimaUtil zhimaUtil = new ZhimaUtil();
        String[] resultCreditScore = zhimaUtil.zhimaCreditScoreGet(appUser.getZhimaOpenid());
        if (resultCreditScore!=null){
            IAppZhima zhimaDTO = new AppZhimaDTO();
            zhimaDTO.setType(IAppZhima.TYPE_CREDIT_SCORE);
            zhimaDTO.setBizNo(resultCreditScore[2]);
            if (Boolean.parseBoolean(resultCreditScore[0])){
                zhimaDTO.setScore(resultCreditScore[3]);
                //更新用户芝麻信用分和权限
                appUser.setZhimaScore(resultCreditScore[3]);
                appUser.setAuthority(Constants.AHORITY_MEDIUM);
                update(appUser);
                //推送成功消息回调
                if (!StringUtil.isNullOrBlank(alias)) {
                    try {
                        String result = JsonUtil.object2Json(Result.createSuccessResult((CustomToken.generate(new SimpleToken(uId,
                                Constants.AHORITY_MEDIUM))),"获取芝麻信用分成功"));
                        JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, result));
                    } catch (Exception e) {
                        e.printStackTrace();
                        String result = JsonUtil.object2Json(Result.createErrorResult().setMessage("生成token异常"));
                        JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, result));
                    }

                }
            }else{
                zhimaDTO.setErrorMessage(resultCreditScore[1]);
                //推送失败消息回调
                if (!StringUtil.isNullOrBlank(alias)) {
                    String result = JsonUtil.object2Json(Result.createErrorResult().setMessage("获取芝麻信用分失败，" +
                            "error_message:"+resultCreditScore[1]));
                    JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, result));
                }
            }
            zhimaDTO.setUid(appUser.getId());
            appZhimaDao.save(zhimaDTO);
        }else{
            //推送失败消息回调
            if (!StringUtil.isNullOrBlank(alias)) {
                String result = JsonUtil.object2Json(Result.createErrorResult().setMessage("芝麻信用接口返回为null"));
                JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, result));
            }
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


    @Override
    public Result saveRegisterUser(String mobile ,String pwd) {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile(mobile);
        List<IAppUser> appUsers = appUserDao.find(appUser);
        if (appUsers!=null&&appUsers.size()>0){
            appUser = appUsers.get(0);
        }else{
            appUser = null;
        }
        if (appUser!=null){
            return Result.createErrorResult().setMessage("用户已存在");
        }else {
            appUser = new AppUserDTO();
            appUser.setMobile(mobile);
            appUser.setPwd(Md5Util.generatePassword(pwd));
            appUserDao.save(appUser);
            return Result.createSuccessResult().setMessage("注册成功");
        }
    }

    @Override
    public String saveRegisterInfo(HttpServletRequest request) throws Exception {
        String params = request.getParameter("params");
        String sign = request.getParameter("sign");
        log.debug("renxinhua zhima params = "+params +" sign = "+sign);
        ZhimaUtil zhimaUtil = new ZhimaUtil();
        String result = zhimaUtil.getResult(params,sign);

        Map map = StringUtil.urlSplit(result);
        String open_id = "";
        if (map.get("open_id")!=null){
            open_id = map.get("open_id").toString();
        }
        String error_message = map.get("error_message").toString();
        String success = map.get("success").toString();
        String error_code = map.get("error_code").toString();
        String state = map.get("state").toString();
        log.debug("renxinhua open_id = "+open_id+" error_message = "+error_message+" success = "+success+
                " error_code = "+error_code+" state = "+state);
        String[] datas = state.split("\\,");
        String zhimaCertName = datas[0];
        String zhimaCertNo = datas[1];
        String token = datas[2];
        log.debug("renxinhua  zhimaCertName = "+zhimaCertName +" zhimaCertNo = "+zhimaCertNo +" token = "+token);

        //验证权限
        Object tokenValidResult = CustomToken.tokenValidate(CustomToken.parse(token),Constants.AHORITY_LOW);
        if (!(tokenValidResult instanceof SimpleToken)){
            //return "permission_error";
            return "zhimafail";
        }
        Integer uId = ((SimpleToken) tokenValidResult).getId();
        IAppUser appUser = findById(uId);
        String alias = appUser.getJpushAlias();

        if (result!=null && !"error".equals(result)){
            if ("true".equals(success)){
                //更新芝麻信息
                appUser.setZhimaCertName(zhimaCertName);
                appUser.setZhimaCertNo(zhimaCertNo);
                appUser.setZhimaOpenid(open_id);
                updateUserZhimaInfo(appUser);
                //return "success";
                return "zhimasuccess";
            }else {
                if (!StringUtil.isNullOrBlank(alias)) {
                    String message = JsonUtil.object2Json(Result.createErrorResult().setMessage("芝麻认证授权失败，" +
                            error_code+":"+error_message));
                    JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, message));
                }
                //return error_code+":"+error_message;
                return "zhimafail";
            }
        }else {
            if (!StringUtil.isNullOrBlank(alias)) {
                String message = JsonUtil.object2Json(Result.createErrorResult().setMessage("芝麻认证授权失败"));
                JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_message(alias, message));
            }
            //return "error";
            return "zhimafail";
        }
    }

    @Override
    public Result updateResetPwd(String mobile, String pwd) {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile(mobile);
        List<IAppUser> appUsers = appUserDao.find(appUser);
        if (appUsers!=null&&appUsers.size()>0){
            appUser = appUsers.get(0);
        }else{
            appUser = null;
        }
        if (appUser!=null){
            appUser.setId(appUser.getId());
            appUser.setPwd(Md5Util.generatePassword(pwd));
            appUser.setUpdateDate(new Date());
            appUserDao.update(appUser);
            return Result.createSuccessResult().setMessage("重置密码成功");
        }else{
            return Result.createErrorResult().setMessage("重置密码失败");
        }
    }
}
