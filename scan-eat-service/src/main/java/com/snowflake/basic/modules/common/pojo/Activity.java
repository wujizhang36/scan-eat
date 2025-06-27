package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 活动表
 * </p>
 *
 * @author System
 * @since 2025-04-08
 */
@Getter
@Setter
@TableName("activity")
@Schema(description = "活动表")
public class Activity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "活动名称")
    @TableField("name")
    private String name;

    @Schema(description = "活动名称")
    @TableField("name_en")
    private String nameEn;

    @Schema(description = "活动描述")
    @TableField("description")
    private String description;

    @Schema(description = "活动描述")
    @TableField("description_en")
    private String descriptionEn;

    @Schema(description = "活动开始时间")
    @TableField("start_time")
    private LocalDateTime startTime;

    @Schema(description = "活动结束时间")
    @TableField("end_time")
    private LocalDateTime endTime;

    @Schema(description = "活动状态（1=启用，0=禁用）")
    @TableField("status")
    private Byte status;

    @Schema(description = "投票总记录数")
    @TableField("total_votes")
    private Long totalVotes;

    @Schema(description = "图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "banner")
    @TableField("banner")
    private String banner;

    @Schema(description = "活动类型")
    @TableField("activity_type")
    private String activityType;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
