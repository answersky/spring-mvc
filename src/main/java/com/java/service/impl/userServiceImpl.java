package com.java.service.impl;

import com.java.dao.UserDao;
import com.java.domain.User;
import com.java.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuf on 2016/7/3.
 */
@Service
public class userServiceImpl implements UserService{
    @Resource
    private UserDao userDao;


    public User findUserById(int id) {
        return userDao.findUserById(id);
    }

    public List<User> findUsers() {
        return userDao.findUsers();
    }
}
