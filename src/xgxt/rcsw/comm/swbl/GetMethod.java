package xgxt.rcsw.comm.swbl;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;

public class GetMethod {
	
	/**
	 * 学年列表
	 * @return List<HashMap<String,String>>
	 */
	public static List<HashMap<String,String>>getXnList(){
		
		return Base.getXnndList();
	}
	
	/**
	 * 学期列表
	 * @return List<HashMap<String,String>>
	 */
	public static List<HashMap<String,String>>getXqList(){
		
		return Base.getXqList();
	}
	
	/**
	 * 年度列表
	 * @return List<HashMap<String,String>>
	 */
	public static List<HashMap<String,String>>getNdList(){
		
		return Base.getXnndList();
	}
}
