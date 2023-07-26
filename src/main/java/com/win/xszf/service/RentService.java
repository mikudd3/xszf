package com.win.xszf.service;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.pojo.dto.RentPageQueryDto;
import com.win.xszf.pojo.dto.WYZFDto;
import com.win.xszf.pojo.entity.Rent;

import java.sql.SQLException;


public interface RentService {

    PageResult getPage(RentPageQueryDto rentPageQueryDto) throws Exception;

    Result addRent(Rent rent) throws Exception;

    /*修改租房表记录
    参数: Rent实例（通过id去确定修改）
    返回：布尔类型*/
    Result updataRent(Rent rent) throws Exception;

    ;

    /*查找租房表记录
    参数: Rent实例（以成员变量为条件）
    返回：Rent容器*/
//    ArrayList<Rent> selectRent(Rent rent);

    PageResult getPageXQ(RentPageQueryDto rentPageQueryDto) throws Exception;

    //我要租房
    Result wyzf(WYZFDto wyzfDto) throws Exception;

    ;

    //业主查看谁要租房子
    Result shzf(Integer id) throws Exception;

    //同意出租
    Result tycz(Integer id) throws Exception;

    //拒绝租房请求
    Result jjcz(Integer id) throws Exception;

    //分批查账
    PageResult fpcz(RentPageQueryDto rentPageQueryDto) throws Exception;

    //获取租房信息
    Result getZFMsg(Integer id) throws Exception;
}
