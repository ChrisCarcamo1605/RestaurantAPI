INSERT INTO users (id, username, email, password, active)
VALUES (201, 'waiter_one', 'waiter1@restaurant.com', 'waiterpass1', 1),
       (202, 'waiter_two', 'waiter2@restaurant.com', 'waiterpass2', 1),
       (203, 'waiter_three', 'waiter3@restaurant.com', 'waiterpass3', 1),
       (204, 'waiter_four', 'waiter4@restaurant.com', 'waiterpass4', 1),
       (205, 'other_user1', 'other1@example.com', 'otherpass1', 1),
       (206, 'other_user2', 'other2@example.com', 'otherpass2', 1),
       (207, 'other_user3', 'other3@example.com', 'otherpass3', 0),
       (208, 'other_user4', 'other4@example.com', 'otherpass4', 1),
       (209, 'other_user5', 'other5@example.com', 'otherpass5', 1),
       (210, 'other_user6', 'other6@example.com', 'otherpass6', 0);



INSERT INTO bills (total_amount, customer, emission_Date, done_Date, active, waiter)
VALUES (150.50, 101, '2025-04-01 10:00:00', '2025-04-01 10:30:00', 1, 201),
       (220.75, 102, '2025-04-01 11:15:00', '2025-04-01 11:45:00', 1, 202),
       (85.20, 103, '2025-04-02 13:00:00', '2025-04-02 13:25:00', 1, 201),
       (310.99, 104, '2025-04-02 14:45:00', '2025-04-02 15:20:00', 1, 203),
       (55.00, 105, '2025-04-03 18:00:00', '2025-04-03 18:15:00', 1, 202),
       (120.30, 106, '2025-04-03 19:30:00', '2025-04-03 20:00:00', 1, 204),
       (180.45, 107, '2025-04-04 09:00:00', '2025-04-04 09:40:00', 1, 201),
       (95.60, 108, '2025-04-04 12:00:00', '2025-04-04 12:20:00', 1, 203),
       (255.80, 109, '2025-04-05 15:30:00', '2025-04-05 16:05:00', 1, 202),
       (70.15, 110, '2025-04-05 17:45:00', '2025-04-05 18:00:00', 1, 204);