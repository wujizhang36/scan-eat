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
 * 文件表，存储图片和附件信息
 * </p>
 *
 * @author System
 * @since 2025-03-06
 */
@Getter
@Setter
@TableName("file")
@Schema(description = "文件表，存储图片和附件信息")
public class File implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识文件")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "文件存储 Key")
    @TableField("`key`")
    private String key;

    @Schema(description = "文件 URL")
    @TableField("url")
    private String url;

    @Schema(description = "文件名称")
    @TableField("name")
    private String name;

    @Schema(description = "文件大小（字节）")
    @TableField("size")
    private Integer size;

    @Schema(description = "犬舍 ID")
    @TableField("kennel_id")
    private Long kennelId;

    @Schema(description = "宠物图片 ID")
    @TableField("img_of_id")
    private String imgOfId;

    @Schema(description = "宠物头像 ID")
    @TableField("avatar_of_id")
    private String avatarOfId;

    @Schema(description = "文件状态")
    @TableField("status")
    private String status;

    @Schema(description = "犬ID")
    @TableField("pet_id")
    private Long petId;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
