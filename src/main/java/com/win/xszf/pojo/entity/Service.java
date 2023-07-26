package com.win.xszf.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @project: xszf维修表
 * @author: Win
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Service implements Serializable {
    private Integer id;         //维护信息表
    private Integer zfid;       //租房id
    private String type;        //维修类型
    private String status;      //维修状态
    private Double money;       //维修费用
    private String date;        //维修日期
    private String set_date;    //报修日期
}
