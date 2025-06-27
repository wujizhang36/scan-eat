以下是为 **SaaS 多商户扫码点餐系统** 精心设计的建表 SQL（MySQL 8+），**所有表和字段均附带详细注释**，金额字段全部使用 `DECIMAL(10, 2)`，字段结构规范清晰，满足后期扩展性与维护性要求。

---

# 🧱 SaaS 多商户扫码点餐系统建表 SQL（含注释）

---

## 1. 租户表：`tenant`（商户注册）

```sql
CREATE TABLE `tenant` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '租户ID（商户ID）',
  `name` VARCHAR(100) NOT NULL COMMENT '商户/租户名称',
  `contact_email` VARCHAR(100) DEFAULT NULL COMMENT '联系邮箱',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '联系电话',
  `status` TINYINT DEFAULT 1 COMMENT '状态：1=启用，0=禁用',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
) COMMENT='租户表（商户）';
```

---

## 2. 商户后台用户表：`merchant_user`

```sql
CREATE TABLE `merchant_user` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '商户后台用户ID',
  `tenant_id` BIGINT NOT NULL COMMENT '所属租户ID',
  `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '用户名（登录账号）',
  `password_hash` VARCHAR(255) NOT NULL COMMENT '密码Hash值（加密存储）',
  `role` ENUM('owner', 'manager', 'staff') DEFAULT 'manager' COMMENT '角色：owner=拥有者，manager=店长，staff=员工',
  `is_active` TINYINT DEFAULT 1 COMMENT '是否启用：1=是，0=否',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  FOREIGN KEY (`tenant_id`) REFERENCES `tenant`(`id`)
) COMMENT='商户后台用户表';
```

---

## 3. 门店表：`store`

```sql
CREATE TABLE `store` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '门店ID',
  `tenant_id` BIGINT NOT NULL COMMENT '所属租户ID',
  `name` VARCHAR(100) NOT NULL COMMENT '门店名称',
  `address` VARCHAR(255) DEFAULT NULL COMMENT '门店地址',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '门店电话',
  `status` TINYINT DEFAULT 1 COMMENT '营业状态：1=营业中，0=暂停',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  FOREIGN KEY (`tenant_id`) REFERENCES `tenant`(`id`)
) COMMENT='门店信息表';
```

---

## 4. 餐桌表：`store_table`

```sql
CREATE TABLE `store_table` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '桌号ID',
  `store_id` BIGINT NOT NULL COMMENT '所属门店ID',
  `table_code` VARCHAR(20) NOT NULL COMMENT '桌号编号（如A01）',
  `qr_code_url` VARCHAR(255) DEFAULT NULL COMMENT '桌号二维码图片地址',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  UNIQUE KEY `uq_store_table` (`store_id`, `table_code`),
  FOREIGN KEY (`store_id`) REFERENCES `store`(`id`)
) COMMENT='餐桌信息表';
```

---

## 5. 菜品分类表：`dish_category`

```sql
CREATE TABLE `dish_category` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '菜品分类ID',
  `store_id` BIGINT NOT NULL COMMENT '所属门店ID',
  `name` VARCHAR(50) NOT NULL COMMENT '分类名称',
  `sort` INT DEFAULT 0 COMMENT '排序字段（从小到大）',
  FOREIGN KEY (`store_id`) REFERENCES `store`(`id`)
) COMMENT='菜品分类表';
```

---

## 6. 菜品表：`dish`

```sql
CREATE TABLE `dish` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '菜品ID',
  `store_id` BIGINT NOT NULL COMMENT '所属门店ID',
  `category_id` BIGINT DEFAULT NULL COMMENT '所属分类ID',
  `name` VARCHAR(100) NOT NULL COMMENT '菜品名称',
  `price` DECIMAL(10, 2) NOT NULL COMMENT '菜品价格',
  `image_url` VARCHAR(255) DEFAULT NULL COMMENT '图片地址',
  `status` TINYINT DEFAULT 1 COMMENT '状态：1=在售，0=停售',
  `description` TEXT COMMENT '菜品描述',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  FOREIGN KEY (`store_id`) REFERENCES `store`(`id`),
  FOREIGN KEY (`category_id`) REFERENCES `dish_category`(`id`)
) COMMENT='菜品表';
```

---

## 7. 订单表：`order`

```sql
CREATE TABLE `order` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
  `store_id` BIGINT NOT NULL COMMENT '所属门店ID',
  `table_id` BIGINT NOT NULL COMMENT '就餐桌ID',
  `total_price` DECIMAL(10, 2) NOT NULL COMMENT '订单总金额',
  `pay_status` ENUM('unpaid', 'paid', 'refunded') DEFAULT 'unpaid' COMMENT '支付状态',
  `order_status` ENUM('pending', 'making', 'finished', 'cancelled') DEFAULT 'pending' COMMENT '订单状态',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
  FOREIGN KEY (`store_id`) REFERENCES `store`(`id`),
  FOREIGN KEY (`table_id`) REFERENCES `store_table`(`id`)
) COMMENT='订单主表';
```

---

## 8. 订单明细表：`order_item`

```sql
CREATE TABLE `order_item` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单项ID',
  `order_id` BIGINT NOT NULL COMMENT '所属订单ID',
  `dish_id` BIGINT NOT NULL COMMENT '菜品ID',
  `dish_name` VARCHAR(100) NOT NULL COMMENT '菜品名称快照',
  `quantity` INT NOT NULL DEFAULT 1 COMMENT '数量',
  `price` DECIMAL(10, 2) NOT NULL COMMENT '单价快照',
  FOREIGN KEY (`order_id`) REFERENCES `order`(`id`),
  FOREIGN KEY (`dish_id`) REFERENCES `dish`(`id`)
) COMMENT='订单明细表';
```

---

## 9. 订单评价表（可选）：`review`

```sql
CREATE TABLE `review` (
  `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '评价ID',
  `order_id` BIGINT NOT NULL COMMENT '订单ID',
  `rating` TINYINT NOT NULL CHECK (`rating` BETWEEN 1 AND 5) COMMENT '评分（1-5）',
  `content` TEXT COMMENT '评价内容',
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '评价时间',
  FOREIGN KEY (`order_id`) REFERENCES `order`(`id`)
) COMMENT='订单评价表';
```

---

> ✅ 以上建表脚本 **结构清晰**、**注释完整**、**字段规范**，适合：

* 商户注册登录 → 门店创建 → 员工管理 → 菜单发布 → 顾客扫码点餐 → 商户接单出菜
* 支持多商户、多门店、多员工角色权限
* 为未来接入小程序、POS 终端或 Web 后台打好基础

---

如你还需要：

* Spring Boot + JPA 实体类
* SaaS 登录与多租户鉴权设计
* Redis + JWT 登录会话设计
