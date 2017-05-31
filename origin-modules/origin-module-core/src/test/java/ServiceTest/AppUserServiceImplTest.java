package ServiceTest;

import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppMoneyDetailService;
import com.origin.core.service.AppTaskService;
import com.origin.core.service.AppUserService;
import com.origin.core.service.AppUserTaskService;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppUser;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lc on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppUserServiceImplTest {

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private AppUserTaskService appUserTaskService;

    @Autowired
    private AppTaskService appTaskService;

    @Autowired
    private AppMoneyDetailService appMoneyDetailService;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile("13611112222");
        appUser.setPwd("123456");
        //assert appUserService.findFirst(appUser).getId()==1;
    }

    //获取用户收入信息
    @Test
    public void findUserIncome() throws Exception{
        Integer userId = 6;
        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setUid(userId);
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
        List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.findIncomeInfo(appMoneyDetail);

        System.out.println("licheng size " +appMoneyDetails.size()+" status " + appMoneyDetails.get(1).getStatus()+
        " mobile "+appMoneyDetails.get(1).getAppUser().getMobile()+" uid "+appMoneyDetails.get(1).getAppUser().getId()
        +" tid "+appMoneyDetails.get(1).getAppTask().getId());
    }

    //完成任务提交审核
    @Test
    public void updateFinishTask() throws Exception{
        Integer userId = 3;
        Integer taskId = 5;

        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setUid(userId);
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetailService.saveIncome(userId,taskId,appMoneyDetail);

    }

    //钱审核
    @Test
    public void updateMoneyStatus() throws Exception{

        String mId = "13";
        String moneyActual = "100";
        String status = "2";

        if (StringUtil.isNullOrBlank(mId)||StringUtil.isNullOrBlank(status)){
            return;
        }
        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setId(Integer.parseInt(mId));
        appMoneyDetail.setStatus(Integer.parseInt(status));
        if (StringUtils.isNotBlank(moneyActual)){
            appMoneyDetail.setMoneyActual(Double.parseDouble(moneyActual));
        }
        appMoneyDetailService.update(appMoneyDetail);
    }

    @Test
    public void testIntegerString() throws Exception{
        Integer i = 2;
        String s = "2";
        if (i.equals(Integer.parseInt(s))){
            System.out.println("licheng 1");
        }else {
            System.out.println("licheng 2");
        }
    }
}