package jdbal.action;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdbal.structure.Condition;
import jdbal.types.DataTypes;
import jdbal.types.OperationTypes;

/**
 * 條件建立者物件
 * 使用範例如下，建立條件式
 * <blockquote><pre>{@code
 * 	   ConditionBuilder cb = new ConditionBuilder()
 * 			.addCondition(new Condition(field1, OperationTypes.Greater, 0, DataTypes.Number))
 * 			.AND()
 * 			.addCondition(new Condition(field1, OperationTypes.Less, 5, DataTypes.Number));}	
 * </pre></blockquote>
 * 
 * @author 高浩馭
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
	 * 加入左括號(
	 * @return 自己
	 */
	public ConditionBuilder addLeftQuote(){
		conditions += " ( ";
		expression += "(";
		updated = true;
		return this;
		
	}
	
	/**
	 * 加入右括號
	 * @return 自己
	 */
	public ConditionBuilder addRightQuote(){
		conditions += " ) ";
		expression += ")";
		updated = true;
		return this;
	}
	
	/**
	 * 加入邏輯AND
	 * @return 自己
	 */
	public ConditionBuilder AND(){
		conditions += " AND ";
		expression += "L";
		updated = true;
		return this;
		
	}
	
	/**
	 * 加入邏輯OR
	 * @return 自己
	 */
	public ConditionBuilder OR(){
		conditions += " OR ";
		expression += "L";
		updated = true;
		return this;
		
	}
	
	/**
	 * 加入條件
	 * @param con 條件物件
	 * @return 自己
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
	 * 建立條件式
	 * @return 條件字串
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
	 * 驗證條件表示式是否合理
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
					 String message = "條件格式錯誤:條件 + 條件\n應該為: 條件 + 邏輯 + 條件\n回傳條件式為空字串";
					 throw new ConditionBuildException(message);
				 }
				 if(L_count>1){
					 String message = "條件格式錯誤:條件 +邏輯...邏輯+ 條件\n應該為條件 + 邏輯 + 條件\n回傳條件式為空字串";
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
