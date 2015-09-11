package jdbal.types;

/**
 * ��Ʈw��������
 * @author ���E��
 * @since  JDBAL-0.0.2
 *
 */
public class DataBaseTypes {

	/**
	 * ���MySQL��Ʈw
	 */
	public static final int MySQL = 1;
	/**
	 * ���SQLServer��Ʈw
	 */
	public static final int SQLServer = 2;
	/**
	 * ���Oracle��Ʈw
	 */
	public static final int Oracle = 3;
	/**
	 * ���PostgreSQL��Ʈw
	 */
	public static final int PostgreSQL = 4;
	
	/**
	 * ���MySQL��Java�X�ʵ{��
	 */
	public static final String MySQLDriver = "org.gjt.mm.mysql.Driver";
	/**
	 * ���SQLServer��Java�X�ʵ{��
	 */
	public static final String SQLServerDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	/**
	 * ���Oracle��Java�X�ʵ{��
	 */
	public static final String OracleDriver = "oracle.jdbc.OracleDriver";
	/**
	 * ���PostgreSQL��Java�X�ʵ{��
	 */
	public static final String PostgreSQLDriver = "org.postgresql.Driver";
	
	/**
	 * ���MySQLPort�w�]�q�T��
	 */
	public static final int DefaultMySQLPort = 3306;
	/**
	 * ���SQLServer�w�]�q�T��
	 */
	public static final int DefaultSQLServerPort = 1433;
	/**
	 * ���Oracle�w�]�q�T��
	 */
	public static final int DefaultOraclePort = 0;
	/**
	 * ���PostgreSQL�w�]�q�T��
	 */
	public static final int DefaultPostgreSQLPort = 5432;
	
}
