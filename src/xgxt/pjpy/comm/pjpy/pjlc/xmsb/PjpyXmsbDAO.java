package xgxt.pjpy.comm.pjpy.pjlc.xmsb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.form.User;
import xgxt.pjpy.comm.pjpy.PjpyCommDAO;
import xgxt.pjpy.comm.pjpy.PjpyCommService;
import xgxt.pjpy.comm.pjpy.PjxtszModel;
import xgxt.pjpy.comm.pjpy.model.PjpyXmszModel;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 评奖评优_评奖流程_项目上报_DAO类
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

public class PjpyXmsbDAO extends PjpyCommDAO {

	/**
	 * 获得项目上报学生列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXsList(PjpyXmsbForm model,
			User user) {

		PjpyCommService commService = new PjpyCommService();
		
		// 评奖学年
		String pjxn = PjxtszModel.pjxtszModel.getPjxn();
		// 评奖学期
		String pjxq = PjxtszModel.pjxtszModel.getPjxq();
		// 评奖年度
		String pjnd = PjxtszModel.pjxtszModel.getPjnd();
		// 项目代码
		String xmdm = model.getXmdm();

		// 项目设置model初始化
		String pk = pjxn + pjxq + pjnd + xmdm;
		PjpyXmszModel xmszModel = commService.getXmszForXmdm(pk);

		// 申请周期
		String sqzq = xmszModel.getSqzq();

		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "无";
			pjnd = "无";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "无";
		} else {
			pjxq = "无";
			pjxq = "无";
		}
		
		// 综测排名
		String zcpm = model.getZcpm();
		String pmzd = "";
		String zypmzd = "";
		if ("3".equalsIgnoreCase(zcpm)) {
			pmzd="zcfbjpm";
		} else if ("2".equalsIgnoreCase(zcpm)) {
			pmzd="zcfnjzypm";
		}
		
		String zypm = model.getZypm();
		if ("3".equalsIgnoreCase(zypm)) {
			zypmzd="zyfbjpm";
		} else if ("2".equalsIgnoreCase(zypm)) {
			zypmzd="zyfnjzypm";
		}
		
		// 班级代码
		String bjdm = model.getBjdm();

		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select a.xh, a.xm, a.xb, a.pjbjmc bjmc,a.pjbjmc,b.zd1 zhf,");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end pm,");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end zcfbjpm,");
		sql.append(" b.zd3 zyf, ");
		sql.append(" case when length(b."+pmzd+") = '1' then '0'||b."+pmzd);
		sql.append(" else b."+pmzd+" end zyfpm,");
		
		sql.append(" c.xh ysq,'' cz  ");
		sql.append("from view_xg_pjpy_ryqd a ");
		//综测
		sql.append("left join xg_pjpy_zhcpb b on a.xh = b.xh ");
		sql.append("and a.pjxn = b.xn ");
		//已申请
		sql.append("left join (");
		sql.append("select xh from xg_pjpy_pjxmsqb ");
		sql.append("where 1 = 1 ");
		sql.append("and xmdm = '" + xmdm + "' ");
		sql.append("and pjxn = '" + pjxn + "' ");
		sql.append("and pjxq = '" + pjxq + "' ");
		sql.append("and pjnd = '" + pjnd + "' ");
		sql.append(") c on a.xh = c.xh ");
		
		sql.append("where a.pjbjdm = ? ");
		sql.append(") ");
		
		// 排序
		String arrange = model.getArrange();
		// 排序方式
		String fashion = model.getFashion();
		
		if ("no".equalsIgnoreCase(arrange)) {// 无需排序
			sql.append("order by to_number(" + pmzd + ") ");
		} else {
			sql.append("order by " + arrange + " ");
			sql.append(fashion);
		}

		return getRsList("", "", new String[] { bjdm }, new String[] { "xh",
				"xm", "xb", "pjbjmc", "zhf", "pm","zyf","zyfpm", "ysq","cz" }, sql.toString());
	}
	
	/**
	 * 根据录入获取项目列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getXmOption(PjpyXmsbForm model) {

		DAO dao = DAO.getInstance();

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;
		
		String xmmc = model.getXmmc();// 部门名称
		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 将学期
		String pjnd = jbszModel.getPjnd();// 评奖年度

		StringBuilder sql = new StringBuilder();

		sql.append("select dm,mc from (");
		sql.append(" select xmdm dm ,xmmc mc,xssx from xg_pjpy_pjxmwhb a ");
		sql.append(" where 1 = 1 ");
		sql.append(Base.isNull(xmmc) ? "" : " and xmmc like '" + xmmc + "%' ");
		sql.append(" and pjxn=? ");
		sql.append(" and pjxq=? ");
		sql.append(" and pjnd=? ");
		sql.append(" and sfqy='是' ");
		sql.append(" and sqfs='lssb' ");
		sql.append(" and not exists ( ");
		sql.append(" select 1 from xg_pjpy_sjszb b ");
		sql.append(" where a.pjxn = b.pjxn ");
		sql.append(" and a.pjxq = b.pjxq ");
		sql.append(" and a.pjnd = b.pjnd ");
		sql.append(" and a.xmdm = b.xmdm ");
		sql.append(" and to_char(Sysdate, 'YYYYMMDD') < b.sqkssj ");
		sql.append(" and to_char(Sysdate, 'YYYYMMDD') > b.sqjssj ");
		sql.append(" and b.sqkzkg ='1' ");
		sql.append(")");
		sql.append(")");
		sql.append(" where rownum <=10 ");
		sql.append(" order by to_number(xssx) ");

		System.out.println("sql:"+sql);
		System.out.println("pjxn:"+pjxn);
		System.out.println("pjxq:"+pjxq);
		System.out.println("pjnd:"+pjnd);
		return dao.getList(sql.toString(), new String[] {pjxn,pjxq,pjnd}, new String[] {
				"dm", "mc" });
	}
	
	/**
	 * 获得项目设置人数
	 * 
	 * @author 伟大的骆
	 */
	public String getXmszrs(PjpyXmsbForm model) {
		
		PjpyXmszModel xmszModel = model.getXmszModel();
		String xmdm = model.getXmdm();
		String szfw = xmszModel.getKzfw();
		String bmdm = model.getBjdm();
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select qdrs from xg_pjpy_rsszb ");
		sql.append(" where 1 = 1 ");
		sql.append(" and xmdm = ? ");
		sql.append(" and szfw = ? ");
		sql.append(" and bmdm = ? ");
		
		DAO dao = DAO.getInstance();
		
		String qdrs = dao.getOneRs(sql.toString(), new String[]{xmdm,szfw,bmdm}, "qdrs");
		
		return qdrs;
	}
	
