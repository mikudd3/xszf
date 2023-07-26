package com.win.xszf.mapper;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;

@Mapper
public interface ServiceImageMapper {

    @Insert("insert into service_image(photo,sercive_id) values (#{photo},#{id})")
    Boolean addphoto(@Param("photo") String photo,@Param("id") Integer id) throws SQLException;
}
