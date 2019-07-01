package xgxt.studentInfo.nthy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;

public class XszcDAO {
	
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
		
		if ("zczt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未处理","已注册","未注册" };
			mc = new String[] { "未处理","已注册" ,"未注册"};
		} else if ("tjjg".equals(lx)) {
			dm = new String[] { "xy", "zy","bj" };
			mc = new String[] {Base.YXPZXY_KEY, "专业","班级"};
		} else if("sfqf".equals(lx)){
			dm = new String[] { "0","1" };
			mc = new String[] { "已交清" ,"未交清"};
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
	public HashMap<String, String> viewZcxx(XszcActionForm form){
		String sql = "select a.xh,a.xydm,a.xymc,a.zydm,a.zymc,a.bjdm,a.bjmc,a.nj,a.xb,a.xm,b.xn,b.xq," +
				"b.zczt,b.wzcyy,b.czr,b.czsj,case when nvl(c.sfqf,'0')='1' then '未交清' else '已交清' end sfqfmc from view_xsjbxx a " +
				"left join xg_xsxx_nthy_xszcb b on a.xh=b.xh and b.xn=? and b.xq=? left join xg_rcsw_nthy_xsqfxxb c on a.xh=c.xh and c.xn=?  where a.xh=?";
		return dao.getMap(sql, new String[]{form.getXn(),form.getXq(),form.getXn(),form.getXh()}, new String[]{"xh","xm","nj","xymc","zymc","bjmc","zczt","wzcyy","czr","czsj","sfqfmc"});
	}
}
