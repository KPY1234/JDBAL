package jdbal.action;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import jdbal.conn.Connection;
import jdbal.types.DataBaseTypes;

/**
 * 中繼資料取得
 * 
 * @author 高浩馭
 * @see     jdbal.conn.Connection
 * @see     jdbal.action.ConditionBuilder
 * @since   JDBAL-0.1.0
 */
public class MetaDataExtractor {
	
	/**
	 * 建構子
	 */
	public MetaDataExtractor(){
	}
	
	
	/**
	 * 取得資料表欄位名稱
	 * @param conn 連結資料庫的物件
	 * @param tbname 資料表名稱
	 * @return 資料表欄位名稱陣列
	 * @throws SQLException 資料庫例外
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
	 * 取得資料表欄位種類
	 * @param conn 連結資料庫的物件
	 * @param tbname 資料表名稱
	 * @return 資料表欄位種類陣列
	 * @throws SQLException 資料庫例外
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
