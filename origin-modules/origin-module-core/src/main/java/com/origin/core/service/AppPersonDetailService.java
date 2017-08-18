package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.data.entity.IAppPersonDetail;
import java.util.List;

public interface AppPersonDetailService {

    void save(IAppPersonDetail appPersonDetail);
    void delete(Integer id);
    void update(IAppPersonDetail appPersonDetail);
    IAppPersonDetail findById(Integer id);
    List<IAppPersonDetail> find(IAppPersonDetail appPersonDetail);
    Result savePersonInfo(Integer uId, String infoCompanyName,String infoCompanyAddress,String infoQq,
                          String infoWeixin,String infoHome,String infoEmycontactRelation,String infoEmycontactMobile,
                          String infoContactRelation,String infoContactMobile);
}