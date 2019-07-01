
package xgxt.pjpy.shcbys.jxj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.String.StringUtils;

public class JxjDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = new ArrayList<String>();//查询结果及值
	/**
	 * 查询表头
	 * @param enList
	 * @param cnList
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getTitleResult(String[] enList,
			String[] cnList) throws Exception {
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 奖学金查询条件值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql(JxjModel model) throws Exception {
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
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}
	
	/**
	 * 奖学金查询条件值
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public StringBuffer getWhereSql1(JxjModel model, String userName) throws Exception {
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
		if (!StringUtils.isNull(model.getXm())) {
			whereSql.append(" and xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (!StringUtils.isNull(model.getXq())) {
			whereSql.append(" and xq = ?");
			values.add(model.getXq());
		}
		if (!StringUtils.isNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}
	
	/**
	 * 考勤信息查询结果
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> kqxxResult(JxjModel model) throws Exception {
		
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select xh||xn||xq pk,rownum,xn,xq,xh,xm,xb,nj,bjmc,kkcs,zdcs,ztcs " +
				"from view_pjpy_kqb where 1=1 ";
		String[] opList = new String[] { "pk", "rownum", "xn", "xq", "xh","xm", "xb",
				"nj", "bjmc", "kkcs", "zdcs", "ztcs" };
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 考勤详细信息
	 * @param pkValue
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> kqxxDetails(String pkValue) throws Exception {
		
		return dao.getMapNotOut("select xn,(select b.xqmc from xqdzb b where b.xqdm=view_pjpy_kqb.xq) xq,xh,xm,xb,nj,xymc,zymc,bjmc,kkcs,zdcs,ztcs," +
				"bz from view_pjpy_kqb where xh||xn||xq = ?", new String[]{pkValue});
	}
	
	/**
	 * 考试修改
	 * @param pkValue
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean kqxxUpdate(String pkValue, JxjModel model,
			HttpServletRequest request) throws Exception {
		return StandardOperation.update("pjpy_kqb", new String[] { "kkcs",
				"zdcs", "ztcs", "bz" }, new String[] { model.getKkcs(),
				model.getZdcs(), model.getZtcs(),
				DealString.toGBK(model.getBz()) }, "xh||xn||xq", pkValue, request);
	}
	
	/**
	 * 考勤信息批量删除
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String kqxxDel(String[] keys, HttpServletRequest request) throws Exception {
		String del = "";//删除未成功记录数
		for (int i = 0; i < keys.length; i++) {
			String whichpk = keys[i];// 得到主键
			boolean bFlag = StandardOperation.delete("pjpy_kqb", "xh||xn||xq", whichpk, request);
			if (!bFlag){//删除不成功
				del += (i+1);
				del += ",";
			}
		}
		return StringUtils.isNull(del) ? "" : del.substring(0, del.length() - 1);
	}
	
	/**
	 * 学生详细信息
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getStuDetails(String xh) throws Exception { 
		HashMap<String, String> rs = new HashMap<String, String>();
		rs = dao.getMapNotOut("select xh,xm,xb,nj,xymc,zymc,bjmc,zzmmmc zzmm,mzmc mz,csrq,syd" +
				" from view_xsxxb where xh=?", new String[]{xh});
		
		return rs;
	}
	
	/**
	 * 学期名称
	 * @param xqdm
	 * @return
	 * @throws Exception
	 */
	public String getXqmc(String xqdm) throws Exception {
		return dao.getOneRs("select xqmc from xqdzb where xqdm=?",
				new String[] { xqdm }, "xqmc");
	}
	
	/**
	 * 学生成绩过渡
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(String xh) throws Exception {
		String sql = "select xn,xq,kcmc,kcxz,cj,bkcj from view_cjb where xh=? and xn=? and xq=?";
		return dao.rsToVator(sql, new String[] { xh, Base.getJxjsqxn(),
				Base.getJxjsqxq() }, new String[] { "xn", "xq", "kcmc", "kcxz","cj","bkcj" });
	}
	
	/**
	 * 学生成绩过渡
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList(HashMap<String, String> map) throws Exception {
		StringBuilder sql = new StringBuilder("select xn,xq,kcmc,kcxz,cj,bkcj from view_cjb where xh=?");
		values = new ArrayList<String>();
		values.add(map.get("xh"));
		if (!map.isEmpty()) {
			if (StringUtils.isNotNull(map.get("xn"))) {
				sql.append(" and xn=?");
				values.add(map.get("xn"));
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				values.add(map.get("xq"));
				sql.append(" and xq=?");
			}
		}
		return dao.rsToVator(sql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] { map.get("xh") },
				new String[] { "xn", "xq", "kcmc", "kcxz", "cj", "bkcj" });
	}
	
	
	/**
	 * 学生成绩过渡浙江理工
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjzjlgList(String xh) throws Exception {
		String sql = "select xn,xq,kcmc,kcxz,cj,bkcj from view_cjb where xh=? and xn=?";
		return dao.rsToVator(sql, new String[] { xh, Base.getJxjsqxn(),},
				new String[] { "xn", "xq", "kcmc", "kcxz","cj","bkcj" });
	}
	
	/**
	 * 学生成绩学分
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjListByxf(String xh) throws Exception {
		String sql = "select xn,xq,kcmc,kclx,zpcj2,bkcj from view_cjb where xh=? and xn=? and xq=?";
		return dao.rsToVator(sql, new String[] { xh, Base.getJxjsqxn(),
				Base.getJxjsqxq() }, new String[] { "xn", "xq", "kcmc", "kclx","zpcj2","bkcj" });
	}
	
	/**
	 * 学生成绩学分
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjListByxf(HashMap<String, String> map) throws Exception {
		StringBuilder sql = new StringBuilder("select xn,xq,kcmc,kclx,zpcj2,bkcj from view_cjb where xh=?");
		values = new ArrayList<String>();
		values.add(map.get("xh"));
		if (!map.isEmpty()) {
			if (StringUtils.isNotNull(map.get("xn"))) {
				sql.append(" and xn=?");
				values.add(map.get("xn"));
			}
			if (StringUtils.isNotNull(map.get("xq"))) {
				values.add(map.get("xq"));
				sql.append(" and xq=?");
			}
		}
		return dao.rsToVator(sql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] { map.get("xh") },
				new String[] { "xn", "xq", "kcmc", "kclx", "zpcj2", "bkcj" });
	}
	
	public String getJwFlag() {
		return dao.getOneRs("select jwflag from xtszb", new String[]{}, "jwflag");
	}
	
	public HashMap<String, String> getJd(String xh) throws Exception {
		String sql = "select distinct pjxfjd,mc1 from view_xsxqpmjgb where xh=? and xn=? and xq=?";
		return dao.getMapNotOut(sql, new String[]{xh, Base.getJxjsqxn(), Base.getJxjsqxq()});
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList1(String pkValue) throws Exception {
		String sql = "select xn,xq,kcmc,cj from view_cjb where xh=(select xh from view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=?";
		return dao.rsToVator(sql, new String[] { pkValue, Base.getJxjsqxn(),
				Base.getJxjsqxq() }, new String[] { "xn", "xq", "kcmc", "cj" });
	}
	
	/**
	 * 学生成绩
	 * @param xh
	 * @return
	 * @throws Exception
	 */
	public List<String[]> getCjList2(String xh, String pkValue) throws Exception {
		String sql = "";
		if (!StringUtils.isNull(xh)) {
			sql = "select xn,xq,kcmc,cj from view_cjb where xh=? and xn=? and xq=?";
			pkValue = xh;
		} else {
			sql = "select xn,xq,kcmc,cj from view_cjb where xh=(select xh from view_xsjxjb where xn||nd||jxjdm||xh=?) and xn=? and xq=?";
		}
		
		return dao.rsToVator(sql, new String[] { pkValue, Base.getJxjsqxn(),
				Base.getJxjsqxq() }, new String[] { "xn", "xq", "kcmc", "cj" });
	}
	
	/**
	 * 学年学期内学生是否有成绩不及格
	 * @param xh
	 * @return true 无, false有
	 * @throws Exception
	 */
	public boolean chkStucj(String xh) throws Exception {
		boolean bFlag = true;
		String num = dao
				.getOneRs(
						"select count(*) num from view_cjb where xh=? and xn=? and xq=?" +
						" and to_number(cj)<60",
						new String[] {xh, Base.getJxjsqxn(), Base.getJxjsqxq()}, "num");
		if (!StringUtils.isNull(num) && !StringUtils.isEqual("0", num)) {//不及格成绩
			bFlag = false;
		}
		return bFlag;
	}
	
	/**
	 * 学年学期内旷课,迟到,早退是否有三次以上
	 * @param xh
	 * @return true 无  flase 有
	 * @throws Exception
	 */
	public boolean chkKqxx(String xh) throws Exception {
		boolean bFlag = true;
		String[] kkList = dao.getOneRs("select kkcs,ztcs,zdcs from pjpy_kqb where xh=? and xn=? " +
				"and xq=?", new String[]{xh, Base.getJxjsqxn(), Base.getJxjsqxq()}, new String[]{"kkcs", "ztcs", "zdcs"});
		int kk = 0;
		int cd= 0;
		int zt = 0;
		if (kkList != null && kkList.length==3) {
			kk = StringUtils.isNull(kkList[0]) ? 0 : Integer.parseInt(kkList[0].trim());
			zt = StringUtils.isNull(kkList[1]) ? 0 : Integer.parseInt(kkList[1].trim());
			cd = StringUtils.isNull(kkList[2]) ? 0 : Integer.parseInt(kkList[2].trim());
		}
		int tmp = kk + cd + zt;
		if (tmp > 3) {//旷课,迟到,早退达三次以上
			bFlag = false;
		}
		return bFlag;
	}
	
	/**
	 * 是否有缓考成绩
	 * @param xh
	 * @return false 有, true 否
	 * @throws Exception
	 */
	public boolean chkCjhk(String xh) throws Exception {
		boolean bFlag = true;
		String num = dao.getOneRs("select count(*) num from (select * from view_cjb " +
				"where xh=? and xn=? and xq=?) where bkcj is not null or cxcj is not " +
				"null or cxbj is not null",
				new String[]{xh, Base.getJxjsqxn(), Base.getJxjsqxq()}, "num");
		int tmp = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
		if (tmp > 0) {//有缓考成绩
			bFlag = false;
		}
		return bFlag;
	}
	
	/**
	 * 学期绩点是否符合申请该奖学金的绩点条件
	 * @param jd
	 * @param jxjdm
	 * @return true 符合 false不符合
	 * @throws Exception
	 */
	public boolean chkJd(String jd, String jxjdm){
		boolean bFlag = false;
		String jdbz = dao.getOneRs("select szjdbz from jxjdmb where jxjdm=?",
				new String[] { jxjdm }, "szjdbz");
		double iJd = StringUtils.isNull(jd) ? 0 : Double.parseDouble(jd);//学生学期绩点
		double iBz = StringUtils.isNull(jdbz) ? 0 : Double.parseDouble(jdbz);//奖学金绩点标准
		if (iJd >= iBz) {//符合条件
			bFlag = true;
		}
		return bFlag;
	}
	
	/**
	 * 保存奖学金信息
	 * @param model
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public boolean jxjsqSave(JxjModel model, HttpServletRequest request) throws Exception {
		return StandardOperation.insert("xsjxjb", new String[] { "xh", "xn",
				"nd", "xq", "jxjdm", "drzw", "jd", "sqly" }, new String[] {
				model.getXh(), Base.getJxjsqxn(), Base.getJxjsqnd(), Base.getJxjsqxq(),
				model.getJxjdm(), DealString.toGBK(model.getDrzw()),
				model.getJd(), DealString.toGBK(model.getSqly()) }, request);
	}
	
	/**
	 * 学院奖学金审核查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xyjxjshQryRes(JxjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String zt = model.getBz();
		if (!StringUtils.isNull(zt)) {
			if ("0".equalsIgnoreCase(zt)) {
				zt = " and xysh='未审核'";
			} else if ("1".equalsIgnoreCase(zt)) {
				zt = " and xysh='不通过'";
			} else if ("2".equalsIgnoreCase(zt)) {
				zt = " and xysh='通过'";
			} else 
				zt = "";
		} else {
			zt = "";
		}
		String sql = "select (case when xxsh='通过' then 'disabled' else '' end) flag,(case nvl(xysh,'未审核') when '通过' then '#FFFFFF' " +
				"when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,xh||jxjdm||xn||xq" +
				" pk,rownum,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,xm," +
				"xb,bjmc,jd,jxjmc,xysh,(select mc1 from view_xsxqpmjgb b where view_xsjxjb.xh=b.xh and view_xsjxjb.xn=b.xn and view_xsjxjb.xq=b.xq) mc1 from view_xsjxjb where fdysh='通过' ";
		String[] opList = new String[] { "flag","bgcolor","pk","rownum", "xn", "xq", "xm", "xb",
				"bjmc", "jd","mc1", "jxjmc", "xysh" };
		return dao.rsToVator(sql + whereSql.toString() + zt, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 学院奖学金审核查询结果
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> xxjxjshQryRes(JxjModel model) throws Exception {
		String zt = model.getBz();
		if (!StringUtils.isNull(zt)) {
			if ("0".equalsIgnoreCase(zt)) {
				zt = " and xxsh='未审核'";
			} else if ("1".equalsIgnoreCase(zt)) {
				zt = " and xxsh='不通过'";
			} else if ("2".equalsIgnoreCase(zt)) {
				zt = " and xxsh='通过'";
			} else 
				zt = "";
		} else {
			zt = "";
		}
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select (case nvl(xxsh,'未审核') when '通过' then '#FFFFFF' " +
				"when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,xh||jxjdm||xn||xq" +
				" pk,rownum,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,xm," +
				"xb,bjmc,jd,jxjmc,xxsh,(select mc1 from view_xsxqpmjgb b where view_xsjxjb.xh=b.xh and view_xsjxjb.xn=b.xn and view_xsjxjb.xq=b.xq) mc1 from view_xsjxjb where 1=1 and xysh='通过' and fdysh='通过' ";
		String[] opList = new String[] { "bgcolor","pk", "rownum","xn", "xq", "xm", "xb",
				"bjmc", "jd","mc1", "jxjmc", "xxsh" };
		return dao.rsToVator(sql + whereSql.toString() + zt, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 辅导员审核
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public List<String[]> fdyjxjshQryRes(JxjModel model, String userName) throws Exception {
		String zt = model.getBz();
		if (!StringUtils.isNull(zt)) {
			if ("0".equalsIgnoreCase(zt)) {
				zt = " and fdysh='未审核'";
			} else if ("1".equalsIgnoreCase(zt)) {
				zt = " and fdysh='不通过'";
			} else if ("2".equalsIgnoreCase(zt)) {
				zt = " and fdysh='通过'";
			} else 
				zt = "";
		} else {
			zt = "";
		}
		StringBuffer whereSql = getWhereSql1(model, userName);
		String sql = "select (case when xxsh='通过' or xysh='通过' then 'disabled' else '' end) flag,(case nvl(fdysh,'未审核') when '通过' then '#FFFFFF' " +
				"when '未审核' then '#DDDDDD' else '#BBBBBB' end) bgcolor,xh||jxjdm||xn||xq" +
				" pk,rownum,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,xm," +
				"xb,bjmc,jd,jxjmc,fdysh,(select mc1 from view_xsxqpmjgb b where view_xsjxjb.xh=b.xh and view_xsjxjb.xn=b.xn and view_xsjxjb.xq=b.xq) mc1 from view_xsjxjb where 1=1 ";
		String[] opList = new String[] { "flag","bgcolor","pk","rownum", "xn", "xq", "xm", "xb",
				"bjmc", "jd", "mc1", "jxjmc", "fdysh" };
		return dao.rsToVator(sql + whereSql.toString() + "and exists(select 1 from view_fdybbj d where view_xsjxjb.bjdm=d.bjdm and d.zgh='"+userName+"') " + zt, values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 学院审核奖学金结果(兼得检查,人数检查)
	 * @param tg
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xyjxjshRes(String tg, String[] keys, HttpServletRequest request ) throws Exception {
		String jg = "";
		String jd = "";
		String rs = "";
		tg = "tg".equalsIgnoreCase(tg) ? "通过" : ("btg".equalsIgnoreCase(tg) ? "不通过" : "未审核");
		if (StringUtils.isEqual("通过", tg)) {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
						" and bmlb='zydm' and bmdm=(select distinct zydm from view_xsjxjb where " +
						"xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqnd(), whichpk, whichpk,whichpk}, "jxjrs");//奖学金限制人数
				String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
						" and xq=? and xysh='通过' and zydm=(select distinct zydm from view_xsjxjb " +
						"where xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqxq(), whichpk, whichpk, whichpk}, "num");//专业已审核通地的人数
				//String num = "";
//					dao.getOneRs(
//						"select count(*) num from view_xsjxjb where xh = (select xh from " +
//						"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and xysh='通过' and " +
//						"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//						new String[] {whichpk, Base.getJxjsqxn(), Base.getJxjsqxq(), whichpk}, "num");//奖学金兼得
				int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
				//int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
				int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//				if (tmpk > 0) {
//					jd += (i+1) + ",";//有兼得其它类奖学金
//					continue;
//				}
				if ((tmp != 0) && (tmpkh >= tmp)) {
					rs += (i+1) + ",";//超限制人数
					continue;
				}
				StandardOperation.update("xsjxjb", new String[] { "xysh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}
		} else {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				StandardOperation.update("xsjxjb", new String[] { "xysh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}	
		}
//		if (!StringUtils.isNull(jd)) {
//			jd = "其中第" + jd + "条数据中该学生有兼得其它类奖学金,操作失败!\n";
//		}
		if (!StringUtils.isNull(rs)) {
			rs = "其中第" + rs + "条数据操作失败,审核通过人已超过奖学金专业设置人数!";
		}
		jg = jd + rs;
		return jg;
	}
	
	/**
	 * 辅导员审核奖学金结果(兼得检查,人数检查)批量审核
	 * @param tg
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String fdyjxjshRes(String tg, String[] keys, HttpServletRequest request, JxjModel model ) throws Exception {
		String jg = "";
		String jd = "";
		String rs = "";
		String jxjdm = model.getJxjdm();
		jxjdm = StringUtils.isNull(jxjdm) ? "" : jxjdm.trim();
		String jxjdmStr = Base.initProperties.get("shcbysjxjdm");
		jxjdmStr = StringUtils.isNull(jxjdmStr) ? "" : jxjdmStr;
		String[] jxjdmList = new String[4];
		if (jxjdmStr!="" && jxjdmStr.length()>0) {
			jxjdmList = jxjdmStr.split(",");
		}
		tg = "tg".equalsIgnoreCase(tg) ? "通过" : ("btg".equalsIgnoreCase(tg) ? "不通过" : "未审核");
		String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
				" and bmlb='zydm' and bmdm=? and jxjdm=? and nj=?", new String[]{Base.getJxjsqxn(), 
				Base.getJxjsqnd(), model.getZydm(), model.getJxjdm(), model.getNj()}, "jxjrs");//奖学金限制人数
		int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
		if (StringUtils.isEqual("通过", tg)) {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
						" and xq=? and fdysh='通过' and zydm=? and jxjdm=? and nj=?", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqxq(), model.getZydm(), model.getJxjdm(), model.getNj()}, "num");//专业已审核通地的人数
//				String num = dao.getOneRs(
//						"select count(*) num from view_xsjxjb where xh = (select xh from " +
//						"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and fdysh='通过' and " +
//						"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//						new String[] {whichpk, Base.getJxjsqxn(), Base.getJxjsqxq(), whichpk}, "num");//奖学金兼得
				
			//	int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
				int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//				if (tmpk > 0) {
//					jd += (i+1) + ",";//有兼得其它类奖学金
//					continue;
//				}
				if ((tmp != 0) && (tmpkh >= tmp)) {
					if (jxjdm.equalsIgnoreCase(jxjdmList[0])
							|| jxjdm.equalsIgnoreCase(jxjdmList[1])
							|| jxjdm.equalsIgnoreCase(jxjdmList[2])) {
						if (jxjdm.equalsIgnoreCase(jxjdmList[0])) {
							dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[1],whichpk});
						} else if (jxjdm.equalsIgnoreCase(jxjdmList[1])) {
							dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[2],whichpk});
						} else {
							dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[3],whichpk});
						}
						
						jd += (i+1) + ",";//超限制人数
						
					} else {
						rs += (i+1) + ",";//超限制人数
					}
					continue;
				}
				StandardOperation.update("xsjxjb", new String[] { "fdysh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}
		} else {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				StandardOperation.update("xsjxjb", new String[] { "fdysh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}	
		}
//		if (!StringUtils.isNull(jd)) {
//			jd = "其中第" + jd + "条数据中该学生有兼得其它类奖学金,操作失败!\n";
//		}
		if (!StringUtils.isNull(rs)) {
			rs = "其中第" + rs + "条数据操作失败,审核通过人数已超过奖学金专业设置人数!";
			jg = rs;
		}
		if (!StringUtils.isNull(jd)) {
			jd = "其中第" + jd + "条数据审核失败,审核通过人数已超过奖学金专业设置人数!\n超过人数将划分到下一等级奖学金名单中.";
			jg = jd;
		}
		return jg;
	}
	
	/**
	 * 学校审核奖学金结果
	 * @param tg
	 * @param keys
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String xxjxjshRes(String tg, String[] keys, HttpServletRequest request ) throws Exception {
		String jg = "";
		String jd = "";
		String rs = "";
		tg = "tg".equalsIgnoreCase(tg) ? "通过" : ("btg".equalsIgnoreCase(tg) ? "不通过" : "未审核");
		if (StringUtils.isEqual("通过", tg)) {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
						" and bmlb='zydm' and bmdm=(select distinct zydm from view_xsjxjb where " +
						"xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?) ", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqnd(), whichpk, whichpk, whichpk}, "jxjrs");//奖学金限制人数
				String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
						" and xq=? and xxsh='通过' and zydm=(select distinct zydm from view_xsjxjb " +
						"where xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?) ", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqxq(), whichpk, whichpk, whichpk}, "num");//专业已审核通地的人数
//				String num = dao.getOneRs(
//						"select count(*) num from view_xsjxjb where xh = (select xh from " +
//						"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and xxsh='通过' and " +
//						"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//						new String[] {whichpk, Base.getJxjsqxn(), Base.getJxjsqxq(), whichpk}, "num");//奖学金兼得
				int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
				//int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
				int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//				if (tmpk > 0) {
//					jd += (i+1) + ",";//有兼得其它类奖学金
//					continue;
//				}
				if ((tmp != 0) && (tmpkh >= tmp)) {
					rs += (i+1) + ",";//超限制人数
					continue;
				}
				StandardOperation.update("xsjxjb", new String[] { "xxsh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}
		} else {
			for (int i=0;i<keys.length;i++) {
				String whichpk = keys[i];
				StandardOperation.update("xsjxjb", new String[] { "xxsh" },
						new String[] { tg }, "xh||jxjdm||xn||xq", whichpk, request);
			}	
		}
//		if (!StringUtils.isNull(jd)) {
//			jd = "其中第" + jd + "条数据中该学生有兼得其它类奖学金,操作失败!\n";
//		}
		if (!StringUtils.isNull(rs)) {
			rs = "其中第" + rs + "条数据操作失败,审核通过人已超过奖学金专业设置人数!";
		}
		jg = jd + rs;
		return jg;
	}
	
	/**
	 * 学院单个审核奖学金页面
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> xyjxjshDetails(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xb,xm,nj,xymc,zymc,bjmc,xxsh,xysh yesNo,xyshyj yj," +
						"fdyyj,jd,sqly,jxjmc,drzw,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,nd from view_xsjxjb where xh||jxjdm||xn||xq=?",
						new String[] { pkValue });
	}
	
	/**
	 * 辅导员单个审核奖学金页面
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> fdyjxjshDetails(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xb,xm,nj,xymc,jxjdm,zymc,bjmc,xxsh,xysh,fdysh yesNo," +
						"fdyyj yj,jd,sqly,jxjmc,drzw,(select mc1 from view_xsxqpmjgb b where view_xsjxjb.xh=b.xh and view_xsjxjb.xn=b.xn and view_xsjxjb.xq=b.xq) mc1,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,nd from view_xsjxjb where xh||jxjdm||xn||xq=?",
						new String[] { pkValue });
	}
	
	/**
	 * 学院单个审核奖学金页面
	 * @param pkValue
	 * @return
	 */
	public HashMap<String, String> xxjxjshDetails(String pkValue) {
		return dao
				.getMapNotOut(
						"select xh,xb,xm,nj,xymc,zymc,bjmc,xxsh,xxsh yesNo,xxshyj yj," +
						"fdyyj,jd,sqly,jxjmc,drzw,(select mc1 from view_xsxqpmjgb b where view_xsjxjb.xh=b.xh and view_xsjxjb.xn=b.xn and view_xsjxjb.xq=b.xq) mc1,xn,(select b.xqmc from xqdzb b where b.xqdm=view_xsjxjb.xq) xq,nd from view_xsjxjb where xh||jxjdm||xn||xq=?",
						new String[] { pkValue });
	}
	
	/**
	 * 奖学金单个审核
	 * @param model
	 * @param pkValue
	 * @param userType
	 * @return
	 * @throws Exception
	 */
	public String jxjshoneRes(JxjModel model,String pkValue, String userType) throws Exception {
		if (StringUtils.isEqual("xy", userType) ) {
			if (!StringUtils.isNull(model.getYesNo()) && StringUtils.isEqual("通过", DealString.toGBK(model.getYesNo()))) {
				String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
						" and bmlb='zydm' and bmdm=(select distinct zydm from view_xsjxjb where " +
						"xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?) ", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqnd(), pkValue, pkValue,pkValue}, "jxjrs");//奖学金限制人数
				String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
						" and xq=? and xysh='通过' and zydm=(select distinct zydm from view_xsjxjb " +
						"where xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqxq(), pkValue, pkValue,pkValue}, "num");//专业已审核通地的人数
//				String num = dao.getOneRs(
//						"select count(*) num from view_xsjxjb where xh = (select xh from " +
//						"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and xysh='通过' and " +
//						"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//						new String[] {pkValue, Base.getJxjsqxn(), Base.getJxjsqxq(), pkValue}, "num");//奖学金兼得
				int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
				//int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
				int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//				if (tmpk > 0) {
//					return "操作失败,该生已获得其它类别奖学金不能兼得!";//违返兼得
//				}
				if ((tmp != 0) && (tmpkh >= tmp)) {
					return "操作失败,奖学金审核通过人数已超专业设置限制人数!";//超限制人数
				}
				boolean bFlag = dao.runUpdate("update xsjxjb set xysh=?,xyshyj=? " +
						"where xh||jxjdm||xn||xq=?", new String[]{
						DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), 
						pkValue});
				if (bFlag) {
					return "";
				} else {
					return "操作失败";
				}
			} else {
				boolean bFlag = dao.runUpdate("update xsjxjb set xysh=?,xyshyj=? " +
						"where xh||jxjdm||xn||xq=?", new String[]{
						DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), 
						pkValue});
				if (bFlag) {
					return "";
				} else {
					return "操作失败";
				}
			}
		} else {
			if (!StringUtils.isNull(model.getYesNo()) && StringUtils.isEqual("通过", DealString.toGBK(model.getYesNo()))) {

				String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
						" and bmlb='zydm' and bmdm=(select distinct zydm from view_xsjxjb where " +
						"xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqnd(), pkValue, pkValue,pkValue}, "jxjrs");//奖学金限制人数
				String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
						" and xq=? and xxsh='通过' and zydm=(select distinct zydm from view_xsjxjb " +
						"where xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
						Base.getJxjsqxq(), pkValue, pkValue,pkValue}, "num");//专业已审核通地的人数
