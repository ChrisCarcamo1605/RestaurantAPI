INSERT INTO tables (capacity, active, number)
VALUES (4, 1, 'T1'),
       (2, 1, 'T2'),
       (6, 1, 'T3'),
       (4, 0, 'T4'),
       (2, 1, 'T5'),
       (8, 1, 'T6'),
       (4, 1, 'T7'),
       (2, 0, 'T8'),
       (6, 1, 'T9'),
       (4, 1, 'T3');

INSERT INTO customers (id_customer, name, table_fk)
VALUES (101, 'Lionel Messi', 1),
       (102, 'Cristiano Ronaldo', 2),
       (103, 'Neymar Jr', 3),
       (104, 'Kylian Mbappé', 1),
       (105, 'Robert Lewandowski', 4),
       (106, 'Erling Haaland', 2),
       (107, 'Kevin De Bruyne', 5),
       (108, 'Mohamed Salah', 3),
       (109, 'Karim Benzema', 4),
       (110, 'Vinícius Júnior', 5);

