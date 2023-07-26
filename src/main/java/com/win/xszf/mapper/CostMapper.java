package com.win.xszf.mapper;

import com.github.pagehelper.Page;
import com.win.xszf.pojo.dto.CostPageQueryDto;
import com.win.xszf.pojo.entity.Cost;
import com.win.xszf.pojo.vo.FeiYonVo;
import com.win.xszf.pojo.vo.Tj_sumVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.xml.transform.Result;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CostMapper {

    //分页查询
    Page<FeiYonVo> yHpageQuery(CostPageQueryDto costPageQueryDto) throws SQLException;

    Page<FeiYonVo> fdpageQuery(CostPageQueryDto costPageQueryDto) throws SQLException;

    /**
     * 添加消费纪录
     */
    boolean addCost(Cost cost) throws SQLException;

    /**
     * 查找消费纪录
     * 条件：房子id，状态，季度
     */
    @Select("select * from cost where house_id = #{house_id}")
    ArrayList<Cost> selectCost(Integer house_id) throws SQLException;

    /**
     * 删除消费纪录
     */

    boolean deleteCost(Integer id) throws SQLException;

    /**
     * 更改消费纪录
     * 内容：房子id，状态，季度
     */
    boolean updateCost(Cost cost) throws SQLException;

    List<FeiYonVo> tongji(Integer id) throws SQLException;

    @Select("select DATE_FORMAT(cost.createTime, '%Y-%m') AS month,sum(cost.water_fee+cost.electricity_fee+cost.rent_fee+cost.service_fee) AS total from cost,house where cost.house_id = house.id and house.user_id =3 and cost.zt = 1 GROUP BY month LIMIT 6")
    List<Tj_sumVo> tongjitotal(Integer id) throws SQLException;
}
