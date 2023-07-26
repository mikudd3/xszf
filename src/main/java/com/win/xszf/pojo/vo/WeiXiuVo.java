package com.win.xszf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 维修vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiXiuVo implements Serializable {
    //维修id
    private Integer id;
    //地址
    private String address;
    //项目
    private String type;
    //申请人
    private String realname;
    //电话
    private String phone;
    //维修费用
    private Double money;
    //维修日期
    private String date;
    //报修日期
    private String set_date;
    //维修状态
    private Integer status;
}
