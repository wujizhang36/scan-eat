package com.snowflake.basic.modules.common;

public class Constant {

    public static class DeviceConst {
        public static final String CLIENT = "client";
        public static final String MANAGE = "manage";
    }

    public static class ClientCommonConst {

        // 客户端
        public static final String CLIENT_KEY = "client:";

        // 随机验证码
        public static final String IMG_VERIFICATION_CODE = CLIENT_KEY + "img_verification_code:";

        // 浏览器指纹24小时内注册次数
        public static final String BROWSER_FINGERPRINT_REGISTER_TOTAL = CLIENT_KEY + "browser_fingerprint_register_total:";

        // 浏览器指纹2分钟内尝试登录次数
        public static final String BROWSER_FINGERPRINT_TRY_LOGIN_TOTAL = CLIENT_KEY + "browser_fingerprint_try_login_total:";
    }

    public static class Regex {
        public final static String EMAIL_REGEX = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/"; // 邮箱验证
        public final static String MOBILE_REGEX = "^1[3-9]\\\\d{9}$"; // 手机号验证

        public final static String NUMBER_REGEX = "^\\d+$"; // 只能为数字

        public final static String NUMBER_IS_ACTIVE = "^[01]$"; // 只能为数字 0或1
    }


    public static class DateTime {
        /**
         * 默认日期时间格式
         */
        public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        /**
         * 默认日期格式
         */
        public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
        /**
         * 默认时间格式
         */
        public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    }
}
