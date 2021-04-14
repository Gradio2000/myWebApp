DROP TABLE IF EXISTS tests CASCADE ;

CREATE TABLE tests
(
    test_id serial not null primary key,
    test_name VARCHAR not null,
    group_id int not null constraint tests_group_test_id_grouptest_fk
        references group_test
);

CREATE UNIQUE INDEX tests_test_id_uindex
    on tests ("test_id");

INSERT INTO tests (test_name, group_id) VALUES ('Знание установленного нормативными актами Банка России порядка ' ||
                                      'ведения эмиссионных и кассовых операций, хранения и перевозки ' ||
                                      'банкнот и монеты резервных фондов, наличных денег и ценностей, ' ||
                                      'обслуживания банкоматов', 1);
INSERT INTO tests (test_name, group_id) VALUES ('Для ревизии', 2);
INSERT INTO tests (test_name, group_id) VALUES ('Совместный', 3);