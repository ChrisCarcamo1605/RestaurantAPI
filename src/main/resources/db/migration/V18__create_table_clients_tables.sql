-- tabla mesa
create table mesa (
                      id_mesa        bigint auto_increment primary key,
                      capacidad      int not null,
                      activo_mesa    boolean not null
);

-- tabla cliente
create table cliente (
                         id_cliente     bigint auto_increment primary key,
                         nombre         varchar(100) not null,
                         mesa_fk        bigint,
                         constraint fk_mesa_cliente
                             foreign key (mesa_fk) references mesa(id_mesa)
);
