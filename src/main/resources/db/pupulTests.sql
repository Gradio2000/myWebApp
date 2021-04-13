DROP TABLE IF EXISTS tests CASCADE ;

create table tests
(
    test_id serial not null primary key,
    test_name VARCHAR not null
);

create unique index tests_test_id_uindex
    on tests ("test_id");

INSERT INTO tests (test_name) VALUES ('Знание установленного нормативными актами Банка России порядка ' ||
                                      'ведения эмиссионных и кассовых операций, хранения и перевозки ' ||
                                      'банкнот и монеты резервных фондов, наличных денег и ценностей, ' ||
                                      'обслуживания банкоматов');
INSERT INTO tests (test_name) VALUES ('Для ревизии');
INSERT INTO tests (test_name) VALUES ('Совместный');