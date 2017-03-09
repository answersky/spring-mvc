package com.java.dao;

import com.java.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/17.
 */
public interface IUserInfoDao {
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
    void updateETime(@Param("eTime")Date eTime,@Param("time") String time,@Param("id") Long id);
}
