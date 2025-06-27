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
 * 用户表，存储用户基本信息
 * </p>
 *
 * @author System
 * @since 2025-03-03
 */
@Getter
@Setter
@TableName("auth_users_info")
@Schema(description = "用户表，存储用户基本信息")
public class AuthUsersInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识用户")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "auth_user表Id")
    @TableField("auth_user_id")
    private Long authUserId;

    @Schema(description = "邮箱地址")
    @TableField("email")
    private String email;

    @Schema(description = "邮箱验证时间")
    @TableField("email_verified")
    private LocalDateTime emailVerified;

    @Schema(description = "联系二维码")
    @TableField("contact_qr_code")
    private String contactQrCode;

    @Schema(description = "用户头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "免费剩余投票数")
    @TableField("free_vote_count")
    private Integer freeVoteCount;

    @Schema(description = "付费剩余投票数")
    @TableField("paid_vote_count")
    private Integer paidVoteCount = 0;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
}

