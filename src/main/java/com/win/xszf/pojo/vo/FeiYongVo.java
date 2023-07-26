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
public class FeiYongVo implements Serializable {
    private Integer id;
    //房号
    private Integer house_id;
    //地址
    private Integer address;
    //季度
    private String season;
    //水费
    private Double water_fee;
    //电费
    private Double electricity_fee;
    //租金
    private Double rent_fee;
    //维修费用
    private Double service_fee;
    //总费用
    private Double all_fee;
    //状态
    private Integer status;

}
