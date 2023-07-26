package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.FDQueryZKDto;
import com.win.xszf.pojo.dto.UserPageQueryDto;
import com.win.xszf.pojo.entity.User;
import com.win.xszf.service.UserService;
import com.win.xszf.service.impl.UserServiceImpl;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //用户登录
    @RequestMapping("/login")
    public Result userLogin(User user, HttpServletRequest request) {
        Result result = null;
        try {
            result =userService.login(user, request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;

    }

    //用户注册
    @RequestMapping("/add")
    public Result userAdd(User user) {
        Result result = null;
        try {
            result = userService.userAdd(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


    @RequestMapping("/all")
    public Result selectAll(User user) {
        Result result = null;
        try {
             result = userService.selectAll(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


    //管理员分页查询所有用户
    @PostMapping("/page")
    public Result getPage(@RequestBody UserPageQueryDto userPageQueryDto) {
        log.info("输入的分页信息为：" + userPageQueryDto);
        PageResult page = null;
        try {
            page = userService.getPage(userPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }

    //房东分页查询自己的租客
    @PostMapping("/page1")
    public Result getPage1(@RequestBody FDQueryZKDto fdQueryZKDto) {
        log.info("输入的分页信息为：" + fdQueryZKDto);
        PageResult page = null;
        try {
            page = userService.getPage1(fdQueryZKDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(page);
    }


    //修改用户信息
    @RequestMapping("/update")
    public Result userUpdate(@RequestBody User user) {
        Result result = null;
        try {
            result = userService.userUpdate(user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;

    }

    //管理员删除用户
    @RequestMapping("/delete")
    public Result userDelete(Integer id) {
        Result result = null;
        try {
            result = userService.userDelete(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;

    }

    //从session中获取用户对象
    @GetMapping("/getUser")
    public Result getUser(HttpServletRequest request) {
        Result result = null;
        try {
            result = userService.getUser(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


    // 查询用户申请成为房东审核
    @PostMapping("/userShPage")
    public Result userShPage(@RequestBody Map<String, Object> map) {
        Integer currentPage = (Integer) map.get("currentPage");
        Integer pageSize = (Integer) map.get("pageSize");
        String username = (String) map.get("username");
        PageResult pageResult = null;
        try {
            pageResult = userService.userShPage(currentPage, pageSize, username);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(pageResult);
    }

    //获取用户的申请信息
    @GetMapping("/getUserShVo")
    public Result getUserShVo(Integer id) {
        Result result = null;
        try {
            result = userService.getUserShVo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //用户通过审核
    @PostMapping("/UserPass")
    public Result UserPass(Integer id) {

        try {
            userService.changeYhsf(id, 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //拒绝用户请求
    @PostMapping("/UserRefuse")
    public Result UserRefuse(Integer id) {
        try {
            userService.changeYhsf(id, 0);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    @PostMapping("/addZmcl")
    public Result addZmcl(@RequestBody String zmcl, HttpServletRequest request) {
        Result result = null;
        try {
            result = userService.addZmcl(zmcl, request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //根据房子id获取用户
    @GetMapping("/getUserByHouseId")
    public Result getUserByHouseId(Integer id) {
        Result result = null;
        try {
            result = userService.getUserByHouseId(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;

    }


}
