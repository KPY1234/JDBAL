package jdbal.structure;

/**
 * 合併操作之物件
 * @author 高浩馭
 * @since  JDBAL-0.02
 *
 */
public class Join {

	private String joinType;
	private String selfTable;
	private String selfField;
	private String referTable;
	private String referField;
	
	/**
	 * 建構子
	 * @param joinType 合併操作的種類
	 * @param selfTable 自己資料表名稱
	 * @param selfField 自己欄位名稱
	 * @param referTable 外部資料表名稱
	 * @param referField 外部欄位名稱
	 */
	public Join(String joinType, String selfTable, String selfField, String referTable, String referField){
		this.joinType = joinType;
		this.selfTable = selfTable;
		this.selfField = selfField;
		this.referTable = referTable;
		this.referField = referField;
	}
	
	
	/**
	 * 取得合併操作種類
	 * @return 合併操作種類
	 */
	public String getJoinType(){
		return joinType;
	}
	
	/**
	 * 取得自己資料表名稱
	 * @return 資料表名稱
	 */
	public String getSelfTable(){
		return selfTable;
	}

	/**
	 * 取得自己欄位名稱
	 * @return 欄位名稱
	 */
	public String getSelfField(){
		return selfField;
	}
	
	/**
	 * 取得外部資料表名稱
	 * @return 外部資料表名稱
	 */
	public String getReferTable(){
		return referTable;
	}
	
	/**
	 * 取得外部欄位名稱
	 * @return 欄位名稱
	 */
	public String getReferField(){
		return referField;
	}
}
