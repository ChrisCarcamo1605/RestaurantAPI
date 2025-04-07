CREATE TABLE IF NOT EXISTS Venta (
                                     idVenta BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     bill_id BIGINT NOT NULL,
                                     active BOOLEAN DEFAULT TRUE
);