package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.IndexHouseListDto;
import com.win.xszf.pojo.dto.WYZFPageDto;
import com.win.xszf.pojo.entity.House;
import com.win.xszf.pojo.vo.FZSHVo;
import com.win.xszf.pojo.vo.FZTGVo;
import com.win.xszf.pojo.vo.WYZFVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HouseMapper {
    //添加房子
    boolean add(House house) throws SQLException;;

    //房子推广分页查询
    Page<FZTGVo> fztgPageQuery(String house_no) throws SQLException;

    House getById(Integer id) throws SQLException;

    @Update("update house set sort = #{sort} where id = #{id}")
    void updateHouseSort(@Param("id") Integer id, @Param("sort") Integer sort) throws SQLException;

    //房子审核分页
    Page<FZSHVo> FzShSelect(@Param("zfzz") Integer zfzz, @Param("house_no") String house_no) throws SQLException;

    //获取审核的证明材料
    FZSHVo getfzshVo(Integer id) throws SQLException;

    //改变房子审核状态
    @Update("update house set zfzz = #{zfzz} where id = #{id}")
    void changZfzz(@Param("id") Integer id, @Param("zfzz") Integer zfzz) throws SQLException;

    @Select("select id from house where house_no = #{houseNo}")
    Integer selectByfzbh(String houseNo) throws SQLException;

    //房子管理分页查询
    Page<House> yzPage(@Param("address") String address, @Param("user_id") Integer user_id) throws SQLException;

    @Update("update house set zt = #{status} where id = #{id}")
    void xjOrSjHouse(@Param("id") Integer id, @Param("status") Integer status) throws SQLException;;

    //更新房子信息
    void update(House house) throws SQLException;


    List<House> getHouseList(IndexHouseListDto indexHouseListDto) throws SQLException;

    //根据房子id获取房子的配套信息
    @Select("select house_add from house where id = #{id}")
    String getHouseAddBYId(Integer id) throws SQLException;

    @Select("select `condition` from house where id = #{id}")
    Integer selectConditionById(Integer id)throws SQLException;

    //总要租房界面的全部房子信息展示
    Page<WYZFVo> WYZFPage(WYZFPageDto wyzfPageDto)throws SQLException;
}
