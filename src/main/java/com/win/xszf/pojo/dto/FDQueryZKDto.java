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
public class FDQueryZKDto implements Serializable {
    private Integer currentPage;
    //当前页码
    private Integer pageSize;
    //登录的id
    private Integer id;
    //用户名
    private String username;
    //电话
    private String phone;


}
