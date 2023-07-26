package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HeTongPageQueryDot {
    //当前页面
    private Integer currentPage;
    //当前页码
    private Integer pageSize;

    //返回的userid
    private Integer id;
}
