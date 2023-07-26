package com.win.xszf.pojo.vo;

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
public class SHZFVo implements Serializable {

    //id
    private Integer id;
    //姓名
    private String realname;
    //性别
    private String sex;
    //身份证
    private String sfz;
    //联系方式
    private String phone;
    //邮箱
    private String email;
    //地址
    private String address;
    //门牌号
    private String mph;
    //租房期限
    private Integer zfqx;
    //几人住
    private Integer people;

}
