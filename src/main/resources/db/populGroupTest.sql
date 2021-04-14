DROP TABLE IF EXISTS group_test CASCADE;

create table group_test
(
    groupTest_id serial  not null primary key,
    name varchar not null
);

create unique index "group_test_id_groupTest_uindex"
    on group_test (groupTest_id);


INSERT INTO group_test (name) VALUES ('Эмиссионная и кассовая работа');
INSERT INTO group_test (name) VALUES ('Информационная безопасность');
INSERT INTO group_test (name) VALUES ('Операционная деятельность');