package jdbal.structure;

/**
 * 一個紀錄
 * 
 * @author 高浩馭
 * @since  JDBAL-0.0.4
 *
 */
public class Record {

	private String value;
	
	public Record(String value){
		this.value = value;
	}
	
	public String getValue(){
		return value;
	}
	
	public String toString(){
		return value;
	}
}
