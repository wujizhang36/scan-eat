/*
 * @description
 * @author System
 * @version 1.0
 */

package com.snowflake.basic.modules.common.exception;

import lombok.Getter;

/**
 * @author System
 * @description 系统异常
 */
@Getter
public class SystemException extends Throwable {

    /**
     * 错误码
     */
    protected Integer errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;

    public SystemException() {
        super();
    }

    public SystemException(SystemErrorInfoInterface systemErrorInfoInterface) {
        super(systemErrorInfoInterface.getMessage());
        this.errorCode = systemErrorInfoInterface.getCode();
        this.errorMsg = systemErrorInfoInterface.getMessage();
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

}
