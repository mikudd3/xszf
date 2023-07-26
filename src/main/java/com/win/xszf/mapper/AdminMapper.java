package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;


@Mapper
public interface AdminMapper {
    //查询全部
    @Select("select * from admin")
    Admin selectAll() throws SQLException;

    //根据用户名查询用户
    @Select("select * from admin where username = #{name}")
    Admin selectByName(String name) throws SQLException;

    //分页查询
    Page<Admin> pageQuery(AdminPageQueryDto adminPageQueryDto) throws SQLException;

    //增加
    public void add(Admin admin) throws SQLException;

    //根据id查询
    @Select("select * from admin where id = #{id}")
    Admin getById(Integer id) throws SQLException;

    //更新数据
    void update(Admin admin) throws SQLException;

    @Update("update admin set zt = #{status} where id = #{id}")
    void changeStatus(@Param("id") Integer id, @Param("status") Integer status) throws SQLException;

    @Delete("delete from admin where id = #{id}")
    void deleteById(Integer id) throws SQLException;
}