//				String num = dao.getOneRs(
//						"select count(*) num from view_xsjxjb where xh = (select xh from " +
//						"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and xxsh='通过' and " +
//						"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//						new String[] {pkValue, Base.getJxjsqxn(), Base.getJxjsqxq(), pkValue}, "num");//奖学金兼得
				int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
				//int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
				int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//				if (tmpk > 0) {
//					return "操作失败,该生已获得其它类别奖学金不能兼得!";//违返兼得
//				}
				if ((tmp != 0) && (tmpkh >= tmp)) {
					return "操作失败,奖学金审核通过人数已超专业设置限制人数!";//超限制人数
				}
				boolean bFlag = dao.runUpdate("update xsjxjb set xxsh=?,xxshyj=? " +
						"where xh||jxjdm||xn||xq=?", new String[]{
						DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), pkValue});
				if (bFlag) {
					return "";
				} else {
					return "操作失败";
				}
			
			} else {
				boolean bFlag = dao.runUpdate("update xsjxjb set xxsh=?,xxshyj=? " +
						"where xh||jxjdm||xn||xq=?", new String[]{
						DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), pkValue});
				if (bFlag) {
					return "";
				} else {
					return "操作失败";
				}
			}
		}
	}
	
	public String fdyjxjshoneRes(JxjModel model,String pkValue, String userType) throws Exception {
		String jxjdm = model.getJxjdm();
		jxjdm = StringUtils.isNull(jxjdm) ? "" : jxjdm.trim();
		String jxjdmStr = Base.initProperties.get("shcbysjxjdm");
		jxjdmStr = StringUtils.isNull(jxjdmStr) ? "" : jxjdmStr;
		String[] jxjdmList = new String[4];
		if (jxjdmStr!="" && jxjdmStr.length()>0) {
			jxjdmList = jxjdmStr.split(",");
		}
		if (!StringUtils.isNull(model.getYesNo())
				&& StringUtils
						.isEqual("通过", DealString.toGBK(model.getYesNo()))) {
			String xzrs = dao.getOneRs("select jxjrs from xyjxjrs where xn=? and nd=?" +
					" and bmlb='zydm' and bmdm=(select distinct zydm from view_xsjxjb where " +
					"xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
					Base.getJxjsqnd(), pkValue, pkValue, pkValue}, "jxjrs");//奖学金限制人数
			String zytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn=?" +
					" and xq=? and fdysh='通过' and zydm=(select distinct zydm from view_xsjxjb " +
					"where xh||jxjdm||xn||xq=?) and jxjdm=(select jxjdm from view_xsjxjb where xh||jxjdm||xn||xq=?) and nj=(select nj from view_xsjxjb where xh||jxjdm||xn||xq=?)", new String[]{Base.getJxjsqxn(), 
					Base.getJxjsqxq(), pkValue, pkValue,pkValue}, "num");//专业已审核通地的人数
