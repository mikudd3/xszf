package com.win.xszf.controller;

import com.github.pagehelper.Page;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.HeTongPageQueryDot;
import com.win.xszf.pojo.dto.ScHeTongDto;
import com.win.xszf.pojo.entity.Hetong;
import com.win.xszf.pojo.entity.Rent;
import com.win.xszf.pojo.vo.HeTongVo;
import com.win.xszf.service.HeTongService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Log4j
@RestController
@RequestMapping("/hetong")
public class HeTongController {
    @Autowired
    HeTongService heTongService;

    //房东合同分页查询
    @PostMapping("/page")
    public Result getPage(@RequestBody HeTongPageQueryDot heTongPageQueryDot) {
        log.info("输入的分页信息为：" + heTongPageQueryDot);
        PageResult page = null;
        try {
            page = heTongService.getPage(heTongPageQueryDot);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //租客合同查询
    @PostMapping("/zkgethetong")
    public Result zkgethetong(@RequestBody HeTongVo id) {
        Page<Hetong> ht = null;
        try {
            ht = heTongService.fkgetHetong(id.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(ht);
    }

    //房东取合同
    @PostMapping("/yzgethetong")
    public Result yzgethetong(@RequestBody HeTongVo heTongVo) {
        HeTongVo ht = null;
        try {
            ht = heTongService.yzgetHetong(heTongVo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(ht);
    }

    @PostMapping("/scheTong")
    public Result scheTong(@RequestBody ScHeTongDto scHeTongDto) {
        log.info(scHeTongDto);
        Result result = null;
        try {
            result = heTongService.scheTong(scHeTongDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


}
