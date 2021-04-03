DROP TABLE IF EXISTS questions CASCADE ;

create table questions
(
    question_id serial not null primary key,
    question_name VARCHAR not null
);

create unique index questions_question_id_uindex
    on questions ("question_id");

INSERT INTO questions (question_id, question_name) VALUES (0, '');
INSERT INTO questions (question_name) VALUES ('Солько ног у осла?');
INSERT INTO questions (question_name) VALUES ('Вопрос 2');
INSERT INTO questions (question_name) VALUES ('Вопрос 3');