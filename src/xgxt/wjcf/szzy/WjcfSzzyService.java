
package xgxt.wjcf.szzy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 深圳职业信息技术学院学生考勤信息Services</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-05-12</p>
 */
public class WjcfSzzyService {
	
	private WjcfSzzyDAO dao = null; 
	
	/**
	 * 查询表头
	 * @return
	 */
	public ArrayList<HashMap<String, String>>  getSearchTitle(){
		dao = new WjcfSzzyDAO();
		ArrayList<HashMap<String, String>> topTr = dao.getSearchTitle(); 
		return topTr;
	}
	
	/**
	 * 查询结果
	 * @param szzyQryModel
	 * @param tableName
	 * @return
	 */
	public ArrayList<String[]> getSearchResult(WjcfSzzyQryModel szzyQryModel, String tableName) throws Exception {
		dao = new WjcfSzzyDAO();
		ArrayList<String[]> searchResult = dao.getSearchResult(szzyQryModel, tableName);
		return searchResult;
	}
	
	/**
	 * 获取星期列表
	 * @return
	 */
	public List<HashMap<String, String>> arrayToList() {
		ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
		String[] arr1 = new String[]{"1", "2", "3", "4", "5", "6", "7"};
		String[] arr2=new String[]{"一", "二", "三", "四", "五", "六", "日"};
		int len = (arr1.length > arr2.length) ? arr2.length : arr1.length;
		HashMap<String, String> map = null;
		for (int i = 0; i < len; i++) {
			map = new HashMap<String, String>();
			map.put("en", arr1[i]);
			map.put("cn", arr2[i]);
			list.add(map);
		}
		return list;
	}
	
	/**
	 * 增加考勤时获取的信息
	 * @param tableName
	 * @param pk
	 * @param pkValue
	 * @return
	 */
	public Map<String, String> getAddResult(String tableName, String pk, String pkValue ,String pkValue2){
		dao = new WjcfSzzyDAO();
		Map<String, String> result = dao.getAddResult(tableName, pk, pkValue ,pkValue2);
		return result;
	}
	
	/**
	 * 保存考勤信息
	 * @param tableName
	 * @param pkVal
	 * @param szzyKqxxModel
	 * @return
	 * @throws Exception
	 */
	public boolean saveKqxx(String tableName, String pkVal, WjcfSzzyKqxxModel szzyKqxxModel, String pkValue2) throws Exception {
		dao = new WjcfSzzyDAO();
		boolean flag = dao.saveKqxx(tableName, pkVal, szzyKqxxModel, pkValue2);
		return flag;
	}
	
	/**
	 * 学工考勤信息查询
	 * @param szzyQryModel
	 * @param tableName
	 * @return
	 * @throws Exception
	 */
	public ArrayList<String[]> getKqxxTjJg(WjcfSzzyQryModel szzyQryModel, String tableName) throws Exception {
		dao = new WjcfSzzyDAO();
		ArrayList<String[]> searchResult = dao.getKqxxTjJg(szzyQryModel, tableName);
		return searchResult;
	}
	
	/**
	 * 获取年份，月份列表
	 * @param type
	 * @return
	 */
	public List<HashMap<String, String>> getChkList1(int type) {
		List<HashMap<String, String>> li = new ArrayList<HashMap<String,String>>();
		String[] sl = null;
		if (type == 1) {
			sl = new String[] { "2002", "2003","2004","2005","2006","2007" ,"2008","2009","2010","2011"};
			for (int i=0;i<sl.length;i++){
				HashMap<String, String> rs = new HashMap<String, String>(); 
				rs.put("en", sl[i]);
				rs.put("cn", sl[i]);
				li.add(rs);
			}
		}// end if
		if (type == 2) {
			sl = new String[] { "01", "02","03","04","05","06" ,"07","08","09","10","11","12"};
			for (int i=0;i<sl.length;i++){
				HashMap<String, String> rs = new HashMap<String, String>(); 
				rs.put("en", sl[i]);
				rs.put("cn", sl[i]);
				li.add(rs);
			}
		}
		return li;
	}
}
