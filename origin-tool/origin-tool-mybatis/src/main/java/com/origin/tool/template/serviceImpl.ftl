package com.origin.<#if module??>${module}.</#if>service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.origin.<#if module??>${module}.</#if>service.${model}Service;
import com.origin.data.dao.I${model}Dao;
import com.origin.data.entity.I${model};

@Service
public class ${model}ServiceImpl  implements ${model}Service {

@Autowired
private I${model}Dao<I${model},Integer> ${_model}Dao;

    @Override
    public void save(I${model} ${_model}) {
        ${_model}Dao.save(${_model});
    }

    @Override
    public void delete(Integer id) {
        ${_model}Dao.delete(id);
    }

    @Override
    public void update(I${model} ${_model}) {
        ${_model}Dao.update(${_model});
    }

    @Override
    public I${model} findById(Integer id) {
        return ${_model}Dao.findByPK(id);
    }

    @Override
    public I${model} findFirst(I${model} ${_model}) {
        return ${_model}Dao.findFirst(${_model});
    }

    @Override
    public List<I${model}> find(I${model} ${_model}) {
        return ${_model}Dao.find(${_model});
    }
}
