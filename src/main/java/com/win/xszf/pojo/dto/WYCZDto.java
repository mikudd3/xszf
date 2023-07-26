package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 我要出租
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WYCZDto implements Serializable {
    //真实姓名
    private String username;
    //地址
    private String address;
    //门牌号
    private String mph;
    //房子类型 0,大别野;1,公寓;2,普通房子
    private Integer type;
    //租金
    private Double basemoney;
    //押金
    private Double yajin;
    //面积
    private Double area;
    //房子编号
    private String house_no;
    //房屋配套
    private List<String> house_add;
    //toward
    private String toward;
    //出租要求或说明
    private String statement;
    //楼层
    private Integer floor;
    //房屋装修(0，精装；1，简装）
    private Integer fwzx;
    //图片
    private List<String> image;
    //证明材料(图片）
    private String zmcl;


}
