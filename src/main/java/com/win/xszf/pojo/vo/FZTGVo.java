package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 房子推广vo
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FZTGVo implements Serializable {
    //房子id
    private Integer id;
    //房东名
    private String realname;
    //房子编号
    private String house_no;
    //排序字段
    private Integer sort;
}
