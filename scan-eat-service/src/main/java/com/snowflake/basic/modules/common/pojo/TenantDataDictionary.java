package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 数据字典表，存储应用程序的全局设置或特定租户的配置参数
 * </p>
 *
 */
@Data
@Builder
@TableName("tenant_data_dictionary")
@Schema(description = "数据字典表，存储应用程序的全局设置或特定租户的配置参数")
public class TenantDataDictionary {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识符，自动递增")
    @TableId(value = "config_id", type = IdType.AUTO)
    private Long configId;

    @Schema(description = "租户ID，NULL表示全局配置，非NULL表示特定租户配置")
    @TableField("tenant_id")
    private Long tenantId;

    @Schema(description = "配置项的键，唯一标识")
    @TableField("config_key")
    private String configKey;

    @Schema(description = "配置项的值，可以是任意文本")
    @TableField("config_value")
    private String configValue;

    @Schema(description = "配置项的描述")
    @TableField("description")
    private String description;

    @Schema(description = "是否启用（1:启用，0:禁用）")
    @TableField("is_validity")
    private Boolean isValidity;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "最后更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
