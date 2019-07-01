package xgxt.xsxx.comm.jbsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_基本设置_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2011
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XsxxJbszDAO extends CommDAO {

	/**
	 * 获得最大显示区代码
	 * 
	 * @author 伟大的骆
	 */
	public String getMaxXsqdm() {

		DAO dao = DAO.getInstance();

		String sql = "select max(xsqdm) maxXsq from xg_view_xsxx_xsqsz";

		String maxXsq = dao.getOneRs(sql, new String[] {}, "maxXsq");

		if (Base.isNull(maxXsq)) {

			maxXsq = "0";

		}
		return maxXsq;
	}

	/**
	 * 清空显示区
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delXsq() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqszb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}
	
	/**
	 * 清空合并行
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delHbh() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqhbb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}
	
	/**
	 * 清空字段位置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delZdwz() throws Exception {

		DAO dao = DAO.getInstance();

		String sql = "delete from xg_xsxx_xsqwzb";

		boolean flag = dao.runUpdate(sql, new String[] {});

		return flag;
	}

	/**
	 * 保存显示区设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXsqsz(XsxxJbszForm model, User user) throws Exception {

		// 显示区代码
		String[] xsqdm = model.getXsqdm();
		// 显示顺序
		String[] xssx = model.getXssx();
		// 是否展开
		String[] sfzk = model.getSfzk();
		// 是否置顶
		String[] sfzd = model.getSfzd();
		// 是否启用
		String[] sfqy = model.getSfqy();

		boolean flag = true;

		if (xsqdm != null && xsqdm.length > 0) {

			String[] sql = new String[xsqdm.length];

			for (int i = 0; i < xsqdm.length; i++) {

				StringBuilder updateSql = new StringBuilder();
				updateSql.append("update xg_xsxx_xsqszb set ");
				updateSql.append("xssx = '" + xssx[i] + "',");
				updateSql.append("sfzk = '" + sfzk[i] + "',");
				updateSql.append("sfzd = '" + sfzd[i] + "',");
				updateSql.append("sfqy = '" + sfqy[i] + "'");
				updateSql.append(" where xsqdm = '" + xsqdm[i] + "'");

				sql[i] = updateSql.toString();
			}

			flag = saveArrDate(sql);
		}

		return flag;
	}

	/**
	 * 获得已应被分配字段列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYfpZdList(XsxxJbszForm model) {

		DAO dao = DAO.getInstance();

		// 字段
		String[] zd = model.getZd();
		// 是否启用
		String[] sfqy = model.getSfqy();

		ArrayList<String> zdList = new ArrayList<String>();
		if (zd != null && zd.length > 0) {
			for (int i = 0; i < zd.length; i++) {
				if ("否".equalsIgnoreCase(sfqy[i])) {
					zdList.add(zd[i]);
				}
			}
		}

		List<HashMap<String, String>> list = null;

		if (zdList != null && zdList.size() > 0) {
			StringBuilder sql = new StringBuilder();

			sql.append("select zd,xsmc from (select a.search_zd zd ");
			sql.append(",a.search_ymxs xsmc,decode(b.zd,'','否','是') sfqy ");
			sql.append("from xg_view_xsxx_zdszb a left join xg_xsxx_xsqwzb b ");
			sql.append("on a.search_zd = b.zd) where sfqy = '是' ");

			sql.append("and (");
			for (int i = 0; i < zdList.size(); i++) {
				if (i != 0) {
					sql.append("or ");
				}
				sql.append("zd=? ");
			}
			sql.append(")");

			list = dao.getList(sql.toString(), zdList.toArray(new String[] {}),
					new String[] { "zd", "xsmc" });
		}

		return list;
	}
}
