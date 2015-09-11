package jdbal.structure;

/**
 * ���P�Ȫ���
 * @author ���E��
 * @since  JDBAL-0.02
 *
 */
public class FieldValue {
	
	private String col_field;
	private String col_value;
	private int col_dataType;
	
	/**
	 * �غc�l
	 * @param field ���
	 * @param value ��
	 * @param dataType ��ƫ��A
	 */
	public FieldValue(String field, int value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * �غc�l
	 * @param field ���
	 * @param value ��
	 * @param dataType ��ƫ��A
	 */
	public FieldValue(String field, long value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * �غc�l
	 * @param field ���
	 * @param value ��
	 * @param dataType ��ƫ��A
	 */
	public FieldValue(String field, double value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * �غc�l
	 * @param field ���
	 * @param value ��
	 * @param dataType ��ƫ��A
	 */
	public FieldValue(String field, float value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * �غc�l
	 * @param field ���
	 * @param value ��
	 * @param dataType ��ƫ��A
	 */
	public FieldValue(String field, String value, int dataType){
		col_field = field;
		col_value = value;
		col_dataType = dataType;
	}
	
	/**
	 * ���o���W��
	 * @return ���W��
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * ���o��
	 * @return ��
	 */
	public String getValue(){
		return col_value;
	}
	
	/**
	 * ���o��ƫ��A
	 * @return ��ƫ��A
	 */
	public int getDataType(){
		return col_dataType;
	}

}
