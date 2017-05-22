package com.origin.core.service;

import com.origin.data.entity.IAppGuide;
import java.util.List;

public interface AppGuideService {

    void save(IAppGuide appGuide);
    void delete(Integer id);
    void update(IAppGuide appGuide);
    IAppGuide findById(Integer id);
    List<IAppGuide> find(IAppGuide appGuide);
}