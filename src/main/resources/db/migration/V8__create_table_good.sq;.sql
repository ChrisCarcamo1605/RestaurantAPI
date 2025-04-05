CREATE TABLE goods (
                       good_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       price DECIMAL(10,2) NOT NULL,
                       stock DOUBLE NOT NULL,
                       supplier_id BIGINT,
                       good_type VARCHAR(20) NOT NULL,
                       measurement_unit VARCHAR(10) NOT NULL,
                       active BOOLEAN NOT NULL DEFAULT TRUE,
                       FOREIGN KEY (supplier_id) REFERENCES supplier(supplier_id)
);