package com.win.xszf.pojo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostPageQueryDto implements Serializable {
    //当前页面
    private Integer currentPage;
    //当前页码
    private Integer pageSize;
    //返回的季度 season
    private String season;
    //返回的状态 zt
    private String zt;
    //返回的房子no house_no
    private String house_no;

    private String house_id;
    //用户登录id
    private Integer id;
    private String address;
}
