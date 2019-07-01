package xgxt.pjpy.hhgxy.zhszcp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class PjpyHhgxyZhszcpDAO {

	private DAO dao = DAO.getInstance();
	
	private ArrayList<String> values = new ArrayList<String>();
	
	public static PjpyHhgxyZhszcpDAO myDao = new PjpyHhgxyZhszcpDAO();
	
	public static PjpyHhgxyZhszcpDAO getInstance() {
		return myDao;
	}
	
	/**
	 * 德育操行分单个保存
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean saveDycxf(ZhszcpModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.insert("dychfb_hhgxy", new String[] { "xh",
				"xn", "xq", "dm", "df" }, new String[] { model.getXh(),
				model.getXn(), model.getXq(), model.getDm(), model.getDf() },
				request);
	}
	
	/**
	 * 德育操行分单个修改
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean updateDycxf(ZhszcpModel model, HttpServletRequest request)
			throws Exception {
		return StandardOperation.update("dychfb_hhgxy", new String[] { "xn",
				"xq", "dm", "df" }, new String[] { model.getXn(),
				model.getXq(), model.getDm(), model.getDf() }, "id", model
				.getPkValue(), request);
	}
	
	/**
	 * 德育操作分批量删除
	 * @param keys
	 * @return
	 * @throws Exception
	 */
	public String deleteDycxf(String[] keys) throws Exception {
		String rs = "";
		String[] sql = new String[keys.length];
		for (int i = 0; i < keys.length; i++) {
			StringBuffer sb = new StringBuffer("delete from dychfb_hhgxy where id='");
			sb.append(keys[i]);
			sb.append("'");
			sql[i] = sb.toString();
		}
		int flag[] = dao.runBatch(sql);
		for (int i = 0;i < flag.length; i++) {
			if (flag[i] == -1) {
				rs += (i+1) + ",";
			}
		}
		return StringUtils.isNull(rs) ? "" : rs.substring(0, rs.length() - 1);
	} 
	
	public StringBuffer getWhereSql(ZhszcpModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		values = new ArrayList<String>();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm = ?");
			values.add(DealString.toGBK(model.getXm()));
		}
		
		return whereSql;
	}
	
	/**
	 * 德育操作分查询
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryDycxf(ZhszcpModel model) throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		String sql = "select rownum,a.id pk,a.xh,a.xn,a.xm,a.xq,a.mc,a.df,'',a.bjmc zf view_dychfb_hhgxy a where 1=1 ";
		String[] opList = new String[]{"pk", "rownum", "xh", "xm", "bjmc", "xn", "xq", "mc", "df", "zf"};
		StringBuffer whereSql = getWhereSql(model);
		rs = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return rs;
	}
	
	/**
	 * 德育操作分修改显示信息
	 * 
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> viewDycxf(String pkValue) throws Exception {
		return dao
				.getMapNotOut(
						"select id,xh,xm,xb,nj,xymc,zymc,bjmc,xn,xq,mc,df from view_dychfb_hhgxy where id=?",
						new String[] { pkValue });
	}
	
	/**
	 * 操行项目列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getCxList() throws Exception {
		return dao.getList("select dm,xm mc from dycxxmb", new String[]{}, new String[]{"dm", "mc"});
	}
	
	/**
	 * 
	 * @param param
	 * @param xn
	 * @param xq
	 * @return
	 * @throws SQLException
	 */
	public boolean saveDycxf(List<HashMap<String, String>> param,
			String xn, String xq) throws SQLException {
		DAO dao = DAO.getInstance();
		boolean doFlag = false;
		int length = param.size();
		String[] delPkSql = new String[length];
		String[] inserSql = new String[length];
		String pk = "xn||xq||xh||dm";
		// String[] fields =
		// {"xh","xn","xq","sjzt","sjnr","sjsj","sjd","wzclqk","wzclpj","sjfs","bz"};
		for (int i = 0; i < length; i++) {
			String xh = DealString.toGBK(param.get(i).get("xh"));
			String dm = DealString.toGBK(param.get(i).get("sjzt"));
			String df = DealString.toGBK(param.get(i).get("sjlr"));
			if (!Base.isNull(xh) && !Base.isNull(xn)
					&& !Base.isNull(xq) && !Base.isNull(dm)) {
				delPkSql[i] = "delete dychfb_hhgxy where " + pk + "= '" + xn.trim() + xq.trim()
						+ xh.trim() + dm.trim() + "'";
				inserSql[i] = "insert into dychfb_hhgxy(xh,xn,xq,dm,df) "
						+ "values('"
						+ xh
						+ "','"
						+ xn
						+ "','"
						+ xq
						+ "','"
						+ dm
						+ "','"
						+ df
						+ ""
						+"')";
			}
		}
		String[] exesql = dao.unionArrayTo(delPkSql, inserSql);//合并数组
		int[] array = null;
		array = dao.runBatch(exesql);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}   
}
