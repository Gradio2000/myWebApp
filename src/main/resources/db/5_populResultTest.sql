DROP TABLE IF EXISTS resultTests CASCADE ;

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




DROP TABLE IF EXISTS registr_test CASCADE ;
create table registr_test
(
    registr_id serial not null
        constraint registr_test_pk
            primary key,
    attempt_id integer,
    ques_id    integer
);


create unique index registr_test_registr_id_uindex
    on registr_test (registr_id);