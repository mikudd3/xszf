package com.win.xszf.service;


import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.*;

import javax.servlet.http.HttpServletRequest;

public interface HouseService {
    //添加数据
    void add(WYCZDto wyczDto, HttpServletRequest request)throws Exception;

    //房子推广page
    PageResult fztgPage(FZTGPageQueryDto fztgPageQueryDto)throws Exception;


    Result fztgGetById(Integer id)throws Exception;

    //更新房子排序
    Result updateHouseSort(UpdateHouseSortDto updateHouseSortDto)throws Exception;

    // 房子审核分页查询
    PageResult fzshPage(FZSHPageQueryDto fzshPageQueryDto)throws Exception;

    //获取审核的证明材料
    Result getfzshVo(Integer id)throws Exception;

    void changZfzz(Integer id, Integer zfzz)throws Exception;

    //业主房子管理分类信息
    PageResult YzPage(YzPageQueryDto yzPageQueryDto)throws Exception;

    //上架或下架房子
    Result xjOrSjHouse(Integer id, Integer status)throws Exception;

    //根据id获取房子信息
    Result getHouseByID(Integer id)throws Exception;

    Result updateHouse(HouseDto houseDto)throws Exception;

    Result getLunBaoHouse()throws Exception;

    //首页房子展示
    Result getHouseList(IndexHouseListDto indexHouseListDto)throws Exception;

    //房子详情页面处图片
    Result getHouseImageById(Integer id)throws Exception;

    //根据房子id获取房子配套信息
    Result getHouseAdd(Integer id)throws Exception;

    //我要租房展示
    Result getPage(WYZFPageDto wyzfPageDto)throws Exception;
}
