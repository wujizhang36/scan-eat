package com.snowflake.basic.client.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.snowflake.basic.modules.common.model.ApiResultModel;
import com.snowflake.basic.modules.common.model.helper.ApiResultHelper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "菜单")
@Slf4j
@RestController
@RequestMapping("/menu")
public class CMenuController {

    @Operation(summary = "分类")
    @GetMapping("/categories")
    public ApiResultModel findCategories() throws JsonProcessingException {
        // 构造 categories 列表
        List<Map<String, Object>> categories = new ArrayList<>();
        categories.add(Map.of("id", 1, "name", "热菜"));
        categories.add(Map.of("id", 2, "name", "凉菜"));
        categories.add(Map.of("id", 3, "name", "饮料"));

        return ApiResultHelper.success(categories);
    }

    @Operation(summary = "")
    @GetMapping("/dishes")
    public ApiResultModel findDishes() throws JsonProcessingException {
        List<Map<String, Object>> dishes = new ArrayList<>();

        dishes.add(Map.of("id", 101, "name", "宫保鸡丁", "price", 28, "categoryId", 1));
        dishes.add(Map.of("id", 102, "name", "鱼香肉丝", "price", 26, "categoryId", 1));
        dishes.add(Map.of("id", 201, "name", "拍黄瓜", "price", 12, "categoryId", 2));
        dishes.add(Map.of("id", 202, "name", "凉拌木耳", "price", 14, "categoryId", 2));
        dishes.add(Map.of("id", 301, "name", "可乐", "price", 6, "categoryId", 3));
        dishes.add(Map.of("id", 302, "name", "雪碧", "price", 6, "categoryId", 3));

        return ApiResultHelper.success(dishes);
    }
}
