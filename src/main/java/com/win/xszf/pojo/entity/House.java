package com.win.xszf.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @project: xszf 房子信息
 * @author: Win
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class House implements Serializable {
    private Integer id;                      //房子主键
    private String address;             //地址(城市）
    private String mph;             //门牌号
    private Integer type;                     //房子类型
    private Double basemoney;                //租金
    private Double yajin;                    //押金(押一付一等)
    private Integer condition;                //出租状态（已出租，未出租）（展示界面仅有未出租的）
    private Integer userId;                //所属房东
    private Double area;                    //面积
    private String house_no;                //房子编号
    private String toward;                   //朝向
    private String statement;                //出租要求or说明
    private Integer floor;                   //楼层
    private Integer fwzx;                     //房屋装修（例：0精装，1简装）
    private Integer zfzz;                     //房子资质(0未审核，1审核中，2审核未通过，3审核通过）
    private Integer zt;                       //状态
    private String title;                    //标题
    private String house_add;               //房子配套
    //排序
    private Integer sort;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
}
