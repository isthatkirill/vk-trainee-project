insert into users (username, password)
values ('admin', '$2a$10$TLtccjJ3WncyxfPzYfYeh.6sHOfepzLeu5JCd9lajRphX/B.yC6B2'), -- bcrypt "admin"
       ('kirill', '1'),
       ('anna', '1');



insert into user_roles (app_user_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_POSTS'),
       (2, 'ROLE_USERS'),
       (3, 'ROLE_ALBUMS');