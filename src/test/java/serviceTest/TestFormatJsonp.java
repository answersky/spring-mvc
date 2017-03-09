package serviceTest;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.Gson;
import com.java.dao.UserDao;
import com.java.domain.User;
import com.java.service.IFormatJsonpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuf on 2016/7/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class TestFormatJsonp {
    @Resource
    private IFormatJsonpService formatJsonpService;
    @Resource
    private UserDao userDao;

    @Test
    public void testgetJsonp(){
        String callback="list";
        List<User> list=userDao.findUsers();
        Gson gson=new Gson();
        String object= gson.toJson(list);
        String jsonp=formatJsonpService.getJsonpData(callback,3, object, 5);
        System.out.println(jsonp);
    }
}
