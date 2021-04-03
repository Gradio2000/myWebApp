DROP TABLE IF EXISTS resultTests;

create table resulttests
(
    resulttest_id serial  not null
        constraint resulttests_pkey
            primary key,
    attempt_id    integer not null
        constraint resulttests_attempttests_attempt_id_fk
            references attempttests ON DELETE CASCADE ON UPDATE CASCADE,
    question_id   integer not null
        constraint resulttests_questions_question_id_fk
            references questions ON DELETE CASCADE ON UPDATE CASCADE,
    answer_id     integer not null
        constraint resulttests_answers_answer_id_fk
            references answers ON DELETE CASCADE ON UPDATE CASCADE
);

create unique index resulttests_resulttest_id_uindex
    on resulttests (resulttest_id);


INSERT INTO resultTests (attempt_id, question_id, answer_id) VALUES
    (1, 1, 1);