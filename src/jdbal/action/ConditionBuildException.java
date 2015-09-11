package jdbal.action;
/**
 * 條件建立例外
 * @author 高浩馭
 * @since  JDBAL-0.0.3
 *
 */
public class ConditionBuildException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 建構子
	 * @param message 例外訊息
	 */
	public ConditionBuildException(String message){
		super(message);
	}
	

//	public static void main(String[] args) throws ConditionBuildException {
//		
//		int x=0;
//		
//		if(x==0){
//			throw new ConditionBuildException("x=0");
//		}
//		if(x==1){
//			throw new ConditionBuildException("x=1");
//		}
//		
//	}
	
}
