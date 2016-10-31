package jdbal.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbal.conn.Connection;
import jdbal.structure.Condition;
import jdbal.structure.FieldOrder;
import jdbal.structure.Tuple;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;
import jdbal.types.OrderTypes;
/**
 * 資料庫搜尋的類別
 * <p>
 * Selection 物件用來撈出資料庫資料，可額外加條件限制、合併(Join)操作，可設定回傳結果的數量，舉例說明：
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Selection select = new Selection(conn, tbname);
 * </pre></blockquote><p>
 * 加入要回傳的欄位，使用
 * <blockquote><pre>
 *     select.addField(field);
 * </pre></blockquote>
 * 加入條件式
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));
 * 	   select.addConditions(cb);}	
 * </pre></blockquote>
 * 加入排序功能
 * <blockquote><pre>
 *     select.addOrder(new FieldOrder(field, OrderTypes.ASC));
 * </pre></blockquote>
 * 設定最大回傳筆數
 * <blockquote><pre>
 *     select.setQuantity(50);
 * </pre></blockquote>
 * 執行搜尋操作
 * <blockquote><pre>
 *     select.execute();
 * </pre></blockquote>
 * 上述動作可用一行敘述表示
 * <blockquote><pre> {@code
 * 	   ArrayList<Tuple> results = new Selection(conn, tbname)
 * 			.addField(field1)
 * 			.addField(field2)
 * 			.addConditions(cb)
 * 			.addOrder(new FieldOrder(field1, OrderTypes.ASC))
 * 			.setQuantity(50)
 * 			.execute();}	
 * </pre></blockquote>
 * 若使用合併(Join)操作，必須指定資料表名稱，舉例說明：
 * <blockquote><pre> {@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(tableName.field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(tableName.field1, OperationTypes.Less, 5, DataTypes.Number));
 * 	   JoinBuilder jb = new JoinBuilder().
				addJoin(new Join(JoinTypes.LEFT, tableName1, field, tableName2, field));
 * 	   ArrayList<Tuple> results = new Selection(conn, tbname)
 * 			.addField(tableName.field1)
 * 			.addField(tableName.field2)
 * 			.addConditions(cb)
 * 			.addJoins(jb)
 * 			.addOrder(new FieldOrder(field1, OrderTypes.ASC))
 * 			.setQuantity(50)
 * 			.execute();}	
 * </pre></blockquote>
 * 
 * 
 * 
 * @author 高浩馭
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @see     jdbal.action.JoinBuilder
 * @see     jdbal.structure.FieldOrder
 * @since   JDBAL-0.0.3
 */
public class Selection {
	
	private Connection db_conn;
	private String db_table;
	private ArrayList<String> fields;
	private ConditionBuilder condBuilder;
	private JoinBuilder joinBuilder;
	private ArrayList<FieldOrder> fieldOrders;
	private int quantity;
	
	/**
	 * 建構子
	 * @param conn 連結資料庫的物件
	 * @param tbName 資料表名稱
	 */
	public Selection(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fields= new ArrayList<String>();
		fieldOrders = new ArrayList<FieldOrder>();
		quantity = 0;
	}
	
	
	/**
	 * 加入所有回傳的欄位
	 * @return 自己類別
	 */
	public Selection addAllFields(){
		fields.add("*");
		return this;
	}
	
	/**
	 * 新增一個回傳的欄位
	 * @param field 欄位名稱
	 * @return 自己類別
	 */
	public Selection addField(String field){
		fields.add(field);
		return this;
	}
	
	/**
	 * 新增排序的規則
	 * @param fieldOrder 欄位與排序之物件
	 * @return 自己類別
	 */
	public Selection addOrder(FieldOrder fieldOrder){
		fieldOrders.add(fieldOrder);
		return this;
	}
	
	/**
	 * 新增合併運算
	 * @param jb 合併運算建構者
	 * @return 自己類別
	 */
	public Selection addJoins(JoinBuilder jb){
		joinBuilder = jb;
		return this;
	}
	
	/**
	 * 新增條件
	 * @param cb 條件建構者
	 * @return 自己類別
	 */
	public Selection addConditions(ConditionBuilder cb){
		condBuilder = cb;
		return this;
	}
	
