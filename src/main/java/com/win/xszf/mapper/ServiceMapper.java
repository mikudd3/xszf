package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.ServicePageQueryDto;
import com.win.xszf.pojo.dto.ServicegetdataDto;
import com.win.xszf.pojo.entity.Service;
import com.win.xszf.pojo.vo.WeiXiuVo;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

@Mapper
public interface ServiceMapper {
    /*增加维修表记录
    参数: Service实例（id为null，数据库有自增）
    返回：布尔类型*/
    @Insert("insert into service(zfid,type,status,money,date,set_date) values (#{zfid},#{type},#{status},#{money},#{date},#{set_date})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Boolean addService(Service service) throws SQLException;

    /*删除维修表记录
    参数: Integer（通过id删除）
    返回：布尔类型*/
    Boolean deleteServiceBy_id(Integer integer) throws SQLException;

    /*修改维修表记录
    参数: Service实例（通过id去确定修改）
    返回：布尔类型*/
    Boolean updataService(WeiXiuVo weiXiuVo) throws SQLException;

    /*查找维修表记录
    参数: Service实例（以成员变量为条件）
    返回：Service容器*/
    ArrayList<Service> selectService(Service service) throws SQLException;

    //拿房子地址及租房id
    Page<ServicegetdataDto> getdata(ServicegetdataDto servicegetdataDto) throws SQLException;

    Page<ServicegetdataDto> getdata1(ServicegetdataDto servicegetdataDto) throws SQLException;


    //分页查询
    Page<WeiXiuVo> pageQuery(ServicePageQueryDto servicePageQueryDto)throws SQLException;

    Page<WeiXiuVo> fdpageQuery(ServicePageQueryDto servicePageQueryDto)throws SQLException;

    @Select("select sum(money) from sercice where zfid =#{id} and status = 2 and date > #{date}}")
    Double weixiuzd(@Param("id") Integer id, @Param("date") String date)throws SQLException;
}
