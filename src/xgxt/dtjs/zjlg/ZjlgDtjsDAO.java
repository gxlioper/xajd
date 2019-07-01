package xgxt.dtjs.zjlg;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.dtjs.DtjsDAO;
import xgxt.dtjs.zjlg.zsdy.ZsdyModel;
import xgxt.form.SaveForm;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;

public class ZjlgDtjsDAO extends DtjsDAO {

	/**
	 * 获得学生层次
	 */
	public List<HashMap<String, String>> getXscc() {
		return commonQueryforList("dtjs_xsccb", "", new String[] {},
				new String[] { "xsccdm", "xsccmc" }, "");
	}
	
	/**
	 * 获得支部名称
	 */
	public String getZbmc(String xh) {
		DAO dao = new DAO();

		StringBuffer sql = new StringBuffer();
		sql.append("select b.zbmc from zjlg_zbfp a,zjlg_zbmc  b where a.zbdm = b.zbdm and ");
		sql.append("a.bjdm = (select bjdm from view_xsjbxx where xh = ?)");

		String zbmc = dao.getOneRs(sql.toString(), new String[] { xh }, "zbmc");
		zbmc = Base.isNull(zbmc) ? "无所属" : zbmc;
		return zbmc;
	}
	
	/**
	 * 获得党员相关列表
	 * 
	 * @author luojw
	 */
	public ArrayList<String[]> getDyxxList(String tableName, ZsdyModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		String[] queryList = new String[] { "nj", "zzmm", "zbdm", "xydm",
				"zydm", "bjdm" };
		String[] queryLikeList = new String[] { "xh", "xm" };
		MakeQuery myQuery = new MakeQuery();
		myQuery.makeQuery(queryList, queryLikeList, model);
		return CommonQueryDAO.commonQuery(tableName, myQuery.getQueryString(),
				myQuery.getInputList(), colList, "", model);
	}
	
	/**
	 * 获得联系人信息
	 * 
	 * @author luojw
	 */
	public HashMap<String, String> getLxrInfo(String xh) {
		DAO dao = new DAO();
		String[] colList = new String[] { "xh", "xm", "bjmc", "zbmc","zzmm" };
		String sql = "select * from view_zjlg_lxrxx where xh =?";
		return dao.getMap(sql, new String[] { xh }, colList);
	}
	
	/**
	 * 获得违纪情况
	 * 
	 * @author luojw
	 */
	public String getWjqk(String xh) {
		DAO dao = new DAO();
		String sql = "select xh, count(*) wjcs from view_wjcf where xxsh = '通过' and xh=? group by xh";
		String wjcs= dao.getOneRs(sql, new String[]{xh}, "wjcs");
		String wjqk = "否";
		if (!Base.isNull(wjcs) && !"0".equalsIgnoreCase(wjcs)) {
			wjqk = "是";
		}
		return wjqk;
	}
	
	/**
	 * 批量保存党团建设相关信息
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public boolean saveDtjs(SaveForm form, Object model) throws Exception {
		DAO dao = DAO.getInstance();
		return dao.saveData(form, model);
	}
	
	/**
	 * 获得批量学号
	 * 
	 * @author luojw
	 * @throws Exception
	 */
	public String[] getArrZd(String tableName, String zd, String pk,
			String[] pkValue) throws Exception {
		DAO dao = DAO.getInstance();
		StringBuffer sql = new StringBuffer();
		sql.append("select " + zd + " from " + tableName);
		if (pkValue.length == 1) {
			sql.append(" where " + pk + "='" + pkValue[0] + "'");
		} else {
			for (int i = 0; i < pkValue.length; i++) {
				if (i == 0) {
					sql.append(" where " + pk + " = '" + pkValue[i] + "'");
				} else {
					sql.append(" or " + pk + " = '" + pkValue[i] + "'");
				}
			}
		}

		return dao.getRs(sql.toString(), new String[] {}, zd);
	}
}
