package com.declare.web.config;

import com.declare.common.BizError;
import com.declare.common.model.BusinessException;
import com.declare.common.model.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Result<?>> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error("BusinessException：", e);
        return new ResponseEntity<>(Result.fail(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result<?>> handleException(Exception e, HttpServletRequest request) {
        log.error("Exception：", e);
        return new ResponseEntity<>(Result.fail(BizError.SYSTEM_ERROR), HttpStatus.OK);
    }

}
