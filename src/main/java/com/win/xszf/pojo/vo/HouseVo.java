package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @project: 房子管理
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseVo implements Serializable {
    //id
    private Integer id;
    //地址详细
    private String address;
    //门牌号
    private String mph;
    //房屋类型
    private Integer type;
    //租金
    private Double basemoney;
    //押金
    private Double yajin;
    //出租状态
    private Integer condition;
    //面积
    private Double area;
    //朝向
    private String toward;
    //出租要求
    private String statement;
    //楼层
    private Integer floor; //装修
    //装修情况0精装，1简装
    private Integer fwzx;
    //房子资质
    private Integer zfzz;
    //状态
    private Integer zt;
    //图片
    private List<String> image;
    //房子配套
    private List<String> house_add;
    //房子证明材料
    private List<String> zmcl;
    //标题
    private String title;

}
