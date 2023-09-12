package com.declare.common.model;

import com.declare.common.BizError;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;

@Data
public class BusinessException extends RuntimeException {

    private String message;

    private String code;

    private BizError error;

    private Object[] args;

    public BusinessException() {
        super();
    }

    public BusinessException(BizError error, Object... args) {
        super(error.getDesc());
        this.message = error.getDesc();
        this.code = error.getValue();
        this.error = error;
        this.args = args;
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        if(ArrayUtils.isEmpty(args)){
            return message;
        }
        return error.getDesc(args);
    }

}
