package com.win.xszf.controller;

import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.SystemException;
import com.win.xszf.pojo.dto.*;
import com.win.xszf.service.HouseService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.NodeList;

import javax.servlet.http.HttpServletRequest;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@RestController
@RequestMapping("/house")
@Log4j
public class HouseController {

    @Autowired
    private HouseService houseService;

    //添加房子
    @PostMapping("/add")
    public Result add(@RequestBody WYCZDto wyczDto, HttpServletRequest request) {
        log.info("添加房子返回数据" + wyczDto.toString());
        try {
            houseService.add(wyczDto, request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //房子推广page
    @PostMapping("/fztgPage")
    public Result fztgPage(@RequestBody FZTGPageQueryDto fztgPageQueryDto) {
        log.info("房子推广接收数据" + fztgPageQueryDto);
        PageResult pageResult = null;
        try {
            pageResult = houseService.fztgPage(fztgPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(pageResult);
    }

    //根据id返回房子信息
    @GetMapping("/fztgGetById")
    public Result fztgGetById(Integer id) {
        Result result = null;
        try {
            result = houseService.fztgGetById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //修改房子排序
    @PostMapping("/updateSort")
    public Result updateHouseSort(@RequestBody UpdateHouseSortDto updateHouseSortDto) {
        Result result = null;
        try {
            result = houseService.updateHouseSort(updateHouseSortDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    @PostMapping("/fzshPage")
    public Result fzshPage(@RequestBody FZSHPageQueryDto fzshPageQueryDto) {
        log.info("房子审核接收数据" + fzshPageQueryDto);
        PageResult pageResult = null;
        try {
            pageResult = houseService.fzshPage(fzshPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(pageResult);
    }

    @PostMapping("/getfzshVo")
    private Result getfzshVo(Integer id) {
        log.info("房子的id为：" + id);
        Result result = null;
        try {
            result = houseService.getfzshVo(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //通过审核
    @PostMapping("/housePass")
    public Result housePass(Integer id) {
        try {
            houseService.changZfzz(id, 3);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //审核不通过
    @PostMapping("/houseRefuse")
    public Result houseRefuse(Integer id) {
        try {
            houseService.changZfzz(id, 2);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //设置状态为审核中
    @PostMapping("/houseSHZ")
    public Result houseSHZ(Integer id) {
        try {
            houseService.changZfzz(id, 1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success();
    }

    //房子管理
    @PostMapping("/YzPage")
    public Result YzPage(@RequestBody YzPageQueryDto yzPageQueryDto) {
        log.info("业主房子管理分页接收数据" + yzPageQueryDto);
        PageResult pageResult = null;
        try {
            pageResult = houseService.YzPage(yzPageQueryDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return Result.success(pageResult);
    }

    //上架或下架房子
    @PostMapping("/xjHouse")
    public Result xjOrSjHouse(@RequestParam("id") Integer id, @RequestParam("status") Integer status) {
        log.info("输入id为：" + id + "输入状态为：" + status);
        Result result = null;
        try {
            result = houseService.xjOrSjHouse(id, status);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //根据id查询房子信息
    @GetMapping("/getHouseById")
    public Result getHouseByID(Integer id) {
        log.info("输入id为：" + id);
        Result result = null;
        try {
            result = houseService.getHouseByID(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    @PostMapping("/updateHouse")
    public Result updateHouse(@RequestBody HouseDto houseDto) {
        log.info("更新房子信息输入为：" + houseDto);
        Result result = null;
        try {
            result = houseService.updateHouse(houseDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //获取轮播图
    @GetMapping("/getlbHouse")
    public Result getLunBaoHouse() {
        Result result = null;
        try {
            result = houseService.getLunBaoHouse();
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


    @PostMapping("/getHouseList")
    public Result getHouseList(@RequestBody IndexHouseListDto indexHouseListDto) {
        Result result = null;
        try {
            result = houseService.getHouseList(indexHouseListDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //房子详情页面处图片
    @GetMapping("/getHouseImageById")
    public Result getHouseImageById(Integer id) {
        Result result = null;
        try {
            result = houseService.getHouseImageById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

    //获取房子配套信息
    @GetMapping("/getHouseAdd")
    public Result getHouseAdd(Integer id) {
        Result result = null;
        try {
            result = houseService.getHouseAdd(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }


    //我要租房界面展示所有房子信息
    @PostMapping("/wyzfPage")
    public Result getPage(@RequestBody WYZFPageDto wyzfPageDto) {
        Result result = null;
        try {
            result =houseService.getPage(wyzfPageDto);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("系统错误，请联系管理员");
        }
        return result;
    }

}
