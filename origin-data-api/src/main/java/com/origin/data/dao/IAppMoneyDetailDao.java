package com.origin.data.dao;

import com.origin.data.entity.IAppMoneyDetail;

import java.io.Serializable;
import java.util.List;

public interface IAppMoneyDetailDao<T, PK extends Serializable> extends IBaseDao<T, PK> {
    List<IAppMoneyDetail> findIncomeInfo(IAppMoneyDetail appMoneyDetail);
    List<IAppMoneyDetail> findMoneyUser(IAppMoneyDetail appMoneyDetail);
    Double findTotalActualMoney(IAppMoneyDetail appMoneyDetail);
    Double findTotalAskMoney(IAppMoneyDetail appMoneyDetail);
}