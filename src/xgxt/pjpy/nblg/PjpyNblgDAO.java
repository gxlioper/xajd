
package xgxt.pjpy.nblg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.struts.util.MessageResources;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.String.StringUtils;

/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: 宁波理工学院评奖评优DAO</p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: 李涛</p>
 * <p>Version: 1.0</p>
 * <p>Time: 2008-07-30</p>
 */
public class PjpyNblgDAO {

	MessageResources message = MessageResources
					.getMessageResources("config.ApplicationResources");
			
	DAO dao = DAO.getInstance();//数据操作通用DAO
	ArrayList<String> values = new ArrayList<String>();//查询条件值
	/**
	 * 获取考级考试列表
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksList() throws Exception {
		List<HashMap<String, String>> djksList = new ArrayList<HashMap<String,String>>();
		String sql = "select djksdm,djksmc from djksdmb";
		djksList = dao.getList(sql, new String[]{}, new String[]{"djksdm", "djksmc"});
		return djksList;
	}
	
	/**
	 * 等级考试成绩查询表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDjksCjTitle() throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"rownum" ,"xh", "xm", "xymc", "bjmc", "djksmc", "cj"};
		String[] cn = new String[]{"行号", "学号" ,"姓名", Base.YXPZXY_KEY , "班级", "等级考试", "成绩"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", en[i]);
			tmp.put("cn", cn[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	public List<HashMap<String, String>> getCjbTitle() throws Exception {
		List<HashMap<String, String>> topList = new ArrayList<HashMap<String,String>>();
		String[] en = new String[]{"rownum", "xn", "xq" ,"xh", "xm","xymc", "bjmc", "kcmc", "cj"};
		String[] cn = new String[]{"行号", "学年", "学期", "学号" ,"姓名", Base.YXPZXY_KEY, "班级", "课程", "成绩"};
		for (int i = 0; i < en.length; i++) {
			HashMap<String, String> tmp = new HashMap<String, String>();
			tmp.put("en", en[i]);
			tmp.put("cn", cn[i]);
			topList.add(tmp);
		}
		return topList;
	}
	
	/**
	 * 查询条件值
	 * @param djkscjModel
	 * @return
	 * @throws Exception
	 */
	
