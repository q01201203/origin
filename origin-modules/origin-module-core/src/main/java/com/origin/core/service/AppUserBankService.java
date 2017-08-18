package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserBankDTO;
import com.origin.data.entity.IAppUserBank;

import java.util.List;

public interface AppUserBankService {

    void save(IAppUserBank appUserBank);
    void delete(Integer id);
    void update(IAppUserBank appUserBank);
    IAppUserBank findById(Integer id);
    List<IAppUserBank> find(IAppUserBank appUserBank);
    Result saveBankInfo(Integer uId, AppUserBankDTO appUserBank);
}