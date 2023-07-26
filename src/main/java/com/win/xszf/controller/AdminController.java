package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.LoginDto;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.service.AdminService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@RequestMapping("/admin")
@Log4j
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登录功能
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        log.info("输入的登录信息为：" + loginDto);
        Result result = null;
        try {
            result = adminService.login(loginDto,request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //分页查询
    @PostMapping("/page")
    public Result getPage(@RequestBody AdminPageQueryDto adminPageQueryDto) {
        log.info("输入的分页信息为：" + adminPageQueryDto);
        PageResult page = null;
        try {
            page = adminService.getPage(adminPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //添加员工
    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) {
        log.info("添加信息为：" + admin);
        Result result = null;
        try {
            result = adminService.add(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //根据id查询
    @GetMapping("/selectById")
    public Result selectByid(Integer id) {
        log.info("获取的id为:" + id);
        Result result = null;
        try {
            result = adminService.getByid(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody Admin admin) {
        log.info("更新数据为：" + admin);
        try {
            adminService.update(admin);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //启用或禁用员工
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        log.info("输入id为：" + id + "输入状态为：" + status);
        try {
            adminService.changeStatus(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //根据id删除
    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        log.info("输入id为：" + id);
        try {
            adminService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }
}
