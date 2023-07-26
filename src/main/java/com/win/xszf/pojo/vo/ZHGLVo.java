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
public class ZHGLVo implements Serializable {
    private Integer id;
    //用户名
    private String username;
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
    //头像
    private String image;

}
