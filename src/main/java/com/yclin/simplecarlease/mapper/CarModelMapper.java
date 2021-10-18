package com.yclin.simplecarlease.mapper;

import com.yclin.simplecarlease.param.carmodel.SelectCarModelListParam;
import com.yclin.simplecarlease.ropo.CarModelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LinYuchang
 */
@Mapper
public interface CarModelMapper {

    List<CarModelVo> selectVoList(SelectCarModelListParam param);

}
