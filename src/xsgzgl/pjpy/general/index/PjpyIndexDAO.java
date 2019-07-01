package xsgzgl.pjpy.general.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

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
 * Description: 评奖评优_首页_通用_DAO类
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

public class PjpyIndexDAO extends CommDAO {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			

	// ==================执行查询操作 begin==============================
	
	/**
	 * 获得评奖等级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjdjList(PjpyIndexModel model,
			User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select a.lcdj, a.sftj, b.lcdj, b.sftj kfcz ");
		sql.append("from xg_pjpy_pjlcdjb a ");
		sql.append("left join xg_pjpy_pjlcdjb b ");
		sql.append("on a.lcdj = (b.lcdj + 1) ");
		sql.append("order by to_number(a.lcdj) ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lcdj", "sftj", "kfcz" });

		return list;
	}

	/**
	 * 获得已定制评奖流程列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCustomPjlcList(
			PjpyIndexModel model, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select lcdm,lcmc,lcdj,method,sftj,picname from xg_pjpy_pjlcb ");
		sql.append("where lcdj is not null ");
		sql.append("order by to_number(lcdj),to_number(lcdm)");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lcdm", "lcmc", "lcdj",
						"method", "sftj","picname" });

		return list;
	}

	/**
	 * 获得自由评奖流程列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getFreePjlcList(PjpyIndexModel model,
			User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select lcdm,lcmc from xg_pjpy_pjlcb ");
		sql.append("order by to_number(lcdm)");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] {}, new String[] { "lcdm", "lcmc" });

		return list;
	}

	/**
	 * 获得下一评奖等级列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getPjlcList(String lcdj, User user) {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("select lcdm from xg_pjpy_pjlcb ");
		sql.append("where lcdj = ? ");

		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { lcdj }, new String[] { "lcdm" });

		return list;
	}
	
	// ==================执行查询操作 end==============================

	// ==================执行保存操作 begin==============================
	
	/**
	 * 保存数据（xg_pjpy_pjlcb）
	 * 
	 * @table 评奖流程表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean savePjlcb(PjpyIndexModel model, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_pjlcb";

		// 流程代码
		String[] lcdm = model.getLcdm();

		// 流程等级
		String[] lcdj = model.getLcdj();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjlcb ");
		sql.append("set lcdj = ? ");
		sql.append("where lcdm = ? ");

		List<String[]> params = new ArrayList<String[]>();

		boolean flag = false;

		if (lcdm != null && lcdm.length > 0) {
			for (int i = 0; i < lcdm.length; i++) {
				String[] value = new String[] { lcdj[i], lcdm[i] };
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * 保存数据（xg_pjpy_pjlcdjb）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean savePjlcdjb(PjpyIndexModel model, User user)
			throws Exception {

		// 表名
		String tableName = "xg_pjpy_pjlcdjb";

		// 最大评奖流程
		String maxPjlc = model.getMaxPjlc();

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjlcdjb ");
		sql.append("(lcdj)");
		sql.append("values(?) ");

		List<String[]> params = new ArrayList<String[]>();

		boolean flag = false;

		if (!Base.isNull(maxPjlc)) {
			for (int i = 0; i < Integer.parseInt(maxPjlc); i++) {
				String[] value = new String[] { String.valueOf(i + 1) };
				params.add(value);
			}

			flag = saveArrDate(sql.toString(), params, tableName, user);
		}

		return flag;
	}

	/**
	 * 保存数据（xg_pjpy_xtszb）
	 * 
	 * @table 评奖系统设置表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean saveXtszb(PjpyIndexModel model, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_xtszb";

		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_xtszb ");
		sql.append("(pjxn,pjxq,pjzq,zcpm,zypm,cpz)");
		sql.append("values(?,?,?,?,?,?) ");

		String pjxn = model.getPjxn();// 评奖学年

		String pjxq = model.getPjxq();// 评奖学期

		String pjzq = model.getPjzq();// 评奖周期

		String zcpm = model.getZcpm();// 综测排名
		
		String zypm = model.getZypm();// 智育排名
		
		String cpz = model.getCpz();// 参评组

		List<String[]> params = new ArrayList<String[]>();

		String[] value = new String[] { pjxn, pjxq, pjzq, zcpm, zypm, cpz };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}

	// ==================执行保存操作 end==============================

	// ==================执行更新操作 begin=============================
	
	/**
	 * 修改数据（xg_pjpy_pjlcdjb）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updatePjlcdjb(String lcdj, User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_pjlcdjb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjlcdjb ");
		sql.append("set sftj = 'yes' ");
		sql.append("where lcdj = ? ");
		
		List<String[]> params = new ArrayList<String[]>();

		String[] value = new String[] { lcdj };
		params.add(value);

		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	/**
	 * 修改数据（xg_pjpy_pjlcb）
	 * 
	 * @table 评奖流程流程表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean updatePjlcb(User user) throws Exception {

		// 表名
		String tableName = "xg_pjpy_pjlcb";

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjlcb a ");
		sql.append("set a.sftj = 'yes' ");
		sql.append("where exists(");
		sql.append("select 1 from xg_pjpy_pjlcdjb b ");
		sql.append("where a.lcdj = b.lcdj ");
		sql.append("and b.sftj = 'yes' ");
		sql.append(")");
		sql.append("and 1=?");

		List<String[]> params = new ArrayList<String[]>();
		String[] value = new String[] { "1" };
		params.add(value);
		
		boolean flag = saveArrDate(sql.toString(), params, tableName, user);

		return flag;
	}
	
	// ==================执行更新操作 end==============================

	// ==================执行删除操作 begin==============================
	
	/**
	 * 删除数据（xg_pjpy_pjlcdjb）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delPjlcdjb(PjpyIndexModel model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_pjlcdjb ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] {});

		return flag;
	}

	/**
	 * 删除数据（xg_pjpy_xtszb）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public Boolean delXtszb(PjpyIndexModel model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_pjpy_xtszb ");

		boolean flag = dao.runUpdate(sql.toString(), new String[] {});

		return flag;
	}
	
	// ==================执行删除操作 end==============================
	
	// ==================执行初始化操作 begin ==============================
	
	/**
	 * 初始化数据（xg_pjpy_pjlcdjb:评奖流程等级表）
	 * 
	 * @table 评奖流程等级表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initPjlcdjb(PjpyIndexModel model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjlcdjb ");
		sql.append("set sftj = 'no' ");

		dao.runUpdate(sql.toString(), new String[] {});
	}

	/**
	 * 初始化数据（xg_pjpy_pjlcb:评奖流程表）
	 * 
	 * @table 评奖流程表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initPjlcb(PjpyIndexModel model, User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();
		sql.append("update xg_pjpy_pjlcb ");
		sql.append("set sftj = 'no' ");

		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_pjrykb:评奖人员库）
	 * 
	 * @table 评奖人员库表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initPjry(User user) throws Exception {

		DAO dao = DAO.getInstance();
		
		// 清空评奖人员库
		String del = "delete from xg_pjpy_pjrykb";
		dao.runUpdate(del, new String[] {});

		// 初始化评奖人员库
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjrykb ");
		sql.append("(xh,xm,bjdm,bjmc,sfcp,cpz) ");
		sql.append("select xh,xm,bjdm,bjmc, ");
		sql.append("'yes' sfcp,'' cpz from ");
		sql.append("( ");
		sql.append("select a.xh,a.xm, a.bjdm, b.bjmc ");
		sql.append("from (select xh,xm, bjdm ");
		sql.append("from bks_xsjbxx a ");
		sql.append("WHERE NOT EXISTS (SELECT 1 FROM xsxxb b WHERE a.xh = b.xh) ");
		sql.append("and (sfyby = '否' or sfyby is null) ");
		sql.append("and (sfzx = '在校' or sfzx is null) ");
		sql.append("UNION ALL ");
		sql.append("SELECT xh,xm,bjdm ");
		sql.append("FROM xsxxb a ");
		sql.append("where (sfyby = '否' or sfyby is null) ");
		sql.append("and (sfzx = '在校' or sfzx is null)) a ");
		sql.append("left join view_njxyzybj_all b on a.bjdm = b.bjdm ");
		sql.append("where a.bjdm is not null ");
		sql.append("and b.bjmc is not null ");
		sql.append(") ");

		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_cpzb:参评小组表）
	 * 
	 * @table 参评小组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initCpxz(User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		sql.append("delete from xg_pjpy_cpzb a ");
		sql.append("where 1=1 ");

		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_pjrykb:评奖人员库表）
	 * 
	 * @table 参评小组表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initCpxzRy(User user) throws Exception {

		DAO dao = DAO.getInstance();

		StringBuilder sql = new StringBuilder();

		// 需要参评组
		sql.append("update xg_pjpy_pjrykb a ");
		sql.append("set cpz = ");
		sql.append("(select cpzmc from xg_pjpy_cpzb b where a.bjdm = b.bjdm) ");
		sql.append("where exists ");
		sql.append("(select 1 from xg_pjpy_cpzb c where a.bjdm = c.bjdm) ");

		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_zcxmb:综测项目表）
	 * 
	 * @table 综测项目表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initZcxm(User user) throws Exception {
		
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 清空综测项目
		StringBuilder del = new StringBuilder();
		del.append(" delete from xg_pjpy_zcxmb a ");
		del.append(" where 1=1 ");
		del.append(" and a.xn=? ");
		del.append(" and a.xq=? ");
		del.append(" and a.nd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });
		
		//清空综测比例代码
		del = new StringBuilder();
		del.append(" delete from xg_pjpy_zcbldmb a ");
		del.append(" where 1=1 ");
		del.append(" and a.xn=? ");
		del.append(" and a.xq=? ");
		del.append(" and a.nd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });		
		
		//清空综测比例
		del = new StringBuilder();
		del.append(" delete from xg_pjpy_zcblb a ");
		del.append(" where 1=1 ");
		del.append(" and a.xn=? ");
		del.append(" and a.xq=? ");
		del.append(" and a.nd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });		
		
		//清空扩展字段
		del = new StringBuilder();
		del.append(" delete from xg_pjpy_zckzzdb a ");
		del.append(" where 1=1 ");
		del.append(" and a.xn=? ");
		del.append(" and a.xq=? ");
		del.append(" and a.nd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });		
		
		// 复制最新的综测项目
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_zcxmb ");
		sql.append(" (xn,xq,nd,xmdm,xmmc,xmjb,sjdm,lyb,zd,");
		sql.append(" condition,glxn,glxq,glnd,bzd,mrxm,jjf,lrly,ddwh)");
		sql.append(" select '" + pjxn + "' xn,'" + pjxq + "' xq,'" + pjnd
				+ "' nd,xmdm,xmmc,xmjb,sjdm,lyb,zd,");
		sql.append(" condition,glxn,glxq,glnd,bzd,mrxm,jjf,lrly,ddwh ");
		sql.append(" from xg_pjpy_zcxmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.xn, t.xq, t.nd ");
		sql.append(" from xg_pjpy_zcxmb t ");
		sql.append(" order by xn desc, xq desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd ");
		sql.append(" ) ");
		dao.runUpdate(sql.toString(), new String[] {});
		
		// 复制最新的综测比例代码
		sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_zcbldmb ");
		sql.append(" (xn,xq,nd,bldm,blmc,");
		sql.append(" lyb,zd,condition)");
		sql.append(" select '" + pjxn + "' xn,'" + pjxq + "' xq,'" + pjnd
				+ "' nd,bldm,blmc,");
		sql.append(" lyb,zd,condition ");
		sql.append(" from xg_pjpy_zcbldmb a ");
		sql.append(" where 1=1 ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.xn, t.xq, t.nd ");
		sql.append(" from xg_pjpy_zcbldmb t ");
		sql.append(" order by xn desc, xq desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd ");
		sql.append(" ) ");
		
		dao.runUpdate(sql.toString(), new String[] {});
		
		// 复制最新的综测比例
		sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_zcblb ");
		sql.append(" (xn,xq,nd,xmdm,bldm,bl)");
		sql.append(" select '" + pjxn + "' xn,'" + pjxq + "' xq,'" + pjnd
				+ "' nd,xmdm,bldm,bl ");
		sql.append(" from xg_pjpy_zcblb a ");
		sql.append(" where 1=1 ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.xn, t.xq, t.nd ");
		sql.append(" from xg_pjpy_zcblb t ");
		sql.append(" order by xn desc, xq desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd ");
		sql.append(" ) ");
		
		// 复制最新的综测扩展字段
		sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_zckzzdb ");
		sql.append(" (xn,xq,nd,xmdm,kzzd,xsmc,zdlx,checked,");
		sql.append(" source_table,select_dm,select_mc,xssx,sfxs)");
		sql.append(" select '" + pjxn + "' xn,'" + pjxq + "' xq,'" + pjnd
				+ "' nd,xmdm,kzzd,xsmc,zdlx,checked,source_table,select_dm,select_mc,xssx,sfxs ");
		sql.append(" from xg_pjpy_zckzzdb a ");
		sql.append(" where 1=1 ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.xn, t.xq, t.nd ");
		sql.append(" from xg_pjpy_zckzzdb t ");
		sql.append(" order by xn desc, xq desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.xn = b.xn ");
		sql.append(" and a.xq = b.xq ");
		sql.append(" and a.nd = b.nd ");
		sql.append(" ) ");
		
		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_zhcpb:综合测评表）
	 * 
	 * @table 评奖人员库表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initZhcp(User user) throws Exception {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		boolean flag = isExists("xg_pjpy_zhcpb", "xn||xq||nd", pjxn + pjxq
				+ pjnd);
		
		// 初始化评奖人员库
		StringBuilder sql = new StringBuilder();
		
		if(flag){//已存在
			
			sql = new StringBuilder();
			sql.append("delete from xg_pjpy_zhcpb a ");
			sql.append("where not exists( ");
			sql.append("select 1 from xg_view_pjpy_pjryk b ");
			sql.append("where a.xh=b.xh ");
			sql.append(") ");
			sql.append("and a.xn=? ");
			sql.append("and a.xq=? ");
			sql.append("and a.nd=? ");
			dao.runUpdate(sql.toString(), new String[] { pjxn, pjxq, pjnd });
			
			sql = new StringBuilder();
			sql.append("insert into xg_pjpy_zhcpb ");
			sql.append("(xn,xq,nd,xh) ");
			sql.append("select ? xn,? xq,? nd,a.xh ");
			sql.append("from xg_view_pjpy_pjryk a ");
			sql.append("where not exists( ");
			sql.append("select 1 from xg_pjpy_zhcpb b ");
			sql.append("where a.xh=b.xh ");
			sql.append("and b.xn=? ");
			sql.append("and b.xq=? ");
			sql.append("and b.nd=? ");
			sql.append(") ");
			dao.runUpdate(sql.toString(), new String[] { pjxn, pjxq, pjnd, pjxn, pjxq, pjnd});
			
		}else{//未存在
			sql.append("insert into xg_pjpy_zhcpb ");
			sql.append("(xn,xq,nd,xh) ");
			sql.append("select ? xn,? xq,? nd,xh ");
			sql.append("from xg_view_pjpy_pjryk ");
			dao.runUpdate(sql.toString(), new String[] { pjxn, pjxq, pjnd });
		}	
	}
	
	/**
	 * 初始化数据（xg_pjpy_pjxmwhb:评奖项目表）
	 * 
	 * @table 评奖项目表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initPjxm(User user) throws Exception {
		
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 清空评奖项目
		StringBuilder del = new StringBuilder();
		del.append(" delete from xg_pjpy_pjxmwhb a ");
		del.append(" where 1=1 ");
		del.append(" and a.pjxn=? ");
		del.append(" and a.pjxq=? ");
		del.append(" and a.pjnd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });	
		
		// 复制最新的评奖项目
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_pjxmwhb ");
		sql.append(" (pjxn,pjxq,pjnd,xmmc,xssx,xmxz,xmlx,kzfw,rssz,");
		sql.append(" xmsm,sqfs,xmje,kzjb,sfqy,sfsh,lcid,rskz,jdkz,xmsy,tsrq)");
		sql.append(" select '" + pjxn + "' xn,'" + pjxq + "' xq,'" + pjnd
				+ "' nd,xmmc,xssx,xmxz,xmlx,kzfw,rssz,");
		sql.append(" xmsm,sqfs,xmje,kzjb,sfqy,sfsh,lcid,rskz,jdkz,xmsy,tsrq ");
		sql.append(" from xg_pjpy_pjxmwhb a ");
		sql.append(" where 1=1 ");
		sql.append(" and exists( ");
		sql.append(" select 1 from ");
		sql.append(" (select * ");
		sql.append(" from (select t.pjxn, t.pjxq, t.pjnd ");
		sql.append(" from xg_pjpy_pjxmwhb t ");
		sql.append(" order by pjxn desc, pjxq desc) ");
		sql.append(" where rownum = 1) b ");
		sql.append(" where a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" ) ");
		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化品德表现互评分信息
	 * @param user
	 * @throws Exception
	 */
	public void initPdbx(User user) throws Exception{
		
		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();
		
		// 清空品德表现互评分
		StringBuilder del = new StringBuilder();
		
		del.append(" delete from xg_pjpy_pdbxhpb a ");
		del.append(" where 1=1 ");
		del.append(" and a.xn=? ");
		del.append(" and a.xq=? ");
		del.append(" and a.nd=? ");
		dao.runUpdate(del.toString(), new String[] { pjxn, pjxq, pjnd });	
		
		// 初始化品德表现互评分信息
		StringBuilder sql = new StringBuilder();
		
		sql.append(" insert into xg_pjpy_pdbxhpb(pfr,bpfr,xn,xq,nd,sftj,jsqr) ");
		sql.append("  select a.pfr,a.bpfr,?,?,?,'否'sftj,'否' jsqr from  ");
		sql.append("  (select a.xh pfr,b.xh bpfr from xg_pjpy_pjrykb a  ");
		sql.append("  left join xg_pjpy_pjrykb b on a.bjdm=b.bjdm where a.xh<>b.xh)a  ");
		dao.runUpdate(sql.toString(), new String[] { pjxn, pjxq, pjnd });	

	}
	
