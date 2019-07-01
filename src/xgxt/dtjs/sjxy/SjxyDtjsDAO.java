package xgxt.dtjs.sjxy;


import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class SjxyDtjsDAO extends CommonQueryDAO {

	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(SjxyDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNdXnXqList(request);
		
		String xydm = myForm.getXydm();
		
		List<HashMap<String, String>> zbmcList = getSelectList("sjxy_dzbb", "sszb", "sszb","","xydm",xydm);//所属党支部
		List<HashMap<String, String>> xsccList = getSelectList("dtjs_xsccb", "xsccdm", "xsccmc","","","");//学生层次
		List<HashMap<String, String>> zzlxList = getSelectList1("zzlx");//转正类型
		List<HashMap<String, String>> dyzzlxList = getSelectList1("dyzzlx");//党员转正类型
		request.setAttribute("zbmcList", zbmcList);
		request.setAttribute("xsccList", xsccList);
		request.setAttribute("zzlxList", zzlxList);
		request.setAttribute("dyzzlxList", dyzzlxList);
	}

	/**
	 * 获得党支部信息（各个干部）
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getDzbxxList(String pk) {
		DAO dao = DAO.getInstance();
		String sql = "select id, sszb,sj, fsj, zzwy, xcwy, jjwy,xsrs from view_sjxy_dzb where xydm = ?";
		List<HashMap<String, String>> list = dao.getList(sql,
				new String[] { pk }, new String[] { "id", "sszb", "sj", "fsj",
						"zzwy", "xcwy", "jjwy", "xsrs" });
		return list;
	}
	
	/**
	 * 获得需维护下拉框值
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList(String tableName,
			String dm, String mc, String msg, String pk, String pkValue) {

		DAO dao = DAO.getInstance();
		
		if (Base.isNull(msg)) {
			msg = "----请选择-----";
		}

		StringBuffer sql = new StringBuffer();
		sql.append("select '' dm, '" + msg + "'mc from dual union");
		sql.append(" select distinct(");
		sql.append(dm);
		sql.append(" ) dm,");
		sql.append(mc + " mc");
		sql.append(" from " + tableName);
		if (!Base.isNull(pk)) {
			sql.append(" where " + pk + "= '" + pkValue + "'");
		}
		sql.append(" order by dm nulls first");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 获得无需维护下拉框值
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getSelectList1(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("zzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "延长预备期", "预备期满被取消预备党员资格", "预备期未满被取消预备党员资格" };
			mc = new String[] { "延长预备期", "预备期满被取消预备党员资格", "预备期未满被取消预备党员资格" };
		}else if ("dyzzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "按期转正", "延长预备期满转正"};
			mc = new String[] { "按期转正", "延长预备期满转正"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(dm, mc);
	}
	
	/**
	 * 获得党总支信息
	 * 
	 * @param tableName(表名)
	 * @param dm(代码)
	 * @param mc(名称)
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getDzz(String xydm) {

		DAO dao = DAO.getInstance();
		String sql = "select xydm, xymc, dzzmc, khqk, xsrs, dyrs, sqrs, bz from view_sjxy_dzz where xydm = ?";
		return dao
				.getMap(sql.toString(), new String[] { xydm }, new String[] {
						"xydm", "xymc", "dzzmc", "khqk", "xsrs", "dyrs",
						"sqrs", "bz" });
	}
}
	