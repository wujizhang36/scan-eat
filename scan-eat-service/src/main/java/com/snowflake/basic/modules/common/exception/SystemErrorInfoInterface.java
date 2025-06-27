package com.snowflake.basic.modules.common.exception;


/**
 * @author System
 * @description <p>
 * 服务接口类
 * </p>
 */
public interface SystemErrorInfoInterface {

    /**
     * 错误码
     *
     * @return
     */
    Integer getCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getMessage();
}
