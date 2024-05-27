package Data_Access_Object;

import java.util.ArrayList;

public interface Interface<T> {
	
	public int Add(T t);
	
	public int Update (T t);
	
	public int Delete (T t);
	
	public ArrayList<T> selectAll();
	
	public T seclectById(T t);
	
	public ArrayList<T> selectByCondition(String condition);
	
}
