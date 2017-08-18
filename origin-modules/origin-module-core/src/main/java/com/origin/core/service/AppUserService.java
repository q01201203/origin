package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.data.entity.IAppUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AppUserService {

    void save(IAppUser appUser);
    void delete(Integer id);
    void update(IAppUser appUser);
    IAppUser findById(Integer id);
    List<IAppUser> find(IAppUser appUser);
    void updateUserZhimaInfo(IAppUser appUser);
    Result saveRegisterUser(String mobile , String pwd);
    String saveRegisterInfo(HttpServletRequest request) throws Exception;
    Result updateResetPwd(String mobile , String pwd);
}