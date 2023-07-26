package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.RentMapper;
import com.win.xszf.pojo.dto.RentPageQueryDto;
import com.win.xszf.pojo.dto.WYZFDto;
import com.win.xszf.pojo.entity.Rent;
import com.win.xszf.pojo.vo.SHZFVo;
import com.win.xszf.pojo.vo.ZFMsgVo;
import com.win.xszf.pojo.vo.ZFXQVo;
import com.win.xszf.pojo.vo.ZFXXVo;
import com.win.xszf.service.RentService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
@Log4j
public class RentServiceImpl implements RentService {

    @Autowired
    private RentMapper rentMapper;

    public Result addRent(Rent rent) {
        try {
            rentMapper.addRent(rent);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    /*修改租房表记录
    参数: Rent实例（通过id去确定修改）
    返回：布尔类型*/
    public Result updataRent(Rent rent) {
        try {
            rentMapper.updataRent(rent);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    @Override
    public PageResult getPage(RentPageQueryDto rentPageQueryDto) {
        //开始分页查询
        //select * from rent limit 0,5
        PageHelper.startPage(rentPageQueryDto.getCurrentPage(), rentPageQueryDto.getPageSize());
        Page<ZFXXVo> rentPage = null;
        try {
            rentPage = rentMapper.pageQuery(rentPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的rentPage对象为：
        log.info("输出的rentPage对象为：" + rentPage.toString());
        long total = rentPage.getTotal();
        log.info("输出的total为：" + total);
        List<ZFXXVo> records = rentPage.getResult();
        return new PageResult(total, records);
    }

    @Override
    public PageResult getPageXQ(RentPageQueryDto rentPageQueryDto) {
        //开始分页查询
        //select * from rent limit 0,5
        PageHelper.startPage(rentPageQueryDto.getCurrentPage(), rentPageQueryDto.getPageSize());

        Page<ZFXQVo> rentXqPage = null;
        try {
            rentXqPage = rentMapper.pageXQQuery(rentPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的rentPage对象为：
        log.info("输出的rentPage对象为：" + rentXqPage.toString());
        //获取当前时间
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s2 = sdf.format(d1);
        //获取返回的时间
        List<ZFXQVo> list = rentXqPage.getResult();
        for (ZFXQVo vo : list) {
            String s1 = vo.getDaoqi();
            //两个时间进行比较
            if (s1 != null && s1.compareTo(s2) > 0) {
                //大于当前时间
                //给状态设置成1在期
                vo.setStatus(1);
            } else {
                //小于当前时间
                //给状态设置成0过期
                vo.setStatus(0);
            }
        }
        long total = rentXqPage.getTotal();
        log.info("输出的total为：" + total);
        List<ZFXQVo> records = rentXqPage.getResult();
        return new PageResult(total, records);
    }

    //我要租房
    @Override
    public Result wyzf(WYZFDto wyzfDto) {
        log.info(wyzfDto);
        //向租房表中添加租房数据
        Rent rent = new Rent();
        BeanUtils.copyProperties(wyzfDto, rent);
        log.info(rent);
        //设置是否同意租房为不同意，默认为不同意
        rent.setAgree(1);
        //设置创建时间
        rent.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        //向数据库中添加数据
        try {
            rentMapper.add(rent);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();

    }

    // 业主查看谁要租房
    @Override
    public Result shzf(Integer id) {
        List<SHZFVo> shzfVos = null;
        try {
            shzfVos = rentMapper.selectShZF(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(shzfVos);
    }

    //同意出租
    @Override
    public Result tycz(Integer id) {
        //先根据id查询租房表
        Rent rent = null;
        try {
            rent = rentMapper.selectRentById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //设置其他属性
        //2.1设置入住日期
        Date date = new Date();
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        rent.setZfrq(outputFormat.format(date));
        //2.2 设置租房表状态
        rent.setZt(0);
        //设置订单号
        rent.setNo(UUID.randomUUID().toString().replace("-", ""));
        //设置租房表到期时间
        // 创建一个Calendar实例
        Calendar calendar = Calendar.getInstance();
        // 将当前日期设置到Calendar中
        calendar.setTime(date);
        // 添加一个月
        calendar.add(Calendar.MONTH, rent.getZfqx());
        //设置到期时间
        rent.setDaoqi(outputFormat.format(calendar.getTime()));
        //修改状态
        rent.setAgree(0);
        try {
            rentMapper.updataRent(rent);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //更新表中信息
        //这里需要添加合同表操作
        return Result.success();
    }

    @Override
    public Result jjcz(Integer id) {
        try {
            rentMapper.deleteRentBy_id(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    //分批查账，用于每日插入rent表的账单
    @Override
    public PageResult fpcz(RentPageQueryDto rentPageQueryDto) {
        //开始分页查询
        //select * from rent limit 0,5
        PageHelper.startPage(rentPageQueryDto.getCurrentPage(), rentPageQueryDto.getPageSize());
        Page<Rent> rentPage = null;
        try {
            rentPage = rentMapper.fpcz(rentPageQueryDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的rentPage对象为：
        log.info("输出的rentPage对象为：" + rentPage.toString());
        long total = rentPage.getTotal();
        log.info("输出的total为：" + total);
        List<Rent> records = rentPage.getResult();
        return new PageResult(total, records);
    }

    //获取租房信息
    @Override
    public Result getZFMsg(Integer id) {
        List<ZFMsgVo> zfMsg = null;
        try {
            zfMsg = rentMapper.getZfMsg(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(zfMsg);
    }
}
