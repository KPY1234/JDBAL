package jdbal.structure;

/**
 * 欄位排序物件
 * @author 高浩馭
 * @since  JDBAL-0.02
 */
public class FieldOrder {

	private String col_field;
	private String orderType;
	
	/**
	 * 建構子
	 * @param field 欄位名稱
	 * @param orderType 排序方法
	 */
	public FieldOrder(String field, String orderType){
		col_field = field;
		this.orderType = orderType;
	}
	
	/**
	 * 取得欄位名稱
	 * @return 欄位名稱
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * 取得排序方法
	 * @return 排序方法
	 */
	public String getOrder(){
		return orderType;
	}
}
