package com.declare.common;

import java.util.Arrays;
import java.util.Objects;

public interface IEnum<T> {

    /**
     * 获取值
     *
     * @return value
     */
    T getValue();

    /**
     * 获取描述
     *
     * @return desc
     */
    String getDesc();


    default boolean equalsValue(T value) {
        return Objects.equals(getValue(), value);
    }

    default boolean notEqualsValue(T value) {
        return !Objects.equals(getValue(), value);
    }

    /**
     * 根据值获取枚举
     *
     * @param enumClass 枚举类
     * @param value     值
     * @param <T>       值类型
     * @param <K>       枚举类型
     * @return 枚举
     */
    @SuppressWarnings("unchecked")
    static <T, K extends IEnum<T>> K valueOf(Class<? extends IEnum<T>> enumClass, T value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("enum value is null!");
        }
        return (K) Arrays.stream(enumClass.getEnumConstants())
                .filter(item -> Objects.equals(item.getValue(), value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No enum constant " + enumClass.getCanonicalName() + "." + value));

    }

    /**
     * 根据值获取枚举, 未匹配到返回null 而不是抛出异常
     */
    @SuppressWarnings("unchecked")
    static <T, K extends IEnum<T>> K valueOfNullable(Class<? extends IEnum<T>> enumClass, T value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return (K) Arrays.stream(enumClass.getEnumConstants())
                .filter(item -> Objects.equals(item.getValue(), value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 判断是否是枚举值
     */
    static boolean isEnumValue(Class<? extends IEnum<?>> enumClass, Object value) {
        if (Objects.isNull(value)) {
            return false;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(item -> Objects.equals(item.getValue(), value));
    }

    /**
     * 通过枚举class和枚举值获取描述
     */
    static String getDesc(Class<? extends IEnum<?>> enumClass, Object value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(item -> Objects.equals(item.getValue(), value))
                .findFirst()
                .map(IEnum::getDesc)
                .orElse(value.toString());
    }

    /**
     * 判断是否在枚举值中
     *
     * @param value 值
     * @param enums 枚举
     * @return 是否在枚举值中
     */
    static boolean in(Object value, IEnum<?>... enums) {
        if (Objects.isNull(value)) {
            return false;
        }
        return Arrays.stream(enums)
                .anyMatch(item -> Objects.equals(item.getValue(), value));
    }

    /**
     * 判断是否不在枚举值中
     *
     * @param value 值
     * @param enums 枚举
     * @return 是否不在枚举值中
     */
    static boolean notIn(Object value, IEnum<?>... enums) {
        return !in(value, enums);
    }
}
