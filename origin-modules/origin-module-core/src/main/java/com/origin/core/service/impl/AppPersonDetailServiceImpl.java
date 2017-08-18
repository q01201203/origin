package com.origin.core.service.impl;


import com.origin.common.model.mybatis.Result;
import com.origin.core.dto.AppPersonDetailDTO;
import com.origin.core.service.AppPersonDetailService;
import com.origin.data.dao.IAppPersonDetailDao;
import com.origin.data.entity.IAppPersonDetail;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AppPersonDetailServiceImpl implements AppPersonDetailService {

    @Autowired
    private IAppPersonDetailDao<IAppPersonDetail, Integer> appPersonDetailDao;

    @Override
    public void save(IAppPersonDetail appPersonDetail) {
        appPersonDetailDao.save(appPersonDetail);
    }

    @Override
    public void delete(Integer id) {
        appPersonDetailDao.delete(id);
    }

    @Override
    public void update(IAppPersonDetail appPersonDetail) {
        appPersonDetail.setUpdateDate(new Date());
        appPersonDetailDao.update(appPersonDetail);
    }

    @Override
    public IAppPersonDetail findById(Integer id) {
        return appPersonDetailDao.findByPK(id);
    }

    @Override
    public List<IAppPersonDetail> find(IAppPersonDetail appPersonDetail) {
        return appPersonDetailDao.find(appPersonDetail);
    }

    @Override
    public Result savePersonInfo(Integer uId, String infoCompanyName,String infoCompanyAddress,String infoQq,
                                 String infoWeixin,String infoHome,String infoEmycontactRelation,String infoEmycontactMobile,
                                 String infoContactRelation,String infoContactMobile) {
        boolean save = false;
        IAppPersonDetail appPersonDetail = new AppPersonDetailDTO();
        appPersonDetail.setUid(uId);
        List<IAppPersonDetail> appPersonDetails = appPersonDetailDao.find(appPersonDetail);
        if (appPersonDetails!=null&&appPersonDetails.size()>0){
            appPersonDetail = appPersonDetails.get(0);
        }else{
            appPersonDetail = null;
        }
        if (appPersonDetail == null){
            save = true;
            appPersonDetail = new AppPersonDetailDTO();
        }
		/*appPersonDetail.setInfoMobile(infoMobile);*/
        appPersonDetail.setInfoCompanyAddress(infoCompanyAddress);
        appPersonDetail.setInfoCompanyName(infoCompanyName);
        appPersonDetail.setInfoQq(infoQq);
        appPersonDetail.setInfoWeixin(infoWeixin);
        appPersonDetail.setInfoHome(infoHome);
        appPersonDetail.setInfoEmycontactRelation(Integer.parseInt(infoEmycontactRelation));
        appPersonDetail.setInfoEmycontactMobile(infoEmycontactMobile);
        appPersonDetail.setInfoContactRelation(Integer.parseInt(infoContactRelation));
        appPersonDetail.setInfoContactMobile(infoContactMobile);
        appPersonDetail.setUid(uId);
        if (save){
            appPersonDetailDao.save(appPersonDetail);
        }else {
            update(appPersonDetail);
        }
        return Result.createSuccessResult().setMessage("社会人群信息保存成功");
    }
}
