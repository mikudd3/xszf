package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.AdminMapper;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.LoginDto;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.service.AdminService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
@Log4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    //登录功能
    @Override
    public Result login(LoginDto loginDto, HttpServletRequest request) throws Exception {
        //1.根据名字查询数据库
        Admin admin = null;
        try {
            admin = adminMapper.selectByName(loginDto.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //2.判断admin是否未空，
        if (admin == null) {
            return Result.error("账号不存在");
        }
        //3.账号存在则比对密码
        if (!admin.getPassword().equals(loginDto.getPassword())) {
            return Result.error("密码错误");
        }
        //4.密码正确，返回正确信息,将用户信息放入session域中
        request.getSession().setAttribute("admin", admin.getId());
        return Result.success();
    }

    //分页查询
    @Override
    public PageResult getPage(AdminPageQueryDto adminPageQueryDto) throws Exception {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(adminPageQueryDto.getCurrentPage(), adminPageQueryDto.getPageSize());
        Page<Admin> adminPage = null;
        try {
            adminPage = adminMapper.pageQuery(adminPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + adminPage.toString());
        long total = adminPage.getTotal();
        log.info("输出的total为：" + total);
        List<Admin> records = adminPage.getResult();
        return new PageResult(total, records);
    }

    //添加
    @Override
    public Result add(Admin admin) throws Exception {
        //给其他属性赋值
        //默认新添加的员工状态为禁用
        admin.setZt(1);
        //设置创建时间
        admin.setCreateTime(new Date());
        //添加到数据库
        try {
            adminMapper.add(admin);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    // 根据id查询
    @Override
    public Result getByid(Integer id) throws Exception {
        Admin admin = null;
        try {
            admin = adminMapper.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(admin);
    }

    //更新数据
    @Override
    public void update(Admin admin) throws Exception {
        try {
            adminMapper.update(admin);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }
    //启用或禁用员工

    @Override
    public void changeStatus(Integer id, Integer status) throws Exception {
        try {
            adminMapper.changeStatus(id, status);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            adminMapper.deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }
}
