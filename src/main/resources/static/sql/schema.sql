-- 用户表
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    `id`            bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    gmt_create      datetime        NOT NULL comment '创建时间',
    gmt_modified    datetime        NOT NULL comment '修改时间',
    is_deleted      int             NOT NULL comment '删除标记(0: 正常, 1: 删除)',
    username        varchar(128)    NOT NULL comment '用户登录名',
    password        varchar(1024)   NOT NULL comment '密码',
    phone           varchar(32)     NULL comment '电话号码',
    email           varchar(128)    NULL comment '邮箱',
    nick            varchar(128)    NULL comment '昵称',
    gender          varchar(32)     NULL comment '性别',
    last_login_time datetime        NOT NULL comment '最后登录时间',
    `type`          varchar(32)     NOT NULL comment '账号类型(消费者, 供应商)',
    `status`        int             NOT NULL comment '状态',
    feature         json            NULL comment '扩展字段',
    UNIQUE KEY uk_username (`username`)
) ENGINE = InnoDB
  DEFAULT COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表';

-- 申报表
DROP TABLE IF EXISTS `declare`;
CREATE TABLE `declare`
(
    `id`                            bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    gmt_create                      datetime        NOT NULL comment '创建时间',
    gmt_modified                    datetime        NOT NULL comment '修改时间',
    is_deleted                      int             NOT NULL comment '删除标记(0: 正常, 1: 删除)',
    user_id                         bigint          NOT NULL comment '用户ID',
    status                          int             NOT NULL comment '状态',
    build_company_name              VARCHAR(255)    NOT NULL COMMENT '搭建公司名称',
    exhibition                      VARCHAR(255)    NOT NULL COMMENT '展会',
    exhibit_company_name            VARCHAR(255)    NOT NULL COMMENT '参展单位名称',
    exhibit_contact                 VARCHAR(32)     NOT NULL COMMENT '参展单位联系人',
    exhibit_contact_phone           VARCHAR(32)     NOT NULL COMMENT '参展单位联系人电话',
    gallery_name                    VARCHAR(255)    NOT NULL COMMENT '序馆/馆号',
    booth_number                    VARCHAR(20)     NOT NULL COMMENT '展位号',
    booth_area                      VARCHAR(20)     NOT NULL COMMENT '展位面积',
    booth_structure                 VARCHAR(10)     NOT NULL COMMENT '展台结构',
    booth_size                      VARCHAR(20)     NOT NULL COMMENT '展台尺寸（长X宽单m）',
    booth_highest_point             DECIMAL(10, 2)  NOT NULL COMMENT '最高点至地面垂直高度（m）',
    booth_widest_length             DECIMAL(10, 2)  NOT NULL COMMENT '横跨结构最大尺寸（m）',
    booth_material_desc             TEXT            NOT NULL COMMENT '展台材质说明',
    construction_site_manager       VARCHAR(255)    NOT NULL COMMENT '施工现场负责人',
    construction_site_manager_phone VARCHAR(15)     NOT NULL COMMENT '施工现场负责人电话',
    feature                         json            NULL comment '扩展字段',
    UNIQUE KEY uk_user_exhibition (`user_id`, `build_company_name`)
) ENGINE = InnoDB
  DEFAULT COLLATE = utf8mb4_0900_ai_ci COMMENT = '申报表';

-- 申报资料表
DROP TABLE IF EXISTS `declare_information_img`;
CREATE TABLE `declare_information_img`
(
    `id`         bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    gmt_create   datetime        NOT NULL comment '创建时间',
    gmt_modified datetime        NOT NULL comment '修改时间',
    is_deleted   int             NOT NULL comment '删除标记(0: 正常, 1: 删除)',
    user_id      bigint          NOT NULL comment '用户ID',
    declare_id   int             NOT NULL comment '状态',
    type         varchar(32)     NOT NULL comment '资料类型',
    path         varchar(128)    NOT NULL comment '资料地址'
) ENGINE = InnoDB
  DEFAULT COLLATE = utf8mb4_0900_ai_ci COMMENT = '申报表';

-- 申报费用项目表
DROP TABLE IF EXISTS `declare_expense_item`;
CREATE TABLE `declare_expense_item`
(
    `id`                     bigint unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    gmt_create               datetime        NOT NULL comment '创建时间',
    gmt_modified             datetime        NOT NULL comment '修改时间',
    is_deleted               int             NOT NULL comment '删除标记(0: 正常, 1: 删除)',
    user_id                  bigint          NOT NULL comment '用户ID',
    declare_id               int             NOT NULL comment '状态',
    deposit                  VARCHAR(32)     NOT NULL COMMENT '押金',
    management_fee           VARCHAR(32)     NOT NULL COMMENT '管理费',
    electric_fee             VARCHAR(64)     NOT NULL COMMENT '电费',
    electric_fee_detail      TEXT            NOT NULL COMMENT '电费明细',
    temp_electric_fee        VARCHAR(64)     NULL COMMENT '临时电费',
    temp_electric_fee_detail TEXT            NULL COMMENT '临时电费明细',
    gas_fee                  VARCHAR(64)     NULL COMMENT '气费',
    gas_fee_detail           TEXT            NULL COMMENT '气费明细',
    water_fee                VARCHAR(32)     NULL COMMENT '水费',
    network_fee              VARCHAR(64)     NULL COMMENT '网络通讯安装费',
    network_fee_detail       TEXT            NULL COMMENT '网络通讯安装费明细'
) ENGINE = InnoDB
  DEFAULT COLLATE = utf8mb4_0900_ai_ci COMMENT = '申报表';
