package com.yclin.simplecarlease.ropo;

import com.yclin.simplecarlease.model.LeaseOrder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LeaseOrderVo extends LeaseOrder {

    protected String carNumberPlate;

    protected String carModelId;

    protected String carModelName;
}
