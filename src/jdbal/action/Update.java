package jdbal.action;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbal.conn.Connection;
import jdbal.structure.Condition;
import jdbal.structure.FieldValue;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;

/**
 * 資料庫更新的類別
 * <p>
 * Update 物件用來更新資料庫的資料，舉例說明：
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Update update = new Update(conn, tbname);
 * </pre></blockquote><p>
 * 加入需要更新的欄位與值，使用
 * <blockquote><pre>
 *     update.setFieldValue(new FieldValue(field2, "CC001", DataTypes.String));
 *     update.setFieldValue(new FieldValue(field3, 12, DataTypes.Number));
 * </pre></blockquote>
 * 加入條件式
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Equal, 1, DataTypes.Number));
 * 	   update.addConditions(cb);}	
 * </pre></blockquote>
 * 執行新增資料操作
 * <blockquote><pre>
 *     update.execute();
 * </pre></blockquote>
 * 上述動作可用一行敘述表示
 * <blockquote><pre> {@code
 * 		int result = new Update(conn, "test")
 * 			  	.setFieldValue(new FieldValue(field2, "AAQQQ", DataTypes.String))
 * 			  	.setFieldValue(new FieldValue(field3, "AB009", DataTypes.String))
 * 				.addConditions(cb)
 * 				.execute();}
 * </pre></blockquote>
 * 
 * @author 高浩馭
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @see     jdbal.structure.FieldValue
 * @since   JDBAL-0.0.2
 *
 */
public class Update {
	
	private Connection db_conn;
	private String db_table;
	private ArrayList<FieldValue> fieldValues;
	private ConditionBuilder condBuilder;
	
	/**
	 * 建構子
	 * @param conn 連結資料庫的物件
	 * @param tbName 資料表名稱
	 */
	public Update(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fieldValues = new ArrayList<FieldValue>();
	}
	
	/**
	 * 新增需更新的欄位與更新值的物件
	 * @param fv 的欄位與更新值的物件
	 * @return 自己類別
	 */
	public Update setFieldValue(FieldValue fv){
		fieldValues.add(fv);
		return this;
	}
	
	/**
	 * 新增條件
	 * @param cb 條件建構者
	 * @return 自己類別
	 */
	public Update addConditions(ConditionBuilder cb){
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
	 * 取得所有欄位與更新值的物件
	 * @return 所有欄位與更新值的物件
	 */
	public ArrayList<FieldValue> getFieldValues(){
		return fieldValues;
	}
	
	/**
	 * 取得條件建構者
	 * @return 條件建構者
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * 執行更新動作
	 * @return 更新的數量
	 */
	public int execute(){
		int returnValue = 0;
		QueryBuilder qb = QueryBuilderCreator.getInstance(db_conn.getDBType());
		String sql = qb.updateQueryBuild(this);
		
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
	 * 使用sql語法執行更新動作
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
	 * 執行完後清除過去的更新敘述
	 */
	private void clear(){
		condBuilder = null;
		fieldValues = new ArrayList<FieldValue>();
	}
	
//	public static void main(String[] args) {
//		
//		ConditionBuilder cb = new ConditionBuilder()
//			.addCondition(new Condition("F1", OperationTypes.Equal, 6, DataTypes.Number));
//		
//		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
//				"sa", "icrdc4isr19+","test","127.0.0.1",DataBaseTypes.DefaultSQLServerPort);
//		Update update = new Update(conn, "test");
//		update.setFieldValue(new FieldValue("F2", "AAQQQ", DataTypes.String));
//		update.setFieldValue(new FieldValue("F3", "AB009", DataTypes.String));
//		update.addConditions(cb);
//		int result = update.execute();
//		System.out.println(result);
//		update.setFieldValue(new FieldValue("F2", "AAQQQ", DataTypes.String));
//		update.setFieldValue(new FieldValue("F3", "AB010", DataTypes.String));
//		update.addConditions(cb);
//		result = update.execute();
//		
//		conn.close();
//		
//	}
	
}
