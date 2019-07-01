package xgxt.xszz.comm.exp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import common.Globals;
import common.XszzXmdm;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.xszz.XszzTyForm;
import xgxt.xszz.comm.XszzCommDAO;

public class XszzExpDAO extends XszzCommDAO {
	
	/**
	 * 获得导出数据表头
	 * 
	 * @param xh
	 * @return
	 */
	public List<HashMap<String, String>> getTopTr(String xxdm, String xmdm) {

		DAO dao = DAO.getInstance();

		String[] colListCN = null;
		String[] colListEN = null;

		if (Globals.XXDM_ZGDZDX.equalsIgnoreCase(xxdm)) {// 中国地大
			colListCN = new String[] { "序号", "项目代码", "项目名称", "学号", "姓名", "性别",
					"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "民族", "政治面貌", "身份证号","级别", "金额",
					"学年", "学期", "年度", "申请时间", "审核状态1", "审核状态2", "审核状态3",
					"困难生级别", "是否孤残", "是否低保", "烈士子女", "是否单亲", "家庭户口", "家庭地址",
					"家庭电话", "家庭邮编", "家庭人数", "家庭人均收入", "收入来源", "已获资助情况",
					"家庭欠债情况", "家庭其他情况", "计算机水平", "外语水平", "上半学期学分绩点",
					"下半学期学分绩点", "班级排名", "综测排名", "科研情况", "获奖日期1", "获奖名称1",
					"获奖日期2", "获奖名称2", "获奖日期3", "获奖名称3", "获奖日期4", "获奖名称4",
					"最近情况" };
			colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
					"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
					"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
					"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
					"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
					"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
					"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
					"zjqk" };
		} else if (Globals.XXDM_WHSYFWXY.equalsIgnoreCase(xxdm)) {//武汉商业服务
			if (XszzXmdm.XSZZ_GJZXJ.equalsIgnoreCase(xmdm)) {//国家助学金特殊
				colListCN = new String[] { "序号", "项目代码", "项目名称", "学号", "姓名", "性别",
						"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "民族", "政治面貌", "身份证号","出生日期", "入学日期", "级别", "金额",
						"学年", "学期", "年度", "申请时间", "区域","审核状态1", "审核状态2", "审核状态3",
						"困难生级别", "是否孤残", "是否低保", "烈士子女", "是否单亲", "家庭户口", "家庭地址",
						"家庭电话", "家庭邮编", "家庭人数", "家庭人均收入", "收入来源", "已获资助情况",
						"家庭欠债情况", "家庭其他情况", "计算机水平", "外语水平", "上半学期学分绩点",
						"下半学期学分绩点", "班级排名", "综测排名", "科研情况", "获奖日期1", "获奖名称1",
						"获奖日期2", "获奖名称2", "获奖日期3", "获奖名称3", "获奖日期4", "获奖名称4",
						"最近情况" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "csrq", "rxrq", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "qy","shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
						"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
						"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
						"zjqk" };
			} else {//其它通用
				colListCN = new String[] { "序号", "项目代码", "项目名称", "学号", "姓名", "性别",
						"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "民族", "政治面貌", "身份证号","出生日期", "入学日期", "级别", "金额",
						"学年", "学期", "年度", "申请时间", "审核状态1", "审核状态2", "审核状态3",
						"困难生级别", "是否孤残", "是否低保", "烈士子女", "是否单亲", "家庭户口", "家庭地址",
						"家庭电话", "家庭邮编", "家庭人数", "家庭人均收入", "收入来源", "已获资助情况",
						"家庭欠债情况", "家庭其他情况", "计算机水平", "外语水平", "上半学期学分绩点",
						"下半学期学分绩点", "班级排名", "综测排名", "科研情况", "获奖日期1", "获奖名称1",
						"获奖日期2", "获奖名称2", "获奖日期3", "获奖名称3", "获奖日期4", "获奖名称4",
						"最近情况" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "csrq", "rxrq", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk", "jsjsp", "wysp", "sbxqxfjd",
						"xbxqxfjd", "bjpm", "zcpm", "kyqk", "hjrq1", "hjmc1",
						"hjrq2", "hjmc2", "hjrq3", "hjmc3", "hjrq4", "hjmc4",
						"zjqk" };
			}
		}else if (Globals.XXDM_HZNYDX.equalsIgnoreCase(xxdm) && XszzXmdm.XSZZ_HZNY_LSTD.equalsIgnoreCase(xmdm)) {//国家助学金特殊
				
				colListCN = new String[] { "学院", "专业", "姓名", "学费", "住宿费", "杂费","欠费总额","生源省","备注" };
				colListEN = new String[] { "xymc","xymc","xm","kzzd1","kzzd2","kzzd3","kzzd4","sydq","bz" };
			
		}else if (Globals.XXDM_HZSFXY.equalsIgnoreCase(xxdm)) {//湖州师范学院
			//“陆侯燕英”帮困奖学金&“迎南树人”奖学金
			if (XszzXmdm.XSZZ_HZSF_LHYY.equalsIgnoreCase(xmdm) || XszzXmdm.XSZZ_HZSF_YNSR.equalsIgnoreCase(xmdm)) {
				colListCN = new String[] { "序号", Base.YXPZXY_KEY, "姓名", "性别","班级", "在校期间获奖情况","困难类别" };
				colListEN = new String[] { "num","xymc","xm","xb","bjmc","bz","xmzzjb"};
			}else{
				colListCN = new String[] { "序号", "项目代码", "项目名称", "学号", "姓名", "性别",
						"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "民族", "政治面貌", "级别", "金额",
						"学年", "学期", "年度", "申请时间", "审核状态1", "审核状态2", "审核状态3",
						"困难生级别", "是否孤残", "是否低保", "烈士子女", "是否单亲", "家庭户口", "家庭地址",
						"家庭电话", "家庭邮编", "家庭人数", "家庭人均收入", "收入来源", "已获资助情况",
						"家庭欠债情况", "家庭其他情况" };
				colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
						"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
						"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
						"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
						"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
						"jtqzqk", "jtqtqk" };
			}
		}else {
			colListCN = new String[] { "序号", "项目代码", "项目名称", "学号", "姓名", "性别",
					"年级", Base.YXPZXY_KEY+"名称", "专业名称", "班级名称", "民族", "政治面貌", "级别", "金额",
					"学年", "学期", "年度", "申请时间", "审核状态1", "审核状态2", "审核状态3",
					"困难生级别", "是否孤残", "是否低保", "烈士子女", "是否单亲", "家庭户口", "家庭地址",
					"家庭电话", "家庭邮编", "家庭人数", "家庭人均收入", "收入来源", "已获资助情况",
					"家庭欠债情况", "家庭其他情况" };
			colListEN = new String[] { "num", "xmdm", "xmmc", "xh", "xm", "xb",
					"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
					"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2",
					"shzt3", "knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk",
					"jtdz", "jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk",
					"jtqzqk", "jtqtqk" };
		}

		return dao.arrayToList(colListEN, colListCN);
	}

	/**
	 * 获得导出数据列表(中国地大)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getZgddExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'无级别') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================查询条件=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========申请周期必须相同（导出项目与其他信息）=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================家庭信息取最新的一条=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========申请周期必须相同（导出项目与其他信息）=========
		sql.append("学年".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("年度".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("一级审核".equalsIgnoreCase(knshjb)) {// 周期学年
			sql.append(" and g.shzt1 = '通过' ");
		} else if ("两级审核".equalsIgnoreCase(knshjb)) {// 周期年度
			sql.append(" and g.shzt2 = '通过' ");
		} else if ("三级审核".equalsIgnoreCase(knshjb)) {// 周期学期
			sql.append(" and g.shzt3 = '通过' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * 获得导出数据列表(信阳师范)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getXysfExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'无级别') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk from (select a.xmdm,a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,");
		sql.append(" b.zymc, b.bjdm, b.bjmc, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================查询条件=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		// ======================家庭信息取最新的一条=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========申请周期必须相同（导出项目与其他信息）=========
		sql.append("学年".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("年度".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("一级审核".equalsIgnoreCase(knshjb)) {// 周期学年
			sql.append(" and g.shzt1 = '通过' ");
		} else if ("两级审核".equalsIgnoreCase(knshjb)) {// 周期年度
			sql.append(" and g.shzt2 = '通过' ");
		} else if ("三级审核".equalsIgnoreCase(knshjb)) {// 周期学期
			sql.append(" and g.shzt3 = '通过' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk"};
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * 获得导出数据列表(武汉商业服务)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getWhsyExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'无级别') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,b.csrq,b.rxrq,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================查询条件=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========申请周期必须相同（导出项目与其他信息）=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================家庭信息取最新的一条=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========申请周期必须相同（导出项目与其他信息）=========
		sql.append("学年".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("年度".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("一级审核".equalsIgnoreCase(knshjb)) {// 周期学年
			sql.append(" and g.shzt1 = '通过' ");
		} else if ("两级审核".equalsIgnoreCase(knshjb)) {// 周期年度
			sql.append(" and g.shzt2 = '通过' ");
		} else if ("三级审核".equalsIgnoreCase(knshjb)) {// 周期学期
			sql.append(" and g.shzt3 = '通过' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh","csrq", "rxrq", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * 获得导出数据列表(武汉商业服务 国家助学金特殊导出)
	 * 
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getWhsyGjzxjExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t.* from (");
		sql.append(" select distinct a.*,rownum num,nvl(g.xmzzjb,'无级别') knsjb,f.sfgc,f.sfdq,f.lszn,f.sfdb,f.jthk,f.jtdz,");
		sql.append(" f.jtdh,f.jtyb,f.jtrs,f.jtrjsr,f.srly,f.yhzzqk,f.jtqzqk,");
		sql.append(" f.jtqtqk,d.jsjsp,d.wysp,d.sbxqxfjd,d.xbxqxfjd,");
		
		sql.append(" nvl(d.bjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) bjpm,");
		
		sql.append(" nvl(d.zcbjpm, 0) || '/' ||(select e.num from (select");
		sql.append(" count(1) num, bjdm from view_xsjbxx group by bjdm) e");
		sql.append(" where a.bjdm = e.bjdm) zcpm,");
		
		sql.append(" d.kyqk,d.hjrq1,d.hjmc1,d.hjrq2,d.hjmc2,d.hjrq3,");
		sql.append(" d.hjmc3,d.hjrq4,d.hjmc4,d.zjqk from (select a.xmdm,");
		
		sql.append(" a.sqsj,a.shzt1,a.shzt2,a.shzt3,");
		sql.append(" (select c.xmmc from xszz_zzxmb c where a.xmdm = c.xmdm) xmmc,");
		sql.append(" b.xm, a.xh, b.xb, b.nj, b.xydm, b.xymc, b.zydm,a.xn,a.xq,a.nd,b.csrq,b.rxrq,");
		sql.append(" b.zymc, b.bjdm, b.bjmc,b.sfzh, b.mzmc, b.zzmmmc, a.xmzzjb,a.qy,");
		sql.append(" a.xmzzje from ");
		sql.append(xmb);
		sql.append(" a,view_xsxxb b where a.xh = b.xh ");
		
		// ======================查询条件=============================
		sql.append(Base.isNull(xmdm) ? "" : " and a.xmdm = '" + xmdm + "' ");
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
		sql.append(" ) a ");
		
		sql.append(" left join (select a.* from xsqtxxb a, (select xh, Max(sqsj) sqsj from xsqtxxb group by xh) b ");
		sql.append(" where a.sqsj = b.sqsj and a.xh = b.xh) d on a.xh = d.xh ");
//		sql.append(" left join xsqtxxb d on a.xh = d.xh ");
//		sql.append(" and d.sqsj=(select max(sqsj) from xsqtxxb where xh = '"+xh+"')");
		
		// ===========申请周期必须相同（导出项目与其他信息）=========
		/*sql.append(" and a.xn = d.xn ");
		sql.append(" and a.nd = d.nd ");
		sql.append(" and a.xn = d.xn ");
		sql.append(" and a.xq = d.xq ");*/
		// }
		// ======================end=============================
		
		// ======================家庭信息取最新的一条=====================
		sql.append(" left join (select a.* from jtqkdcb a where exists (select 1");
		sql.append(" from (select xh, max(sqsj) sqsj from jtqkdcb group by xh) b");
		sql.append(" where a.xh = b.xh and a.sqsj = b.sqsj)) f on a.xh = f.xh");
		// ======================end=============================

		sql.append(" left join xszz_knsb g on a.xh = g.xh");
		// ===========申请周期必须相同（导出项目与其他信息）=========
		sql.append("学年".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("年度".equalsIgnoreCase(knshzq) ? " and g.nd = a.nd" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xn = a.xn" : "");
		sql.append("学期".equalsIgnoreCase(knshzq) ? " and g.xq = a.xq" : "");
		
		if ("一级审核".equalsIgnoreCase(knshjb)) {// 周期学年
			sql.append(" and g.shzt1 = '通过' ");
		} else if ("两级审核".equalsIgnoreCase(knshjb)) {// 周期年度
			sql.append(" and g.shzt2 = '通过' ");
		} else if ("三级审核".equalsIgnoreCase(knshjb)) {// 周期学期
			sql.append(" and g.shzt3 = '通过' ");
		}
		// ======================end=============================
		
		sql.append(") t order by num");
		//System.out.println(sql.toString());
		String[] outputValue = new String[] { "num","xmdm", "xmmc", "xh", "xm", "xb",
				"nj", "xymc", "zymc", "bjmc", "mzmc", "zzmmmc","sfzh","csrq", "rxrq", "xmzzjb",
				"xmzzje", "xn", "xq", "nd", "sqsj","qy", "shzt1", "shzt2", "shzt3",
				"knsjb", "sfgc", "sfdq", "lszn", "sfdb", "jthk", "jtdz",
				"jtdh", "jtyb", "jtrs", "jtrjsr", "srly", "yhzzqk", "jtqzqk",
				"jtqtqk", "jsjsp", "wysp", "sbxqxfjd", "xbxqxfjd", "bjpm",
				"zcpm", "kyqk", "hjrq1", "hjmc1", "hjrq2", "hjmc2", "hjrq3",
				"hjmc3", "hjrq4", "hjmc4", "zjqk" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * 获取华中农业绿色通道导出信息
	 * @param xh
	 * @return
	 */
	public ArrayList<String[]> getHznyLstdExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select a.* from (");
		sql.append(" select a.*,g.kzzd1,g.kzzd2,g.kzzd3,g.kzzd4,g.bz ");
		sql.append("  from view_xsbfxx a  ");
		sql.append(" ,xszz_comm_zzwsb g where a.xh=g.xh and g.xmdm='"+xmdm+"')a where 1=1 ");
		// ======================查询条件=============================
	
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
	
	
		// ======================end=============================
		
		String[] outputValue = new String[] {"xymc","zymc","xm","kzzd1",
				"kzzd2","kzzd3","kzzd4","sydsmc","bz" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
	
	/**
	 * 获取湖州师范“陆侯燕英”帮困和“迎南树人”的导出信息
	 * @param xh
	 * @return
	 * @author honglin
	 * @date 2013-01-21
	 */
	public ArrayList<String[]> getHzsfLhyyAndYnsrExpList(XszzTyForm model,
			HashMap<String, String> xmInfo) {

		DAO dao = DAO.getInstance();
		// 困难生审核级别
		String knshjb = dao.getOneValue("xszz_zzxmb", "shjb", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 困难生申请周期
		String knshzq = dao.getOneValue("xszz_zzxmb", "sqzq", "xmdm",
				XszzXmdm.XSZZ_KNS);
		// 项目表
		String xmb = xmInfo.get("xmb");
		// 申请周期
		String sqzq = xmInfo.get("sqzq");
		// 学年
		String xn = model.getXn();
		// 学期
		String xq = model.getXq();
		// 年度
		String nd = model.getNd();
		// 年级
		String nj = model.getNj();
		// 学院
		String xydm = model.getXydm();
		// 专业
		String zydm = model.getZydm();
		// 班级
		String bjdm = model.getBjdm();
		// 学号
		String xh = model.getXh();
		// 姓名
		String xm = model.getXm();
		// 开始时间
		String kssj = model.getKssj();
		// 结束时间
		String jssj = model.getJssj();
		// 项目代码
		String xmdm = model.getXmdm();
		// 项目级别
		String xmzzjb = model.getXmzzjb();
		// 审核状态1
		String shzt1 = model.getShzt1();
		// 审核状态2
		String shzt2 = model.getShzt2();
		// 审核状态3
		String shzt3 = model.getShzt3();
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select a.*,(select q.hjrq1||'获得'||q.hjmc1||'颁奖单位'||q.bjdw1||'，'||q.hjrq2||'获得'||q.hjmc2||'颁奖单位'||q.bjdw2||'，'||q.hjrq3||'获得'||q.hjmc3||'颁奖单位'||q.bjdw3||'，'||q.hjrq4||'获得'||q.hjmc4||'颁奖单位'||q.bjdw4 bz from (select t.* from xsqtxxb t order by t.sqsj desc) q where q.xmdm='5005' and q.xh=a.xh and rownum=1) bz,(select k.xmzzjb from (select n.* from xszz_knsb n order by n.sqsj desc) k where k.xh=a.xh and rownum=1) xmzzjb from (");
		sql.append(" select rownum num,a.* ");
		sql.append("from xszz_comm_zzwsb z ");
		sql.append("left join view_xsbfxx a on z.xh=a.xh where z.xmdm='"+xmdm+"') a where 1=1 ");
		// ======================查询条件=============================
	
		if ("xn".equalsIgnoreCase(sqzq)) {// 周期学年
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
		} else if ("nd".equalsIgnoreCase(sqzq)) {// 周期年度
			sql.append(Base.isNull(nd) ? "" : " and a.nd = '" + nd + "' ");
		} else if ("xq".equalsIgnoreCase(sqzq)) {// 周期学期
			sql.append(Base.isNull(xn) ? "" : " and a.xn = '" + xn + "' ");
			sql.append(Base.isNull(xq) ? "" : " and a.xq = '" + xq + "' ");
		}
		sql.append(Base.isNull(kssj) ? "" : " and a.sqsj >= '" + kssj + "'");
		sql.append(Base.isNull(jssj) ? "" : " and a.sqsj <= '" + jssj + "'");

		sql.append(Base.isNull(xmzzjb) ? "" : " and a.xmzzjb = '" + xmzzjb
				+ "' ");
		sql.append(Base.isNull(shzt1) ? "" : " and a.shzt1 = '" + shzt1 + "' ");
		sql.append(Base.isNull(shzt2) ? "" : " and a.shzt2 = '" + shzt2 + "' ");
		sql.append(Base.isNull(shzt3) ? "" : " and a.shzt3 = '" + shzt3 + "' ");
		
		sql.append(Base.isNull(nj) ? "" : " and b.nj = '" + nj + "' ");
		sql.append(Base.isNull(xydm) ? "" : " and b.xydm = '" + xydm + "' ");
		sql.append(Base.isNull(zydm) ? "" : " and b.zydm = '" + zydm + "' ");
		sql.append(Base.isNull(bjdm) ? "" : " and b.bjdm = '" + bjdm + "' ");
		
		sql.append(Base.isNull(xh) ? "" : " and b.xh like '%" + xh + "%' ");
		sql.append(Base.isNull(xm) ? "" : " and b.xm like '%" + xm + "%' ");
		// ======================end=============================
	
	
		// ======================end=============================
		
		String[] outputValue = new String[] {"num","xymc","xm","xb","bjmc","bz","xmzzjb" };
		ArrayList<String[]> list = dao.rsToVator(sql.toString(), new String[]{}, outputValue);
		
		return list;
	}
}