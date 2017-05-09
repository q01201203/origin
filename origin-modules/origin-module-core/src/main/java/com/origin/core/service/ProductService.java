package com.origin.core.service;

import com.origin.data.entity.IProduct;
import java.util.List;

public interface ProductService {

    void save(IProduct product);
    void delete(Integer id);
    void update(IProduct product);
    IProduct findById(Integer id);
    List<IProduct> find(IProduct product);
}