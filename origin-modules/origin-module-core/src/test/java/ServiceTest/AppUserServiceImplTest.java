package ServiceTest;

import com.origin.core.dto.AppUserDTO;
import com.origin.core.dto.AppUserTaskDTO;
import com.origin.core.service.AppUserService;
import com.origin.core.service.AppUserTaskService;
import com.origin.data.entity.IAppUser;
import com.origin.data.entity.IAppUserTask;
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

    @Test
    public void findUserTask() throws Exception{
        IAppUserTask appUserTask = new AppUserTaskDTO();
        appUserTask.setTid(1);
        List<IAppUserTask> appUserTasks = appUserTaskService.findTaskUserInfo(appUserTask);
        System.out.println("licheng size " +appUserTasks.size()+" status "+appUserTasks.get(1).getStatus() +
        " mobile "+appUserTasks.get(1).getAppUser().getMobile());
    }

    @Test
    public void updateFinishTask() throws Exception{
        IAppUserTask appUserTask = new AppUserTaskDTO();
        appUserTask.setUid(6);
        appUserTask.setTid(3);
        appUserTaskService.updateTaskSuccess(appUserTask);
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