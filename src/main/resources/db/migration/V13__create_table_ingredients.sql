CREATE TABLE ingredients (
                             ingredient_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             consumable_id BIGINT,
                             quantity DOUBLE NOT NULL,
                             product_id BIGINT NOT NULL,
                             FOREIGN KEY fk_ingredient_consumable (consumable_id) REFERENCES consumables(consumable_id),
                             FOREIGN KEY fk_ingredient_product (product_id) REFERENCES products(product_id)
);