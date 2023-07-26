package com.win.xszf.pojo.dto;/**
 * @project: xszf
 * @author: Win
 * @version: 1.0
 */

import com.win.xszf.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @project:
 * @author: fqq
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPageQueryDto implements Serializable {
    //当前页面
    private Integer currentPage;
    //当前页码
    private Integer pageSize;

    //返回的user
    private User user;

    //登录的id
    private  Integer id;

}
