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
 * 活动投票记录表
 * </p>
 *
 * @author System
 * @since 2025-04-18
 */
@Getter
@Setter
@TableName("activity_vote")
@Schema(description = "活动投票记录表")
public class ActivityVote implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "活动 ID（关联 activity 表）")
    @TableField("activity_id")
    private Long activityId;

    @Schema(description = "投票用户 ID（可为 NULL 表示匿名投票）")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "投票目标 ID（例如作品、选手等）")
    @TableField("target_id")
    private Long targetId;

    @Schema(description = "投票数量（默认 0）")
    @TableField("vote_count")
    private Integer voteCount;

    @Schema(description = "投票类型（0=免费，1=付费）")
    @TableField("vote_type")
    private Byte voteType;

    @Schema(description = "若票数相同，系统依据 预设权重 进行排序。数字越大，排序越靠前。")
    @TableField("sort")
    private Integer sort;

    @Schema(description = "投票时间")
    @TableField("created_at")
    private LocalDateTime createdAt;
}
