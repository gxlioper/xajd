package xgxt.xsxx.comm.sjy.jcsjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xgxt.xsxx.comm.sjy.XsxxSjyDAO;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 学生信息_数据源_基础数据设置_DAO类
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

public class SjyJcsjszDAO extends XsxxSjyDAO {

	/**
	 * 获得字段来源表数量
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getZdLybNum(String[] zd) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.zd,nvl(b.num,0) num from ");
		sql.append(" (select zd from xg_xsxx_zdszb ");
		sql.append(" where 1 = 1 ");
		if (zd != null && zd.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < zd.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" zd = '" + zd[i] + "'");
			}
			sql.append(" ) ");
		}
		sql.append(") a left join ");
		sql.append(" (select zd,count(1) num from xg_xsxx_zdlyb ");
		sql.append(" group by zd) b on a.zd = b.zd ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> lybNumList = dao.getList(sql.toString(),
				new String[] {}, new String[] { "zd", "num" });

		return lybNumList;
	}
	
	/**
	 * 保存字段设置
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveZdsz(List<String[]> params, User user) throws Exception {
		
		String tableName = "xg_xsxx_zdszb";
		StringBuilder sql = new StringBuilder();	
		sql.append("update xg_xsxx_zdszb set ");
		sql.append("xsmc = ?, ");
		sql.append("xgwz = ?, ");
		sql.append("lrxz = ?, ");
		sql.append("wkxz = ?, ");
		sql.append("lrxs = ?, ");
		sql.append("lyb = ?, ");
		sql.append("sfqy = ? ");
		sql.append("where zd = ? ");
		
		return saveArrDate(sql.toString(), params, tableName, user);
	}
	
	//===========================以下方法by 今天中五百万=========================
	/**
	 * 获取显示模块列表
	 * @author 今天中五百万 
	 */
	public List<HashMap<String, String>> getXsmkList(SjyJcsjszForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select ename,cname,sfxs from xg_xsxx_xspz order by xssx ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"ename", "cname","sfxs" });
	}
	
	/**
	 * 获取学生信息详细页配置
	 * @author 今天中五百万 
	 */
	public List<HashMap<String, String>> getXxypz(SjyJcsjszForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		sql.append(" select ename,xslxdm,xslxmc,picname,bz from xg_xsxx_xxypzb order by xslxdm ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"ename", "xslxdm", "xslxmc", "picname","bz"  });
	}
	
	/**
	 * 修改学生信息详细页显示顺序
	 * @author 今天中五百万 
	 */
	public boolean updateXssx(String[] xsxm_can, String[] xssx)
			throws Exception {

		String[] updateSql = new String[xsxm_can.length];
		for (int i = 0; i < xsxm_can.length; i++) {
			updateSql[i] = " update xg_xsxx_xspz set xssx='" + xssx[i]
					+ "' where ename= '" + xsxm_can[i] + "' ";

		}
		return this.saveArrDate(updateSql);

	}
	
	/**
	 * 修改学生信息详细页显示顺序
	 * @author 今天中五百万 
	 */
	public boolean updateXxypz(String[] xsxm_no, String[] xslxArr)
			throws Exception {

		String[] updateSql = new String[xsxm_no.length];
		for (int i = 0; i < xsxm_no.length; i++) {
			updateSql[i] = " update xg_xsxx_xspz set sfxs='" + xslxArr[i]
					+ "' where ename= '" + xsxm_no[i] + "' ";

		}
		return this.saveArrDate(updateSql);

	}
	//=================================================================
}
