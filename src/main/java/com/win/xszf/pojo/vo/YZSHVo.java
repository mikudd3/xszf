package com.win.xszf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class YZSHVo implements Serializable {

    private Integer id;         //主键（用户）
    private String username;    //用户名
    private String password;    //密码
    private String phone;       //电话
    private String sex;         //性别
    private String realname;    //姓名
    private String sfz;         //身份证
    private String tx;          //头像
    private Integer yhsf;        //用户身份
    private Integer zt;          //状态
    private String Email;       //邮箱
    private List<String> zmcl;
}
