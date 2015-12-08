import com.speedment.config.parameters.*

name = "sql696688";
packageLocation = "src/main/java";
packageName = "com.company.speedment.test";
enabled = true;
dbms {
    ipAddress = "sql6.freesqldatabase.com";
    name = "db0";
    port = 3306;
    typeName = "MySQL";
    username = "sql696688";
    enabled = true;
    schema {
        columnCompressionType = ColumnCompressionType.NONE;
        fieldStorageType = FieldStorageType.WRAPPER;
        name = "sql696688";
        schemaName = "sql696688";
        storageEngineType = StorageEngineType.ON_HEAP;
        defaultSchema = false;
        enabled = true;
        table {
            columnCompressionType = ColumnCompressionType.INHERIT;
            fieldStorageType = FieldStorageType.INHERIT;
            name = "borrowed";
            storageEngineType = StorageEngineType.INHERIT;
            tableName = "borrowed";
            enabled = true;
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "ref";
                typeMapper = com.speedment.internal.core.config.mapper.identity.IntegerIdentityMapper.class;
                autoincrement = true;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "employeeid";
                typeMapper = com.speedment.internal.core.config.mapper.identity.ShortIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "book";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            primaryKeyColumn {
                name = "ref";
                enabled = true;
            }
            index {
                name = "FK_borrowed";
                enabled = true;
                unique = false;
                indexColumn {
                    name = "employeeid";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
            index {
                name = "PRIMARY";
                enabled = true;
                unique = true;
                indexColumn {
                    name = "ref";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
            foreignKey {
                name = "FK_borrowed";
                enabled = true;
                foreignKeyColumn {
                    foreignColumnName = "id";
                    foreignTableName = "employee";
                    name = "employeeid";
                    enabled = true;
                }
            }
        }
        table {
            columnCompressionType = ColumnCompressionType.INHERIT;
            fieldStorageType = FieldStorageType.INHERIT;
            name = "employee";
            storageEngineType = StorageEngineType.INHERIT;
            tableName = "employee";
            enabled = true;
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "id";
                typeMapper = com.speedment.internal.core.config.mapper.identity.ShortIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "firstname";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "lastname";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "birthdate";
                typeMapper = com.speedment.internal.core.config.mapper.identity.DateIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = true;
            }
            primaryKeyColumn {
                name = "id";
                enabled = true;
            }
            index {
                name = "PRIMARY";
                enabled = true;
                unique = true;
                indexColumn {
                    name = "id";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
            index {
                name = "idx_lastname";
                enabled = true;
                unique = false;
                indexColumn {
                    name = "lastname";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
        }
        table {
            columnCompressionType = ColumnCompressionType.INHERIT;
            fieldStorageType = FieldStorageType.INHERIT;
            name = "user";
            storageEngineType = StorageEngineType.INHERIT;
            tableName = "user";
            enabled = true;
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "id";
                typeMapper = com.speedment.internal.core.config.mapper.identity.LongIdentityMapper.class;
                autoincrement = true;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "name";
                typeMapper = com.speedment.internal.core.config.mapper.identity.StringIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = false;
            }
            column {
                columnCompressionType = ColumnCompressionType.INHERIT;
                fieldStorageType = FieldStorageType.INHERIT;
                name = "age";
                typeMapper = com.speedment.internal.core.config.mapper.identity.IntegerIdentityMapper.class;
                autoincrement = false;
                enabled = true;
                nullable = false;
            }
            primaryKeyColumn {
                name = "id";
                enabled = true;
            }
            index {
                name = "PRIMARY";
                enabled = true;
                unique = true;
                indexColumn {
                    name = "id";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
            index {
                name = "name";
                enabled = true;
                unique = true;
                indexColumn {
                    name = "name";
                    orderType = OrderType.ASC;
                    enabled = true;
                }
            }
        }
    }
}