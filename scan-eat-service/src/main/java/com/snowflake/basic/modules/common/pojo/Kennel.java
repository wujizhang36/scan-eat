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
 * 犬舍表，存储宠物犬舍信息
 * </p>
 *
 * @author System
 * @since 2025-03-03
 */
@Getter
@Setter
@TableName("kennel")
@Schema(description =  "犬舍表，存储宠物犬舍信息")
public class Kennel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识犬舍")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "犬舍名称")
    @TableField("name")
    private String name;

    @Schema(description = "犬舍英文名称")
    @TableField("name_en")
    private String nameEn;

    @Schema(description = "犬舍描述")
    @TableField("description")
    private String description;

    @Schema(description = "犬舍描述英文")
    @TableField("description_en")
    private String descriptionEn;

    @Schema(description = "犬舍头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

//    @Schema(description = "犬舍Id 临时字段")
//    @TableField("id_str")
//    private String idStr;
}
