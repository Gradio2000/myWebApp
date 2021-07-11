DROP TABLE IF EXISTS positions cascade;

CREATE TABLE positions
    (
       id_position serial not null primary key,
       position VARCHAR not null
);
create unique index positions_position_id_uindex
    on tests ("test_id");

INSERT INTO positions (position) VALUES ('Начальник банка');
INSERT INTO positions (position) VALUES ('Заместитель начальника банка');
INSERT INTO positions (position) VALUES ('Начальник кассы');
INSERT INTO positions (position) VALUES ('Ведущий экономист');
INSERT INTO positions (position) VALUES ('Экономист 1 категории');
INSERT INTO positions (position) VALUES ('Ведущий инженер');