package com.origin.core.service.impl;


import com.origin.common.constants.ResultCode;
import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.data.dao.IAppMoneyDetailDao;
import com.origin.data.dao.IAppTaskDao;
import com.origin.data.dao.IAppUserTaskDao;
import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppTask;
import com.origin.data.entity.IAppUserTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppMoneyDetailServiceImpl  implements AppMoneyDetailService {

    @Autowired
    private IAppMoneyDetailDao<IAppMoneyDetail,Integer> appMoneyDetailDao;

    @Autowired
    private IAppTaskDao<IAppTask,Integer> appTaskDao;

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
    public Result saveIncome(Integer uid, Integer tid,IAppMoneyDetail appMoneyDetail) {
        IAppTask appTask = appTaskDao.findByPK(tid);
        Integer taskNumber = appTask.getTaskNumber();
        String taskName = appTask.getTaskName();
        Double taskMoney = appTask.getTaskMoney();
        if (taskNumber<1){
            System.out.println("number 小于1");
            return Result.create(ResultCode.SERVICE_ERROR).setMessage("任务数量没了");
        }
        //任务数量减一
        appTask.setTaskNumber(taskNumber-1);
        appTaskDao.update(appTask);

        //增加收入记录
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setExtensionOne(taskName);
        appMoneyDetail.setMoneyAsk(taskMoney);
        appMoneyDetailDao.save(appMoneyDetail);
        System.out.println("money id "+appMoneyDetail.getId());
        Integer mid = appMoneyDetail.getId();

        //中间关系表添加记录
        IAppUserTask appUserTask = new AppUserTaskDTO();
        appUserTask.setUid(uid);
        appUserTask.setTid(tid);
        appUserTask.setMid(mid);
        appUserTaskDao.save(appUserTask);
        return Result.createSuccessResult().setMessage("收入提交成功");
    }
}
