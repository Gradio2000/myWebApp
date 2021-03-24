
DROP TABLE IF EXISTS users;

create table users
(
    user_id serial not null primary key,
    login VARCHAR not null,
    email VARCHAR not null,
    admin_role boolean not null default false
);


create unique index users_user_id_uindex
    on users ("user_id");

INSERT INTO users (login, email, admin_role)
    VALUES ('name1', 'aa@aa.aa', false);





