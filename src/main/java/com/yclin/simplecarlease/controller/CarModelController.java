package com.yclin.simplecarlease.controller;

import com.yclin.simplecarlease.core.CarLeaseController;
import com.yclin.simplecarlease.param.carmodel.SelectCarModelListParam;
import com.yclin.simplecarlease.ropo.ApiResult;
import com.yclin.simplecarlease.ropo.CarModelVo;
import com.yclin.simplecarlease.ropo.ListRo;
import com.yclin.simplecarlease.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author LinYuchang
 */
@Slf4j
@CarLeaseController
public class CarModelController {

    private final CarService carService;

    public CarModelController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/car_models")
    public ApiResult<ListRo<CarModelVo>> getCars(SelectCarModelListParam param) {
        ListRo<CarModelVo> cars = carService.selectCarModels(param);
        return ApiResult.C200(cars);
    }

}
