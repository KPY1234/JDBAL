package jdbal.action;

import jdbal.structure.Join;

/**
 * �X�֪�ܦ��إߪ̪���
 * 
 * @author ���E��
 * @see    jdbal.structure.Join
 * @since  JDBAL-0.0.3
 *
 */
public class JoinBuilder {

	String joins = "";
	
	/**
	 * �[�J�X�֤�k
	 * @param join �X�֤�k
	 * @return �ۤv
	 */
	public JoinBuilder addJoin(Join join){
		
		joins += " "+join.getJoinType()+" "+join.getReferTable()+" ON "+join.getSelfTable()+"."
				+join.getSelfField()+" = "+join.getReferTable()+"."+join.getReferField();
		
		return this;
	}
	
	/**
	 * �إߦX�֪�ܦ�
	 * @return �X�֪�ܦ�
	 */
	String build(){
		return joins;
	}
	
}
