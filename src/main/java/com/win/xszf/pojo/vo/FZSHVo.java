package com.win.xszf.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 房子审核vo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FZSHVo implements Serializable {
    //房子id
    private Integer id;
    //用户名
    private String username;
    //申请人真实姓名
    private String realname;
    //身份证
    private String sfz;
    //电话
    private String phone;
    //邮箱
    private String email;
    //审核进度
    private Integer status;
    //申请时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    //地址
    private String address;
    //房子编号
    private String house_no;
    //图片
    private String image;
    //房子资质
    private Integer zfzz;
    //证明材料
    private String zmcl;

}
