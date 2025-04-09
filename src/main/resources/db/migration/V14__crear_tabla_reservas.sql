CREATE TABLE IF NOT EXISTS reservations (
                                            id_reservation BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            table_id BIGINT NOT NULL,
                                            client VARCHAR(255) NOT NULL,
    date_time DATETIME NOT NULL,
    reservation_amount DECIMAL(10, 2) NOT NULL,
    active BOOLEAN DEFAULT TRUE
    );
