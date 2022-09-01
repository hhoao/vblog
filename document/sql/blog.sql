DROP DATABASE IF EXISTS blog;
CREATE DATABASE IF NOT EXISTS blog;
USE blog;

# 菜单表
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT,
    `parent_id`   bigint     NULL COMMENT '父级id',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    `title`       varchar(100) DEFAULT NULL COMMENT '菜单名称',
    `level`       int(4)       DEFAULT NULL COMMENT '菜单级数',
    `sort`        int(4)       DEFAULT NULL COMMENT '菜单排序',
    `name`        varchar(100) DEFAULT NULL COMMENT '前端名称',
    `icon`        varchar(200) DEFAULT NULL COMMENT '前端图标',
    `hidden`      int(1)       DEFAULT NULL COMMENT '前端隐藏',
    PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='后台菜单表';
INSERT INTO `ums_menu`
VALUES ('1', 0, '2020-02-02 14:51:50', '首页', '0', '0', 'home', 'home', '0');
INSERT INTO `ums_menu`
VALUES ('2', 7, '2020-02-07 16:29:51', '管理员列表', '1', '0', 'admin', 'ums-admin', '0');
INSERT INTO `ums_menu`
VALUES ('3', 7, '2020-02-07 16:30:13', '角色列表', '1', '0', 'role', 'ums-role', '0');
INSERT INTO `ums_menu`
VALUES ('4', 7, '2020-02-07 16:30:53', '菜单列表', '1', '0', 'menu', 'ums-menu', '0');
INSERT INTO `ums_menu`
VALUES ('5', 7, '2020-02-07 16:31:13', '资源列表', '1', '0', 'resource', 'ums-resource', '0');
INSERT INTO `ums_menu`
VALUES ('6', 0, '2020-02-07 16:31:13', '营销', '0', '0', 'sms', 'sms', '0');
INSERT INTO `ums_menu`
VALUES ('7', 12, '2020-02-07 16:31:13', '反馈列表', '1', '0', 'feedback', 'sms-feedback', '0');
INSERT INTO `ums_menu`
VALUES ('8', 0, '2020-02-07 16:31:13', '其他', '1', '0', 'oms', 'oms', '0');
INSERT INTO `ums_menu`
VALUES ('9', 20, '2020-02-07 16:31:13', '文件列表', '1', '0', 'file', 'file', '0');

