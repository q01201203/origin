package com.origin.core.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lc on 2017/6/14.
 */
public class JPushUtil {

    protected static final String APP_KEY = "899eacc5ce504bec6369c3bd";
    protected static final String MASTER_SECRET = "68df804c5a8c4df4017c6804";
    public static final String ALERT = "JPush Test - alert";

    protected static final Logger LOG = LoggerFactory.getLogger(JPushUtil.class);

    public static void sendPush(PushPayload payload) {
        //ClientConfig clientConfig = ClientConfig.getInstance();
        JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY);//, null, clientConfig);
        //PushPayload.alertAll(ALERT);
        //buildPushObject_all_alias_alert();
        //PushPayload payload = buildPushObject_all_alias_alert();
        try {
            PushResult result = jpushClient.sendPush(payload);
            int status = result.getResponseCode();
            LOG.info("Got result - " + result);

        } catch (APIConnectionException e) {
            LOG.error("Connection error. Should retry later. ", e);
            LOG.error("Sendno: " + payload.getSendno());

        } catch (APIRequestException e) {
            LOG.error("Error response from JPush server. Should review and fix it. ", e);
            LOG.info("HTTP Status: " + e.getStatus());
            LOG.info("Error Code: " + e.getErrorCode());
            LOG.info("Error Message: " + e.getErrorMessage());
            LOG.info("Msg ID: " + e.getMsgId());
            LOG.error("Sendno: " + payload.getSendno());
        }
    }

    public static PushPayload buildPushObject_all_alert(String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.alert(content))
                .build();
    }

    public static PushPayload buildPushObject_all_alias_alert(String alias,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(content))
                .build();
    }

    public static PushPayload buildPushObject_all_alias_message(String alias,String content) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setMessage(Message.content(content))
                .build();
    }
}
