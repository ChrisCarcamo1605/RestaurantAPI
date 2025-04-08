create table user_types
(
    type_id bigint primary key auto_increment,
    name    varchar(50) not null unique
);
