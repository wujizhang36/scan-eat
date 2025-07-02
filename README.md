bully-registry-backend 是一个多模块的 Spring Boot 微服务项目，使用了 Spring Boot 3、Spring Cloud 2023、MyBatis-Plus、Redis、Redisson、Sa-Token 等流行技术栈。

---

###  项目结构

这个 Maven 项目是一个父模块（parent POM），打包类型为 <packaging>pom</packaging>，包含以下三个子模块：

1.  bully-registry-client-app
   → 客户端接口应用，比如对外提供 API 的服务。

2.  bully-registry-manage-app
   → 后台管理服务，提供管理界面或管理 API。

3.  bully-registry-service
   → 核心服务层，封装了公共业务逻辑、数据库访问、工具类等。

---

###  技术栈

#### 1. Java 与 Spring 框架

* Java 17
* Spring Boot 3.3.10
* Spring Cloud 2023.0.3
* Spring Cloud Alibaba 2022.0.0.0（用于注册中心、配置中心、限流等）

#### 2. 数据访问

* MyBatis-Plus 3.5.5
* MyBatis Spring Boot Starter 3.0.3（MyBatis 与 Spring Boot 集成）
* 数据库连接池使用 Druid 1.2.24
* 数据库驱动使用 MySQL Connector 8.0.33

#### 3. 权限管理

* 使用了 Sa-Token 1.40.0，轻量级权限认证框架，支持 RBAC、Session、Token、注解鉴权等。

#### 4. 分布式缓存与锁

* Redis（通过 spring-boot-starter-data-redis 整合）
* Redisson 3.45.0，用于分布式锁、分布式对象、延迟队列等

#### 5. 文档工具

* Knife4j 4.5.0，用于自动生成 OpenAPI 3 接口文档，方便前后端联调

---

###  测试与构建

* 测试框架：Junit + Spring Boot Test
* 构建插件：

  * maven-compiler-plugin：Java 编译
  * spring-boot-maven-plugin：Spring Boot 打包
  * maven-dependency-plugin：依赖分析
  * versions-maven-plugin：版本管理
  * maven-surefire-plugin：单元测试（配置了跳过测试）

---

###  仓库配置

* Nexus 私服地址：

---

