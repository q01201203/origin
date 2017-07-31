package com.origin.core.service;

import com.origin.data.entity.IAppConstants;
import java.util.List;

public interface AppConstantsService {

    void save(IAppConstants appConstants);
    void delete(Integer id);
    void update(IAppConstants appConstants);
    IAppConstants findById(Integer id);
    List<IAppConstants> find(IAppConstants appConstants);
    IAppConstants findByKey(IAppConstants appConstants);
}