//			String num = dao.getOneRs(
//					"select count(*) num from view_xsjxjb where xh = (select xh from " +
//					"view_xsjxjb where xh||jxjdm||xn||xq=?) and xn=? and xq=? and fdysh='通过' and " +
//					"jxjlb <> (select distinct jxjlb from view_xsjxjb where xh||jxjdm||xn||xq=?)",
//					new String[] {pkValue, Base.getJxjsqxn(), Base.getJxjsqxq(), pkValue}, "num");//奖学金兼得
			int tmp = StringUtils.isNull(xzrs) ? 0 : Integer.parseInt(xzrs);
			//int tmpk = StringUtils.isNull(num) ? 0 : Integer.parseInt(num);
			int tmpkh = StringUtils.isNull(zytgrs) ? 0 : Integer.parseInt(zytgrs);
//			if (tmpk > 0) {
//				return "操作失败,该生已获得其它类别奖学金不能兼得!";//违返兼得
//			}
			if ((tmp != 0) && (tmpkh >= tmp)) {
				if (jxjdm.equalsIgnoreCase(jxjdmList[0])
						|| jxjdm.equalsIgnoreCase(jxjdmList[1])
						|| jxjdm.equalsIgnoreCase(jxjdmList[2])) {
					if (jxjdm.equalsIgnoreCase(jxjdmList[0])) {
						dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[1],pkValue});
					} else if (jxjdm.equalsIgnoreCase(jxjdmList[1])) {
						dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[2],pkValue});
					} else {
						dao.runUpdate("update xsjxjb set jxjdm=? where xh||jxjdm||xn||xq=? and fdysh<>'通过'", new String[]{jxjdmList[3],pkValue});
					}
					return "审核失败,奖学金审核通过人数已超专业设置限制人数!\n该信息将划分到下一等级奖学金名单中.";
				} else {
					return "操作失败,奖学金审核通过人数已超专业设置限制人数!";//超限制人数
				}
			}
			boolean bFlag = dao.runUpdate("update xsjxjb set fdysh=?,fdyyj=? " +
					"where xh||jxjdm||xn||xq=?", new String[]{
					DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), 
					pkValue});
			if (bFlag) {
				return "";
			} else {
				return "操作失败";
			}
		} else {
			boolean bFlag = dao.runUpdate("update xsjxjb set fdysh=?,fdyyj=? " +
					"where xh||jxjdm||xn||xq=?", new String[]{
					DealString.toGBK(model.getYesNo()), DealString.toGBK(model.getYj()), 
					pkValue});
			if (bFlag) {
				return "";
			} else {
				return "操作失败";
			}
		}
	
	}
	
	/**
	 * 查询学生申请奖学金详细信息
	 * @param pk
	 * @param pkValue
	 * @return HashMap<String, String>
	 * */
	public HashMap<String, String> getJxjsqInfo(String pk,String pkValue){
		String sql = "select xn,nd,xq,xh,xm,xb,nj,xymc,zymc,bjmc,(select csrq from view_xsxxb b where a.xh=b.xh) csrq," +
				"(select mzmc from view_xsxxb b where a.xh=b.xh) mzmc,(select zzmmmc from view_xsxxb b where a.xh=b.xh) zzmmmc," +
				"(select syd from view_xsxxb b where a.xh=b.xh) syd,drzw,jxjdm,sqly,jd,(select mc1 from view_xsxqpmjgb b where a.xh=b.xh and a.xn=b.xn and a.xq=b.xq) mc1 from view_xsjxjb a where "+pk + "= ?";
		String[] outputValue = {"xn","nd","xq","xh","xm","xb","nj","xymc","zymc","bjmc","csrq","mzmc","zzmmmc","syd",
				 "drzw","jxjdm","sqly","jd", "mc1"};
		
		return dao.getMap(sql, new String[]{pkValue}, outputValue);
	}
	
	public String stuname(String xh) throws Exception {
		return dao.getOneRs("select distinct xm from view_xsjbxx where xh=?", new String[]{xh}, "xm");
	}
	
	public HashMap sn(String pkValue) {
		return dao.getMapNotOut("select xh,xm from view_xsjxjb where xh||jxjdm||xn||xq=? ", new String[]{pkValue});
	}
	
	public String getJxjlb(String jxjdm) {
		return dao.getOneRs("select jxjlb from jxjdmb where jxjdm=?", new String[]{jxjdm}, "jxjlb");
	}
	
	public HashMap<String, String> getPjpyZq() {
		return dao.getMapNotOut("select xn,nd,xq from pjpy_pjzqb", new String[]{});
	}
}
