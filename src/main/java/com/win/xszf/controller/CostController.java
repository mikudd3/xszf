package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.CostPageQueryDto;
import com.win.xszf.pojo.vo.FeiYonVo;
import com.win.xszf.service.CostService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController //responnody + controller
@RequestMapping("/cost")
@Log4j
public class CostController {
    @Autowired
    private CostService costService;

    //作为房东时分页查询
    @PostMapping("/fdpage")
    public Result getPage(@RequestBody CostPageQueryDto costPageQueryDto) {
        log.info("输入的分页信息为：" + costPageQueryDto);
        PageResult page = null;
        try {
            page = costService.getfdPage(costPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //作为用户时分页查询
    @PostMapping("/yhPage")
    public Result userPage(@RequestBody CostPageQueryDto costPageQueryDto) {
        log.info("输入的分页信息为：" + costPageQueryDto);
        PageResult page = null;
        try {
            page = costService.getyhPage(costPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }


    @PostMapping("/tongji")
    public Result tongji(Integer house_id) {
        log.info("输入的分页信息为：" + house_id);
        Result page = null;
        try {
            page = costService.tongji(house_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return page;
    }


    //根据house_id查询
    @GetMapping("/select")
    public Result select(Integer house_id) {
        log.info("输入：" + house_id);
        Result result = null;
        try {
            result = costService.selectCost(house_id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }
    //获取当前季度的费用总和
    @PostMapping("/gettj")
    public Result gettj(Integer id){
        log.info("输入：" + id);
        Result result = null;
        try {
            result = costService.tongjitotal(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }
}
