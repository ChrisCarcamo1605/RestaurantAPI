Create table bill_details(
    details_id bigint primary key auto_increment,
    product_id long not null,
    quantity int not null,
    total decimal(10,2) not null
);