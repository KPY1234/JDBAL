package jdbal.structure;

import jdbal.types.DataTypes;

/**
 * ���󪫥�
 * @author ���E��
 * @since  JDBAL-0.02
 */
public class Condition {
	
	private String col_field;
	private String operationType;
	private Object comparedValue;
	private int dataType;
	
	/**
	 * �غc�l
	 * @param field ���W��
	 * @param operationType �B�⦡����
	 * @param comparedValue �Q�B�⪺��
	 * @param dataType �������
	 */
	public Condition(String field, String operationType, Object comparedValue, int dataType){
		col_field = field;
		this.operationType = operationType;
		this.comparedValue = comparedValue;
		this.dataType = dataType;
	}
	
	/**
	 * ���o���W��
	 * @return ���W��
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * ���o�B�����
	 * @return �B�����
	 */
	public String getOperationType(){
		return operationType;
	}
	
	/**
	 * ���o�Q�B�⪺��
	 * @return �Q�B�⪺��
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
	 * ���o��ƫ��A
	 * @return �������
	 */
	public int getDataType(){
		return dataType;
	}

}
