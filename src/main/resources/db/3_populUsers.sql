DROP TABLE IF EXISTS users CASCADE ;

create table users
(
    user_id serial not null primary key ,
    name VARCHAR,
    login VARCHAR not null,
    password VARCHAR not null,
    email VARCHAR,
    admin_role VARCHAR,
    pos_id Integer,
    registered boolean default false,
    key uuid not null,
    company_id int
);

alter table users
    add constraint users_positions_id_position_fk
        foreign key (pos_id) references positions
            on update cascade;

create unique index users_user_id_uindex on users ("user_id");

INSERT INTO users (name, login, password, email, admin_role, pos_id, registered, key, company_id)
    VALUES ('Админ1', '1', '$2a$10$q2wdxC5m1aynNFEDUyxYE.3WO.WYX0izrfo/ApGEd7DudkmvCewya', 'aa@aa.aa', 'ADMIN', 1, true, '36e88c7b-5bd2-4075-be86-f23eccf244b9', '1');

INSERT INTO users (name, login, password, email, admin_role, pos_id, registered, key, company_id)
VALUES ('Админ2', '2', '$2a$10$UrMRLmIoZIpUUWQStQp/.OzF1SeSf8gY7n977EeRFc408KAIelYGa', 'aa@aa.aa', 'ADMIN', 1, true, '36e88c7b-5bd2-4075-be86-f23eccf244b9', '2');



