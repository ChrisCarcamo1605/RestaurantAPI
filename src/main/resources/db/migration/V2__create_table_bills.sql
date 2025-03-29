Create table bills(
    bill_id       bigint primary key auto_increment,
    total_amount  decimal(10, 2) not null,
    customer      varchar(25),
    emission_Date dateTime       not null,
    done_Date     DateTime       not null
);
