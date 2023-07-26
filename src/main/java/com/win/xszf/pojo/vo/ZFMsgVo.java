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
public class ZFMsgVo implements Serializable {

    //租房id
    private Integer id;
    //租房期限
    private Integer zfqx;
    //租客名
    private String realname;
    //租客电话
    private String phone;
    //身份证
    private String sfz;
    //房子地址
    private String address;
    //门牌号
    private String mph;
}
