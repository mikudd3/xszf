package com.win.xszf.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.win.xszf.common.PageResult;
import com.win.xszf.common.Result;
import com.win.xszf.exception.BusinessException;
import com.win.xszf.mapper.*;
import com.win.xszf.pojo.dto.*;
import com.win.xszf.pojo.entity.*;
import com.win.xszf.pojo.vo.*;
import com.win.xszf.service.HouseService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.*;

/**
 * @project:
 * @author: mikudd3
 * @version: 1.0
 */
@Log4j
@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    private HouseMapper houseMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HouseImageMapper houseImageMapper;
    @Autowired
    private HouseZmclMapper houseZmclMapper;

    /**
     * 添加房子
     *
     * @param wyczDto
     */
    @Override
    public void add(WYCZDto wyczDto, HttpServletRequest request) {
        //1.根据输入的用户名查询用户
        House house = new House();
        //根据同名复制原则，将wyczDto里面的同名对象复制到House中
        BeanUtils.copyProperties(wyczDto, house);
        //3.设置house的其他属性
        //从登录用户的session域中获取用户id
        Integer userId = (Integer) request.getSession().getAttribute("user");
        //设置房子的用户名
        //3.1设置所属用户id
        house.setUserId(userId);
        //3.2 设置出租状态，默认为0表示未出租
        house.setCondition(0);
        //3.3 设置房子资质(0未审核，1审核中，2审核未通过，3审核通过）,刚上传的房子默认为0
        house.setZfzz(0);
        //3.4 设置状态
        house.setZt(0);
        //3.5 设置创建时间
        house.setCreateTime(new Date());
        //3.6 标题
        house.setTitle("无");
        //3.7 排序
        //添加配套信息
        house.setSort(10);
        List<String> houseAdd = wyczDto.getHouse_add();
        StringJoiner fwpt = new StringJoiner(",", "[", "]");
        for (String s : houseAdd) {
            fwpt.add(s);
        }
        house.setHouse_add(fwpt.toString());
        //4.将房子添加到数据库
        try {
            houseMapper.add(house);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //5.根据房子编号查找新添加的房子的id
        Integer id = null;
        try {
            id = houseMapper.selectByfzbh(house.getHouse_no());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        List<String> image = wyczDto.getImage();
        //添加图片到图片表
        for (String i : image) {
            HouseImage image1 = new HouseImage();
            image1.setHouse_id(id);
            image1.setImage(i);
            //添加到数据库
            try {
                houseImageMapper.add(image1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
        }
        //添加证明材料到数据库
        HouseZmcl houseZmcl = new HouseZmcl();
        houseZmcl.setHouse_id(id);
        houseZmcl.setZmcl(wyczDto.getZmcl());
        try {
            houseZmclMapper.add(houseZmcl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }

    @Override
    public PageResult fztgPage(FZTGPageQueryDto fztgPageQueryDto) {
        //1。进行分页查询
        //select * from fz limit 0,10
        PageHelper.startPage(fztgPageQueryDto.getCurrentPage(), fztgPageQueryDto.getPageSize());
        Page<FZTGVo> housePage = null;
        try {
            housePage = houseMapper.fztgPageQuery(fztgPageQueryDto.getHouse_no());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //获取查询总数
        long total = housePage.getTotal();
        //获取查询到的集合
        List<FZTGVo> result = housePage.getResult();
        return new PageResult(total, result);
    }

    //根据id查询房子信息
    @Override
    public Result fztgGetById(Integer id) {
        House house = null;
        try {
            house = houseMapper.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(house);
    }

    //更新房子排序
    @Override
    public Result updateHouseSort(UpdateHouseSortDto updateHouseSortDto) {
        try {
            houseMapper.updateHouseSort(updateHouseSortDto.getId(), updateHouseSortDto.getSort());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }

    @Override
    public PageResult fzshPage(FZSHPageQueryDto fzshPageQueryDto) {
        //1.获取输入信息
        Integer zfzz = fzshPageQueryDto.getZfzz();
        String house_no = fzshPageQueryDto.getHouse_no();
        //2。进行分页查询
        //select * from fz limit 0,10
        PageHelper.startPage(fzshPageQueryDto.getCurrentPage(), fzshPageQueryDto.getPageSize());
        Page<FZSHVo> housePage = null;
        try {
            housePage = houseMapper.FzShSelect(zfzz, house_no);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //获取查询总数
        long total = housePage.getTotal();
        //获取查询到的集合
        List<FZSHVo> result = housePage.getResult();
        return new PageResult(total, result);
    }

    //获取房子审核证明材料
    @Override
    public Result getfzshVo(Integer id) {
        FZSHVo fzshVo = null;
        try {
            fzshVo = houseMapper.getfzshVo(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success(fzshVo);
    }

    //审核
    @Override
    public void changZfzz(Integer id, Integer zfzz) {
        //审核通过，则将zfzz变成3
        try {
            houseMapper.changZfzz(id, zfzz);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
    }

    //业主房子管理分页信息
    @Override
    public PageResult YzPage(YzPageQueryDto yzPageQueryDto) {
        PageHelper.startPage(yzPageQueryDto.getCurrentPage(), yzPageQueryDto.getPageSize());
        Page<House> housePage = null;
        try {
            housePage = houseMapper.yzPage(yzPageQueryDto.getAddress(), yzPageQueryDto.getUser_id());
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //获取查询总数
        long total = housePage.getTotal();
        //获取查询到的集合
        List<House> result = housePage.getResult();
        return new PageResult(total, result);
    }

    @Override
    public Result xjOrSjHouse(Integer id, Integer status) {
        if (status == 1) {
            //查看该房子是否已经在出租
            Integer ret = null;
            try {
                ret = houseMapper.selectConditionById(id);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
            if (ret == 0) {
                return Result.error("该房子正在出租，不可下架");
            }
        }
        try {
            houseMapper.xjOrSjHouse(id, status);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success("下架成功");
    }


    @Override
    public Result getHouseByID(Integer id) {
        //1.根据id查询房子信息
        House h = null;
        try {
            h = houseMapper.getById(id);
        } catch (SQLException e) {
            throw new BusinessException("操作错误，请正确操作");
        }
        HouseVo houseVo = new HouseVo();
        BeanUtils.copyProperties(h, houseVo);
        //2.设置配套信息
        List<String> house_add = new ArrayList<>(Arrays.asList(h.getHouse_add().split(",")));
        houseVo.setHouse_add(house_add);
        //2.根据id查询图片
        List<String> images = null;
        try {
            images = houseImageMapper.selectByHouseId(id);
        } catch (SQLException e) {
            throw new BusinessException("操作错误，请正确操作");
        }
        //将查找的图片放入houseVo中
        houseVo.setImage(images);
        //查找证明材料
        List<String> zmcl = null;
        try {
            zmcl = houseZmclMapper.selectHouseZmclById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        houseVo.setZmcl(zmcl);
        return Result.success(houseVo);
    }

    /**
     * 更新房子信息
     *
     * @param houseDto
     * @return
     */
    @Override
    public Result updateHouse(HouseDto houseDto) {
        House house = new House();
        BeanUtils.copyProperties(houseDto, house);
        //更新房子信息
        try {
            houseMapper.update(house);
        } catch (SQLException e) {
            throw new BusinessException("操作错误，请正确操作");
        }
        Integer id = houseDto.getId();
        //添加配套信息到配套表
        List<String> houseAdd = houseDto.getHouse_add();
        //先根据房子id删除以前的信息
        StringJoiner fwpt = new StringJoiner(",", "[", "]");
        for (String s : houseAdd) {
            fwpt.add(s);
        }
        house.setHouse_add(fwpt.toString());
        List<String> image = houseDto.getImage();
        //先根据房子id删除以前的信息
        try {
            houseImageMapper.deleteByHouseId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //添加图片到图片表
        for (String i : image) {
            HouseImage image1 = new HouseImage();
            image1.setHouse_id(id);
            image1.setImage(i);
            //添加到数据库
            try {
                houseImageMapper.add(image1);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
        }
        //删除以前的houseZmcl
        try {
            houseZmclMapper.deleteByHouseId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //添加证明材料到数据库
        HouseZmcl houseZmcl = new HouseZmcl();
        houseZmcl.setHouse_id(id);
        houseZmcl.setZmcl(houseDto.getZmcl().get(0));
        try {
            houseZmclMapper.add(houseZmcl);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        return Result.success();
    }


    @Override
    public Result getLunBaoHouse() {
        List<LBTVo> lbtVos = null;
        try {
            lbtVos = houseZmclMapper.getLunBaoTu();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //遍历集合
        for (LBTVo lbtVo : lbtVos) {
            //根据获得的房子id获取房子的图片
            List<String> images = null;
            try {
                images = houseImageMapper.selectByHouseId(lbtVo.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
            if (!images.isEmpty()) {
                lbtVo.setUrl(images.get(0));
            }
        }
        return Result.success(lbtVos);
    }

    //首页房子展示
    @Override
    public Result getHouseList(IndexHouseListDto indexHouseListDto) {
        log.info(indexHouseListDto.getRange());
        //对rang进行处理
        if (indexHouseListDto.getRange() != null) {
            //对数据进行处理
            String[] strings = indexHouseListDto.getRange().split("-");
            indexHouseListDto.setFromPrice(Double.valueOf(strings[0]));
            indexHouseListDto.setToPrice(Double.valueOf(strings[1]));
        }
        //进行查询
        List<House> houses = null;
        try {
            houses = houseMapper.getHouseList(indexHouseListDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        List<HouseListVo> list = new ArrayList<>();
        //设置数据库
        for (House house : houses) {
            HouseListVo houseListVo = new HouseListVo();
            //设置房屋配套
            BeanUtils.copyProperties(house, houseListVo, "house_add");
            //设置house_add
            List<String> house_add = new ArrayList<>(Arrays.asList(house.getHouse_add().split(",")));
            houseListVo.setHouse_add(house_add);
            //设置图片
            List<String> images = null;
            try {
                images = houseImageMapper.selectByHouseId(house.getId());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
            log.info("图片为：" + images);
            //判断获取的图片是否为空
            if (!images.isEmpty()) {
                houseListVo.setImage(images.get(0));
            }
            //设置房主真实姓名
            log.info("房东的id为：" + house.getUserId());
            try {
                houseListVo.setRealname(userMapper.getByid(house.getUserId()).getRealname());
            } catch (SQLException e) {
                e.printStackTrace();
                throw new BusinessException("操作错误，请正确操作");
            }
            //添加到集合
            list.add(houseListVo);
        }
        return Result.success(list);
    }


    //房子详情页面处图片
    @Override
    public Result getHouseImageById(Integer id) {
        //根据id获取房子信息
        House house = null;
        try {
            house = houseMapper.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //获取房子的title
        String title = house.getTitle();
        //获取房子对应的图片
        List<String> images = null;
        try {
            images = houseImageMapper.selectByHouseId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        List<HouseImagesVo> list = new ArrayList<>();
        if (images.isEmpty()) {
            return Result.error("该房子没有图片");
        }
        //如果有图片
        for (String image : images) {
            HouseImagesVo imagesVo = new HouseImagesVo();
            imagesVo.setTitle(title);
            imagesVo.setImage(image);
            list.add(imagesVo);
        }
        return Result.success(list);
    }

    //根据房子id获取房子配套信息
    @Override
    public Result getHouseAdd(Integer id) {
        String houseAdd = null;
        try {
            houseAdd = houseMapper.getHouseAddBYId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        List<String> house_add = new ArrayList<>(Arrays.asList(houseAdd.split(",")));
        //返回配套信息
        return Result.success(house_add);
    }


    //我要租房分页查询
    @Override
    public Result getPage(WYZFPageDto wyzfPageDto) {
        //进行分页查询
        PageHelper.startPage(wyzfPageDto.getCurrentPage(), wyzfPageDto.getPageSize());
        //进行分页查询
        Page<WYZFVo> page = null;
        try {
            page = houseMapper.WYZFPage(wyzfPageDto);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BusinessException("操作错误，请正确操作");
        }
        //获取总个数
        Long total = page.getTotal();
        //获取数据
        List<WYZFVo> records = page.getResult();
        log.info(records);
        PageResult pageResult = new PageResult(total, records);
        return Result.success(pageResult);
    }
}
