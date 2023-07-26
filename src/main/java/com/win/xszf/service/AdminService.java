package com.win.xszf.service;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.LoginDto;
import com.win.xszf.pojo.entity.Admin;

import javax.servlet.http.HttpServletRequest;

public interface AdminService {

    Result login(LoginDto loginDto, HttpServletRequest request) throws Exception;

    //分页查询
    PageResult getPage(AdminPageQueryDto adminPageQueryDto) throws Exception;

    //添加员工
    Result add(Admin admin) throws Exception;

    //根据id查询
    Result getByid(Integer id) throws Exception;

    //更新操作
    void update(Admin admin) throws Exception;

    //启用或禁用员工
    void changeStatus(Integer id, Integer status) throws Exception;

    //根据id删除
    void deleteById(Integer id) throws Exception;
}
