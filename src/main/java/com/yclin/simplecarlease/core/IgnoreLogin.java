package com.yclin.simplecarlease.core;

import java.lang.annotation.*;

/**
 * @author LinYuchang
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLogin {
}
