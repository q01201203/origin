package com.origin.core.service;

import com.origin.data.entity.IAppMoneyDetail;
import java.util.List;

public interface AppMoneyDetailService {

    void save(IAppMoneyDetail appMoneyDetail);
    void delete(Integer id);
    void update(IAppMoneyDetail appMoneyDetail);
    IAppMoneyDetail findById(Integer id);
    List<IAppMoneyDetail> find(IAppMoneyDetail appMoneyDetail);
}