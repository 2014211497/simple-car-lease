package com.yclin.simplecarlease.service;

import com.yclin.simplecarlease.mapper.UserMapper;
import com.yclin.simplecarlease.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LinYuchang
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public User getUserById(String id) {
        return userMapper.selectById(id);
    }
}
