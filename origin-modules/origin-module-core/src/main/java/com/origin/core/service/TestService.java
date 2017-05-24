package com.origin.core.service;

import com.origin.data.entity.ITest;
import java.util.List;

public interface TestService {

    void save(ITest test);
    void delete(Integer id);
    void update(ITest test);
    ITest findById(Integer id);
    List<ITest> find(ITest test);
}