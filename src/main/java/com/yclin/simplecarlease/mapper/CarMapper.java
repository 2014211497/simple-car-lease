package com.yclin.simplecarlease.mapper;

import com.yclin.simplecarlease.model.Car;
import com.yclin.simplecarlease.param.carmodel.SelectCarModelListParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author LinYuchang
 */
@Mapper
public interface CarMapper {

    List<Car> select(SelectCarModelListParam param);

    Car selectById(String id);

    Car selectOneToLeaseByCarModelId(String modelId);

    Car selectOneToReleaseByOrderId(String orderId);

    int updateUnderLeaseStatus(@Param("id") String id, @Param("isUnderLease") boolean isUnderLease);

}
