package jdbal.action;

import java.sql.SQLException;
import java.sql.Statement;

import jdbal.conn.Connection;
import jdbal.structure.Condition;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;

/**
 * 刪除資料庫資料
 * <p>
 * Deletion 物件用來刪除資料庫資料，舉例說明：
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Deletion delete = new Deletion(conn, tbname);
 * </pre></blockquote><p>
 * 加入條件式
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));
 * 	   delete.addConditions(cb);}	
 * </pre></blockquote>
 * 執行刪除資料操作
 * <blockquote><pre>
 *     delete.execute();
 * </pre></blockquote>
 * 
 * @author 高浩馭
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @since   JDBAL-0.0.2
 */
public class Deletion {
	
	private Connection db_conn;
	private String db_table;
	private ConditionBuilder condBuilder;
	
	/**
	 * 建構子
	 * @param conn 連結資料庫的物件
	 * @param tbName 資料表名稱
	 */
	public Deletion(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
	}
	
	/**
	 * 新增條件
	 * @param cb 條件建構者
	 * @return 自己類別
	 */
	public Deletion addConditions(ConditionBuilder cb){
		condBuilder = cb;
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
	 * 取得條件建構者
	 * @return 條件建構者
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * 執行刪除資料動作
	 * @return 刪除的資料筆數
	 */
	public int execute(){
		int returnValue = 0;
		
		QueryBuilder qb = QueryBuilderCreator.getInstance(db_conn.getDBType());
		String sql = qb.deleteQueryBuild(this);
		
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
	 * 執行完後清除過去的刪除敘述
	 */
	private void clear(){
		condBuilder = null;
	}
	
//	public static void main(String[] args) {
//		
//		ConditionBuilder cb = new ConditionBuilder()
//			.addCondition(new Condition("F1", OperationTypes.Less, 3, DataTypes.Number));
//		
//		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
//				"sa", "icrdc4isr19+","test","127.0.0.1",DataBaseTypes.DefaultSQLServerPort);
//		
//		Deletion delete = new Deletion(conn, "Test");
//		int result = delete
////			.addConditions(cb)
//			.execute();
//		
////		cb = new ConditionBuilder()
////		.addCondition(new Condition("F1", OperationTypes.Less, 6, DataTypes.Number));
////		
////		delete.addConditions(cb)
////			.execute();
//		
//		conn.close();
//		
//	}

}
