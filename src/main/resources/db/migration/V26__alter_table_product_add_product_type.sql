alter table products add product_Type bigint not null;
alter table restaurantdb.products  ADD FOREIGN KEY (product_type) references product_type(type_id) ON DELETE  CASCADE ;