package com.yuel.mybatis_plus_demo.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("t_user")
public class User {
private Long id;
@TableField("user_name")
private String name;
private Integer age;
private String email;
@TableLogic
private Integer isDeleted;
}
