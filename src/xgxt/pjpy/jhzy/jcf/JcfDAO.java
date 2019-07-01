package xgxt.pjpy.jhzy.jcf;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.pjpy.jhzy.JhzyPjpyDAO;

public class JcfDAO extends JhzyPjpyDAO {

	/**
	 * @author luo
	 * @describe 获得奖惩分列表
	 */
	public ArrayList<String[]> getJcList(String tableName, JcfModel model,
			String[] colList) throws IllegalArgumentException,
			SecurityException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		
		// 学院代码
		String xydm = DealString.toGBK(model.getXydm());
		// 专业代码
		String zydm = DealString.toGBK(model.getZydm());
		// 班级代码
		String bjdm = DealString.toGBK(model.getBjdm());
		// 年级
		String nj = DealString.toGBK(model.getNj());
		// 学年
		String xn = DealString.toGBK(model.getXn());
		// 学期
		String xq = DealString.toGBK(model.getXq());
		// 学号
		String xh = DealString.toGBK(model.getXh());
		xh = Base.isNull(xh) ? "%" : xh;
		// 姓名
		String xm = DealString.toGBK(model.getXm());
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		
		
		StringBuffer query = new StringBuffer();

		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		String[] inPutList = new String[] {xh,xm};
		
		return commonQuery(tableName, query.toString(), inPutList, colList,
				"", model);
	}

	/**
	 * 保存奖惩分
	 * 
	 * @throws SQLException
	 */
	public boolean saveJcf(JcfModel model, HttpServletRequest request)
			throws SQLException {
		boolean flg = false;
		DAO dao = DAO.getInstance();
		String[] sqlList = new String[model.getJfxm().length + 1];
		StringBuffer sql = null;
		try {
			StringBuffer buff = new StringBuffer();
			buff.append(model.getXh()).append(model.getXn()).append(model.getXq());
			StandardOperation.delete("jhzy_jcf", "xh||xn||xq", buff.toString(),
					request);
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		for (int i = 0; i < model.getJfxm().length; i++) {
			sql = new StringBuffer();
			sql.append("insert into jhzy_jcf (xh,xn,xq,jfxm,fz,jfly) values('");
			sql.append(model.getXh());
			sql.append("','");
			sql.append(model.getXn());
			sql.append("','");
			sql.append(model.getXq());
			sql.append("','");
			sql.append(DealString.toGBK(model.getJfxm()[i]));
			sql.append("','");
			sql.append(model.getFz()[i]);
			sql.append("','");
			sql.append(DealString.toGBK(model.getJfly()[i]).replace("'","‘"));
			sql.append("')");
			sqlList[i] = sql.toString();
		}
		int[] res = dao.runBatch(sqlList);
		for (int i = 0; i < res.length; i++) {
			flg = (res[i] == Statement.EXECUTE_FAILED) ? false : true;
			if (!flg)
				break;
		}

		return flg;
	}
	
	/**
	 * @author luo
	 * @describe 获得学期列表
	 */
	public List<HashMap<String, String>> getXqList() {
		return commonQueryforList("xqdzb", "", new String[] {}, new String[] {
				"xqmc", "xqdm" }, "");
	}
	
	/**
	 * @author luo
	 * @describe 获得学生基本信息
	 */
	public static HashMap<String, String> getStuInfo(String xh) {
		DAO dao = DAO.getInstance();
		String sql = "select xh, xm, xb,nj, xymc, zymc, bjmc,nj from view_xsjbxx where xh = ?";
		return dao.getMap(sql,new String[]{xh}, new String[] { "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc","nj" });
	}
	
	/**
	 * 获得学生奖惩分值
	 * 
	 * @throws SQLException
	 */
	public List<HashMap<String, String>> getJcf(String pk) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select jfxm,fz,jfly from jhzy_jcf where xh||xn||xq=? order by jfxm";
		return dao.getList(sql, new String[] { pk }, new String[] { "jfxm",
				"fz", "jfly" });
	}
	
	/**
	 * 获得学生违纪处分
	 * 
	 * @throws SQLException
	 */
	public List<String> getWjcf(String xn,String xq,String xh
			) throws SQLException {
		DAO dao = DAO.getInstance();
		String sql = "select a.cflbmc || ':扣'||b.cfkf||'分' cf from view_wjcf a, zjlg_wjkfb b"
				+ " where a.xn = b.xn and a.cflb = b.cfdm and a.xn = ? and a.xq=? and a.xh = ?";
		return dao.getList(sql, new String[]{xn,xq,xh}, "cf");
		
	}
	
	/**
	 * 删除奖惩分
	 * @throws Exception 
	 */
	public boolean delJcf(String pk, HttpServletRequest request)
			throws Exception {
		return StandardOperation.delete("jhzy_jcf", "xh||xn||xq", pk, request);
	}

	/**
	 * 获得学期名称
	 * @throws Exception 
	 */
	public String getXqmc(String xqdm) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?", new String[]{xqdm}, "xqmc");
	}
	
	/**
	 * 获得学期代码
	 * @throws Exception 
	 */
	public String getXqdm(String xqmc) {
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select xqdm from xqdzb where xqmc=?", new String[]{xqmc}, "xqdm");
	}
	
	/**
	 * 获得综合素质分上限
	 * 
	 * @throws Exception
	 */
	public String isZhfsx() {
		DAO dao = DAO.getInstance();
		String sql = "select count(*) num from jhzy_zhf_sx where xn=? and xq=?";
		return dao.getOneRs(sql, new String[] { Base.getJxjsqxn(), Base.getJxjsqxq() },
				"num");
	}
}
