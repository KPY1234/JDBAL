package jdbal.action;

import jdbal.structure.Join;

/**
 * 合併表示式建立者物件
 * 
 * @author 高浩馭
 * @see    jdbal.structure.Join
 * @since  JDBAL-0.0.3
 *
 */
public class JoinBuilder {

	String joins = "";
	
	/**
	 * 加入合併方法
	 * @param join 合併方法
	 * @return 自己
	 */
	public JoinBuilder addJoin(Join join){
		
		joins += " "+join.getJoinType()+" "+join.getReferTable()+" ON "+join.getSelfTable()+"."
				+join.getSelfField()+" = "+join.getReferTable()+"."+join.getReferField();
		
		return this;
	}
	
	/**
	 * 建立合併表示式
	 * @return 合併表示式
	 */
	String build(){
		return joins;
	}
	
}
