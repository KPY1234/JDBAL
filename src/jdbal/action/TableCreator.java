package jdbal.action;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import jdbal.conn.Connection;
import jdbal.structure.FieldAttribute;
import jdbal.types.DataBaseTypes;
import jdbal.types.DataTypes;

public class TableCreator {
	
	private Connection db_conn;
	private String db_table;
	private ArrayList<FieldAttribute> fieldAttributes;
	
	public TableCreator(Connection conn, String tbName){
		
		db_conn = conn;
		db_table = tbName;
		fieldAttributes = new ArrayList<FieldAttribute>();
	}
	
	public TableCreator addFieldAttribute(FieldAttribute fa){
		
		fieldAttributes.add(fa);
		return this;
		
	}
	
	
	public String getTableName(){
		return db_table;
	}
	
	
	public ArrayList<FieldAttribute> getFieldAttributes(){
		return fieldAttributes;
	}
	
	public int execute(){
		int returnValue = 0;
		
		QueryBuilder qb = QueryBuilderCreator.getInstance(db_conn.getDBType());
		String sql = qb.createTableQueryBuild(this);
		
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
		fieldAttributes = new ArrayList<FieldAttribute>();
	}
	
	public static void main(String[] argv) throws Exception {
	  
		Connection conn = new Connection(DataBaseTypes.MySQL, DataBaseTypes.MySQLDriver, 
				"stocker", "icrd00","stock","127.0.0.1",DataBaseTypes.DefaultMySQLPort);
	  
		TableCreator tc = new TableCreator(conn, "test2")
				.addFieldAttribute(new FieldAttribute("col1", DataTypes.Int, false, true))
				.addFieldAttribute(new FieldAttribute("col2", DataTypes.Text, false, false));
	  
		tc.execute();
  
	}
}