package com.yclin.simplecarlease.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeaseOrder extends BaseModel {

    protected String carId;

    protected Long beginTime;

    protected Long endTime;

    protected Long finishTime;

    protected Boolean isFinished;

    protected Boolean isCanceled;
}
