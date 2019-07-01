package xsgzgl.pjpy.general.zhcp;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.pjpy.comm.zhcp.jbsz.ZhcpJbszForm;
import xgxt.pjpy.comm.zhcp.zczf.ZhcpZczfForm;
import xgxt.utils.CommonQueryDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_综合测评_通用_DAO类
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

public class PjpyZhcpDAO extends CommDAO {

	// ==================基础方法 begin==============================

	public String[] getNotNullArr(String[] pkValue) {

		List<String> notNullArr = new ArrayList<String>();
		for (int i = 0; i < pkValue.length; i++) {
			if (!Base.isNull(pkValue[i])) {
				notNullArr.add(pkValue[i]);
			}
		}
		return notNullArr.toArray(new String[] {});

	}

	/**
	 * 获取综测排名显示列 lx en,cn
	 * 
	 * @return String[]
	 */
	public String[] getZcPmArr(String lx) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zcpm = jbszForm.getZcpm();

		List<String> en = new ArrayList<String>();

		List<String> cn = new ArrayList<String>();

		if ("0".equalsIgnoreCase(zcpm)) {

			en.add("cpzpm");
			cn.add("参评组排名");
		}

		if ("1".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			en.add("zcfnjxypm");
			cn.add("综测分年级学院排名");

		}

		if ("2".equalsIgnoreCase(zcpm) || "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			en.add("zcfnjzypm");
			cn.add("综测分年级专业排名");
		}

		if ("3".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			en.add("zcfbjpm");
			cn.add("综测分班级排名");
		}

