INSERT INTO `users` (`id`, `username`, `nickname`, `password`, `status`, `email`, `phone_number`, `avatar`, `deleted`,
                     `created_time`, `updated_time`)
VALUES (2, 'admin', 'admin', '$2a$10$rqvehVcOWBC8n9GfhyTXheQWNEn8ZL9z2v260DkGH8sVyNFdvgRZu', 0, 'admin@zhaojj11.com',
        '1234567890', '', 0, 1669042800, 1669042800),
       (3, 'user', 'user', '$2a$10$7p63uLZxpzC7dS3nnIfpYeZZE8oVmIjdcFbRfv9Se0bG5cy2x9B2u', 0, 'admin@zhaojj11.com',
        '1234567890', '', 0, 1669042800, 1669042800);

INSERT INTO `roles`(`id`, `name`, `key`, `created_time`, `updated_time`)
VALUES (1, '超级管理员', 'admin', 1670426400, 1670426400),
       (2, '普通用户', 'common', 1670426400, 1670426400);

INSERT INTO user_roles(id, user_id, role_id)
VALUES (1, 2, 1),
       (2, 3, 2);

INSERT INTO menus(id, name, url, status, permission, icon, deleted, created_time, updated_time)
VALUES (1, '管理员菜单', '/system/admin/index', 0, 'system:admin:list', '#', 0, 1670426400, 1670426400),
       (2, '普通菜单', '/system/user/index', 0, 'system:user:index', '#', 0, 1670426400, 1670426400);

INSERT INTO role_menus(id, role_id, menu_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 2);