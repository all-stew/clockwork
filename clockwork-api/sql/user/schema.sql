DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`           BIGINT UNSIGNED  NOT NULL COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
    `username`     VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`     VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '昵称',
    `password`     VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '密码',
    `status`       TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '状态',
    `email`        VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '邮箱',
    `phone_number` VARCHAR(64)      NOT NULL DEFAULT '' COMMENT '手机号',
    `avatar`       VARCHAR(128)     NOT NULL DEFAULT '' COMMENT '头像',
    `deleted`      TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_time` INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_time` INT UNSIGNED     NOT NULL DEFAULT 0 COMMENT '更新时间'
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`
(
    `id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '用户id',
    `role_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户角色表';

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`
(
    `id`           BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(128)      NOT NULL DEFAULT '' COMMENT '角色名',
    `key`          VARCHAR(128)      NOT NULL DEFAULT '' COMMENT '角色权限字符串',
    `order_num`    SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `status`       TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '角色状态(0正常，1停用)',
    `remark`       VARCHAR(512)      NOT NULL DEFAULT '' COMMENT '备注',
    `deleted`      TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_time` INT UNSIGNED      NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_time` INT UNSIGNED      NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

DROP TABLE IF EXISTS `role_menus`;
CREATE TABLE `role_menus`
(
    `id`      BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    `role_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '角色id',
    `menu_id` BIGINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '菜单id',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色菜单表';

DROP TABLE IF EXISTS `menus`;
CREATE TABLE `menus`
(
    `id`           BIGINT UNSIGNED   NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(64)       NOT NULL DEFAULT '' COMMENT '菜单名称',
    `parent_id`    BIGINT UNSIGNED   NOT NULL DEFAULT 0 COMMENT '父菜单id',
    `order_num`    SMALLINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '显示顺序',
    `url`          VARCHAR(256)      NOT NULL DEFAULT NULL COMMENT '路由地址',
    `target`       TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '打开方式(0业签，1新窗口)',
    `type`         TINYINT UNSIGNED  NOT NULL DEFAULT '' COMMENT '菜单类型(C目录，M菜单，B按钮)',
    `status`       TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '菜单状态(0正常，1停用)',
    `permission`   VARCHAR(128)      NOT NULL DEFAULT '' COMMENT '权限标识',
    `icon`         VARCHAR(128)      NOT NULL DEFAULT '#' COMMENT '菜单图标',
    `remark`       VARCHAR(512)      NOT NULL DEFAULT '' COMMENT '备注',
    `deleted`      TINYINT UNSIGNED  NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_time` INT UNSIGNED      NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_time` INT UNSIGNED      NOT NULL DEFAULT 0 COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='菜单表';
