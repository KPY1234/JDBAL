package jdbal.structure;

import java.util.ArrayList;

/**
 * 一列資料組物件，由多個紀錄組成
 * 
 * @author 高浩馭
 * @see    jdbal.structure.Record
 * @since  JDBAL-0.0.4
 *
 */
public class Tuple {

	ArrayList<Record> records;
	
	public Tuple(){
		records = new ArrayList<Record>();
	}
	
	public void addRecord(String record){
		records.add(new Record(record));
	}
	
	public Record getRecord(int index){
		return records.get(index);
	}
	
	public int size(){
		return records.size();
	}
	
	public String toString(){
		String str = "[ ";
		for(int i=0;i<records.size();i++){
			str += records.get(i);
			if(i!=records.size()-1)
				str += "\t";
		}
		str += " ]";
		return str;
	}
}
