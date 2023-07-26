package com.win.xszf.service;


import com.github.pagehelper.Page;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.HeTongPageQueryDot;
import com.win.xszf.pojo.dto.ScHeTongDto;
import com.win.xszf.pojo.entity.Hetong;
import com.win.xszf.pojo.vo.HeTongVo;

public interface HeTongService {

    Page<Hetong> fkgetHetong(Integer id) throws Exception;

    HeTongVo yzgetHetong(Integer id) throws Exception;

    PageResult getPage(HeTongPageQueryDot heTongPageQueryDot) throws Exception;

    //上传合同
    Result scheTong(ScHeTongDto scHeTongDto) throws Exception;
}
