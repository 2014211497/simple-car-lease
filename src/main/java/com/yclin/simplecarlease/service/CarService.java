package com.yclin.simplecarlease.service;

import com.yclin.simplecarlease.model.Car;
import com.yclin.simplecarlease.param.carmodel.SelectCarModelListParam;
import com.yclin.simplecarlease.ropo.CarModelVo;
import com.yclin.simplecarlease.ropo.ListRo;

/**
 * @author LinYuchang
 */
public interface CarService {

    ListRo<CarModelVo> selectCarModels(SelectCarModelListParam param);

    Car leaseCarByCarModelId(String carModelId);

    void releaseCarByLeaseOrderId(String orderId);
}
