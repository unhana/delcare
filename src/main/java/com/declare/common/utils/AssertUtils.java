package com.declare.common.utils;


import com.declare.common.BizError;
import com.declare.common.model.BusinessException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

public class AssertUtils {
    /**
     * throw Exception
     *
     * @param error 错误码
     */
    public static void throwException(BizError error, Object... args) {
        throw new BusinessException(error, args);
    }

    /**
     * 校验目标值不能为空
     *
     * @param obj   目标值
     * @param error 错误码
     */
    public static void notNull(Object obj, BizError error, Object... args) {
        if (Objects.isNull(obj)) {
            throw new BusinessException(error, args);
        }
    }

    /**
     * 校验条件为true
     * false抛异常
     *
     * @param expression 条件
     * @param error      错误码
     */
    public static void assertTrue(boolean expression, BizError error, Object... args) {
        if (!expression) {
            throw new BusinessException(error, args);
        }
    }

    /**
     * 校验集合不能为空
     *
     * @param collection 集合
     * @param error      错误码
     */
    public static void notEmpty(Collection<?> collection, BizError error, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(error, args);
        }
    }

    /**
     * 校验字符串不能为空
     *
     * @param str   字符串
     * @param error 错误码
     */
    public static void notEmpty(String str, BizError error, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(error, args);
        }
    }
}
