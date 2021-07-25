DROP TABLE IF EXISTS companies CASCADE;

create table companies
(
    id_company serial not null,
    company_name varchar
);

create unique index companies_id_company_uindex
    on companies (id_company);

alter table companies
    add constraint companies_pk
        primary key (id_company);

INSERT INTO companies (company_name) VALUES ('Новая компания');

DROP TABLE IF EXISTS group_test CASCADE;

create table group_test
(
    groupTest_id serial  not null primary key,
    name varchar not null,
    company_id   integer
);

create unique index "group_test_id_groupTest_uindex"
    on group_test (groupTest_id);


INSERT INTO group_test (name, company_id) VALUES ('Эмиссионная и кассовая работа', '1');
INSERT INTO group_test (name, company_id) VALUES ('Тесты на логику', 1);


DROP TABLE IF EXISTS tests CASCADE ;

CREATE TABLE tests
(
    test_id serial not null primary key,
    test_name VARCHAR not null,
    group_id int  constraint tests_group_test_id_grouptest_fk
        references group_test on update cascade on delete cascade,
    criteria double precision,
    time double precision,
    ques_amount int,
    deleted boolean,
    ques_mix boolean default true
);

CREATE UNIQUE INDEX tests_test_id_uindex
    on tests ("test_id");

INSERT INTO tests (test_name, group_id, criteria, time, deleted, ques_mix) VALUES ('Знание установленного нормативными актами Банка России порядка ' ||
                                      'ведения эмиссионных и кассовых операций, хранения и перевозки ' ||
                                      'банкнот и монеты резервных фондов, наличных денег и ценностей, ' ||
                                      'обслуживания банкоматов', 1, 30, 0, false, true);
INSERT INTO tests (test_name, group_id, criteria, time, deleted, ques_mix) VALUES ('Тест на логичское мышление (М. Войнаровский)', 2, 0, 0, false, false);
--

DROP TABLE IF EXISTS questions CASCADE ;

create table questions
(
    question_id   serial  not null
        constraint questions_pkey
            primary key,
    question_name varchar not null,
    test_id       integer
        constraint questions_tests_test_id_fk
            references tests
            on update cascade on delete cascade
);

create unique index questions_question_id_uindex
    on questions ("question_id");


DROP TABLE IF EXISTS answers CASCADE ;

create table answers

(
    answer_id   serial                not null
        constraint answers_pkey
            primary key,
    answer_name varchar               not null,
    question_id integer
        constraint answers_questions_question_id_fk
            references questions
            on update cascade on delete cascade,
    is_right    boolean default false not null

);

create unique index answers_answer_id_uindex on answers (answer_id);

