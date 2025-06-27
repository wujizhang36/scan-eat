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
 * 登记表，存储宠物登记信息
 * </p>
 *
 * @author System
 * @since 2025-03-06
 */
@Getter
@Setter
@TableName("registration")
@Schema(description = "登记表，存储宠物登记信息")
public class Registration implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识登记信息")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "可读 ID")
    @TableField("readable_id")
    private Long readableId;

    @Schema(description = "登记状态")
    @TableField("status")
    private String status;

    @Schema(description = "登记时间")
    @TableField("registered_at")
    private LocalDateTime registeredAt;

    @Schema(description = "登记结束时间")
    @TableField("register_end")
    private LocalDateTime registerEnd;

    @Schema(description = "审核时间")
    @TableField("reviewed_at")
    private LocalDateTime reviewedAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "审核人 ID")
    @TableField("reviewed_by_id")
    private Long reviewedById;

    @Schema(description = "附件路径")
    @TableField("attachments")
    private String attachments;

    @Schema(description = "宠物 ID")
    @TableField("pet_id")
    private Long petId;

//    @Schema(description = "临时字段")
//    @TableField("idStr")
//    private String idStr;
//
//    @Schema(description = "临时字段")
//    @TableField("petIdStr")
//    private String petIdStr;
}
