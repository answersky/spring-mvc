package com.java.dao;

import com.java.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:config/application.xml"})
public class IUserInfoDaoTest{
    @Resource
    private IUserInfoDao userInfoDao;

    @Test
    public void insertUserInfo() throws Exception {
        UserInfo userInfo=new UserInfo();
        userInfo.setIp("192.168.3.122");
        userInfo.setsTime(new Date());
        userInfo.setUrl("www.baidu.com");
        userInfoDao.insertUserInfo(userInfo);
    }

    @Test
    public void updateInfo(){
        Date eTime=new Date();
        String time="20";
        userInfoDao.updateETime(eTime,time,1l);
    }

}