package com.origin.core.service;

import com.origin.data.entity.IAppZhima;
import java.util.List;

public interface AppZhimaService {

    void save(IAppZhima appZhima);
    void delete(Integer id);
    void update(IAppZhima appZhima);
    IAppZhima findById(Integer id);
    IAppZhima findFirst(IAppZhima appZhima);
    List<IAppZhima> find(IAppZhima appZhima);
}