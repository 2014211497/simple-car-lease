package com.yclin.simplecarlease.param.carmodel;

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
public class SelectCarModelListParam extends SelectListParam {

    private static final Set<String> availableOrderBys = new HashSet<String>() {{
        add("model");
    }};

    protected String fuzzyModel;

    protected Boolean available;

    @Override
    protected Set<String> getAvailableOrderBys() {
        return super.getAvailableOrderBys();
    }

    @Override
    public void clean() {
        super.clean();
        if (this.fuzzyModel != null) {
            this.fuzzyModel = this.fuzzyModel.trim();
        }
    }
}
