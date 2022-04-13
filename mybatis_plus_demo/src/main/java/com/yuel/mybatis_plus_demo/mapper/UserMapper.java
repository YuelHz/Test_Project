package com.yuel.mybatis_plus_demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuel.mybatis_plus_demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);
}
