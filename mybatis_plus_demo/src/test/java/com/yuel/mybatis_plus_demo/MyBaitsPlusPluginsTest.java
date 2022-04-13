package com.yuel.mybatis_plus_demo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yuel.mybatis_plus_demo.mapper.UserMapper;
import com.yuel.mybatis_plus_demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBaitsPlusPluginsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testPage(){
        Page<User> page = new Page<>(1,3);
        userMapper.selectPage(page,null);
        System.out.println(page);
    }

}
