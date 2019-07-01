package xgxt.studentInfo.nthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class XsbdDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 下拉列表选项维护
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("bdzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未处理","已报到","未报到" };
			mc = new String[] { "未处理","已报到" ,"未报到"};
		} else if ("tjjg".equals(lx)) {
			dm = new String[] { "xy", "zy","bj" };
			mc = new String[] {Base.YXPZXY_KEY, "专业","班级"};
		} 
		return dao.arrayToList(dm, mc);
	}
	

	/**
	 * 学期列表
	 * @return
	 */
	public List<HashMap<String, String>> getXq() {
		String sql = "select xqdm,xqmc from xqdzb where xqjb<3";
		return dao.getList(sql, new String[] {}, new String[] {"xqdm","xqmc"});
	}
	
	/**
	 * 获取学年下拉
	 * @return
	 */
	public List<HashMap<String, String>> getXnList() {
		// 返回查询时使用的学年、年代列表
		ArrayList<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		String xn = null;
		String nd = null;
		HashMap<String, String> map = new HashMap<String, String>();
		int currentNd = Integer.parseInt(DealString.getDateTime().substring(0,
				4));
		for (int i = currentNd - 5; i < currentNd + 5; i++) {
			map = new HashMap<String, String>();
			nd = String.valueOf(i);
			xn = String.valueOf(i) + "-" + String.valueOf(i + 1);
			map.put("nd", nd);
			map.put("xn", xn);
			aList.add(map);
		}
		return aList;
	}
	
	/**
	 * 查看学生报到相关信息
	 * @param form
	 * @return
	 */
	public HashMap<String, String> viewBdxx(XsbdActionForm form){
		String sql = "select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq,b.bdzt,b.wbdyy,b.czr,b.czsj from view_xsjbxx a left join xg_xsxx_nthy_xsbdb b on a.xh=b.xh and b.xn=? and b.xq=?   where a.xh=?";
		return dao.getMap(sql, new String[]{form.getXn(),form.getXq(),form.getXh()}, new String[]{"xh","xm","nj","xymc","zymc","bjmc","bdzt","wbdyy","czr","czsj"});
	}
}