	/**
	 * 設定回傳資料數量
	 * @param quantity 資料數量
	 * @return 自己類別
	 */
	public Selection setQuantity(int quantity){
		this.quantity = quantity;
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
	 * 取得所有回傳的欄位名稱
	 * @return 所有回傳的欄位名稱
	 */
	public ArrayList<String> getFields(){
		return fields;
	}
	
	/**
	 * 取得合併建構者
	 * @return 合併建構者
	 */
	public JoinBuilder getJoinBuilder(){
		return joinBuilder;
	}
	
	/**
	 * 取得條件建構者
	 * @return 條件建構者
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * 取得所有欄位排序對
	 * @return 所有欄位排序對
	 */
	public ArrayList<FieldOrder> getFieldOrders(){
		return fieldOrders;
	}
	
	/**
	 * 取得回傳數量
	 * @return 回傳數量
	 */
	public int getQuantity(){
		return quantity;
	}
	
	/**
	 * 執行資料庫查詢功能
	 * @return 查詢結果
	 */
	public ArrayList<Tuple> execute(){
		
		QueryBuilder qb = QueryBuilderCreator.getInstance(db_conn.getDBType());
		String sql = qb.selectQueryBuild(this);
		
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		
		try {
			Statement stmt = db_conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int columns = rs.getMetaData().getColumnCount();
				Tuple tuple = new Tuple();
				for(int i=0;i<columns;i++){
					String value = "null";
					if(rs.getString(i+1)!=null)
						value = rs.getString(i+1).trim();
					tuple.addRecord(value);
				}
				tuples.add(tuple);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clear();
		return tuples;
	}
	
	/**
	 * 使用自訂的SQL字串執行資料庫查詢功能
	 * @param sql 自訂的SQL字串
	 * @return 查詢結果
	 */
	public ArrayList<Tuple> executeBySQL(String sql){
		
		ArrayList<Tuple> tuples = new ArrayList<Tuple>();
		try {
			Statement stmt = db_conn.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				int columns = rs.getMetaData().getColumnCount();
				Tuple tuple = new Tuple();
				for(int i=0;i<columns;i++){
					String value = rs.getString(i+1);
					tuple.addRecord(value);
				}
				tuples.add(tuple);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clear();
		return tuples;
	}
	
	/**
	 * 執行完後清除過去的新增敘述
	 */
	private void clear(){
		condBuilder = null;
		joinBuilder = null;
		fields= new ArrayList<String>();
		fieldOrders = new ArrayList<FieldOrder>();
		quantity = 0;
	}
	
	
//	public static void main(String[] args) {
//
//		Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
//				"sa", "icrdc4isr19+","test","127.0.0.1",DataBaseTypes.DefaultSQLServerPort);
//		
//		ConditionBuilder cb = new ConditionBuilder()
//		.addCondition(new Condition("f2", OperationTypes.Contain, "A", DataTypes.String))
//		.AND()
//		.addLeftQuote()
//		.addCondition(new Condition("f2", OperationTypes.Less, "5", DataTypes.String))
//		.OR()
//		.addCondition(new Condition("f2", OperationTypes.Greater, "6", DataTypes.String))
//		.addRightQuote();
//		
//		Selection select = new Selection(conn, "test");
//		
//		select.addField("F2");
//		select.addField("F3");
//		select.addConditions(cb);
//		select.addOrder(new FieldOrder("F1", OrderTypes.ASC));
//		select.setQuantity(50);
//		ArrayList<Tuple> results = select.execute();
//		
////		ArrayList<String> results = new Selection(conn, "test")
//////			.addField("F1")
////			.addField("F2")
////			.addField("F3")
////			.addConditions(cb)
////			.addOrder(new FieldOrder("F1", OrderTypes.ASC))
////			.setQuantity(50)
////			.execute();
//		
//		for(int i=0;i<results.size();i++)
//			System.out.println(results.get(i));
//		
////		results = select.addField("F2")
////				.addField("F3")
////			.execute();
//		
////		for(int i=0;i<results.size();i++)
////			System.out.println(results.get(i));
//		
//		conn.close();
//		
//	}

}
