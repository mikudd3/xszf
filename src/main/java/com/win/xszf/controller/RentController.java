package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.RentPageQueryDto;
import com.win.xszf.pojo.dto.WYZFDto;
import com.win.xszf.pojo.entity.Rent;
import com.win.xszf.service.RentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@RequestMapping("/rent")
@Log4j
public class RentController {
    @Autowired
    private RentService rentService;

    //查询
    @PostMapping("/page")
    public Result getPage(@RequestBody RentPageQueryDto rentPageQueryDto) {
        log.info("输入的分页信息为：" + rentPageQueryDto);
        PageResult page = null;
        try {
            page = rentService.getPage(rentPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    @PostMapping("/pageXQ")
    public Result getPageXQ(@RequestBody RentPageQueryDto rentPageQueryDto) {
        log.info("输入的分页信息为：" + rentPageQueryDto);
        PageResult page = null;
        try {
            page = rentService.getPageXQ(rentPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }


    //我要租房
    @PostMapping("/wyzf")
    public Result wyzf(@RequestBody WYZFDto wyzfDto) {
        log.info("租客信息" + wyzfDto);
        try {
            rentService.wyzf(wyzfDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }


    //业主查看谁要租房
    @GetMapping("/shfz")
    public Result ShZf(Integer id) {
        log.info("租客id:" + id);
        Result result = null;
        try {
            result = rentService.shzf(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //同意出租
    @PostMapping("/tycz")
    public Result tycz(Integer id) {
        log.info("同意租房id:" + id);
        Result result = null;
        try {
            result = rentService.tycz(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //拒绝租房请求
    @PostMapping("/jjcz")
    public Result jjcz(Integer id) {
        log.info("拒绝租房");
        Result result = null;
        try {
            result = rentService.jjcz(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    @GetMapping("/getZFMsg")
    public Result getZFMsg(Integer user_id) {
        Result result = null;
        try {
            result = rentService.getZFMsg(user_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }
}
