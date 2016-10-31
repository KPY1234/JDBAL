package jdbal.action;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbal.conn.Connection;
import jdbal.structure.FieldValue;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;

/**
 * �s�W��Ʀܸ�Ʈw
 * <p>
 * Insertion ����Ψӷs�W��Ʀܸ�Ʈw�A�|�һ����G
 * <blockquote><pre>{@code
 *     Connection conn = new Connection(DataBaseTypes.SQLServer, DataBaseTypes.SQLServerDriver, 
 *     					username, password, dbname, "127.0.0.1", DataBaseTypes.DefaultSQLServerPort);
 *     Insertion insert = new Insertion(conn, tbname);}
 * </pre></blockquote><p>
 * �[�J�s�W�����P�ȡA�ϥ�
 * <blockquote><pre>{@code
 *     insert.addFieldValue(new FieldValue(field2, "CC001", DataTypes.String));
 *     insert.addFieldValue(new FieldValue(field3, 12, DataTypes.Number));}
 * </pre></blockquote>
 * ����s�W��ƾާ@
 * <blockquote><pre>
 *     int result = insert.execute();
 * </pre></blockquote>
 * �W�z�ʧ@�i�Τ@��ԭz���
 * <blockquote><pre>{@code
 *     int result = new Insertion(conn, tbname)
 *     			.addFieldValue(new FieldValue(field2, "CC001", DataTypes.String))
 *     			.addFieldValue(new FieldValue(field3, 12, DataTypes.Number))
 *     			.execute();}
 * </pre></blockquote>
 * 
 * 
 * @author ���E��
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
	 * �غc�l
	 * @param conn �s����Ʈw������
	 * @param tbName ��ƪ�W��
	 */
	public Insertion(Connection conn, String tbName){
		db_conn = conn;
		db_table = tbName;
		fieldValues = new ArrayList<FieldValue>();
	}
	
	/**
	 * �s�W�����P�Ȫ�����
	 * @param fv ���P�Ȫ�����
	 * @return �ۤv���O
	 */
	public Insertion addFieldValue(FieldValue fv){
		fieldValues.add(fv);
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
	 * ���o�Ҧ����P�Ȫ�����
	 * @return �Ҧ����P�Ȫ�����
	 */
	public ArrayList<FieldValue> getFieldValues(){
		return fieldValues;
	}
	
	/**
	 * ����s�W��ưʧ@
	 * @return �[�J����Ƶ���
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
	 * ���槹��M���L�h���s�W�ԭz
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
