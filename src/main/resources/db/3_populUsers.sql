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
    key uuid not null,
    company_id int not null
);

alter table users
    add constraint users_positions_id_position_fk
        foreign key (pos_id) references positions
            on update cascade;

create unique index users_user_id_uindex on users ("user_id");

INSERT INTO users (name, login, password, email, admin_role, pos_id, registered, key, company_id)
    VALUES ('Админ1', 'login', '$2a$10$GnnBWLO8WT8iwXSmrfr6dOZzlpK8MJkKh9.NkOlml/LsQdBSgGxR.', 'aa@aa.aa', 'ADMIN', 7, true, '36e88c7b-5bd2-4075-be86-f23eccf244b9', '1');

INSERT INTO users (name, login, password, email, admin_role, pos_id, registered, key, company_id)
VALUES ('Админ2', 'login', '$2a$10$PgHc1un9pFF8EaF254lixOGvCiZTAEOt1tE6dmbvA4qdlBKTJ98Ji', 'aa@aa.aa', 'ADMIN', 7, true, '36e88c7b-5bd2-4075-be86-f23eccf244b9', '2');



