package com.yclin.simplecarlease.controller;

import com.yclin.simplecarlease.core.CarLeaseController;
import com.yclin.simplecarlease.model.Car;
import com.yclin.simplecarlease.param.leaseorder.CancelLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.CreateLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.FinishLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.SelectLeaseOrderListParam;
import com.yclin.simplecarlease.ropo.ApiResult;
import com.yclin.simplecarlease.ropo.LeaseOrderVo;
import com.yclin.simplecarlease.ropo.ListRo;
import com.yclin.simplecarlease.service.CarService;
import com.yclin.simplecarlease.service.LeaseOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author LinYuchang
 */
@Slf4j
@CarLeaseController
public class LeaseOrderController {

    private static final Object CREATE_LOCK = new Object();
    private static final Object UPDATE_LOCK = new Object();

    private final CarService carService;
    private final LeaseOrderService leaseOrderService;

    public LeaseOrderController(CarService carService, LeaseOrderService leaseOrderService) {
        this.carService = carService;
        this.leaseOrderService = leaseOrderService;
    }

    /**
     * Api for booking a car(creating a new lease order).
     * <p>
     * To simplify, only one car of specific model can be leased in an order.
     * <p>
     * If are no available cars, error code 540 will return.
     * <p>
     * If there are available cars, it takes two steps to finished the booking task:
     * 1. Select an idle car of the model and update it's {@code isUnderLease} as true;
     * 2. Use the carId of step1 to create a new lease order.
     * <p>
     * To keep the consistency of data, {@code Transactional} annotation is used here to make
     * the 2 steps executes within a transaction.
     *
     * @param param {@link CreateLeaseOrderParam}
     * @return The id of the newly created leaser order will return if success
     */
    @Transactional
    @RequestMapping(value = "/lease_orders", method = RequestMethod.POST)
    public ApiResult<String> createLeaseOrder(@RequestBody CreateLeaseOrderParam param) {
        synchronized (CREATE_LOCK) {
            Car car = carService.leaseCarByCarModelId(param.getCarModelId());
            if (car == null) {
                return ApiResult.C540(null, "No available cars.");
            }
            param.setCarId(car.getId());
            String leaseOrderId = leaseOrderService.createLeaseOrder(param);
            return ApiResult.C200(leaseOrderId);
        }
    }

    @RequestMapping(value = "/lease_orders", method = RequestMethod.GET)
    public ApiResult<ListRo<LeaseOrderVo>> selectLeaseOrderList(SelectLeaseOrderListParam param) {
        ListRo<LeaseOrderVo> data = leaseOrderService.selectLeaseOrderList(param);
        return ApiResult.C200(data);
    }

    @RequestMapping(value = "/lease_orders/_finish", method = RequestMethod.PUT)
    public ApiResult<Boolean> finishLeaseOrder(@RequestBody FinishLeaseOrderParam param) {
        synchronized (UPDATE_LOCK) {
            boolean finished = leaseOrderService.finishLeaseOrder(param);
            if (finished) {
                carService.releaseCarByLeaseOrderId(param.getId());
            }
            return ApiResult.C200(finished);
        }
    }

    @RequestMapping(value = "/lease_orders/_cancel", method = RequestMethod.PUT)
    public ApiResult<Boolean> cancelLeaseOrder(@RequestBody CancelLeaseOrderParam param) {
        synchronized (UPDATE_LOCK) {
            boolean canceled = leaseOrderService.cancelLeaseOrder(param);
            if (canceled) {
                carService.releaseCarByLeaseOrderId(param.getId());
            }
            return ApiResult.C200(canceled);
        }
    }
}
