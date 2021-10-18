package com.yclin.simplecarlease.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yclin.simplecarlease.mapper.LeaseOrderMapper;
import com.yclin.simplecarlease.model.LeaseOrder;
import com.yclin.simplecarlease.param.leaseorder.CancelLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.CreateLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.FinishLeaseOrderParam;
import com.yclin.simplecarlease.param.leaseorder.SelectLeaseOrderListParam;
import com.yclin.simplecarlease.ropo.LeaseOrderVo;
import com.yclin.simplecarlease.ropo.ListRo;
import com.yclin.simplecarlease.ropo.Ropo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author LinYuchang
 */
@Slf4j
@Service
public class LeaseOrderServiceImpl implements LeaseOrderService {

    @Resource
    private LeaseOrderMapper leaseOrderMapper;

    @Override
    public ListRo<LeaseOrderVo> selectLeaseOrderList(SelectLeaseOrderListParam param) {
        PageHelper.startPage(param.getPage(), param.getSize());
        List<LeaseOrderVo> list = leaseOrderMapper.selectVoList(param);
        PageInfo<LeaseOrderVo> info = new PageInfo<>(list);
        return new ListRo<>(list, info.getTotal(), info.isHasNextPage());
    }

    @Override
    public String createLeaseOrder(CreateLeaseOrderParam param) {
        LeaseOrder leaseOrder = Ropo.convert(param, LeaseOrder.class);
        String orderId = leaseOrder.initId();
        leaseOrder.initCreateInfo();
        leaseOrderMapper.create(leaseOrder);
        log.info("create lease order[{}] with car[{}]", leaseOrder.getId(), param.getCarId());
        return orderId;
    }

    @Override
    public boolean finishLeaseOrder(FinishLeaseOrderParam param) {
        return leaseOrderMapper.finish(param.getId()) > 0;
    }

    @Override
    public boolean cancelLeaseOrder(CancelLeaseOrderParam param) {
        return leaseOrderMapper.cancel(param.getId()) > 0;
    }
}
