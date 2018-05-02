package com.itheima.testnewproject.common.exception;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public class EmptyException extends IllegalStateException {
    public EmptyException() {
        super();
    }

    public EmptyException(String detailMessage) {
        super(detailMessage);
    }

    public EmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyException(Throwable cause) {
        super(cause);
    }
}
