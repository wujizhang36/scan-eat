/*
 * @description
 * @author System
 * @version 1.0
 */

package com.snowflake.basic.modules.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * 接口结果实体封装模型
 *
 * <p>
 * 有时我们关注结果是否成功，如果错误，不作处理；
 * 有时，我们需要对错误进行处理，考虑到这个问题，
 * 我们增加了这个实体，并且增加了属性：code
 * </p>
 *
 * <p>
 * 参数code的类型，我们允许是泛型，可以根据自己的系统进行合理规划
 * </p>
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ApiResultModel<T> implements Serializable {


    @Serial
    private static final long serialVersionUID = 4173621868035511882L;
    /**
     * 返回码
     */
    private Integer code;

    /**
     * 成功的标志，true / false
     */
    private Boolean success = false;

    /**
     * 描述信息
     */
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, Object> dropdownValues;

    /**
     * 数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

}