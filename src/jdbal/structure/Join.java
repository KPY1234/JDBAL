package jdbal.structure;

/**
 * �X�־ާ@������
 * @author ���E��
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
	 * �غc�l
	 * @param joinType �X�־ާ@������
	 * @param selfTable �ۤv��ƪ�W��
	 * @param selfField �ۤv���W��
	 * @param referTable �~����ƪ�W��
	 * @param referField �~�����W��
	 */
	public Join(String joinType, String selfTable, String selfField, String referTable, String referField){
		this.joinType = joinType;
		this.selfTable = selfTable;
		this.selfField = selfField;
		this.referTable = referTable;
		this.referField = referField;
	}
	
	
	/**
	 * ���o�X�־ާ@����
	 * @return �X�־ާ@����
	 */
	public String getJoinType(){
		return joinType;
	}
	
	/**
	 * ���o�ۤv��ƪ�W��
	 * @return ��ƪ�W��
	 */
	public String getSelfTable(){
		return selfTable;
	}

	/**
	 * ���o�ۤv���W��
	 * @return ���W��
	 */
	public String getSelfField(){
		return selfField;
	}
	
	/**
	 * ���o�~����ƪ�W��
	 * @return �~����ƪ�W��
	 */
	public String getReferTable(){
		return referTable;
	}
	
	/**
	 * ���o�~�����W��
	 * @return ���W��
	 */
	public String getReferField(){
		return referField;
	}
}
