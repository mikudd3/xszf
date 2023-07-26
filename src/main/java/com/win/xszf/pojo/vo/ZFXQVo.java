package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 租房详情Vo
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZFXQVo implements Serializable {
    //租房id
    private Integer id;
    //详细地址
    private String address;
    //租金
    private Double basemoney;
    //押金
    private Double yajin;
    //房东名字
    private String fdm;
    //合约期
    private String daoqi;
    //状态
    private Integer status;
}
