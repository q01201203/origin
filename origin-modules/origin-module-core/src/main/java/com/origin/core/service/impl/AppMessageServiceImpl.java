package com.origin.core.service.impl;


import com.origin.core.dto.AppMessageDTO;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppMessageService;
import com.origin.core.util.JPushUtil;
import com.origin.data.dao.IAppMessageDao;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.entity.IAppMessage;
import com.origin.data.entity.IAppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppMessageServiceImpl  implements AppMessageService {

    @Autowired
    private IAppMessageDao<IAppMessage,Integer> appMessageDao;

    @Autowired
    private IAppUserDao<IAppUser,Integer> appUserDao;

    @Override
    public void save(IAppMessage appMessage) {
        appMessageDao.save(appMessage);
    }

    @Override
    public void delete(Integer id) {
        appMessageDao.delete(id);
    }

    @Override
    public void update(IAppMessage appMessage) {
        appMessage.setUpdateDate(new Date());
        appMessageDao.update(appMessage);
    }

    @Override
    public IAppMessage findById(Integer id) {
        return appMessageDao.findByPK(id);
    }

    @Override
    public IAppMessage findFirst(IAppMessage appMessage) {
        return appMessageDao.findFirst(appMessage);
    }

    @Override
    public List<IAppMessage> find(IAppMessage appMessage) {
        return appMessageDao.find(appMessage);
    }

    @Override
    public List<IAppMessage> findSystemMessage(IAppMessage appMessage) {
        return appMessageDao.findSystemMessage(appMessage);
    }

    public void saveBatchSystemMessage(IAppMessage appMessage){
        List<IAppUser> appUsers = appUserDao.find(new AppUserDTO());
        List<IAppMessage> appMessages = new ArrayList<>();
        for (IAppUser iAppUser: appUsers) {
            IAppMessage message = new AppMessageDTO();
            message.setType(IAppMessage.TYPE_SYSTEM);
            message.setContent(appMessage.getContent());
            message.setUid(iAppUser.getId());
            appMessages.add(message);
        }
        appMessageDao.saveBatchSystemMessage(appMessages);
        JPushUtil.sendPush(JPushUtil.buildPushObject_all_alert(appMessage.getContent()));
    }
}
