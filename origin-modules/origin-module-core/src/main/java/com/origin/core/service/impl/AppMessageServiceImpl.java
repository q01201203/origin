package com.origin.core.service.impl;


import com.origin.core.dto.AppMessageDTO;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppMessageService;
import com.origin.core.util.JPushUtil;
import com.origin.core.util.StringUtil;
import com.origin.data.dao.IAppMessageDao;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.entity.IAppMessage;
import com.origin.data.entity.IAppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AppMessageServiceImpl  implements AppMessageService {

    Logger log = LoggerFactory.getLogger(AppMessageServiceImpl.class);
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
    public List<IAppMessage> find(IAppMessage appMessage) {
        return appMessageDao.find(appMessage);
    }

    @Override
    public void savePersonalMessage(IAppMessage appMessage){
        IAppUser appUser = appUserDao.findByPK(appMessage.getUid());
        String alias = appUser.getJpushAlias();
        String content = appMessage.getContent();
        appMessage.setType(IAppMessage.TYPE_PERSONAL);
        appMessageDao.save(appMessage);
        log.debug(""+appMessage.getId());
        if (!StringUtil.isNullOrBlank(alias)){
            JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_alert_message(alias,content,appMessage.getId()));
        }
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

    public void updateBatch(List<IAppMessage> appMessages){
        for (IAppMessage appMessage:appMessages) {
            appMessage.setUpdateDate(new Date());
        }
        appMessageDao.updateBatch(appMessages);
    }

    @Override
    public List<IAppMessage> findOrderBy(IAppMessage appMessage) {
        return appMessageDao.findOrderBy(appMessage);
    }
}
