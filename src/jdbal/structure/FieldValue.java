package jdbal.structure;

/**
 * 欄位與值物件
 * @author 高浩馭
 * @since  JDBAL-0.02
 *
 */
public class FieldValue {
	
	private String col_field;
	private String col_value;
	private int col_dataType;
	
	/**
	 * 建構子
	 * @param field 欄位
	 * @param value 值
	 * @param dataType 資料型態
	 */
	public FieldValue(String field, int value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * 建構子
	 * @param field 欄位
	 * @param value 值
	 * @param dataType 資料型態
	 */
	public FieldValue(String field, long value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * 建構子
	 * @param field 欄位
	 * @param value 值
	 * @param dataType 資料型態
	 */
	public FieldValue(String field, double value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * 建構子
	 * @param field 欄位
	 * @param value 值
	 * @param dataType 資料型態
	 */
	public FieldValue(String field, float value, int dataType){
		this(field,""+value,dataType);
	}
	
	/**
	 * 建構子
	 * @param field 欄位
	 * @param value 值
	 * @param dataType 資料型態
	 */
	public FieldValue(String field, String value, int dataType){
		col_field = field;
		col_value = value;
		col_dataType = dataType;
	}
	
	/**
	 * 取得欄位名稱
	 * @return 欄位名稱
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * 取得值
	 * @return 值
	 */
	public String getValue(){
		return col_value;
	}
	
	/**
	 * 取得資料型態
	 * @return 資料型態
	 */
	public int getDataType(){
		return col_dataType;
	}

}
