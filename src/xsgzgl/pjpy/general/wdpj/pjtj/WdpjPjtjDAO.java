package xsgzgl.pjpy.general.wdpj.pjtj;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.CommDAO;
import xsgzgl.pjpy.general.PjpyGeneralForm;
import xsgzgl.pjpy.general.pjsz.pjxm.PjszPjxmModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_我的评奖_评奖条件_通用_DAO类
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

public class WdpjPjtjDAO extends CommDAO {

	// ================执行查询操作 begin===========================
	/**
	 * 查询项目时间
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String, String> getXmsjInfo(String xmdm) {

		DAO dao = DAO.getInstance();

		// 项目设置对象
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append("select sqkssj,sqjssj,sqkzkg, ");
		sql.append("shkssj,shjssj,shkzkg,bz, ");
		sql.append("to_char(sysdate,'yyyyMMdd') nowtime ");
		sql.append("from xg_pjpy_sjszb ");
		sql.append("where 1=1 ");
		sql.append("and pjxn=? ");
		sql.append("and pjxq=? ");
		sql.append("and pjnd=? ");
		sql.append("and xmdm=? ");

		return dao.getMapNotOut(sql.toString(), new String[] { pjxn, pjxq,
				pjnd, xmdm });
	}

	/**
	 * 获得评奖条件
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjtjList(String xmdm) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.tjdm,a.tjfw,a.gx,a.tjz, ");
		sql.append("b.tjlx,b.tablename,b.zd,b.tsgs, ");
		sql.append("b.condition,b.xn,b.xq,b.nd,b.bzd, ");
		sql.append("b.tjms,b.tjmc,b.pjxm ");
		sql.append("from xg_pjpy_tjszb a,xg_pjpy_pjtjkb b ");
		sql.append("where a.tjdm = b.tjdm ");
		sql.append("and a.xmdm = ? ");
		sql.append("order by a.tjfw desc ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "tjdm", "tjfw", "tjms",
						"tjmc", "gx", "tjz", "tjlx", "tablename", "zd", "tsgs",
						"condition", "xn", "xq", "nd", "bzd", "pjxm" });

		return list;
	}

	/**
	 * 获得比较值
	 * 
	 * @author 伟大的骆
	 */
	public String getBjz(String xh, Map<String, String> map, String lx) {

		// 用户类型
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 限制条件
		String condition = map.get("condition");
		// 学年限制
		String xn = map.get("xn");
		// 学期限制
		String xq = map.get("xq");
		// 年度限制
		String nd = map.get("nd");
		// 项目代码
		String xmdm = map.get("xmdm");
		// 评奖项目
		String pjxm = map.get("pjxm");
		// 表字段
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 当前学年
		String dqxn = Base.currXn;
		// 当前学期
		String dqxq = Base.currXq;
		// 当前年度
		String dqnd = Base.currNd;

		// 过滤输入值
		ArrayList<String> inputV = new ArrayList<String>();
		inputV.add(xh);

		StringBuilder sql = new StringBuilder();

		sql.append(" select bjz from (");
		sql.append(" select ");

		// 类型非空
		if (!Base.isNull(lx)) {
			sql.append(lx);
			sql.append("(");
			sql.append(zd);
			sql.append(")");
		} else {
			sql.append(zd);
		}
		sql.append(" bjz from ");
		sql.append(tablename);
		sql.append(" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		// 限制条件
		sql.append(Base.isNull(condition) ? "" : condition);

		// 需要控制学年
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// 需要控制学期
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// 需要控制年度
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		// 需要控制评奖项目
		if ("yes".equalsIgnoreCase(pjxm)) {
			sql.append(" and xmdm = ? ");
			inputV.add(xmdm);
		}
		
		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();

		String bjz = dao.getOneRs(sql.toString(), inputV
				.toArray(new String[] {}), "bjz");

		return bjz;
	}
	
	/**
	 * 
	 * @描述:取终测数据
	 * @作者：ligl
	 * @日期：2013-10-12 上午09:39:11
	 * @修改记录: 
	 * @param xh
	 * @param map
	 * @param lx
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getBjzData(String xh, Map<String, String> map, String lx) {

		// 用户类型
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 条件来源表
		String tablename = map.get("tablename");
		// 字段
		String zd = map.get("zd");
		// 限制条件
		String condition = map.get("condition");
		// 学年限制
		String xn = map.get("xn");
		// 学期限制
		String xq = map.get("xq");
		// 年度限制
		String nd = map.get("nd");
		// 项目代码
		String xmdm = map.get("xmdm");
		// 评奖项目
		String pjxm = map.get("pjxm");
		// 表字段
		String bzd = map.get("bzd");
		bzd = Base.isNull(bzd) ? "" : bzd;

		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 当前学年
		String dqxn = Base.currXn;
		// 当前学期
		String dqxq = Base.currXq;
		// 当前年度
		String dqnd = Base.currNd;

		// 过滤输入值
		ArrayList<String> inputV = new ArrayList<String>();
		inputV.add(xh);

		StringBuilder sql = new StringBuilder();

		sql.append(" select * from (");
		sql.append(" select ");
		sql.append(" * from ");
		sql.append(tablename);
		sql.append(" a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xh = ? ");
		// 限制条件
		sql.append(Base.isNull(condition) ? "" : condition);

		// 需要控制学年
		if ("pjxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(pjxn);
		} else if ("dqxn".equalsIgnoreCase(xn)) {
			sql.append(" and " + bzd + "xn = ? ");
			inputV.add(dqxn);
		}

		// 需要控制学期
		if ("pjxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(pjxq);
		} else if ("dqxq".equalsIgnoreCase(xq)) {
			sql.append(" and " + bzd + "xq = ? ");
			inputV.add(dqxq);
		}

		// 需要控制年度
		if ("pjnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(pjnd);
		} else if ("dqnd".equalsIgnoreCase(nd)) {
			sql.append(" and " + bzd + "nd = ? ");
			inputV.add(dqnd);
		}

		// 需要控制评奖项目
		if ("yes".equalsIgnoreCase(pjxm)) {
			sql.append(" and xmdm = ? ");
			inputV.add(xmdm);
		}
		
		sql.append(" ) where rownum = 1 ");

		DAO dao = DAO.getInstance();
		
		HashMap<String, String> resultMap = dao.getMapNotOut(sql.toString(), inputV
				.toArray(new String[] {}));
		return resultMap;
	}
	
	

	/**
	 * 获得项目设置人数
	 * 
	 * @author 伟大的骆
	 */
	public String getSzrs(String xh, String xmdm, String kzfw) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select nvl(a.qdrs,0) qdrs ");
		sql.append(" from xg_pjpy_rsszb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xmdm=? ");
		sql.append(" and a.szfw=? ");
		sql.append(" and exists (select 1 ");
		sql.append(" from xg_view_pjpy_pjryk b ");
		sql.append(" where b.xh = ? ");
		sql.append(" and a.bmdm=");
		if ("nj".equalsIgnoreCase(kzfw)) {// 年级
			sql.append("b.nj");
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 学院
			sql.append("b.xydm");
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 年级+学院
			sql.append("b.nj||b.xydm");
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 年级+专业
			sql.append("b.nj||b.zydm");
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// 年级+专业
			sql.append("b.cpzdm");
		} else {// 班级
			sql.append("b.bjdm");
		}
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String szrs = dao.getOneRs(sql.toString(), new String[] { xmdm, kzfw,
				xh }, "qdrs");

		return szrs;
	}

