package com.origin.core.service.impl;


import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppMessageDTO;
import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.core.util.JPushUtil;
import com.origin.core.util.StringUtil;
import com.origin.data.dao.*;
import com.origin.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppMoneyDetailServiceImpl  implements AppMoneyDetailService {

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
    public IAppMoneyDetail findFirst(IAppMoneyDetail appMoneyDetail) {
        return appMoneyDetailDao.findFirst(appMoneyDetail);
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
    public Result saveIncome(Integer uid, Integer tid,IAppMoneyDetail appMoneyDetail) {
        IAppTask appTask = appTaskDao.findByPK(tid);
        Integer taskNumber = appTask.getTaskNumber();
        String taskName = appTask.getTaskName();
        Double taskMoney = appTask.getTaskMoney();
        Date taskEndTime = appTask.getTaskEndTime();
        Date nowTime = new Date();
        //判断数量
        if (taskNumber<1){
            System.out.println("renxinhua number 小于1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务数量没了");
        }

        //判断任务时间
        System.out.println("renxinhua nowtime = "+ nowTime + " endtime = "+ taskEndTime);
        if (nowTime.after(taskEndTime)){
            System.out.println("renxinhua 任务过期");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务过期");
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
            System.out.println("renxinhua 重复提交1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("重复提交");
        }
        appMoneyDetailDTO.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
        appUserTask.setAppMoneyDetail(appMoneyDetailDTO);
        appUserTasks = appUserTaskDao.findTaskUserInfo(appUserTask);
        if (appUserTasks.size()>0){
            System.out.println("renxinhua 重复提交2");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("重复提交");
        }

        //任务数量减一
        appTask.setTaskNumber(taskNumber-1);
        appTaskDao.update(appTask);

        //增加收入记录
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setTaskName(taskName);
        appMoneyDetail.setMoneyAsk(taskMoney);
        appMoneyDetailDao.save(appMoneyDetail);
        System.out.println("money id "+appMoneyDetail.getId());
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

    //审核同时 修改用户余额
    @Override
    public void updateAudit(IAppMoneyDetail appMoneyDetail,String messageContent) {
        Integer mid = appMoneyDetail.getId();
        Integer status = appMoneyDetail.getStatus();
        Double moneyActual = appMoneyDetail.getMoneyActual();
        Double moneyAsk = appMoneyDetail.getMoneyAsk();
        Integer moneyType = appMoneyDetail.getType();
        Integer uid = appMoneyDetail.getUid();
        appMoneyDetailDao.update(appMoneyDetail);
        //审核通过修改用户余额
        if (IAppMoneyDetail.STATUS_AUDIT_SUCCESS.equals(status)){
            appMoneyDetail = appMoneyDetailDao.findByPK(mid);
            if (IAppMoneyDetail.TYPE_REPAY.equals(appMoneyDetail.getType())){
                if (IAppMoneyDetail.REPAY_WAY_BALANCE.equals(appMoneyDetail.getRepayWay())){
                    updateUserBalance(appMoneyDetail,false);
                }
            }else if (IAppMoneyDetail.TYPE_WITHDRAW.equals(appMoneyDetail.getType())){
                updateUserBalance(appMoneyDetail,false);
            }else if (IAppMoneyDetail.TYPE_INCOME.equals(appMoneyDetail.getType())){
                updateUserBalance(appMoneyDetail,true);
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
    }

    //flag true:增加 false:减少
    private void updateUserBalance(IAppMoneyDetail appMoneyDetail,boolean flag) {
        Integer uid = appMoneyDetail.getUid();
        Double moneyActual = appMoneyDetail.getMoneyActual();
        IAppUser appUser = appUserDao.findByPK(uid);
        Double balance = appUser.getBalance();
        if (flag){
            balance = balance + moneyActual;
        }else{
            balance = balance - moneyActual;
        }
        appUser.setBalance(balance);
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
}
