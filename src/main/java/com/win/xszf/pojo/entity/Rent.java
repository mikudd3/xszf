package com.win.xszf.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @project: xszf租房信息表
 * @author: Win
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Rent implements Serializable {
    private Integer id;         //租房主键
    private Integer user_id;    //租客
    private Integer house_id;   //房号
    private String zfrq;        //入住日期
    private Integer zfqx;        //租房期限
    private Integer people;     //入住人数
    private Integer zt;          //状态
    private String no;         //订单号
    private Integer agree;      //是否同意用户的租房请求（0同意，1不同意）
    private String createTime;  //创建时间
    //到期时间
    private String daoqi;

}
