
package xgxt.shgz.server.common;

import java.util.HashMap;
import java.util.List;

import xgxt.action.Base;
import xgxt.szdw.dao.common.CommonDAO;

public class CommonService {
	
	/**
	* <p>Title: 学工管理系统</p>
	* <p>Description: 学生信息管理思政队伍-基础service类</p>
	* <p>Copyright: Copyright (c) 2008</p>
	* <p>Company: zfsoft</p>
	* @author 鲁宁
	* @version 1.0
	*/
	
	CommonDAO dao = new CommonDAO();
	
	public HashMap<String, String> inputXnNjXq(HashMap<String, String> rs){
	//    判断rs里学年，年度，学期是否有值，无则放入当前学年，年度，学期
		String xn           = Base.currXn;
		String xq           = Base.currXq;
		String nd           = Base.currNd;
		if(rs.get("xn").equalsIgnoreCase("")){
			rs.put("xn", xn);
		}
		
		if(rs.get("nd").equalsIgnoreCase("")){
			rs.put("nd", nd);
		}
		
		if(rs.get("xq").equalsIgnoreCase("")){
			rs.put("xq", xq);
		}
		return rs;
	}
	
	public List getShztList() {
		//    得到审核状态列表
			List ShztList = dao.getChkList(3);//表头 
			return ShztList;
	}

}
