package com.win.xszf.mapper;

import com.win.xszf.pojo.entity.HouseZmcl;
import com.win.xszf.pojo.vo.LBTVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HouseZmclMapper {

    @Insert("insert into house_zmcl(house_id,zmcl) values (#{house_id},#{zmcl})")
    void add(HouseZmcl houseZmcl) throws SQLException;

    //查找证明材料
    @Select("select zmcl from house_zmcl where house_id = #{id}")
    List<String> selectHouseZmclById(Integer id) throws SQLException;

    @Delete("delete from house_zmcl where house_id = #{id}")
    void deleteByHouseId(Integer id) throws SQLException;

    @Select("select id,title from house order by sort limit 6 ")
    List<LBTVo> getLunBaoTu() throws SQLException;

}
