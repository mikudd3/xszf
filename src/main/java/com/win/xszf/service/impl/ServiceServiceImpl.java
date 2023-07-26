package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.ServiceImageMapper;
import com.win.xszf.mapper.ServiceMapper;
import com.win.xszf.pojo.dto.ServicePageQueryDto;
import com.win.xszf.pojo.dto.ServicegetdataDto;
import com.win.xszf.pojo.vo.WeiXiuVo;
import com.win.xszf.service.ServiceService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
@Log4j
public class ServiceServiceImpl implements ServiceService {
    @Autowired
    private ServiceMapper serviceMapper;

    @Autowired
    private ServiceImageMapper serviceImageMapper;

    //已租客的身份分页查询
    @Override
    public PageResult getPage(ServicePageQueryDto servicePageQueryDto) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(servicePageQueryDto.getCurrentPage(), servicePageQueryDto.getPageSize());
//        log.info(serviceMapper.pageQuery(servicePageQueryDto));
        Page<WeiXiuVo> weixiuPage = null;
        try {
            weixiuPage = serviceMapper.pageQuery(servicePageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        log.info(weixiuPage);
        //输出的weixiuPage对象为：
        log.info("输出的weixiuPage对象为：" + weixiuPage.toString());
        long total = weixiuPage.getTotal();
        log.info("输出的total为：" + total);
        List<WeiXiuVo> records = weixiuPage.getResult();
        return new PageResult(total, records);
    }


    //已房东的身份查询
    @Override
    public PageResult getPage1(ServicePageQueryDto servicePageQueryDto) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(servicePageQueryDto.getCurrentPage(), servicePageQueryDto.getPageSize());
//        log.info(serviceMapper.pageQuery(servicePageQueryDto));
        Page<WeiXiuVo> weixiuPage = null;
        try {
            weixiuPage = serviceMapper.fdpageQuery(servicePageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        log.info(weixiuPage);
        //输出的weixiuPage对象为：
        log.info("输出的weixiuPage对象为：" + weixiuPage.toString());
        long total = weixiuPage.getTotal();
        log.info("输出的total为：" + total);
        List<WeiXiuVo> records = weixiuPage.getResult();
        return new PageResult(total, records);
    }

    @Override
    public Boolean updata(WeiXiuVo weiXiuVo) {
        try {
            return serviceMapper.updataService(weiXiuVo);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }

    @Override
    public Boolean add(ServicegetdataDto servicegetdataDto) {
        com.win.xszf.pojo.entity.Service s = new com.win.xszf.pojo.entity.Service();
        s.setZfid(servicegetdataDto.getId());
        s.setType(servicegetdataDto.getType());
        s.setSet_date(String.valueOf(LocalDate.now()));
        s.setStatus("0");
        Boolean ret = null;
        try {
            ret = serviceMapper.addService(s);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (servicegetdataDto.getPhoto() != null) {
            //获取维修表id
            try {
                ret = serviceImageMapper.addphoto(servicegetdataDto.getPhoto(), s.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
        }
        return ret;
    }

    @Override
    public PageResult getdata(ServicegetdataDto servicegetdataDto) {
        Page<ServicegetdataDto> s = null;
        try {
            s = serviceMapper.getdata(servicegetdataDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        log.info("输出的s对象为：" + s.toString());
        long total = s.getTotal();
        log.info("输出的total为：" + total);
        List<ServicegetdataDto> records = s.getResult();
        return new PageResult(total, records);
    }

    @Override
    public PageResult getdata1(ServicegetdataDto servicegetdataDto) {
        Page<ServicegetdataDto> s = null;
        try {
            s = serviceMapper.getdata1(servicegetdataDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        log.info("输出的s对象为：" + s.toString());
        long total = s.getTotal();
        log.info("输出的total为：" + total);
        List<ServicegetdataDto> records = s.getResult();
        return new PageResult(total, records);
    }
}
