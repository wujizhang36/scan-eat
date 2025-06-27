package com.snowflake.basic.manage.generator;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Arrays;
import java.util.List;

public class Generator {
    public static void main(String[] args) {
        /*
            特别注意：生成的时间类型均为：“LocalDateTime”格式，需要假如以下注解方可正常使用
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")//用于指定日期/时间类型的属性在序列化为JSON字符串时的格式。
            @JsonDeserialize(using = LocalDateTimeDeserializer.class)//用于指定反序列化时使用的自定义类，LocalDateTimeDeserializer是一个实现了JsonDeserializer接口的类，可以将JSON字符串转换为LocalDateTime对象
            @JsonSerialize(using = LocalDateTimeSerializer.class)//用于指定序列化时使用的自定义类，LocalDateTimeSerializer是一个实现了JsonSerializer接口的类，可以将LocalDateTime对象转换为JSON字符串
        */


        //数据库连接
        String url = "jdbc:mysql://47.243.195.127:3306/bully-registry?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false";//数据库url
        String username = "root";//账号
        String password = "qSUaGfSiBpr8KERSx";//密码
        //String module = "up";//模块名
        //全局配置参数
        String author = "System";//作者
        //String outputDir = System.getProperty("user.dir")+"\\"+module+"\\src\\main\\java";//指定输出目录
        String outputDir = System.getProperty("user.dir") + "/snowflake-basic-manage-app/src/main/java";//指定输出目录
//        String outputDir = "/Users/brook/codeGen";
        //包配置参数
        String parent = "";//父包名
//        String moduleName = "up";//父包模块名

        String entity = "com.snowflake.basic.manage.data.entity";//Entity 实体类包名
        String mapper = "com.snowflake.basic.manage.dao";//Mapper 包名
        String mapperXml = "./resources/mapper";//Mapper XML 包名
        String service = "com.snowflake.basic.manage.service";//Service 包名
        String serviceImpl = "com.snowflake.basic.manage.service.impl";//Service Impl 包名
        String controller = "com.snowflake.basic.manage.controller";//Controller 包名
        //要生成的数据库表
        String tag = "C";
        List<String> tables = Arrays.asList("auth_users_info");
        //开始生成
        FastAutoGenerator.create(url, username, password)
                //全局配置
                .globalConfig(builder -> {
                    builder.author(author)
                            .outputDir(outputDir)
                            .enableSwagger()//开启swagger
                            .commentDate("yyyy-MM-dd");//注释日期
                })
                //包配置
                .packageConfig(builder -> {
                    builder.parent(parent)
//                            .moduleName(moduleName)
                            .entity(entity)
                            .mapper(mapper)
                            .xml(mapperXml)
                            .service(service)
                            .serviceImpl(serviceImpl)
                            .controller(controller);
                })
                //策略配置
                .strategyConfig(builder -> {
                    builder.addInclude(tables)
                            //开启生成实体类
                            .entityBuilder()
                            .enableLombok()//开启 lombok 模型
                            .enableTableFieldAnnotation()//开启生成实体时生成字段注解
                            //开启生成mapper
                            .mapperBuilder()
                            .enableBaseResultMap()//启用 BaseResultMap 生成
                            .superClass(BaseMapper.class)//设置父类
                            .enableMapperAnnotation()//开启 @Mapper 注解
                            .formatMapperFileName(tag + "%sMapper")//格式化 mapper 文件名称
                            .formatXmlFileName(tag + "%sMapper")//格式化 xml 实现类文件名称
                            //开启生成service及impl
                            .serviceBuilder()
                            .formatServiceFileName(tag + "%sService")//格式化 service 接口文件名称
                            .formatServiceImplFileName(tag + "%sServiceImpl")//格式化 service 实现类文件名称
                            //开启生成controller
                            .controllerBuilder()
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName(tag + "%sController")//格式化文件名称
                            .enableRestStyle();
                    ;
                })
                .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                //.templateConfig(builder -> builder.controller(""))//关闭生成controller
                .execute();
    }
}

