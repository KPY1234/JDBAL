package jdbal.action;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jdbal.conn.Connection;
import jdbal.types.DataBaseTypes;

/**
 * ���~��ƨ��o
 * 
 * @author ���E��
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @since   JDBAL-0.1.0
 */
public class MetaDataExtractor {
	
	/**
	 * �غc�l
	 */
	public MetaDataExtractor(){
	}
	
	
	/**
	 * ���o��ƪ����W��
	 * @param conn �s����Ʈw������
	 * @param tbname ��ƪ�W��
	 * @return ��ƪ����W�ٰ}�C
	 * @throws SQLException ��Ʈw�ҥ~
	 */
	public static String[] getColumnName(Connection conn, String tbname) throws SQLException{
		
		String[] columnName;
		
		String sql = "SELECT * FROM "+tbname+" LIMIT 1";
		Statement stat = conn.getConnection().createStatement();
		ResultSet rs = stat.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnSize = rsmd.getColumnCount();
		
		columnName = new String[columnSize];
		
		for(int i=0;i<columnSize;i++){
			columnName[i] = rsmd.getColumnName(i+1);
//			System.out.println(columnName[i]);
		}
		
		rs.close();
	
		
		return columnName;
	}
	
	/**
	 * ���o��ƪ�������
	 * @param conn �s����Ʈw������
	 * @param tbname ��ƪ�W��
	 * @return ��ƪ��������}�C
	 * @throws SQLException ��Ʈw�ҥ~
	 */
	public static String[] getColumnType(Connection conn, String tbname) throws SQLException{
		
		String[] columnNameType;
		
		String sql = "SELECT * FROM "+tbname+" LIMIT 1";
		Statement stat = conn.getConnection().createStatement();
		ResultSet rs = stat.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnSize = rsmd.getColumnCount();
		
		columnNameType = new String[columnSize];
		
		for(int i=0;i<columnSize;i++){
			columnNameType[i] = rsmd.getColumnTypeName(i+1);
//			System.out.println(rsmd.getColumnDisplaySize(i+1));
//			System.out.println(columnNameType[i]);
		}
		
		rs.close();
	
		
		return columnNameType;
	}
	
	
	public static void main(String[] args) {
		
		Connection conn = new Connection(DataBaseTypes.MySQL, DataBaseTypes.MySQLDriver, 
				"TrackMining", "icrdc4isr19+","track_prediction","192.168.4.230",DataBaseTypes.DefaultMySQLPort);
		try {
			System.out.println(MetaDataExtractor.getColumnType(conn, "plot")[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
