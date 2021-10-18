package com.yclin.simplecarlease.param;

import com.yclin.simplecarlease.ropo.CheckResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SelectListParam extends BaseParam {

    protected int page = 1;

    protected int size = 10;

    protected String orderBy;

    protected boolean orderDesc = true;

    @Override
    public CheckResult check() {
        if (page < 1) {
            return CheckResult.fail("page must >= 1");
        }
        if (size < 0 || size > 1000) {
            return CheckResult.fail("size must >= 0 and <= 1000");
        }
        if (orderBy != null) {
            Set<String> orderBys = this.getAvailableOrderBys();
            if (orderBys != null && !orderBys.contains(orderBy)) {
                return CheckResult.fail("invalid orderBy value: " + orderBy);
            }
        }
        return super.check();
    }

    protected Set<String> getAvailableOrderBys() {
        return null;
    }
}
