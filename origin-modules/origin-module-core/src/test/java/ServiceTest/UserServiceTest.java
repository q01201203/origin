package ServiceTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.origin.data.mybatis.dao.UserDao;
import com.origin.core.dto.UserDTO;
import com.origin.data.entity.IUser;

import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-mybatis.xml")
public class UserServiceTest extends TestCase  {
	@Autowired
	UserDao<IUser,Integer> userDao;
    private final static Logger logger = LoggerFactory.getLogger(UserServiceTest.class);
    //@Test
	public void findFirst(){
		String username = "admin";
		UserDTO param = new UserDTO();
		param.setUsername(username);
		IUser user = userDao.findFirst(param);
		System.out.println("============"+user);
	}
    //@Test
    public void find(){
    	String username = "zj";
    	UserDTO param = new UserDTO();
    	param.setUsername(username);
    	List<IUser> lst = userDao.find(param);
    	System.out.println("============"+lst);
    }
    //@Test
    public void save(){
    	IUser user = new UserDTO();
    	user.setUsername("ceshi");
    	user.setPassword("123456");
    	user.setRealName("测试");
    	userDao.save(user);
    	System.out.println("============================"+user.getId());
    }
    //@Test
    public void update(){
    	IUser user = new UserDTO();
    	user.setId(2);
    	user.setUsername("add1");
    	user.setPassword("123456");
    	userDao.update(user);
    }
    @Test
    public void updatePks(){
    	List<Integer> pks = new ArrayList<Integer>();
    	pks.add(2);
    	Integer status = 0;
    	userDao.updatePks(pks, status);
    }
    //@Test
    public void delete(){
    	userDao.delete(22);
    }
}
