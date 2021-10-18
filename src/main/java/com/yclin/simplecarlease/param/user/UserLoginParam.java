package com.yclin.simplecarlease.param.user;

import com.yclin.simplecarlease.param.BaseParam;
import com.yclin.simplecarlease.ropo.CheckResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LinYuchang
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserLoginParam extends BaseParam {

    protected String username;

    protected String password;

    @Override
    public CheckResult check() {
        if (username == null) {
            return CheckResult.fail("parameter username is required.");
        }
        if (password == null) {
            return CheckResult.fail("parameter password is required.");
        }
        return super.check();
    }
}
