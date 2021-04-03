DROP TABLE IF EXISTS answers;

create table answers

(
    answer_id serial not null primary key,
    answer_name VARCHAR not null,
    question_id int not null,
    is_right boolean not null default false,
    FOREIGN KEY (question_id) REFERENCES questions (question_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

create unique index answers_answer_id_uindex on answers (answer_id);

INSERT INTO answers (answer_name, question_id, is_right)
VALUES ('У осла 4 ноги', 1, true), ('У осла 5 ног', 1, false), ('У осла 3 ноги', 1, false),
       ('Ответ на вопрос 2', 2, false), ('Ответ на вопрос 2', 2, true), ('Ответ на вопрос 2', 2, false),
       ('Ответ на вопрос 3', 3, false), ('Ответ на вопрос 3', 3, false), ('Ответ на вопрос 3', 3, true);