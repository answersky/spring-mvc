package com.java.service;

import com.java.domain.User;

import java.util.List;

/**
 * Created by liuf on 2016/7/3.
 */
public interface UserService {
    public User findUserById(int id);

    List<User> findUsers();
}
