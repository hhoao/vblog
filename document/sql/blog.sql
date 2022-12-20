DROP DATABASE IF EXISTS blog;
CREATE DATABASE IF NOT EXISTS blog;
USE blog;


-- 资源表
DROP TABLE IF EXISTS ums_resource;
CREATE TABLE IF NOT EXISTS ums_resource
(
    `id`          BIGINT AUTO_INCREMENT NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `name`        VARCHAR(50)           NOT NULL UNIQUE COMMENT '资源名称',
    `method`      VARCHAR(20)           NOT NULL COMMENT '请求方法',
    `url`         VARCHAR(64)           NOT NULL COMMENT '请求路径',
    `description` VARCHAR(64) COMMENT '描述',
    UNIQUE (`method`, `url`),
    CONSTRAINT PK_RESOURCE PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = Innodb
  AUTO_INCREMENT = 1
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
VALUES (8, NOW(), '管理员退出登陆', '/account', 'DELETE', '退出登陆');

-- 角色表
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role`
(
    `id`          BIGINT AUTO_INCREMENT,
    `name`        VARCHAR(100) NOT NULL COMMENT '名称',
    `status`      BOOLEAN      DEFAULT FALSE COMMENT '启用状态：false->禁用；true->启用',
    `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX (`name`)
) ENGINE = INNODB
  AUTO_INCREMENT = 8
  DEFAULT CHARSET = UTF8MB4 COMMENT ='角色表';
INSERT INTO ums_role(id, name)
VALUES (1, 'ROLE_ADMIN');

