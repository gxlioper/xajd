package xgxt.qgzx;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.String.StringUtils;

public class QgzxGbmygExcelDAO {
	DAO dao = DAO.getInstance();
	private ArrayList<String> values = new ArrayList<String>();

	/**
	 * 获取部门列表
	 * 
	 * @param myForm
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getBmmcList(
			QgzxGbmygExcelActionForm myForm, HttpServletRequest request)
			throws Exception {
		String sql = "select yrdwdm pk,'' dis,yrdwdm,yrdwmc from yrdwdmb";
		String[] colList = new String[] { "pk", "dis", "yrdwdm", "yrdwmc" };
		return dao.getList(sql + " order by yrdwdm", values != null ? values
				.toArray(new String[0]) : new String[] {}, colList);
	}

	/**
	 * 获取每个部门的地址
	 * 
	 * @param myForm
	 * @param request
	 * @param sqdw
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getDdmcList(
			QgzxGbmygExcelActionForm myForm, HttpServletRequest request,
			String sqdw) throws Exception {
		String sql = "select gwdm||gwsbsj pk,'' dis,gwdm,sqdw from gwxxb where sqdw='"
				+ sqdw.toString() + "'";
		String[] colList = new String[] { "pk", "dis", "gwdm", "sqdw" };
		return dao.getList(sql, values != null ? values.toArray(new String[0])
				: new String[] {}, colList);
	}

	/**
	 * 获取固定人数
	 * 
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getRsslList(
			QgzxGbmygExcelActionForm myForm, HttpServletRequest request,
			String gwdm) throws Exception {
		String sql = "select xh||gwdm||gwsbsj pk,'' dis,gwdm,xyyj,xxyj,gwsbsj from xsgwxxb where xxyj='通过' and gwdm='"
				+ gwdm.toString() + "'";
		String[] colList = new String[] { "pk", "dis", "gwdm", "xyyj", "xxyj",
				"gwsbsj" };
		return dao.getList(sql, values != null ? values.toArray(new String[0])
				: new String[] {}, colList);
	}

	/**
	 * 获取发放金额
	 * 
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getFyjeList(
			QgzxGbmygExcelActionForm myForm, HttpServletRequest request,
			String gwdm, String ffsjks, String ffsjjs) throws Exception {

		String sql = "select xh||gwdm||sqsj||nd||yf||ffsj pk,'' dis,gwdm,cjje,ffsj,xxsh from xscjffb where xxsh='通过' and gwdm='"
				+ gwdm.toString() + "'";
		if (!StringUtils.isNull(ffsjks)) {
			// sql+=" and ffsj >=to_char(to_date('"+ffsjks+"','YYYY-MM-DD'))";
			sql += " and to_number(substr(ffsj,0,8)) >= to_number("
					+ ffsjks.replaceAll("-", "") + ")";
		}
		if (!StringUtils.isNull(ffsjjs)) {
			// sql+=" and ffsj <=to_char(to_date('"+ffsjjs+"','YYYY-MM-DD'))";
			sql += " and to_number(substr(ffsj,0,8)) <= to_number("
					+ ffsjjs.replaceAll("-", "") + ")";
		}
		String[] colList = new String[] { "pk", "dis", "gwdm", "cjje", "ffsj",
				"xxsh" };
		return dao.getList(sql, values != null ? values.toArray(new String[0])
				: new String[] {}, colList);
	}

	/**
	 * 工资发放表数据
	 * 
	 * @param myForm
	 * @param request
	 * @param gwdm
	 * @param ffsjks
	 * @param ffsjjs
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, String>> getgzffList(
			QgzxGbmygExcelActionForm myForm, HttpServletRequest request,
			String gwdm, String ffsjks, String ffsjjs) throws Exception {
		String sql = "select rownum deptnum,a.yrdwmc,b.gwdm,f.bjmc,f.xm,c.xh,d.gzsj,d.cjje,e.gwxzmc,d.xxsh,d.bz" +
				" from yrdwdmb a,gwxxb b,xsgwxxb c,xscjffb d,gwxzdmb e,view_xsxxb f" +
				" where a.yrdwdm=b.sqdw and b.gwdm=c.gwdm and c.xh=d.xh and c.gwdm=d.gwdm  and e.gwxzdm=b.gwxz and d.xh=f.xh and a.yrdwmc='"
				+ gwdm.toString() + "'";
		sql+=" and d.xxsh='通过' ";
		if (!StringUtils.isNull(ffsjks)) {
			sql += " and to_number(substr(d.ffsj,0,8)) >= to_number("
					+ ffsjks.replaceAll("-", "") + ")";
		}
		if (!StringUtils.isNull(ffsjjs)) {
			sql += " and to_number(substr(d.ffsj,0,8)) <= to_number("
					+ ffsjjs.replaceAll("-", "") + ")";
		}
		sql+=" order by a.yrdwmc,b.gwdm,f.bjmc,f.xm";
		String[] colList = new String[] { "deptnum", "yrdwmc", "gwdm", "bjmc", "xm",
				"xh","gzsj","cjje","gwxzmc","xxsh","bz" };
		return dao.getList(sql, values != null ? values.toArray(new String[0])
				: new String[] {}, colList);
	}


	
//	 -----------------------2012.3.14 qlj 获取临时岗位酬金发放信息 begin------------------------
	/**
	 * 2012.3.14 qlj
	 * 统计学生酬金发放信息BY临时岗位（当前学年、学期信息）
	 * @param myForm
	 * @return List<String[]>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public List<String[]> getXscjInfoByLsgw(QgzxGbmygExcelActionForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// -----------------岗位信息-----------------
		StringBuilder sql = new StringBuilder();
		sql.append("  select sqdw, yrdwmc, gwdm, sqsj, xh,xm, nvl(cjje,0)cjje, nvl(gzsj,0)gzsj, bjdm, bjmc, ")
			.append(" gwdm||'!!@@!!'||sqsj pk  from view_xscjff ")
			.append(" where 1=1  ")
			.append(" and xn = '"+Base.currXn+"'  and xq = '"+Base.currXq+"' ")
			.append(" and xxsh = '通过' and gwxzmc like '%临时%' ")
			.append(" group by sqdw, yrdwmc, gwdm,sqsj, xh,xm, cjje, gzsj, bjdm, bjmc ")
			.append(" order by sqdw,yrdwmc,gwdm,sqsj,bjmc,xh ");

		return CommonQueryDAO.commonQueryNotFy(sql.toString(), "",
				new String[] {}, new String[] {"sqdw","pk","yrdwmc","gwdm","bjmc","xm","xh","gzsj","cjje"}, myForm);
	}
	
	/**
	 * 2012.3.14 qlj
	 * 获取酬金发放总人数、金额、工时（当前学年、学期）
	 * @param myForm
	 * @return HashMap<String,String>
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public HashMap<String,String> getZrsJeGs(QgzxGbmygExcelActionForm myForm)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		// -----------------岗位信息-----------------
		StringBuilder sql = new StringBuilder();
		sql.append(" select zrs, zgs, zje ");
		
		// ------------------统计总人数---------------------
		sql.append("  from (select count(1) zrs ");
		sql.append("   from xscjffb a left join gwxxb b on a.gwdm=b.gwdm and a.sqsj=b.gwsbsj ");
		sql.append("  left join gwxzdmb c on b.gwxz=c.gwxzdm ");
		sql.append("  where 1 = 1 ");
		sql.append("  and a.xn = '"+Base.currXn+"' ");
		sql.append("  and a.xq ='"+Base.currXq+"' ");
		sql.append("  and a.xxsh = '通过' ");
		sql.append("  and gwxzmc like '%临时%') a ");
		
		// ------------------统计总工时、总金额---------------------
		sql.append("  left join (select nvl(sum(to_number(a.gzsj)),0) zgs, nvl(sum(to_number(a.cjje)),0) zje ");
		sql.append("  from xscjffb a left join gwxxb b on a.gwdm=b.gwdm and a.sqsj=b.gwsbsj ");
		sql.append("   left join gwxzdmb c on b.gwxz=c.gwxzdm ");
		sql.append("  where 1 = 1 ");
		sql.append("   and a.xn = '"+Base.currXn+"' ");
		sql.append("  and a.xq = '"+Base.currXq+"' ");
		sql.append("  and a.xxsh = '通过' ");
		sql.append("  and gwxzmc like '%临时%') b on 1 = 1 ");

		return dao.getMap(sql.toString(), 
				new String[] {}, new String[] {"zrs","zgs","zje"});
	}
	
	/**
	 * 2012.3.14 qlj
	 * 获取学期名称信息（传入学期代码）
	 * @param xq
	 * @return String
	 */
	public String getXqmc(String xq){
		
        DAO dao=DAO.getInstance();
		
		String sql=" select xqmc from xqdzb where xqdm=? ";
		
		return dao.getOneRs(sql, new String[]{xq}, "xqmc");
	}
//	 -----------------------2012.3.14 qlj 获取临时岗位酬金发放信息 end------------------------

}
