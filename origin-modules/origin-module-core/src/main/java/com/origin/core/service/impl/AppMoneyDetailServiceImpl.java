package com.origin.core.service.impl;


import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppMessageDTO;
import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppUserBankDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.core.util.JPushUtil;
import com.origin.core.util.StringUtil;
import com.origin.data.dao.*;
import com.origin.data.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AppMoneyDetailServiceImpl  implements AppMoneyDetailService {

    Logger log = LoggerFactory.getLogger(AppMoneyDetailServiceImpl.class);

    @Autowired
    private IAppMoneyDetailDao<IAppMoneyDetail,Integer> appMoneyDetailDao;

    @Autowired
    private IAppTaskDao<IAppTask,Integer> appTaskDao;

    @Autowired
    private IAppUserDao<IAppUser,Integer> appUserDao;

    @Autowired
    private IAppUserTaskDao<IAppUserTask,Integer> appUserTaskDao;

    @Autowired
    private IAppMessageDao<IAppMessage,Integer> appMessageDao;

    @Autowired
    private IAppUserBankDao<IAppUserBank,Integer> appUserBankDao;

    @Override
    public void save(IAppMoneyDetail appMoneyDetail) {
        appMoneyDetailDao.save(appMoneyDetail);
    }

    @Override
    public void delete(Integer id) {
        appMoneyDetailDao.delete(id);
    }

    @Override
    public void update(IAppMoneyDetail appMoneyDetail) {
        appMoneyDetail.setUpdateDate(new Date());
        appMoneyDetailDao.update(appMoneyDetail);
    }

    @Override
    public IAppMoneyDetail findById(Integer id) {
        return appMoneyDetailDao.findByPK(id);
    }

    @Override
    public List<IAppMoneyDetail> find(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.find(appMoneyDetail);
    }

    @Override
    public List<IAppMoneyDetail> findIncomeInfo(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findIncomeInfo(appMoneyDetail);
    }

    @Override
    public List<IAppMoneyDetail> findMoneyUser(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findMoneyUser(appMoneyDetail);
    }

    @Override
    public void saveWithdraw(IAppMoneyDetail appMoneyDetail) {
        save(appMoneyDetail);
        Integer uid = appMoneyDetail.getUid();
        Double money = appMoneyDetail.getMoneyAsk();
        //修改余额
        updateUserBalance(uid,money,false);
    }

    @Override
    public void saveRepay(IAppMoneyDetail appMoneyDetail) {
        //保存还款记录
        save(appMoneyDetail);
        //修改借款记录的还款状态
        appMoneyDetail = findById(appMoneyDetail.getPid());
        appMoneyDetail.setRepayStatus(IAppMoneyDetail.REPAY_STATUS_WAIT);
        update(appMoneyDetail);
        //修改余额
        if (IAppMoneyDetail.REPAY_WAY_BALANCE.equals(appMoneyDetail.getRepayWay())){
            Integer uid = appMoneyDetail.getUid();
            Double money = appMoneyDetail.getMoneyAsk();
            updateUserBalance(uid,money,false);
        }
    }

    @Override
    public Result saveIncome(Integer uid, Integer tid,IAppMoneyDetail appMoneyDetail) {
        IAppTask appTask = appTaskDao.findByPK(tid);
        Integer taskNumber = appTask.getTaskNumber();
        Integer taskType = appTask.getTaskType();
        String taskName = appTask.getTaskName();
        Double taskMoney = appTask.getTaskMoney();
        Date taskEndTime = appTask.getTaskEndTime();
        Date nowTime = new Date();
        //判断数量
        if (taskNumber<1){
            log.debug("renxinhua number 小于1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务数量没了");
        }

        //判断任务时间
        if (taskType.equals(IAppTask.TYPE_TIMELIMIT)){
            log.debug("renxinhua nowtime = "+ nowTime + " endtime = "+ taskEndTime);
            if (nowTime.after(taskEndTime)){
                log.debug("renxinhua 任务过期");
                return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务过期");
            }
        }

        IAppUserTask appUserTask = new AppUserTaskDTO();
        appUserTask.setUid(uid);
        appUserTask.setTid(tid);
        IAppMoneyDetail appMoneyDetailDTO = new AppMoneyDetailDTO();
        appMoneyDetailDTO.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
        appUserTask.setAppMoneyDetail(appMoneyDetailDTO);
        List<IAppUserTask> appUserTasks = appUserTaskDao.findTaskUserInfo(appUserTask);
        //防止重复提交
        if (appUserTasks.size()>0){
            log.debug("renxinhua 重复提交1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("重复提交");
        }
        appMoneyDetailDTO.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
        appUserTask.setAppMoneyDetail(appMoneyDetailDTO);
        appUserTasks = appUserTaskDao.findTaskUserInfo(appUserTask);
        if (appUserTasks.size()>0){
            log.debug("renxinhua 重复提交2");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("重复提交");
        }

        //任务数量减一
        appTask.setTaskNumber(taskNumber-1);
        appTask.setUpdateDate(new Date());
        appTaskDao.update(appTask);

        //增加收入记录
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setTaskName(taskName);
        appMoneyDetail.setMoneyAsk(taskMoney);
        appMoneyDetailDao.save(appMoneyDetail);
        log.debug("money id "+appMoneyDetail.getId());
        Integer mid = appMoneyDetail.getId();

        //中间关系表添加记录
        appUserTask.setMid(mid);
        appUserTaskDao.save(appUserTask);
        return Result.createSuccessResult().setMessage("收入提交成功");
    }

    @Override
    public Double findTotalActualMoney(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findTotalActualMoney(appMoneyDetail);
    }

    @Override
    public Double findTotalAskMoney(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findTotalAskMoney(appMoneyDetail);
    }

    //审核同时 修改用户余额
    @Override
    public void updateAudit(IAppMoneyDetail appMoneyDetail,String messageContent) {
        Integer mid = appMoneyDetail.getId();
        Integer status = appMoneyDetail.getStatus();
        Double moneyActual = appMoneyDetail.getMoneyActual();
        Double moneyAsk = appMoneyDetail.getMoneyAsk();
        Integer moneyType = appMoneyDetail.getType();
        Integer uid = appMoneyDetail.getUid();
        Integer pid = appMoneyDetail.getPid();
        update(appMoneyDetail);
        //审核通过修改用户余额
        if (IAppMoneyDetail.STATUS_AUDIT_SUCCESS.equals(status)){
            appMoneyDetail = appMoneyDetailDao.findByPK(mid);
            if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
                if (IAppMoneyDetail.REPAY_WAY_BALANCE.equals(appMoneyDetail.getRepayWay())){
                    updateUserBalance(uid,moneyAsk - moneyActual,true);
                }
            }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(moneyType)){
                updateUserBalance(uid,moneyAsk - moneyActual,true);
            }else if (IAppMoneyDetail.TYPE_INCOME.equals(moneyType)){
                updateUserBalance(uid,moneyActual,true);
            }
        }else if (IAppMoneyDetail.STATUS_AUDIT_FAIL.equals(status)){
            appMoneyDetail = appMoneyDetailDao.findByPK(mid);
            if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
                if (IAppMoneyDetail.REPAY_WAY_BALANCE.equals(appMoneyDetail.getRepayWay())){
                    updateUserBalance(uid,moneyAsk,true);
                }
            }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(moneyType)){
                updateUserBalance(uid,moneyAsk,true);
            }
        }

        //保存消息推送
        IAppUser appUser = appUserDao.findByPK(uid);
        String userAlias = appUser.getJpushAlias();
        String userName = appUser.getNickname();
        //String messageContent = getContent(status, moneyActual, moneyAsk, moneyType, userName,1);
        String contentExtra = getContent(status, moneyActual, moneyAsk, moneyType, userName,2);

        IAppMessage appMessage = new AppMessageDTO();
        appMessage.setUid(uid);
        appMessage.setContent(messageContent);
        appMessage.setStatus(IAppMessage.STATUS_YES);
        appMessage.setType(IAppMessage.TYPE_PERSONAL);
        appMessage.setContentExtra(contentExtra);
        appMessageDao.save(appMessage);

        if (!StringUtil.isNullOrBlank(userAlias)){
            JPushUtil.sendPush(JPushUtil.buildPushObject_all_alias_alert_message(userAlias, messageContent,appMessage.getId()));
        }

        //如果是借钱成功则发送定时推送，如果还钱成功则取消
        if (IAppMoneyDetail.STATUS_AUDIT_SUCCESS.equals(status)){
            if (IAppMoneyDetail.TYPE_BORROW.equals(moneyType)){
                appMoneyDetail = appMoneyDetailDao.findByPK(mid);
                Integer repayTimeType = appMoneyDetail.getRepayTimeType();
                Date auditTime = appMoneyDetail.getUpdateDate();
                int repayDay = 0;
                int advanceDay = 3; //提前推送的天数
                if (IAppMoneyDetail.REPAY_TIME_TYPE_7.equals(repayTimeType)){
                    repayDay = 7 ;
                }else if (IAppMoneyDetail.REPAY_TIME_TYPE_15.equals(repayTimeType)){
                    repayDay = 15 ;
                }
                Date repayDayTime = dateIncre(auditTime,repayDay -advanceDay); //还款提醒日期
                Date repayDeadline = dateIncre(auditTime,repayDay);//还款截止日期
                log.debug("renxinhua repayDayTime = "+repayDayTime +" repayDeadline = "+ repayDeadline);
                appMoneyDetail.setRepayDeadline(repayDeadline);

                if (!StringUtil.isNullOrBlank(userAlias)){
                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String scheduleId = JPushUtil.createSingleSchedule("borrow",sdf.format(repayDayTime),userAlias,"您还有借款未还清，请及时还清");
                    appMoneyDetail.setScheduleId(scheduleId);
                }
                update(appMoneyDetail);
            }else if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
                if (pid != null){
                    appMoneyDetail = appMoneyDetailDao.findByPK(pid);
                    String scheduleId = appMoneyDetail.getScheduleId();
                    if (!StringUtil.isNullOrBlank(scheduleId)){
                        JPushUtil.deleteSchedule(scheduleId);
                    }
                }
            }
        }

        //更新借款记录的还款状态
        if (IAppMoneyDetail.STATUS_AUDIT_SUCCESS.equals(status)){
            if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
                if (pid != null){
                    appMoneyDetail = appMoneyDetailDao.findByPK(pid);
                    appMoneyDetail.setRepayStatus(IAppMoneyDetail.REPAY_STATUS_YES);
                    update(appMoneyDetail);
                }
            }
        }else if (IAppMoneyDetail.STATUS_AUDIT_FAIL.equals(status)){
            if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)) {
                if (pid != null) {
                    appMoneyDetail = appMoneyDetailDao.findByPK(pid);
                    appMoneyDetail.setRepayStatus(IAppMoneyDetail.REPAY_STATUS_NO);
                    update(appMoneyDetail);
                }
            }
        }
    }

    private Date dateIncre(Date date,int day){
        Calendar ca=Calendar.getInstance();
        ca.setTime(date);
        ca.add(Calendar.DAY_OF_YEAR, day);
        log.debug("renxinhua date = "+date);
        return ca.getTime();
    }

    //flag true:增加 false:减少
    private void updateUserBalance(Integer uid,Double money,boolean flag) {
        IAppUser appUser = appUserDao.findByPK(uid);
        Double balance = appUser.getBalance();
        if (flag){
            balance = balance + money;
        }else{
            balance = balance - money;
        }
        appUser.setBalance(balance);
        appUser.setUpdateDate(new Date());
        appUserDao.update(appUser);
    }

    //flag 1通知内容 2消息提示
    private String getContent(Integer status, Double moneyActual, Double moneyAsk, Integer moneyType, String userName,int flag) {
        StringBuffer content = new StringBuffer();
        content.append(userName+"你好，你申请的");
        if (IAppMoneyDetail.TYPE_BORROW.equals(moneyType)){
            content.append("贷款");
        }else if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
            content.append("还款");
        }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(moneyType)){
            content.append("提现");
        }else if (IAppMoneyDetail.TYPE_INCOME.equals(moneyType)){
            content.append("任务");
        }
        if (flag == 1){
            content.append(moneyAsk+"元");
        }
        if (IAppMoneyDetail.STATUS_AUDIT_SUCCESS.equals(status)){
            if (flag ==1){
                content.append("实际到账"+moneyActual+"元");
            }
            content.append("已成功通过审核");
        }else if (IAppMoneyDetail.STATUS_AUDIT_FAIL.equals(status)){
            content.append("未能通过审核");
        }
        if (flag == 2){
            content.append("，你可以到");
            if (IAppMoneyDetail.TYPE_BORROW.equals(moneyType)){
                content.append("我的贷款");
            }else if (IAppMoneyDetail.TYPE_REPAY.equals(moneyType)){
                content.append("我的还款");
            }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(moneyType)){
                content.append("我的提现");
            }else if (IAppMoneyDetail.TYPE_INCOME.equals(moneyType)){
                content.append("我的任务");
            }
            content.append("中去查看详情");
        }
        return content.toString();
    }

    @Override
    public Result saveSubmitMoney(Integer uId,String askMoney,String type,String repayTimeType,String repayWay,String taskId
            ,String taskUserName,String taskMobile,String pid,String delayMoney,String orderId) {
        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setUid(uId);
        if (IAppMoneyDetail.TYPE_BORROW.equals(Integer.parseInt(type))){
            log.debug("renxinhua 请求的repayTimeType为" + repayTimeType);
            if (StringUtil.isNullOrBlank(askMoney)||StringUtil.isNullOrBlank(repayTimeType)){
                return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
            }

            //借钱不能大于可借额度
            IAppUser appUser = appUserDao.findByPK(uId);
            Double moneyMax = appUser.getMoneyMax();
            Double borrowActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
            Double borrowAsk = getTotalAskMoney(uId,IAppMoneyDetail.TYPE_BORROW);
            Double repayActual = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
            Double borrowLine = moneyMax - borrowActual - borrowAsk + repayActual;
            if (Double.parseDouble(askMoney)>borrowLine){
                return Result.create(ResultCode.SERVICE_ERROR).setMessage("借钱金额大于可借额度");
            }

            appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
            appMoneyDetail.setRepayTimeType(Integer.parseInt(repayTimeType));
            appMoneyDetail.setRepayStatus(IAppMoneyDetail.REPAY_STATUS_NO);
            appMoneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
            save(appMoneyDetail);
            return Result.createSuccessResult().setMessage("借款申请成功");
        }else if (IAppMoneyDetail.TYPE_REPAY.equals(Integer.parseInt(type))){
            log.debug("renxinhua 请求的repayWay为" + repayWay);
            if (StringUtil.isNullOrBlank(askMoney)||StringUtil.isNullOrBlank(repayWay)){
                return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
            }
            //还钱不能大于需还金额
            Double borrow = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_BORROW);
            Double repay = getTotalActualMoney(uId,IAppMoneyDetail.TYPE_REPAY);
            Double needRepay = borrow - repay;
            if (Double.parseDouble(askMoney)>needRepay){
                return Result.create(ResultCode.SERVICE_ERROR).setMessage("还钱金额大于需还金额");
            }

            appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
            appMoneyDetail.setRepayWay(Integer.parseInt(repayWay));
            appMoneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
            appMoneyDetail.setRepayTime(new Date());
            appMoneyDetail.setPid(Integer.parseInt(pid));
            appMoneyDetail.setDelayMoney(Double.parseDouble(delayMoney));
            appMoneyDetail.setExtensionOne(orderId);
            saveRepay(appMoneyDetail);
            return Result.createSuccessResult().setMessage("还款申请成功");
        }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(Integer.parseInt(type))){
            if (StringUtil.isNullOrBlank(askMoney)){
                return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
            }
            //提现之前判断用户是否绑定银行卡
            IAppUserBank appUserBank = new AppUserBankDTO();
            appUserBank.setUid(uId);
            List<IAppUserBank> appUserBanks = appUserBankDao.find(appUserBank);
            if (appUserBanks == null || appUserBanks.size() ==0){
                return Result.create(ResultCode.SERVICE_ERROR).setMessage("您还未绑定银行卡");
            }

            IAppUser appUser = appUserDao.findByPK(uId);
            Double balance = appUser.getBalance();
            if (Double.parseDouble(askMoney)>balance){
                return Result.create(ResultCode.SERVICE_ERROR).setMessage("提现金额大于余额");
            }

            appMoneyDetail.setMoneyAsk(Double.parseDouble(askMoney));
            appMoneyDetail.setType(IAppMoneyDetail.TYPE_WITHDRAW);
            saveWithdraw(appMoneyDetail);
            return Result.createSuccessResult().setMessage("提现申请成功");
        }else if (IAppMoneyDetail.TYPE_INCOME.equals(Integer.parseInt(type))){
            if (StringUtil.isNullOrBlank(taskId)||StringUtil.isNullOrBlank(taskUserName)||StringUtil.isNullOrBlank(taskMobile)){
                return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
            }
            appMoneyDetail.setTaskUsername(taskUserName);
            appMoneyDetail.setTaskMobile(taskMobile);
            return saveIncome(uId,Integer.parseInt(taskId),appMoneyDetail);
        }else {
            return Result.create(ResultCode.VALIDATE_ERROR).setMessage("参数错误");
        }
    }

    private Double getTotalAskMoney(Integer uId,Integer type){
        return getTotalAskMoney(uId,type,null);
    }

    private Double getTotalAskMoney(Integer uId,Integer type,Integer repayWay){
        IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
        moneyDetail.setType(type);
        moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
        if (repayWay!=null){
            moneyDetail.setRepayWay(repayWay);
        }
        moneyDetail.setUid(uId);
        Double totalAskMoney = findTotalAskMoney(moneyDetail);
        return totalAskMoney==null?0:totalAskMoney;
    }

    private Double getTotalActualMoney(Integer uId,Integer type){
        return getTotalActualMoney(uId,type,null);
    }

    private Double getTotalActualMoney(Integer uId,Integer type,Integer repayWay){
        IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
        moneyDetail.setType(type);
        moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
        if (repayWay!=null){
            moneyDetail.setRepayWay(repayWay);
        }
        moneyDetail.setUid(uId);
        Double totalActualMoney = findTotalActualMoney(moneyDetail);
        return totalActualMoney==null?0:totalActualMoney;
    }
}
