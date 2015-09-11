package jdbal.action;

import java.util.ArrayList;

import jdbal.structure.FieldOrder;
import jdbal.structure.FieldValue;
import jdbal.types.DataTypes;

class MySQLQueryBuilder implements QueryBuilder{

	@Override
	public String selectQueryBuild(Selection se) {
		// TODO Auto-generated method stub
		
		String db_table = se.getTableName();
		ArrayList<String> fields = se.getFields();
		JoinBuilder joinBuilder = se.getJoinBuilder();
		ConditionBuilder condBuilder = se.getConditionBuilder();
		ArrayList<FieldOrder> fieldOrders = se.getFieldOrders();
		int quantity = se.getQuantity();
		
		String sql = "SELECT ";

		for(int i=0;i<fields.size();i++){
			sql+=fields.get(i);
			if(i!=fields.size()-1)
				sql+=", ";
		}
		sql+=" FROM "+db_table;
		
		if(joinBuilder!=null)
			sql+=joinBuilder.build();
		
		if(condBuilder!=null){
			sql+=condBuilder.build();
		
		}
		
		if(fieldOrders.size()!=0){
			for(int i=0;i<fieldOrders.size();i++){
				sql+=" ORDER BY "+fieldOrders.get(i).getField()+" "+fieldOrders.get(i).getOrder();
				if(i!=fieldOrders.size()-1)
					sql+=", ";
			}
		}
		
		if(quantity!=0)
			sql+=" LIMIT "+quantity;
		
		return sql;
	}

	@Override
	public String insertQueryBuild(Insertion insert) {
		// TODO Auto-generated method stub
		
		String db_table = insert.getTableName();
		ArrayList<FieldValue> fieldValues = insert.getFieldValues();
		
		String sql = "INSERT INTO "+db_table+"(";
		
		for(int i=0;i<fieldValues.size();i++){
			sql+=fieldValues.get(i).getField();
			if(i!=fieldValues.size()-1)
				sql+=", ";
		}
		sql+=") VALUES (";
		
		for(int i=0;i<fieldValues.size();i++){
			if(fieldValues.get(i).getDataType()==DataTypes.Number)
				sql+=fieldValues.get(i).getValue();
			else 
				sql+="'"+fieldValues.get(i).getValue()+"'";
			if(i!=fieldValues.size()-1)
				sql+=", ";
		}
		
		sql+=")";
		
		return sql;
	}

	@Override
	public String updateQueryBuild(Update update) {
		// TODO Auto-generated method stub
		
		String db_table = update.getTableName();
		ArrayList<FieldValue> fieldValues = update.getFieldValues();
		ConditionBuilder condBuilder = update.getConditionBuilder();
		
		String sql = "UPDATE "+db_table+" SET ";
		
		for(int i=0;i<fieldValues.size();i++){
			sql+=fieldValues.get(i).getField();
			sql+=" = ";
			if(fieldValues.get(i).getDataType()==DataTypes.Number)
				sql+=fieldValues.get(i).getValue();
			else 
				sql+="'"+fieldValues.get(i).getValue()+"'";
			if(i!=fieldValues.size()-1)
				sql+=", ";
		}
		
		if(condBuilder!=null){
			sql+=condBuilder.build();
		
		}
			
		
		return sql;
	}

	@Override
	public String deleteQueryBuild(Deletion delete) {
		// TODO Auto-generated method stub
		
		String db_table = delete.getTableName();
		ConditionBuilder condBuilder = delete.getConditionBuilder();
		
		String sql = "DELETE FROM "+db_table+" ";
		if(condBuilder!=null){
			sql+=condBuilder.build();
		
		}
			
		
		return sql;
	}
	
}
