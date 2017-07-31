package com.origin.core.service;

import com.origin.data.entity.IAppStuDetail;
import java.util.List;

public interface AppStuDetailService {

    void save(IAppStuDetail appStuDetail);
    void delete(Integer id);
    void update(IAppStuDetail appStuDetail);
    IAppStuDetail findById(Integer id);
    List<IAppStuDetail> find(IAppStuDetail appStuDetail);
}