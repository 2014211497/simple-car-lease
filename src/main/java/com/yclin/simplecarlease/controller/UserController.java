package com.yclin.simplecarlease.controller;

import com.yclin.simplecarlease.core.ApplicationProperties;
import com.yclin.simplecarlease.core.CarLeaseController;
import com.yclin.simplecarlease.core.ContextHolder;
import com.yclin.simplecarlease.core.IgnoreLogin;
import com.yclin.simplecarlease.model.User;
import com.yclin.simplecarlease.param.user.UserLoginParam;
import com.yclin.simplecarlease.ropo.ApiResult;
import com.yclin.simplecarlease.service.UserService;
import com.yclin.simplecarlease.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * A simple user service
 *
 * @author LinYuchang
 */
@Slf4j
@CarLeaseController
public class UserController {

    private final UserService userService;
    private final ApplicationProperties properties;

    public UserController(UserService userService, ApplicationProperties properties) {
        this.userService = userService;
        this.properties = properties;
    }

    @IgnoreLogin
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ApiResult<String> userLogin(@RequestBody UserLoginParam param) {
        User user = userService.getUserById(param.getUsername());
        if (user == null || !param.getPassword().equals(user.getPassword())) {
            return ApiResult.C200401(null, "invalid username or password.");
        }
        Long expireAt = System.currentTimeMillis() + properties.getTokenExpireTime();
        String token = JwtUtil.createToken(user.getId(), expireAt);
        return ApiResult.C200(token);
    }

    @RequestMapping(value = "/user/profile", method = RequestMethod.GET)
    public ApiResult<User> getProfile() {
        String userId = ContextHolder.getAccessContext().getUserId();
        User user = userService.getUserById(userId);
        return ApiResult.C200(user);
    }
}
