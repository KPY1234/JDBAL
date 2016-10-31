package jdbal.structure;

public class FieldAttribute {

	
	private String col_field;
	private int col_dataType;
	private boolean hasNull;
	private boolean isPrimary;
	
	public FieldAttribute(String field, int dataType, boolean hasNull, boolean isPrimary){
		this.col_field = field;
		this.col_dataType = dataType;
		this.hasNull = hasNull;
		this.isPrimary = isPrimary;
	}
	
	
	public String getField(){
		return col_field;
	}
	
	/**
	 * ���o��ƫ��A
	 * @return ��ƫ��A
	 */
	public int getDataType(){
		return col_dataType;
	}
	
	public boolean getHasNull(){
		return hasNull;
	}
	
	
	public boolean getIsPrimary(){
		return isPrimary;
	}
	
}
