package com.yclin.simplecarlease.param.leaseorder;

import com.yclin.simplecarlease.param.BaseParam;
import com.yclin.simplecarlease.ropo.CheckResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Parameter for car booking api
 *
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CreateLeaseOrderParam extends BaseParam {

    /**
     * the id of selected car model to book
     */
    protected String carModelId;

    /**
     * field for inner use, ignored
     */
    protected String carId;

    /**
     * the time when the order will begin (timestamp in seconds)
     */
    protected Long beginTime;

    /**
     * the time when the order will end (timestamp in seconds)
     */
    protected Long endTime;

    @Override
    public CheckResult check() {
        if (carModelId == null || carModelId.isEmpty()) {
            return CheckResult.fail("parameter carModelId cannot be null or empty.");
        }
        long nowTime = System.currentTimeMillis() / 1000L;
        if (beginTime == null || endTime == null || beginTime <= nowTime || beginTime >= endTime) {
            return CheckResult.fail("invalid values of beginTime and endTime");
        }
        return super.check();
    }
}
