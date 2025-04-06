-- Migración para renombrar columna good_id a consumable_id
ALTER TABLE consumables
    CHANGE COLUMN good_id consumable_id BIGINT AUTO_INCREMENT NOT NULL;

-- Actualizar constraints si es necesario (opcional)
ALTER TABLE consumables
DROP PRIMARY KEY,
ADD CONSTRAINT pk_consumables PRIMARY KEY (consumable_id);