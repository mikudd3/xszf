package com.win.xszf.mapper;


import com.win.xszf.pojo.entity.UserZmcl;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserZmclMapper {

    @Select("select zmcl from user_zmcl where user_id = #{id}")
    List<String> selectZmclByid(Integer id) throws SQLException;

    @Insert("insert into user_zmcl(user_id,zmcl) values (#{user_id},#{zmcl})")
    void add(UserZmcl userZmcl) throws SQLException;
}
