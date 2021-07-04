DROP TABLE IF EXISTS attemptTests CASCADE;

create table attempttests (
    attempt_id serial not null constraint attempttests_pkey primary key,
    date_time  timestamp not null,
    test_id integer
        not null constraint attempttests_tests_test_id_fk references tests
            ON DELETE CASCADE ON UPDATE CASCADE,
    user_id integer
        not null constraint attempttests_users_user_id_fk references users
            ON DELETE CASCADE ON UPDATE CASCADE,
    time_attempt integer
);

create unique index attempttests_attempt_id_uindex
    on attempttests (attempt_id);

INSERT INTO attemptTests (date_time, test_id, user_id, time_attempt) VALUES
    ('01.01.2021 15:00', 1, 1, 0);