-- 资源表
DROP TABLE IF EXISTS ums_resource;
CREATE TABLE IF NOT EXISTS ums_resource
(
    `id`          bigint AUTO_INCREMENT NOT NULL,
    `create_time` datetime DEFAULT NOW() COMMENT '创建时间',
    `name`        varchar(50) UNIQUE COMMENT '资源名称',
    `method`      varchar(20) COMMENT '请求方法',
    `url`         varchar(50) COMMENT '请求路径',
    `description` varchar(50) COMMENT '描述',
    UNIQUE (`method`, `url`),
    CONSTRAINT PK_RESOURCE PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = Innodb
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = UTF8MB4 COMMENT '资源表';
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (1, NOW(), '所有GET资源', '/**', 'GET', '所有GET资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (2, NOW(), '所有POST资源', '/**', 'POST', '所有POST资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (3, NOW(), '所有DELETE资源', '/**', 'DELETE', '所有DELETE资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (4, NOW(), '所有PUT资源', '/**', 'PUT', '所有PUT资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (5, NOW(), '所有PATCH资源', '/**', 'PATCH', '所有PATCH资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (7, NOW(), '插入资源', '/resource', 'POST', '插入资源');
INSERT INTO ums_resource(id, create_time, name, url, method, description)
VALUES (8, NOW(), '管理员退出登陆', '/administrator', 'DELETE', '退出登陆');

-- 角色表
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`
(
    `status`      int          DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) DEFAULT NULL COMMENT '名称',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `create_time` datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = UTF8MB4 COMMENT ='角色表';
INSERT INTO ums_role(id, name)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ums_role(id, name)
VALUES (2, 'ROLE_FACTORY');
INSERT INTO ums_role(id, name)
VALUES (3, 'ROLE_USER');
INSERT INTO ums_role(id, name)
VALUES (4, 'ROLE_CUSTOM');


-- 角色菜单关系表
DROP TABLE IF EXISTS ums_role_menu_relation;
CREATE TABLE IF NOT EXISTS ums_role_menu_relation
(
    id      bigint AUTO_INCREMENT PRIMARY KEY,
    role_id bigint,
    menu_id bigint,
    UNIQUE (menu_id, role_id),
    FOREIGN KEY (menu_id) REFERENCES ums_menu (`id`),
    FOREIGN KEY (`role_id`) REFERENCES ums_role (`id`)
) COMMENT '资源角色关系表';
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '1');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '2');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '3');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '4');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '5');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '6');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '7');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '8');
INSERT INTO ums_role_menu_relation(role_id, menu_id)
VALUES ('1', '9');

-- 资源角色关系表
DROP TABLE IF EXISTS ums_role_resource_relation;
CREATE TABLE IF NOT EXISTS ums_role_resource_relation
(
    id          bigint AUTO_INCREMENT PRIMARY KEY,
    role_id     bigint,
    resource_id bigint,
    UNIQUE (resource_id, role_id),
    FOREIGN KEY (resource_id) REFERENCES ums_resource (`id`),
    FOREIGN KEY (`role_id`) REFERENCES ums_role (`id`)
) COMMENT '资源角色关系表';
INSERT INTO ums_role_resource_relation(role_id, resource_id)
VALUES ('1', '1');
INSERT INTO ums_role_resource_relation(role_id, resource_id)
VALUES ('1', '2');
INSERT INTO ums_role_resource_relation(role_id, resource_id)
VALUES ('1', '3');
INSERT INTO ums_role_resource_relation(role_id, resource_id)
VALUES ('1', '4');
INSERT INTO ums_role_resource_relation(role_id, resource_id)
VALUES ('1', '5');

-- 表
DROP TABLE IF EXISTS ums_administrator;
create table IF NOT EXISTS ums_administrator
(
    id          bigint                 NOT NULL AUTO_INCREMENT,
    password    varchar(100)           NOT NULL COMMENT '密码',
    username    varchar(64)            NULL COMMENT '用户名',
    status      int          DEFAULT '1' COMMENT '账号启用状态: 0->禁言， 1->启用',
    create_time datetime     DEFAULT NOW() COMMENT '创建时间',
    latest_time datetime     DEFAULT NOW() COMMENT '最后登录时间',
    icon        varchar(500) DEFAULT null COMMENT '头像',
    role_id     bigint       DEFAULT 4 NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `ums_role` (`id`),
    INDEX (username),
    CHECK (`status` in ('0', '1'))
) ENGINE = InnoDB
  AUTO_INCREMENT = 1 COMMENT '管理员表';
INSERT INTO ums_administrator(`id`, password, username, status, role_id)
VALUES ('1', '$2a$10$xDpwrinpPCImweyjDMl.0.xIo9hbOXYu1xOOenyERJndMzWnmonqG', 'test', 1, 1);
INSERT INTO ums_administrator(`id`, password, username, status, role_id)
VALUES ('2', '$2a$10$xDpwrinpPCImweyjDMl.0.xIo9hbOXYu1xOOenyERJndMzWnmonqG', 'admin', 1, 1);

#文件表
DROP TABLE IF EXISTS `oms_file`;
CREATE TABLE `oms_file`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '文件id',
    `file_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件名称',
    `type`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件类型',
    `size`        double(32, 2)                                                 NULL DEFAULT NULL COMMENT '文件大小（KB）',
    `url`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '下载链接',
    `uuid`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '文件uuid',
    `enable`      tinyint                                                       NULL DEFAULT 1 COMMENT '链接是否可用（1：是 0：否）',
    `create_time` timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                                     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `name` (`file_name`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT = '文件表'
  ROW_FORMAT = Dynamic;