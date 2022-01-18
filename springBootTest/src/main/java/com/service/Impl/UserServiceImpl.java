package com.service.Impl;

import com.entity.UserInfo;
import com.mapper.UserMapper;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserInfo> queryAllUserList() {
       return   userMapper.selectList(null);
    }
}
