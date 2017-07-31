package com.origin.core.service;

import com.origin.data.entity.IAppMessage;

import java.util.List;

public interface AppMessageService {

    void save(IAppMessage appMessage);
    void delete(Integer id);
    void update(IAppMessage appMessage);
    IAppMessage findById(Integer id);
    List<IAppMessage> find(IAppMessage appMessage);
    public List<IAppMessage> findSystemMessage(IAppMessage appMessage);
    public void saveBatchSystemMessage(IAppMessage appMessage);
    public void updateBatch(List<IAppMessage> appMessages);
    List<IAppMessage> findOrderBy(IAppMessage appMessage);
    void savePersonalMessage(IAppMessage appMessage);
}