package ServiceTest;

import com.origin.core.dto.AppUserDTO;
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
        List<IAppUserTask> appUserTasks = appUserTaskService.findTaskUserByTaskId(Integer.parseInt("1"));
        System.out.println("licheng size " +appUserTasks.size()+" status "+appUserTasks.get(1).getStatus() +
        " mobile "+appUserTasks.get(1).getAppUser().getMobile());
    }
}