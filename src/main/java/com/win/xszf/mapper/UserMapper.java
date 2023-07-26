package com.win.xszf.mapper;


import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.FDQueryZKDto;
import com.win.xszf.pojo.dto.UserPageQueryDto;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.pojo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;

@Mapper
public interface UserMapper {

    int userAdd(User user)throws SQLException;


    //根据id删除用户
    @Delete("delete from user where id = #{id}")
    int userDel(Integer id)throws SQLException;


    //修改用户信息
    int userUpdate(User user)throws SQLException;

    //根据用户名查找用户
    @Select("select * from user where username = #{username}")
    User selectByUsername(User user)throws SQLException;

    @Select("select * from user where username = #{username}")
    User selectByName(String username)throws SQLException;


    //动态sql查询
    ArrayList<User> selectByUser(User user)throws SQLException;

    Page<User> pageQuery(User user)throws SQLException;


    @Select("select * from user where username = #{username}")
    User selectLogin(User user)throws SQLException;

    @Select("select * from user where id = #{userId}")
    User getByid(Integer userId)throws SQLException;

    //用户申请成为房东分页查询
    Page<User> UserShpageQuery(String username)throws SQLException;

    @Update("update user set yhsf = #{i} where id = #{id}")
    void changeYhsf(@Param("id") Integer id, @Param("i") int i)throws SQLException;

    Page<User> fdQueryZk(FDQueryZKDto fdQueryZKDto)throws SQLException;
}
