DROP TABLE IF EXISTS attemptTests;

CREATE TABLE attemptTests (
    attempt_id serial not null primary key,
    date_time timestamp not null,
    test_id int not null,
    user_id int not null
);

create unique index attemptTests_attempt_id_uindex on attemptTests ("attempt_id");

INSERT INTO attemptTests (date_time, test_id, user_id) VALUES
    ('01.01.2021 15:00', 1, 1);