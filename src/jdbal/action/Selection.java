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
 * ��Ʈw�j�M�����O
 * <p>
 * Selection ����ΨӼ��X��Ʈw��ơA�i�B�~�[���󭭨�B�X��(Join)�ާ@�A�i�]�w�^�ǵ��G���ƶq�A�|�һ����G
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Selection select = new Selection(conn, tbname);
 * </pre></blockquote><p>
 * �[�J�n�^�Ǫ����A�ϥ�
 * <blockquote><pre>
 *     select.addField(field);
 * </pre></blockquote>
 * �[�J����
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));
 * 	   select.addConditions(cb);}	
 * </pre></blockquote>
 * �[�J�Ƨǥ\��
 * <blockquote><pre>
 *     select.addOrder(new FieldOrder(field, OrderTypes.ASC));
 * </pre></blockquote>
 * �]�w�̤j�^�ǵ���
 * <blockquote><pre>
 *     select.setQuantity(50);
 * </pre></blockquote>
 * ����j�M�ާ@
 * <blockquote><pre>
 *     select.execute();
 * </pre></blockquote>
 * �W�z�ʧ@�i�Τ@��ԭz���
 * <blockquote><pre> {@code
 * 	   ArrayList<Tuple> results = new Selection(conn, tbname)
 * 			.addField(field1)
 * 			.addField(field2)
 * 			.addConditions(cb)
 * 			.addOrder(new FieldOrder(field1, OrderTypes.ASC))
 * 			.setQuantity(50)
 * 			.execute();}	
 * </pre></blockquote>
 * �Y�ϥΦX��(Join)�ާ@�A�������w��ƪ�W�١A�|�һ����G
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
 * @author ���E��
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
	 * �غc�l
	 * @param conn �s����Ʈw������
	 * @param tbName ��ƪ�W��
	 */
	public Selection(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fields= new ArrayList<String>();
		fieldOrders = new ArrayList<FieldOrder>();
		quantity = 0;
	}
	
	
	/**
	 * �[�J�Ҧ��^�Ǫ����
	 * @return �ۤv���O
	 */
	public Selection addAllFields(){
		fields.add("*");
		return this;
	}
	
	/**
	 * �s�W�@�Ӧ^�Ǫ����
	 * @param field ���W��
	 * @return �ۤv���O
	 */
	public Selection addField(String field){
		fields.add(field);
		return this;
	}
	
	/**
	 * �s�W�ƧǪ��W�h
	 * @param fieldOrder ���P�ƧǤ�����
	 * @return �ۤv���O
	 */
	public Selection addOrder(FieldOrder fieldOrder){
		fieldOrders.add(fieldOrder);
		return this;
	}
	
	/**
	 * �s�W�X�ֹB��
	 * @param jb �X�ֹB��غc��
	 * @return �ۤv���O
	 */
	public Selection addJoins(JoinBuilder jb){
		joinBuilder = jb;
		return this;
	}
	
	/**
	 * �s�W����
	 * @param cb ����غc��
	 * @return �ۤv���O
	 */
	public Selection addConditions(ConditionBuilder cb){
		condBuilder = cb;
		return this;
	}
	
	/**
	 * �]�w�^�Ǹ�Ƽƶq
	 * @param quantity ��Ƽƶq
	 * @return �ۤv���O
	 */
	public Selection setQuantity(int quantity){
		this.quantity = quantity;
		return this;
	}
	
	/**
	 * ���o��ƪ�W��
	 * @return ��ƪ�W��
	 */
	public String getTableName(){
		return db_table;
	}
	
	/**
	 * ���o�Ҧ��^�Ǫ����W��
	 * @return �Ҧ��^�Ǫ����W��
	 */
	public ArrayList<String> getFields(){
		return fields;
	}
	
	/**
	 * ���o�X�֫غc��
	 * @return �X�֫غc��
	 */
	public JoinBuilder getJoinBuilder(){
		return joinBuilder;
	}
	
	/**
	 * ���o����غc��
	 * @return ����غc��
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * ���o�Ҧ����Ƨǹ�
	 * @return �Ҧ����Ƨǹ�
	 */
	public ArrayList<FieldOrder> getFieldOrders(){
		return fieldOrders;
	}
	
	/**
	 * ���o�^�Ǽƶq
	 * @return �^�Ǽƶq
	 */
	public int getQuantity(){
		return quantity;
	}
	
	/**
	 * �����Ʈw�d�ߥ\��
	 * @return �d�ߵ��G
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
	 * �ϥΦۭq��SQL�r������Ʈw�d�ߥ\��
	 * @param sql �ۭq��SQL�r��
	 * @return �d�ߵ��G
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
	 * ���槹��M���L�h���s�W�ԭz
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
