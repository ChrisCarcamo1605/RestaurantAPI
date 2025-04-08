ALTER TABLE bill_details
DROP COLUMN product_id;

-- 2. Vuelve a crear la columna con el tipo correcto (BIGINT)
ALTER TABLE bill_details
    ADD COLUMN product_id BIGINT NOT NULL AFTER details_id;

-- 3. Ahora agrega la llave foránea correctamente
ALTER TABLE bill_details
    ADD CONSTRAINT fk_billdetails_product
        FOREIGN KEY (product_id) REFERENCES products(product_id)
            ON DELETE RESTRICT
            ON UPDATE CASCADE;