package com.origin.core.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.core.service.ProductService;
import com.origin.data.dao.IProductDao;
import com.origin.data.entity.IProduct;

@Service
public class ProductServiceImpl  implements ProductService {

@Autowired
private IProductDao<IProduct,Integer> productDao;

@Override
public void save(IProduct product) {
productDao.save(product);
}

@Override
public void delete(Integer id) {
productDao.delete(id);
}

@Override
public void update(IProduct product) {
productDao.update(product);
}

@Override
public IProduct findById(Integer id) {
return productDao.findByPK(id);
}

@Override
public List<IProduct> find(IProduct product) {
    return productDao.find(product);
    }
}
