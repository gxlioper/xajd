package xgxt.dtjs.czxx;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.dtjs.DtjsDAO;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;

public class CzxxDtjsDAO extends DtjsDAO {

	/**
	 * 设置所需列表
	 * 
	 * @param form
	 * @param request
	 * @author luojw
	 * @throws Exception
	 */
	public void setList(CzxxDtjsForm myForm, HttpServletRequest request)
			throws Exception {
		
		//FormModleCommon.setNjXyZyBjList(request);
		FormModleCommon.setNjXyZyBjListForFdyBzr(request);
		FormModleCommon.setNdXnXqList(request);
		
		String xydm = myForm.getXydm();
		String xxdm = StandardOperation.getXxdm();//学校代码
		String zhdj = myForm.getZhdj();//转换等级
		
		List<HashMap<String, String>> zbmcList = getSelectList("sjxy_dzbb", "sszb", "sszb","","xydm",xydm);//所属党支部
		List<HashMap<String, String>> xsccList = getSelectList("dtjs_xsccb", "xsccdm", "xsccmc","","","");//学生层次
		List<HashMap<String, String>> zzlxList = getSelectList("zzlx");//转正类型
		List<HashMap<String, String>> dyzzlxList = getSelectList("dyzzlx");//党员转正类型
		List<HashMap<String, String>> sflxList = getSelectList("sflx2");//是否类型
		List<HashMap<String, String>> zhList = getZhdjList(zhdj);
		
		request.setAttribute("zbmcList", zbmcList);
		request.setAttribute("xsccList", xsccList);
		request.setAttribute("zzlxList", zzlxList);
		request.setAttribute("dyzzlxList", dyzzlxList);
		request.setAttribute("sflxList", sflxList);
		request.setAttribute("zhList", zhList);
		
		request.setAttribute("xxdm", xxdm);
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
	public List<HashMap<String, String>> getSelectList(String lx) {
		String[] dm = null;
		String[] mc = null;
		
		if ("zzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "延长预备期", "预备期满被取消预备党员资格", "预备期未满被取消预备党员资格" };
			mc = new String[] { "延长预备期", "预备期满被取消预备党员资格", "预备期未满被取消预备党员资格" };
		}else if ("dyzzlx".equalsIgnoreCase(lx)) {
			dm = new String[] { "按期转正", "延长预备期满转正"};
			mc = new String[] { "按期转正", "延长预备期满转正"};
		}else if ("sflx1".equalsIgnoreCase(lx)) {
			dm = new String[] { "是", "否"};
			mc = new String[] { "是", "否"};
		}else if ("sflx2".equalsIgnoreCase(lx)) {
			dm = new String[] { "是"};
			mc = new String[] { "是"};
		}
		DAO dao = DAO.getInstance();
		return dao.arrayToList(dm, mc);
	}
}
	