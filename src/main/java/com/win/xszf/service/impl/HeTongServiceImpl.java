package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.HeTongMapper;
import com.win.xszf.pojo.dto.HeTongPageQueryDot;
import com.win.xszf.pojo.dto.ScHeTongDto;
import com.win.xszf.pojo.entity.Hetong;
import com.win.xszf.pojo.vo.HeTongVo;
import com.win.xszf.service.HeTongService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Service
@Log4j
public class HeTongServiceImpl implements HeTongService {
    @Autowired
    private HeTongMapper heTongMapper;


    //租客通过租房id拿合同
    @Override
    public Page<Hetong> fkgetHetong(Integer id) {
        Page<Hetong> hetongs = null;
        try {
            hetongs = heTongMapper.selectZk(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return hetongs;
    }

    @Override
    public HeTongVo yzgetHetong(Integer id) {
        HeTongVo yzgethetong = null;
        try {
            yzgethetong = heTongMapper.yzgethetong(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return yzgethetong;
    }


    @Override
    public PageResult getPage(HeTongPageQueryDot heTongPageQueryDot) {
        //开始分页查询
        //select * from admin limit 0,5
        PageHelper.startPage(heTongPageQueryDot.getCurrentPage(), heTongPageQueryDot.getPageSize());
        Page<HeTongVo> yzHeTongVoPage = null;
        try {
            yzHeTongVoPage = heTongMapper.selectYz(heTongPageQueryDot);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //输出的adminPage对象为：
        log.info("输出的yzHeTongVoPage对象为：" + yzHeTongVoPage.toString());
        long total = yzHeTongVoPage.getTotal();
        log.info("输出的total为：" + total);
        List<HeTongVo> records = yzHeTongVoPage.getResult();
        return new PageResult(total, records);
    }

    //上传合同
    @Override
    public Result scheTong(ScHeTongDto scHeTongDto) {
        Hetong h = new Hetong();
        h.setZfid(scHeTongDto.getZfid());
        h.setText(scHeTongDto.getImage());
        h.setZt(0);
        h.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        try {
            heTongMapper.add(h);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }
}
