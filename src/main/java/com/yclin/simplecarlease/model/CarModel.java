package com.yclin.simplecarlease.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CarModel extends BaseModel {

    protected String name;
}
