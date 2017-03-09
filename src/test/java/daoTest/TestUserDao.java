package daoTest;

import com.java.dao.UserDao;
import com.java.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuf on 2016/7/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class TestUserDao {
    @Resource
    private UserDao userDao;

    @Test
    public void insert(){
        User user=new User();
        user.setName("ww");
        user.setAge(20);
        userDao.insertUser(user);
    }

    @Test
    public void testFind(){
        User user=userDao.findUserById(7);
        System.out.println(user);
    }

    @Test
    public void findUsers(){
        List<User> list=userDao.findUsers();
        System.out.println(list);
    }
}

