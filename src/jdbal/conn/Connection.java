package jdbal.conn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import jdbal.types.DataBaseTypes;

/**
 * ��Ʈw�쵲������
 * <p>
 * Connection ����Ψӳs����Ʈw�A²�ƤF���java api �������s���{�ǡA�|�һ����G
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
				username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 * </pre></blockquote><p>
 * ��n�����P��Ʈw���s���ɡA�ϥ�
 * <blockquote><pre>
 *     conn.close();
 * </pre></blockquote>
 * @author ���E��
 * @see    jdbal.types.DataBaseTypes
 * @since  JDBAL-0.02
 */
public class Connection {

	private int db_type;
	private String db_driver;
	private String db_user;
	private String db_password; 
	private String db_name;
	private String db_address;
	private int db_port;
	
	private java.sql.Connection conn;
	
	/**
	 * �غc�l
	 * @param dbtype ��Ʈw�����A�i�Ѧ�<a href="../types/DataBaseTypes.html">DataBaseTypes</a>���O
	 * @param dbdriver ��Ʈw�X�ʵ{���W�١A�i�Ѧ�<a href="../types/DataBaseTypes.html">DataBaseTypes</a>���O
	 * @param dbuser ��Ʈw�ϥΪ̱b��
	 * @param dbpassword ��Ʈw�ϥΪ̱K�X
	 * @param dbname ��Ʈw�W��
	 * @param dbadress ��ƮwIP��}
	 * @param port ��Ʈw�s����A�i�Ѧ�<a href="../types/DataBaseTypes.html">DataBaseTypes</a>���O
	 */
	
	public Connection(int dbtype, String dbdriver, String dbuser, String dbpassword, 
						String dbname, String dbadress, int port){
		db_type = dbtype;
		db_driver = dbdriver;
		db_user = dbuser;
		db_password = dbpassword;
		db_name = dbname;
		db_address = dbadress;
		db_port = port;
		open();
	}
	
	private void open(){
		
		String db_url = getDBUrl(db_type);
		
		try {
			Class.forName(db_driver);
			conn = DriverManager.getConnection(db_url, db_user, db_password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(conn==null)
			System.out.println("Connect failed!!");
		else
			System.out.println("Connect ok!!");
	}
	
	/**
	 * ������Ʈw�s��
	 */
	public void close(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * �]�w��Ʈw����
	 * @param type ����
	 */
	public void setDBType(int type){
		db_type = type;
	}
	
	/**
	 * �]�w��Ʈw�X�ʵ{���W��
	 * @param driver ��Ʈw�X�ʵ{���W��
	 */
	public void setDriver(String driver){
		db_driver = driver;
	}
	
	/**
	 * �]�w��Ʈw�ϥΪ̱b��
	 * @param user �ϥΪ̱b��
	 */
	public void setUser(String user){
		db_user = user;
	}
	
	/**
	 * �]�w��Ʈw�K�X
	 * @param password ��Ʈw�K�X
	 */
	public void setPassword(String password){
		db_password = password;
	}
	
	/**
	 * �]�w��Ʈw�W��
	 * @param dbName ��Ʈw�W��
	 */
	public void setDBName(String dbName){
		db_name = dbName;
	}
	
	/**
	 * �]�w��ƮwIP��}
	 * @param address IP��}
	 */
	public void setDBAdress(String address){
		db_address = address;
	}
	
	/**
	 * ���o�s������
	 * @return �s������
	 */
	public java.sql.Connection getConnection(){
		return conn;
	}
	
	/**
	 * ���o��Ʈw����
	 * @return ��Ʈw����
	 */
	public int getDBType(){
		return db_type;
	}
	
	private String getDBUrl(int db_type){
		
		String url;
		
		if(db_type == DataBaseTypes.MySQL){
			url = "jdbc:mysql://"+db_address+":"+db_port+"/"+db_name;
		}else if(db_type == DataBaseTypes.SQLServer){
			url = "jdbc:sqlserver://"+db_address+":"+db_port+";database="+db_name;
		}else if(db_type == DataBaseTypes.Oracle){
			url = "";
		}else if(db_type == DataBaseTypes.PostgreSQL){
			url = "jdbc:postgresql://"+db_address+":"+db_port+"/"+db_name;
		}else{
			url = "";
		}
		
		return url;
	}
	
	
	public static void main(String[] args) {
		long st = new Date().getTime();
		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
				"sa", "icrdc4isr19+","test","127.0.0.1",DataBaseTypes.DefaultSQLServerPort);
		conn.close();
		long et = new Date().getTime();
		System.out.println("spend time: "+(et - st)+" ms");
	}
	
}
