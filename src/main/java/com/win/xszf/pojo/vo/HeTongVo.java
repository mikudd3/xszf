package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 合同vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeTongVo implements Serializable {
    //合同id
    private Integer id;
    //合同文件
    private String text;
    //房子编号
    private String house_no;
    //房子地址
    private String address;
    //状态
    private Integer zt;
}
