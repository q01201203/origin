package ServiceTest;

import com.origin.common.model.mybatis.Result;
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
 * Created by 罗小雨丶 on 2017/5/11.
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
    public void login() throws Exception {
        IAppUser appUser = new AppUserDTO();
        appUser.setMobile("13611112222");
        appUser.setPwd("123456");
        Result result = appUserService.login(appUser);
        assert result.isSuccess()==true;
    }

}