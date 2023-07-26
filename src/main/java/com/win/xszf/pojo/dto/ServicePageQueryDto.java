package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicePageQueryDto  implements Serializable {
    //当前页面
    private Integer currentPage;
    //当前页码
    private Integer pageSize;

    //维修状态
    private String status;

    //租房id
    private String zfid;

    //用户id
    private Integer id;

    //地址
    private String address;

}
