-- тестовые данные, добавляемые при запуске

insert into users (username, password)
values ('admin', '$2a$10$TLtccjJ3WncyxfPzYfYeh.6sHOfepzLeu5JCd9lajRphX/B.yC6B2'), -- bcrypt "admin"
       ('kirill', '$2a$10$pSBAXpWp/xwxl74yvTPun.69MS0y8W9c7kKJfw2n.gFJ3OMhQi8oW'), -- bcrypt "pass123"
       ('anna', '$2a$10$zqziPFP.mPH4NbhazoB8I.UxNENc.uohYUfEZAuh30B7FiDvtZtAm'); -- bcrypt "anna1979"

insert into user_roles (app_user_id, role)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_POSTS'),
       (2, 'ROLE_USERS'),
       (3, 'ROLE_ALBUMS');