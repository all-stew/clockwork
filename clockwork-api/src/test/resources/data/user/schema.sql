CREATE TABLE `users`
(
    `id`           BIGINT       NOT NULL COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
    `username`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '昵称',
    `password`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '密码',
    `status`       TINYINT      NOT NULL DEFAULT 0 COMMENT '状态',
    `email`        VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '邮箱',
    `phone_number` VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '手机号',
    `avatar`       VARCHAR(128) NOT NULL DEFAULT '' COMMENT '头像',
    `deleted`      TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_time` INT          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_time` INT          NOT NULL DEFAULT 0 COMMENT '更新时间'
) ENGINE = INNODB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';