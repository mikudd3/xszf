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
public class YzPageQueryDto implements Serializable {

    //当前页面
    private Integer currentPage;
    //当前页码
    private Integer pageSize;
    //地址
    private String address;
    private Integer user_id;
}
