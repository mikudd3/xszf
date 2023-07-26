package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 个人资料vo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements Serializable {
    //id
    private Integer id;
    //用户名
    private String username;
    //真实姓名
    private String realname;
    //性别
    private String sex;
    //电话
    private String phone;
    //邮箱
    private String email;
    //身份证
    private String sfz;
    //头像
    private String image;
    //密码
    private String password;
    //用户身份
    private Integer yhsf;
    //账号状态
    private Integer status;


}
