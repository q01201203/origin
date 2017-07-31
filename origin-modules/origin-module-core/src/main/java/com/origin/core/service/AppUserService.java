package com.origin.core.service;

import com.origin.data.entity.IAppUser;
import java.util.List;

public interface AppUserService {

    void save(IAppUser appUser);
    void delete(Integer id);
    void update(IAppUser appUser);
    IAppUser findById(Integer id);
    List<IAppUser> find(IAppUser appUser);
    void updateUserZhimaInfo(IAppUser appUser);
}