package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.AdminPageQueryDto;
import com.win.xszf.pojo.dto.HeTongPageQueryDot;
import com.win.xszf.pojo.entity.Admin;
import com.win.xszf.pojo.entity.Hetong;
import com.win.xszf.pojo.entity.User;
import com.win.xszf.pojo.vo.HeTongVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.SQLException;

@Mapper
public interface HeTongMapper {


    /*
    * 房东合同分页查询
    * */
    Page<HeTongVo> selectYz(HeTongPageQueryDot heTongPageQueryDot)throws SQLException;

    /*
    *租客通过租房id查询合同
    * */
    Page<Hetong> selectZk(Integer id)throws SQLException;

    /*
    * 通过合同id返回合同
    * */
    HeTongVo yzgethetong(Integer id)throws SQLException;

    //添加合同
    public void insert(Hetong hetong)throws SQLException;

    //更新合同
    public Boolean updata(String zt)throws SQLException;

    @Insert("insert into hetong(text,zfid,zt,createTime) values (#{text},#{zfid},#{zt},#{createTime})")
    void add(Hetong hetong) throws SQLException;
}
