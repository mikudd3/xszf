package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WHGLVo implements Serializable {
    private Integer id;
    //维护类型
    private String whlx;
    //房子地址
    private String fzdz;
    //说明
    private String sm;
    //姓名
    private String name;
    //联系方式
    private String phone;
    //申请日期
    private String sqrq;
    //维修状态
    private Integer wxzt;
    //处理日期
    private Date clrq;

}
