package com.snowflake.basic.modules.common.enums;

/**
 * 商户申请状态
 *
 * @return null
 * @date 2025/2/26
 **/
public enum MerchantApplyingStatusEnum {
    // 申请中
    APPLYING("Applying"),
    // 审核中
    UNDER_REVIEW("Under Review"),
    // 测试中
    TESTING("Testing"),
    // 已上线
    LIVE("Live"),
    // Rejected
    REJECTED("Rejected");

    private final String status;

    MerchantApplyingStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
