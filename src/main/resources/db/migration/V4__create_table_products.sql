CREATE TABLE PRODUCTS
(
    product_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name             varchar(50)    not null unique,
    price_Cost       decimal(10, 2) not null,
    price_Sell       decimal(10, 2) not null,
    MEASUREMENT_UNIT VARCHAR(20)    NOT NULL
);