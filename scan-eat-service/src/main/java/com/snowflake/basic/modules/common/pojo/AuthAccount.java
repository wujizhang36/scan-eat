package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 账户表，存储用户的第三方账号信息
 * </p>
 *
 * @author System
 * @since 2025-04-07
 */
@Getter
@Setter
@TableName("auth_account")
@Schema(description = "账户表，存储用户的第三方账号信息")
public class AuthAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识账号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "用户 ID，关联 auth_users 表")
    @TableField("auth_users_id")
    private Long authUsersId;

    @Schema(description = "账号类型")
    @TableField("account_type")
    private String accountType;

    @Schema(description = "账号提供者")
    @TableField("provider")
    private String provider;

    @Schema(description = "提供者账户 ID")
    @TableField("provider_account_id")
    private Long providerAccountId;

    @Schema(description = "刷新令牌")
    @TableField("refresh_token")
    private String refreshToken;

    @Schema(description = "访问令牌")
    @TableField("access_token")
    private String accessToken;

    @Schema(description = "令牌过期时间（时间戳）")
    @TableField("expires_at")
    private Integer expiresAt;

    @Schema(description = "令牌类型")
    @TableField("token_type")
    private String tokenType;

    @Schema(description = "权限范围")
    @TableField("scope")
    private String scope;

    @Schema(description = "身份令牌")
    @TableField("id_token")
    private String idToken;

    @Schema(description = "会话状态")
    @TableField("session_state")
    private String sessionState;
}
