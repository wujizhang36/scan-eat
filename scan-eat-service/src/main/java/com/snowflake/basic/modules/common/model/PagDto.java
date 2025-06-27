package com.snowflake.basic.modules.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
public class PagDto implements Serializable {

    /**
     * 每页的数据量大小。
     */
    @Schema(description = "每页的数据量大小。", defaultValue = "10")
    protected long pageSize = 10;

    /**
     * 当前页码，从1开始。
     */
    @Schema(description = "当前页码", defaultValue = "1")
    protected long currentPage = 1;
}
