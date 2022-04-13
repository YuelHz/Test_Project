package com.yuel.mybatis_plus_demo;


import com.yuel.mybatis_plus_demo.mapper.UserMapper;
import com.yuel.mybatis_plus_demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
//        List<User> users = userMapper.selectList(null);
//        users.forEach(System.out::println);
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }

    @Test
    public void testInsert(){
        User user = new User(null, "zhangsan", 18, "zhangsan@test",null);
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("id:"+user.getId());
    }

    @Test
    public void testDelete(){
//        int i = userMapper.deleteById(1514071900265766914L);
        Map<String, Object> map = new HashMap<>();
        map.put("name","zhangsan");
        map.put("age",18);
        int result = userMapper.deleteByMap(map);
    }
}
