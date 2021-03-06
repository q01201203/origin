package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.data.entity.IAppValidcode;

import java.util.List;

public interface AppValidcodeService {

    void save(IAppValidcode appValidcode);
    void delete(Integer id);
    void update(IAppValidcode appValidcode);
    IAppValidcode findById(Integer id);
    List<IAppValidcode> find(IAppValidcode appValidcode);
    Result updateValidate(String mobile ,String type , String validcode);
}