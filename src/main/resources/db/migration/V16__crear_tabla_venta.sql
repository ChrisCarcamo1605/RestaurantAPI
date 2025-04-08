CREATE TABLE IF NOT EXISTS Sale (
                                     id_Sale BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     bill_id BIGINT NOT NULL,
                                     active BOOLEAN DEFAULT TRUE
);