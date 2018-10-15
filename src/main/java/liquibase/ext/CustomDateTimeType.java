package liquibase.ext;

import liquibase.database.Database;
import liquibase.database.core.MSSQLDatabase;
import liquibase.datatype.DataTypeInfo;
import liquibase.datatype.DatabaseDataType;
import liquibase.datatype.LiquibaseDataType;
import liquibase.datatype.core.DateTimeType;

/**
 * Converter for Liquibase to ensure that the datetime2 type is used.
 *
 * @author Chris Foxley-Evans.
 * @version 1.0.
 */
@DataTypeInfo(name = "datetime",
              aliases = { "java.sql.Types.DATETIME", "smalldatetime", "datetime2" },
              minParameters = 0,
              maxParameters = 1,
              priority = LiquibaseDataType.PRIORITY_DATABASE)
public class CustomDateTimeType extends DateTimeType {

  @Override
  public DatabaseDataType toDatabaseDataType(Database database) {
    if (database instanceof MSSQLDatabase) {
      return new DatabaseDataType("datetime2");
    }
    return super.toDatabaseDataType(database);
  }
}
