CREATE TABLE `users`
(
    `id`           BIGINT       NOT NULL COMMENT 'ID' PRIMARY KEY AUTO_INCREMENT,
    `username`     VARCHAR(64)  NOT NULL DEFAULT '' COMMENT '昵称',
    `email`        VARCHAR(256) NOT NULL DEFAULT '' COMMENT '邮箱',
    `status`       TINYINT      NOT NULL DEFAULT 0 COMMENT '状态',
    `password`     VARCHAR(256) NOT NULL DEFAULT '' COMMENT '密码',
    `deleted`      TINYINT      NOT NULL DEFAULT 0 COMMENT '删除标记',
    `created_time` INT          NOT NULL DEFAULT 0 COMMENT '创建时间',
    `updated_time` INT          NOT NULL DEFAULT 0 COMMENT '更新时间'
);