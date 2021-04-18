DROP TABLE IF EXISTS answers CASCADE ;

create table answers

(
    answer_id serial not null primary key,
    answer_name VARCHAR not null,
    question_id int constraint answers_questions_question_id_fk
        references questions
        on update cascade on delete cascade,
    is_right boolean not null default false

);

create unique index answers_answer_id_uindex on answers (answer_id);

INSERT INTO answers (answer_name, question_id, is_right)
VALUES ('У осла 4 ноги', 1, true), ('У осла 5 ног', 1, false), ('У осла 3 ноги', 1, false),
       ('Ответ на вопрос 2', 2, false), ('Ответ на вопрос 2', 2, true), ('Ответ на вопрос 2', 2, false),
       ('Ответ на вопрос 3', 3, false), ('Ответ на вопрос 3', 3, false), ('Ответ на вопрос 3', 3, true);