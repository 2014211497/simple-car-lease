package com.yclin.simplecarlease.mapper;

import com.yclin.simplecarlease.model.LeaseOrder;
import com.yclin.simplecarlease.param.leaseorder.SelectLeaseOrderListParam;
import com.yclin.simplecarlease.ropo.LeaseOrderVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author LinYuchang
 */
@Mapper
public interface LeaseOrderMapper {

    int create(LeaseOrder leaseOrder);

    int update(LeaseOrder leaseOrder);

    List<LeaseOrder> select(SelectLeaseOrderListParam param);

    List<LeaseOrderVo> selectVoList(SelectLeaseOrderListParam param);

    int finish(String id);

    int cancel(String id);
}
