package com.itheima.testnewproject.common.exception;

/**
 * 描述说明  <br/>
 * Author : zhongw <br/>
 */
public class ResultMessageException extends IllegalStateException {
    public ResultMessageException() {
        super();
    }

    public ResultMessageException(String detailMessage) {
        super(detailMessage);
    }

    public ResultMessageException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResultMessageException(Throwable cause) {
        super(cause);
    }
}
