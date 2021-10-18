package com.yclin.simplecarlease.core;

/**
 * @author LinYuchang
 */
public class ContextHolder {

    private static final ThreadLocal<AccessContext> accessContext = ThreadLocal.withInitial(AccessContext::new);

    public static AccessContext getAccessContext() {
        return accessContext.get();
    }

    public static void setAccessContext(AccessContext context) {
        accessContext.set(context);
    }

    public static void clear() {
        accessContext.remove();
    }
}
