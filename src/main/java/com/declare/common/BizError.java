package com.declare.common;

import java.text.MessageFormat;

public enum BizError implements IEnum<String> {
    // 00-0001 00-通用 01-user 02-item 03-inventory - 04-order 05 支付
    /****************************************
     *      00-通用
     ****************************************/
    SYSTEM_ERROR("00-0001", "系统异常"),
    PARAMS_IS_NULL("00-0002", "参数不能为空"),
    NOT_LOGIN("00-0003", "用户未登录"),
    NOT_PERMISSION("00-0004", "用户无权限"),
    PARAMS_IS_NULL_WITH_FIELD("00-0005", "参数[{0}]不能为空"),
    IMAGE_NOT_SELECTED("00-0007", "请选择图片"),
    // 文件大小不得超过10M
    IMAGE_SIZE_LIMIT("00-0008", "文件大小不得超过10M"),
    // 业务类型不合法
    BIZ_TYPE_INVALID("00-0009", "业务类型不合法"),
    /****************************************
     *      01-user
     ****************************************/
    ACCOUNT_NOT_REGISTER("01-0001", "账号未注册"),
    /****************************************
     *      02-declare
     ****************************************/
    DECLARE_NOT_EXIST("02-0001", "申报记录不存在"),
    DECLARE_ALREADY_PROCESSED("02-0002", "申报已处理"),
    SELECT_DECLARE_RECORD("02-0003", "选择一条申报记录"),


    ;

    BizError(String value, String message) {
        this.value = value;
        this.desc = message;
    }

    private String desc;
    private String value;

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public String getDesc(Object... args) {
        return MessageFormat.format(getDesc(), args);
    }

}
