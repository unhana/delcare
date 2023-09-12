package com.declare.common.model;

import com.declare.common.BizError;
import lombok.Data;

@Data
public class Result<T> {

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 数据
     */
    private T data;

    /**
     * 错误编码
     */
    private String errorCode;

    /**
     * 错误信息
     */
    private String errorMsg;

    public static <T> Result<T> fail() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        return result;
    }

    public Boolean isSuccess() {
        return success;
    }

    public static <T> Result<T> success() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        return result;
    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> fail(String errorCode, String errorMsg) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setErrorCode(errorCode);
        result.setErrorMsg(errorMsg);
        return result;
    }

    public static <T> Result<T> fail(BizError error) {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setErrorCode(error.getValue());
        result.setErrorMsg(error.getDesc());
        return result;
    }
}
