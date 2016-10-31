package jdbal.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdbal.structure.Condition;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;

/**
 * ����إߪ̪���
 * �ϥνd�Ҧp�U�A�إ߱���
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));}	
 * </pre></blockquote>
 * 
 * @author ���E��
 * @see    jdbal.structure.Condition
 * @see    jdbal.types.DataTypes
 * @see    jdbal.types.OperationTypes
 * @since  JDBAL-0.0.3
 *
 */
public class ConditionBuilder {
	
	private boolean updated = false;
	private String expression = "";
	String conditions = "";
	
	/**
	 * �[�J���A��(
	 * @return �ۤv
	 */
	public ConditionBuilder addLeftQuote(){
		conditions += " ( ";
		expression += "(";
		updated = true;
		return this;
		
	}
	
	/**
	 * �[�J�k�A��
	 * @return �ۤv
	 */
	public ConditionBuilder addRightQuote(){
		conditions += " ) ";
		expression += ")";
		updated = true;
		return this;
	}
	
	/**
	 * �[�J�޿�AND
	 * @return �ۤv
	 */
	public ConditionBuilder AND(){
		conditions += " AND ";
		expression += "L";
		updated = true;
		return this;
		
	}
	
	/**
	 * �[�J�޿�OR
	 * @return �ۤv
	 */
	public ConditionBuilder OR(){
		conditions += " OR ";
		expression += "L";
		updated = true;
		return this;
		
	}
	
	/**
	 * �[�J����
	 * @param con ���󪫥�
	 * @return �ۤv
	 */
	public ConditionBuilder addCondition(Condition con){
		conditions += con.getField()+" ";
		conditions += con.getOperationType()+" ";
		conditions += con.getComparedValue();
		expression += "C";
		updated = true;
		return this;
	}
	
	/**
	 * �إ߱���
	 * @return ����r��
	 */
	String build(){
	
		try {
			validate();
		} catch (ConditionBuildException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(updated)
			conditions = " WHERE "+conditions;
		updated = false;
		return conditions;
	}

	
	/**
	 * ���ұ����ܦ��O�_�X�z
	 * @throws ConditionBuildException
	 */
	private void validate() throws ConditionBuildException{
		
		int C_count=0;
		
		for(int i=0;i<expression.length();i++)
			if(expression.charAt(i)=='C')
				C_count++;
		
		if(C_count>1){
			
			 Pattern p = Pattern.compile("C[^C]*C");
			 Matcher m = p.matcher(expression);
			 
			 int pos = 0;
			 
			 while(m.find(pos)){
				 int start = m.start();
				 int end = m.end();
				 pos = end - 1;
				
				 String sentence = expression.substring(start, end);
				 int L_count=0;
				 
				 for(int i=0;i<sentence.length();i++)
					 if(sentence.charAt(i)=='L')
						 L_count++;
				 
				 if(L_count==0){
					 String message = "����榡���~:���� + ����\n���Ӭ�: ���� + �޿� + ����\n�^�Ǳ��󦡬��Ŧr��";
					 throw new ConditionBuildException(message);
				 }
				 if(L_count>1){
					 String message = "����榡���~:���� +�޿�...�޿�+ ����\n���Ӭ����� + �޿� + ����\n�^�Ǳ��󦡬��Ŧr��";
					 throw new ConditionBuildException(message);
				 }
			 }
			
		}
	}
	
//	public static void main(String[] args) {
//		
//		String conditions = null;
//		
//		conditions = new ConditionBuilder()
//				.addCondition(new Condition("f2", OperationTypes.Contain, "A", DataTypes.String))
//				.AND()
//				.addLeftQuote()
//				.addCondition(new Condition("f2", OperationTypes.Less, 5, DataTypes.Number))
//				.OR()
//				.addCondition(new Condition("f2", OperationTypes.Greater, 6, DataTypes.Number))
//				.addRightQuote()
//				.build();
//	
//		
//		System.out.println(conditions);
//	}

}
