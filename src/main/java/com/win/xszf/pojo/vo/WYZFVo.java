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
public class WYZFVo implements Serializable {
    //id
    private Integer id;
    //房东名
    private String realname;
    //门牌号
    private String mph;
    //地址详细
    private String address;
    //租金
    private Double basemoney;
    //押金
    private Double yajin;
    //说明
    private String statement;

}
