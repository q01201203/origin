package com.origin.core.service.impl;


import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppStuDetailDTO;
import com.origin.core.service.AppStuDetailService;
import com.origin.data.dao.IAppStuDetailDao;
import com.origin.data.entity.IAppStuDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppStuDetailServiceImpl implements AppStuDetailService {

    @Autowired
    private IAppStuDetailDao<IAppStuDetail, Integer> appStuDetailDao;

    @Override
    public void save(IAppStuDetail appStuDetail) {
        appStuDetailDao.save(appStuDetail);
    }

    @Override
    public void delete(Integer id) {
        appStuDetailDao.delete(id);
    }

    @Override
    public void update(IAppStuDetail appStuDetail) {
        appStuDetail.setUpdateDate(new Date());
        appStuDetailDao.update(appStuDetail);
    }

    @Override
    public IAppStuDetail findById(Integer id) {
        return appStuDetailDao.findByPK(id);
    }

    @Override
    public List<IAppStuDetail> find(IAppStuDetail appStuDetail) {
        return appStuDetailDao.find(appStuDetail);
    }

    @Override
    public Result saveStudentInfo(Integer uId, String infoSchool, String infoDepartment, String infoClass,
                                  String infoRoomNumber, String infoEmycontactRelation, String infoEmycontactMobile,
                                  String infoContactRelation, String infoContactMobile) {
        boolean save = false;
        IAppStuDetail appStuDetail = new AppStuDetailDTO();
        appStuDetail.setUid(uId);
        List<IAppStuDetail> appStuDetails = appStuDetailDao.find(appStuDetail);
        if (appStuDetails!=null&&appStuDetails.size()>0){
            appStuDetail = appStuDetails.get(0);
        }else{
            appStuDetail = null;
        }
        if (appStuDetail == null){
            save = true;
            appStuDetail = new AppStuDetailDTO();
        }

        //appStuDetail.setInfoMobile(infoMobile);
        appStuDetail.setInfoSchool(infoSchool);
        appStuDetail.setInfoDepartment(infoDepartment);
        appStuDetail.setInfoClass(infoClass);
        appStuDetail.setInfoRoomnumber(infoRoomNumber);
        appStuDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
        appStuDetail.setInfoEmycontactMobile(infoEmycontactMobile);
        appStuDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
        appStuDetail.setInfoContactMobile(infoContactMobile);
        appStuDetail.setUid(uId);
        if (save){
            appStuDetailDao.save(appStuDetail);
        }else {
            update(appStuDetail);
        }
        return Result.createSuccessResult().setMessage("学生信息保存成功");
    }
}
