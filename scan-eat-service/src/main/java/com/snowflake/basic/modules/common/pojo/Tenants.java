package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 租户表，存储系统中所有租户的信息
 * </p>
 *
 */
@Data
@TableName("tenants")
@Schema(description = "租户表，存储系统中所有租户的信息")
public class Tenants {

    private static final long serialVersionUID = 1L;

    @Schema(description = "唯一标识符,6位随机数")
    @TableId("tenant_id")
    private Long tenantId;

    @Schema(description = "租户名称")
    @TableField("tenant_name")
    private String tenantName;

    @Schema(description = "资源访问key")
    @TableField("access_key")
    private String accessKey;

    @Schema(description = "公钥")
    @TableField("public_Key")
    private String publicKey;

    @Schema(description = "私钥")
    @TableField("private_Key")
    private String privateKey;

    @Schema(description = "AES 密钥")
    @TableField("aes_Key")
    private String aesKey;

    @Schema(description = "是否启用")
    @TableField("is_validity")
    private Boolean isValidity;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "最后更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}