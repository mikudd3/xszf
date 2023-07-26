package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WYZFDto implements Serializable {
    //id
    private Integer id;
    //user_id
    private Integer user_id;
    //house_id
    private Integer house_id;
    //用户名
    private String username;
    //真实姓名
    private String realname;
    //性别
    private String sex;
    //联系电话
    private String phone;
    //联系邮箱
    private String email;
    //身份证
    private String sfz;
    //租期(单位月)
    private Integer zfqx;
    //几人入住
    private Integer people;

}
