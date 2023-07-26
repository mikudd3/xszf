package com.win.xszf.common;

import com.win.xszf.exception.SystemException;
import com.win.xszf.mapper.CostMapper;
import com.win.xszf.mapper.HouseMapper;
import com.win.xszf.mapper.ServiceMapper;
import com.win.xszf.pojo.dto.RentPageQueryDto;
import com.win.xszf.pojo.entity.Cost;
import com.win.xszf.pojo.entity.House;
import com.win.xszf.pojo.entity.Rent;
import com.win.xszf.service.CostService;
import com.win.xszf.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

/*
* 用于每日检查是否有租房费产生
* */
public class TaskExecutor {
//    用于插入租房账单
    @Autowired
    private CostMapper costMapper;

//    用于获取基础租金
    @Autowired
    private HouseMapper houseMapper;

//    用于获取维修费用
    @Autowired
    private ServiceMapper serviceMapper;

//    用于检索租房以及判断
    @Autowired
    private RentService rentService;

    public void executeDailyTask() {
        // 在此处编写你要执行的任务逻辑
        System.out.println("执行每日任务 - " + LocalDateTime.now());

        RentPageQueryDto redto = new RentPageQueryDto();
        //每批的个数
        redto.setPageSize(100);
        //当前第几批
        redto.setCurrentPage(1);
        //当前时间
        LocalDate nowdate = LocalDate.now();
        //总页数
        long totol = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        //分批生成账单
        do{
            try{
                PageResult pc = rentService.fpcz(redto);
                totol = pc.getTotal();
                List<Rent> fzs = pc.getRecords();
                for (Rent fz:fzs) {
                    String rzsj =fz.getZfrq();

                    // 解析日期字符串
                    LocalDate perdate = LocalDate.parse(rzsj, formatter);
                    long diffday = perdate.until(nowdate, ChronoUnit.DAYS);
                    if (diffday % 30 == 0){
                        Cost newcoust = new Cost();
                        //租房id，状态，时间,季度
                        newcoust.setHouse_id(fz.getHouse_id());
                        newcoust.setZt(0);
                        newcoust.setCreateTime(nowdate.format(formatter));
                        newcoust.setSeason(String.valueOf(nowdate.getMonthValue()/3));
                        //基础租金
                        House house = houseMapper.getById(fz.getHouse_id());
                        newcoust.setRent_fee(house.getBasemoney());
                        //维修费
                        String data = nowdate.minusDays(30).format(formatter);
                        Double weixiuf = serviceMapper.weixiuzd(house.getId(), data);
                        newcoust.setService_fee(weixiuf);
                        //水电费
                        newcoust.setWater_fee(new Random(200).nextDouble());
                        newcoust.setElectricity_fee(new Random(200).nextDouble());
                        //插入表中
                        costMapper.addCost(newcoust);
                    }
                }
            }catch (Exception e){
                new SystemException("系统错误，请联系管理员");
            }
            redto.setCurrentPage(redto.getCurrentPage()+1);
        }while (redto.getCurrentPage() <= totol);
    }


}