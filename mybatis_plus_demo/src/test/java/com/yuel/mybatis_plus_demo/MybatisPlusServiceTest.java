package com.yuel.mybatis_plus_demo;

import com.yuel.mybatis_plus_demo.pojo.User;
import com.yuel.mybatis_plus_demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数："+count);
    }

    @Test
    public void testInsertMore(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setName("zwn"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
