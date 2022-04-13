package com.yuel.mybatis_plus_demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.yuel.mybatis_plus_demo.mapper.UserMapper;
import com.yuel.mybatis_plus_demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01(){
        //查询用户名包含a  年龄在20到30之间  邮箱不为null的用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","a")
                        .between("age",20,30)
                                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test02(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    @Test
    public void test03(){
        //删除邮箱为null的用户信息
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int delete = userMapper.delete(queryWrapper);
        System.out.println("result:"+delete);

    }

    @Test
    public void test04(){
        //将（年龄大于20且用户名中包含有a） 或者邮箱为null的用户信息修改
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("user_name","a")
                .or()
                .isNull("email");
        User user = new User();
        user.setName("小明");
        user.setEmail("yuel@emil");
        userMapper.update(user,queryWrapper);

    }

    @Test
    public void test05(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user = new User();
        user.setName("小红");
        user.setEmail("yuel@qq.com");
        int update = userMapper.update(user, queryWrapper);

    }

    @Test
    public void test06(){
        //查询用户的用户名  年龄  邮箱
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id","select id from t_user where id<=100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test08(){
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("user_name","a")
                .and(i ->i.gt("age",20).or().isNull("email"));
        userUpdateWrapper.set("user_name","小黑").set("email","yuel");
        int update = userMapper.update(null, userUpdateWrapper);

    }

    @Test
    public void test09(){
        String username ="";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            queryWrapper.like("user_name",username);
        }
        if (ageBegin != null){
            //大于等于
            queryWrapper.ge("age",ageBegin);
        }
        if (ageEnd != null){
            //小于等于
            queryWrapper.le("age",ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test10(){
        String username ="";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                .ge(ageBegin != null,"age",ageBegin)
                .le(ageEnd != null,"age",ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);

    }

    @Test
    public void test11(){
        String username ="";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin != null,User::getAge,ageBegin)
                .le(ageEnd != null,User::getAge,ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);

    }
}
