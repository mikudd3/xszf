package com.win.xszf.service;


import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.FDQueryZKDto;
import com.win.xszf.pojo.dto.UserPageQueryDto;
import com.win.xszf.pojo.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    Result userAdd(User user)throws Exception;

    Result selectAll(User user)throws Exception;

    PageResult getPage(UserPageQueryDto userPageQueryDto)throws Exception;

    Result userUpdate(User user)throws Exception;

    Result userDelete(Integer id)throws Exception;

    Result login(User user, HttpServletRequest request)throws Exception;

    //从session中获取用户对象
    Result getUser(HttpServletRequest request)throws Exception;

    //用户申请成为房东分页查询
    PageResult userShPage(Integer currentPage, Integer pageSize, String username)throws Exception;


    //根据id获取用户审核信息
    Result getUserShVo(Integer id)throws Exception;

    //通过id改变用户身份
    void changeYhsf(Integer id, int i)throws Exception;

    //房东查询租客信息
    PageResult getPage1(FDQueryZKDto fdQueryZKDto)throws Exception;

    //添加证明材料
    Result addZmcl(String zmcl,HttpServletRequest request)throws Exception;

    //根据房子id获取用户
    Result getUserByHouseId(Integer id)throws Exception;
}
