package com.origin.core.service.impl;


import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserBankDTO;
import com.origin.core.service.AppUserBankService;
import com.origin.core.util.StringUtil;
import com.origin.data.dao.IAppUserBankDao;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserBank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppUserBankServiceImpl  implements AppUserBankService {

    @Autowired
    private IAppUserBankDao<IAppUserBank,Integer> appUserBankDao;

    @Autowired
    private IAppUserDao<IAppUser,Integer> appUserDao;

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
		appUserBank.setUpdateDate(new Date());
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

    @Override
    public Result saveBankInfo(Integer uId, AppUserBankDTO appUserBank) {
        IAppUser appUser = appUserDao.findByPK(uId);
        String merCustId = "rxh"+appUser.getMobile();

        String bankName = appUserBank.getBankName();
        String bankNumber = appUserBank.getBankNumber();
        String bankMobile = appUserBank.getBankMobile();
        Integer bankType = appUserBank.getBankType();
        String bankCardType = appUserBank.getBankCardType();
        String bindNo = appUserBank.getBindNo();
        String usrBusiAgreementId = appUserBank.getUsrBusiAgreementId();
        String usrPayAgreementId = appUserBank.getUsrPayAgreementId();
        String gateId = appUserBank.getGateId();
        String identityCode = appUserBank.getIdentityCode();
        String cardHolder = appUserBank.getCardHolder();

        if (StringUtil.isNullOrBlank(bankName)||StringUtil.isNullOrBlank(bankNumber)
                ||StringUtil.isNullOrBlank(bankMobile) ||StringUtil.isNullOrBlank(bankCardType)
                || StringUtil.isNullOrBlank(usrBusiAgreementId)||StringUtil.isNullOrBlank(usrPayAgreementId)
                ||StringUtil.isNullOrBlank(gateId)||StringUtil.isNullOrBlank(identityCode)
                ||StringUtil.isNullOrBlank(cardHolder)||bankType==null){
            return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
        }
        boolean save = false;
        IAppUserBank appUserBankDTO = new AppUserBankDTO();
        appUserBankDTO.setUid(uId);
        List<IAppUserBank> appUserBanks = appUserBankDao.find(appUserBankDTO);
        if (appUserBanks!=null&&appUserBanks.size()>0){
            appUserBankDTO = appUserBanks.get(0);
        }else{
            appUserBankDTO = null;
        }
        if (appUserBankDTO == null){
            save = true;
            appUserBankDTO = new AppUserBankDTO();
        }

        appUserBankDTO.setBankNumber(bankNumber);
        appUserBankDTO.setBankName(bankName);
        appUserBankDTO.setBankMobile(bankMobile);
        appUserBankDTO.setBankType(bankType);
        appUserBankDTO.setBankCardType(bankCardType);
        appUserBankDTO.setMerCustId(merCustId);
        appUserBankDTO.setBindNo(bindNo);
        appUserBankDTO.setUsrBusiAgreementId(usrBusiAgreementId);
        appUserBankDTO.setUsrPayAgreementId(usrPayAgreementId);
        appUserBankDTO.setGateId(gateId);
        appUserBankDTO.setIdentityCode(identityCode);
        appUserBankDTO.setCardHolder(cardHolder);
        appUserBankDTO.setUid(uId);
        if (save){
            appUserBankDao.save(appUserBankDTO);
        }else {
            update(appUserBankDTO);
        }
        return Result.createSuccessResult().setMessage("银行卡信息保存成功");
    }
}
