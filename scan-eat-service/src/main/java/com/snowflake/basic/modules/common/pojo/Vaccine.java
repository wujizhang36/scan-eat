package com.snowflake.basic.modules.common.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 疫苗表，存储宠物的疫苗接种记录
 * </p>
 *
 * @author System
 * @since 2025-03-17
 */
@Getter
@Setter
@TableName("vaccine")
@Schema(description = "疫苗表，存储宠物的疫苗接种记录")
public class Vaccine implements Serializable {

    @Schema(description = "主键 ID，唯一标识疫苗记录")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "宠物 ID，关联 pet 表")
    @TableField("pet_id")
    private Long petId;

    @Schema(description = "疫苗名称")
    @TableField("vaccine_name")
    private String vaccineName;

    @Schema(description = "疫苗批号")
    @TableField("batch_number")
    private String batchNumber;

    @Schema(description = "生产厂家")
    @TableField("manufacturer")
    private String manufacturer;

    @Schema(description = "接种日期")
    @TableField("vaccination_date")
    private LocalDateTime vaccinationDate;

    @Schema(description = "下次接种日期")
    @TableField("next_due_date")
    private LocalDateTime nextDueDate;

    @Schema(description = "兽医名称")
    @TableField("vet_name")
    private String vetName;

    @Schema(description = "兽医诊所名称")
    @TableField("clinic_name")
    private String clinicName;

    @Schema(description = "创建时间")
    @TableField("created_at")
    private LocalDateTime createdAt;

    @Schema(description = "更新时间")
    @TableField("updated_at")
    private LocalDateTime updatedAt;

}
