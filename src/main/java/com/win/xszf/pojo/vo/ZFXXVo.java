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
public class ZFXXVo implements Serializable {
    private Integer id;
    //房号
    private String address;
    //状态
    private Integer status;
    //租户名
    private String zhm;


}
