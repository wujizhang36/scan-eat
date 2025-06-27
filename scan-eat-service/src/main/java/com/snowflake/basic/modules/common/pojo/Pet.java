package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 宠物表，存储宠物基本信息
 * </p>
 *
 * @author System
 * @since 2025-03-03
 */
@Getter
@Setter
@TableName("pet")
@Schema(description = "宠物表，存储宠物基本信息")
public class Pet implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID，唯一标识宠物")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "宠物名称")
    @TableField("name")
    private String name;

    @Schema(description = "宠物英文名称")
    @TableField("name_en")
    private String nameEn;

    @Schema(description = "繁育者")
    @TableField("breeder")
    private String breeder;

    @Schema(description = "繁育者英文名称")
    @TableField("breeder_en")
    private String breederEn;

    @Schema(description = "拥有者姓名")
    @TableField("owner_name")
    private String ownerName;

    @Schema(description = "拥有者姓名英文名称")
    @TableField("owner_name_en")
    private String ownerNameEn;

    @Schema(description = "拥有者手机号")
    @TableField("owner_mobile")
    private String ownerMobile;

    @Schema(description = "宠物类型")
    @TableField("type")
    private String type;

    @Schema(description = "宠物类型英文")
    @TableField("type_en")
    private String typeEn;

    @Schema(description = "性别")
    @TableField("gender")
    private String gender;

    @Schema(description = "出生日期")
    @TableField("birth_date")
    private LocalDateTime birthDate;

    @Schema(description = "品种")
    @TableField("breed")
    private String breed;

    @Schema(description = "品种英文")
    @TableField("breed_en")
    private String breedEn;

    @Schema(description = "颜色")
    @TableField("color")
    private String color;

    @Schema(description = "颜色英文")
    @TableField("color_en")
    private String colorEn;

    @Schema(description = "位置")
    @TableField("location")
    private String location;

    @Schema(description = "位置英文")
    @TableField("location_en")
    private String locationEn;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

    @Schema(description = "创建人 ID")
    @TableField("created_by_id")
    private Long createdById;

    @Schema(description = "犬舍 ID")
    @TableField("kennel_id")
    private Long kennelId;

//    @Schema(description = "ID-临时字段")
//    @TableField("idStr")
//    private String idStr;
//
//    @Schema(description = "创建人 ID-临时字段")
//    @TableField(value = "createdByIdStr" ,fill = FieldFill.INSERT)
//    private String createdByIdStr;
//
//    @Schema(description = "犬舍 ID-临时字段")
//    @TableField("kennelIdStr")
//    private String kennelIdStr;
}
