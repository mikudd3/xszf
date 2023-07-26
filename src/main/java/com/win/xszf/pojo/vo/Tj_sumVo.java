package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
* 用来接各月份的总收入
* */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tj_sumVo {
    private String month;
    private Double total;
}
