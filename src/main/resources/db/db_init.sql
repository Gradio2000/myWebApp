
DROP TABLE IF EXISTS users;

create table users
(
    user_id serial not null primary key,
    name VARCHAR not null,
    login VARCHAR not null,
    password VARCHAR not null,
    email VARCHAR not null,
    admin_role boolean not null default false
);


create unique index users_user_id_uindex
    on users ("user_id");

INSERT INTO users (name, login, password, email, admin_role)
    VALUES ('name1', 'login', 'password', 'aa@aa.aa', true);





