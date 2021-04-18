DROP TABLE IF EXISTS questions CASCADE ;

create table questions
(
    question_id serial not null primary key,
    question_name VARCHAR not null,
        test_id       integer
        constraint questions_tests_test_id_fk
        references tests
        on update cascade on delete cascade
);

create unique index questions_question_id_uindex
    on questions ("question_id");

INSERT INTO questions (question_id, question_name) VALUES (0, '');
INSERT INTO questions (question_name, test_id) VALUES ('Солько ног у осла?', 1);
INSERT INTO questions (question_name, test_id) VALUES ('Вопрос 2', 2);
INSERT INTO questions (question_name, test_id) VALUES ('Вопрос 3', 3);