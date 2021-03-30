DROP TABLE IF EXISTS tests;

create table tests
(
    test_id serial not null primary key,
    test_name VARCHAR not null
);

create unique index tests_test_id_uindex
    on tests ("test_id");

INSERT INTO tests (test_name) VALUES ('Для работы');
INSERT INTO tests (test_name) VALUES ('Для ревизии');
INSERT INTO tests (test_name) VALUES ('Совместный');