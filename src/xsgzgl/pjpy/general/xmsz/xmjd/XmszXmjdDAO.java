package xsgzgl.pjpy.general.xmsz.xmjd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.form.User;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_项目设置_项目兼得_通用_DAO类
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

public class XmszXmjdDAO extends CommDAO {

	// ==================执行查询操作 begin==============================
	/**
	 * 获得结果集(评奖项目)
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmjdList(String xmdm) {

		DAO dao = DAO.getInstance();

		// 用户类型
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();

		// ====================SQL拼装================================
		StringBuilder sql = new StringBuilder();

		sql.append("select a.xmdm,a.xmmc,b.fjddm sfjd, ");
		sql.append("case when length(a.xmmc)>5 then substr(a.xmmc,0,5)||'...' ");
		sql.append("else a.xmmc end xmsx, ");
		sql.append("decode(a.xmlx,'01','奖学金','荣誉称号') xmlx ");
		sql.append("from xg_pjpy_pjxmwhb a ");
		sql.append("left join (select xmdm,fjddm from xg_pjpy_jdszb where xmdm = ? ) b ");
		sql.append("on a.xmdm = b.fjddm ");
		sql.append("where 1=1 ");
		sql.append("and a.pjxn = ? ");
		sql.append("and a.pjxq = ? ");
		sql.append("and a.xmdm <> ? ");
		sql.append("and (a.sfqy is null or a.sfqy = 'yes' or a.sfqy = '是') ");

		// ====================SQL拼装 end================================

		String[] inputValue = new String[] { xmdm, pjxn, pjxq, xmdm };
		String[] outputValue = new String[] { "xmdm", "xmmc", "xmlx", "xmsx",
				"sfjd" };

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				inputValue, outputValue);

		return list;
	}
	// ==================执行查询操作 end==============================

	// ==================执行新增操作 begin=============================
	/**
	 * 新增数据（xg_pjpy_jdszb:兼得设置表）
	 * 
	 * @table 兼得设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertJdszb(String xmdm, String[] fjddm, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_jdszb ");
		sql.append("(xmdm,fjddm)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (fjddm != null && fjddm.length > 0) {
			for (int i = 0; i < fjddm.length; i++) {
				String[] value = new String[] { xmdm, fjddm[i] };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	
	/**
	 * 新增数据（xg_pjpy_jdszb:兼得设置表）
	 * 
	 * @table 兼得设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean insertJdszb(String[] fjddm, String xmdm, User user)
			throws Exception {

		boolean flag = false;

		// 表名
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_jdszb ");
		sql.append("(xmdm,fjddm)");
		sql.append("values");
		sql.append("(?,?)");

		List<String[]> params = new ArrayList<String[]>();
		if (fjddm != null && fjddm.length > 0) {
			for (int i = 0; i < fjddm.length; i++) {
				String[] value = new String[] { fjddm[i], xmdm };
				params.add(value);
			}
			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}
	// ==================执行新增操作 end=============================
	
	// ==================执行删除操作 begin=============================
	/**
	 * 删除数据（xg_pjpy_jdszb:兼得设置表）
	 * 
	 * @table 兼得设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean deleteJdszb(String xmdm, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_jdszb";

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_jdszb ");
		sql.append("where 1=1 ");
		sql.append("and (xmdm=? or fjddm=?) ");

		List<String[]> params = new ArrayList<String[]>();
		params.add(new String[] { xmdm, xmdm });

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	// ==================执行删除操作 end =============================
}
