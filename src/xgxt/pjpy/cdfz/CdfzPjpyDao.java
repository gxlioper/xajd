package xgxt.pjpy.cdfz;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.DAO.DAO;
import xgxt.comm.CommDAO;
import xgxt.utils.MakeQuery;
import xgxt.comm.search.SearchModel;
import xgxt.utils.CommonQueryDAO;
import xgxt.form.User;
public class CdfzPjpyDao extends CommDAO {
	
	/**
	 * 获取参评人数列表
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyRsList(CdfzPjpyForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();
		StringBuilder querySql=new StringBuilder();
		StringBuilder userQuery=new StringBuilder();
		User user=model.getUser();
		String tjlx=model.getTjlx();
		
		
		if(!StringUtils.isEmpty(model.getXydm())){
			querySql.append(" and pjxydm='"+model.getXydm()+"'");
		}
		
		if(!StringUtils.isEmpty(model.getZydm())){
			querySql.append(" and pjzydm='"+model.getZydm()+"'");
		}
		
		if(!StringUtils.isEmpty(model.getBjdm())){
			querySql.append(" and pjbjdm='"+model.getBjdm()+"'");
		}
		
		if(!StringUtils.isEmpty(model.getNj())){
			querySql.append(" and pjnj='"+model.getNj()+"'");
		}
		
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("xy".equalsIgnoreCase(userType)){
			userQuery.append(" and xydm='"+userDep+"' ");
		}
		
		sql.append(" select  dm ,mc,count(1)rs from(  ");
		sql.append(" select "+tjlx+"dm  dm,"+tjlx+"mc  mc from ( ");
		
		sql.append(" select xh,pjnj nj,pjxydm xydm,pjzydm zydm,pjbjdm bjdm ");
		sql.append(" ,pjxymc xymc,pjzymc zymc,pjbjmc bjmc ");
		sql.append(" from view_xg_pjpy_pjry ");
		sql.append(" where pjxn='"+model.getXn()+"' and pjxq='"+model.getXq()+"'  ");
		sql.append(querySql);
		
		sql.append(" )a where 1=1 " );
		sql.append(userQuery);
		sql.append( ") ");
		
		sql.append(" group by dm,mc ");
		sql.append("  order by mc ");
		
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm","rs" });
	}

	/**
	 * 根据统计类型获取部门信息列表
	 * 
	 * @param model
	 * @return
	 */
	public List<HashMap<String, String>> getPjpyXmList(CdfzPjpyForm model) {

		DAO dao = DAO.getInstance();
		StringBuilder sql = new StringBuilder();

		sql.append(" select xmdm dm, xmmc mc,pjxn,pjxq from xg_pjpy_pjxmwhb  " );
		sql.append(" where xmlx='01' and pjxn = '"+model.getXn()+"' " );
		sql.append(" and pjxq = '"+model.getXq()+"' " );
		sql.append("  order by xssx ");
		return dao.getList(sql.toString(), new String[] {}, new String[] {
				"dm", "mc" });
	}

