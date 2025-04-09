-- tabla mesa
create table tables (
                      id_table        bigint auto_increment primary key,
                      capacity      int not null,
                      active    boolean not null
);

-- tabla cliente
create table customers (
                         id_customer     bigint auto_increment primary key,
                         name         varchar(100) not null,
                         table_fk        bigint,
                         constraint fk_mesa_customer
                             foreign key ( table_fk ) references tables(id_table)
);
