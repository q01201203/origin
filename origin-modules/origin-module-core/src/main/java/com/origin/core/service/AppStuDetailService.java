package com.origin.core.service;

import com.origin.common.model.mybatis.Result;
import com.origin.data.entity.IAppStuDetail;
import java.util.List;

public interface AppStuDetailService {

    void save(IAppStuDetail appStuDetail);
    void delete(Integer id);
    void update(IAppStuDetail appStuDetail);
    IAppStuDetail findById(Integer id);
    List<IAppStuDetail> find(IAppStuDetail appStuDetail);
    Result saveStudentInfo(Integer uId, String infoSchool, String infoDepartment, String infoClass,
                          String infoRoomNumber, String infoEmycontactRelation, String infoEmycontactMobile,
                          String infoContactRelation, String infoContactMobile);
}