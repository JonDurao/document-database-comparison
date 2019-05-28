package jdurao.kschool.util;

import org.hibernate.dialect.PostgreSQL10Dialect;

import java.sql.Types;

public class MyPostgresDialect extends PostgreSQL10Dialect {
    public MyPostgresDialect() {
        this.registerColumnType(Types.JAVA_OBJECT, "jsonb");
    }
}
