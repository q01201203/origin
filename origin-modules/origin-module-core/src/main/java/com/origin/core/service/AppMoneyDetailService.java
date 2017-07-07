package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.data.entity.IAppMoneyDetail;
import java.util.List;

public interface AppMoneyDetailService {

    void save(IAppMoneyDetail appMoneyDetail);
    void delete(Integer id);
    void update(IAppMoneyDetail appMoneyDetail);
    IAppMoneyDetail findById(Integer id);
    IAppMoneyDetail findFirst(IAppMoneyDetail appMoneyDetail);
    List<IAppMoneyDetail> find(IAppMoneyDetail appMoneyDetail);
    List<IAppMoneyDetail> findIncomeInfo(IAppMoneyDetail appMoneyDetail);
    List<IAppMoneyDetail> findMoneyUser(IAppMoneyDetail appMoneyDetail);
    Result saveIncome(Integer uid , Integer tid,IAppMoneyDetail appMoneyDetail);
    Double findTotalActualMoney(IAppMoneyDetail appMoneyDetail);
    void updateAudit(IAppMoneyDetail appMoneyDetail,String messageContent);
}