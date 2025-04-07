CREATE TABLE IF NOT EXISTS reservas (
                                        id_reserva BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        mesa_id BIGINT NOT NULL,
                                        cliente VARCHAR(255) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    monto_reserva DECIMAL(10, 2) NOT NULL,
    activo BOOLEAN DEFAULT TRUE
    -- , CONSTRAINT fk_reserva_mesa FOREIGN KEY (mesa_id) REFERENCES mesas(id_mesa)
    );
