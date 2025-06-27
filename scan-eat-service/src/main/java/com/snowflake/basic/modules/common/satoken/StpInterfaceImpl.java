package com.snowflake.basic.modules.common.satoken;

import cn.dev33.satoken.SaManager;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 *  todo 自定义权限验证接口扩展
 */
// @Component
public class StpInterfaceImpl implements StpInterface {

    // 返回一个账号所拥有的权限码集合
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getPermissionList(Object loginId, String loginType) {

        // 1. 声明权限码集合
        List<String> list = new ArrayList<>();

        // 2. 遍历角色列表，查询拥有的权限码
        for (String roleId : getRoleList(loginId, loginType)) {
            List<String> permissionList = (List<String>) SaManager.getSaTokenDao().getObject("satoken:role-find-permission:" + roleId);
            if(permissionList == null) {
                // 从数据库查询这个角色 id 所拥有的权限列表
//                permissionList = ...
                // 查好后，set 到缓存中
                SaManager.getSaTokenDao().setObject("satoken:role-find-permission:" + roleId, permissionList, 60 * 60 * 24 * 30);
            }
            list.addAll(permissionList);
        }

        // 3. 返回权限码集合
        return list;
    }

    // 返回一个账号所拥有的角色标识集合
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getRoleList(Object loginId, String loginType) {
        List<String> roleList = (List<String>)SaManager.getSaTokenDao().getObject("satoken:loginId-find-role:" + loginId);
        if(roleList == null) {
            // 从数据库查询这个账号id拥有的角色列表，
//            roleList = ...
            // 查好后，set 到缓存中
            SaManager.getSaTokenDao().setObject("satoken:loginId-find-role:" + loginId, roleList, 60 * 60 * 24 * 30);
        }
        return roleList;
    }

}