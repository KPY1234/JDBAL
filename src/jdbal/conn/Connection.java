package jdbal.conn;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import jdbal.types.DataBaseTypes;

/**
 * 資料庫鏈結之物件
 * <p>
 * Connection 物件用來連結資料庫，簡化了原生java api 複雜的連結程序，舉例說明：
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
				username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 * </pre></blockquote><p>
 * 當要關閉與資料庫的連結時，使用
 * <blockquote><pre>
 *     conn.close();
 * </pre></blockquote>
 * @author 高浩馭
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
	 * 建構子
	 * @param dbtype 資料庫種類，可參考<a href="../types/DataBaseTypes.html">DataBaseTypes</a>類別
	 * @param dbdriver 資料庫驅動程式名稱，可參考<a href="../types/DataBaseTypes.html">DataBaseTypes</a>類別
	 * @param dbuser 資料庫使用者帳號
	 * @param dbpassword 資料庫使用者密碼
	 * @param dbname 資料庫名稱
	 * @param dbadress 資料庫IP位址
	 * @param port 資料庫連結埠，可參考<a href="../types/DataBaseTypes.html">DataBaseTypes</a>類別
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
	 * 關閉資料庫連結
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
	 * 設定資料庫種類
	 * @param type 種類
	 */
	public void setDBType(int type){
		db_type = type;
	}
	
	/**
	 * 設定資料庫驅動程式名稱
	 * @param driver 資料庫驅動程式名稱
	 */
	public void setDriver(String driver){
		db_driver = driver;
	}
	
	/**
	 * 設定資料庫使用者帳號
	 * @param user 使用者帳號
	 */
	public void setUser(String user){
		db_user = user;
	}
	
	/**
	 * 設定資料庫密碼
	 * @param password 資料庫密碼
	 */
	public void setPassword(String password){
		db_password = password;
	}
	
	/**
	 * 設定資料庫名稱
	 * @param dbName 資料庫名稱
	 */
	public void setDBName(String dbName){
		db_name = dbName;
	}
	
	/**
	 * 設定資料庫IP位址
	 * @param address IP位址
	 */
	public void setDBAdress(String address){
		db_address = address;
	}
	
	/**
	 * 取得連結物件
	 * @return 連結物件
	 */
	public java.sql.Connection getConnection(){
		return conn;
	}
	
	/**
	 * 取得資料庫種類
	 * @return 資料庫種類
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
		Connection conn = new Connection(DataBaseTypes.MySQL, DataBaseTypes.MySQLDriver, 
				"stocker", "icrd00","stock","127.0.0.1",DataBaseTypes.DefaultMySQLPort);
		conn.close();
		long et = new Date().getTime();
		System.out.println("spend time: "+(et - st)+" ms");
	}
	
}
