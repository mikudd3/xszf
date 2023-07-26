package com.win.xszf.service;

import com.win.xszf.common.PageResult;
import com.win.xszf.pojo.dto.ServicePageQueryDto;
import com.win.xszf.pojo.dto.ServicegetdataDto;
import com.win.xszf.pojo.entity.Service;
import com.win.xszf.pojo.vo.WeiXiuVo;

public interface ServiceService {
    //分页查询
    PageResult getPage(ServicePageQueryDto servicePageQueryDto)throws Exception;
    PageResult getPage1(ServicePageQueryDto servicePageQueryDto)throws Exception;

    Boolean updata(WeiXiuVo weiXiuVo)throws Exception;

    Boolean add(ServicegetdataDto servicegetdataDto)throws Exception;

    PageResult getdata(ServicegetdataDto servicegetdataDto)throws Exception;
    PageResult getdata1(ServicegetdataDto servicegetdataDto)throws Exception;
}
