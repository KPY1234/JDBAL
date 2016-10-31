package jdbal.action;

import jdbal.types.DataBaseTypes;

class QueryBuilderCreator {
	
	public static QueryBuilder getInstance(int databaseType){
		
		if(databaseType == DataBaseTypes.MySQL){
			return new MySQLQueryBuilder();
		} else if (databaseType == DataBaseTypes.SQLServer){
			return new SQLServerQueryBuilder();
		} else if (databaseType == DataBaseTypes.Oracle){
			return null;
		} else if (databaseType == DataBaseTypes.PostgreSQL){
			return new PostgreSQLQueryBuilder();
		}
		return null;
		
	}
	
}
