package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.RentPageQueryDto;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.pojo.entity.Rent;
import com.win.xszf.pojo.vo.SHZFVo;
import com.win.xszf.pojo.vo.ZFMsgVo;
import com.win.xszf.pojo.vo.ZFXQVo;
import com.win.xszf.pojo.vo.ZFXXVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface RentMapper {
    //    Page<Rent> pageQuery(RentPageQueryDto rentPageQueryDto);
    /*增加租房表记录
    参数: Rent实例（id为null，数据库有自增）
    返回：布尔类型*/
    Boolean addRent(Rent rent) throws SQLException;

    /*删除租房表记录
    参数: Integer（通过id删除）
    返回：布尔类型*/
    Boolean deleteRentBy_id(Integer integer) throws SQLException;

    /*修改租房表记录
    参数: Rent实例（通过id去确定修改）
    返回：布尔类型*/
    void updataRent(Rent rent) throws SQLException;

    /*查找租房表记录
    参数: Rent实例（以成员变量为条件）
    返回：Rent容器*/
//    ArrayList<Rent> selectRent(Rent rent);

    Page<ZFXXVo> pageQuery(RentPageQueryDto rentPageQueryDto) throws SQLException;

    Page<ZFXQVo> pageXQQuery(RentPageQueryDto rentPageQueryDto) throws SQLException;

    //添加合同
    void add(Rent rent) throws SQLException;

    //查看租房信息
    List<SHZFVo> selectShZF(Integer id) throws SQLException;

    //根据id查询租房表
    @Select("select * from rent where id = #{id}")
    Rent selectRentById(Integer id) throws SQLException;

    Page<Rent> fpcz(RentPageQueryDto rentPageQueryDto) throws SQLException;

    //获取租房信息id
    List<ZFMsgVo> getZfMsg(Integer id) throws SQLException;
}
