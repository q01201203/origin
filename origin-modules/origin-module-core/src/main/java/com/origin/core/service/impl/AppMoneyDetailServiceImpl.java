package com.origin.core.service.impl;


import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.data.dao.IAppMoneyDetailDao;
import com.origin.data.dao.IAppTaskDao;
import com.origin.data.dao.IAppUserDao;
import com.origin.data.dao.IAppUserTaskDao;
import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserTask;
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
            System.out.println("licheng number 小于1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务数量没了");
        }

        //判断任务时间
        System.out.println("licheng nowtime = "+ nowTime + " endtime = "+ taskEndTime);
        if (nowTime.after(taskEndTime)){
            System.out.println("licheng 任务过期");
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
            System.out.println("licheng 重复提交1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("重复提交");
        }
        appMoneyDetailDTO.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
        appUserTask.setAppMoneyDetail(appMoneyDetailDTO);
        appUserTasks = appUserTaskDao.findTaskUserInfo(appUserTask);
        if (appUserTasks.size()>0){
            System.out.println("licheng 重复提交2");
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
    public void updateAudit(IAppMoneyDetail appMoneyDetail) {
        Integer mid = appMoneyDetail.getId();
        appMoneyDetailDao.update(appMoneyDetail);
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
}