		if ("en".equalsIgnoreCase(lx)) {

			return en.toArray(new String[] {});

		} else {

			return cn.toArray(new String[] {});

		}
	}

	/**
	 * 获取综测排名显示列 lx en,cn
	 * 
	 * @return String[]
	 */
	public String[] getZyPmArr(String lx) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zypm = jbszForm.getZypm();

		List<String> en = new ArrayList<String>();

		List<String> cn = new ArrayList<String>();

		if ("0".equalsIgnoreCase(zypm)) {
			en.add("zyfcpzpm");
			cn.add("智育分参评组排名");
		}

		if ("1".equalsIgnoreCase(zypm) || "5".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm) || "7".equalsIgnoreCase(zypm)) {
			en.add("zyfnjxypm");
			cn.add("智育分年级学院排名");
		}

		if ("2".equalsIgnoreCase(zypm) || "4".equalsIgnoreCase(zypm)
				|| "6".equalsIgnoreCase(zypm) || "7".equalsIgnoreCase(zypm)) {
			en.add("zyfnjzypm");
			cn.add("智育分年级专业排名");
		}

		if ("3".equalsIgnoreCase(zypm) || "5".equalsIgnoreCase(zypm)
				|| "4".equalsIgnoreCase(zypm) || "7".equalsIgnoreCase(zypm)) {
			en.add("zyfbjpm");
			cn.add("智育分班级排名");
		}

		if ("en".equalsIgnoreCase(lx)) {

			return en.toArray(new String[] {});

		} else {

			return cn.toArray(new String[] {});

		}

	}

	// ==================基础方法 end==============================

	// ==================综测分维护 begin==============================
	/**
	 * 获得综测项目 （根据综测项目表配置信息获取）
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getCshXmList(User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		String pjnd = jbszModel.getPjnd();

		// -------------------获取需要单独维护的综测项目 begin-----------------
		sql.append(" select a.*   ");
		sql.append(" from xg_pjpy_zcxmb a ");
		sql.append(" where xn = ? and  xq=? and  nd=? ");
		sql.append(" and ddwh = 'yes'  ");
		sql.append(" union ");
		// -------------------获取需要单独维护的综测项目 end-------------------

		// ---------获取存在子项无需单独维护并且数据非来自来源表的下项目 begin-----------------

		sql.append(" select a.*  from xg_pjpy_zcxmb a ");
		sql.append(" where xn = ? and xq=? and  nd=?");
		sql.append(" and exists (select 1 ");
		sql.append(" from xg_pjpy_zcxmb b ");
		sql.append(" where xn = ? and xq=? and  nd=? ");
		sql.append(" and ddwh = 'no' ");
		sql.append(" and a.xmdm=b.sjdm) ");

		// -----------剔除子项数据取自来源表的项目 begin--------
		sql.append(" and not exists ( ");
		sql.append(" select 1 from( ");
		sql.append(" select a.*  from xg_pjpy_zcxmb a where exists( ");
		sql.append(" select * from xg_pjpy_zcxmb b  ");
		sql.append(" where  lyb is not null ");
		sql.append(" and a.xmdm=b.sjdm ");
		sql.append(" and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd ");
		sql.append(" and a.xn = ? and a.xq=? and  a.nd=? ) ");
		sql.append(" )b where a.xmdm=b.xmdm ) ");

		sql.append(" and  exists (select * ");
		sql.append(" from xg_pjpy_zcxmb b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and  exists (select *  from xg_pjpy_zcxmb c ");
		sql.append(" where c.sjdm = b.xmdm ");
		sql.append(" and c.xn = b.xn ");
		sql.append(" and c.xq = b.xq ");
		sql.append(" and c.nd = b.nd ");
		sql.append(" and b.xn = ? ");
		sql.append(" and b.xq = ? ");
		sql.append(" and b.nd = ? )) ");
		// -----------剔除子项数据取自来源表的项目 end--------

		// ---------获取存在子项无需单独维护并且数据非来自来源表的下项目 end-----------------

		sql.append(" union ");
		// -----------获取项目中存在子项目数据取自来源表且无需单独维护的项目 begin--------
		sql.append(" select a.*  from xg_pjpy_zcxmb a where exists( ");
		sql.append(" select 1 from xg_pjpy_zcxmb b ");
		sql.append(" where  lyb is not null ");
		sql
				.append(" and a.xmdm=b.sjdm and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and b.ddwh<>'yes') ");
		sql.append(" and xn = ? and xq=? and  nd=? ");
		// ----------获取项目中存在子项目数据取自来源表且无需单独维护的项目 end -----------

		sql.append(" union ");
		// -------------------有特殊的扩展字段的综测项目需单独维护 begin-------------------
		sql.append(" select a.* from xg_pjpy_zcxmb a, xg_pjpy_zckzzdb b ");
		sql.append(" where a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and a.xn = ? ");
		sql.append(" and a.xq = ? ");
		sql.append(" and a.nd = ? ");
		// -------------------有特殊的扩展字段的综测项目需单独维护 end-------------------

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, pjxn, pjxq,
						pjnd, pjxn, pjxq, pjnd, pjxn, pjxq, pjnd, pjxn, pjxq,
						pjnd, pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"xmmc", "xmjb", "lyb" });

		return list;
	}

	/**
	 * 获得综测项目扩 展字段信息（本评奖学年）
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getKzzdList(String xmdm, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.kzzd,a.xsmc,a.zdlx,a.checked ");
		sql.append(" ,a.source_table,a.select_dm,a.select_mc ");
		sql.append(" from xg_pjpy_zckzzdb a ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");
		sql.append(" order by to_number(xssx)");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, pjxn, pjxq, pjnd }, new String[] { "kzzd",
						"xsmc", "zdlx", "checked", "source_table", "select_dm",
						"select_mc" });

		return list;
	}

	/**
	 * 获得综测项目扩展字段信息
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getKzzdList(User user) {

		DAO dao = DAO.getInstance();
		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.kzzd,a.xsmc,a.zdlx,a.checked ");
		sql.append(" ,a.source_table,a.select_dm,a.select_mc ");
		sql.append(" from xg_pjpy_zckzzdb a ");
		sql.append(" where 1=1 ");
		sql.append(" and sfxs='1' ");

		sql.append(" and xn='" + zcxn + "' ");
		sql.append(" and xq='" + zcxq + "' ");
		sql.append(" and nd='" + zcnd + "' ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "kzzd", "xsmc", "zdlx",
						"checked", "source_table", "select_dm", "select_mc" });

		return list;
	}

	/**
	 * 获得综测子项
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getZczxList(String xmdm, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_pjpy_zcxmb  ");
		sql.append(" where xn=? and xq=? and nd=? ");
		sql.append(" and (sjdm =? and ddwh='no' ");
		sql.append("  or xmdm=? and ddwh='yes') ");
		sql.append(" order by to_number(replace(xmdm,'zd',''))");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, xmdm, xmdm }, new String[] {
						"xmdm", "xmmc", "jjf", "lyb" });

		return list;
	}

	/**
	 * 获取单独维护项目信息
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getDdwhList(String xmdm, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_pjpy_zcxmb  ");
		sql.append(" where xn=? and xq=? and nd=? ");
		sql.append("  and (sjdm =? and ddwh='no' ");
		sql.append("  or xmdm=? and ddwh='yes') ");
		sql.append(" order by to_number(replace(xmdm,'zd',''))");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, xmdm, xmdm }, new String[] {
						"xmdm", "xmmc", "jjf" });

		return list;
	}

	/**
	 * 获取单独维护项目信息
	 * 
	 * @author qlj
	 */
	public List<HashMap<String, String>> getBczdList(String xmdm, User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from xg_pjpy_zcxmb  ");
		sql.append(" where xn=? and xq=? and nd=? ");
		sql.append("  and (sjdm =? and ddwh='no' ");
		sql.append("  or xmdm=? and ddwh='yes'  )and lyb is null ");
		sql.append(" order by to_number(replace(xmdm,'zd',''))");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, pjxq, pjnd, xmdm, xmdm }, new String[] {
						"xmdm", "xmmc", "jjf" });

		return list;
	}

	/**
	 * 控制不同项目功能按钮
	 * 
	 * @author qlj
	 */
	public HashMap<String, String> getButMap(PjpyGeneralForm model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String xmdm = model.getXmdm();

		String pjxn = jbszModel.getPjxn();
		String pjxq = jbszModel.getPjxq();
		String pjnd = jbszModel.getPjnd();

		sql
				.append(" select a.xmdm,case when b.xmdm is null then 'no' else 'yes' end lrf , ");
		sql
				.append(" case when c.xmdm is null then 'no' else 'yes' end  lyf from xg_pjpy_zcxmb a ");

		sql.append(" left join ( ");
		sql.append(" select * from xg_pjpy_zcxmb a where (exists( ");
		sql.append(" select 1 from xg_pjpy_zcxmb b where ddwh='no'   ");
		sql
				.append(" and a.xn=b.xn and a.xq=b.xq and a.xmdm=b.sjdm and lyb is null) ");
		sql.append("  or (a.ddwh = 'yes' and lyb is null) ) and xmdm=? ");

		sql.append(" union ");
		sql.append(" select a.* from xg_pjpy_zcxmb a, xg_pjpy_zckzzdb b ");
		sql.append(" where a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd ");
		sql.append(" and a.xmdm = b.xmdm ");

		sql.append(" )b on a.xn=b.xn and a.xq=b.xq and a.xmdm =b.xmdm ");

		sql.append(" left join ( ");
		sql.append(" select * from xg_pjpy_zcxmb a where (exists( ");
		sql.append(" select * from xg_pjpy_zcxmb b where lyb is not null   ");
		sql
				.append(" and a.xn=b.xn and a.xq=b.xq and a.xmdm=b.sjdm and b.ddwh='no') ");
		sql.append(" or a.lyb is not null)and xmdm=? ");
		sql.append(" )c on a.xn=c.xn and a.xq=c.xq and a.xmdm =c.xmdm ");
		sql.append(" where a.xmdm=? ");

		sql.append(" and a.xn=? ");
		sql.append(" and a.xq=? ");
		sql.append(" and a.nd=? ");

		HashMap<String, String> map = dao.getMap(sql.toString(), new String[] {
				xmdm, xmdm, xmdm, pjxn, pjxq, pjnd }, new String[] { "xmdm",
				"lrf", "lyf" });

		return map;
	}

	/**
	 * 查询结果集(综合测评_综测信息)
	 * 
	 * @author 伟大的骆
	 */
	public ArrayList<String[]> getZhcpZcxxList(PjpyGeneralForm myForm,
			PjpyZhcpModel model, User user,
			List<HashMap<String, String>> kzzdList,
			List<HashMap<String, String>> zczxList)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		//
		List<String> colList = new ArrayList<String>();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();

		// 项目代码
		String xmdm = model.getXmdm();

		// ====================过滤条件===================================
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(myForm.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(myForm.getSearchModel());

		String query = " where 1 = 1 ";
		query += searchTj;
		query += searchTjByUser;
		// ====================过滤条件 end================================

		StringBuilder sql = new StringBuilder();
		sql.append("select rownum r,a.* from (");
		sql.append("select a.* from (");
		sql.append("select a.xh,b.xm,b.nj,b.bjmc,b.cpzdm,b.cpzmc, ");
		sql.append("a.");
		sql.append(xmdm);
		sql.append(" fs,");
		sql.append("a.xn,a.xq,b.xydm,b.zydm,b.bjdm ");

		// -------------------子项信息 begin-------------------------------
		if (zczxList != null && zczxList.size() > 0) {
			for (int i = 0; i < zczxList.size(); i++) {
				HashMap<String, String> map = zczxList.get(i);
				String zxmdm = map.get("xmdm");// 项目字段
				String lyb = map.get("lyb");// 项目来源表
				lyb = Base.isNull(lyb) ? "no" : lyb;

				sql.append(",");
				sql.append(zxmdm + "||'luojw'||'" + lyb + "' " + zxmdm);
				sql.append(" ");

				colList.add(zxmdm);
			}
		}
		// -------------------子项信息 end-------------------------------

		// ------------------取扩展字段 begin-----------------------
		if (kzzdList != null && kzzdList.size() > 0) {
			for (int i = 0; i < kzzdList.size(); i++) {
				HashMap<String, String> map = kzzdList.get(i);
				String kzzd = map.get("kzzd");// 扩展字段
				sql.append(",");
				sql.append(kzzd);
				sql.append(" ");

				colList.add(kzzd);
			}
		}
		// ------------------取扩展字段 end -----------------------

		sql.append(" from xg_pjpy_zhcpb a,");
		sql.append(" xg_view_pjpy_pjryk ");
		sql.append(" b ");
		sql.append(" where a.xh=b.xh ");

		sql.append(" and a.xn='" + pjxn + "'");
		sql.append(" and a.xq='" + pjxq + "'");

		sql.append(" ) a ");
		sql.append(query);
		sql.append(" order by a.nj desc,a.xydm,a.zydm,a.bjdm,a.xh ");
		sql.append(" ) a ");
		sql.append(" where 1=1 ");
		String[] colArr = new String[] { "xh", "xm", "nj", "bjmc", "fs" };

		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageQuery(myForm.getPages(), sql.toString(), inputV, dao
						.unionArray(colArr, colList.toArray(new String[] {})));

		return list;
	}

	/**
	 * 获得字段设置列表
	 * 
	 * @author luojw
	 */
	public List<HashMap<String, String>> getZdszList(String zd) {

		StringBuilder sql = new StringBuilder();
		sql.append("select kzzd,xsmc,zdlx,checked,");
		sql.append("source_table,select_dm,select_mc,xssx ");
		sql.append("from xg_pjpy_zckzzdb ");
		sql.append(Base.isNull(zd) ? "" : "where kzzd='" + zd + "' ");
		sql.append("order by to_number(xssx)");

		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "kzzd", "xsmc", "zdlx",
						"checked", "source_table", "select_dm", "select_mc", });
		return list;
	}

	/**
	 * 按学期保存综测基本信息
	 * 
	 * @param kzzdInfo
	 * @param zczxInfo
	 * @param xh
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean saveZhcpByXq(String[] kzzdInfo, String[] zczxInfo,
			String[] xh, PjpyGeneralForm model, User user) {

		String pjxn = model.getPjxn();
		String pjxq = model.getPjxq();

		List<HashMap<String, String>> kzzdList = getKzzdList(model.getXmdm(),
				user);

		List<HashMap<String, String>> zczxList = getBczdList(model.getXmdm(),
				user);

		StringBuilder insertSql = new StringBuilder();
		StringBuilder zd = new StringBuilder();

		List<String[]> inputV = new ArrayList<String[]>();

		int zdLen = 3;

		zd.append(" set ");
		for (int i = 0; i < kzzdList.size(); i++) {
			HashMap<String, String> kzzdMap = kzzdList.get(i);
			if (i != 0) {
				zd.append(",");
			}
			zd.append(kzzdMap.get("kzzd") + "=? ");

		}

		for (int i = 0; i < zczxList.size(); i++) {
			HashMap<String, String> zczxMap = zczxList.get(i);

			if (i != 0 || kzzdList.size() > 0) {
				zd.append(",");
			}
			zd.append(zczxMap.get("xmdm") + "=? ");

		}

		zdLen += kzzdList.size() + zczxList.size();

		insertSql.append(" update  xg_pjpy_zhcpb ");
		insertSql.append(zd);
		insertSql.append(" where xh =? and xn=? and xq=? ");
		for (int i = 0; i < xh.length; i++) {
			String[] colArr = new String[zdLen];

			for (int j = 0; j < kzzdInfo.length; j++) {

				String zdyz = kzzdInfo[j].split("!!@@!!")[i];
				colArr[j] = zdyz.trim();
			}

			for (int j = 0; j < zczxInfo.length; j++) {
				String zdyz = zczxInfo[j].split("!!@@!!")[i];
				colArr[kzzdInfo.length + j] = zdyz.trim();
			}

			colArr[zdLen - 3] = xh[i].trim();
			colArr[zdLen - 2] = pjxn;
			colArr[zdLen - 1] = pjxq;

			inputV.add(colArr);
		}
		DAO dao = DAO.getInstance();

		try {
			dao.runBatch(insertSql.toString(), inputV);

		} catch (Exception ex) {

			return false;
		}

		return true;
	}

	/**
	 * 按学年保存综测基本信息
	 * 
	 * @param kzzdInfo
	 * @param zczxInfo
	 * @param xh
	 * @param model
	 * @param user
	 * @return
	 */
	public boolean saveZhcpByXn(String[] kzzdInfo, String[] zczxInfo,
			String[] xh, PjpyGeneralForm model, User user) {

		String pjxn = model.getPjxn();

		List<HashMap<String, String>> kzzdList = getKzzdList(model.getXmdm(),
				user);

		List<HashMap<String, String>> zczxList = getBczdList(model.getXmdm(),
				user);

		StringBuilder insertSql = new StringBuilder();
		StringBuilder zd = new StringBuilder();

		List<String[]> inputV = new ArrayList<String[]>();

		int zdLen = 2;

		zd.append(" set ");
		for (int i = 0; i < kzzdList.size(); i++) {
			HashMap<String, String> kzzdMap = kzzdList.get(i);
			if (i != 0) {

				zd.append(",");
			}
			zd.append(kzzdMap.get("kzzd") + "=? ");

		}

		for (int i = 0; i < zczxList.size(); i++) {
			HashMap<String, String> zczxMap = zczxList.get(i);

			if (i != 0 || kzzdList.size() > 0) {
				zd.append(",");
			}
			zd.append(zczxMap.get("xmdm") + "=? ");

		}

		zdLen += kzzdList.size() + zczxList.size();

		insertSql.append(" update  xg_pjpy_zhcpb ");
		insertSql.append(zd);
		insertSql.append(" where xh =? and xn=? and xq='no'");
		for (int i = 0; i < xh.length; i++) {
			String[] colArr = new String[zdLen];

			for (int j = 0; j < kzzdInfo.length; j++) {
				String zdyz = kzzdInfo[j].split("!!@@!!")[i];
				colArr[j] = zdyz.trim();
			}

			for (int j = 0; j < zczxInfo.length; j++) {
				String zdyz = "";
				if (!Base.isNull(zczxInfo[j])) {
					zdyz = zczxInfo[j].split("!!@@!!")[i];
					colArr[kzzdInfo.length + j] = zdyz.trim();
				}
			}

			colArr[zdLen - 2] = xh[i].trim();
			colArr[zdLen - 1] = pjxn;

			inputV.add(colArr);
		}
		DAO dao = DAO.getInstance();

		try {
			System.out.println(insertSql);
			dao.runBatch(insertSql.toString(), inputV);

		} catch (Exception ex) {

			return false;
		}

		return true;
	}

	/**
	 * 获取来源数据基本设置
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLybInfo() {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pjxn = jbszModel.getPjxn();

		String pjxq = jbszModel.getPjxq();

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select xmdm,lyb,zd,glxn,glxq,sjdm from xg_pjpy_zcxmb where lyb is not null ");

		sql.append(" and xn=? ");
		sql.append(" and xq=? ");

		return dao.getList(sql.toString(), new String[] { pjxn, pjxq },
				new String[] { "xmdm", "lyb", "zd", "glxn", "glxq", "sjdm" });

	}

	// ==================综测分维护 end==============================

	// ==================综测分结果 begin==============================

	/**
	 * 获取综测总分表头字段
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZhcpResultTop(User user) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc,sjdm from xg_pjpy_zcxmb  where 1=1 ");

		sql.append(" and xn='" + zcxn + "' ");
		sql.append(" and xq='" + zcxq + "' ");
		sql.append(" and nd='" + zcnd + "' ");

		sql.append(" order by xmmc,sjdm ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmdm", "xmmc" });
	}

	/**
	 * 获取综测总分表头字段
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZcxmByHistory(User user) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc,sjdm from xg_pjpy_zcxmb  where 1=1 ");

		sql.append(" and xn='" + zcxn + "' ");
		sql.append(" and xq='" + zcxq + "' ");
		sql.append(" and nd='" + zcnd + "' ");

		sql.append(" and (sjdm ='1' or sjdm='2') order by xmmc,sjdm ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmdm", "xmmc" });
	}

	/**
	 * 获取综测显示信息
	 * 
	 * @param model
	 * @param user
	 * @param colList
	 * @return List<String[]>
	 * @throws Exception
	 */
	public List<String[]> getZhcpResultList(PjpyGeneralForm model, User user,
			String[] colList) throws Exception {

		DAO dao = DAO.getInstance();

		String zczq_tj = model.getSearchModel().getSearch_tj_zczq()[0];

		model.getSearchModel().setSearch_tj_zczq(null);

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String pmfs = jbszModel.getZcpm();

		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		searchTj += searchTjByUser;
		String[] inputSearch = SearchService.getTjInput(model.getSearchModel());

		List<HashMap<String, String>> top = getZhcpResultTop(user);

		StringBuilder sql = new StringBuilder();

		// ==================显示字段===================
		StringBuilder xszd = new StringBuilder();
		for (int i = 0; i < top.size(); i++) {
			HashMap<String, String> topMap = top.get(i);

			xszd.append(",");

			xszd.append(topMap.get("xmdm"));
		}
		// ==================显示字段end===================

		sql
				.append(" select rownum r,a.*,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xh pkValue from( ");
		sql.append(" select xn,xq,nd,xh,xm,nj,xymc,xydm,zymc,zydm,bjdm,bjmc ");
		sql.append(" ,zd1,zd2,zd3,zd4,zd5,zd6,zd7,zd8,zd9,zd10,zd11,zd12,zd13");
		sql
				.append(" ,zd14,zd15,zd16,zd17,zd18,zd19,zd20,zd21,zd22,zd23,zd24,zd25,zd26,zd27,zd28,zd29,zd30");
		sql
				.append(" ,zcfnjxypm,zcfnjzypm,zcfbjpm,zyfnjxypm,zyfnjzypm,zyfbjpm,cpzpm,zyfcpzpm,cpzdm,cpzmc,dyfbjpm,dyfnjzypm from( select  a.*");
		sql.append(" ,b.nj,b.xymc,b.zymc,b.bjmc,b.cpzdm,b.cpzmc ");
		sql.append(" ,xydm,zydm,bjdm,xm ");
		sql.append("  from(select * from xg_pjpy_zhcpb where 1=1  ");

		String[] inPutList = null;

		String[] zczqArr = zczq_tj.split("luojw");
		String pjxn = zczqArr[0];
		String pjxq = zczqArr[1];
		String pjnd = zczqArr[2];

		sql.append("  and xn=?  and xq=? and nd=? ");
		inPutList = new String[] { pjxn, pjxq, pjnd };

		sql.append(" ) a left join ");
		sql.append(" xg_view_pjpy_pjryk ");
		sql.append(" b on a.xh=b.xh  ");

		if ("0".equalsIgnoreCase(pmfs)) {

			sql.append(" order by cpzdm,to_number(zd1) desc ");
		} else if ("1".equalsIgnoreCase(pmfs)) {

			sql.append(" order by nj,xydm,to_number(zd1) desc ");
		} else if ("2".equalsIgnoreCase(pmfs)) {

			sql.append(" order by nj,zydm,to_number(zd1) desc ");
		} else if ("3".equalsIgnoreCase(pmfs)) {

			sql.append(" order by bjdm,to_number(zd1) desc ");
		}
		
		sql.append(" )a ");
		
		//--------------2013.01.08 begin---------------------------
		//--------------edit by 伟大的骆---------------------------
		
		// 排序
		String arrange = model.getSearchModel().getArrange();
		String fashion = model.getSearchModel().getFashion();
		
		if(!Base.isNull(arrange)){
			sql.append(" order by ");
			sql.append(arrange);
			sql.append("  ");
			sql.append(fashion);
		}
		
		//--------------2013.01.08 end---------------------------
		
		sql.append(" order by nj desc,xydm,zydm,bjdm,xh)a  where 1=1 ");
		sql.append(searchTj);
		
		return CommonQueryDAO.commonQuery(sql.toString(), "", dao.unionArray(
				inPutList, inputSearch), colList, model);
	}

	/**
	 * 获取综测总分表头字段
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public List<HashMap<String, String>> getZczfTop(PjpyGeneralForm model,
			User user) {
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();
		String zcnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm,xmmc,sjdm from xg_pjpy_zcxmb where 1=1 ");

		sql.append(" and xn='" + zcxn + "' ");
		sql.append(" and xq='" + zcxq + "' ");
		sql.append(" and nd='" + zcnd + "' ");

		sql.append(" order by xmmc,sjdm ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmdm", "xmmc" });
	}

	// ==================综测分结果 end==============================

	/**
	 * 获取指定用户选中的列
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String, String>>
	 */
	public HashMap<String, String> getCheckKind(PjpyGeneralForm model, User user) {

		SearchModel searchModel = model.getSearchModel();
		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		String yhlx = "tea";

		if ("stu".equalsIgnoreCase(user.getUserType())) {
			yhlx = "stu";
		}

		sql.append(" select * from xg_pjpy_zczf_xszdszb where 1=1 ");
		sql.append(" and yhm=? ");
		sql.append(" and yhlx=? ");
		sql.append(" and path=?");
		sql.append(" and rownum=1 ");
		return dao.getMap(sql.toString(), new String[] { user.getUserName(),
				yhlx, searchModel.getPath() }, new String[] { "xxszd" });

	}

	/**
	 * 先删后增列选
	 * 
	 * @param yhm
	 * @param yhlx
	 * @param xszd
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public int[] saveKindChoose(String yhm, String yhlx, String xszd,
			String path) throws Exception {
		DAO dao = DAO.getInstance();

		String[] sql = new String[2];
		sql[0] = " delete from xg_pjpy_zczf_xszdszb where yhm='" + yhm
				+ "' and yhlx='" + yhlx + "' and path='" + path + "' ";
		sql[1] = " insert into xg_pjpy_zczf_xszdszb(yhm,yhlx,path,xxszd) values('"
				+ yhm + "','" + yhlx + "','" + path + "','" + xszd + "') ";

		// 只需判断是否成功插入
		int[] result = dao.runBatch(sql);

		return new int[] { result[1] };
	}

	/**
	 * 获取综测项目详细配置信息
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getZcxmInfo(PjpyGeneralForm model,
			User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();
		// ===========综测周期=============
		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();

		StringBuilder zqQuery = new StringBuilder();

		zqQuery.append(" and a.xn='" + zcxn + "'");
		zqQuery.append(" and a.xq='" + zcxq + "'");

		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();
		sql
				.append(" select a.xmdm,sjdm,a.bldm,a.bl,lyb,zd,condition,xmjb,jjf ");
		sql
				.append(" from( select a.xn,a.xq,a.nd,a.xmdm,sjdm,bldm,bl,xmjb,jjf ");
		sql.append(" from xg_pjpy_zcxmb a  left join xg_pjpy_zcblb b  ");
		sql
				.append(" on a.xmdm = b.xmdm and a.xn = b.xn and a.xq = b.xq  and a.nd = b.nd ");
		sql.append(" where 1=1 ");
		sql.append(zqQuery);
		sql
				.append(" )a left join xg_pjpy_zcbldmb b on a.bldm=b.bldm and a.xn=b.xn");
		sql.append(" and a.xq=b.xq and a.nd=b.nd ");

		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"xmdm", "sjdm", "bldm", "bl", "lyb", "zd", "condition", "xmjb",
				"jjf" });

	}

	/**
	 * 获取项目级别列表
	 * 
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXmjbList() {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		// ===========综测周期=============
		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();
		String zcnd = jbszModel.getPjnd();

		// ===========综测周期end=============
		sql.append(" select xmjb from xg_pjpy_zcxmb where 1=1 ");
		sql.append(" and xn = '" + zcxn + "' and xq =  '" + zcxq
				+ "'  and nd =  '" + zcnd + "'  ");

		sql.append("  group by xmjb  order by xmjb desc ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] { "xmjb" });
	}

	/**
	 * 根据上级代码获取来源数据基本设置
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getLybInfo(String sjdm) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select xmdm,lyb,zd,glxn,glxq,sjdm from xg_pjpy_zcxmb a where lyb is not null ");
		sql
				.append(" and (xmdm=? or exists(select 1 from xg_pjpy_zcxmb b where a.sjdm=b.xmdm and a.sjdm=? )) ");
		sql.append(" and xn='" + jbszModel.getPjxn() + "' ");
		sql.append(" and xq='" + jbszModel.getPjxq() + "' ");

		return dao.getList(sql.toString(), new String[] { sjdm, sjdm },
				new String[] { "xmdm", "lyb", "zd", "glxn", "glxq", "sjdm" });

	}

	/**
	 * 获取来源数据基本设置
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean updateLybInfo(PjpyGeneralForm model, User user)
			throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String sjdm = jbszModel.getXmdm();

		System.out.println("综测同步sjdm:" + sjdm);
		List<HashMap<String, String>> lybInfo = getLybInfo(sjdm);

		boolean flag = true;
		for (int i = 0; i < lybInfo.size(); i++) {

			HashMap<String, String> lybMap = lybInfo.get(i);

			String glxn = lybMap.get("glxn");
			String glxq = lybMap.get("glxq");

			String xn = "no";
			String xq = "no";

			if ("pjxn".equalsIgnoreCase(glxn)) {

				xn = jbszModel.getPjxn();
			} else if ("xn".equalsIgnoreCase(glxn)) {
				xn = Base.currXn;
			}

			if ("pjxq".equalsIgnoreCase(glxq)) {

				xq = jbszModel.getPjxq();
			} else if ("xn".equalsIgnoreCase(glxq)) {

				xq = Base.currXq;
			}

			if (flag) {
				// 用户名
				String userName = user.getUserName();
				flag = updateLybInfo(lybMap, xn, xq, userName);

			} else {
				break;
			}
		}

		return flag;
	}

	public boolean updateLybInfo(HashMap<String, String> lybMap, String xn,
			String xq, String userName) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		String zczq = jbszModel.getZczq();

		String[] sql = new String[3];

		String xmdm = lybMap.get("xmdm");
		String lyb = lybMap.get("lyb");
		String zd = lybMap.get("zd");
		String glxn = lybMap.get("glxn");
		String glxq = lybMap.get("glxq");

		sql[0] = " delete from xg_pjpy_zclsb where yhm = '" + userName + "'";

		String insertZd = "";

		if (!Base.isNull(glxn) && !Base.isNull(glxq)) {
			insertZd = ",xn,xq,'no'nd";
		} else if (!Base.isNull(glxn)) {
			insertZd = ",xn,'no' xq,'no' nd ";
		}

		sql[1] = " insert into xg_pjpy_zclsb(xh,xn,xq,nd,zd1,yhm) ";
		sql[1] += " select xh" + insertZd + "," + zd + ",'"+userName+"' from ";
		sql[1] += lyb;

		sql[2] = " update xg_pjpy_zhcpb a set ";
		sql[2] += xmdm + "=(select  ";
		sql[2] += " zd1  from  ";
		sql[2] += "xg_pjpy_zclsb b where a.xh=b.xh ";

		sql[1] += " where xn='" + xn + "' and xq='" + xq + "' ";
		sql[2] += " and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd ";
		sql[2] += " and b.xn='" + xn + "' and b.xq='" + xq + "' ";
		sql[2] += " and b.yhm='" + userName + "'";
		sql[2] += " ) ";
		sql[2] += " where exists (select 1 from xg_pjpy_zclsb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd and b.yhm='"+userName+"')";

		System.out.println("0:" + sql[0]);
		System.out.println("1:" + sql[1]);
		System.out.println("2:" + sql[2]);

		return saveArrDate(sql);
	}

	/**
	 * 获取当前周期综测项目
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getZhcpXmByZq() {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		sql.append(" select xmdm, xmmc ");
		sql.append("  from xg_pjpy_zcxmb ");
		sql.append("  where xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and nd = ? ");
		sql.append("  order by xmjb, xmdm ");

		return dao.getList(sql.toString(), new String[] { pjxn, pjxq, pjnd },
				new String[] { "xmdm", "xmmc" });
	}

	/**
	 * 获取当前周期综测项目
	 * 
	 * @return
	 */
	public List<Object> getZcByZqList(String xh) {

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		List<HashMap<String, String>> zcxmByZqList = getZhcpXmByZq();

		StringBuilder zd = new StringBuilder();

		List<Object> bczcInfo = new ArrayList<Object>();

		String zd1mc = "";

		int n = 0;

		String[] top = new String[zcxmByZqList.size() + 1];
		String[] colList = new String[zcxmByZqList.size() + 1];

		for (int i = 0; i < zcxmByZqList.size(); i++) {

			HashMap<String, String> zcxmByZqMap = zcxmByZqList.get(i);

			if (!"zd1".equalsIgnoreCase(zcxmByZqMap.get("xmdm"))) {

				if (n != 0) {

					zd.append(",");
				}

				zd.append(zcxmByZqMap.get("xmdm"));

				top[n] = zcxmByZqMap.get("xmmc");
				colList[n] = zcxmByZqMap.get("xmdm");
				n++;

			} else {

				zd1mc = zcxmByZqMap.get("xmmc");
			}

		}
		top[zcxmByZqList.size() - 1] = zd1mc;
		colList[zcxmByZqList.size() - 1] = "zd1";

		top[zcxmByZqList.size()] = "综测分班级排名";
		colList[zcxmByZqList.size()] = "zcfbjpm";

		zd.append(",zd1");

		zd.append(",zcfbjpm");

		bczcInfo.add(top);

		sql.append("( select  ");
		sql.append(zd);
		sql.append(" from xg_pjpy_zhcpb where  ");
		sql.append("   xn = ? ");
		sql.append("  and xq = ? ");
		sql.append("  and nd = ? ");
		sql.append("  and xh = ?) ");

		bczcInfo.addAll(CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { pjxn, pjxq, pjnd, xh }, colList, null));

		return bczcInfo;
	}

	// ------------------综测分计算 begin----------------
	/**
	 * 获取综测比例代码表中比列类型
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getBldmList(PjpyGeneralForm model,
			User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();
		// ===========综测周期=============
		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();
		String zcnd = jbszModel.getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();
		sql.append(" select bldm from xg_pjpy_zcbldmb where 1=1 ");

		sql.append(" and xn='" + zcxn + "' ");
		sql.append(" and xq='" + zcxq + "' ");
		sql.append(" and nd='" + zcnd + "' ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] { "bldm" });
	}

	/**
	 * 获取有子项目的项目列表
	 * 
	 * @param model
	 * @param user
	 * @return List<HashMap<String,String>>
	 */
	public List<HashMap<String, String>> getXjsxmList(PjpyGeneralForm model,
			User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		DAO dao = DAO.getInstance();
		// ===========综测周期=============
		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();

		// ===========综测周期end=============
		StringBuilder zqQuery = new StringBuilder();

		zqQuery.append(" and xn='" + zcxn + "'");
		zqQuery.append(" and xq='" + zcxq + "'");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xmdm from xg_pjpy_zcxmb a ");
		sql.append(" where not exists (select xmdm ");
		sql.append(" from (select * from xg_pjpy_zcxmb a where not exists ");
		sql.append(" (select 1 from xg_pjpy_zcxmb b where a.xmdm = b.sjdm) ");

		sql.append(zqQuery);
		sql.append("  ) b ");
		sql.append(" where a.xmdm = b.xmdm and a.xn=b.xn) ");
		// 倒顺排列用于计算
		sql.append(zqQuery);

		sql.append(" order by xmjb desc ");

		return dao.getList(sql.toString(), new String[] {},
				new String[] { "xmdm" });
	}

	/**
	 * 获取需要计算的综测项目sql
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public List<HashMap<String, String>> getZcjsSql(PjpyGeneralForm model,
			User user) {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.jbszModel;
		// 比例代码列表
		List<HashMap<String, String>> bldmList = getBldmList(model, user);
		// 获取需要计算的项目
		List<HashMap<String, String>> xjsxmList = getXjsxmList(model, user);
		// 获取综测项目列表
		List<HashMap<String, String>> zcxmList = getZcxmInfo(model, user);

		// ===========综测周期=============
		String zcxn = jbszModel.getPjxn();
		String zcxq = jbszModel.getPjxq();
		String zcnd = jbszModel.getPjnd();
		String zczq = jbszModel.getZczq();
		// ===========综测周期end=============
		String bmQuery = getBmQuery(model, user);

		List<HashMap<String, String>> zcjsList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < bldmList.size(); i++) {
			HashMap<String, String> bldmMap = bldmList.get(i);
			for (int j = 0; j < xjsxmList.size(); j++) {
				HashMap<String, String> xjsxmMap = xjsxmList.get(j);
				HashMap<String, String> zcjsMap = new HashMap<String, String>();
				StringBuilder querySql = new StringBuilder();
				StringBuilder sumZd = new StringBuilder();
				StringBuilder updateSql = new StringBuilder();

				updateSql.append("  update xg_pjpy_zhcpb a set ");
				boolean first = true;
				String lyb = null;
				for (int z = 0; z < zcxmList.size(); z++) {
					// 综测项目详细配置信息
					HashMap<String, String> zcxmMap = zcxmList.get(z);

					if (bldmMap.get("bldm").equalsIgnoreCase(
							zcxmMap.get("bldm"))
							&& xjsxmMap.get("xmdm").equalsIgnoreCase(
									zcxmMap.get("sjdm"))) {
						if (!first) {
							sumZd.append("+");
						} else {
							updateSql.append(zcxmMap.get("sjdm"));
							updateSql.append(" = ");
							lyb = zcxmMap.get("lyb");
							querySql.append(" and exists(select 1 from "
									+ zcxmMap.get("lyb") + "  b where ");
							querySql.append(zcxmMap.get("condition"));
							querySql.append("  and a.xh=b.xh)  ");

							zcjsMap.put("xmjb", zcxmMap.get("xmjb"));

							first = false;
						}
						sumZd.append(" to_number(nvl(");
						sumZd.append(zcxmMap.get("jjf") + zcxmMap.get("xmdm"));
						sumZd.append(" ,0))* to_number(nvl(");
						sumZd.append(zcxmMap.get("bl"));
						sumZd.append(" ,0))/100 ");
					}
				}
				updateSql.append(" ( ");
				updateSql.append(sumZd);
				updateSql.append(" ) where 1=1 ");
				if (!Base.isNull(lyb)) {
					updateSql.append(querySql);
				}

				updateSql.append(" and xn='" + zcxn + "' and xq='" + zcxq
						+ "' ");

				updateSql.append(bmQuery);
				zcjsMap.put("sql", updateSql.toString());
				zcjsList.add(zcjsMap);
			}

		}

		return zcjsList;
	}

	/**
	 * 获取综测总分计算时所选的部门条件
	 * 
	 * @param model
	 * @param user
	 * @return
	 */
	public String getBmQuery(PjpyGeneralForm model, User user) {

		String searchTjByUser = SearchService.getSearchTjByUser(user, "b",
				"xydm", "bjdm");

		String nj = model.getNj();

		String xydm = model.getXydm();

		String zydm = model.getZydm();

		String bjdm = model.getBjdm();

		String userType = user.getUserType();

		StringBuilder sql = new StringBuilder();

		if (!Base.isNull(nj) || !Base.isNull(xydm) || !Base.isNull(zydm)
				|| !Base.isNull(bjdm) || !"xx".equalsIgnoreCase(userType)
				|| !"admin".equalsIgnoreCase(userType)) {

			sql.append(" and exists(select 1 from    ");

			sql.append(" xg_view_pjpy_pjryk ");
			sql.append(" b where 1=1  ");

			if (!Base.isNull(nj)) {
				sql.append(" and nj='" + nj + "'");
			}

			if (!Base.isNull(xydm)) {
				sql.append(" and xydm='" + xydm + "'");
			}

			if (!Base.isNull(zydm)) {
				sql.append(" and zydm='" + zydm + "'");
			}

			if (!Base.isNull(bjdm)) {
				sql.append(" and bjdm='" + bjdm + "'");
			}
			sql.append(" and a.xh=b.xh ");
			sql.append(searchTjByUser);
			sql.append(" ) ");
		}

		return sql.toString();
	}

	// --------------------------- 综测总分排名 begin -------------------------------

	// ----------------------------综测总分排名计算 begin
	// ----------------------------------

	/**
	 * 计算班级排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean bjpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" bjdm order by to_number(trim(zd1)) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");

		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		System.out.println(sql);
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新班级排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjpm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" update xg_pjpy_zhcpb  a set zcfbjpm=(select pm from xg_pjpy_zhcplsb b");
		sql.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级学院排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njxypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,xydm order by to_number(trim(zd1)) desc nulls last)) xypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级学院排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateXypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zcfnjxypm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级专业排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njzypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" nj,zydm order by to_number(trim(zd1)) desc nulls last)) zypm ");
		sql.append(" from (select a.xh, zd1, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级专业排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateZypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zcfnjzypm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 参评组排名计算
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean cpzpmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.cpzpm from( ");

		// -------------------参评组排名计算 begin----------------------
		sql.append(" select a.xh,a.cpzpm,cpz ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql
				.append(" cpz order by to_number(trim(zd1)) desc nulls last)) cpzpm ");
		sql.append(" from (select a.xh, zd1, cpz, xn, xq, nd ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		// -------------------参评组排名计算 end------------------------

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新参评组排名进正式表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateCpzpm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set cpzpm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	// ----------------------------综测总分排名计算
	// end----------------------------------

	// ===========================智育排名计算 begin
	// ==================================
	// --------------------------- 综测总分排名 end -------------------------------

	/**
	 * 计算年级学院智育排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njxyZypmjs(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.xypm from( ");
		sql.append(" select a.xh, a.xypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,xydm order by to_number(zd3) desc nulls last)) xypm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级学院智育排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateNjXyZypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zyfnjxypm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算班级智育排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean bjZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(zd3) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级专业智育排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjZypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zyfbjpm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算班级排名,并且放入临时表【德育分】
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean bjDypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.bjpm from( ");
		sql.append(" select a.xh, a.bjpm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" bjdm order by to_number(zd2) desc nulls last)) bjpm ");
		sql.append(" from (select a.xh, zd2, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级专业智育排名【德育分】
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateBjDypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set dyfbjpm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	/**
	 * 计算年级专业排名,并且放入临时表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean njzyZypmjs(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zypm from( ");
		sql.append(" select a.xh, a.zypm,b.xydm,b.zydm,b.bjdm ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" nj,zydm order by to_number(zd3) desc nulls last)) zypm ");
		sql.append(" from (select a.xh, zd3, bjdm, xn, xq, nd,nj,xydm,zydm ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,d.bmdm xydm,c.zydm,b.bjdm,nj ");
		sql
				.append(" from xg_pjpy_pjrykb a, bks_bjdm b, bks_zydm c, zxbz_xxbmdm d");
		sql.append(" where a.bjdm = b.bjdm");
		sql.append(" and b.zydm = c.zydm");
		sql.append(" and c.bmdm = d.bmdm)");
		sql.append(" b on a.xh=b.xh)a  where 1=1 ");

		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();
		return saveArrDate(sqlArr);
	}

	/**
	 * 更新年级专业智育排名
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateNjZyZypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" update xg_pjpy_zhcpb  a set zyfnjzypm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	// ------------------综测分计算 end----------------

	// --------------------其它模块调用 begin---------------------------

	/**
	 * 参评组智育分排名计算
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean cpzZypmjs(PjpyGeneralForm model, User user) throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql.append(" insert into xg_pjpy_zhcplsb(xn,xq,nd,xh,pm) ");
		sql.append(" select '" + zcxn + "','" + zcxq + "','" + zcnd
				+ "',a.xh,a.zyfcpzpm from( ");

		// -------------------参评组排名计算 begin----------------------
		sql.append(" select a.xh,a.zyfcpzpm,cpz ");
		sql.append(" from (select xh,(rank() over(partition by xn,xq,nd, ");
		sql.append(" cpz order by to_number(zd3) desc nulls last)) zyfcpzpm ");
		sql.append(" from (select a.xh, zd3, cpz, xn, xq, nd ");
		sql.append(" from xg_pjpy_zhcpb a  left join ");

		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh = b.xh ");
		sql.append("  where xn = '" + zcxn + "' and xq = '" + zcxq
				+ "' and nd = '" + zcnd + "'  ");
		sql.append(" ) b) a left join ");
		sql.append("(select a.xh,a.cpz ");
		sql.append(" from xg_pjpy_pjrykb a ) ");

		sql.append(" b on a.xh=b.xh)a  where 1=1 ");
		// -------------------参评组排名计算 end------------------------

		// 数据进入正式库后，清空临时表
		String[] sqlArr = new String[2];
		sqlArr[0] = " delete from xg_pjpy_zhcplsb ";
		sqlArr[1] = sql.toString();

		return saveArrDate(sqlArr);
	}

	/**
	 * 更新参评组排名进正式表
	 * 
	 * @param model
	 * @param use
	 * @return
	 * @throws Exception
	 */
	public boolean updateCpzZypm(PjpyGeneralForm model, User user)
			throws Exception {

		// ===========综测周期=============
		String zcxn = PjpyGeneralForm.getJbszModel().getPjxn();
		String zcxq = PjpyGeneralForm.getJbszModel().getPjxq();
		String zcnd = PjpyGeneralForm.getJbszModel().getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		// --------------------修改智育分参评组排名 begin-------------------------------
		sql
				.append(" update xg_pjpy_zhcpb  a set zyfcpzpm=(select pm from xg_pjpy_zhcplsb b");
		sql
				.append(" where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq and a.nd=b.nd) ");
		sql.append(" where xn='" + zcxn + "' and xq='" + zcxq + "' and nd='"
				+ zcnd + "' ");
		// --------------------修改智育分参评组排名 end---------------------------------

		String[] sqlArr = new String[2];
		sqlArr[0] = sql.toString();

		// 数据进入正式库后，清空临时表
		sqlArr[1] = " delete from xg_pjpy_zhcplsb ";

		return saveArrDate(sqlArr);
	}

	// ===========================智育排名计算 end ==================================

	/**
	 * 根据学号获取本次综测分信息 author qlj
	 */
	public HashMap<String, String> getZhcpInfo(String xh) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		// ===========综测周期=============
		String zcxn = jbszForm.getPjxn();
		String zcxq = jbszForm.getPjxq();
		String zcnd = jbszForm.getPjnd();
		// ===========综测周期end=============

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select * from xg_pjpy_zhcpb where xn=? and xq=? and nd=?  and xh=?  ");

		return dao.getMapNotOut(sql.toString(), new String[] { zcxn, zcxq,
				zcnd, xh });
	}

	/**
	 * 根据学号、学年、学期、年度获取综测分信息
	 * 
	 * @date 2013.01.08
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getZhcpInfo(String xh, String zcxn,
			String zcxq, String zcnd) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql
				.append(" select * from xg_pjpy_zhcpb where xn=? and xq=? and nd=?  and xh=?  ");

		return dao.getMapNotOut(sql.toString(), new String[] { zcxn, zcxq,
				zcnd, xh });
	}
	
	/**
	 * 动态获取综测排名字段 author qlj
	 */
	public List<HashMap<String, String>> getZcxxpmList() {

		DAO dao = DAO.getInstance();

		String[] zcpmen = getZcPmArr("en");
		String[] zcpmcn = getZcPmArr("cn");

		List<HashMap<String, String>> zcpm = dao.arrayToList(zcpmen, zcpmcn);

		String[] zypmen = getZyPmArr("en");
		String[] zypmcn = getZyPmArr("cn");

		List<HashMap<String, String>> zypm = dao.arrayToList(zypmen, zypmcn);

		zcpm.addAll(zypm);

		return zcpm;
	}

	/**
	 * 获取指定周期综测 项目信息列表
	 * 
	 * @param xn
	 * @param xq
	 * @param nd
	 *            author qlj
	 */
	public List<HashMap<String, String>> getZcxmList(String xn, String xq,
			String nd) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append(" select xmdm en,xmmc cn from xg_pjpy_zcxmb where xn=? and xq=? and nd=?  order by xmdm ");

		return dao.getListNotOut(sql.toString(), new String[] { xn, xq, nd });
	}

	/**
	 * 获取当前周期综测项目 author qlj
	 */
	public HashMap<String, String> getZcListByZq(String xh) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		// ------------综测周期 begin-------------------
		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();
		// -------------综测周期 end------------

		// ---------------------动态获取需显示字段 begin---------------------
		// 获取本周期综测项目列表
		List<HashMap<String, String>> zcxmByZq = getZhcpXmByZq();

		List<String> en = new ArrayList<String>();

		for (int i = 0; i < zcxmByZq.size(); i++) {

			HashMap<String, String> zcxmMap = zcxmByZq.get(i);
			// 综测项目
			en.add(zcxmMap.get("xmdm"));

		}
		// 综测项目
		String[] colList = en.toArray(new String[] {});
		// 综测排名
		String[] zcpmArr = getZcPmArr("en");
		// 智育排名
		String[] zypmArr = getZyPmArr("en");

		colList = dao.unionArray(colList, zcpmArr);
		colList = dao.unionArray(colList, zypmArr);
		// ---------------------动态获取需显示字段 end---------------------

		sql.append(" (select xn, case when xq = 'no' then '无'  ");
		sql
				.append(" else (select xqmc  from xqdzb b  where a.xq = b.xqdm) end xqmc, ");
		sql
				.append(" zd1,zcfbjpm,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xh pkValue ");
		sql.append(" from xg_pjpy_zhcpb a  where 1 = 1 and xh = ? ");
		sql
				.append(" and not exists (select 1  from xg_pjpy_zhcpb b where xn = ? ");
		sql.append(" and xq =? and nd = ? and a.xh = b.xh  and a.xn = b.xn ");
		sql.append(" and a.xq = b.xq  and a.nd = b.nd)  and exists (select 1 ");
		sql.append(" from xg_pjpy_zcxmb c where a.xn = c.xn  and a.xq = c.xq ");
		sql.append(" and a.nd = c.nd) order by xn,xq ) ");

		return dao.getMap(sql.toString(),
				new String[] { xh, pjxn, pjxq, pjnd }, colList);
	}

	/**
	 * 获取当前周期综测项目 authoer qlj
	 */
	public List<String[]> getZcList(String xh) {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszForm = PjpyGeneralForm.getJbszModel();

		StringBuilder sql = new StringBuilder();

		String pjxn = jbszForm.getPjxn();

		String pjxq = jbszForm.getPjxq();

		String pjnd = jbszForm.getPjnd();

		String zcpm = jbszForm.getZcpm();

		List<String> title = new ArrayList<String>();

		// ---------------动态获取显示字段 begin---------------------
		String[] colList = new String[] { "xn", "xqmc", "zd1" };// 基础字段

		List<String> pmzdArr = new ArrayList<String>();// 排名字段

		title.add("学年");// 表头
		title.add("学期");
		title.add("综测分");

		// 参评组排名
		if ("0".equalsIgnoreCase(zcpm)) {

			pmzdArr.add("cpzpm");
			title.add("参评组排名");
		}

		// 需要年级学院排名
		if ("1".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			pmzdArr.add("zcfnjxypm");
			title.add("年级" + Base.YXPZXY_KEY + "排名");
		}

		// 年级专业排名
		if ("2".equalsIgnoreCase(zcpm) || "4".equalsIgnoreCase(zcpm)
				|| "6".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			pmzdArr.add("zcfnjzypm");
			title.add("年级专业排名");
		}

		// 班级排名
		if ("3".equalsIgnoreCase(zcpm) || "5".equalsIgnoreCase(zcpm)
				|| "4".equalsIgnoreCase(zcpm) || "7".equalsIgnoreCase(zcpm)) {
			pmzdArr.add("zcfbjpm");
			title.add("班级排名");
		}
		pmzdArr.add("pkValue");
		title.add("操作");

		List<String[]> zcInfo = new ArrayList<String[]>();

		zcInfo.add(title.toArray(new String[] {}));

		sql.append(" (select xn, case when xq = 'no' then '无'  ");
		sql
				.append(" else (select xqmc  from xqdzb b  where a.xq = b.xqdm) end xqmc, ");
		sql
				.append(" zd1,zcfnjxypm,zcfnjzypm,zcfbjpm,cpzpm,xn||'!!@@!!'||xq||'!!@@!!'||nd||'!!@@!!'||xh pkValue ");
		sql.append(" from xg_pjpy_zhcpb a  where 1 = 1 and xh = ? ");
		sql
				.append(" and not exists (select 1  from xg_pjpy_zhcpb b where xn = ? ");
		sql.append(" and xq =? and nd = ? and a.xh = b.xh  and a.xn = b.xn ");
		sql.append(" and a.xq = b.xq  and a.nd = b.nd)  and exists (select 1 ");
		sql.append(" from xg_pjpy_zcxmb c where a.xn = c.xn  and a.xq = c.xq ");
		sql.append(" and a.nd = c.nd) order by xn,xq) ");

		// 将表头字段与结果集字段合一
		zcInfo.addAll(CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] { xh, pjxn, pjxq, pjnd }, dao.unionArray(colList,
						pmzdArr.toArray(new String[] {})), null));

		return zcInfo;
	}
	// --------------------其它模块调用 end---------------------------

}
