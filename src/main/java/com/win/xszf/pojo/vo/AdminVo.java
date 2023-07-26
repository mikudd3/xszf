package com.win.xszf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import java.util.Date;

/**
 * 员工vo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVo implements Serializable {
    private Integer id;             //主键管理员id
    private String username;        //用户名
    private String password;        //密码
    private Integer zt;              //状态
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
