create table supplier
(
    supplier_id      bigint primary key auto_increment,
    name varchar(20)  not null unique,
    contact   varchar(50)  not null unique,
    address varchar(200) not null,
    active bit(1) not null
)