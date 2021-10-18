package com.yclin.simplecarlease.param.leaseorder;

import com.yclin.simplecarlease.param.BaseParam;
import com.yclin.simplecarlease.ropo.CheckResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FinishLeaseOrderParam extends BaseParam {

    protected String id;

    @Override
    public CheckResult check() {
        if (id == null || id.isEmpty()) {
            return CheckResult.fail("parameter id cannot be null or empty.");
        }
        return super.check();
    }
}
