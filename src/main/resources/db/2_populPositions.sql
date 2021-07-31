DROP TABLE IF EXISTS positions cascade;

CREATE TABLE positions
    (
       id_position serial not null primary key,
       position VARCHAR not null,
       company_id int not null
);
create unique index positions_position_id_uindex
    on tests ("test_id");

INSERT INTO positions (position, company_id) VALUES ('Администратор', 2);
INSERT INTO positions (position, company_id) VALUES ('Начальник банка', 1);
INSERT INTO positions (position, company_id) VALUES ('Заместитель начальника банка', 1);
INSERT INTO positions (position, company_id) VALUES ('Начальник кассы', 1);
INSERT INTO positions (position, company_id) VALUES ('Ведущий экономист', 1);
INSERT INTO positions (position, company_id) VALUES ('Экономист 1 категории', 2);
INSERT INTO positions (position, company_id) VALUES ('Ведущий инженер', 2);
