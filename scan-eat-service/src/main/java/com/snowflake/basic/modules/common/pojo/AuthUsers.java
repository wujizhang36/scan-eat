package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author System
 * @since 2025-03-18
 */
@Getter
@Setter
@TableName("auth_users")
@Schema(description = "用户表")
public class AuthUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户名")
    @TableField("user_name")
    private String username;

    @Schema(description = "密码（加密存储）")
    @TableField("password")
    private String password;

    @Schema(description = "状态（1=正常，0=禁用）")
    @TableField("is_active")
    private Byte isActive;

    @Schema(description = "二维码")
    @TableField("qr_code")
    private String qrCode;

    @Schema(description = "二维码key")
    @TableField("qr_code_key")
    private String qrCodeKey;

    @Schema(description = "是否为超级管理员（1=是，0=否）")
    @TableField("is_admin")
    private Integer isAdmin;

    @Schema(description = "用户类型：0=管理端用户，1=客户端用户")
    @TableField("type")
    private int type;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
}