	public StringBuffer getWhereSql(DjkscjModel djkscjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
//		String xn = djkscjModel.getXn();
//		String xq = djkscjModel.getXq();
		String djksdm = djkscjModel.getDjksdm();
		String xydm = djkscjModel.getXydm();
		String zydm = djkscjModel.getZydm();
		String bjdm = djkscjModel.getBjdm();
		if (!StringUtils.isNull(djksdm)) {
			whereSql.append(" and djksdm like '%");
			whereSql.append(djksdm);
			whereSql.append("%'");
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and bjdm = ?");
			values.add(bjdm);
		}
		return whereSql;
	}
	
	
	public StringBuffer getWhereSql1(DjkscjModel djkscjModel) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		String xn = djkscjModel.getXn();
		String xq = djkscjModel.getXq();
//		String djksdm = djkscjModel.getDjksdm();
		String xydm = djkscjModel.getXydm();
		String zydm = djkscjModel.getZydm();
		String bjdm = djkscjModel.getBjdm();
		if (!StringUtils.isNull(xn)) {
			whereSql.append(" and a.xn = ?");
			values.add(xn);
		}
		if (!StringUtils.isNull(xq)) {
			whereSql.append(" and a.xq like  '%");
			xq = xq.length()==2 ? xq.substring(1, 2) : xq;
			whereSql.append(xq);
			whereSql.append("%'");
		}
		if (!StringUtils.isNull(xydm)) {
			whereSql.append(" and b.xydm = ?");
			values.add(xydm);
		}
		if (!StringUtils.isNull(zydm)) {
			whereSql.append(" and b.zydm = ?");
			values.add(zydm);
		}
		if (!StringUtils.isNull(bjdm)) {
			whereSql.append(" and b.bjdm = ?");
			values.add(bjdm);
		}
		return whereSql;
	}
	/**
	 * 等级考试成绩查询结果
	 * @param djkscjModel
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getDjksCjResult(DjkscjModel djkscjModel,
			PjpyNblgActionForm dataSearchForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select * from (select rownum r,xn,xq,xh,xm,bjmc,xymc,djksmc,cj from view_xsdjksb where 1=1 ";
		StringBuffer whereSql = getWhereSql(djkscjModel);
		String[] opList = new String[] { "r", "xh", "xm","xymc", "bjmc",
				"djksmc", "cj" };
		resList = dao.rsToVator(sql + whereSql.toString() + ") where r<=" + (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) + " and r> "+dataSearchForm.getPages().getStart(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList;
	}
	
	/**
	 * 等级考试成绩查询结果
	 * @param djkscjModel
	 * @return
	 * @throws Exception
	 */
	public int getDjksCjResultNum(DjkscjModel djkscjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select rownum,xn,xq,xh,xm,bjmc,djksmc,cj from (select a.xn,a.xq,a.xh,a.xm,b.bjmc,a.djksmc,a.cj,b.bjdm,b.zydm,b.xydm,(select b.djksdm from djksdmb b where b.djksmc = a.djksmc) djksdm from xsdjksb a left join view_xsjbxx b on a.xh=b.xh) where 1=1 ";
		StringBuffer whereSql = getWhereSql(djkscjModel);
		String[] opList = new String[] { "rownum", "xh", "xm", "bjmc",
				"djksmc", "cj" };
		resList = dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList.size();
	}

	public List<String[]> getCjResult(DjkscjModel djkscjModel,
			PjpyNblgActionForm dataSearchForm) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select * from (select rownum r,a.xn,a.xq,a.xh,b.xm,b.bjmc,a.kcmc,a.cj,b.xydm,b.zydm,b.bjdm,b.xymc from cjb a left join view_xsjbxx b on a.xh=b.xh where 1=1 ";
		StringBuffer whereSql = getWhereSql1(djkscjModel);
		String[] opList = new String[] { "r", "xn", "xq", "xh", "xm",
				"xymc", "bjmc", "kcmc", "cj" };
		resList = dao.rsToVator(sql + whereSql.toString() + ") where r<=" + (dataSearchForm.getPages().getStart() + dataSearchForm.getPages().getPageSize()) + " and r> "+dataSearchForm.getPages().getStart(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList;
	}
	
	public int getCjResultNum(DjkscjModel djkscjModel) throws Exception {
		List<String[]> resList = new ArrayList<String[]>();
		String sql = "select rownum,a.xn,a.xq,a.xh,b.xm,b.bjmc,a.kcmc,a.cj,b.xydm,b.zydm,b.bjdm from cjb a left join view_xsjbxx b on a.xh=b.xh where 1=1 ";
		StringBuffer whereSql = getWhereSql1(djkscjModel);
		String[] opList = new String[] { "rownum", "xn", "xq", "xh", "xm",
				"bjmc", "kcmc", "cj" };
		resList = dao.rsToVator(sql + whereSql.toString(),
				values != null ? values.toArray(new String[0])
						: new String[] {}, opList);
		return resList.size();
	}
	
	/**
	 * 与教务等级考试同步成绩
	 * @param xn
	 * @param xq
	 * @param djksmc
	 * @throws Exception
	 */
	public boolean djksCjTb(String xn, String xq) throws Exception {
		String sql = "delete from xsdjksb";
		boolean bDel = dao.runUpdate(sql , new String[]{});
		sql = "insert into xsdjksb (xh,djksmc,ksrq,cj) select xh,kskm,ksrq,kscj from v_bks_djks@ORCL@ZFXG";
		if (bDel) {
			return dao.runUpdate(sql, new String[]{});
		} else {
			return false;
		}
	}
	
	/**
	 * 与教务等级考试同步成绩
	 * @param xn
	 * @param xq
	 * @param djksmc
	 * @throws Exception
	 */
	public boolean djksCjTb1(String xn, String xq) throws Exception {
		String sql = "delete from xsdjksb";
		boolean bDel = dao.runUpdate(sql , new String[]{});
		sql = "insert into xsdjksb (xh,djksmc,ksrq,cj) select xh,djksmc,ksrq,cj from xsdjksb@dblink_jw";
		if (bDel) {
			return dao.runUpdate(sql, new String[]{});
		} else {
			return false;
		}
	}
	
	/**
	 * 获取等级考试名称
	 * @param djksdm
	 * @return
	 * @throws Exception
	 */
	public String getDjksmc(String djksdm) throws Exception {
		String sql = "select djksmc from djksdmb where djksdm = ?";
		return dao.getOneRs(sql, new String[]{djksdm}, "djksmc");
	}
	
	/**
	 * 成绩同步
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjbTb(String xn, String xq) throws Exception {
		boolean bDel = dao.runUpdate("delete from cjb where xn=? and xq=?", new String[]{xn, xq});
		if (!StringUtils.isNull(xq) && xq.length() == 2) {
			dao.runUpdate("delete from cjb where xn=? and xq=?", new String[]{xn, xq.substring(1, 2)});
		}
		if (bDel) {
			return dao
					.runUpdate(
							"insert into cjb (xn,xq,xh,kcsbm,kcmc,xf,cj,cxbj,cxcj,bkcj,kcxz,xkkh) select xn,xq,xh,kcmc,kcmc,xf,cj,sfcx,sfcx,bkcj,kcxz,to_char(sysdate,'yyyymmddhh24miss') from v_bks_kscj@ORCL@ZFXG where xn=? and xq=?",
							new String[] { xn, xq });
		}
		return false;
	}
	
	/**
	 * 成绩同步
	 * @param xn
	 * @param xq
	 * @return
	 * @throws Exception
	 */
	public boolean cjbTb1(String xn, String xq) throws Exception {
		boolean bDel = dao.runUpdate("delete from cjb where xn=?", new String[]{xn});
		if (bDel) {
			return dao
					.runUpdate(
							"insert into cjb (xn,xq,xh,kcsbm,kcmc,xf,cj,cxbj,cxcj,bkcj,kcxz,xkkh) select xn,xq,xh,kcmc,kcmc,xf,(case when (select to_char(b.dycj) from cjdzb@dblink_jw b where to_char(b.cj)=to_char(a.cj)) is not null then (select to_char(b.dycj) from cjdzb@dblink_jw b where to_char(b.cj)=to_char(a.cj)) else a.cj end) cj,cxbj,cxcj,bkcj,kcxz,xkkh from cjb@dblink_jw a where xn=?",
							new String[] { xn});
		}
		return false;
	}
	
	public HashMap<String, String> getStuInfo(String xh) throws Exception {
		return dao.getMapNotOut(
				"select xh,xm,xb,csrq,nj,xy xymc,zymc,bjmc from xsxxb where xh=?",
				new String[] { xh });
	}
	
	public List<HashMap<String, String>> tjTitle() throws Exception {
		String[] enList = new String[]{"pk","rownum","xn", "jxjmc", "tj", "bl"};
		String[] cnList = new String[]{"pk","行号","学年", "奖学金", "条件", "排名"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> tjResult(String xn, String jxjdm) throws Exception {
		StringBuffer query = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			query.append(" and xn='");
			query.append(xn);
			query.append("'");
		}
		if (!StringUtils.isNull(jxjdm)) {
			query.append(" and jxjdm='");
			query.append(jxjdm);
			query.append("'");
		}
		String sql = "select xn||jxjdm||tj pk,rownum,xn,(select jxjmc from jxjdmb b where b.jxjdm=a.jxjdm) jxjmc,(case tj when 'jqf' then '学习加权平均分' when 'zhcp' then '综合素质测评分' else '' end) tj,bl||'%' bl from nbzy_tjszb a where 1=1 ";
		String[] opList = new String[]{"pk","rownum","xn", "jxjmc", "tj", "bl"};
		return dao.rsToVator(sql + query.toString(), new String[]{}, opList);
	}
	
	public boolean tjSave(String xn, String jxjdm, String tj, String bl) throws Exception {
		dao.runUpdate("delete from nbzy_tjszb where xn=? and jxjdm=? and tj=?", new String[]{xn,jxjdm,tj});
		return dao.runUpdate("insert into nbzy_tjszb (xn,jxjdm,tj,bl) values (?,?,?,?)", new String[]{xn,jxjdm,tj,bl});
	}
	
	public boolean tjDel(String[] keys, String tname) throws Exception {
		for (int i=0;i<keys.length;i++) {
			dao.runUpdate("delete from "+ tname +" where xn||jxjdm||tj=?", new String[]{keys[i]});
		}
		return true;
	}
	
	public List<HashMap<String, String>> yytjTitle() throws Exception {
		String[] enList = new String[]{"pk","rownum","xn", "jxjmc", "tj", "tj", "fs"};
		String[] cnList = new String[]{"pk","行号","学年", "奖学金", "条件", "比较", "分数"};
		return dao.arrayToList(enList, cnList);
	}
	
	public List<String[]> yytjResult(String xn, String jxjdm) throws Exception {
		StringBuffer query = new StringBuffer();
		if (!StringUtils.isNull(xn)) {
			query.append(" and xn='");
			query.append(xn);
			query.append("'");
		}
		if (!StringUtils.isNull(jxjdm)) {
			query.append(" and jxjdm='");
			query.append(jxjdm);
			query.append("'");
		}
		String sql = "select xn||jxjdm||tj pk,rownum,xn,(select jxjmc from jxjdmb b where b.jxjdm=a.jxjdm) jxjmc,(case tj when 'cet4' then '英语四级' when 'cet6' then '英语六级' when 'pcet4' then '英语专业四级' else '' end) tj,'大于等于' bj,fs from nblg_jxjyytjszb a where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xn", "jxjmc", "tj",
				"bj", "fs" };
		return dao.rsToVator(sql + query.toString(), new String[]{}, opList);
	}
	
	public boolean saveYytj(String xn, String jxjdm, String tj, String fs)
			throws Exception {
		boolean bFlag = dao.runUpdate(
				"delete from nblg_jxjyytjszb where xn||jxjdm||tj=?",
				new String[] { xn + jxjdm + tj });
		if (bFlag) {
			return dao
					.runUpdate(
							"insert into nblg_jxjyytjszb (xn,jxjdm,tj,fs) values (?,?,?,?)",
							new String[] { xn, jxjdm, tj, fs });
		} else {
			return false;
		}
	}
	
	/**
	 * 奖学金审核表头
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getjxjshTitle(String userType) throws Exception {
		Base.YXPZXY_KEY = message.getMessage("lable.xb");
		String[] enList = new String[] { "pk", "dis","rownum", "xh", "xm", "xn",
				"bjmc", "jxjmc", "dycj", "jqcj","jqf", "zhszcpzf","zhcp", "sh"};
		String[] cnList = new String[] { "pk","dis", "行号", "学号", "姓名", "学年", "班级",
				"奖学金名称", "德育测评成绩", "加权成绩","专业排名", "综合测评成绩", "班级排名","学校审核" };
		if ("xy".equalsIgnoreCase(userType)) {
			cnList = new String[] { "pk","dis", "行号", "学号", "姓名", "学年", "班级",
					"奖学金名称", "德育测评成绩", "加权成绩", "专业排名","综合测评成绩","班级排名", Base.YXPZXY_KEY+"审核" };
		}
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 单项奖学金与优秀奖学金兼得条件
	 * @param jxjdm
	 * @return
	 * @throws Exception
	 */
	public String jxjJdtj(String jxjdm, String xn) throws Exception {
		String whereSql = "";
		String dxjxjlb = dao
				.getOneRs(
						"select distinct jxjlbdm from jxjlbdmb where jxjlbmc like '%单项%'",
						new String[] {}, "jxjlbdm");//单项奖学金类别
		dxjxjlb = StringUtils.isNull(dxjxjlb) ? "" : dxjxjlb.trim();
		String yxjxjlb = dao
				.getOneRs(
						"select distinct jxjlbdm from jxjlbdmb where jxjlbmc like '%优秀%'",
						new String[] {}, "jxjlbdm");//优秀奖学金类别
		yxjxjlb = StringUtils.isNull(yxjxjlb) ? "" : yxjxjlb.trim();
		String jxjlb = dao.getOneRs("select jxjlb from jxjdmb where jxjdm=?",
				new String[] { jxjdm }, "jxjlb");
		jxjlb = StringUtils.isNull(jxjlb) ? "" : jxjlb.trim();
		if (dxjxjlb.equalsIgnoreCase(jxjlb)) {
			whereSql = " and not exists(select 1 from view_xsjxjb c where c.xh=a.xh and c.xysh='通过' and c.xn='"
					+ xn + "' and c.jxjlb = '" + yxjxjlb + "')";
		} else if (yxjxjlb.equalsIgnoreCase(jxjlb)) {
			whereSql = " and not exists(select 1 from view_xsjxjb c where c.xh=a.xh and c.xysh='通过' and c.xn='"
					+ xn + "' and c.jxjlb = '" + dxjxjlb + "')";
		}
		return whereSql;
	}
	
	/**
	 * 学院奖学金审核时的查询结果
	 * 奖学金,班级为必选项
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyjxjshResult(PjpyNblgModel model) throws Exception {
		
		String jdtj = jxjJdtj(model.getJxjdm(), model.getXn());//奖学金兼得条件
		List<String[]> tjList = dao.rsToVator(
				"select tj,nvl(bl,0) bl from nbzy_tjszb where xn=? and jxjdm=?",
				new String[] { model.getXn(), model.getJxjdm() }, new String[] {
						"tj", "bl" });//奖学金申请基本条件
		List<String[]> yytjList = dao.rsToVator(
				"select tj,fs from nblg_jxjyytjszb where xn=? and jxjdm=?",
				new String[] { model.getXn(), model.getJxjdm() }, new String[] {
						"tj", "fs" });//奖学金申请英语条件
		String sbjrs = dao.getOneRs("select count(xh) num from bks_xsjbxx where bjdm=?", new String[]{model.getBjdm()}, "num");
		String szyrs = dao.getOneRs("select count(xh) num from bks_xsjbxx where zydm=(select distinct zydm from bks_xsjbxx where bjdm=?)", new String[]{model.getBjdm()}, "num");
		//班级人数,专业人数
		int bjrs = StringUtils.isNull(sbjrs) ? 0 : Integer.parseInt(sbjrs);
		int zyrs = StringUtils.isNull(szyrs) ? 0 : Integer.parseInt(szyrs);
		float bjpm = 0;
		float zypm = 0;
		StringBuffer whereSql = new StringBuffer("");
		if (tjList != null) {//追加基本条件
			for (int i = 0; i < tjList.size(); i++) {
				String[] tmpList = tjList.get(i);
				if ("jqf".equalsIgnoreCase(tmpList[0])) {// 加权分百分比
					zypm = zyrs
							* (StringUtils.isNull(tmpList[1]) ? 1 : Float
									.parseFloat(tmpList[1])) / 100;//加权分比例排名
					whereSql.append(" and a.jqf <= ");
					whereSql.append(Math.round(zypm));
				} else if ("zhcp".equalsIgnoreCase(tmpList[0])) {// 综合测评分百分比
					bjpm = bjrs
							* (StringUtils.isNull(tmpList[1]) ? 1 : Float
									.parseFloat(tmpList[1])) / 100;//综合分比例排名
					whereSql.append(" and a.zhcp <= ");
					whereSql.append(Math.round(bjpm));
				}
			}
		}
		if (yytjList != null) {//追加英语条件
			for (int i=0;i<yytjList.size();i++) {
				String[] tmpList = yytjList.get(i);
				if ("cet4".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and cet4>=");
					whereSql.append(tmpList[1]);
				} else if ("cet6".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and cet6>=");
					whereSql.append(tmpList[1]);
				} else if ("pcet4".equalsIgnoreCase(tmpList[0])) {
					whereSql.append(" and pcet4>=");
					whereSql.append(tmpList[1]);
				}
			}
		}
		List<String[]> rs = new ArrayList<String[]>();
		StringBuffer sql = new StringBuffer("select a.xh||b.xn||b.jxjdm pk,(case when b.xxsh='通过' " +
				"then 'disabled' else '' end) dis,rownum,a.xh,a.xm,'"+model.getXn()+"'xn,a.bjmc," +
						"(select jxjmc from jxjdmb where jxjdm='"+model.getJxjdm()+"') jxjmc,(select jxjlb from jxjdmb where jxjdm='"+model.getJxjdm()+"') jxjlb,b.xysh," +
				"(to_number(a.ddpycj)+to_number(a.xwjscj)+to_number(a.shqjscj)) dycj,a.kcjqf jqcj,a.jqf,a.zhcp,a.zhszcpzf " +
				"from view_nblg_xsxxzhszcp a left join view_xsjxjb b on a.xh=b.xh " +
				"and b.xn=? and b.jxjdm=? where 1=1 and not exists (select 1 from wjcfb c where a.xh=c.xh)" +
				" and not exists (select 1 from cjb c where a.xh=c.xh and (c.bkcj is not null or " +
				"c.cxcj is not null) and c.kcxz like '%必修课%')");
		
		values = new ArrayList<String>();
		values.add(model.getXn());
		values.add(model.getJxjdm());
		if (!StringUtils.isNull(model.getNj())) {
			sql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			sql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			sql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			sql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		String[] opList = new String[] { "pk", "dis", "rownum", "xh", "xm", "xn",
				"bjmc", "jxjmc", "dycj", "jqcj","jqf", "zhszcpzf", "zhcp","xysh"};
		//条件过滤SQL
		rs = dao.rsToVator("select * from (" + sql.toString() + whereSql.toString() + ")" +
				" a where 1=1 and not exists(select 1 from view_xsjxjb c " +
				"where  c.xh = a.xh and c.xn='"+model.getXn()+"' and a.jxjlb=c.jxjlb and c.xysh='通过' " +
						"and c.jxjdm<>'"+model.getJxjdm()+"')" + jdtj , values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return rs;
		
	}
	
	/**
	 * 学校奖学金审核时的查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxjxjshResult(PjpyNblgModel model) throws Exception {
		List<String[]> rs = new ArrayList<String[]>();
		String sql = "select xh||xn||jxjdm pk,'' dis,rownum,xh,xm,xn,bjmc,jxjmc," +
					"(select to_number(b.ddpycj)+to_number(b.xwjscj)+to_number(b.shqjscj)" +
					" from nblg_zhszcp b where a.xn=b.xn and a.xh=b.xh) dycj," +
					"(select b.kcjqf from nblg_zhszcp b where a.xn=b.xn and a.xh=b.xh) " +
					"jqcj,(select b.jqf from view_nblg_xsxxzhszcp b where a.xh=b.xh) jqf,(select b.zhszcpzf from nblg_zhszcp b where a.xn=b.xn and a.xh=b.xh)" +
					" zhszcpzf,(select b.zhcp from view_nblg_xsxxzhszcp b where a.xh=b.xh) zhcp,xxsh from view_xsjxjb a where xysh='通过' ";
		String[] opList = new String[] { "pk", "dis","rownum", "xh", "xm", "xn",
				"bjmc", "jxjmc", "dycj", "jqcj","jqf", "zhszcpzf", "zhcp","xxsh"};
		StringBuffer whereSql = getWhereSql(model);
		rs = dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
		return rs;
	}
	
	public StringBuffer getWhereSql(PjpyNblgModel model) throws Exception {
		values = new ArrayList<String>();
		StringBuffer whereSql = new StringBuffer();
		if (!StringUtils.isNull(model.getXh())) {
			whereSql.append(" and xh = ?");
			values.add(model.getXh());
		}
		if (!StringUtils.isNull(model.getXn())) {
			whereSql.append(" and xn = ?");
			values.add(model.getXn());
		}
		if (!StringUtils.isNull(model.getNj())) {
			whereSql.append(" and nj = ?");
			values.add(model.getNj());
		}
		if (!StringUtils.isNull(model.getXydm())) {
			whereSql.append(" and xydm = ?");
			values.add(model.getXydm());
		}
		if (!StringUtils.isNull(model.getZydm())) {
			whereSql.append(" and zydm = ?");
			values.add(model.getZydm());
		}
		if (!StringUtils.isNull(model.getBjdm())) {
			whereSql.append(" and bjdm = ?");
			values.add(model.getBjdm());
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}
	
	/**
	 * 学院奖学金审核结果
	 * KEYS 中存放的可能是主键,也可能是学号
	 * @param keys
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String xyjxjshResult(String[] keys, String type, PjpyNblgModel model) throws Exception {
		String xn = Base.getJxjsqxn();
		String rs = "";
		String xycb = "";//学院超标
		String xxcb = "";//学校超标
		String names = "";
		String jxjdm = model.getJxjdm();
		String nj = model.getNj();
		type = StringUtils.isNull(type) ? "未审核"
				: ("tg".equalsIgnoreCase(type) ? "通过" : ("btg"
						.equalsIgnoreCase(type) ? "不通过" : "未审核"));
		
 		for (int i=0;i<keys.length;i++) {
			boolean bExists = chkJxjDataExists(keys[i]);//检查数据是否存在
			if (bExists) {//存在就要更新审核状态
				if ("通过".equalsIgnoreCase(type)) {//审核通过
					//学校设置的学院总人数
					String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and xn=? and jxjdm='"+jxjdm+"' and nj=?", new String[]{keys[i],xn,nj}, "jxjrs");
					//学院通过的人数
					String xyrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and xn=? and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?) and xydm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?)", new String[]{xn,keys[i], keys[i]}, "num");
					String zrs = "";
					//奖学金分配方式
					String jxjfpfs = dao.getOneRs("select distinct fpfs from xyfpjxjfs where xn=? and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?)", new String[]{xn, keys[i]}, "fpfs");
					jxjfpfs = StringUtils.isNull(jxjfpfs) ? "" : jxjfpfs.trim();
					String tgrs = "";
					if ("zy".equalsIgnoreCase(jxjfpfs)) {//专业通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and zydm=(select zydm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?) and xn=?", new String[]{keys[i], keys[i], xn}, "num");
						//专业限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn='"+xn+"' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and fpbm=(select zydm from view_xsjxjb where xh||xn||jxjdm = ?) and nj=(select nj from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?)", new String[]{keys[i],keys[i],keys[i],keys[i]}, "rstz");
						names = "专业";
					} else if ("bj".equalsIgnoreCase(jxjfpfs)) {//班级通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and bjdm=(select bjdm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ? and xn = ?)", new String[]{keys[i], keys[i],xn}, "num");
						//班级限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn='"+xn+"' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and fpbm=(select bjdm from view_xsjxjb where xh||xn||jxjdm = ?) and nj=(select nj from xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?)", new String[]{keys[i],keys[i],keys[i],keys[i]}, "rstz");
						names = "班级";
					}
					int tmpxyzrs = StringUtils.isNull(xyzrs) ? 0 : Integer.parseInt(xyzrs.trim());
					int tmpzrs = StringUtils.isNull(zrs) ? 0 : Integer.parseInt(zrs.trim());
					int tmptgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
					int tmpxyrs = StringUtils.isNull(xyrs) ? 0 : Integer.parseInt(xyrs.trim());
					if (tmpxyzrs !=0 && tmpzrs != 0 && tmptgrs >= tmpzrs) {
						xycb += ((i+1) + ",");
						continue;
					} else if (tmpxyzrs !=0 && tmpxyrs != 0 && tmpxyrs>=tmpxyzrs) {
						xxcb += ((i+1) + ",");
						continue;
					} else {
						dao.runUpdate("update xsjxjb set xysh='通过' where xh||xn||jxjdm = ?", new String[]{keys[i]});
					}
				} else {//审核不通过
					boolean bFlag = dao.runUpdate(
							"update xsjxjb set xysh=? where xh||xn||jxjdm=?",
							new String[] { "不通过", keys[i] });
					if (!bFlag) {
						rs += (i+1) + ",";//记录不通过行数
					}
				}
			} else {//不存在就要插入并更新审核状态
				if ("通过".equalsIgnoreCase(type)) {//审核通过插入状态
					//审核通过
					//学校设置的学院总人数
					String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=? and xn=? and jxjdm='"+jxjdm+"' and nj=?", new String[]{model.getXydm(),xn,nj}, "jxjrs");
					//学院通过的人数
					String xyrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and xn=? and jxjdm=? and xydm=?", new String[]{xn,jxjdm, model.getXydm()}, "num");
					String zrs = "";
					//奖学金分配方式
					String jxjfpfs = dao.getOneRs("select distinct fpfs from xyfpjxjfs where xn=? and bmdm=?", new String[]{xn, model.getXydm()}, "fpfs");
					jxjfpfs = StringUtils.isNull(jxjfpfs) ? "" : jxjfpfs.trim();
					String tgrs = "";
					if ("zy".equalsIgnoreCase(jxjfpfs)) {//专业通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and zydm=(select zydm from bks_xsjbxx where xh = ?) and jxjdm=? and xn=?", new String[]{keys[i], jxjdm, xn}, "num");
						//专业限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=? and bmdm=? and fpbm=(select zydm from bks_xsjbxx where xh = ?) and nj=? and jxjdm=?", new String[]{xn, model.getXydm(),keys[i],nj,jxjdm}, "rstz");
						names = "专业";
					} else if ("bj".equalsIgnoreCase(jxjfpfs)) {//班级通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and bjdm=? and jxjdm=? and xn=?", new String[]{model.getXydm(), jxjdm, xn}, "num");
						//班级限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=? and bmdm=? and fpbm=? and nj=? and jxjdm=?", new String[]{xn, model.getXydm(), model.getBjdm(), nj, jxjdm}, "rstz");
						names = "班级";
					}
					int tmpxyzrs = StringUtils.isNull(xyzrs) ? 0 : Integer.parseInt(xyzrs.trim());
					int tmpzrs = StringUtils.isNull(zrs) ? 0 : Integer.parseInt(zrs.trim());
					int tmptgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
					int tmpxyrs = StringUtils.isNull(xyrs) ? 0 : Integer.parseInt(xyrs.trim());
					if (tmpxyzrs !=0 && tmpzrs != 0 && tmptgrs >= tmpzrs) {
						xycb += ((i+1) + ",");
						continue;
					} else if (tmpxyzrs !=0 && tmpxyrs != 0 && tmpxyrs>=tmpxyzrs) {
						xxcb += ((i+1) + ",");
						continue;
					} else {
						dao.runUpdate("insert into xsjxjb (xh,xn,jxjdm,xysh) values (?,?,?,?)", new String[]{keys[i], xn, jxjdm, "通过"});
					}
				} else {//审核不通过插入状态
					dao.runUpdate("insert into xsjxjb (xh,xn,jxjdm,xysh) values (?,?,?,?)", new String[]{keys[i], xn, jxjdm, "不通过"});
				}
			}
		}
 		xycb = StringUtils.isNull(xycb) ? "" : "其中第" + xycb
				+ "条记录审核失败,原因是审核通过人数已超院系设置的" + names + "获奖人数!";
		xxcb = StringUtils.isNull(xxcb) ? "" : "其中第" + xxcb
				+ "条记录审核失败,原因是审核通过人数已超学校设置的获奖人数!";
		if (StringUtils.isNull(xycb) && StringUtils.isNull(xxcb)) {
			return "";
		}
		rs = "操作完成:" + xycb + "\n    " + xxcb;
		return rs;
	}
	
	/**
	 * 检查奖学金数据是否存在
	 * TRUE 存在,FALSE 不存在
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public boolean chkJxjDataExists(String pkValue) throws Exception {
		String num = dao.getOneRs(
				"select count(*) num from xsjxjb where xh||xn||jxjdm=?",
				new String[] { pkValue }, "num");
		if (!StringUtils.isNull(num) && !"0".equalsIgnoreCase(num)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 学校奖学金审核结果
	 * @param keys
	 * @param jxjdm
	 * @param res
	 * @return
	 * @throws Exception
	 */
	public String jxjshByxx(String[] keys, String res, PjpyNblgModel model) throws Exception {
		String rs = "";
		String xxcb = "";
		String xn = Base.getJxjsqxn();
		String nj = model.getNj();
		res = StringUtils.isNull(res) ? "未审核"
				: ("tg".equalsIgnoreCase(res) ? "通过" : ("btg"
						.equalsIgnoreCase(res) ? "不通过" : "未审核"));
		for (int i=0;i<keys.length;i++) {
			if ("通过".equalsIgnoreCase(res)) {//审核通过
				//学校审核通过人数
				String xytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn='"+xn+"' and xydm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from view_xsjxjb where xh||xn||jxjdm = ?) and xxsh='通过'", new String[]{keys[i], keys[i]}, "num");
				//学校设置学院获奖人数
				String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and xn=? and jxjdm=(select jxjdm from view_xsjxjb where xh||xn||jxjdm = ?) and nj=?", new String[]{keys[i],xn,keys[i],nj}, "jxjrs");
				int tmpxytgrs = StringUtils.isNull(xytgrs) ? 0 : Integer.parseInt(xytgrs.trim());
				int tmpxyzrs = StringUtils.isNull(xyzrs) ? 0 : Integer.parseInt(xyzrs.trim());
				if (tmpxyzrs !=0 && tmpxytgrs >= tmpxyzrs) {
					xxcb += (i+1) + ",";
					continue;
				} else {
					dao
							.runUpdate(
									"update xsjxjb set xxsh='通过' where xh||xn||jxjdm = ?",
									new String[] { keys[i] });
				}
				
			} else {//审核不通过
				boolean bFlag = dao.runUpdate(
						"update xsjxjb set xxsh=? where xh||xn||jxjdm=?",
						new String[] { res, keys[i] });
				if (!bFlag) {
					rs += (i+1) + ",";//记录不通过行数
				}
			}
		}
		xxcb = StringUtils.isNull(xxcb) ? "" : "操作完成,其中第" + xxcb
				+ "条记录审核失败,原因是审核通过人数已超学校设置的获奖人数!";
		rs = xxcb;
		return rs;
	}
	
	/**
	 * 通过班级奖学金限制人数
	 * 
	 * @param jxjdm
	 * @param xydm
	 * @param bjdm
	 * @param nj
	 * @return
	 * @throws Exception
	 */
	public String getJxjxzrs(String jxjdm, String bjdm) throws Exception {
		String rs = dao
				.getOneRs(
						"select rstz from xyjxjfpb where xn=? and bmdm=(select distinct bmdm from bks_xsjbxx where bjdm=?) and fpbm=(select distinct zydm from bks_xsjbxx where bjdm=?) and nj=(select distinct nj from bks_xsjbxx where bjdm=?) and jxjdm=?",
						new String[] { Base.getJxjsqxn(), bjdm, bjdm, bjdm,
								jxjdm }, "rstz");
		if (StringUtils.isNull(rs)) {
			rs = dao
					.getOneRs(
							"select rstz from xyjxjfpb where xn=? and bmdm=(select distinct bmdm from bks_xsjbxx where bjdm=?) and fpbm=? and nj=(select distinct nj from bks_xsjbxx where bjdm=?) and jxjdm=?",
							new String[] { Base.getJxjsqxn(), bjdm, bjdm, bjdm,
									jxjdm }, "rstz");
		}
		return StringUtils.isNull(rs) ? "未设置" : rs.trim() + " 人";
	} 
}


