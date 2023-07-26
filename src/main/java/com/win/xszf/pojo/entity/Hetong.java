package com.win.xszf.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @project: xszf 合同
 * @author: Win
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Hetong implements Serializable {
    private Integer id;                     //主键(合同)
    private String text;                    //电子合同（文件）
    private Integer zfid;                   //租房表的id
    private Integer zt;                      //状态
    private String createTime;
}
