package jdbal.types;

/**
 * 資料庫種類物件
 * @author 高浩馭
 * @since  JDBAL-0.0.2
 *
 */
public class DataBaseTypes {

	/**
	 * 選擇MySQL資料庫
	 */
	public static final int MySQL = 1;
	/**
	 * 選擇SQLServer資料庫
	 */
	public static final int SQLServer = 2;
	/**
	 * 選擇Oracle資料庫
	 */
	public static final int Oracle = 3;
	/**
	 * 選擇PostgreSQL資料庫
	 */
	public static final int PostgreSQL = 4;
	
	/**
	 * 選擇MySQL的Java驅動程式
	 */
	public static final String MySQLDriver = "org.gjt.mm.mysql.Driver";
	/**
	 * 選擇SQLServer的Java驅動程式
	 */
	public static final String SQLServerDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/**
	 * 選擇Oracle的Java驅動程式
	 */
	public static final String OracleDriver = "oracle.jdbc.OracleDriver";
	/**
	 * 選擇PostgreSQL的Java驅動程式
	 */
	public static final String PostgreSQLDriver = "org.postgresql.Driver";
	
	/**
	 * 選擇MySQLPort預設通訊埠
	 */
	public static final int DefaultMySQLPort = 3306;
	/**
	 * 選擇SQLServer預設通訊埠
	 */
	public static final int DefaultSQLServerPort = 1433;
	/**
	 * 選擇Oracle預設通訊埠
	 */
	public static final int DefaultOraclePort = 0;
	/**
	 * 選擇PostgreSQL預設通訊埠
	 */
	public static final int DefaultPostgreSQLPort = 5432;
	
}
