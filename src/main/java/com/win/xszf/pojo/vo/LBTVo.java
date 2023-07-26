package com.win.xszf.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LBTVo implements Serializable {

    //房子id
    private Integer id;
    //house的title
    private String title;
    //轮播图url
    private String url;
    //house的全部图片
    private List<String> image;

}
