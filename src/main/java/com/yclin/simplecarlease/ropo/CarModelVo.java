package com.yclin.simplecarlease.ropo;

import com.yclin.simplecarlease.model.CarModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CarModelVo extends CarModel {

    protected Integer stock;
}
