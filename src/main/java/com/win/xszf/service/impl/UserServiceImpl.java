package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.HouseMapper;
import com.win.xszf.mapper.UserMapper;
import com.win.xszf.mapper.UserZmclMapper;
import com.win.xszf.pojo.dto.FDQueryZKDto;
import com.win.xszf.pojo.dto.UserPageQueryDto;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.pojo.entity.House;
import com.win.xszf.pojo.entity.User;
import com.win.xszf.pojo.entity.UserZmcl;
import com.win.xszf.pojo.vo.YZSHVo;
import com.win.xszf.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Property;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserZmclMapper userZmclMapper;
    @Autowired
    private HouseMapper houseMapper;

    public Result userAdd(User user) {
        User u = null;
        try {
            u = userMapper.selectByUsername(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (u != null) {
            return Result.error("该用户名已被使用");
        }
        //设置用户身份，刚注册的用户身份默认为用户
        user.setYhsf(0);
        //设置账号状态，默认为0启用
        user.setZt(0);
        //设置创建时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String d = sdf.format(date);
        user.setCreateTime(d);
        boolean ret = false;
        try {
            ret = userMapper.userAdd(user) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (ret) {
            return Result.success(user);
        }
        return Result.error("注册失败");
    }

    public Result selectAll(User user) {
        ArrayList<User> users = null;
        try {
            users = userMapper.selectByUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(users);
    }

    @Override
    public PageResult getPage(UserPageQueryDto userPageQueryDto) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(userPageQueryDto.getCurrentPage(), userPageQueryDto.getPageSize());
        Page<User> userPage = null;
        try {
            userPage = userMapper.pageQuery(userPageQueryDto.getUser());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + userPage.toString());
        long total = userPage.getTotal();
        log.info("输出的total为：" + total);
        List<User> records = userPage.getResult();
        return new PageResult(total, records);
    }

    @Override
    public Result userUpdate(User user) {
        boolean ret = false;
        try {
            ret = userMapper.userUpdate(user) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (ret) {
            return Result.success(user);
        }
        return Result.error("更新失败");
    }

    @Override
    public Result userDelete(Integer id) {
        boolean ret = false;
        try {
            ret = userMapper.userDel(id) > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (ret) {
            return Result.success("删除成功");
        }
        return Result.error("删除失败");
    }

    @Override
    public Result login(User user, HttpServletRequest request) {
        User u = null;
        try {
            u = userMapper.selectLogin(user);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        if (u == null) {
            return Result.error("用户不存在");
        }
        //2.用户存在则比对密码
        if (!user.getPassword().equals(u.getPassword())) {
            return Result.error("密码错误");
        }
        //密码正确，则将u的id存到session域中
        request.getSession().setAttribute("user", u.getId());
        return Result.success(u);
    }

    //从session中获取用户对象
    @Override
    public Result getUser(HttpServletRequest request) {
        Integer userId = (Integer) request.getSession().getAttribute("user");
        //判断userid是否未空
        if (userId == null) {
            return Result.error("你还未登录，请登录");
        }
        //根据id获取用户对象
        User user = null;
        try {
            user = userMapper.getByid(userId);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(user);
    }

    //用户申请成为房东分页查询
    @Override
    public PageResult userShPage(Integer currentPage, Integer pageSize, String username) {
        PageHelper.startPage(currentPage, pageSize);
        Page<User> userPage = null;
        try {
            userPage = userMapper.UserShpageQuery(username);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + userPage.toString());
        long total = userPage.getTotal();
        log.info("输出的total为：" + total);
        List<User> records = userPage.getResult();
        return new PageResult(total, records);
    }

    @Override
    public Result getUserShVo(Integer id) {
        //根据id获取用户信息
        User user = null;
        try {
            user = userMapper.getByid(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        YZSHVo yzshVo = new YZSHVo();
        BeanUtils.copyProperties(user, yzshVo);
        //根据用户id查询证明材料
        List<String> zmcl = null;
        try {
            zmcl = userZmclMapper.selectZmclByid(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //将它放入yzshVo中
        yzshVo.setZmcl(zmcl);
        return Result.success(yzshVo);
    }

    //通过id改变用户身份
    @Override
    public void changeYhsf(Integer id, int i) {
        try {
            userMapper.changeYhsf(id, i);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }

    @Override
    public PageResult getPage1(FDQueryZKDto fdQueryZKDto) {
        PageHelper.startPage(fdQueryZKDto.getCurrentPage(), fdQueryZKDto.getPageSize());
        Page<User> userPage = null;
        try {
            userPage = userMapper.fdQueryZk(fdQueryZKDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + userPage.toString());
        long total = userPage.getTotal();
        log.info("输出的total为：" + total);
        List<User> records = userPage.getResult();
        return new PageResult(total, records);
    }

    //添加证明材料
    @Override
    public Result addZmcl(String zmcl, HttpServletRequest request) {
        //获取登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("user");
        if (userId == null) {
            return Result.error("添加失败，请先登录");
        }

        if (zmcl == null) {
            return Result.error("请先上传图片");
        }
        UserZmcl userZmcl = new UserZmcl();
        userZmcl.setUser_id(userId);
        userZmcl.setZmcl(zmcl);
        try {
            userZmclMapper.add(userZmcl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //添加成功之后将user的yhsf置为2
        try {
            userMapper.changeYhsf(userId, 2);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    //根据房子id获取用户对象
    @Override
    public Result getUserByHouseId(Integer id) {
        House houseId = null;
        try {
            houseId = houseMapper.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //根据房东id获取房东信息
        User user = null;
        try {
            user = userMapper.getByid(houseId.getUserId());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(user);
    }


}
