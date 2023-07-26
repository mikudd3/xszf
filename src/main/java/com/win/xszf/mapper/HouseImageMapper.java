package com.win.xszf.mapper;

import com.win.xszf.pojo.entity.HouseImage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;


@Mapper
public interface HouseImageMapper {


    @Insert("insert into house_image(house_id,image) values (#{house_id},#{image})")
    public void add(HouseImage image1)throws SQLException;


    //    List<HouseImage> selectByHouseId(Integer id);

    @Select(" select image from house_image where house_id = #{id}")
    List<String> selectByHouseId(Integer id)throws SQLException;

    @Delete("delete from house_image where house_id = #{id} ")
    void deleteByHouseId(Integer id)throws SQLException;
}
