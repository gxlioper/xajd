/**
 * <p>Title: 学生工作管理系统</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2008</p>
 * <p>Company: zfsoft</p>
 * <p>Author: LP</p>
 * <p>Version: 1.0</p>
 * <p>Time:2009-10-8 下午01:32:31</p>
 */
package xgxt.xsgygl.jhzyjsxy;


import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.base.DealString;
import xgxt.daoActionLogic.StandardOperation;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.FormModleCommon;
import xgxt.utils.Pages;
import xgxt.utils.String.StringUtils;
import xgxt.xsgygl.jhzyjsxy.yxlcqsz.YxlcqszModel;


public class GyglJhzyDAO {
	/**
	 * 获取相关信息
	 */
	public HashMap<String,String> dao_getInfo(String table,String[]colList,String querry){
		DAO dao = DAO.getInstance();
		if(colList==null){
			colList =  dao.getColumnName("select * from "+table);
			for(int i=0;i<colList.length;i++){
				colList[i]=colList[i].toLowerCase();
			}
		}
		String sql = "select * from "+table;
		return dao.getMap(sql+querry.toString(),new String[]{},colList);
	}
	/**
	 * 获取楼栋
	 */
	public List<HashMap<String,String>> getSsLdList(){
		//获取宿舍划分楼栋列表
		DAO dao             = DAO.getInstance();
//		StringBuffer querry = new StringBuffer(" where 1=1 ");		 
//		querry.append(Base.isNull(xiaoq)?" and 1=2 ":"  and xqdm = '"+xiaoq+"' ");
//		querry.append(Base.isNull(xbxd)?" and 1=2  ":" and xbxd like '%" + xbxd +"%' ");
		String sql = " select '' dm,'--请选择--' mc from dual union all select dm,mc from (select distinct lddm dm,ldmc mc from sslddmb where 1=1  order by to_number(dm))";
		return dao.getList(sql, new String[]{}, new String[] { "dm", "mc" });
	}
	/**
	 * 获取楼层
	 */
	public List<HashMap<String,String>> getSsLcList(String lddm){
		//获取楼层列表
		DAO dao = DAO.getInstance();
		String sql = "";
		StringBuffer querry = new StringBuffer();
		querry.append(Base.isNull(lddm)?" and 1=2 ":" and lddm='"+lddm+"' ");
		sql = " select '' dm,'--请选择--' mc from dual union all select dm,mc from " +
				"(select distinct cs dm,'第'||cs||'层' mc from ssxxb a where 1=1 "
				+ querry + "  order by to_number(dm))";
		List<HashMap<String, String>> csList = dao.getList(sql, new String[] {},
				new String[] { "dm","mc" });
		return csList;
	}
	/**
	 * 获取宿舍
	 *  @ lddm (楼栋代码)
	 *  @ lch (楼层号)
	 *  @ xxzt (是否有公寓辅导员  有：yes，没有 ：no)
	 */
	public List<HashMap<String,String>> getSsmList(String lddm,String lch,String xxzt,String fdy,String xn,String xq){
		//获取楼层列表
		DAO dao = DAO.getInstance();
		if("null".equals(lch)){
			lch = "";
		}
		if(StringUtils.isNull(xn)){
			xn = "%";
		}
		if(StringUtils.isNull(xq)){
			xq = "%";
		}
		StringBuffer query = new StringBuffer("and 1=1");
		
		if(StringUtils.isNotNull(lddm)){
			query.append(" and a.lddm = '"); 
			query.append(lddm); 
			query.append("'"); 
		}
		if(StringUtils.isNotNull(lch)){
			query.append(" and a.cs = '"); 
			query.append(lch); 
			query.append("'"); 
		}
		String sql = "";
		if("yes".equals(xxzt)){
			if(StringUtils.isNotNull(xn)){
				query.append(" and xn = '"); 
				query.append(xn); 
				query.append("'"); 
			}
			if(StringUtils.isNotNull(xq)){
				query.append(" and xq = '"); 
				query.append(xq); 
				query.append("'"); 
			}
			query.append(" and exists (select 1 from jhzy_gyfdyb b where a.lddm=b.lddm and a.cs=b.lcdm and a.qsh=b.qsh and xn like '"+xn+"' and xq like '"+xq+"'");
			if(StringUtils.isNotNull(fdy)){
				query.append(" and b.yhm='")
					 .append(fdy)
					.append("'");	
			}
			query.append(")");
			sql = "select a.lddm||'/'||a.cs||'/'||a.qsh||'/'||a.ssbh||'/'||yhm||'/'||xm dm,a.ldmc||'/第'||a.cs||'层/'||a.qsh||'/'||xm||'('||yhm||')' mc from view_ssxx a,jhzy_gyfdyb b where a.lddm=b.lddm and a.cs=b.lcdm and a.qsh=b.qsh "+query.toString()+" order by dm";	
		}else if("no".equals(xxzt)){
			query.append(" and not exists (select 1 from jhzy_gyfdyb b where a.lddm=b.lddm and a.cs=b.lcdm and a.qsh=b.qsh and xn like '"+xn+"' and xq like '"+xq+"') ");
			sql = "select lddm||'/'||cs||'/'||qsh||'/'||ssbh dm, ldmc||'/第'||cs||'层/'||qsh mc from view_ssxx a where 1=1 "+query.toString()+" order by dm";
		}
		
//		if("yes".equals(xxzt)){ 
//			sql = "select yhm||'/'||a.lddm || '/' || a.cs || '/' || a.qsh||'/'||a.ssbh dm,b.yhm||'/'||a.ldmc || '/第' || a.cs || '层/' || a.qsh mc from view_ssxx a,jhzy_gyfdyb b "+query+" and a.ssbh=b.ssbh order by dm ";
//		}else {
//			sql = "select lddm||'/'||cs||'/'||qsh||'/'||ssbh dm, ldmc||'/第'||cs||'层/'||qsh mc from view_ssxx a "+query.toString()+" order by dm";
			
		//		}
			List<HashMap<String, String>> ssList = dao.getList(sql, new String []{}, new String []{"dm","mc"});
		return ssList;
	}
	/**
	 * dwr 根据用户名刷用户的相关信息
	 */
	public HashMap<String,String> getUserInfo(String yhm){
		DAO dao = DAO.getInstance();
		String sql = "select c.yhm,c.xm,(select zmc from yhzb where zdm in (select zdm from yhb b where b.yhm=c.yhm)) zmc,"
			+ "(select bmmc from ZXBZ_XXBMDM where bmdm in (select szbm from yhb b where b.yhm=c.yhm)) bmmc, "
			+ "(select dwmc from bks_dwdmb where dwdm in (select dwdm from yhb b where b.yhm=c.yhm)) dwmc "
			+ " from yhb c where c.yhm = ? ";
		HashMap<String,String> map = dao.getMapNotOut(sql, new String[] { yhm });
		return map;
	}
	/**
	 * 获取公寓辅导员用户相关信息
	 */
	public List<HashMap<String,String>> getUserInfoList(){
		//获取楼层列表
		DAO dao = DAO.getInstance();
		String sql = "select a.yhm,a.xm||'('||a.yhm||')' xm,b.zmc from yhb a,yhzb b," +
				"ZXBZ_XXBMDM c,bks_dwdmb d where a.zdm<>'0001' and a.zdm=b.zdm and " +
				"a.szbm=c.bmdm and a.dwdm=d.dwdm order by  xm";
		List<HashMap<String,String>> list = dao.getList(sql, new String[] {}, new String[] { "yhm","xm" });
		return list;
	}
	/**
	 * 获取行政公寓辅导员用户相关信息
	 */
	public List<HashMap<String,String>> getXzGyfdyInfoList(){
		//获取楼层列表
		DAO dao = DAO.getInstance();
		String sql = "select distinct a.zgh yhm,a.zgh||'('||a.xm||')' xm from view_fdybbj a order by zgh";
		List<HashMap<String,String>> list = dao.getList(sql, new String[] {}, new String[] { "yhm","xm" });
		return list;
	}
	/**
	 * 辅导员信息保存
	 *  @ lddm (楼栋代码)
	 *  @ lch (楼层号)
	 *  @ xxzt (是否有公寓辅导员  有：yes，没有 ：no)
	 */
	public boolean dao_GyfdyIntoSave(GyglGyfdyModel model) throws Exception{
		boolean doFlag = true;
		DAO dao = DAO.getInstance();
		String[] colList ={"yhm","xn","xq","zmc","bmmc","dwmc","gzs","dzyx","lxdh","bz"};
		@SuppressWarnings("unused")
		String[] gyfsys = FormModleCommon.modelToStrings(colList, model);
//		String hflx = model.getHflx();
		String[] yhfss = model.getYhfss();
		String[] yhfssold = model.getYhfssbf();
		String[] ldlcqsh ;
		StringBuffer delsql = null;
		StringBuffer inssql = null;
		String[] delStr  = new String[yhfssold.length];
		String[] insStr  = new String[yhfss.length];
//		StringBuffer sqlbuf = new StringBuffer();
		
		if(StringUtils.isArrayNotNull(yhfssold)){
			for(int i = 0;i<yhfssold.length;i++){
				delsql = new StringBuffer();
				ldlcqsh = yhfssold[i].split("/");
				delsql.append("delete from jhzy_gyfdyb where 1=1");
				if (StringUtils.isNotNull(model.getYhm())) {
					delsql.append("and yhm='");
					delsql.append(model.getYhm());
					delsql.append("'");
				}
				delsql.append(" and xn='");
				delsql.append(model.getXn());
				delsql.append("' and xq='");
				delsql.append(model.getXq());
				delsql.append("' ");
//				if("ld".equals(hflx)){
//					delsql.append(" and lddm='");
//					delsql.append(ldlcqsh[0]);
//					delsql.append("'");
//				}else if("lc".equals(hflx)){
//					delsql.append(" and lddm='");
//					delsql.append(ldlcqsh[0]);
//					delsql.append("' and lcdm='");
//					delsql.append(ldlcqsh[1]);
//					delsql.append("'");
//				}else{
					delsql.append(" and lddm='");
					delsql.append(ldlcqsh[0]);
					delsql.append("' and lcdm='");
					delsql.append(ldlcqsh[1]);
					delsql.append("' and qsh='");
					delsql.append(ldlcqsh[2]);
					delsql.append("'");
//				}
				delStr[i] = delsql.toString();
			}
		}else{
			delStr = new String[]{"delete from jhzy_gyfdyb where 1=2"};
		}
		if(StringUtils.isArrayNotNull(yhfss)){
			for(int i = 0;i<yhfss.length;i++){
				inssql = new StringBuffer();
				String yhm = "";
				String xm = model.getXm();
				ldlcqsh = yhfss[i].split("/");
				if(StringUtils.isNotNull(model.getYhm())){
					yhm = model.getYhm();
				}else if(ldlcqsh.length>4 &&StringUtils.isNull(yhm)){
					yhm = DealString.toGBK(ldlcqsh[4]);
					xm = DealString.toGBK(ldlcqsh[5]);
				}
				inssql.append("insert into jhzy_gyfdyb(yhm,xm,xn,xq,zmc,bmmc,dwmc,gzs,dzyx,lxdh,bz,lddm,lcdm,qsh,ssbh) values('"+yhm+"','"+xm+"','"+model.getXn()+"','"+model.getXq()+"','"+model.getZmc()+"','"+model.getBmmc()+"','"+model.getDwmc()+"','"+model.getGzs()+"','"+model.getDzyx()+"','"+model.getLxdh()+"','"+model.getBz()+"'");
				inssql.append(",'"+ldlcqsh[0]+"'");
				inssql.append(",'"+ldlcqsh[1]+"'");
				inssql.append(",'"+ldlcqsh[2]+"'");
				inssql.append(",'"+ldlcqsh[3]+"'");
				inssql.append(")");
				insStr[i] = inssql.toString();
			}
		}
		if(!StringUtils.isArrayNotNull(yhfss)){
				inssql = new StringBuffer();
				String yhm = "";
				String xm = model.getXm();
				if(StringUtils.isNotNull(model.getYhm())){
					yhm = model.getYhm();
				}
				delStr = new String[]{"delete from jhzy_gyfdyb where yhm='"+yhm+"' and xn='"+model.getXn()+"' and xq='"+model.getXq()+"'"};
				inssql.append("insert into jhzy_gyfdyb(yhm,xm,xn,xq,zmc,bmmc,dwmc,gzs,dzyx,lxdh,bz) values('"+yhm+"','"+xm+"','"+model.getXn()+"','"+model.getXq()+"','"+model.getZmc()+"','"+model.getBmmc()+"','"+model.getDwmc()+"','"+model.getGzs()+"','"+model.getDzyx()+"','"+model.getLxdh()+"','"+model.getBz()+"'");
				inssql.append(")");
				insStr = new String[]{inssql.toString()};
	}
		String[] exesql =  dao.unionArray(delStr,insStr);
		int[] array     = dao.runBatch(exesql);
		doFlag = dao.checkBatch(array);
		return doFlag;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public List<String[]> dao_QueryGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
			DAO dao = DAO.getInstance();
			List<String[]> list = new ArrayList<String[]>();
			String[] queryList = {"xn","xq","yhm"};
			StringBuffer query = FormModleCommon.makeQuery(queryList, model);
			String[] colList ={"yhm","xn","xq","zmc","bmmc","dwmc","gzs","lxdh","xqdm"};
			String  sql = "select * from (select distinct yhm,xn,xq,zmc,bmmc,dwmc,gzs,lxdh,rownum r,xqdm " 
				+"from (select distinct yhm,xn,(select b.xqmc from xqdzb b where b.xqdm=xq) xq," 
				+"zmc,bmmc,dwmc,gzs,lxdh,xq xqdm from jhzy_gyfdyb a "+query+")) where r<="
			    + (myForm.getPages().getStart() + myForm.getPages().getPageSize())
			    + " and r> "
			    + myForm.getPages().getStart();
			list.addAll(dao.rsToVator(sql, new String[] {}, colList));
			String sqlPagination = "select count(distinct yhm) count from jhzy_gyfdyb where 1=1";
			myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs( sqlPagination, new String[] {},"count")));
		return list;
	}
	/**
	 * 行政公寓辅导员信息查询
	 */
	public List<String[]> dao_QueryXzGyfdyInfo(GyglGyfdyModel model,GyglJhzyForm myForm) throws Exception{
			DAO dao = DAO.getInstance();
			List<String[]> list = new ArrayList<String[]>();
//			String[] queryList = {"zgh"};
			String zgh = myForm.getZgh();
			String xm = myForm.getXm();
			String xydm = myForm.getXydm();
			String lddm = myForm.getLddm();
			String lcdm = myForm.getLcdm();
			String qsh = myForm.getQsh();
			StringBuffer query = new StringBuffer("where 1=1 ");
			query.append(Base.isNull(zgh) ? " and 1=1" : " and zgh='"+zgh.trim()+ "'");
			query.append(Base.isNull(xm) ? " and 1=1" : " and xm like '%"+xm.trim()+ "%'");
			query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm.trim()+ "'");
			query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm.trim()+ "'");	
			query.append(Base.isNull(lcdm) ? " and 1=1" : " and cs='"+lcdm.trim()+ "'");
			query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh.trim()+ "'");
			
