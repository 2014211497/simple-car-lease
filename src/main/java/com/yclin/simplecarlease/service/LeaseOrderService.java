package com.yclin.simplecarlease.service;

import com.yclin.simplecarlease.param.leaseorder.CancelLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.CreateLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.FinishLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.SelectLeaseOrderListParam;
import com.yclin.simplecarlease.ropo.LeaseOrderVo;
import com.yclin.simplecarlease.ropo.ListRo;

/**
 * @author LinYuchang
 */
public interface LeaseOrderService {

    ListRo<LeaseOrderVo> selectLeaseOrderList(SelectLeaseOrderListParam param);

    String createLeaseOrder(CreateLeaseOrderParam param);

    boolean finishLeaseOrder(FinishLeaseOrderParam param);

    boolean cancelLeaseOrder(CancelLeaseOrderParam param);
}
