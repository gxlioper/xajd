package xgxt.studentInfo.jqlx;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;

public class JqlxDAO {
	
	DAO dao = DAO.getInstance();
	
	/**
	 * 通过学号获取学生信息
	 * @param xh 学号
	 * @return
	 */
	public HashMap<String, String> getStuInfo(String xh){
		String sql = "select * from (select a.xh,a.xm,a.xb,a.xymc,a.zymc,a.bjmc,b.xm fdyxm from view_xsxxb a left join (select a.*,b.xm from fdybjb a,fdyxxb b where a.zgh=b.zgh) b on a.bjdm=b.bjdm) where xh=?";
		return dao.getMap(sql, new String[]{xh}, new String[]{"xh","xm","xb","xymc","zymc","bjmc","fdyxm"});
	}
	
	/**
	 * 获取学生假期留校申请信息
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public HashMap<String, String> getJqsqInfo(String pk){
		String sql = "select * from view_xg_xsxx_xsjqlxsqb where xn||xq||xh=? ";
		return dao.getMapNotOut(sql, new String[]{pk});
	}
	
	/**
	 * 获取学生假期留校申请条数
	 * @param pk 学年+学期+学号
	 * @return
	 */
	public String getCont(String pk){
		String sql = "select count(1) cont from xg_xsxx_xsjqlxsqb where xn||xq||xh=?";
		return dao.getOneRs(sql, new String[]{pk}, "cont");
	}
	
	/**
	 * 根据系统当前设置学期获取学期名称
	 * @return
	 */
	public String getXqmcFromXqdm(){
		String sql = "select xqmc from xqdzb where xqdm=?";
		return dao.getOneRs(sql, new String[]{Base.currXq}, "xqmc");
	}
	
	/**
	 * 下拉列表选项维护
	 * 
	 * @param lx
	 * @return
	 */
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("shzt".equalsIgnoreCase(lx)) {
			dm = new String[] { "未审核", "通过","不通过" };
			mc = new String[] { "未审核", "通过" ,"不通过"};
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
}
