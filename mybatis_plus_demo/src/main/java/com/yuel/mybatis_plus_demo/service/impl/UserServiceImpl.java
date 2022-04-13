package com.yuel.mybatis_plus_demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuel.mybatis_plus_demo.mapper.UserMapper;
import com.yuel.mybatis_plus_demo.pojo.User;
import com.yuel.mybatis_plus_demo.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
