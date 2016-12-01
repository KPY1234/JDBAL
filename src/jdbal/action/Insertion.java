package jdbal.action;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbal.conn.Connection;
import jdbal.structure.FieldValue;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;

/**
 * 新增資料至資料庫
 * <p>
 * Insertion 物件用來新增資料至資料庫，舉例說明：
 * <blockquote><pre>{@code
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Insertion insert = new Insertion(conn, tbname);}
 * </pre></blockquote><p>
 * 加入新增的欄位與值，使用
 * <blockquote><pre>{@code
 *     insert.addFieldValue(new FieldValue(field2, "CC001", DataTypes.String));
 *     insert.addFieldValue(new FieldValue(field3, 12, DataTypes.Number));}
 * </pre></blockquote>
 * 執行新增資料操作
 * <blockquote><pre>
 *     int result = insert.execute();
 * </pre></blockquote>
 * 上述動作可用一行敘述表示
 * <blockquote><pre>{@code
 *     int result = new Insertion(conn, tbname)
 *     			.addFieldValue(new FieldValue(field2, "CC001", DataTypes.String))
 *     			.addFieldValue(new FieldValue(field3, 12, DataTypes.Number))
 *     			.execute();}
 * </pre></blockquote>
 * 
 * 
 * @author 高浩馭
 * @see     jdbal.conn.Connection
 * @see     jdbal.structure.FieldValue
 * @since   JDBAL-0.0.2
 * 
 */
public class Insertion {
	
	private Connection db_conn;
	private String db_table;
	private ArrayList<FieldValue> fieldValues;
	
	/**
	 * 建構子
	 * @param conn 連結資料庫的物件
	 * @param tbName 資料表名稱
	 */
	public Insertion(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fieldValues = new ArrayList<FieldValue>();
	}
	
	/**
	 * 新增之欄位與值的物件
	 * @param fv 欄位與值的物件
	 * @return 自己類別
	 */
	public Insertion addFieldValue(FieldValue fv){
		fieldValues.add(fv);
		return this;
	}
	
	/**
	 * 取得資料表名稱
	 * @return 資料表名稱
	 */
	public String getTableName(){
		return db_table;
	}
	
	/**
	 * 取得所有欄位與值的物件
	 * @return 所有欄位與值的物件
	 */
	public ArrayList<FieldValue> getFieldValues(){
		return fieldValues;
	}
	
	/**
	 * 執行新增資料動作
	 * @return 加入的資料筆數
	 */
	public int execute(){
		int returnValue = 0;
		
		QueryBuilder qb = QueryBuilderCreator.getInstance(db_conn.getDBType());
		String sql = qb.insertQueryBuild(this);
		
		try {
			Statement stmt = db_conn.getConnection().createStatement();
			returnValue = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clear();
		return returnValue;
	}
	
	/**
	 * 使用sql語法執行新增資料動作
	 * @param sql sql語法字串
	 * @return 更新的數量
	 */
	public int executeBySQL(String sql){
		int returnValue = 0;
		
		try {
			Statement stmt = db_conn.getConnection().createStatement();
			returnValue = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clear();
		return returnValue;
	}
	
	/**
	 * 執行完後清除過去的新增敘述
	 */
	private void clear(){
		fieldValues = new ArrayList<FieldValue>();
	}
	
	
//	public static void main(String[] args) {
////		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
////				"sa", "sqlmgr","HSSQLDB","192.168.4.74",DataBaseTypes.DefaultSQLServerPort);
////		
////		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
////		
////		java.util.Date sd = new java.util.Date();
////		java.util.Date rd = new java.util.Date(sd.getTime()+5000);
////		
////		String SdateStr = sdf.format(sd);
////		String RdateStr = sdf.format(rd);
////		
////		int result = new Insertion(conn, "tb3000")
////					.addFieldValue(new FieldValue("recvtime", RdateStr, DataTypes.String))
////					.addFieldValue(new FieldValue("targetid", "AA001", DataTypes.String))
////					.addFieldValue(new FieldValue("seqnum", 2, DataTypes.Number))
////					.execute();
//		
//		long num = 1000002222222L;
//		
//		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
//				"sa", "icrdc4isr19+","test","127.0.0.1",DataBaseTypes.DefaultSQLServerPort);
//		Insertion insert = new Insertion(conn, "Test");
//		
//		insert.addFieldValue(new FieldValue("F2", "AA", DataTypes.String))
//			.addFieldValue(new FieldValue("F4", num, DataTypes.Number));
//		insert.execute();
//	
//		conn.close();
//	}
	

}
