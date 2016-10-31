package jdbal.structure;

import jdbal.types.DataTypes;

/**
 * 條件物件
 * @author 高浩馭
 * @since  JDBAL-0.02
 */
public class Condition {
	
	private String col_field;
	private String operationType;
	private Object comparedValue;
	private int dataType;
	
	/**
	 * 建構子
	 * @param field 欄位名稱
	 * @param operationType 運算式種類
	 * @param comparedValue 被運算的值
	 * @param dataType 資料類型
	 */
	public Condition(String field, String operationType, Object comparedValue, int dataType){
		col_field = field;
		this.operationType = operationType;
		this.comparedValue = comparedValue;
		this.dataType = dataType;
	}
	
	/**
	 * 取得欄位名稱
	 * @return 欄位名稱
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * 取得運算種類
	 * @return 運算種類
	 */
	public String getOperationType(){
		return operationType;
	}
	
	/**
	 * 取得被運算的值
	 * @return 被運算的值
	 */
	public String getComparedValue(){
		
		if(dataType == DataTypes.Number)
			return comparedValue.toString();
		else{
			if(operationType.equals("LIKE"))
				return "'%"+comparedValue.toString()+"%'";
			else
				return "'"+comparedValue.toString()+"'";
		}	
	}
	
	/**
	 * 取得資料型態
	 * @return 資料類型
	 */
	public int getDataType(){
		return dataType;
	}

}