	/**
	 * 获取评奖汇总列表
	 * 
	 * @param model
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getPjhzList(CdfzPjpyForm model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		DAO dao = DAO.getInstance();
		User user=model.getUser();
		StringBuilder userQuery=new StringBuilder();
		StringBuilder sql = new StringBuilder();
		StringBuilder caseSql = new StringBuilder();
		StringBuilder sumSql = new StringBuilder();
		StringBuilder zrsSql = new StringBuilder();
		StringBuilder zjeSql=new StringBuilder();
		
		// ------------------评奖项目列表----------------------
		List<HashMap<String, String>> pjxmList = getPjpyXmList(model);
		
		if(pjxmList!=null && pjxmList.size()>0){
			
			String tjlx = model.getTjlx();
			String discxzd = "";
			String cxzd = "";
			String groupZd="";
	
			
			if ("xy".equalsIgnoreCase(tjlx)) {
				discxzd = " distinct(xydm) dm,xymc mc ";
				cxzd = " xydm dm,xymc mc ";
				groupZd=" xydm,xymc";
			} else if ("zy".equalsIgnoreCase(tjlx)) {
				discxzd = " distinct(zydm) dm,zymc mc ";
				cxzd = " zydm dm,zymc mc ";
				groupZd=" zydm,zymc";
			} else if ("bj".equalsIgnoreCase(tjlx)) {
				discxzd = "  distinct(bjdm) dm,bjmc mc ";
				cxzd = " bjdm dm,bjmc mc ";
				groupZd=" bjdm,bjmc";
			}
	
			// ---学院查询条件---
			StringBuilder querySql=new StringBuilder();
			if(!StringUtils.isEmpty(model.getXydm())){
				querySql.append(" and xydm='"+model.getXydm()+"'");
			}
			
			if(!StringUtils.isEmpty(model.getZydm())){
				querySql.append(" and zydm='"+model.getZydm()+"'");
			}
			
			if(!StringUtils.isEmpty(model.getBjdm())){
				querySql.append(" and bjdm='"+model.getBjdm()+"'");
			}
			
			if(!StringUtils.isEmpty(model.getNj())){
				querySql.append(" and nj='"+model.getNj()+"'");
			}
			String userName=user.getUserName();
			String userType=user.getUserType();
			String userDep=user.getUserDep();
			
			if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
				userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
				userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
				userQuery.append(" ) ");
			}else if("true".equalsIgnoreCase(user.getFdyQx())){
				userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
			}else if("true".equalsIgnoreCase(user.getBzrQx())){
				userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
			}else if("xy".equalsIgnoreCase(userType)){
				userQuery.append(" and xydm='"+userDep+"' ");
			}
	
			List<String>colList=new ArrayList<String>();
			colList.add("dm");
			colList.add("mc");
			colList.add("cprs");
			
			// =========================行转列前奏 begin===============================
			
			caseSql.append(" select a.dm,a.mc ");
			sumSql.append(" select a.dm,a.mc,''cprs ");
			
			
			for (int i = 0; i < pjxmList.size(); i++) {
				HashMap<String, String> pjxmMap = pjxmList.get(i);
				caseSql.append(" ,case when xmdm='" + pjxmMap.get("dm")
						+ "' then nvl(xmje,0)  end xmdm_" + i + "_je  ");
				caseSql.append(" ,case when xmdm='" + pjxmMap.get("dm")
						+ "' then nvl(rs,0)  end xmdm_" + i + "_rs  ");
				// ================行转列===================
				sumSql.append(" ,sum(xmdm_" + i + "_rs)xmdm_" + i + "_rs ");
				sumSql.append(" ,sum(xmdm_" + i + "_rs) * sum(xmdm_" + i + "_je) xmdm_" + i + "_je ");
				// ================行转列===================
				if(i==0){
					zrsSql.append(" sum(xmdm_" + i + "_rs) ");
					zjeSql.append(" sum(xmdm_" + i + "_rs) * sum(xmdm_" + i + "_je) ");
				}else{
					zrsSql.append(" +sum(xmdm_" + i + "_rs) ");
					zjeSql.append(" +sum(xmdm_" + i + "_rs) * sum(xmdm_" + i + "_je) ");
				}
				
				colList.add("xmdm_" + i + "_rs");
				colList.add("xmdm_" + i + "_je");
			}
			sumSql.append(" ,"+zrsSql+" zrs,"+zjeSql+" zje from( ");
			caseSql.append(" from( ");
			
			sql.append(sumSql);
			sql.append(caseSql);
			
			colList.add("zrs");
			colList.add("zje");
			// =========================行转列前奏 end===============================
	
			// -----------------项目、部门 统计begin--------------------
			sql.append(" select a.xmdm,a.xmmc,b.dm,b.mc,a.xmje,c.rs from ");
			sql.append(" (select pjxn,pjxq,pjnd,xmdm,xmmc,xmje,sqzq from ");
			sql.append(" xg_pjpy_pjxmwhb where  xmlx='01' and pjxn='"+model.getXn()+"' and pjxq='"+model.getXq()+"' ");
			sql.append("  order by xssx  )a left join ");
			
			sql.append(" (select " + discxzd + " from  ");
			sql.append(" view_xg_pjpy_njxyzybj a where pjxn='"+model.getXn()+"' and pjxq='"+model.getXq()+"'  ");
			sql.append(  querySql );
			sql.append(userQuery);
			sql.append("  )b on 1=1 left join(  ");
			
			sql.append("  select pjxn,pjxq,pjnd,xmdm,xmmc,dm, mc,xmje,count(1)rs from( ");
			sql.append("  select xmdm,xmmc,pjxn,pjxq,pjnd,xh,dm,mc,xmje,shzt from ( ");
			sql.append(" select xmdm,xmmc,pjxn,pjxq,pjnd,xh,shzt," + cxzd+ ",xmje,max(shjb) ");
			sql.append(" from xg_view_pjpy_xmsh_all a where 1=1 "+querySql+userQuery+" group by ");
			sql.append(" xmdm,xmmc,pjxn,pjxq,pjnd,xh,shzt,"+groupZd+",xmje ) ");
			sql.append(" where shzt='通过')group by xmdm,xmmc,dm,mc,xmje,pjxn,pjxq,pjnd ");
			sql.append(" )c  on  ");

			sql.append(" (a.sqzq='xn' and a.pjxn=c.pjxn ");
			sql.append(" or a.sqzq='xq' and a.pjxn=c.pjxn and a.pjxq=c.pjxq ");
			sql.append(" or a.sqzq='nd' and a.pjnd=c.pjnd) ");
			sql.append(" and a.xmdm=c.xmdm and ");
			
			sql.append(" b.dm=c.dm ");
			// -----------------项目、部门 统计end--------------------
			sql.append(" )a)a group by dm,mc ");

			sql.append(" order by mc ");
			
			return dao.rsToVator(sql.toString(), new String[]{},  colList.toArray(new String[]{}));
		
		}else{
			return null;
		}
		
	}
	
	
	// =========================单纯的涛写下面==================================
	
	/**
	 * 根据条件查询已获奖的奖学金代码列表
	 */
	public List<HashMap<String, String>> queryJxjdmList(CdfzPjpyForm form, User user) {
		DAO dao = DAO.getInstance();
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[]{"nj", "xydm", "zydm", "bjdm"}, new String[]{"xh", "xm"}, form);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		StringBuffer userQuery  = new StringBuffer();
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}
		return dao.getList(
						"select distinct xmdm,xmmc from (select xmdm,xmmc,pjxn,pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc," +
						"bjmc,xmje,max(shjb),(select xssx from xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm) xssx from" +
						" xg_view_pjpy_xmsh_all a " + makeQuery.getQueryString() + userQuery.toString() +" and shzt='通过' "
						+ " and exists (select 1 from  xg_pjpy_pjxmwhb c where c.pjxn='"+form.getPjxn()+"' and c.pjxq='"+form.getPjxq()+"' and ((c.sqzq='xn' and c.pjxn=a.pjxn) or " +
								"(c.sqzq='xq' and c.pjxq=a.pjxq and c.pjxn=a.pjxn) or (c.sqzq='nd' and c.pjnd=a.pjnd))) "
						+ "group by xmdm,xmmc,pjxn,pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,xmje) order by xssx",
						makeQuery.getInputList(), new String[] {"xmdm", "xmmc"});
	}
	
	/**
	 * 查询学生个人获奖信息
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXshjmxResult(CdfzPjpyForm form, User user) throws Exception {
		List<HashMap<String, String>> jxjList = queryJxjdmList(form, user);
		StringBuffer sql = new StringBuffer();
		sql.append("select a.*,rownum r from (select a.pjxn,a.pjxq,a.xh,a.xm,a.bjmc!@@! from (select distinct pjxn,pjxq,xh,xm,bjmc,");
		int i = 0;
		List<String> colList = new ArrayList<String>(){};
		colList.add("xh");
		colList.add("xm");
		colList.add("bjmc");
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		StringBuffer userQuery  = new StringBuffer();
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[]{"nj", "xydm", "zydm", "bjdm"}, new String[]{"xh", "xm"}, form);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String maxSql = "";
		
		for (HashMap<String, String> map : jxjList) {
			i++;
			sql.append("(select xmmc from xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm and b.xmdm='");
			sql.append(map.get("xmdm"));
			sql.append("') mc");
			sql.append("" + i);
			sql.append(",");
			colList.add("mc" + i);
			maxSql += ",max(mc" + i + ") mc"+i;
		}
		String str = sql.toString();
		sql = new StringBuffer();
		String aastr = str.substring(0, str.length()-1);
		sql.append(aastr);
		sql.append(" from (select xmdm,xmmc,pjxn,pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc," +
				"bjmc,xmje,max(shjb),(select xssx from xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm) " +
				"xssx from xg_view_pjpy_xmsh_all a " +
				makeQuery.getQueryString()
				+ userQuery.toString()
				+ " and shzt='通过' " 
				+ " and exists (select 1 from  xg_pjpy_pjxmwhb c where c.pjxn='"+form.getPjxn()+"' and c.pjxq='"+form.getPjxq()+"' and  ((c.sqzq='xn' and c.pjxn=a.pjxn) or " +
				"(c.sqzq='xq' and c.pjxq=a.pjxq and c.pjxn=a.pjxn) or (c.sqzq='nd' and c.pjnd=a.pjnd)))" +
				" group by xmdm,xmmc,pjxn," +
				"pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,xmje) a order by xh) a group by a.pjxn,a.pjxq,a.xh,a.xm,a.bjmc order by xh) a");
		String ssql = sql.toString();
		ssql = ssql.replace("!@@!", maxSql);
		return commonQuery(ssql, "", makeQuery.getInputList(), colList.toArray(new String[0]), form);
	}
	
	/**
	 * 查询学生个人获奖信息 导出数据
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public List<String[]> queryXshjmxResultExport(CdfzPjpyForm form, User user) throws Exception {
		List<HashMap<String, String>> jxjList = queryJxjdmList(form, user);
		StringBuffer sql = new StringBuffer();
		sql.append("select pjxn,pjxq,xh,xm,xymc,zymc,bjmc,nj!@@! from (select distinct pjxn,pjxq,xh,xm,xymc,zymc,bjmc,nj,");
		int i = 0;
		List<String> colList = new ArrayList<String>(){};
		colList.add("pjxn");
		colList.add("pjxq");
		colList.add("xh");
		colList.add("xm");
		colList.add("xymc");
		colList.add("zymc");
		colList.add("bjmc");
		colList.add("nj");
		String userName=user.getUserName();
		String userType=user.getUserType();
		String userDep=user.getUserDep();
		StringBuffer userQuery  = new StringBuffer();
		if("true".equalsIgnoreCase(user.getFdyQx()) && "true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append("  union select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ");
			userQuery.append(" ) ");
		}else if("true".equalsIgnoreCase(user.getFdyQx())){
			userQuery.append(" and exists(select 1 from fdybjb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}else if("true".equalsIgnoreCase(user.getBzrQx())){
			userQuery.append(" and exists(select 1 from bzrbbb b where b.zgh='"+userName+"' and a.bjdm=b.bjdm ) ");
		}
		MakeQuery makeQuery = new MakeQuery();
		try {
			makeQuery.makeQuery(new String[]{"nj", "xydm", "zydm", "bjdm"}, new String[]{"xh", "xm"}, form);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		String maxSql = "";
		
		for (HashMap<String, String> map : jxjList) {
			i++;
			sql.append("(select xmmc from xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm and b.xmdm='");
			sql.append(map.get("xmdm"));
			sql.append("') mc");
			sql.append("" + i);
			sql.append(",");
			colList.add("mc" + i);
			maxSql += ",max(mc" + i + ") mc"+i;
		}
		String str = sql.toString();
		sql = new StringBuffer();
		String aastr = str.substring(0, str.length()-1);
		sql.append(aastr);
		sql.append(" from (select xmdm,xmmc,pjxn,pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc," +
				"bjmc,xmje,max(shjb),(select xssx from xg_pjpy_pjxmwhb b where a.xmdm=b.xmdm) " +
				"xssx from xg_view_pjpy_xmsh_all a " +
				makeQuery.getQueryString()
				+ userQuery.toString()
				+ " and shzt='通过' " 
				+ " and exists (select 1 from  xg_pjpy_pjxmwhb c where c.pjxn='"+form.getPjxn()+"' and c.pjxq='"+form.getPjxq()+"' and  ((c.sqzq='xn' and c.pjxn=a.pjxn) or " +
				"(c.sqzq='xq' and c.pjxq=a.pjxq and c.pjxn=a.pjxn) or (c.sqzq='nd' and c.pjnd=a.pjnd)))" +
				" group by xmdm,xmmc,pjxn," +
				"pjxq,pjnd,xh,xm,nj,xydm,zydm,bjdm,xymc,zymc,bjmc,xmje) a order by xh) a group by a.pjxn,a.pjxq,a.xh,a.xm,a.xymc,a.zymc,a.bjmc,a.nj order by xh");
		String ssql = sql.toString();
		ssql = ssql.replace("!@@!", maxSql);
		return commonQueryNotFy(ssql, "", makeQuery.getInputList(), colList.toArray(new String[0]), form);
	}
	
}