	/**
	 * 获得项目通过人数
	 * 
	 * @author 伟大的骆
	 */
	public String getTgrs(String xh, String xmdm, String kzfw, String gwid) {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		String bmdm = "";
		if ("nj".equalsIgnoreCase(kzfw)) {// 年级
			bmdm = "b.nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 学院
			bmdm = "b.xydm";
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 年级+学院
			bmdm = "b.nj||b.xydm";
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 年级+专业
			bmdm = "b.nj||b.zydm";
		} else {// 班级
			bmdm = "b.bjdm";
		}

		StringBuilder sql = new StringBuilder();

		sql.append(" select count(1) tgrs ");
		sql.append(" from (");
		sql.append(" select a.xh, ");
		sql.append(" " + bmdm + " bmdm ");
		sql.append(" from xg_pjpy_pjxmsqb a ");
		sql.append(" left join xg_view_pjpy_pjryk b");
		sql.append(" on a.xh=b.xh");
		sql.append(" where 1=1 ");
		sql.append(" and a.xmdm=? ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");
		sql.append(" and a.pjnd=? ");
		sql.append(" and a.xh<>? ");
		sql.append(" and a.sqjg<>'btg' ");
		sql.append(" ) a ");
		sql.append(" where exists (select 1 ");
		sql.append(" from ( ");
		sql.append(" select ");
		sql.append(bmdm);
		sql.append(" bmdm from xg_view_pjpy_pjryk b ");
		sql.append(" where b.xh = ? ");
		sql.append(" ) b ");
		sql.append(" where a.bmdm=b.bmdm");
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String szrs = dao.getOneRs(sql.toString(), new String[] { xmdm, pjxn,
				pjxq, pjnd, xh, xh }, "tgrs");

		return szrs;
	}

