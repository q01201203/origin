package com.origin.core.service.impl;


import com.origin.core.service.TestService;
import com.origin.data.dao.ITestDao;
import com.origin.data.entity.ITest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl  implements TestService {

@Autowired
private ITestDao<ITest,Integer> testDao;

    @Override
    public void save(ITest test) {
        testDao.save(test);
    }

    @Override
    public void delete(Integer id) {
        testDao.delete(id);
    }

    @Override
    public void update(ITest test) {
        testDao.update(test);
    }

    @Override
    public ITest findById(Integer id) {
        return testDao.findByPK(id);
    }

    @Override
    public List<ITest> find(ITest test) {
        return testDao.find(test);
    }
}
