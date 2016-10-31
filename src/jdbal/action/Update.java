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
 * ��Ʈw��s�����O
 * <p>
 * Update ����Ψӧ�s��Ʈw����ơA�|�һ����G
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Update update = new Update(conn, tbname);
 * </pre></blockquote><p>
 * �[�J�ݭn��s�����P�ȡA�ϥ�
 * <blockquote><pre>
 *     update.setFieldValue(new FieldValue(field2, "CC001", DataTypes.String));
 *     update.setFieldValue(new FieldValue(field3, 12, DataTypes.Number));
 * </pre></blockquote>
 * �[�J����
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Equal, 1, DataTypes.Number));
 * 	   update.addConditions(cb);}	
 * </pre></blockquote>
 * ����s�W��ƾާ@
 * <blockquote><pre>
 *     update.execute();
 * </pre></blockquote>
 * �W�z�ʧ@�i�Τ@��ԭz���
 * <blockquote><pre> {@code
 * 		int result = new Update(conn, "test")
 * 			  	.setFieldValue(new FieldValue(field2, "AAQQQ", DataTypes.String))
 * 			  	.setFieldValue(new FieldValue(field3, "AB009", DataTypes.String))
 * 				.addConditions(cb)
 * 				.execute();}
 * </pre></blockquote>
 * 
 * @author ���E��
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
	 * �غc�l
	 * @param conn �s����Ʈw������
	 * @param tbName ��ƪ�W��
	 */
	public Update(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fieldValues = new ArrayList<FieldValue>();
	}
	
	/**
	 * �s�W�ݧ�s�����P��s�Ȫ�����
	 * @param fv �����P��s�Ȫ�����
	 * @return �ۤv���O
	 */
	public Update setFieldValue(FieldValue fv){
		fieldValues.add(fv);
		return this;
	}
	
	/**
	 * �s�W����
	 * @param cb ����غc��
	 * @return �ۤv���O
	 */
	public Update addConditions(ConditionBuilder cb){
		condBuilder = cb;
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
	 * ���o�Ҧ����P��s�Ȫ�����
	 * @return �Ҧ����P��s�Ȫ�����
	 */
	public ArrayList<FieldValue> getFieldValues(){
		return fieldValues;
	}
	
	/**
	 * ���o����غc��
	 * @return ����غc��
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * �����s�ʧ@
	 * @return ��s���ƶq
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
	 * �ϥ�sql�y�k�����s�ʧ@
	 * @param sql sql�y�k�r��
	 * @return ��s���ƶq
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
	 * ���槹��M���L�h����s�ԭz
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
