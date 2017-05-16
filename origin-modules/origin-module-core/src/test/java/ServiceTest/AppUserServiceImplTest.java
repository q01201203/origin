package ServiceTest;

import com.origin.core.dto.AppUserDTO;
import com.origin.core.service.AppUserService;
import com.origin.data.entity.IAppUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lc on 2017/5/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class AppUserServiceImplTest {

    @Autowired
    private AppUserService appUserService;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void findOne() throws Exception {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile("13611112222");
        appUser.setPwd("123456");
        assert appUserService.findOne(appUser)==true;
    }

}