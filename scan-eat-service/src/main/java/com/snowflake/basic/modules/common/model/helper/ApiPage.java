package com.snowflake.basic.modules.common.model.helper;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @param <T>
 * @author PC
 */
@Data
public class ApiPage<T> implements Serializable {
    /**
     * 符合条件的记录总数。
     */
    protected long total;

    /**
     * 每页的数据量大小。
     */
    protected long pageSize = 10;

    /**
     * 当前页码，从1开始。
     */
    protected long currentPage = 1;

    /**
     * 总页数，根据总记录数和每页大小计算得出。
     */
    protected long pages;

    /**
     * 当前页的记录。
     */
    protected List<T> records;
}
