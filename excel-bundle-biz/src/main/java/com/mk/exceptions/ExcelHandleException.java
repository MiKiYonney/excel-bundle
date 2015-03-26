package com.mk.exceptions;

/**
 * Created by yonney.yang on 2015/3/26.
 */
public class ExcelHandleException extends RuntimeException {
    public ExcelHandleException() {
    }

    public ExcelHandleException(String message) {
        super(message);
    }

    public ExcelHandleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcelHandleException(Throwable cause) {
        super(cause);
    }
}
