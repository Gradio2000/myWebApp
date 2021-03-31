DROP TABLE IF EXISTS answers;

create table answers

(
    answer_id serial not null primary key,
    answer_name VARCHAR not null,
    quest_id int not null,
    FOREIGN KEY (quest_id) REFERENCES questions (question_id)
);

create unique index answers_answer_id_uindex on answers (answer_id);

INSERT INTO answers (answer_name, quest_id) VALUES ('У осла 4 ноги', 1);
INSERT INTO answers (answer_name, quest_id) VALUES ('У осла 5 ног', 1);
INSERT INTO answers (answer_name, quest_id) VALUES ('Ответ на вопрос 2', 2);
INSERT INTO answers (answer_name, quest_id) VALUES ('Ответ на вопрос 2', 2);
INSERT INTO answers (answer_name, quest_id) VALUES ('Ответ на вопрос 3', 3);
INSERT INTO answers (answer_name, quest_id) VALUES ('Ответ на вопрос 3', 3);