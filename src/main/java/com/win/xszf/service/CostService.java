package com.win.xszf.service;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.CostPageQueryDto;
import com.win.xszf.pojo.entity.Cost;
import com.win.xszf.pojo.vo.FeiYonVo;

import java.util.ArrayList;

public interface CostService {

    /**
     * 查找消费纪录
     * 条件：房子id，状态，季度
     */
    Result selectCost(Integer house_id) throws Exception;

    /**
     * 删除消费纪录
     */
    /**
     * 更改消费纪录
     * 内容：房子id，状态，季度
     */


    //当作为用户时的分页查询
    PageResult getyhPage(CostPageQueryDto costPageQueryDto) throws Exception;

    //当作为房东时分页查询
    PageResult getfdPage(CostPageQueryDto costPageQueryDto) throws Exception;

    Result tongji(Integer id) throws Exception;

    Result tongjitotal(Integer id) throws Exception;
}
