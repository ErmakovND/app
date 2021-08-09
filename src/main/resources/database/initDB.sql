set time zone 'UTC';

--drop table if exists users cascade;
create table if not exists users
(
    id       bigserial primary key,
    login    varchar(50)  not null,
    email    varchar(200) not null,
    password varchar(60)  not null
);

--drop table if exists posts cascade;
create table if not exists posts
(
    id       bigserial primary key,
    user_id  bigserial not null references users (id),
    text     varchar(1000),
    category varchar(10),
    date     timestamp
);

--drop table if exists replies cascade;
create table if not exists replies
(
    id      bigserial primary key,
    user_id bigserial not null references users (id),
    post_id bigserial not null references posts (id),
    text    varchar(500),
    date    timestamp
)