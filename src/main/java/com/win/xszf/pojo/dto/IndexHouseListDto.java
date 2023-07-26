package com.win.xszf.pojo.dto;

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
public class IndexHouseListDto implements Serializable {
    //房子id
    private Integer id;
    //前置区间
    private Double fromPrice;
    //后置区间
    private Double toPrice;
    //房子类型
    private Integer type;
    //区间字符串
    private String range;
    //朝向
    private String toward;

}
