package com.yclin.simplecarlease.ropo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

/**
 * @author LinYuchang
 */
public class Ropo {

    /**
     * 以 JSON 为媒介，将 对象 转换为指定类型，避免强制转换的编译错误
     *
     * @param obj   待转换的对象
     * @param clazz 目标类型
     * @param <T>   泛型参数
     * @return 目标类型的对象实例
     */
    public static <T> T convert(Object obj, Class<T> clazz) {
        return JSON.parseObject(obj instanceof String ? ((String) obj) : JSON.toJSONString(obj), clazz);
    }

    /**
     * 以 JSON 为媒介，将 对象 转换为指定类型，避免强制转换的编译错误
     *
     * @param obj       待转换的对象
     * @param reference 目标类型
     * @param <T>       泛型参数
     * @return 目标类型的对象实例
     */
    public static <T> T convert(Object obj, TypeReference<T> reference) {
        return JSON.parseObject(obj instanceof String ? ((String) obj) : JSON.toJSONString(obj), reference);
    }
}
