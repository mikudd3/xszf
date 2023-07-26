package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 房租vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeiYonVo implements Serializable {
    //id
    private Integer id;
    //水费
    private Double water_fee;
    //电费
    private Double electricity_fee;
    //租金
    private Double rent_fee;
    //维修费用
    private Double service_fee;
    //季度
    private String season;
    //地址
    private String address;
    //房子编号
    private String house_no;
    //总费用
    private Double all_fee;
    //状态
    private Integer zt;

}
