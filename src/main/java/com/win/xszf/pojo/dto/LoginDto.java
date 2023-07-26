package com.win.xszf.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录功能从前端接收数据
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto implements Serializable {
    private String username;
    private String password;
}
