package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HouseListVo implements Serializable {
    //id
    private Integer id;
    //房子编号
    private String house_no;
    //房子图片
    private String image;
    //地址
    private String address;
    //房子类型
    private Integer type;
    //房子面积
    private Double area;
    //房主名字
    private String realname;
    //门牌号
    private String mph;
    //房子配套
    private List<String> house_add;
    //房子朝向
    private String toward;
    //房子价格
    private Double basemoney;
}
