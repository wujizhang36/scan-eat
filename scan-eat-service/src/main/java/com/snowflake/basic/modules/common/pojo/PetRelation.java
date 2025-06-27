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
 * 宠物关系表，存储父母-子女关系
 * </p>
 *
 * @author System
 * @since 2025-03-06
 */
@Getter
@Setter
@TableName("pet_relation")
@Schema(description = "宠物关系表，存储父母-子女关系")
public class PetRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键 ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "子宠物 ID")
    @TableField("child_id")
    private Long childId;

    @Schema(description = "父宠物 ID")
    @TableField("parent_id")
    private Long parentId;

    @Schema(description = "父母类型")
    @TableField("relation_type")
    private String relationType;

    public PetRelation() {
    }

    // 方便创建对象的自定义构造函数
    public PetRelation(Long childId, Long parentId, String relationType) {
        this.childId = childId;
        this.parentId = parentId;
        this.relationType = relationType;
    }
}
