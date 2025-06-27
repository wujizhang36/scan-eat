package com.snowflake.basic.modules.common.model;

import com.snowflake.basic.modules.common.model.helper.ApiResultHelper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;

/**
 * 使用MapStruct来实现将HttpEntity中的值复制到BlockchainApiResultModel对象中
 *
 *  
 * @date 2025/2/26
 **/
@Mapper
public interface HttpEntityMapper {

    HttpEntityMapper INSTANCE = Mappers.getMapper(HttpEntityMapper.class);

    ApiResultModel toApiResultModel(ResponseEntity responseEntity);

    default ApiResultModel defaultToApiResultModel(ResponseEntity responseEntity) {
        return ApiResultHelper.success(responseEntity.getStatusCode(), responseEntity);
    }

}