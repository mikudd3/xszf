package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServicegetdataDto implements Serializable {
    //传进用户id与传出房子id
    private Integer id;


    private Integer house_id;


    //地址
    private String address;
    //维修项目
     private String type;
    //图片路径
    private String photo;

}
