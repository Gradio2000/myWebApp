DROP TABLE IF EXISTS users CASCADE ;

create table users
(
    user_id serial not null primary key ,
    name VARCHAR not null,
    login VARCHAR not null,
    password VARCHAR not null,
    email VARCHAR,
    admin_role VARCHAR,
    pos_id Integer
        constraint users_positions_id_position_fk
            references positions
            on update cascade on delete cascade,
    registered boolean default false,
    key uuid not null
);


create unique index users_user_id_uindex on users ("user_id");

INSERT INTO users (name, login, password, email, admin_role, registered, key)
    VALUES ('Ласкин', 'login', '$2a$10$GnnBWLO8WT8iwXSmrfr6dOZzlpK8MJkKh9.NkOlml/LsQdBSgGxR.', 'aa@aa.aa', 'ADMIN', true, '36e88c7b-5bd2-4075-be86-f23eccf244b9');





