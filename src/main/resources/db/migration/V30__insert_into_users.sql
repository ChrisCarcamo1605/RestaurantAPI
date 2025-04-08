INSERT INTO user_types (name) VALUES
                                  ('Admin'),
                                  ('Mesero'),
                                  ('Cocinero'),
                                  ('Cajero'),
                                  ('Repartidor');




INSERT INTO users (id, username, email, password, active, type)
VALUES
    (201, 'waiter_one', 'waiter1@restaurant.com', 'waiterpass1', 1, 2),
    (202, 'waiter_two', 'waiter2@restaurant.com', 'waiterpass2', 1, 2),
    (203, 'waiter_three', 'waiter3@restaurant.com', 'waiterpass3', 1, 2),
    (204, 'waiter_four', 'waiter4@restaurant.com', 'waiterpass4', 1, 2),
    (205, 'other_user1', 'other1@example.com', 'otherpass1', 1, 1),
    (206, 'other_user2', 'other2@example.com', 'otherpass2', 1, 3),
    (207, 'other_user3', 'other3@example.com', 'otherpass3', 0, 4),
    (208, 'other_user4', 'other4@example.com', 'otherpass4', 1, 5),
    (209, 'other_user5', 'other5@example.com', 'otherpass5', 1, 3),
    (210, 'other_user6', 'other6@example.com', 'otherpass6', 0, 2);
