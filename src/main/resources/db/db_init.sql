
DROP TABLE IF EXISTS users;

create table users
(
    user_id serial not null primary key,
    name VARCHAR not null,
    login VARCHAR not null,
    password VARCHAR not null,
    email VARCHAR not null,
    admin_role VARCHAR
);


create unique index users_user_id_uindex
    on users ("user_id");

INSERT INTO users (name, login, password, email, admin_role)
    VALUES ('Ласкин', 'login', '$2a$10$GnnBWLO8WT8iwXSmrfr6dOZzlpK8MJkKh9.NkOlml/LsQdBSgGxR.', 'aa@aa.aa', 'ADMIN');





