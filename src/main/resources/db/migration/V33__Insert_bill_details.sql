-- !Ups
-- INSERT para bill_details (ejemplo con datos estáticos)
INSERT INTO bill_details (bill_id, product_id, quantity, unit_price, total, active)
VALUES
    (1, 5, 2, 12.50, 25.00, 1),  -- Factura 1, Producto 5
    (1, 8, 1, 3.50, 3.50, 1),     -- Factura 1, Producto 8
    (2, 3, 3, 8.75, 26.25, 1);    -- Factura 2, Producto 3

-- Opción con JOIN a productos (para obtener precios automáticamente)
INSERT INTO bill_details (bill_id, product_id, quantity, unit_price, total, active)
SELECT
    1, p.id, 2, p.price_sell, (p.price_sell * 2), 1
FROM products p
WHERE p.id IN (5, 8);  -- IDs de productos existentes