	/**
	 * 保存项目申请表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmsqb(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 项目设置model初始化
		PjpyXmszModel xmszModel = model.getXmszModel();
		
		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 将学期
		String pjnd = jbszModel.getPjnd();// 评奖年度
		String xmdm = model.getXmdm();// 项目代码
		String[] xh = model.getPjxh();// 评奖学号
		String sqsj = getNowTime("YYYY年MM月DD日");// 申请时间
		String sqly = "老师上报";// 申请理由
		String tjr = user.getUserName();// 提交人
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmsqb");
		sql.append("(xmdm,pjxn,pjxq,pjnd,xh,sqsj,sqly,tjr)");
		sql.append("values(?,?,?,?,?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_pjxmsqb";
		
		String sqzq = xmszModel.getSqzq();//申请周期
		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "无";
			pjnd = "无";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "无";
		} else {
			pjxq = "无";
			pjxq = "无";
		}
		
		boolean flag = true;
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				String[] value = new String[] { xmdm, pjxn, pjxq, pjnd, xh[i],
						sqsj, sqly, tjr };
				params.add(value);
			}
			
			try {
				//保存申请信息
				flag = saveArrDate(sql.toString(), params, tableName, user);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				flag = false;
			}
		}
		
		return flag;
	}
	
	/**
	 * 保存项目审核表
	 * 
	 * @author 伟大的骆
	 */
	public Boolean saveXmshb(PjpyXmsbForm model,
			User user) {
		
		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		// 项目设置model初始化
		PjpyXmszModel xmszModel = model.getXmszModel();
		
		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 将学期
		String pjnd = jbszModel.getPjnd();// 评奖年度
		String xmdm = model.getXmdm();// 项目代码
		String[] xh = model.getPjxh();// 评奖学号
		String sqsj = getNowTime("YYYY年MM月DD日");// 申请时间
		String sqly = "老师上报";// 申请理由
		String tjr = user.getUserName();// 提交人
		List<HashMap<String, String>> shlc = xmszModel.getShlc();
		
		StringBuilder sql = new StringBuilder();
		sql.append("insert into xg_pjpy_pjxmshb");
		sql.append("(xmdm,pjxn,pjxq,pjnd,xh,xtgwid)");
		sql.append("values(?,?,?,?,?,?)");
		
		List<String[]> params = new ArrayList<String[]>();
		String tableName = "xg_pjpy_pjxmshb";
		
		String sqzq = xmszModel.getSqzq();//申请周期
		if ("xn".equalsIgnoreCase(sqzq)) {
			pjxq = "无";
			pjnd = "无";
		} else if ("xq".equalsIgnoreCase(sqzq)) {
			pjnd = "无";
		} else {
			pjxq = "无";
			pjxq = "无";
		}
		
		boolean flag = true;
		
		if (xh != null && xh.length > 0) {
			for (int i = 0; i < xh.length; i++) {
				// 根据项目审核流程，插入项目审核表中
				for (int j = 0; j < shlc.size(); j++) {
					Map<String, String> shMap = shlc.get(j);
					String spgwId = shMap.get("gwid");
					String[] value = new String[] { xmdm, pjxn, pjxq, pjnd,
							xh[i], spgwId };
					params.add(value);
				}

			}

			try {
				// 保存申请信息
				flag = saveArrDate(sql.toString(), params, tableName, user);
			} catch (Exception e) {
				// TODO 自动生成 catch 块
				flag = false;
			}
		}

		return flag;
	}

	/**
	 * 获得学生成绩列表
	 * 
	 * @author 伟大的骆
	 */
	public List<HashMap<String, String>> getCjList(PjpyXmsbForm model) {

		PjxtszModel jbszModel = PjxtszModel.pjxtszModel;

		String xmmc = model.getXmmc();// 部门名称
		String pjxn = jbszModel.getPjxn();// 评奖学年
		String pjxq = jbszModel.getPjxq();// 评奖学期
		String pjnd = jbszModel.getPjnd();// 评奖年度

		// 学号
		String xh = model.getXh();

		StringBuilder sql = new StringBuilder();
		sql.append("select kcmc,kcxz,cj ");
		sql.append("from cjb ");
		sql.append("where xn = ? ");
		sql.append("and xh = ? ");
		sql.append("order by kcmc ");
		
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> list = dao.getList(sql.toString(),
				new String[] { pjxn, xh },
				new String[] { "kcmc", "kcxz", "cj" });

		return list;
	}
}
