package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 房子审核分页查询dto
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FZSHPageQueryDto implements Serializable {
    private Integer currentPage;
    //当前页码
    private Integer pageSize;
    private String house_no;
    private Integer zfzz;
}
