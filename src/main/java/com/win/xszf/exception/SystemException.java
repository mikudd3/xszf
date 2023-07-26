package com.win.xszf.exception;

import lombok.Data;

/**
 * @project:
//自定义异常处理器，用于封装异常信息，对异常进行分类
 * @author: mikudd3
 * @version: 1.0
 */

@Data
public class SystemException extends RuntimeException {
    public SystemException(String message) {
        super(message);
    }
}
