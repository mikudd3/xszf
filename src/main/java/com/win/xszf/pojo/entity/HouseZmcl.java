package com.win.xszf.pojo.entity;

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
public class HouseZmcl implements Serializable {
    private Integer id;
    private Integer house_id;
    private String zmcl;
}
