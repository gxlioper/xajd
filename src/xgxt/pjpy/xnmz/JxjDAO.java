
package xgxt.pjpy.xnmz;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.utils.String.StringUtils;

public class JxjDAO {

	DAO dao = DAO.getInstance();
	ArrayList<String> values = new ArrayList<String>();
	/**
	 * 学院审核奖学金查询结果
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xyjxjshQryTitle() throws Exception {
		String[] enList = new String[]{"pk", "bgcolor", "xh", "xm", "bjmc", "xn", "jxjmc","zdcj","zdjd", "jd", "xf", "xysh"};
		String[] cnList = new String[]{"pk","bgcolor", "学号", "姓名", "班级", "学年", "奖学金","单科最低成绩","单科最低绩点","平均绩点", "总学分", "学院审核"};
		return dao.arrayToList(enList, cnList);
	}
	
	/**
	 * 学校审核奖学金查询结果
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> xxjxjshQryTitle() throws Exception {
		String[] enList = new String[]{"pk", "bgcolor","rownum", "xh", "xm", "bjmc", "xn", "jxjmc","zdcj","zdjd", "jd", "xf", "xxsh"};
		String[] cnList = new String[]{"pk","bgcolor", "行号", "学号", "姓名", "班级", "学年", "奖学金","单科最低成绩","单科最低绩点","平均绩点", "总学分", "学校审核"};
		return dao.arrayToList(enList, cnList);
	}
	
	public StringBuffer getWhereSql(JxjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (StringUtils.isNotNull(model.getXh())) {
			whereSql.append(" and a.xh = ?");
			values.add(model.getXh());
		}
		if (StringUtils.isNotNull(model.getXm())) {
			whereSql.append(" and a.xm like ?");
			values.add("%" + DealString.toGBK(model.getXm()) + "%");
		}
		if (StringUtils.isNotNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (StringUtils.isNotNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (StringUtils.isNotNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (StringUtils.isNotNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		if (StringUtils.isNotNull(model.getXn())) {
			whereSql.append(" and a.xn = ?");
			values.add(model.getXn());
		}
		if (StringUtils.isNotNull(model.getJxjdm())) {
			whereSql.append(" and jxjdm = ?");
			values.add(model.getJxjdm());
		}
		return whereSql;
	}

	public StringBuffer getWhereSql1(JxjModel model) throws Exception {
		StringBuffer whereSql = new StringBuffer();
		if (StringUtils.isNotNull(model.getNj())) {
			whereSql.append(" and a.nj = ?");
			values.add(model.getNj());
		}
		if (StringUtils.isNotNull(model.getXydm())) {
			whereSql.append(" and a.xydm = ?");
			values.add(model.getXydm());
		}
		if (StringUtils.isNotNull(model.getZydm())) {
			whereSql.append(" and a.zydm = ?");
			values.add(model.getZydm());
		}
		if (StringUtils.isNotNull(model.getBjdm())) {
			whereSql.append(" and a.bjdm = ?");
			values.add(model.getBjdm());
		}
		return whereSql;
	}
	
	public List<String[]> xyjxjshQryResult(JxjModel model) throws Exception {
		String xn = model.getXn();
		String jxjdm = model.getJxjdm();
		StringBuffer whereSql = getWhereSql1(model);
		String sql = "select distinct a.xh,a.pk,bgcolor,a.xm,a.nj,a.jxjdm,a.bjmc,a.zydm,a.xn,a.jxjmc,a.zdjd,a.jd,a.xf,a.xysh" +
				",(select min(cj) from view_cjb b where a.xh=b.xh and b.xn='"+xn+"') zdcj from (select a.xh||b.xn||b.jxjdm pk,(case nvl(b.xysh,'未审核') when '通过' " +
				"then '#99FFCC' when '不通过' then '#BBBBBB' when '未审核' then '#FFFFFF' end) bgcolor,a.xh,a.xm,a.nj,a.zydm," +
				"a.bjmc,'"+model.getXn()+"' xn,'"+jxjdm+"' jxjdm,(select jxjmc from jxjdmb where " +
						"jxjdm='"+model.getJxjdm()+"') jxjmc,b.xysh,(select trunc(min(jd),3) from view_xscjjdb c where c.xh=a.xh and c.xn='"+model.getXn()+"' group by xh) zdjd,(select trunc(avg(jd),3) from view_xscjjdb c where c.xh=a.xh and c.xn='"+model.getXn()+"' group by xh) jd,(select sum(c.xf) xf from view_xscjjdb c where c.xh=a.xh and substr('"+model.getXn()+"',0,4)>=substr(c.xn,0,4) group by c.xh) xf from view_xsjbxx" +
								" a left join xsjxjb b on a.xh=b.xh and " +
								"b.xn='"+model.getXn()+"' and b.jxjdm='"+model.getJxjdm()+"' where 1=1 ";
		String[] opList = new String[]{"pk","bgcolor", "xh", "xm", "bjmc", "xn", "jxjmc","zdcj", "zdjd","jd","xf","xysh"};
		return dao.rsToVator(sql + whereSql.toString()+") a,view_xscjjdb d where a.xh = d.xh and d.xn = '"+xn+"' and (select trunc(avg(jd), 3) from xscjjdb d where xn = '2007-2008' and a.xh=d.xh" +
				" group by xh) >= (select szjdbz from jxjdmb b where b.jxjdm=a.jxjdm) and (select min(jd) from xscjjdb d where xn = '"+xn+"' and a.xh=d.xh" +
						" group by xh) >=(select dkjd from jxjdmb b where b.jxjdm=a.jxjdm) and (select sum(xf) from xscjjdb d where substr('"+xn+"',0,4)>=substr(xn,0,4) and a.xh=d.xh " +
								"group by xh) >=(select jd from pjpy_xyjdszb d where a.nj=d.nj and a.zydm=d.zydm and d.xn='"+xn+"')", values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	public List<String[]> xxjxjshQryResult(JxjModel model) throws Exception {
		StringBuffer whereSql = getWhereSql(model);
		String sql = "select a.xh||a.xn||a.jxjdm pk,(case nvl(a.xxsh,'未审核') when '通过' then '#99FFCC' when '不通过' then '#BBBBBB' when '未审核' then '#FFFFFF' end) bgcolor,rownum,a.xh,a.xm,a.bjmc,a.xn,a.jxjmc,a.xxsh," +
				"(select min(cj) from view_cjb b where a.xh=b.xh and b.xn='"+Base.getJxjsqxn()+"') zdcj,(select trunc(min(jd),3) from view_xscjjdb c where c.xh=a.xh and c.xn='"+model.getXn()+"' group by xh) zdjd,(select trunc(avg(jd),3) from view_xscjjdb c where c.xh=a.xh and c.xn='"+model.getXn()+"' group by xh) jd,(select sum(c.xf) xf from view_xscjjdb c where c.xh=a.xh and substr('"+model.getXn()+"',0,4)>=substr(c.xn,0,4) group by c.xh) xf from view_xsjxjb a where a.xysh='通过' ";
		String[] opList = new String[]{"pk", "bgcolor","rownum", "xh", "xm", "bjmc", "xn", "jxjmc","zdcj","zdjd", "jd", "xf", "xxsh"};
		return dao.rsToVator(sql + whereSql.toString(), values != null ? values
				.toArray(new String[0]) : new String[] {}, opList);
	}
	
	/**
	 * 学院奖学金审核结果
	 *     keys 中存放的可能是XH||XN||JXJDM,也可能是XH
	 * @param keys
	 * @param res
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String xyjxjshRes(String[] keys, String res,
			HttpServletRequest request, JxjModel model) throws Exception {
		String rs = "";
		String xycb = "";
		String xxcb = "";
		String xn = model.getXn();
		String jxjdm = model.getJxjdm();
		for (int i=0;i<keys.length;i++) {
			//是否已上报
			String exists = dao.getOneRs("select count(*) num from view_xsjxjb where xh||xn||jxjdm = ?", new String[]{keys[i]}, "num");
			if ("btg".equalsIgnoreCase(res)) {//审核不通过
				if (StringUtils.isNotNull(exists) && !"0".equalsIgnoreCase(exists)) {//已上报则更新审核状态
					dao.runUpdate("update xsjxjb set xysh='不通过' where xh||xn||jxjdm = ?", new String[]{keys[i]});
				} else {
					dao.runUpdate("insert into xsjxjb (xh,xn,jxjdm,xysh) vlaues (?,?,?,'不通过')", new String[]{keys[i],xn,jxjdm});
				}
			} else {//审核通过
				
				if (StringUtils.isNotNull(exists) && !"0".equalsIgnoreCase(exists)) {//已上报则更新审核状态
//					学校设置的学院总人数
					String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and xn=? and jxjdm='"+jxjdm+"'", new String[]{keys[i],xn}, "jxjrs");
					//学院通过的人数
					String xyrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and xn=? and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?) and xydm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?)", new String[]{Base.getJxjsqxn(),keys[i], keys[i]}, "num");
					String zrs = "";
					//奖学金分配方式
					String jxjfpfs = dao.getOneRs("select distinct fpfs from xyfpjxjfs where xn=? and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?)", new String[]{Base.getJxjsqxn(), keys[i]}, "fpfs");
					jxjfpfs = StringUtils.isNull(jxjfpfs) ? "" : jxjfpfs.trim();
					String tgrs = "";
					if ("zy".equalsIgnoreCase(jxjfpfs)) {//专业通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and zydm=(select zydm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?) and xn=?", new String[]{keys[i], keys[i], Base.getJxjsqxn()}, "num");
						//专业限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=(select jxjsqxn from xtszb) and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and fpbm=(select zydm from view_xsjxjb where xh||xn||jxjdm = ?) and nj=(select nj from xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?)", new String[]{keys[i],keys[i],keys[i],keys[i]}, "rstz");
					} else if ("bj".equalsIgnoreCase(jxjfpfs)) {//班级通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and bjdm=(select bjdm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ? and xn = ?)", new String[]{keys[i], keys[i],Base.getJxjsqxn()}, "num");
						//班级限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=(select jxjsqxn from xtszb) and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and fpbm=(select bjdm from view_xsjxjb where xh||xn||jxjdm = ?) and nj=(select nj from xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from xsjxjb where xh||xn||jxjdm = ?)", new String[]{keys[i],keys[i],keys[i],keys[i]}, "rstz");
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
				} else {//未上报则插入数据
//					学校设置的学院总人数
					//String whichpk = keys[i] + xn + jxjdm;
					String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=(select xydm from view_xsjbxx where xh = ?) and xn=? and jxjdm='"+jxjdm+"'", new String[]{keys[i],xn}, "jxjrs");
					String xyrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and xn=? and jxjdm='"+jxjdm+"' and xydm=(select xydm from view_xsjbxx where xh=?)", new String[]{Base.getJxjsqxn(),keys[i]}, "num");
					String zrs = "";
					//奖学金分配方式
					String jxjfpfs = dao.getOneRs("select distinct fpfs from xyfpjxjfs where xn=? and bmdm=(select xydm from view_xsjbxx where xh = ?)", new String[]{Base.getJxjsqxn(), keys[i]}, "fpfs");
					jxjfpfs = StringUtils.isNull(jxjfpfs) ? "" : jxjfpfs.trim();
					String tgrs = "";
					if ("zy".equalsIgnoreCase(jxjfpfs)) {//专业通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and zydm=(select zydm from view_xsjbxx where xh = ?) and jxjdm='"+jxjdm+"' and xn=?", new String[]{keys[i], Base.getJxjsqxn()}, "num");
						//专业限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=(select jxjsqxn from xtszb) and bmdm=(select xydm from view_xsjbxx where xh = ?) and fpbm=(select zydm from view_xsjbxx where xh = ?) and nj=(select nj from view_xsjbxx where xh = ?) and jxjdm='"+jxjdm+"'", new String[]{keys[i],keys[i],keys[i]}, "rstz");
					} else if ("bj".equalsIgnoreCase(jxjfpfs)) {//班级通过人数
						tgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xysh='通过' and bjdm=(select bjdm from view_xsjbxx where xh = ?) and jxjdm='"+jxjdm+"' and xn = ?", new String[]{keys[i],Base.getJxjsqxn()}, "num");
						//班级限制人数
						zrs = dao.getOneRs("select rstz from xyjxjfpb where xn=(select jxjsqxn from xtszb) and bmdm=(select xydm from view_xsjbxx where xh = ?) and fpbm=(select bjdm from view_xsjbxx where xh = ?) and nj=(select nj from view_xsjbxx where xh = ?) and jxjdm='"+jxjdm+"'", new String[]{keys[i],keys[i],keys[i]}, "rstz");
					}
					int tmpxyzrs = StringUtils.isNull(xyzrs) ? 0 : Integer.parseInt(xyzrs.trim());
					int tmpzrs = StringUtils.isNull(zrs) ? 0 : Integer.parseInt(zrs.trim());
					int tmptgrs = StringUtils.isNull(tgrs) ? 0 : Integer.parseInt(tgrs.trim());
					int tmpxyrs = StringUtils.isNull(xyrs) ? 0 : Integer.parseInt(xyrs.trim());
					if (tmpxyzrs !=0 && tmpzrs != 0 && tmptgrs >= tmpzrs) {
						xycb += ((i+1) + ",");
					} else if (tmpxyzrs !=0 && tmpxyrs != 0 && tmpxyrs>=tmpxyzrs) {
						xxcb += ((i+1) + ",");
					} else {
						dao.runUpdate("insert into xsjxjb (xh,xn,jxjdm,xysh) values (?,?,?,'通过')", new String[]{keys[i],xn,jxjdm});
					}
				}
			}
		}
		xycb = StringUtils.isNull(xycb) ? "" : "操作完成,其中第" + xycb + "条记录审核失败,原因是审核通过人数已超院系设置的获奖人数!";
		xxcb = StringUtils.isNull(xxcb) ? "" : "操作完成,其中第" + xxcb + "条记录审核失败,原因是审核通过人数已超学校设置的获奖人数!";
		rs  = xycb + xxcb ;
		return rs;
	}
	
	/**
	 * 学校奖学金审核结果
	 *  
	 * @param keys
	 * @param res
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String xxjxjshRes(String[] keys, String res,
			HttpServletRequest request, JxjModel model) throws Exception {
		String rs = "";
		String xxcb = "";
		for (int i=0;i<keys.length;i++) {
			//是否已上报
			if ("btg".equalsIgnoreCase(res)) {//审核不通过
				dao.runUpdate("update xsjxjb set xxsh='不通过' where xh||xn||jxjdm = ?", new String[]{keys[i]});
			} else {//审核通过
				//学校审核通过人数
				String xytgrs = dao.getOneRs("select count(*) num from view_xsjxjb where xn='"+Base.getJxjsqxn()+"' and xydm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and jxjdm=(select jxjdm from view_xsjxjb where xh||xn||jxjdm = ?) and xxsh='通过'", new String[]{keys[i], keys[i]}, "num");
				//学校设置学院获奖人数
				String xyzrs = dao.getOneRs("select jxjrs from xyjxjrs where bmlb='xydm' and bmdm=(select xydm from view_xsjxjb where xh||xn||jxjdm = ?) and xn=? and jxjdm=(select jxjdm from view_xsjxjb where xh||xn||jxjdm = ?)", new String[]{keys[i],Base.getJxjsqxn(),keys[i]}, "jxjrs");
				int tmpxytgrs = StringUtils.isNull(xytgrs) ? 0 : Integer.parseInt(xytgrs.trim());
				int tmpxyzrs = StringUtils.isNull(xyzrs) ? 0 : Integer.parseInt(xyzrs.trim());
				if (tmpxyzrs !=0 && tmpxytgrs >= tmpxyzrs) {
					xxcb += (i+1) + ",";
					continue;
				} else {
					dao.runUpdate("update xsjxjb set xxsh='通过' where xh||xn||jxjdm = ?", new String[]{keys[i]});
				}
			}	
		}
		xxcb = StringUtils.isNull(xxcb) ? "" : "操作完成,其中第" + xxcb + "条记录审核失败,原因是审核通过人数已超学校设置的获奖人数!";
		rs = xxcb ;
		return rs;
	}		
}
