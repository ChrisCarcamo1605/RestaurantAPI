-- 1. Agregar columna para precio unitario
ALTER TABLE bill_details
    ADD COLUMN unit_price DECIMAL(10,2) NOT NULL DEFAULT 0.00 AFTER quantity;

-- 2. Agregar columna para la relación con tickets/facturas (bill_id)
ALTER TABLE bill_details
    ADD COLUMN bill_id BIGINT NOT NULL AFTER details_id;



-- 4. Definir bill_id como llave foránea (asumiendo que la tabla bills existe)
ALTER TABLE bill_details
    ADD CONSTRAINT fk_billdetails_bill
        FOREIGN KEY (bill_id) REFERENCES bills(bill_id)
            ON DELETE CASCADE
            ON UPDATE CASCADE;