//			StringBuffer query = FormModleCommon.makeQuery(queryList, model);
			String[] colList ={"ldmc","cs","qsh","zgh","xm","xymc"};
			String  sql = "select  distinct lddm,ldmc,cs,qsh,zgh,xm,xydm,xymc from (select distinct rownum r,lddm,ldmc,cs,qsh,zgh,xm,xydm,xymc from view_xzgyfdy a "+query+") where r<="
			    + (myForm.getPages().getStart() + myForm.getPages().getPageSize())
			    + " and r> "
			    + myForm.getPages().getStart();
			list.addAll(dao.rsToVator(sql, new String[] {}, colList));
			String sqlPagination = "select count(distinct zgh) count from view_xzgyfdy where 1=1";
			myForm.getPages().setMaxRecord(Integer.parseInt(dao.getOneRs( sqlPagination, new String[] {},"count")));
		return list;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public HashMap<String, String> dao_GyfdyInfo(GyglGyfdyModel model) throws Exception{
			DAO dao = DAO.getInstance();
			String[] colList ={"yhm","xm","xn","xq","zmc","bmmc","dwmc","gzs","dzyx","lxdh","bz"};
			String  sql = "select distinct yhm,xm,xn,xq,zmc,bmmc,dwmc,gzs,dzyx,lxdh,bz from jhzy_gyfdyb where yhm=? and xn=? and xq=?";
			HashMap<String, String> map = dao.getMap(sql, new String[]{model.getYhm(),model.getXn(),model.getXq()},colList);
			return map;
	}
	/**
	 * 文明公寓楼信息查询
	 */
	public HashMap<String, String> dao_wmgylInfo(GyglGyfdyModel model) throws Exception{
			DAO dao = DAO.getInstance();
			String[] colList ={"pk","xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","gywmgs",
					"fdyyj","xyyj","xxyj","xxsh","xxshsj"};
			String  sql = "select id pk,xn,nj,lzm,qss,xss,xydm,wshgl,wsyxl,xjqsl,gywmgs,fdyyj,xyyj,xxyj,xxsh,xxshsj from jhzy_wmgylsqb where id=?";
			HashMap<String, String> map = dao.getMap(sql, new String[]{model.getYhm()},colList);
			return map;
	}
	/**
	 * 文明公寓楼信息查询
	 */
	public HashMap<String, String> dao_wmgylInfosh(String id) throws Exception{
			DAO dao = DAO.getInstance();
			String[] colList ={"pk","xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","gywmgs",
					"fdyyj","xyyj","xxyj","xxsh","xxshsj"};
			String  sql = "select id pk,xn,nj,(select distinct ldmc from view_ssxx b where a.lzm=b.lddm) lzm,qss,xss,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,wshgl,wsyxl,xjqsl,gywmgs,fdyyj,xyyj,xxyj,xxsh,xxshsj from jhzy_wmgylsqb a where id=?";
			HashMap<String, String> map = dao.getMap(sql, new String[]{id},colList);
			return map;
	}
	/**
	 * 公寓辅导员信息删除
	 */
	public boolean dao_DelGyfdyInfo(String yhm,String xn,String xq,HttpServletRequest request) throws Exception{
			String sql = "delete from jhzy_gyfdyb where yhm='"+yhm+"' and xn='"+xn+"' and xq='"+xq+"'" ;
			boolean result = StandardOperation.delete(sql, "jhzy_gyfdyb", request);
			return result;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public boolean dao_selectIntoFdy(String dqxn,String dqxq,String xn,String xq,HttpServletRequest request) throws Exception{
			DAO dao = DAO.getInstance();
			String sql = "delete from jhzy_gyfdyb where xn='"+xn+"' and xq='"+xq+"'";
			StandardOperation.delete(sql, "jhzy_gyfdyb", request);
			StringBuffer buf = new StringBuffer();
			buf.append("insert into jhzy_gyfdyb(yhm,xn,xq,zmc,bmmc,dwmc,gzs,dzyx,lxdh,bz,lddm,lcdm,ssbh,xm,qsh) select yhm,'");
			buf.append(xn);
			buf.append("' xn,'");
			buf.append(xq);
			buf.append("' xq,zmc,bmmc,dwmc,gzs,dzyx,lxdh,bz,lddm,lcdm,ssbh,xm,qsh from jhzy_gyfdyb where xn='");
		    buf.append(dqxn);
			buf.append("' and xq='");
			buf.append(dqxq);
			buf.append("'"); 
			return dao.runUpdateTab(buf.toString());
	}
	/**
	 * 文明公寓楼保存
	 */
	public boolean dao_saveWmgyl(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
			String[] colList = {"xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","gywmgs",
					"fdyyj","xyyj","xxyj"};
			String[] gyfdys = FormModleCommon.modelToStrings(colList, model);
			return StandardOperation.insert("jhzy_wmgylsqb", colList, gyfdys, request);
	}
	/**
	 * 文明公寓楼是否已经申请
	 */
	public boolean dao_isExists(GyglGyfdyModel model) throws Exception{
			boolean isexists = true;
			DAO dao = DAO.getInstance();
			String sql = "select id pk from jhzy_wmgylsqb where lzm=? and xn=? and xydm=?";
			String[] inputValue = {model.getLzm(),model.getXn(),model.getXydm()};
			String[] outputValue = {"pk"};
			HashMap<String, String> map = dao.getMap(sql, inputValue, outputValue);
			if(map != null && map.size()>0){
				isexists = false;
			}
			return isexists;
	}
	/**
	 *文明公寓楼查询
	 */
	public  ArrayList<String[]> getWmgylCxList(String yesNo,String userType,String userName, GyglGyfdyModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		String xydm = DealString.toGBK(model.getXydm());
		String nj = DealString.toGBK(model.getNj());
		String xn = DealString.toGBK(model.getXn());
		String lzm = DealString.toGBK(model.getLzm());
		String xh = "";
		xh = Base.isNull(xh) ? "%":"%";
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");	
		query.append(Base.isNull(lzm) ? " and 1=1" : " and lzm='"+lzm+ "'");
		query.append(Base.isNull(yesNo)?"":" and xxsh = '"+yesNo+"' ");	
		String[] colList = new String[]{"id","xn","nj","lzm","qss","xss","xydm","wshgl","wsyxl","xjqsl","xxsh"};
		String sql = "(select  rownum r,id,xn,nj,(select distinct ldmc from view_ssxx b where a.lzm=b.lddm) lzm,qss,xss,(select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xydm,wshgl,wsyxl,xjqsl,xxsh from jhzy_wmgylsqb a "+query.toString()+") ";
		return commonQuery(sql, query.toString(), new String[] {}, colList,"", model);		
	}
	/**
	 *是否楼长
	 */
	public HashMap<String, String> dao_isLz(String yhm) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
		HashMap<String, String> map = new HashMap<String, String>();
		String[] colList = new String[]{"lzm","ldmc","lz","zz","xm","xb","nj","xydm","zydm","bjdm","xymc"};
		String sql = " select lddm lzm,ldmc,lz,zz,xm,xb,nj,xydm,zydm,bjdm,xymc from view_lzxx where lz=?";
		map = dao.getMap(sql, new String[]{yhm}, colList);
		return map;		
	}
	/**
	 * Common query.
	 * 
	 * @param innerSql 查询内嵌sql语句
	 * @param query 查询条件
	 * @param inPutList the in put list
	 * @param colList the col list
	 * @param sql the sql
	 * @param model the model
	 * 
	 * @return the array list< string[]>
	 * 
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws SecurityException the security exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws NoSuchMethodException the no such method exception
	 */
	public static ArrayList<String[]> commonQuery(String innerSql,String query,String [] inPutList,
			String [] colList,String sql, Object model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		DAO dao = DAO.getInstance();
    //    查询用 获得数组的通用方法
		ArrayList<String[]> rs = null;
		//取到colList的长度
		int size = colList.length-1;
		Class myClass = model.getClass();
		Pages pages = (Pages)myClass.getMethod("getPages",(Class[]) null).invoke(model,(Object[]) null);
		String count = dao.getOneRs("select count(*) count from "+innerSql, inPutList, "count");
		pages.setMaxRecord(Integer.parseInt(StringUtils.isNull(count)?"0":count));
		if(sql.equalsIgnoreCase("")){
			StringBuffer sqlBuffer = new StringBuffer("select * from (select rownum r,");
			for(int i = 0; i<(size);i++){
				sqlBuffer.append(colList[i]);
				sqlBuffer.append(", ");
			}
			sqlBuffer.append(colList[size]);
			sqlBuffer.append(" from ");
			sqlBuffer.append(innerSql); 
//			sqlBuffer.append(query);
			sqlBuffer.append(")");
			sqlBuffer.append(" where r > "); 
			sqlBuffer.append(pages.getStart());
			sqlBuffer.append(" and r <= ");
			sqlBuffer.append((pages.getStart() + pages.getPageSize()));
			rs = dao.rsToVator(sqlBuffer.toString(), inPutList, colList);
		}else{
			rs = dao.rsToVator(sql, inPutList, colList);
		}
		return rs;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public Boolean dao_WmgylUpdate(GyglGyfdyModel model,HttpServletRequest request) throws Exception{
//			DAO dao = DAO.getInstance();
			String[] colList = {"qss","xss","wshgl","wsyxl","xjqsl","gywmgs",
					            "fdyyj","xyyj","xxyj"};
			String[] inputList = FormModleCommon.modelToStrings(colList, model);
			String pkValue = model.getYhm();
			Boolean result = StandardOperation.update("jhzy_wmgylsqb", colList, inputList, "id", pkValue, request);
		return result;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public String dao_WmgylDel(String pkStr,HttpServletRequest request) throws Exception{
			DAO dao = DAO.getInstance();
			String pks = request.getParameter("pkVStr");
			StringBuffer sb = new StringBuffer();
			String[] keys = pks.split("!!");
			String[] pksql = new String[] {};
			String sql = "";
			for (int i = 0; i < keys.length; i++) {
				String pk = DealString.toGBK(keys[i]);// 得到主键
				sql = "delete from jhzy_wmgylsqb where id = '" + pk + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
			pksql = sb.toString().split("!!#!!");
			int[] judge = dao.runBatch(pksql);
			String whichpk = "";
			for (int i = 0; i < judge.length; i++) {
				if (judge[i] < 0) {
					whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
				}
			}
			return whichpk;
	}
	/**
	 * 公寓辅导员信息查询
	 */
	public String dao_WmgylSh(String pkStr,String str,HttpServletRequest request) throws Exception{
			DAO dao = DAO.getInstance();
			String pks = request.getParameter("pkVStr");
			StringBuffer sb = new StringBuffer();
			String[] keys = pks.split("!!");
			String[] pksql = new String[] {};
			String sql = "";
			for (int i = 0; i < keys.length; i++) {
				String pk = DealString.toGBK(keys[i]);// 得到主键
				sql = "update jhzy_wmgylsqb set xxsh='"+str+"' where id = '" + pk + "'";
				sb.append(sql);
				sb.append("!!#!!");
			}
			pksql = sb.toString().split("!!#!!");
			int[] judge = dao.runBatch(pksql);
			String whichpk = "";
			for (int i = 0; i < judge.length; i++) {
				if (judge[i] < 0) {
					whichpk = whichpk + " 第" + String.valueOf(i) + "条数据删除失败;\n";
				}
			}
			return whichpk;
	}
	/**
	 * 公寓辅导员信息单个审核
	 */
	public Boolean dao_WmgyldgSh(String pkStr,String str,HttpServletRequest request) throws Exception{
			DAO dao = DAO.getInstance();
			Boolean result = false;
			String sql = "update jhzy_wmgylsqb set xxsh='"+str+"' where id = '" + pkStr + "'";
			int[] judge = dao.runBatch(new String[]{sql});
			if(judge[0]>0){
				result = true;
			}
			return result;
	}
	 /**
     * 辅导员入住信息保存 
     */
	public boolean dao_fdyRzXxSave(FdyRzxxModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String zgh = model.getZgh();
		String xydm = DealString.toGBK(model.getXydm());
		String xm   =model.getXm();
		String xb   = DealString.toGBK(model.getXb());
		String zw   = DealString.toGBK(model.getZw());
		String lxdh = DealString.toGBK(model.getLxdh());
		String lddm = model.getLddm();
		String lc   = model.getLc(); 
		String qsh   = model.getQsh(); 
		boolean done = false;
		String flag = dao.returntag("select * from fdyrzxxb where zgh=?", new String[]{zgh});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into fdyrzxxb(zgh,xm,xydm,xb,zw,lddm,lc,qsh,lxdh)values(?,?,?,?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{zgh,xm,xydm,xb,zw,lddm,lc,qsh,lxdh});
		}else{
			sql="update fdyrzxxb set xm=?,xydm=?,xb=?,zw=?,lddm=?,lc=?,qsh=?,lxdh=? where zgh=? ";
			done = dao.runUpdate(sql,new String[]{xm,xydm,xb,zw,lddm,lc,qsh,lxdh,zgh});			
		}
		return done;
	}
	 /**
     * 辅导员入住信息查询
     */
	public  ArrayList<String[]> getFdyRzxxList(FdyRzxxModel model) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		 //DAO dao = DAO.getInstance();					
		String qsh = DealString.toGBK(model.getQsh());	
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());	
		String zgh = model.getZgh();
		zgh = Base.isNull(zgh) ? "%" : zgh;		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");
		query.append(" and zgh like ?");
		query.append(" and xm like ?");	
		
		String[] colList = new String[]{"zgh","xm","xymc","ldmc","qsh","lxdh"}; 
		return CommonQueryDAO.commonQuery("view_fdyrzxx", query.toString(), new String[] {zgh,xm}, colList,"", model);		
	}
	/**
	 * 获取审核下拉框信息
	 */
	public List<HashMap<String, String>>  dao_getChkList(){
		DAO dao = DAO.getInstance();		
		return dao.getChkList(3);
	}
	/**
	 * 辅导员入住信批量删除
	 */
	public boolean dao_fdyRzxxDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete fdyrzxxb where zgh= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
     * 公寓党建信息查询
     */
	public ArrayList<String[]> getGydjxxList(GydjglxxbModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String qsh = DealString.toGBK(model.getQsh());	
		String lddm = DealString.toGBK(model.getLddm());
		String cs = DealString.toGBK(model.getCs());
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(xydm) ? "" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? "" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? "" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? "" : " and nj='"+nj+ "'");
		query.append(Base.isNull(lddm) ? "" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(cs) ? "" : " and cs='"+cs+ "'");
		query.append(Base.isNull(qsh) ? "" : " and qsh='"+qsh+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");	
		
		String[] colList = new String[]{"guid","xh","xm","xymc","ldmc","qsh","cjhdsj","hdzt"}; 
		return CommonQueryDAO.commonQuery("view_gydjglxxb", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * 公寓党建信息批量删除
	 */
	public boolean dao_gydjDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete gydjglxxb where guid= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
     * 公寓党建信息保存 
     */
	public String[] dao_gydjXxSave(GydjglxxbModel model,HttpServletRequest request) throws Exception{
		DAO dao = DAO.getInstance();
		String guid = DealString.toGBK(model.getGuid());
		String xh = DealString.toGBK(model.getXh());
		String ssbh = DealString.toGBK(model.getSsbh());
		String khdx   = DealString.toGBK(model.getKhdx());
		String rdkcsj   = DealString.toGBK(model.getRdkcsj());
		String ssgyzb   = DealString.toGBK(model.getSsgyzb());
		String zbfzr = DealString.toGBK(model.getZbfzr());
		String cjhdsj = DealString.toGBK(model.getCjhdsj());
		String hdzt   = DealString.toGBK(model.getHdzt()); 
		String hdnr   = DealString.toGBK(model.getHdnr()); 
		boolean done = false;
		if(guid.length() != 32){
			done = StandardOperation.insert("gydjglxxb", new String[] { "xh",
					"ssbh", "khdx", "rdkcsj", "ssgyzb", "zbfzr", "cjhdsj",
					"hdzt", "hdnr" }, new String[] { xh, ssbh, khdx, rdkcsj,
					ssgyzb, zbfzr, cjhdsj, hdzt, hdnr }, request);
			if (done) {
				guid = dao.getOneRs(
					"select guid from gydjglxxb where xh=? and ssbh=? and khdx=? and rdkcsj=? and ssgyzb=? and zbfzr=? and cjhdsj=? and hdzt=? and hdnr=? and rownum=1",
					new String[] { xh, ssbh, khdx, rdkcsj, ssgyzb, zbfzr,
							cjhdsj, hdzt, hdnr }, "guid");
			}
		}else{
			done = StandardOperation.update("gydjglxxb", new String[] { "xh",
					"ssbh", "khdx", "rdkcsj", "ssgyzb", "zbfzr", "cjhdsj",
					"hdzt", "hdnr" }, new String[] { xh, ssbh, khdx, rdkcsj,
					ssgyzb, zbfzr, cjhdsj, hdzt, hdnr }, "guid", guid, request);
		}
		return new String[] { done ? "1" : "0", guid };
	}   
    /**
     * 寝室长信息保存 
     */
	public boolean dao_QszXxSave(QszXxModel model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		String ssbh=DealString.toGBK(model.getSsbh());
		String qsz=DealString.toGBK(model.getXh());
		String rzrq=DealString.toGBK(model.getRzrq());
		String lxdh=model.getLxdh();
		String dzyx=model.getDzyx();
		String bz=model.getBz();
		String sfzz = DealString.toGBK(model.getSfzz());
		String lzrq=DealString.toGBK(model.getLzrq());
		boolean done = false;
		if(Base.isNull(pkValue)){
			pkValue = qsz+ssbh+rzrq;
		}
		String flag = dao.returntag("select * from qszxxb where qsz||ssbh||rzrq=?", new String[]{pkValue});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into qszxxb(ssbh,qsz,rzrq,lxdh,dzyx,bz,lzrq)values(?,?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{ssbh,qsz,rzrq,lxdh,dzyx,bz,lzrq});
		}else{
			sql="update qszxxb set ssbh=?,qsz=?,rzrq=?,lxdh=?,dzyx=?,bz=?,lzrq=?,sfzz=? where qsz||ssbh||rzrq=? ";
			done = dao.runUpdate(sql,new String[]{ssbh,qsz,rzrq,lxdh,dzyx,bz,lzrq,sfzz,pkValue});			
		}
		return done;
	}
	/**
     * 寝室长信息查询
     */
	public  ArrayList<String[]> getQszXxCxList(QszXxModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		 //DAO dao = DAO.getInstance();					
		String qsh = DealString.toGBK(model.getQsh());	
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());	
		String nj = DealString.toGBK(model.getNj());	
		String xydm = DealString.toGBK(model.getXydm());
		String zydm = DealString.toGBK(model.getZydm());
		String bjdm = DealString.toGBK(model.getBjdm());
		String sfzz = model.getSfzz();
		String xh = model.getXh();
		xh = Base.isNull(xh) ? "%" : xh;		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");
		query.append(Base.isNull(nj) ? " and 1=1" : " and nj='"+nj+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? " and 1=1" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? " and 1=1" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(sfzz) ? " and 1=1" : " and sfzz='"+sfzz+ "'");
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
	
		query.append(" and qsz like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","qsz","xm","xb","xymc","bjmc","ldmc","qsh","lxdh","sfzz"}; 
		return CommonQueryDAO.commonQuery("view_qszxx", query.toString(), new String[] {xh,xm}, colList,"", model);		
	}
	/**
	 * 寝室长信息批量删除
	 */
	public boolean dao_qszDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete qszxxb where qsz||ssbh||rzrq= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
		/**
     * 寝室卫生检查查询
     */
	public ArrayList<List> dao_qswsjcQuery(GyglGyfdyModel model) throws Exception{
		DAO dao = DAO.getInstance();
		ArrayList<List> result = new ArrayList<List>();
		String lddm = model.getLddm();
		String lc = model.getLcdm();
		String qsh = model.getQsh();
		String jcrq = model.getJcsj();
		String wsdj = model.getGuid();
		String jcsj = model.getJcsj();
		String xn = model.getXn();
		String[] colList= new String[]{"wsjc","ldmc","cs","qsh","dm","mc","wsdj","ssbh","zghxm","xymc"};
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		StringBuffer query1 = new StringBuffer();
		querry.append(Base.isNull(wsdj)?"":" and wsdj='"+wsdj+"' ");
	    query1.append(Base.isNull(jcsj)?"":" and jcrq='"+jcsj+"' ");
	    query1.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		//		String sql = "select * from (select a.wsjc, a.lddm, a.ldmc, a.cs, a.qsh, a.ssbh,a.dm,a.mc,a.dqdm,c.jcrq,c.wsdj from (select a.lddm || '/' || a.cs || '/' || a.qsh||'/'||ssbh wsjc,a.lddm,a.ldmc,a.cs,a.qsh,a.lddm || '/' || a.cs || '/' || a.qsh || '/' || b.dm || '/' ||a.ssbh dm,a.ssbh,b.mc,b.dm dqdm from view_ssxx a left join jhzy_dgldqb b on 1 = 1) a left join jhzy_qswsjcb c on a.ssbh = c.ssbh and a.dqdm = c.dqdm) "+querry.toString()+" order by lddm, cs, qsh, dqdm";
//		String sql = "select * from (select distinct a.wsjc, a.lddm, a.ldmc, a.cs, a.qsh, a.ssbh,a.dm,a.mc,a.dqdm,c.jcrq,c.wsdj from (select a.lddm || '/' || a.cs || '/' || a.qsh||'/'||ssbh wsjc,a.lddm,a.ldmc,a.cs,a.qsh,a.lddm || '/' || a.cs || '/' || a.qsh || '/' || b.dm || '/' ||a.ssbh dm,a.ssbh,b.mc,b.dm dqdm from view_ssxx a left join jhzy_dgldqb b on 1 = 1) a left join  (select distinct jcrq,wsdj,ssbh from jhzy_qswsjcb where 1=1 "+query1.toString()+") c on a.ssbh=c.ssbh)"+querry.toString()+" order by lddm, cs, qsh, dqdm";
	    String sql = "select * from (select distinct a.wsjc, a.lddm, a.ldmc, a.cs, a.qsh, a.ssbh,a.dm,a.mc,a.dqdm,c.jcrq,c.wsdj,zghxm,xymc from (select a.lddm || '/' || a.cs || '/' || a.qsh||'/'||ssbh wsjc,a.lddm,a.ldmc,a.cs,a.qsh,a.lddm || '/' || a.cs || '/' || a.qsh || '/' || b.dm || '/' ||a.ssbh dm,a.ssbh,b.mc,b.dm dqdm from view_ssxx a left join jhzy_dgldqb b on 1 = 1) a left join  (select distinct jcrq,wsdj,ssbh from jhzy_qswsjcb where 1=1 "+query1.toString()+") c on a.ssbh=c.ssbh left join (select ssbh,lower(ltrim(max(sys_connect_by_path(zghxm,',')),',')) zghxm from (select row_number() over(partition by ssbh order by ssbh) pno,row_number() over(partition by ssbh order by ssbh)-1 sno,ssbh,zghxm from (select distinct ssbh,zghxm from view_xzgyfdy) where 1=1) start with pno = 1 connect by prior pno = sno and prior ssbh=ssbh group by ssbh) d on a.ssbh=d.ssbh left join (select (select distinct xymc from view_njxyzybj b where a.xydm=b.xydm) xymc,ssbh from ssfpb a) e on a.ssbh=e.ssbh)"+querry.toString()+" order by lddm, cs, qsh, dqdm";
	    List<HashMap<String, String>> rsTem= dao.getArrayList(sql,new String[] {}, colList);
		List<String[]> oneGnmkdm = new ArrayList<String[]>();
		if(rsTem.size()>0){
			String wsjc = "";
    		for(int i=0;i<rsTem.size();i++){   			
    			String ldmcV = rsTem.get(i).get("ldmc");
    			ldmcV = Base.isNull(ldmcV)?" ":ldmcV;
    			String csV = rsTem.get(i).get("cs");
    			csV = Base.isNull(csV)?" ":csV;
    			String qshV = rsTem.get(i).get("qsh");
    			qshV = Base.isNull(qshV)?" ":qshV;
    			String dmV = rsTem.get(i).get("dm");
    			dmV = Base.isNull(dmV)?" ":dmV;
    			String mcV = rsTem.get(i).get("mc");
    			mcV = Base.isNull(mcV)?" ":mcV;
    			String zghxmV = rsTem.get(i).get("zghxm");
    			zghxmV = Base.isNull(zghxmV)?" ":zghxmV;
    			String xymcV = rsTem.get(i).get("xymc");
    			xymcV = Base.isNull(xymcV)?" ":xymcV;
    			String wsdjV = "";
    			String qswsbzV = "";
    			HashMap<String, String> map = dao.getMap("select wsdj,qswsbz from jhzy_qswsjcb where ssbh=? and jcrq=? and xn=?",
    					new String[]{rsTem.get(i).get("ssbh"),jcrq,xn}, new String[]{"wsdj","qswsbz"});
    			wsdjV = map.get("wsdj");
    			qswsbzV = map.get("qswsbz");
    			if (StringUtils.isNull(wsdjV)) {
					wsdjV = rsTem.get(i).get("wsdj");
				}
    			qswsbzV = Base.isNull(qswsbzV)?"":qswsbzV;
    			wsdjV = Base.isNull(wsdjV)?" ":wsdjV;
    			if(Base.isNull(wsjc)){
    				wsjc =rsTem.get(i).get("wsjc");	
					String[] oneRow = {wsjc,ldmcV,csV,qshV,wsdjV,dmV,mcV,qswsbzV,zghxmV,xymcV};
					oneGnmkdm.add(oneRow);
				} else if(wsjc.equalsIgnoreCase(rsTem.get(i).get("wsjc"))){
					oneGnmkdm.add(new String[]{wsjc,ldmcV,csV,qshV,wsdjV,dmV,mcV,qswsbzV,zghxmV,xymcV});
				} else {
					result.add(oneGnmkdm);
					oneGnmkdm = new ArrayList<String[]>();
					wsjc =rsTem.get(i).get("wsjc");	
					oneGnmkdm.add(new String[]{wsjc,ldmcV,csV,qshV,wsdjV,dmV,mcV,qswsbzV,zghxmV,xymcV});
					wsjc = null;
				}
    		}
    		result.add(oneGnmkdm);
    	}
		return result;
	}
	/**
	 *  寝室卫生检查保存
	 * @throws SQLException 
	 */
	public boolean dao_qswsjcSave(GyglGyfdyModel model) throws SQLException{
		DAO dao  = DAO.getInstance();
		boolean done = false;
		
		String oldValue = model.getCheckedValue();
		String[] oldValueList = oldValue.split("!!");
		String[] olddelSql = new String[oldValueList.length];
		String xn = model.getXn();
		String xq = model.getXq();
		if (StringUtils.isNull(xn)) {
			xn = Base.currXn;
		}
		if (StringUtils.isNull(xq)) {
			xq = Base.currXq;
		}
		String jcsj = model.getJcsj();
		String[] bz = model.getQswsbz();
		if(oldValueList.length>0 && !"".equals(oldValue)){
			for (int i = 0; i < oldValueList.length; i++) {
				String[] sqlold = oldValueList[i].split("/");
				StringBuffer delbuf = new StringBuffer();
				delbuf.append("delete from jhzy_qswsjcb where jcrq = '"+jcsj+"' and ssbh='");
				if (sqlold.length==4) {
					delbuf.append(sqlold[3]);
				}else {
					delbuf.append(sqlold[4]);
				}
				delbuf.append("'");
				olddelSql[i] = delbuf.toString();
			}
		}
		String[] lxids = model.getLxids();
		String wsdjs[] = model.getWsdj();
		
		String[]delSql = new String[lxids.length];
		String[]insSql = new String[lxids.length];
		String ssbhV = "";
		String issame = "";
		int intsame = 0;
		int j = 0;
		for(int i=0;i<lxids.length;i++){
			String lddm = "";
			String cs = "";
			String qsh = "";
			String dm = "";
			String ssbh = "";
			String wsdj = "";
			
			if(i<lxids.length){
				String[] sacon = lxids[i].split("/");
				if(sacon.length == 4){
					lddm = sacon[0];
					cs = sacon[1];
					qsh = sacon[2];
					ssbh = sacon[3];
					wsdj = wsdjs[j];
					if(StringUtils.isNull(issame)){
						issame = sacon[3];
					}else {
						if(!issame.equals(sacon[3])){
							intsame++;
							issame = sacon[3];
						}
					}
				}else {
					lddm = sacon[0];
					cs = sacon[1];
					qsh = sacon[2];
					dm = sacon[3];
					ssbh = sacon[4];
					wsdj = wsdjs[j];
					if(StringUtils.isNull(issame)){
						issame = sacon[4];
					}else {
						if(!issame.equals(sacon[4])){
							intsame++;
							issame = sacon[4];
						}
					}
				}
				if(StringUtils.isNull(ssbhV)){
					ssbhV = ssbh;
				}else if(!ssbhV.equals(ssbh)){
					j++;
					wsdj = wsdjs[j];
					ssbhV = ssbh;
				}
				
				
			}
			StringBuffer delbuf = new StringBuffer();
			delbuf.append("delete from jhzy_qswsjcb where jcrq = '"+jcsj+"' and ssbh='");
			delbuf.append(ssbh);
			delbuf.append("'");
			delSql[i] = delbuf.toString();
			StringBuffer insbuf = new StringBuffer();
			insbuf.append("insert into jhzy_qswsjcb(lddm,cs,qsh,dqdm,ssbh,xn,xq,jcrq,wsdj,qswsbz)values('");
			insbuf.append(lddm);
			insbuf.append("','");
			insbuf.append(cs);
			insbuf.append("','");
			insbuf.append(qsh);
			insbuf.append("','");
			insbuf.append(dm);
			insbuf.append("','");
			insbuf.append(ssbh);
			insbuf.append("','");
			insbuf.append(xn);
			insbuf.append("','");
			insbuf.append(xq);
			insbuf.append("','");
			insbuf.append(jcsj);
			insbuf.append("','");
			insbuf.append(wsdj);
			insbuf.append("','");
			insbuf.append(bz[intsame]);
			insbuf.append("')");
			insSql[i] = insbuf.toString();
		}
		String[] exesql = dao.unionArray(olddelSql, dao.unionArray(delSql, insSql));
		int[] array = null;
		array = dao.runBatch(exesql);
		done = dao.checkBatch(array);
		return done;
	}
	/**
	 * 获取寝室卫生检查存在id
	 */
	public String getqswsjcChecked(GyglGyfdyModel model) throws Exception {
		DAO dao = DAO.getInstance();
		String lddm = model.getLddm();
		String lc = model.getLcdm();
		String qsh = model.getQsh();
		String jcsj = model.getJcsj();
		String xn = model.getXn();
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(jcsj)?"":" and jcrq='"+jcsj+"' ");
		querry.append(Base.isNull(xn)?"":" and xn='"+xn+"' ");
		String sql = "select lddm||'/'||cs||'/'||qsh||'/'||dqdm||'/'||ssbh dqdm from jhzy_qswsjcb "+querry+" order by lddm,cs,qsh,dqdm";
			String [] mkcds = dao.getArray(sql, new String []{}, "dqdm");
			StringBuffer mkcdTmp    =  new StringBuffer();
			for(int i = 0;i<mkcds.length;i++){
				mkcdTmp.append(mkcds[i]);
				if(i!=mkcds.length-1){
					mkcdTmp.append("!!");
					}
				}
				return mkcdTmp.toString();
	}
	/**
     * 寝室卫生检查结果查询
     */
	public List<String[]> dao_qswsjcjgQuery(GyglGyfdyModel model) throws Exception{
		DAO dao = DAO.getInstance();
		String lddm = model.getLddm();
		String lc = model.getLcdm();
		String qsh = model.getQsh();
		String wsdj = model.getGuid();
		String jcsj = model.getJcsj();
		String[] colList= new String[]{"pk","xn","xq","lddm","cs","qsh","jcrq","wsdj","ssbh","qswsbz","xymc","xm"};
		StringBuffer querry = new StringBuffer(" where 1=1 ");
		querry.append(Base.isNull(lddm)?"":" and lddm='"+lddm+"' ");
		querry.append(Base.isNull(lc)?"":" and cs='"+lc+"' ");
		querry.append(Base.isNull(qsh)?"":" and qsh='"+qsh+"' ");
		querry.append(Base.isNull(wsdj)?"":" and wsdj='"+wsdj+"' ");
		querry.append(Base.isNull(jcsj)?"":" and jcrq='"+jcsj+"' ");
		String sql = "select a.*,b.zghxm xm from (select xn||xq||a.ssbh pk,a.xn,(select xqmc from xqdzb b where a.xq=b.xqdm) xq,a.jcrq,a.wsdj,(select distinct ldmc from view_ssxx b where a.lddm=b.lddm) lddm,cs,a.qsh,a.ssbh,qswsbz,(select distinct xymc from view_njxyzybj where b.xydm=xydm) xymc from jhzy_qswsjcb a left join ssfpb b on a.ssbh=b.ssbh "+querry+" ) a left join view_xzgyfdy b on a.ssbh=b.ssbh order by a.xn,a.xq,a.jcrq,a.lddm,a.cs,a.qsh ";
		List<HashMap<String, String>> rsTem= dao.getArrayList(sql,new String[] {}, colList);
		List<String[]> oneGnmkdm = new ArrayList<String[]>();
		if(rsTem.size()>0){
			String pk = "";
			StringBuffer jchz = new StringBuffer();
			String[] inptt = null;
			String ssbh = "";
    		for(int i=0;i<rsTem.size();i++){   	
    			String pkV = rsTem.get(i).get("pk");
    			pkV = Base.isNull(pkV)?" ":pkV;
    			String xnV = rsTem.get(i).get("xn");
    			xnV = Base.isNull(xnV)?" ":xnV;
    			String xqV = rsTem.get(i).get("xq");
    			xqV = Base.isNull(xqV)?" ":xqV;
    			String lddmV = rsTem.get(i).get("lddm");
    			lddmV = Base.isNull(lddmV)?" ":lddmV;
    			String csV = rsTem.get(i).get("cs");
    			csV = Base.isNull(csV)?" ":csV;
    			String qshV = rsTem.get(i).get("qsh");
    			qshV = Base.isNull(qshV)?" ":qshV;
    			String jcrqV = rsTem.get(i).get("jcrq");
    			jcrqV = Base.isNull(jcrqV)?" ":jcrqV;
    			String wsdjV = rsTem.get(i).get("wsdj");
    			wsdjV = Base.isNull(wsdjV)?" ":wsdjV;
    			String xymcV = rsTem.get(i).get("xymc");
    			xymcV = Base.isNull(xymcV)?" ":xymcV;
    			String xmV = rsTem.get(i).get("xm");
    			xmV = Base.isNull(xmV)?" ":xmV;
    			pk =rsTem.get(i).get("pk");	
    			if(!Base.isNull(pk)){
    				if(i>0&&!rsTem.get(i).get("pk").equals(rsTem.get(i-1).get("pk"))){
    					jchz = new StringBuffer();
    					if (!ssbh.equals(rsTem.get(i).get("ssbh"))) {
							jchz.append("检查日期：").append(jcrqV).append(",")
									.append("卫生等级：").append(wsdjV).append(";");
							ssbh = rsTem.get(i).get("ssbh");
						}
						oneGnmkdm.add(inptt);
    					inptt = new String[]{xnV,xqV,lddmV,csV,qshV,jchz.toString(),xymcV,xmV};
    				}else {
    					if (!ssbh.equals(rsTem.get(i).get("ssbh"))) {
							jchz.append("检查日期：").append(jcrqV).append(",")
									.append("卫生等级：").append(wsdjV).append(";");
							ssbh = rsTem.get(i).get("ssbh");
						}
						inptt = new String[]{xnV,xqV,lddmV,csV,qshV,jchz.toString(),xymcV,xmV};
					}
    				if(rsTem.size() == (i+1)){
						inptt = new String[]{xnV,xqV,lddmV,csV,qshV,jchz.toString(),xymcV,xmV};
    					oneGnmkdm.add(inptt);
    				}
    			}
    		}
    	}
		return oneGnmkdm;
	}
	/** 自管会部门List */
	public List<HashMap<String, String>> GetZghbmList() {
		DAO dao = DAO.getInstance();
		String sql = "select bmdm dm,bmmc mc from zghbmxxb ";
		List<HashMap<String, String>> list = dao.getList(sql, new String[] {},
				new String[] { "dm", "mc" });
		return list;
	}
	/**
     * 自管会工作人员信息查询
     */
	public ArrayList<String[]> getZghryList(ZghryModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		String qsh = DealString.toGBK(model.getQsh());
		String lddm = DealString.toGBK(model.getLddm());
		String cs = DealString.toGBK(model.getCs());
		String xydm = model.getXydm();
		String zydm = model.getZydm();
		String bjdm = model.getBjdm();
		String nj = model.getNj();
		String xh = model.getXh();
		String bmdm = model.getBmdm();
		xh = Base.isNull(xh) ? "%" : xh;
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(bmdm) ? "" : " and bmdm='"+bmdm+ "'");
		query.append(Base.isNull(xydm) ? "" : " and xydm='"+xydm+ "'");
		query.append(Base.isNull(zydm) ? "" : " and zydm='"+zydm+ "'");
		query.append(Base.isNull(bjdm) ? "" : " and bjdm='"+bjdm+ "'");
		query.append(Base.isNull(nj) ? "" : " and nj='"+nj+ "'");
		query.append(Base.isNull(lddm) ? "" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(cs) ? "" : " and cs='"+cs+ "'");
		query.append(Base.isNull(qsh) ? "" : " and qsh='"+qsh+ "'");
		query.append(" and xh like ?");
		query.append(" and xm like ?");
		
		String[] colList = new String[]{"xh","xm","xymc","bmmc","zw","lxdh","ldmc","cs","qsh"};
		return CommonQueryDAO.commonQuery("view_zghryxxb", query.toString(), new String[] {xh,xm}, colList,"", model);
	}
	/**
     * 自管会工作人员保存 
     */
	public boolean dao_zghrySave(ZghryModel model, HttpServletRequest request)
			throws Exception {
		DAO dao = DAO.getInstance();
		String xh = DealString.toGBK(model.getXh());
		String ssbh = DealString.toGBK(model.getSsbh());
		String bmdm = DealString.toGBK(model.getBmdm());
		String zw = DealString.toGBK(model.getZw());
		String lxdh = DealString.toGBK(model.getLxdh());

		String num = dao.getOneRs(
				"select count(*) num from zghryxxb where xh=?",
				new String[] { xh }, "num");
		boolean done = false;
		if ("0".equalsIgnoreCase(num)) {
			done = StandardOperation.insert("zghryxxb", new String[] { "xh",
					"ssbh", "bmdm", "zw", "lxdh" }, new String[] { xh, ssbh,
					bmdm, zw, lxdh }, request);
		} else {
			done = StandardOperation.update("zghryxxb", new String[] { "ssbh",
					"bmdm", "zw", "lxdh" },
					new String[] { ssbh, bmdm, zw, lxdh }, "xh", xh, request);
		}
		return done;
	}
	/**
	 * 自管会工作人员批量删除
	 */
	public boolean dao_zghryDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete zghryxxb where xh= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
	/**
	 * 获取相关公寓辅导员卫生检查对应扣分值信息
	 */
	public HashMap<String, String> getWsjcXx(String userName,String xn,String xq,String yf){
		DAO dao = DAO.getInstance();
		List<HashMap<String, String>> djList = new ArrayList<HashMap<String,String>>();
		List<HashMap<String, String>> dqList = new ArrayList<HashMap<String,String>>();
		HashMap<String,String> map = new HashMap<String, String>();
		String sql="select distinct ssbh,wsdj from jhzy_qswsjcb a where substr(jcrq,5,2) =? and xn=? and xq=? ";
		sql+=" and exists(select 1 from jhzy_gyfdyb b where a.xn=b.xn and a.xq=b.xq and b.yhm=? )";
		djList = dao.getList(sql,new String[]{yf,xn,xq,userName},new String[]{"ssbh","wsdj"});
		sql="select ssbh,(select kfz from jhzy_dgldqb b where b.dm=a.dqdm )kfz  from jhzy_qswsjcb a where substr(jcrq,5,2) =? and xn=? and xq=? ";
		sql+=" and exists(select 1 from jhzy_gyfdyb b where a.xn=b.xn and a.xq=b.xq and b.yhm=? ) ";
		dqList = dao.getList(sql,new String[]{yf,xn,xq,userName},new String[]{"ssbh","kfz"});
        int djListSize=1;
        int dqListSize=1;
		int m=0;
       
        if(djList.size()>0){
        	djListSize=djList.size();
        	for(int i=0;i<djListSize;i++){//D表示合格在衛生檢查等級里維護       		
        		if(djList.get(i).get("wsdj")!="D"){
        			m++;
        		}
        	}
        }
		int dgldqkf=0;//大功率電器扣分
		if(dqList.size()>0){
			dqListSize=dqList.size();
			for(int i=0;i<dqListSize;i++){
				dgldqkf+=Integer.parseInt(dqList.get(i).get("kfz"));
			}
		}
		if(m/djListSize<0.7){//寝室卫生合格率≤70%者扣7分，85%≥卫生合格率≥71%扣3分。
			map.put("sfzxsqsws","-7");
		}else if(m/djListSize>0.7&&m/djListSize<0.85){
			map.put("sfzxsqsws","-3");
		}else{
			map.put("sfzxsqsws","0");
		}
		map.put("xsqsdgldq",(dgldqkf==0)?dgldqkf+"":"-"+dgldqkf);
	    return map;
	}
		/**
     * 自主息灯检查信息查询
     */
	public ArrayList<String[]> getZzxdjcList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;
		String qsh = DealString.toGBK(model.getQsh());
		String lddm = DealString.toGBK(model.getLddm());
		String cs = DealString.toGBK(model.getCs());
		String jcrq = model.getJcrq();
		String nd = jcrq.substring(0, 4);
		String yf = jcrq.substring(4, 6);
		String xq = "";
		String xn = "";
		
		if (Integer.parseInt(yf) >= 9) {
			xn = nd + "-" + (Integer.parseInt(nd)+1);
			xq = "01";
		} else {
			xn = (Integer.parseInt(nd)-11) + "-" + nd;
			xq = "02";
		}
				
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(lddm) ? "" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(cs) ? "" : " and cs='"+cs+ "'");
		query.append(Base.isNull(qsh) ? "" : " and qsh='"+qsh+ "'");
		
		String[] colList = new String[]{"ssbhT","ldmc","cs","qsh","fgfdy","xdqk"};
		rs = dao.rsToVator(
						"SELECT ssbh ssbhT,ldmc,cs,qsh,fgfdy,xdqk FROM (SELECT lddm,ldmc,cs,qsh,ssbh,xdqk,fgfdy FROM view_qswsjc WHERE xn=? AND xq=? AND jcrq=? UNION ALL SELECT lddm,ldmc,cs,qsh,ssbh,'已熄灯' xdqk,(select xm from jhzy_gyfdyb b where b.ssbh=a.ssbh and b.xn=? and b.xq=?) fgfdy FROM view_ssxx a WHERE NOT EXISTS(SELECT 1 FROM view_qswsjc c WHERE c.xn=? AND c.xq=? AND c.jcrq=? AND a.ssbh=c.ssbh))"+query+" ORDER BY lddm,cs,qsh",
						new String[] { xn, xq, jcrq, xn, xq, xn, xq, jcrq },
						colList);
		return rs;		
	}
	/**
     * 自主息灯检查保存
	 * @throws SQLException 
     */
	public void saveZzxdjc(ZzxdglModel model) {
		DAO dao = DAO.getInstance();
		
		String jcrq = model.getJcrq();
		String nd = jcrq.substring(0, 4);
		String yf = jcrq.substring(4, 6);
		String xq = "";
		String xn = "";
		
		if (Integer.parseInt(yf) >= 9) {
			xn = nd + "-" + (Integer.parseInt(nd)+1);
			xq = "01";
		} else {
			xn = (Integer.parseInt(nd)-11) + "-" + nd;
			xq = "02";
		}
		
		String[] ssbhT = model.getSsbhT();
		String[] xdqkT = model.getXdqkT();
		String[] sqlT = new String[ssbhT.length*2];
		int j = 0;
		for (int i = 0; i < ssbhT.length; i++) {
			String ssbh = DealString.toGBK(ssbhT[i]);
			String xdqk = DealString.toGBK(xdqkT[i]);
			sqlT[j] = "delete qswsjc where ssbh='"+ssbh+"' and jcrq='"+jcrq+"'";
			j++;
			if ("未熄灯".equalsIgnoreCase(xdqk)) {
				sqlT[j] = "insert into qswsjc(ssbh,xn,xq,yf,xdqk,jcrq) values('"+ssbh+"','"+xn+"','"+xq+"','"+yf+"','"+xdqk+"','"+jcrq+"')";
				j++;
			}
		}
		try {
			dao.runBatch(sqlT);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * 自主息灯检查信息查询
     */
	public ArrayList<String[]> getZzxdjcQueryList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;
		String qsh = DealString.toGBK(model.getQsh());
		String lddm = DealString.toGBK(model.getLddm());
		String cs = DealString.toGBK(model.getCs());
		String jcrq = model.getJcrq();
		String xn = model.getXn();
		String xq = model.getXq();
		String yf = model.getYf();
		
		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(lddm) ? "" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(cs) ? "" : " and cs='"+cs+ "'");
		query.append(Base.isNull(qsh) ? "" : " and qsh='"+qsh+ "'");
		query.append(Base.isNull(jcrq) ? "" : " and jcrq='"+jcrq+ "'");
		query.append(Base.isNull(xn) ? "" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xq) ? "" : " and xq='"+xq+ "'");
		query.append(Base.isNull(yf) ? "" : " and yf='"+yf+ "'");
		
		String[] colList = new String[]{"ldmc","cs","qsh","rq"};
		rs = dao.rsToVator(
						"SELECT b.ldmc,b.cs,b.qsh,a.rq FROM (SELECT ssbh,LTRIM(lower(max(sys_connect_by_path(jcrq,''))),'') rq FROM (SELECT row_number() over(partition by ssbh order by jcrq) pno,row_number() over(partition by ssbh order by jcrq)-1 sno,ldmc,cs,qsh,ssbh,jcrq FROM (SELECT * FROM view_qswsjc "+query+" ORDER BY lddm,cs,qsh)) start with pno = 1 connect by prior pno = sno and prior ssbh=ssbh group by ssbh) a,view_ssxx b WHERE a.ssbh=b.ssbh ORDER BY b.lddm,b.cs,b.qsh",
						new String[] {}, colList);
		return rs;
	}
	/**
     * 公寓辅导员考核保存 
     */
	public boolean dao_gyfdyCheckSave(GyfdyKhModel model,String pkValue) throws Exception{
		DAO dao = DAO.getInstance();
		boolean done = false;
		 String  mywsjc = DealString.toGBK(model.getMywsjc());
		 String  xxbsjs = DealString.toGBK(model.getXxbsjs());
		 String  thjl = DealString.toGBK(model.getThjl());
		 String  gyhdqk = DealString.toGBK(model.getGyhdqk());
		 String  sfzxsqsws = DealString.toGBK(model.getSfzxsqsws());
		 String  xsqsdgldq = DealString.toGBK(model.getXsqsdgldq());
		 String  xshd = DealString.toGBK(model.getXshd());
		 String  jb = DealString.toGBK(model.getJb());
		 String  fdyjzf = DealString.toGBK(model.getFdyjzf());
		 String  zpf = DealString.toGBK(model.getZpf());
		 String  xn = DealString.toGBK(model.getXn());
		 String  xq = DealString.toGBK(model.getXq());
		 String  yf = DealString.toGBK(model.getYf());
		 String  zgh = DealString.toGBK(model.getZgh());
		 String  sjcljsx = DealString.toGBK(model.getSjcljsx());
		 String  ascjhy = DealString.toGBK(model.getAscjhy());
		 String  bzgxgz = DealString.toGBK(model.getBzgxgz());
		 String  ayqrzgy = DealString.toGBK(model.getAyqrzgy());
		 String  mzzkyclh = DealString.toGBK(model.getMzzkyclh());
		if(Base.isNull(pkValue)){
			pkValue=xn+xq+yf+zgh;
		}
		String flag = dao.returntag("select * from GYFDYKHXXB where xn||xq||yf||zgh=?", new String[]{pkValue});
		String sql= "";
		if("empty".equalsIgnoreCase(flag)){
			sql="insert into GYFDYKHXXB(XN,XQ,YF,ZGH,SJCLJSX,ASCJHY,BZGXGZ,AYQRZGY,MZZKYCLH,MYWSJC,XXBSJS,THJL,GYHDQK," +
					"SFZXSQSWS,XSQSDGLDQ,XSHD,JB,FDYJZF,ZPF)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  ";
			done = dao.runUpdate(sql,new String[]{xn,xq,yf,zgh,sjcljsx,ascjhy,bzgxgz,ayqrzgy,mzzkyclh,mywsjc,xxbsjs,thjl,gyhdqk,
					sfzxsqsws,xsqsdgldq,xshd,jb,fdyjzf,zpf});
		}else{
			sql="update GYFDYKHXXB set SJCLJSX=?,ASCJHY=?,BZGXGZ=?,AYQRZGY=?,MZZKYCLH=?,MYWSJC=?,XXBSJS=?,THJL=?,GYHDQK=?, ";
			sql+=" SFZXSQSWS=?,XSQSDGLDQ=?,XSHD=?,JB=?,FDYJZF=?,ZPF=? where xn||xq||yf||zgh=? ";
			done = dao.runUpdate(sql,new String[]{sjcljsx,ascjhy,bzgxgz,ayqrzgy,mzzkyclh,mywsjc,xxbsjs,thjl,gyhdqk,
					sfzxsqsws,xsqsdgldq,xshd,jb,fdyjzf,zpf,pkValue});			
		}
		return done;
	}
	 /**
     * 公寓辅导员考核信息查询
     */
	public  ArrayList<String[]> getgyfdyCheckList(GyfdyKhModel model,String userType,String userName) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		 //DAO dao = DAO.getInstance();					
		String qsh = DealString.toGBK(model.getQsh());	
		String lddm = DealString.toGBK(model.getLddm());
		String lc = DealString.toGBK(model.getLc());	
		String xn = model.getXn();
		String xq = model.getXq();
		String zgh = model.getZgh();
		String xydm = model.getXydm();
		zgh = Base.isNull(zgh) ? "%" : zgh;		
		String xm = model.getXm();
		xm = Base.isNull(xm) ? "%" : "%" + xm + "%";		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		query.append(Base.isNull(lddm) ? " and 1=1" : " and lddm='"+lddm+ "'");
		query.append(Base.isNull(lc) ? " and 1=1" : " and lc='"+lc+ "'");
		query.append(Base.isNull(qsh) ? " and 1=1" : " and qsh='"+qsh+ "'");
		query.append(Base.isNull(xn) ? " and 1=1" : " and xn='"+xn+ "'");
		query.append(Base.isNull(xq) ? " and 1=1" : " and xq='"+xq+ "'");
		query.append(Base.isNull(xydm) ? " and 1=1" : " and xydm='"+xydm+ "'");	
		query.append(" and zgh like ?");
		query.append(" and xm like ?");			
		String[] colList = new String[]{"pk","xn","xqmc","yf","zgh","xm","zpf"}; 
		return CommonQueryDAO.commonQuery("view_gyfdykhxx", query.toString(), new String[] {zgh,xm}, colList,"", model);		
	}
	/**
	 *公寓辅导员考核批量删除
	 */
	public boolean dao_gyfdyCheckDel(String pkVStr) throws SQLException{
		DAO dao = DAO.getInstance();			
		String[] pkValueA = pkVStr.split("!!");		
		String[] updPkSql  = new String[pkValueA.length];		
		for (int i = 0; i < pkValueA.length; i++) {
			updPkSql[i] = Base.isNull(pkValueA[i])?"":"delete gyfdykhxxb where xn||xq||yf||zgh= '"+pkValueA[i]+"'  ";							
		}              
		boolean doFlag = false;
		int[] array = dao.runBatch(updPkSql);
		doFlag = dao.checkBatch(array);   
		return doFlag;
	}
		/**
	 * 判断该学生是否已提交星级寝室申请和是否已审核
	 */
	public String isChecking_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select ssbh from view_xjqsxx a where exists (select 1 from view_xszsxx where xh=? and " +
				"ssbh=a.ssbh) and fdysh<>? and substr(to_char(sysdate,'yyyymmddhh24miss'),5,2)=substr(sqsj,5,2) and xn=?";
		String ssbh = dao.getOneRs(sql, new String[]{xh,"未审核",Base.currXn}, "ssbh");
		if(Base.isNull(ssbh)){
			return "no";
		}else{
			return "yes";
		}
	}
	/**
	 * 获得星级寝室情况
	 */
	public HashMap<String,String> getXszsqk_db(String xh){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.lxdh,qsjsqk,dj from (select lddm,cs lc,qsh,ssbh from view_xszsxx where xh=?) a left join XJQSXXB b" +
				" on a.ssbh=b.ssbh and substr(to_char(sysdate,'yyyymmddhh24miss'),5,2)=substr(sqsj,5,2) and xn=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{xh,Base.currXn});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 获得星级寝室情况
	 */
	public HashMap<String,String> getXjqsqk_db(String lddm,String cs,String qsh){
		DAO dao = DAO.getInstance();
		String sql = "select a.*,b.xn,b.xq,b.lxdh,substr(sqsj,5,2)yf,qsjsqk,dj from (select lddm,ldmc,cs lc,qsh,ssbh from view_ssxx where lddm=? and cs=? and qsh=?) a left join XJQSXXB b" +
				" on a.ssbh=b.ssbh and substr(to_char(sysdate,'yyyymmddhh24miss'),5,2)=substr(sqsj,5,2) and xn=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{lddm,cs,qsh,Base.currXn});
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	/**
	 * 获得宿舍编号
	 */
	public String getSsbh_db(String lddm,String cs,String qsh){
		DAO dao = DAO.getInstance();
		String sql = "select ssbh from view_ssxx where lddm=? and cs=? and qsh=?";
		return dao.getOneRs(sql, new String[]{lddm,cs,qsh}, "ssbh");
	}
	/**
	 * 保存星级寝室
	 * @throws Exception 
	 */
	public boolean saveXjqs_db(YxlcqszModel model) throws Exception{
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = getXjqsqk_db(model.getLddm(),model.getLc(),model.getQsh());
		String ssbh = getSsbh_db(model.getLddm(),model.getLc(),model.getQsh());
		String sql = "";
		String lxdh = DealString.toGBK(model.getLxdh());
		String qsjsqk = DealString.toGBK(model.getQsjsqk());
		String dj = DealString.toGBK(model.getDj());
		boolean flag = false;
		if(map != null && map.get("dj") == null){
			sql = "insert into XJQSXXB (xn,xq,ssbh,lxdh,qsjsqk,dj) values(?,?,?,?,?,?)";
			flag = dao.runUpdate(sql, new String[]{Base.currXn,Base.currXq,ssbh,lxdh,qsjsqk,dj});
		}else{
			sql = "update XJQSXXB set lxdh=?,qsjsqk=?,dj=?,ssbh=?,sqsj=(to_char(sysdate,'yyyymmddhh24miss')) where ssbh=? and substr(to_char(sysdate,'yyyymmddhh24miss'),5,2)=substr(sqsj,5,2)";
			flag = dao.runUpdate(sql, new String[]{lxdh,qsjsqk,dj,ssbh,model.getSsbh()});
		}
		return flag;
	}
	
	/**
	 * 获得宿舍人数
	 */
	public String getSsrs_db(String ssbh){
		DAO dao = DAO.getInstance();
		String sql = "select count(*) count from view_xszsxx where ssbh=?";
		return dao.getOneRs(sql, new String[]{ssbh}, "count");
	}
	
	/**
	 * 获得宿舍班级
	 */
	public String getSsbj_db(String ssbh){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = null;
		String bjmc = "";
		String sql = "select bjmc from view_njxyzybj a where exists (select distinct bjdm from view_xszsxx where ssbh=? and bjdm=a.bjdm)";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{ssbh});
		if(list != null && list.size()>0){
			for(int i=0;i<list.size();i++){
				map = list.get(i);
				bjmc += map.get("bjmc")+",";
				if(i==list.size()-1){
					bjmc = bjmc.substring(0, bjmc.length()-1);
				}
			}
		}
		return bjmc;
	}
	
	/**
	 * 星级寝室查询
	 */
	public List<HashMap<String,String>> queryXjqs_db(YxlcqszModel model,String userType,String userName){
		DAO dao = DAO.getInstance();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String sh="";
		String yshr = "";
		String query1 = "";
		if("xy".equals(userType)){
			sh = "xysh";
			yshr = " and fdysh='通过' ";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
			yshr = " and xysh='通过' ";
		}else{
			sh = "fdysh";			
		}
		query.append(Base.isNull(model.getXn()) ? "" :" and xn='"+model.getXn()+"'");
		query.append(Base.isNull(model.getYf()) ? "" :" and substr(sqsj,5,2)='"+model.getYf()+"'");
		query.append(Base.isNull(model.getLddm()) ? "" :" and lddm='"+model.getLddm()+"'");
		query.append(Base.isNull(model.getLc()) ? "" :" and cs='"+model.getLc()+"'");
		query.append(Base.isNull(model.getQsh()) ? "" :" and qsh='"+model.getQsh()+"'");
		query.append(Base.isNull(model.getYesNo()) ? "" :" and "+sh+"='"+DealString.toGBK(model.getYesNo())+"'");
		if(!Base.isNull(model.getXydm())){
			query1 = " and exists (select 1 from (select distinct ssbh from view_xszsxx where xydm='"+model.getXydm()+"') where ssbh=a.ssbh)";
		}
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb b where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);

		query1 += " order by xn,sqsj,lddm,cs,qsh";
		String sql = "select a.*,"+sh+" shzt from view_xjqsxx a "+query.toString()+yshr+query1;		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 星级寝室批量审核
	 * @throws Exception 
	 */
	public boolean  xjqsPlsh_db(String doType,String pkString,String userType) throws Exception{
		DAO dao = DAO.getInstance();
		String shjg = "";
		String sh = "";
		String sjsh ="";
		if("shtg".equals(doType)){
			shjg = "通过";
		}else{
			shjg = "不通过";
		}
		if("xy".equals(userType)){
			sh = "xysh";
			sjsh =" and xxsh='未审核' ";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
		}else{
			sh = "fdysh";
			sjsh =" and xysh='未审核' ";
		}
		String sql ="update XJQSXXB set "+sh+"=? where instr(?,'@'||sqsj||ssbh||'@')>0"+sjsh;
		return dao.runUpdate(sql, new String[]{shjg,"@"+pkString+"@"});
	}
	/**
	 * 星级寝室单个审核页面
	 */
	public HashMap<String,String> xjqsDgshPage_db(String pkValue,String userType){
		DAO dao = DAO.getInstance();
		String sh = "";
		String yshr = "";
		if("xy".equals(userType)){
			sh = "xysh";
			yshr = ",xxsh sjsh ";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
		}else{
			sh = "fdysh";
			yshr = ",xysh sjsh ";
		}
		String sql ="select a.*,"+sh+" shzt "+yshr+" from view_xjqsxx a where sqsj||ssbh=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkValue});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return new HashMap<String,String>();
		}
	}
	/**
	 * 星级寝室单个审核查看
	 */
	public HashMap<String,String> xjqsview_db(String pkValue){
		DAO dao = DAO.getInstance();
		String sql ="select a.*,xxsh shzt from view_xjqsxx a where sqsj||ssbh=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{pkValue});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return new HashMap<String,String>();
		}
	}
	/**
	 * 星级寝室单个审核
	 * @throws Exception 
	 */
	public boolean xjqsDgsh_db(String pkValue,String dj,String shzt,String userType) throws Exception{
		DAO dao = DAO.getInstance();
		String sh = "";
		if("xy".equals(userType)){
			sh = "xysh";
		}else if("xx".equalsIgnoreCase(userType)||"admin".equalsIgnoreCase(userType)){
			sh = "xxsh";
		}else{
			sh = "fdysh";
		}
		String sql = "update XJQSXXB set "+sh+"=?,dj=? where sqsj||ssbh=?";
		return dao.runUpdate(sql, new String[]{shzt,dj,pkValue});
	}
	/**
	 * 星级寝室结果查询
	 */
	public List<HashMap<String,String>> queryXjqsShjg_db(YxlcqszModel model,String userType,String userName ){
		DAO dao = DAO.getInstance();
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1 ");
		String query1 = "";
		query.append(Base.isNull(model.getXn()) ? "" :" and xn='"+model.getXn()+"'");
		query.append(Base.isNull(model.getYf()) ? "" :" and substr(sqsj,5,2)='"+model.getYf()+"'");
		query.append(Base.isNull(model.getLddm()) ? "" :" and lddm='"+model.getLddm()+"'");
		query.append(Base.isNull(model.getLc()) ? "" :" and cs='"+model.getLc()+"'");
		query.append(Base.isNull(model.getQsh()) ? "" :" and qsh='"+model.getQsh()+"'");
		query.append(Base.isNull(model.getYesNo()) ? "" :" and xxsh='"+DealString.toGBK(model.getYesNo())+"'");
		if(!Base.isNull(model.getXydm())){
			query1 = " and exists (select 1 from (select distinct ssbh from view_xszsxx where xydm='"+model.getXydm()+"') where ssbh=a.ssbh)";
		}
		String fdyQuerry = "isGyFdy".equalsIgnoreCase(userType) ? " and ssbh in (select ssbh from jhzy_gyfdyb b where yhm ='"+ userName + "' and xn='"+Base.currXn+"' and xq='"+Base.currXq+"' )":"";
		query.append(fdyQuerry);
		query1 += " order by xn,sqsj,lddm,cs,qsh";
		String sql = "select a.* from view_xjqsxx a "+query.toString()+query1;		
		return dao.getListNotOut(sql, new String[]{});
	}
	
	/**
	 * 获得宿舍信息
	 */
	public HashMap<String,String>  getSsxx_db(String ssbh){
		DAO dao = DAO.getInstance();
		String sql = "select * from view_ssxx where ssbh=?";
		List<HashMap<String,String>> list = dao.getListNotOut(sql, new String[]{ssbh});
		if(list != null && list.size()>0){
			return list.get(0);
		}else{
			return new HashMap<String,String> ();
		}
	}
	
	/**
	 * 删除星级寝室
	 * @throws Exception 
	 */
	public boolean  delXjqs_db(String pkStr) throws Exception{
		DAO dao = DAO.getInstance();
		String sql = "delete from xjqsxxb where instr(?,'@'||sqsj||ssbh||'@')>0";
		return dao.runUpdate(sql, new String[]{pkStr});
	}
	
	/**
	 * 卫生检查达标信息
	 * @throws Exception 
	 */
	public HashMap<String,String>wsjcDbInfo(String xn,String xq,String yf,String lddm,String lc,String qsh){
		DAO dao = DAO.getInstance();
		HashMap<String,String> map = new HashMap<String,String>();
		StringBuffer sql = new StringBuffer();
		sql.append("select (select  count(*)num from jhzy_qswsjcb b where substr(b.jcrq,5,2)=a.yf and b.xn=a.xn and b.xq=a.xq and b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh and wsdj='A')anum,");
		sql.append(" (select count(*)num from jhzy_qswsjcb b where substr(b.jcrq,5,2)=a.yf and b.xn=a.xn and b.xq=a.xq and b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh and wsdj='B')bnum,");
		sql.append(" (select count(*)num from jhzy_qswsjcb b where substr(b.jcrq,5,2)=a.yf and b.xn=a.xn and b.xq=a.xq and b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh and wsdj='C')cnum,");
		sql.append(" (select count(*)num from jhzy_qswsjcb b where substr(b.jcrq,5,2)=a.yf and b.xn=a.xn and b.xq=a.xq and b.lddm=a.lddm and b.cs=a.cs and b.qsh=a.qsh and wsdj='D')dnum ");
		sql.append(" from (select distinct  a.lddm,a.cs,a.qsh,a.xn,a.xq,substr(jcrq,5,2) yf from jhzy_qswsjcb a where substr(jcrq,5,2)=? and xn=? and xq=? and lddm=? and cs=? and qsh=? )a ");
		HashMap<String,String>tem=dao.getMap(sql.toString(),new String[]{yf,xn,xq,lddm,lc,qsh}, new String[]{"anum","bnum","cnum","dnum"});
		@SuppressWarnings("unused")
		int a=0,b=0,c=0,d=0;
		map.putAll(tem);
		if(tem.size()>0){
			a=Integer.parseInt(tem.get("anum"));
			b=Integer.parseInt(tem.get("bnum"));
			c=Integer.parseInt(tem.get("cnum"));
			d=Integer.parseInt(tem.get("dnum"));			
		}else{
			map.put("anum","0");
			map.put("bnum","0");
			map.put("cnum","0");
			map.put("dnum","0");
		}
		map.put("dbnum",(Integer.parseInt(map.get("bnum"))+Integer.parseInt(map.get("cnum"))+Integer.parseInt(map.get("anum")))+"");
		if(d==0&&a>=3){//一个月内无不达标，且有3次以上评优秀（A等）五星级
			map.put("dj","a");
		}else if(d==0&&a==2){//一个月内无不达标，且有2次评优秀（A等寝室）四星级
			map.put("dj","b");
		}else if(d==0&&a<2){//一个月内无不达标 三星级
			map.put("dj","c");
		}else{//无等级
			map.put("dj","no");
		}
		return map;
	}
	/**
	 * 星级文明寝室申请学生查询
	 */
	public  ArrayList<String[]> getXjwmqsList_stu(String xh) {
		DAO  dao = DAO.getInstance();
		String[] colList = new String[]{"xn","xqmc","xh","xm","bjmc","fdysh","xysh","xxsh"}; 		
		String sql = "select a.xn,a.xqmc,b.xh,b.xm,b.bjmc,a.fdysh,a.xysh,a.xxsh from view_xjqsxx a,view_xszsxx b where b.xh=? and a.ssbh=b.ssbh order by xn";	
		return dao.rsToVator(sql, new String[]{xh}, colList);
	}
	
	/**
     * 自主息灯检查统计
     */
	public ArrayList<String[]> getZzxdjcTjList(ZzxdglModel model)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		DAO dao = DAO.getInstance();
		ArrayList<String[]> rs = null;
		String tjlx = model.getTjlx();
		String tjdw = model.getTjdw();
		String jcrq = model.getJcrq();
		String xn = model.getXn();
		String xq = model.getXq();
		String yf = model.getYf();
		
		
		StringBuffer query = new StringBuffer();
		query.append(" where 1=1");
		
		if ("1".equalsIgnoreCase(tjdw)) {//按学年
			query.append(" and x.xn='"+xn+ "'");
		} else if ("2".equalsIgnoreCase(tjdw)) {//按学期
			query.append(" and x.xn='"+xn+ "'");
			query.append(" and x.xq='"+xq+ "'");
		} else if ("3".equalsIgnoreCase(tjdw)) {//按月份
			query.append(" and x.xn='"+xn+ "'");
			query.append(" and x.yf='"+yf+ "'");
			
			if (Integer.parseInt(yf) >= 9) {
				xq = "01";
			} else {
				xq = "02";
			}
		} else if ("4".equalsIgnoreCase(tjdw)) {//按日期
			query.append(" and x.jcrq='"+jcrq+ "'");
			
			String nd = jcrq.substring(0, 4);
			yf = jcrq.substring(4, 6);
			if (Integer.parseInt(yf) >= 9) {
				xn = nd + "-" + (Integer.parseInt(nd)+1);
				xq = "01";
			} else {
				xn = (Integer.parseInt(nd)-11) + "-" + nd;
				xq = "02";
			}
		}
		
		String[] colList = new String[] { "pm", "dw", "qszs", "xds", "xdl" };
		
		if ("1".equalsIgnoreCase(tjlx)) {//按学院
			rs = dao.rsToVator(
						"SELECT dense_rank() over (ORDER BY xdl NULLS LAST) pm,a.* FROM (SELECT xymc dw,qszs,qszs-wxds xds,(CASE qszs WHEN 0 THEN '' ELSE ROUND((qszs-wxds)/qszs*100,2)||'%' END) xdl FROM (SELECT a.xydm,a.xymc,nvl(b.qszs,'0') qszs,(SELECT COUNT(distinct ssbh) FROM qswsjc x "+query+" AND EXISTS(SELECT 1 FROM view_xszsxx b WHERE x.ssbh=b.ssbh AND b.xydm=a.xydm)) wxds FROM view_njxyzybj a,(SELECT COUNT(*) qszs,xydm FROM (select DISTINCT ssbh,xydm from view_xszsxx t GROUP BY xydm,ssbh) GROUP BY xydm) b WHERE a.xydm=b.xydm(+)  GROUP BY a.xydm,a.xymc,b.qszs) a) a",
						new String[] {}, colList);
		} else if ("2".equalsIgnoreCase(tjlx)) {//按楼栋
			rs = dao.rsToVator(
					"SELECT dense_rank() over (ORDER BY xdl NULLS LAST) pm,a.dw,a.qszs,a.xds,a.xdl FROM (SELECT ldmc dw,qszs,qszs-wxds xds,(CASE qszs WHEN 0 THEN '' ELSE ROUND((qszs-wxds)/qszs*100,2)||'%' END) xdl FROM (SELECT lddm,ldmc,COUNT(*) qszs,NVL((SELECT COUNT(distinct ssbh) FROM view_qswsjc x "+query+" AND x.lddm=a.lddm),'0') wxds FROM view_ssxx a GROUP BY lddm,ldmc) a)a",
					new String[] {}, colList);
		} else if ("3".equalsIgnoreCase(tjlx)) {//按辅导员
			rs = dao.rsToVator(
					"SELECT dense_rank() over (ORDER BY xdl NULLS LAST) pm,a.dw,a.qszs,a.xds,a.xdl FROM (SELECT xm dw,qszs,qszs-wxds xds,(CASE qszs WHEN 0 THEN '' ELSE ROUND((qszs-wxds)/qszs*100,2)||'%' END) xdl FROM (SELECT yhm,xm,COUNT(*) qszs,NVL((SELECT COUNT(*) FROM view_qswsjc x "+query+" AND x.fgfdyyhm=a.yhm GROUP BY ssbh),'0') wxds FROM jhzy_gyfdyb a WHERE xn='"+xn+"' AND xq='"+xq+"' GROUP BY yhm,xm) a)a",
					new String[] {}, colList);
		}
		return rs;
	}
	public List<HashMap<String,String>>getKhdxList(){
		DAO dao = DAO.getInstance();
		return dao.getList(" select dm,khdx from gygl_khdxb", new String[]{},new String[]{"dm","khdx"});
	}
	public List<HashMap<String,String>>getGyzbList(){
		DAO dao = DAO.getInstance();
		return dao.getList(" select dm,zbmc from gyzbb", new String[]{},new String[]{"dm","zbmc"});
	}
	public String getZbfzr(String dm){
		DAO dao = DAO.getInstance();
		return dao.getOneRs("select zbfzr from gyzbb where dm=?", new String[]{dm}, "zbfzr");
	}
}
