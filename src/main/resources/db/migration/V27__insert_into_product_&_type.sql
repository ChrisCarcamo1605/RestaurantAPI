INSERT INTO product_type (name)
VALUES ('Pollos'),
       ('Acompañamientos'),
       ('Bebidas'),
       ('Salsas'),
       ('Postres'),
       ('Promociones'),
       ('Extras'),
       ('Desayunos'), -- Maybe a special breakfast menu item
       ('Merchandising'), -- Pollos Hermanos branded items
       ('Ingredientes'); -- For bulk ingredient sales (less likely, but for completeness)

-- Insert 10 records into the products table
INSERT INTO products (name, price_Cost, price_Sell, MEASUREMENT_UNIT, active, description, product_Type)
VALUES ('Pollos Hermanos Famous Chicken (Whole)', 8.50, 17.99, 'Unit', 1, 'Our signature crispy fried chicken, whole bird.', 1),
       ('Pollos Hermanos Famous Chicken (Half)', 4.75, 9.99, 'Unit', 1, 'Our signature crispy fried chicken, half bird.', 1),
       ('Pollos Hermanos Spicy Chicken (Whole)', 9.25, 19.50, 'Unit', 1, 'Our signature chicken with a fiery kick, whole bird.', 1),
       ('Curly Fries (Large)', 1.50, 3.25, 'g', 1, 'Crispy and seasoned curly fries, large serving (approx. 200g).', 2),
       ('Mashed Potatoes with Gravy (Large)', 1.25, 2.99, 'g', 1, 'Creamy mashed potatoes topped with our special gravy (approx. 250g).', 2),
       ('Cole Slaw (Medium)', 0.75, 1.75, 'g', 1, 'Fresh and crunchy cole slaw, medium serving (approx. 150g).', 2),
       ('Large Soda (Various Flavors)', 0.50, 1.99, 'ml', 1, 'Your choice of refreshing soda, large size (approx. 500ml).', 3),
       ('Iced Tea (Large)', 0.40, 1.75, 'ml', 1, 'Brewed iced tea, served cold, large size (approx. 500ml).', 3),
       ('Pollos Hermanos Dipping Sauce (Large)', 0.25, 0.75, 'ml', 1, 'Our secret recipe dipping sauce, large size (approx. 50ml).', 4),
       ('Side Salad', 1.00, 2.50, 'g', 1, 'Fresh mixed greens with your choice of dressing (approx. 100g).', 2);