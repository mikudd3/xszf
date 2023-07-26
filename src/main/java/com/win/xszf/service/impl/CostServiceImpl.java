package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.CostMapper;
import com.win.xszf.pojo.dto.CostPageQueryDto;
import com.win.xszf.pojo.entity.Cost;
import com.win.xszf.pojo.vo.FeiYonVo;
import com.win.xszf.pojo.vo.Tj_sumVo;
import com.win.xszf.service.CostService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
@Log4j
public class CostServiceImpl implements CostService {
    @Autowired
    private CostMapper costMapper;

    @Override
    public Result selectCost(Integer house_id) {
        ArrayList<Cost> costs = null;
        try {
            costs = costMapper.selectCost(house_id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(costs);
    }

    //当作为用户时分页查询
    @Override
    public PageResult getyhPage(CostPageQueryDto costPageQueryDto) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(costPageQueryDto.getCurrentPage(), costPageQueryDto.getPageSize());
        Page<FeiYonVo> costPage = null;
        try {
            costPage = costMapper.yHpageQuery(costPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + costPage.toString());
        long total = costPage.getTotal();
        log.info("输出的total为：" + total);
        List<FeiYonVo> records = costPage.getResult();
        for (FeiYonVo record : records) {
            Double allFee = record.getElectricity_fee() + record.getWater_fee()
                    + record.getRent_fee() + record.getService_fee();
            DecimalFormat df = new DecimalFormat("#.00");
            Double Fee = Double.valueOf(df.format(allFee));
            record.setAll_fee(Fee);
        }
        return new PageResult(total, records);
    }

    //当作为房东时分页查询
    @Override
    public PageResult getfdPage(CostPageQueryDto costPageQueryDto) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(costPageQueryDto.getCurrentPage(), costPageQueryDto.getPageSize());
        Page<FeiYonVo> costPage = null;
        try {
            costPage = costMapper.fdpageQuery(costPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的adminPage对象为：" + costPage.toString());
        long total = costPage.getTotal();
        log.info("输出的total为：" + total);
        List<FeiYonVo> records = costPage.getResult();
        for (FeiYonVo record : records) {
            Double allFee = record.getElectricity_fee() + record.getWater_fee()
                    + record.getRent_fee() + record.getService_fee();
            DecimalFormat df = new DecimalFormat("#.00");
            Double Fee = Double.valueOf(df.format(allFee));
            record.setAll_fee(Fee);
        }
        return new PageResult(total, records);
    }

    @Override
    public Result tongji(Integer id) {
        List<FeiYonVo> costPage = null;
        try {
            costPage = costMapper.tongji(id);
        } catch (SQLException e) {
            log.info("执行报错");
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        return Result.success(costPage);
    }

    @Override
    public Result tongjitotal(Integer id){
        List<Tj_sumVo> totalcost = null;
        try {
            totalcost = costMapper.tongjitotal(id);
        } catch (SQLException e) {
            log.info("执行报错");
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(totalcost);
    }
}
