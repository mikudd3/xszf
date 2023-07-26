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
public class HouseImagesVo implements Serializable {
    //id
    private Integer id;
    //房子title
    private String title;
    //房子的图片
    private String image;
}
