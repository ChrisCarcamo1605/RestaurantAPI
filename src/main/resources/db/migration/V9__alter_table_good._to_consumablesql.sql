-- Verificar si la tabla goods existe antes de renombrar
SET @dbname = DATABASE();
SET @tablename = 'goods';
SET @preparedStatement = (SELECT IF(
                                         (
                                             SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES
                                             WHERE TABLE_SCHEMA = @dbname AND TABLE_NAME = @tablename
                                         ) > 0,
                                         'ALTER TABLE goods RENAME TO consumables;',
                                         'SELECT 1'
                                 ));

PREPARE renameIfExists FROM @preparedStatement;
EXECUTE renameIfExists;
DEALLOCATE PREPARE renameIfExists;