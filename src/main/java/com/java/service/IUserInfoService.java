package com.java.service;

import com.java.domain.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface IUserInfoService {
    /**
     * 插入用户访问信息
     * @param userInfo
     */
    void insertUserInfo(UserInfo userInfo);

    /**
     * 根据cookie查询访问信息
     * @param cookie
     * @return
     */
    List<UserInfo> findByCookie(String cookie);

    /**
     * 根据id来修改访问结束时间以及访问页面的时长
     * @param eTime
     * @param time
     * @param id
     */
    void updateETime(Date eTime, String time,  Long id);

    /**
     * 记录访问结束时间以及访问页面的时长
     */
    void recordETimeInfo(HttpServletRequest request, String cookieName);
}
