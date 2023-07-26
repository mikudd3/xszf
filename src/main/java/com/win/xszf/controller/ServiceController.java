package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.CostPageQueryDto;
import com.win.xszf.pojo.dto.ServicePageQueryDto;
import com.win.xszf.pojo.dto.ServicegetdataDto;
import com.win.xszf.pojo.entity.Service;
import com.win.xszf.pojo.vo.WeiXiuVo;
import com.win.xszf.service.CostService;
import com.win.xszf.service.ServiceService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@Log4j
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    //租客分页查询
    @PostMapping("/page")
    public Result getPage(@RequestBody ServicePageQueryDto servicePageQueryDto) {
        log.info("输入的分页信息为：" + servicePageQueryDto);
        PageResult page = null;
        try {
            page = serviceService.getPage(servicePageQueryDto);
            log.info(page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //业主分页查询
    @PostMapping("/page1")
    public Result getPage1(@RequestBody ServicePageQueryDto servicePageQueryDto) {
        log.info("输入的分页信息为：" + servicePageQueryDto);
        PageResult page = null;
        try {
            page = serviceService.getPage1(servicePageQueryDto);
            log.info(page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //房东更改维修信息(状态，费用，维修日期)
    @PostMapping("/updata")
    public Result updata(@RequestBody WeiXiuVo weiXiuVo) {

        try {
            Boolean ret = serviceService.updata(weiXiuVo);
            if (ret) {
                return Result.success();
            } else {
                return Result.error("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
    }

    //获取用户id下的租房信息(租房id和地址)放入下拉列表
    @PostMapping("/gethousedata")
    public Result getdata(@RequestBody ServicegetdataDto servicegetdataDto) {
        PageResult page = null;
        try {
            page = serviceService.getdata(servicegetdataDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }


    @PostMapping("/gethousedata1")
    public Result getdata1(@RequestBody ServicegetdataDto servicegetdataDto) {
        PageResult page = null;
        try {
            page = serviceService.getdata1(servicegetdataDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //租客新增维修信息
    @PostMapping("/add")
    public Result add(@RequestBody ServicegetdataDto servicegetdataDto) {
        try {
            Boolean ret = serviceService.add(servicegetdataDto);
            if (ret) {
                return Result.success();
            } else {
                return Result.error("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }

    }
}