	/**
	 * 初始化数据（xg_pjpy_pjlsxxb:历史信息表）
	 * 
	 * @table 历史信息表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initLsxx(User user) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		// 清空同周期数据
		StringBuilder del = new StringBuilder();
		del.append(" delete from xg_pjpy_pjlsxxb c ");
		del.append(" where 1=1 ");
		del.append(" and exists (select 1 from ( ");
		del.append(" select a.pjxn,a.pjxq,b.xmlx,b.xmmc,a.xh,b.xmje,a.sqsj,a.sqly bz ");
		del.append(" from xg_pjpy_pjxmsqb a ");
		del.append(" left join xg_pjpy_pjxmwhb b ");
		del.append(" on a.xmdm = b.xmdm ");
		del.append(" and a.pjxn = b.pjxn ");
		del.append(" and a.pjxq = b.pjxq ");
		del.append(" and a.pjnd = b.pjnd ");
		del.append(" where (sqjg = 'tg' or sqjg = 'wxsh') ");
		//sql.append(" and over = 'yes'  ");
		del.append(" ) d where c.xn=d.pjxn ");
		del.append(" and c.xq=d.pjxq ");
		del.append(" and c.xmmc=d.xmmc ");
		del.append(" and c.xh=d.xh ");
		del.append(" ) ");
		dao.runUpdate(del.toString(), new String[] {});
		
		// 迁移数据
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_pjlsxxb ");
		sql.append(" (xn,xq,xmlx,xmmc,xh,xmje,hdsj,bz) ");
		sql.append(" select a.pjxn,a.pjxq,nvl(b.xmlx,01) xmlx, ");
		sql.append("nvl(b.xmmc,'奖学金') xmmc,a.xh,b.xmje,a.sqsj,a.sqly bz ");
		sql.append(" from xg_pjpy_pjxmsqb a ");
		sql.append(" left join xg_pjpy_pjxmwhb b ");
		sql.append(" on a.xmdm = b.xmdm ");
		sql.append(" and a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" where (sqjg = 'tg' or sqjg = 'wxsh') ");
		//sql.append(" and over = 'yes'  ");
		
		dao.runUpdate(sql.toString(), new String[] {});
		
		// 清空评奖周期申请记录
		del = new StringBuilder();
		del.append(" delete from xg_pjpy_pjxmsqb ");
		dao.runUpdate(del.toString(), new String[] {});
		
		// 清空评奖周期审核记录
		del = new StringBuilder();
		del.append(" delete from xg_pjpy_pjxmshb ");
		dao.runUpdate(del.toString(), new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_tjszb:评奖条件表）
	 * 
	 * @table 历史信息表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initPjtj(User user) throws Exception {
		
		DAO dao = DAO.getInstance();

		String del = "delete from xg_pjpy_tjszb";
		dao.runUpdate(del, new String[] {});
	}
	
	/**
	 * 初始化数据（xg_pjpy_jdszb:项目兼得表）
	 * 
	 * @table 历史信息表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initXmjd(User user) throws Exception {
		
		DAO dao = DAO.getInstance();
		
		String del = "delete from xg_pjpy_jdszb";
		dao.runUpdate(del, new String[] {});
	}
	
	/**
	 * 备份数据【xg_pjpy_pjxmsqb_backup:评奖申请备份表】 【xg_pjpy_pjxmshb_backup:评奖审核备份表】
	 * 
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void backUpTable(User user) throws Exception {

		DAO dao = DAO.getInstance();
		// 备份申请信息
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_pjxmsqb_backup ");
		sql.append(" select a.*,to_char(sysdate,'yyyyMMdd') from xg_pjpy_pjxmsqb a ");
		dao.runUpdate(sql.toString(), new String[] {});

		// 备份审核信息
		sql = new StringBuilder();
		sql.append(" insert into xg_pjpy_pjxmshb_backup ");
		sql.append(" select a.*,to_char(sysdate,'yyyyMMdd') from xg_pjpy_pjxmshb a ");
		dao.runUpdate(sql.toString(), new String[] {});
	}
	
	/**
	 * 初始化备注（xg_pjpy_zhcpb:综合测评表）
	 * 
	 * @table 综合测评表表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initComments(List<HashMap<String, String>> zcxmList, User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		// 重置comments
		StringBuilder sql = new StringBuilder();

		if (zcxmList != null && zcxmList.size() > 0) {
			for (int i = 0; i < zcxmList.size(); i++) {
				String xmdm = zcxmList.get(i).get("en");
				String xmmc = zcxmList.get(i).get("cn");
				sql = new StringBuilder();
				sql.append("comment on column ");
				sql.append("xg_pjpy_zhcpb.");
				sql.append(xmdm);
				sql.append(" is '");
				sql.append(xmmc);
				sql.append("'");
				dao.creatView(sql.toString(), new String[] {});
			}
		}
	}
	
	/**
	 * 初始化数据（drb:导入表）
	 * 
	 * @table 导入表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initDrb(List<HashMap<String, String>> zcxmList,
			List<HashMap<String, String>> kzzdList, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 学校代码
		String xxdm = Base.xxdm;
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		// 表
		String tableName = "xg_pjpy_zhcpb";

		DAO dao = DAO.getInstance();

		// 清空信息
		StringBuilder del = new StringBuilder();
		del.append(" delete from drb a ");
		del.append(" where 1=1 ");
		del.append(" and xxdm = ? ");
		del.append(" and zdssb = ? ");
		dao.runUpdate(del.toString(), new String[] { xxdm, tableName });
		
		// 执行插入
		StringBuilder sql = new StringBuilder();
		sql.append("insert into drb (zdmc,zdssb,xxdm,zdsm,xsxh)");
		sql.append("values(?,?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(new String[] { "xn", tableName, xxdm, "评奖学年" ,"1"});
		params.add(new String[] { "xq", tableName, xxdm, "评奖学期" ,"2"});
		params.add(new String[] { "nd", tableName, xxdm, "评奖年度" ,"3"});
		params.add(new String[] { "xh", tableName, xxdm, "学号","4" });
		
		int n=5;
		if (zcxmList != null && zcxmList.size() > 0) {
			for (int i = 0; i < zcxmList.size(); i++) {
				String xmdm = zcxmList.get(i).get("en");
				String xmmc = zcxmList.get(i).get("cn");

				String[] value = new String[] { xmdm, tableName, xxdm, xmmc,String.valueOf(n) };
				params.add(value);
				n++;
			}
		}
		
		if (kzzdList != null && kzzdList.size() > 0) {
			for (int i = 0; i < kzzdList.size(); i++) {
				String xmdm = kzzdList.get(i).get("kzzd");
				String xmmc = kzzdList.get(i).get("xsmc");
				String[] value = new String[] { xmdm, tableName, xxdm, xmmc,String.valueOf(n) };
				params.add(value);
				n++;
			}
		}
		
		saveArrDate(sql.toString(), params, tableName, user);
	}
	
	/**
	 * 初始化数据（dcb:导出表）
	 * 
	 * @table 导出表
	 * @author 伟大的骆
	 * @throws Exception
	 */
	public void initDcb(List<HashMap<String, String>> zcxmList,
			List<HashMap<String, String>> kzzdList, User user) throws Exception {

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();

		// 学校代码
		String xxdm = Base.xxdm;
		// 评奖周期
		String pjzq = jbszModel.getPjzq();
		// 表
		String tableName = "xg_view_pjpy_zhcpresult_ryk";

		DAO dao = DAO.getInstance();

		// 清空信息
		StringBuilder del = new StringBuilder();
		del.append(" delete from dcb a ");
		del.append(" where 1=1 ");
		del.append(" and xxdm = ? ");
		del.append(" and zdssb = ? ");
		dao.runUpdate(del.toString(), new String[] { xxdm, tableName });
		
		// 执行插入
		StringBuilder sql = new StringBuilder();
		sql.append("insert into dcb (zdmc,zdssb,xxdm,zdsm)");
		sql.append("values(?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		
		params.add(new String[] { "xn", tableName, xxdm, "评奖学年" });
		params.add(new String[] { "xq", tableName, xxdm, "评奖学期" });
		params.add(new String[] { "nd", tableName, xxdm, "评奖年度" });
		params.add(new String[] { "xh", tableName, xxdm, "学号" });
		params.add(new String[] { "xm", tableName, xxdm, "姓名" });
		params.add(new String[] { "nj", tableName, xxdm, "年级" });
		//params.add(new String[] { "xydm", tableName, xxdm, "院系代码" });
		params.add(new String[] { "xymc", tableName, xxdm, "院系名称" });
		//params.add(new String[] { "zydm", tableName, xxdm, "专业代码" });
		params.add(new String[] { "zymc", tableName, xxdm, "专业名称" });
		//params.add(new String[] { "bjdm", tableName, xxdm, "班级代码" });
		params.add(new String[] { "bjmc", tableName, xxdm, "班级名称" });
		params.add(new String[] { "cpzmc", tableName, xxdm, "参评组" });
		
		if (zcxmList != null && zcxmList.size() > 0) {
			for (int i = 0; i < zcxmList.size(); i++) {
				String xmdm = zcxmList.get(i).get("en");
				String xmmc = zcxmList.get(i).get("cn");
				String[] value = new String[] { xmdm, tableName, xxdm, xmmc };
				params.add(value);
			}
		}
		
		if (kzzdList != null && kzzdList.size() > 0) {
			for (int i = 0; i < kzzdList.size(); i++) {
				String xmdm = kzzdList.get(i).get("kzzd");
				String xmmc = kzzdList.get(i).get("xsmc");
				String[] value = new String[] { xmdm, tableName, xxdm, xmmc };
				params.add(value);
			}
		}
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		params.add(new String[] { "zyfnjxypm", tableName, xxdm, "智育分排名(年级"+Base.YXPZXY_KEY+")" });
		params.add(new String[] { "zcfnjxypm", tableName, xxdm, "综合分排名(年级"+Base.YXPZXY_KEY+")" });
		
		params.add(new String[] { "zyfnjzypm", tableName, xxdm, "智育分排名(年级专业)" });
		params.add(new String[] { "zcfnjzypm", tableName, xxdm, "综合分排名(年级专业)" });
		
		params.add(new String[] { "zyfbjpm", tableName, xxdm, "智育分排名(班级)" });
		params.add(new String[] { "zcfbjpm", tableName, xxdm, "综合分排名(班级)" });
		
		params.add(new String[] { "zyfcpzpm", tableName, xxdm, "智育分排名(参评组)" });
		params.add(new String[] { "cpzpm", tableName, xxdm, "综合分排名(参评组)" });
		saveArrDate(sql.toString(), params, tableName, user);
	}
	// ==================执行初始化操作  end=============================
	
	// ==================结束本次评奖  begin=============================
	/**
	 * 获取本次评奖统计信息
	 * author qlj
	 */
	public List<HashMap<String, String>> getBcpjtjInfo(User user)
			throws Exception {

		DAO dao = DAO.getInstance();

		PjpyGeneralForm jbszModel = PjpyGeneralForm.getJbszModel();
		// 评奖学年
		String pjxn = jbszModel.getPjxn();
		// 评奖学期
		String pjxq = jbszModel.getPjxq();
		// 评奖年度
		String pjnd = jbszModel.getPjnd();

		// 备份申请信息
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select xmmc,sum(xmje)xmje,sum(hdrs)hdrs,sum(sqrs)sqrs,sum(zje)zje from ( ");
		sql.append(" select xmmc,to_number(xmje)xmje,hdrs,to_number(sqrs)sqrs,zje from ( ");
		
		// ---------------------评奖流程信息（合计）begin---------------		
		sql.append(" select a.xmmc, a.xmje, nvl(b.hdrs, 0) hdrs, ");
		sql.append(" nvl(c.sqrs, 0) sqrs,nvl((xmje * hdrs), 0) zje ");
		sql.append(" from (select xmdm, xmmc, xmje from xg_pjpy_pjxmwhb ");
		sql.append(" where pjxn = ?  and pjxq = ? and pjnd = ?) a ");
		sql.append(" left join (select xmdm, count(1) hdrs  from xg_pjpy_pjxmsqb ");
		sql.append(" where pjxn = ? and pjxq = ? and pjnd = ? ");
		sql.append(" and (sqjg = 'tg' or sqjg='wxsh')  group by xmdm) b on a.xmdm = b.xmdm ");
		sql.append(" left join (select xmdm, count(1) sqrs  from xg_pjpy_pjxmsqb ");
		sql.append(" where pjxn = ?   and pjxq = ? and pjnd = ? ");
		sql.append(" group by xmdm) c on a.xmdm = c.xmdm)a  ");
		// ---------------------评奖流程信息（合计）end---------------
		
		sql.append(" union ");
		// ---------------------历史表评奖信息（导入数据的合计）begin---------------
		sql.append(" select xmmc,sum(nvl(xmje,0))xmje,count(1)hdrs,0 sqrs,sum(nvl(xmje,0)) zje  ");
		sql.append(" from xg_pjpy_pjlsxxb where xn = ?  and xq = ? group by xn,xq,xmmc  ");
		// ---------------------历史表评奖信息（导入数据的合计）end---------------
		sql.append(" )group by xmmc  order by zje desc nulls last ");

		return dao.getListNotOut(sql.toString(), new String[] { pjxn, pjxq,
				pjnd, pjxn, pjxq, pjnd, pjxn, pjxq, pjnd,pjxn,pjxq });
	}
	
	// ==================结束本次评奖  end=============================
}
