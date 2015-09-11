package jdbal.action;

import java.sql.SQLException;
import java.sql.Statement;

import jdbal.conn.Connection;
import jdbal.structure.Condition;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;

/**
 * �R����Ʈw���
 * <p>
 * Deletion ����ΨӧR����Ʈw��ơA�|�һ����G
 * <blockquote><pre>
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Deletion delete = new Deletion(conn, tbname);
 * </pre></blockquote><p>
 * �[�J����
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));
 * 	   delete.addConditions(cb);}	
 * </pre></blockquote>
 * ����R����ƾާ@
 * <blockquote><pre>
 *     delete.execute();
 * </pre></blockquote>
 * 
 * @author ���E��
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @since   JDBAL-0.0.2
 */
public class Deletion {
	
	private Connection db_conn;
	private String db_table;
	private ConditionBuilder condBuilder;
	
	/**
	 * �غc�l
	 * @param conn �s����Ʈw������
	 * @param tbName ��ƪ�W��
	 */
	public Deletion(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
	}
	
	/**
	 * �s�W����
	 * @param cb ����غc��
	 * @return �ۤv���O
	 */
	public Deletion addConditions(ConditionBuilder cb){
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
	 * ���o����غc��
	 * @return ����غc��
	 */
	public ConditionBuilder getConditionBuilder(){
		return condBuilder;
	}
	
	/**
	 * ����R����ưʧ@
	 * @return �R������Ƶ���
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
	 * �ϥ�sql�y�k����s�W��ưʧ@
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
	 * ���槹��M���L�h���R���ԭz
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
