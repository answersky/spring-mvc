package com.java.service.impl;

import com.java.dao.IUserInfoDao;
import com.java.domain.UserInfo;
import com.java.service.IUserInfoService;
import com.java.utils.CookieUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
@Service
public class UserInfoImpl implements IUserInfoService{
    @Resource
    private IUserInfoDao userInfoDao;

    public void insertUserInfo(UserInfo userInfo) {
        userInfoDao.insertUserInfo(userInfo);
    }

    public List<UserInfo> findByCookie(String cookie) {
        return userInfoDao.findByCookie(cookie);
    }

    public void updateETime(Date eTime, String time, Long id) {
        userInfoDao.updateETime(eTime,time,id);
    }

    public void recordETimeInfo(HttpServletRequest request,String cookieName) {
        //以当前时间为页面关闭时间
        Date eTime=new Date();
        String cookie= CookieUtils.getCookie(request,cookieName);
        List<UserInfo> list=userInfoDao.findByCookie(cookie);
        for(UserInfo userInfo:list){
            Long id=userInfo.getId();
            Date sTime=userInfo.getsTime();

            //访问页面的时间差
            String time=String.valueOf((eTime.getTime()-sTime.getTime())/1000);
            userInfoDao.updateETime(eTime,time,id);
        }
    }
}
