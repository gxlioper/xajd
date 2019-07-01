package xsgzgl.pjpy.general.xmsz.sjsz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_时间设置_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2012
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class XmszSjszDAO extends CommDAO {

	// ==================执行查询操作 begin==============================
	/**
	 * 获得项目时间控制信息
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getSjszInfo(String xmdm) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.sqkssj,a.sqjssj,a.sqkzkg, ");
		sql.append("a.shkssj,a.shjssj,a.shkzkg,a.bz ");
		sql.append("from xg_pjpy_sjszb a ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.xmdm = ? ");

		// ====================SQL拼装 end================================

		String[] inputValue = new String[] { pjxn, pjxq, xmdm };
		String[] outputValue = new String[] { "sqkssj", "sqjssj", "sqkzkg",
				"shkssj", "shjssj", "shkzkg","bz" };

		HashMap<String, String> map = dao.getMap(sql.toString(), inputValue,
				outputValue);

		return map;
	}
	// ==================执行查询操作 end==============================
	
	// ==================执行新增操作 begin=============================
	/**
	 * 新增数据（xg_pjpy_sjszb:时间设置表）
	 * 
	 * @table 时间设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertSjszb(String xmdm, String sqkzkg, String shkzkg,
			String sqkssj, String sqjssj, String shkssj, String shjssj,
			String bz, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 表名
		String tableName = "xg_pjpy_sjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_sjszb ");
		sql.append("(pjxn,pjxq,pjnd,xmdm,bz,");
		sql.append("sqkssj,sqjssj,sqkzkg,");
		sql.append("shkssj,shjssj,shkzkg)");
		sql.append("values");
		sql.append("(?,?,?,?,?,?,?,?,?,?,?)");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { pjxn, pjxq, pjnd, xmdm, bz, sqkssj, sqjssj,
				sqkzkg, shkssj, shjssj, shkzkg });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行新增操作 end=============================
	
	// ==================执行删除操作 begin=============================
	/**
	 * 删除数据（xg_pjpy_sjszb:时间设置表）
	 * 
	 * @table 时间设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteSjszb(String xmdm, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 表名
		String tableName = "xg_pjpy_sjszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_sjszb a ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.pjnd = ? ");
		sql.append("and a.xmdm = ? ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { pjxn, pjxq, pjnd, xmdm });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end =============================
}
