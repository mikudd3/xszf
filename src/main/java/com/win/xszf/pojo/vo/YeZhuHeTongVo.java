package com.win.xszf.pojo.vo;

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
public class YeZhuHeTongVo implements Serializable {
    private Integer id;
    //合同文件
    private String text;
    //房名
    private String zfid;
    //状态
    private Integer status;

}