	/**
	 * 获得项目非兼得项目
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getJdxmList(String xmdm) {

		StringBuilder sql = new StringBuilder();

		sql.append(" select fjddm ");
		sql.append(" from xg_pjpy_jdszb a ");
		sql.append(" where 1=1 ");
		sql.append(" and xmdm=? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm }, new String[] { "fjddm" });

		return list;
	}

	/**
	 * 获得已经获得的项目
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getHdxmList(String xh, String gwid) {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		StringBuilder sql = new StringBuilder();

		sql.append(" select a.xmdm,b.xmmc ");
		sql.append(" from xg_pjpy_pjxmsqb a left join xg_pjpy_pjxmwhb b ");
		sql.append(" on a.xmdm=b.xmdm ");
		sql.append(" and a.pjxn=b.pjxn ");
		sql.append(" and a.pjxq=b.pjxq ");
		sql.append(" and a.pjnd=b.pjnd ");
		sql.append(" where 1=1 ");
		sql.append(" and a.xh=? ");
		sql.append(" and a.pjxn=? ");
		sql.append(" and a.pjxq=? ");
		sql.append(" and a.pjnd=? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xh, pjxn, pjxq, pjnd }, new String[] { "xmdm",
						"xmmc" });

		return list;
	}

	/**
	 * 获得项目申请人列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBshrList(String[] xh) {

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xh,a.xm,a.nj,a.xydm,a.xymc,a.zydm,a.cpzdm,a.cpzmc, ");
		sql.append(" a.zymc,a.bjdm,a.bjmc from xg_view_pjpy_pjryk a ");
		sql.append(" where 1 = 1 ");

		if (xh != null && xh.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < xh.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = ? ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(), xh,
				new String[] { "xh", "xm", "nj", "xydm", "zydm", "bjdm",
						"xymc", "zymc", "bjmc", "cpzdm", "cpzmc" });

		return list;
	}

	/**
	 * 获得项目申请人所在部门
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getSqrbm(List<HashMap<String, String>> sqrList, String kzfw)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select ");

		// 控制范围
		if ("nj".equalsIgnoreCase(kzfw)) {// 年级
			sql.append(" distinct nj bmdm");
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 学院
			sql.append(" distinct xydm bmdm");
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 年级+学院
			sql.append(" distinct nj||xydm bmdm ");
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 年级+专业
			sql.append(" distinct nj||zydm bmdm ");
		} else {
			sql.append(" distinct bjdm bmdm ");
		}

		sql.append(" from view_njxyzybj_all a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and exists (select 1 from xg_view_pjpy_pjryk b ");
		sql.append(" where a.bjdm = b.bjdm  ");

		if (sqrList != null && sqrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < sqrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" b.xh = '" + sqrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" ) ");

		DAO dao = DAO.getInstance();

		String[] bmdm = dao.getRs(sql.toString(), new String[] {}, "bmdm");

		return bmdm;
	}

	/**
	 * 获得项目申请人所在参评组
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public String[] getSqrCpz(List<HashMap<String, String>> sqrList)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(" select distinct cpz ");
		sql.append(" from xg_pjpy_pjrykb a ");
		sql.append(" where 1 = 1 ");

		if (sqrList != null && sqrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < sqrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + sqrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		String[] cpz = dao.getRs(sql.toString(), new String[] {}, "cpz");

		return cpz;
	}
	
	/**
	 * 获得项目通过人数
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getBmrsList(String xmdm, String gwid,
			String[] xh, String[] bmdm, String kzfw) {

		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.bmdm, a.bmrs, nvl(b.ytgrs, 0) ytgrs from ");

		sql.append(" (select a.bmdm,nvl(a.qdrs,0) bmrs from xg_pjpy_rsszb a ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm = ? ");
		sql.append(" and a.szfw = ? ");
		if (bmdm != null && bmdm.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bmdm.length; i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.bmdm = '" + bmdm[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(") a");

		// 控制范围
		String dm = "";
		if ("nj".equalsIgnoreCase(kzfw)) {// 年级
			dm = "nj";
		} else if ("xy".equalsIgnoreCase(kzfw)) {// 学院
			dm = "xydm";
		} else if ("njxy".equalsIgnoreCase(kzfw)) {// 年级+学院
			dm = "nj||b.xydm";
		} else if ("njzy".equalsIgnoreCase(kzfw)) {// 年级+专业
			dm = "nj||b.zydm";
		} else if ("cpz".equalsIgnoreCase(kzfw)) {// 年级+专业
			dm = "cpzdm";
		} else {// 班级
			dm = "bjdm";
		}

		sql.append(" left join (select b." + dm + " bmdm, count(1) ytgrs ");
		sql.append(" from (");
		sql.append(" select c.xh,d.nj,d.xydm,d.zydm,d.bjdm,d.cpzdm ");
		sql.append(" from xg_pjpy_pjxmsqb c, ");
		sql.append(" xg_view_pjpy_pjryk d ");
		sql.append(" where c.xh=d.xh ");
		sql.append(" and c.xmdm = ? ");
		sql.append(" and c.pjxn = ? ");
		sql.append(" and c.pjxq = ? ");
		sql.append(" and c.pjnd = ? ");
		if (!Base.isNull(gwid)) {
			sql.append(" and exists (");
			sql.append(" select 1 from xg_pjpy_pjxmshb e ");
			sql.append(" where e.xmdm=c.xmdm ");
			sql.append(" and e.pjxn=c.pjxn ");
			sql.append(" and e.pjxq=c.pjxq ");
			sql.append(" and e.pjnd=c.pjnd ");
			sql.append(" and e.xh=c.xh ");
			sql.append(" and e.shzt='tg' ");
			sql.append(" and e.xtgwid='" + gwid + "' ");
			sql.append(" )");
		}
		sql.append(" ) b where 1 = 1 ");

		// 被审核人
		if (xh != null && xh.length > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < xh.length; i++) {
				if (i != 0) {
					sql.append(" and ");
				}
				sql.append(" b.xh <> '" + xh[i] + "' ");
			}
			sql.append(" ) ");
		}
		sql.append(" group by b." + dm + ") b on a.bmdm = b.bmdm ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, kzfw, xmdm, pjxn, pjxq, pjnd },
				new String[] { "bmdm", "bmrs", "ytgrs" });

		return list;
	}
	
	/**
	 * 获得非兼得项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmjdList(PjszPjxmModel pjxmModel) {

		// 项目代码
		String xmdm = pjxmModel.getXmdm();

		StringBuilder sql = new StringBuilder();
		sql.append(" select a.xmdm,a.fjddm,b.xmmc fjdmc from xg_pjpy_jdszb a ");
		sql.append(" ,xg_pjpy_pjxmwhb b where a.fjddm=b.xmdm and a.xmdm = ? ");

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm },
				new String[] { "xmdm", "fjddm", "fjdmc" });

		return list;
	}
	
	/**
	 * 获得以获得奖学金列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getYhdjxjList(PjszPjxmModel pjxmModel,
			List<HashMap<String, String>> bshrList) {
		
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		// 项目代码
		String xmdm = pjxmModel.getXmdm();
		// 兼得控制岗位ID
		String gwid = pjxmModel.getJdkz();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.xh, c.xm, a.xmdm ");
		sql.append(" from xg_pjpy_pjxmsqb a, xg_pjpy_pjxmshb b, xg_pjpy_pjrykb c ");
		sql.append(" where 1 = 1 ");
		sql.append(" and a.xmdm <> ? ");
		sql.append(" and a.pjxn = ? ");
		sql.append(" and a.pjxq = ? ");
		sql.append(" and a.pjnd = ? ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.xh = c.xh ");
		sql.append(" and a.xh = b.xh ");
		sql.append(" and b.xtgwid = ? ");
		sql.append(" and b.shzt = 'tg' ");
		sql.append(" and a.sqjg<>'btg' ");
		// 被审核人
		if (bshrList != null && bshrList.size() > 0) {
			sql.append(" and ( ");
			for (int i = 0; i < bshrList.size(); i++) {
				if (i != 0) {
					sql.append(" or ");
				}
				sql.append(" a.xh = '" + bshrList.get(i).get("xh") + "' ");
			}
			sql.append(" ) ");
		}

		DAO dao = DAO.getInstance();

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { xmdm, pjxn, pjxq, pjnd, gwid }, new String[] {
						"xmdm", "xh", "xm" });

		return list;
	}
	// ================执行查询操作 end===========================

	// ================个性化条件 begin===========================
	/**
	 * 判断评奖学年是否有违纪处分
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkWjcfByPjXn(HashMap<String, Object> map)
			throws SQLException {

		DAO dao = DAO.getInstance();

		// 申请类型
		String sqlx = (String) map.get("sqlx");
		// 消息
		String message = "";
		// 申请或者上报学生学号
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql
				.append(" where xn = (select pjxn from xg_pjpy_xtszb where rownum = 1) ");

		sql.append(" and xh in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// 条件说明
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// 不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}

	/**
	 * 判断是否有违纪处分
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkWjcf(HashMap<String, Object> map) throws SQLException {

		DAO dao = DAO.getInstance();

		// 申请类型
		String sqlx = (String) map.get("sqlx");
		// 消息
		String message = "";
		// 申请或者上报学生学号
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from wjcfb ");
		sql.append(" where xh in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		sql.append(" and xxsh = '通过'  ");
		sql.append(" group by xh  ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// 条件说明
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// 不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}

	/**
	 * 是否困难生
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public String checkKns(HashMap<String, Object> map) throws SQLException {

		DAO dao = DAO.getInstance();

		// 申请类型
		String sqlx = (String) map.get("sqlx");
		// 消息
		String message = "";
		// 申请或者上报学生学号
		String[] xhArr = (String[]) map.get("xh");

		StringBuilder sql = new StringBuilder();
		sql.append(" select xh from xszz_knsb ");
		sql.append(" where xh not in( ");
		for (int i = 0; i < xhArr.length; i++) {
			if (i != 0) {

				sql.append(",");
			}

			sql.append("'" + xhArr[i] + "'");

		}
		sql.append(" ) ");

		String[] nozgXh = dao.getArray(sql.toString(), new String[] {}, "xh");

		// 条件说明
		String tjms = (String) map.get("tjms");

		boolean flag = (nozgXh == null || nozgXh.length == 0 ? true : false);

		if (!flag) {
			if ("sq".equalsIgnoreCase(sqlx)) {
				// 不满足条件的话
				message = "申请该项目需要：" + tjms + ",申请者不满足申请条件！";

			} else {

				message = nozgXh[0];
			}
		}
		return message;
	}
	/**
	 * 
	 * 补考通过门数
	 * 浙江旅游
	 */
	public boolean isTgbk(String xh) {
		// TODO 自动生成方法存根
		DAO dao=DAO.getInstance();
		boolean flag=true;
		String sql="select nvl(bkcj,'0') bkcj from view_bjgkc where xh=?";
		try {
			String[] bkcj=dao.getRs(sql, new String[]{xh}, "bkcj");
			for(int i=0;i<bkcj.length;i++){
				flag=Integer.parseInt(bkcj[i])>=60;
				if(!flag){
					break;
				}
			}
		} catch (Exception e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
		return flag;
	}
	
	// ================个性化条件 end===========================
	
	/**
	 * 获取人数控制范围
	 * 为班级的确定人数
	 * @param xmdm
	 * @param bjdm
	 * @return
	 */
	public HashMap<String,String>getQdrsByBj(String xmdm,String bjdm){
		
		DAO dao=DAO.getInstance();
		// 基本设置Model
		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		
		String pjxn=jbszModel.getPjxn();
		
		String pjxq=jbszModel.getPjxq();
		
		String pjnd=jbszModel.getPjnd();
		
		StringBuilder sql= new StringBuilder();
		
		sql.append(" select a.xmmc,b.qdrs from ( ");
		sql.append(" select * from xg_pjpy_pjxmwhb  ");
		sql.append(" where xmdm=? and pjxn=? and pjxq=? and pjnd=? ");
		sql.append(" )a left join  ");
		sql.append(" (select xmdm,nvl(qdrs,0)qdrs,bmdm,szfw from xg_pjpy_rsszb b )b ");
		sql.append(" on a.xmdm=b.xmdm and a.kzfw=b.szfw ");
		sql.append(" and b.bmdm=? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xmdm,pjxn,pjxq,pjnd,bjdm});
	}
	
	/**
	 * 检测学生是否在人员库中
	 * 
	 * @author 伟大的骆
	 */
	public HashMap<String,String> checkRyk(String xh) {
		
		DAO dao=DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from xg_pjpy_pjrykb where xh=? and sfcp='yes' ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
	}


}
