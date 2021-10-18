package com.yclin.simplecarlease.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Car extends BaseModel {

    protected String modelId;

    protected String numberPlate;

    protected Boolean isUnderLease;
}
