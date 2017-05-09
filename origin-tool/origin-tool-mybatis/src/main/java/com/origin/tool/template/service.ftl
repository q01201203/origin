package com.origin.<#if module??>${module}.</#if>service;

import com.origin.data.entity.I${model};
import java.util.List;

public interface ${model}Service {

    void save(I${model} ${_model});
    void delete(Integer id);
    void update(I${model} ${_model});
    I${model} findById(Integer id);
    List<I${model}> find(I${model} ${_model});
}