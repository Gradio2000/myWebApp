DROP TABLE IF EXISTS group_test CASCADE;

create table group_test
(
    groupTest_id serial  not null primary key,
    name varchar not null
);

create unique index "group_test_id_groupTest_uindex"
    on group_test (groupTest_id);


INSERT INTO group_test (name) VALUES ('Эмиссионная и кассовая работа');
INSERT INTO group_test (name) VALUES ('Информационная безопасность');
INSERT INTO group_test (name) VALUES ('Операционная деятельность');




DROP TABLE IF EXISTS tests CASCADE ;

CREATE TABLE tests
(
    test_id serial not null primary key,
    test_name VARCHAR not null,
    group_id int  constraint tests_group_test_id_grouptest_fk
        references group_test on update cascade on delete cascade,
    way1 integer,
    way2 integer
);

CREATE UNIQUE INDEX tests_test_id_uindex
    on tests ("test_id");

INSERT INTO tests (test_name, group_id) VALUES ('Знание установленного нормативными актами Банка России порядка ' ||
                                      'ведения эмиссионных и кассовых операций, хранения и перевозки ' ||
                                      'банкнот и монеты резервных фондов, наличных денег и ценностей, ' ||
                                      'обслуживания банкоматов', 1);
INSERT INTO tests (test_name, group_id) VALUES ('Для ревизии', 2);
INSERT INTO tests (test_name, group_id) VALUES ('Совместный', 3);


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

INSERT INTO questions (question_id, question_name) VALUES (0, '');
INSERT INTO questions (question_name, test_id) VALUES ('Сколько ног у осла?', 1);
INSERT INTO questions (question_name, test_id) VALUES ('Сколько ног у коровы?', 1);
INSERT INTO questions (question_name, test_id) VALUES ('Сколько ног у колобка?', 1);
INSERT INTO questions (question_name, test_id) VALUES ('Вопрос 4', 2);
INSERT INTO questions (question_name, test_id) VALUES ('Вопрос 5', 3);


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

INSERT INTO answers (answer_name, question_id, is_right)
VALUES ('У осла 4 ноги', 1, true),('У осла 5 ног', 1, false), ('У осла 3 ноги', 1, false),
       ('У коровы 4 ноги', 2, true),('У коровы 5 ног', 2, false), ('У коровы 3 ноги', 2, false),
       ('У колобка нет ног', 3, true),('У колобка 5 ног', 3, false), ('У колобка 3 ноги', 3, false),

       ('Ответ на вопрос 4', 4, false), ('Ответ на вопрос 4', 4, true), ('Ответ на вопрос 4', 4, false),
       ('Ответ на вопрос 5', 5, false), ('Ответ на вопрос 5', 5, false), ('Ответ на вопрос 5', 5, true);