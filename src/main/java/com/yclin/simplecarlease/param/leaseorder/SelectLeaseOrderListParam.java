package com.yclin.simplecarlease.param.leaseorder;

import com.yclin.simplecarlease.core.ContextHolder;
import com.yclin.simplecarlease.param.SelectListParam;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SelectLeaseOrderListParam extends SelectListParam {

    protected static final Set<String> availableSortBys = new HashSet<String>() {{
        add("create_time");
    }};

    protected String creatorId;

    @Override
    public void clean() {
        this.creatorId = ContextHolder.getAccessContext().getUserId();
        super.clean();
    }
}
