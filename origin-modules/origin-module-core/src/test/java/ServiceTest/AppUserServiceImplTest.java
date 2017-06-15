package ServiceTest;

import com.origin.core.dto.AppMoneyDetailDTO;
import com.origin.core.dto.AppUserDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.dto.AppValidcodeDTO;
import com.origin.core.service.*;
import com.origin.core.util.StringUtil;
import com.origin.data.entity.IAppMoneyDetail;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserTask;
import com.origin.data.entity.IAppValidcode;
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

    @Autowired
    private AppValidcodeService appValidcodeService;

    @Before
    public void setUp() throws Exception {
    }

    //@Test
    public void findOne() throws Exception {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile("13611112222");
        assert appUserService.findFirst(appUser).getId()==2;

        IAppValidcode appValidcode = new AppValidcodeDTO();
        appValidcode.setMobile("13611114444");
        appValidcode.setValidcode("110110");
        appValidcode.setType(3);
        appValidcode.setStatus(1);
        System.out.println("renxinhua" + appValidcodeService.findFirst(appValidcode).getCreateDate());
    }

    //获取用户收入信息
    //@Test
    public void findUserIncome() throws Exception{
        Integer userId = 6;
        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setUid(userId);
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_WAIT);
        List<IAppMoneyDetail> appMoneyDetails = appMoneyDetailService.findIncomeInfo(appMoneyDetail);

        System.out.println("renxinhua size " +appMoneyDetails.size()+" status " + appMoneyDetails.get(1).getStatus()+
        " mobile "+appMoneyDetails.get(1).getAppUser().getMobile()+" uid "+appMoneyDetails.get(1).getAppUser().getId()
        +" tid "+appMoneyDetails.get(1).getAppTask().getId());
    }

    //完成任务提交审核
    //@Test
    public void updateFinishTask() throws Exception{
        Integer userId = 3;
        Integer taskId = 1;

        IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
        appMoneyDetail.setUid(userId);
        appMoneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        appMoneyDetail.setTaskUsername("sdffff");
        appMoneyDetail.setTaskMobile("13622215564");
        appMoneyDetailService.saveIncome(userId,taskId,appMoneyDetail);

    }

    //钱审核
    //@Test
    public void updateMoneyStatus() throws Exception{

        String mId = "22";
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
        appMoneyDetailService.updateAudit(appMoneyDetail);
    }

    //@Test
    public void testIntegerString() throws Exception{
        Integer i = 2;
        String s = "2";
        if (i.equals(Integer.parseInt(s))){
            System.out.println("renxinhua 1");
        }else {
            System.out.println("renxinhua 2");
        }
    }

    //通过任务id查找审批记录
    //@Test
    public void findTaskUser() throws Exception{
        String uid = "";
        String tid = "";
        String status = "2";
        IAppUserTask appUserTask = new AppUserTaskDTO();
        if(StringUtils.isNotBlank(uid)){
            appUserTask.setUid(Integer.parseInt(uid));
        }
        if(StringUtils.isNotBlank(tid)){
            appUserTask.setTid(Integer.parseInt(tid));
        }
        if(StringUtils.isNotBlank(status)){
            IAppMoneyDetail appMoneyDetail = new AppMoneyDetailDTO();
            appMoneyDetail.setStatus(Integer.parseInt(status));
            appUserTask.setAppMoneyDetail(appMoneyDetail);
        }
        List<IAppUserTask> appUserTasks = appUserTaskService.findTaskUserInfo(appUserTask);
        System.out.println("renxinhua size " +appUserTasks.size()+" status " + appUserTasks.get(1).getAppMoneyDetail().getStatus()+
                " tid "+appUserTasks.get(1).getTid());
    }

    @Test
    public void validateRegex() throws Exception{
        String  s = "123a435";
        String regex = "^\\d{6}$";
        System.out.println("renxinhua"+s.matches(regex));
    }

    //@Test
    public void findTotalMoney() throws Exception{
        Integer uid = 6 ;

        IAppMoneyDetail moneyDetail = new AppMoneyDetailDTO();
        moneyDetail.setType(IAppMoneyDetail.TYPE_BORROW);
        moneyDetail.setStatus(IAppMoneyDetail.STATUS_AUDIT_SUCCESS);
        moneyDetail.setUid(uid);
        Double borrow = appMoneyDetailService.findTotalActualMoney(moneyDetail);
        moneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
        Double repay = appMoneyDetailService.findTotalActualMoney(moneyDetail);
        moneyDetail.setType(IAppMoneyDetail.TYPE_WITHDRAW);
        Double withdraw = appMoneyDetailService.findTotalActualMoney(moneyDetail);
        moneyDetail.setType(IAppMoneyDetail.TYPE_INCOME);
        Double income = appMoneyDetailService.findTotalActualMoney(moneyDetail);
        income = (income==null?0:income);
        moneyDetail.setType(IAppMoneyDetail.TYPE_REPAY);
        moneyDetail.setRepayWay(IAppMoneyDetail.REPAY_WAY_BALANCE);
        Double repayBalance = appMoneyDetailService.findTotalActualMoney(moneyDetail);

        IAppUser appUser = appUserService.findById(uid);
        System.out.println("renxinhua income "+income);
        Double balance = income - (withdraw==null?0:withdraw) - (repayBalance==null?0:repayBalance);
        appUser.setBalance(balance);
        appUserService.update(appUser);
        System.out.println("renxinhua "+balance);
    }
}