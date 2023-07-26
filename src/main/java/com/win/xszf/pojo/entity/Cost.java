package com.win.xszf.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @project: xszf 费用表
 * @author: Win
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cost implements Serializable {
    private Integer id;                     //主键id（费用）
    private Double water_fee;               //水费
    private Double electricity_fee;         //电费
    private Double rent_fee;                //租金
    private Double service_fee;             //维修费用
    private String season;                  //季度
    private Integer house_id;               //房子id
    private Integer zt;                     //状态
    private String createTime;
}
