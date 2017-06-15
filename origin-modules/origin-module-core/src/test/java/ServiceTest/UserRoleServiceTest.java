package ServiceTest;

import com.origin.core.dto.UserRoleDTO;
import com.origin.data.entity.IUserRole;
import com.origin.data.mybatis.dao.UserRoleDao;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mybatis.xml")
public class UserRoleServiceTest  extends TestCase{
	@Autowired
	UserRoleDao<IUserRole,Integer> userRoleDao;
    private final static Logger logger = LoggerFactory.getLogger(UserRoleServiceTest.class);
    //@Test
    public void saveBatch(){
    	List<IUserRole> ts = new ArrayList<IUserRole>();
    	IUserRole urd = new UserRoleDTO();
    	urd.setUserId(14);
    	urd.setRoleId(11);
    	ts.add(urd);
    	urd = new UserRoleDTO();
    	urd.setUserId(10);
    	urd.setRoleId(13);
    	ts.add(urd);
    	
    	userRoleDao.saveBatch(ts);
    }
    //@Test
    public void deleteBatch(){
    	List<IUserRole> ts = new ArrayList<IUserRole>();
    	IUserRole urd = new UserRoleDTO();
    	urd.setUserId(14);
    	urd.setRoleId(11);
    	ts.add(urd);
    	urd = new UserRoleDTO();
    	urd.setUserId(14);
    	urd.setRoleId(13);
    	ts.add(urd);
    	userRoleDao.deleteBatch(ts);
    }
    //@Test
    public void delete(){
    	IUserRole urd = new UserRoleDTO();
    	urd.setUserId(12);
    	urd.setRoleId(11);
    	userRoleDao.deleteEntity(urd);
    }

    @Test
	public void test(){
	}
}
