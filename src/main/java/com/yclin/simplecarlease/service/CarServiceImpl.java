package com.yclin.simplecarlease.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yclin.simplecarlease.mapper.CarMapper;
import com.yclin.simplecarlease.mapper.CarModelMapper;
import com.yclin.simplecarlease.model.Car;
import com.yclin.simplecarlease.param.carmodel.SelectCarModelListParam;
import com.yclin.simplecarlease.ropo.CarModelVo;
import com.yclin.simplecarlease.ropo.ListRo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LinYuchang
 */
@Service
public class CarServiceImpl implements CarService {

    @Resource
    private CarMapper carMapper;

    @Resource
    private CarModelMapper carModelMapper;

    @Override
    public ListRo<CarModelVo> selectCarModels(SelectCarModelListParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());
        List<CarModelVo> list = carModelMapper.selectVoList(param);
        PageInfo<CarModelVo> info = new PageInfo<>(list);
        return new ListRo<>(list, info.getTotal(), info.isHasNextPage());
    }

    @Override
    public Car leaseCarByCarModelId(String carModelId) {
        Car car = carMapper.selectOneToLeaseByCarModelId(carModelId);
        if (car != null) {
            carMapper.updateUnderLeaseStatus(car.getId(), true);
        }
        return car;
    }

    @Override
    public void releaseCarByLeaseOrderId(String orderId) {
        Car car = carMapper.selectOneToReleaseByOrderId(orderId);
        if (car != null) {
            carMapper.updateUnderLeaseStatus(car.getId(), false);
        }
    }
}
