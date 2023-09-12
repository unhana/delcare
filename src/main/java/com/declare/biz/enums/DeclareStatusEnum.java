package com.declare.biz.enums;

import com.declare.common.IEnum;

public enum DeclareStatusEnum implements IEnum<Integer> {

    SUBMIT(1, "审核中"),
    PASS(2, "审核通过"),
    REJECT(3, "审核不通过"),

    ;


    private Integer value;
    private String desc;


    DeclareStatusEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
