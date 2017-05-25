package com.origin.core.service;

import com.origin.data.entity.IAppPersonDetail;
import java.util.List;

public interface AppPersonDetailService {

    void save(IAppPersonDetail appPersonDetail);
    void delete(Integer id);
    void update(IAppPersonDetail appPersonDetail);
    IAppPersonDetail findById(Integer id);
    IAppPersonDetail findFirst(IAppPersonDetail appPersonDetail);
    List<IAppPersonDetail> find(IAppPersonDetail appPersonDetail);
}