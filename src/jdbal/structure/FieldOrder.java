package jdbal.structure;

/**
 * ���ƧǪ���
 * @author ���E��
 * @since  JDBAL-0.02
 */
public class FieldOrder {

	private String col_field;
	private String orderType;
	
	/**
	 * �غc�l
	 * @param field ���W��
	 * @param orderType �ƧǤ�k
	 */
	public FieldOrder(String field, String orderType){
		col_field = field;
		this.orderType = orderType;
	}
	
	/**
	 * ���o���W��
	 * @return ���W��
	 */
	public String getField(){
		return col_field;
	}
	
	/**
	 * ���o�ƧǤ�k
	 * @return �ƧǤ�k
	 */
	public String getOrder(){
		return orderType;
	}
}
