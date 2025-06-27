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
 * 用户档案表，存储社交账号信息
 * </p>
 *
 * @author System
 * @since 2025-03-03
 */
@Getter
@Setter
@TableName("profile")
@Schema(description = "用户档案表，存储社交账号信息")
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识用户档案")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "Instagram 账号")
    @TableField("instagram")
    private String instagram;

    @Schema(description = "Facebook 账号")
    @TableField("facebook")
    private String facebook;

    @Schema(description = "微信号")
    @TableField("wechat")
    private String wechat;

    @Schema(description = "手机号")
    @TableField("mobile")
    private String mobile;

    @Schema(description = "电子邮件")
    @TableField("email")
    private String email;

    @Schema(description = "关联用户 ID")
    @TableField("auth_users_id")
    private String authUsersId;

    @Schema(description = "关联犬舍 ID")
    @TableField("kennel_id")
    private Long kennelId;
}
