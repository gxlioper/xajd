package xgxt.wjsc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WjscService {

	/**
	 * 文件接收信息
	 * @return
	 */
	public List<HashMap<String, String>> queryWjscList(String yhm) {
		WjscDao dao = new WjscDao();
		List<HashMap<String, String>> rs = dao.queryWjjsList(yhm);
		List<HashMap<String, String>> tempList = new ArrayList<HashMap<String,String>>();
		if (rs != null && rs.size() > 0 && rs.size() < 3) {
			for (int i=3;i>rs.size();i--) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("wjh", "");
				map.put("wjffsj", "");
				tempList.add(map);
			}
			rs.addAll(tempList);
		}
		return rs;
	}
}
