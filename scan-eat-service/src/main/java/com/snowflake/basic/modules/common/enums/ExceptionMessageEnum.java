package com.snowflake.basic.modules.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * 系统提示及异常信息
 **/
@Getter
@AllArgsConstructor
public enum ExceptionMessageEnum {

    ERROR_000000(000000, "system.error"), // 系统未知异常

    //    100000 - 199999  系统基础相关明确错误信息
    ERROR_100000(100000, "user.register.name.repeat.error"), // 用户名已存在
    ERROR_100001(100001, "user.register.phone.repeat.error"), // 手机号重复
    ERROR_100002(100002, "user.login.authenticate.not.found.error"), // 用户不存在
    ERROR_100003(100003, "user.invalid.browser.fingerprint.error"), // 浏览器指纹无效
    ERROR_100004(100004, "user.login.not.enabled.error"), // 用户未启用，请联系管理员
    ERROR_100005(100005, "user.captcha.error"), // 验证码错误
    ERROR_100006(100006, "user.captcha.expired.error"), //  验证码过期
    ERROR_100007(100007, "user.login.success"), // 登录成功
    ERROR_100008(100008, "user.login.failed.error"), // 登录失败
    ERROR_100009(100009, "user.login.empty.username.or.password.error"), // 用户名或密码不允许为空
    ERROR_100010(100010, "user.password.empty.old.or.new.error"), // 旧密码或新密码不允许为空
    ERROR_100011(100011, "user.password.incorrect.error"), // 密码错误
    ERROR_100012(100012, "user.login.expired.error"), // 您的登录已过期，请重新登录
    ERROR_100013(100013, "user.login.kicked.error"), // 您的账号已停用，请联系管理员
    ERROR_100014(100014, "user.frequent.login.error"), // 注册太频繁啦，请休息一下，稍后再试～
    ERROR_100015(100015, "user.other.frequent.operation.error"), // 操作太频繁啦，请休息一下，稍后再试～
    ERROR_100016(100016, "user.login.conflict.kickout.error"), // 登录冲突导致被踢出



    //    201000 - 201999  犬舍错误信息
    ERROR_201000(201000, "kennel.kennelNameRequired.error"),// 犬舍名不允许为空
    ERROR_201001(201001, "chat.group.create.groupName.error"),
    ERROR_201002(201002, "chat.group.find.userId.error"),

    //    202000 - 202999  宠物错误信息
    ERROR_202000(202000, "chat.group.create.member.size.error"),
    ERROR_202001(202001, "chat.group.create.groupName.error"),
    ERROR_202002(202002, "chat.group.find.userId.error"),

    //    203000 - 202999  活动错误信息
    ERROR_203000(203000, "activity.invalidVote.error"), // 非法投票
    ERROR_203001(203001, "activity.voteNotEnough.error"), // 票数不足，请充值或等待免费票刷新
    ERROR_203002(203002, "activity.not.started.error"), // 暂时没有可参与的活动，敬请期待！
    ERROR_203003(203003, "activity.not.pet.error") // 搜索的宠物不存在，换个宠物试试
    ;


    private final Integer errorCode;
    private final String languageKey;

    public static ExceptionMessageEnum getExceptionMessageEnum(String languageKey) {
        // 默认返回值
        ExceptionMessageEnum result = ERROR_000000;

        // 如果 languageKey 为空，则返回默认值
        if (StringUtils.isEmpty(languageKey)) {
            return result;
        }

        // 遍历所有枚举值
        ExceptionMessageEnum[] array = ExceptionMessageEnum.values();
        for (ExceptionMessageEnum enumValue : array) {
            // 如果找到了匹配的枚举值，则返回该枚举值
            if (enumValue.getLanguageKey().equals(languageKey)) {
                return enumValue;
            }
        }

        // 如果没有找到匹配的枚举值，则返回默认值
        return result;
    }


}
