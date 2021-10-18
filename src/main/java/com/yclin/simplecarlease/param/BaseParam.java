package com.yclin.simplecarlease.param;

import com.yclin.simplecarlease.ropo.CheckResult;

/**
 * @author LinYuchang
 */
public class BaseParam implements CheckableParam, CleanableParam {

    @Override
    public CheckResult check() {
        return CheckResult.success();
    }

    @Override
    public void clean() {
    }
}
