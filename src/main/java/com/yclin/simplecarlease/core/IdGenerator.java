package com.yclin.simplecarlease.core;

import java.util.UUID;

/**
 * @author LinYuchang
 */
public class IdGenerator {

    public static String uuid32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