-- 资源角色关系表
DROP TABLE IF EXISTS ums_role_resource_relation;
CREATE TABLE IF NOT EXISTS ums_role_resource_relation
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    role_id     BIGINT NOT NULL,
    resource_id BIGINT NOT NULL,
    UNIQUE (resource_id, role_id),
    FOREIGN KEY (resource_id) REFERENCES ums_resource (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`role_id`) REFERENCES ums_role (`id`) ON UPDATE CASCADE ON DELETE CASCADE
) AUTO_INCREMENT = 1 COMMENT '资源角色关系表';
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
DROP TABLE IF EXISTS ums_account;
create table IF NOT EXISTS ums_account
(
    `id`          BIGINT                 NOT NULL AUTO_INCREMENT,
    `password`    VARCHAR(64)            NOT NULL COMMENT '密码',
    `username`    VARCHAR(64)            NOT NULL COMMENT '用户名',
    `status`      BOOLEAN      DEFAULT TRUE COMMENT '账号启用状态: FALSE->禁言， TRUE->启用',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `latest_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后登录时间',
    `avatar`        VARCHAR(512) DEFAULT NULL COMMENT '头像',
    `role_id`     BIGINT       DEFAULT 2 NOT NULL COMMENT '角色id',
    `title`       VARCHAR(64)  DEFAULT NULL COMMENT '头衔',
    `introduce`   VARCHAR(255) DEFAULT NULL COMMENT '介绍',
    `unread_count` INT          DEFAULT 0 COMMENT '未阅读消息数',
    `notify_count` INT          DEFAULT 0 COMMENT '消息数',
    `country`     VARCHAR(16)  DEFAULT NULL COMMENT '国家',
    `address`     VARCHAR(64)  DEFAULT NULL COMMENT '地址',
    `phone`       VARCHAR(32)  DEFAULT NULL COMMENT '电话号码',
    `email`       VARCHAR(64)  DEFAULT NULL COMMENT '电子邮箱',
    `signature`   VARCHAR(255) DEFAULT NULL COMMENT '个性签名',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`role_id`) REFERENCES `ums_role` (`id`),
    INDEX (`username`),
    CHECK (`status` in ('0', '1'))
) ENGINE = INNODB
  AUTO_INCREMENT = 1 COMMENT '账户表';
INSERT INTO ums_account(`id`, password, username, status, role_id)
VALUES ('1', '$2a$10$xDpwrinpPCImweyjDMl.0.xIo9hbOXYu1xOOenyERJndMzWnmonqG', 'test', TRUE, 1);
INSERT INTO ums_account(`id`, password, username, status, role_id)
VALUES ('2', '$2a$10$xDpwrinpPCImweyjDMl.0.xIo9hbOXYu1xOOenyERJndMzWnmonqG', 'admin', TRUE, 1);

#用户标签
DROP TABLE IF EXISTS `ums_tag`;
CREATE TABLE `ums_tag`
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `label` VARCHAR(255) NOT NULL COMMENT '标签',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '标签表';
INSERT INTO `ums_tag`
VALUES ('1', '辣妹子');
INSERT INTO `ums_tag`
VALUES ('2', 'Geek');



DROP TABLE IF EXISTS `ums_account_tag_relation`;
CREATE TABLE `ums_account_tag_relation`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `tag_id`     BIGINT NOT NULL,
    `account_id` BIGINT NOT NULL,
    UNIQUE (`account_id`, `tag_id`),
    FOREIGN KEY (`tag_id`) REFERENCES `ums_tag` (`id`) ON DELETE CASCADE,
    FOREIGN KEY (`account_id`) REFERENCES `ums_account` (`id`) ON DELETE CASCADE,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '账户标签关系表';
INSERT INTO `ums_account_tag_relation`
VALUES (1, 1, 1);


#文件表
DROP TABLE IF EXISTS `oms_file`;
CREATE TABLE `oms_file`
(
    `id`          BIGINT        NOT NULL AUTO_INCREMENT COMMENT '文件id',
    `name`        VARCHAR(255)  NOT NULL COMMENT '文件名称',
    `type`        VARCHAR(255)  DEFAULT NULL COMMENT '文件类型',
    `size`        DOUBLE(32, 2) DEFAULT NULL COMMENT '文件大小（KB）',
    `url`         VARCHAR(255)  NOT NULL COMMENT '下载链接',
    `uuid`        VARCHAR(255)  NOT NULL COMMENT '文件uuid',
    `enable`      BOOLEAN       DEFAULT TRUE COMMENT '链接是否可用（1：是 0：否）',
    `create_time` TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP     NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `name` (`name`) USING BTREE
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '文件表';


# 文章表
DROP TABLE IF EXISTS `ams_article`;
CREATE TABLE `ams_article`
(
    `id`                BIGINT AUTO_INCREMENT COMMENT '文件id',
    `title`             VARCHAR(255) NOT NULL COMMENT '标题',
    `author`            VARCHAR(20) COMMENT '作者',
    `digest`            VARCHAR(255) NOT NULL COMMENT '摘要',
    `type`              TINYINT      DEFAULT 0 COMMENT '类型(0:原创, 1:翻译, 2:转载)',
    `reading_amount`    VARCHAR(255) DEFAULT 0 COMMENT '阅读量',
    `top`               BOOLEAN      DEFAULT FALSE COMMENT '是否置顶',
    `level`             TINYINT      DEFAULT 0 COMMENT '作品等级',
    `visible`           BOOLEAN      DEFAULT TRUE COMMENT '是否可见(0: 否, 1: 是)',
    `cover`             VARCHAR(255) DEFAULT NULL COMMENT '封面图片',
    `content`           TEXT         NOT NULL COMMENT '内容',
    `last_modification` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
    `create_time`       TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX (`title`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '文章';
SELECT * FROM ams_article;

DROP TABLE IF EXISTS `ams_tag`;
CREATE TABLE `ams_tag`
(
    `id`    BIGINT       NOT NULL AUTO_INCREMENT,
    `label` VARCHAR(255) NOT NULL COMMENT '标签',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 37
    COMMENT = '标签表';
INSERT INTO `ams_tag`
VALUES ('1', 'Java');
INSERT INTO `ams_tag`
VALUES ('2', '前端');

# 目录表
DROP TABLE IF EXISTS `ams_catalog`;
CREATE TABLE `ams_catalog`
(
    `id`          BIGINT AUTO_INCREMENT,
    `name`        VARCHAR(255) NOT NULL COMMENT '名称',
    `description` VARCHAR(255) DEFAULT NULL COMMENT '描述',
    `level`       TINYINT      DEFAULT 0 COMMENT '等级',
    `parent_id`   BIGINT       NULL COMMENT '父级id',
    `sort`        TINYINT      DEFAULT NULL COMMENT '菜单排序',
    `enable`      BOOLEAN      DEFAULT TRUE COMMENT '是否启用(0: 否, 1: 是)',
    `create_time` TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    FOREIGN KEY (`parent_id`) REFERENCES `ams_catalog` (`id`),
    INDEX (`name`),
    UNIQUE (`name`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '目录';
INSERT INTO `ams_catalog`(id, name, description, level, parent_id, sort, enable)
VALUES (1, '科技', '科技', 1, NULL, NULL, TRUE);
INSERT INTO `ams_catalog`(id, name, description, level, parent_id, sort, enable)
VALUES (2, '科技1', '科技', 1, NULL, NULL, TRUE);
INSERT INTO `ams_catalog`(id, name, description, level, parent_id, sort, enable)
VALUES (3, '科技2', '科技', 1, NULL, NULL, TRUE);


# 文章目录关系表
DROP TABLE IF EXISTS `ams_article_catalog_relation`;
CREATE TABLE `ams_article_catalog_relation`
(
    `id`         BIGINT AUTO_INCREMENT COMMENT '文件id',
    `article_id` BIGINT NOT NULL,
    `catalog_id` BIGINT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`article_id`) REFERENCES `ams_article` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`catalog_id`) REFERENCES `ams_catalog` (`id`) ON UPDATE CASCADE ON DELETE RESTRICT,
    UNIQUE (`article_id`, `catalog_id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '文章和目录关系表';


# 评论表
DROP TABLE IF EXISTS `ams_comment`;
CREATE TABLE `ams_comment`
(
    `id`          BIGINT AUTO_INCREMENT,
    `article_id`  BIGINT       NOT NULL,
    `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `email`       VARCHAR(255) COMMENT '电子邮箱',
    `nickname`    VARCHAR(255) NOT NULL COMMENT '昵称',
    `reference`   VARCHAR(255) NULL COMMENT '引用',
    PRIMARY KEY (`id`),
    INDEX (`nickname`),
    FOREIGN KEY (`article_id`) REFERENCES `ams_article` (`id`) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`reference`) REFERENCES ams_comment (`nickname`)
) ENGINE = INNODB
  AUTO_INCREMENT = 1
    COMMENT = '文章和目录关系表';
