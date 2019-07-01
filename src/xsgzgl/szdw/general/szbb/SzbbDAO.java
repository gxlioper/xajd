package xsgzgl.szdw.general.szbb;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zfsoft.xgxt.base.exception.SystemException;
import com.zfsoft.xgxt.base.message.MessageKey;
import com.zfsoft.xgxt.base.util.UniqID;
import com.zfsoft.xgxt.szdw.fdyxx.JxqkModel;

import xgxt.DAO.DAO;
import xgxt.action.Base;
import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.CommonQueryDAO;
import xgxt.utils.MakeQuery;
import xgxt.utils.date.DateUtils;
import xsgzgl.comm.BasicDAO;
import xsgzgl.szdw.general.SzdwGeneralDAO;
import xsgzgl.szdw.general.SzdwGeneralForm;

/**
 * <p>
 * Title: 学工管理系统
 * </p>
 * <p>
 * Description: 思政队伍_思政编班_通用_DAO类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2013
 * </p>
 * <p>
 * Company: zfsoft
 * </p>
 * 
 * @author 伟大的骆
 * @version 1.0
 */

public class SzbbDAO extends SzdwGeneralDAO {

	private Log logger = LogFactory.getLog(SzbbDAO.class);
	protected DAO dao = DAO.getInstance();
	// ==================执行查询操作 begin =============================

	/**
	 * 获得思政队伍思政编班结果集
	 * 
	 * @date 2013-01-15
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSzbbList(SzdwGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		logger.info("思政编班 start:" + DateUtils.getCurrTime());
		DAO dao=DAO.getInstance();
		// ====================过滤条件===================================
		
		SearchModel searchModel=myForm.getSearchModel();
		
		String search_lx =searchModel.getMhcx_lx();
		String input_mhcx=searchModel.getInput_mhcx();
		
		StringBuilder query=new StringBuilder();
		String []input=null;
		if(!Base.isNull(input_mhcx)){
			
			input=getMhcxInfo(query, input, search_lx, input_mhcx);
			
			searchModel.setMhcx_lx(null);
			searchModel.setInput_mhcx(null);
		}
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		// 高级查询条件
		query.append(searchTj+searchTjByUser);
		
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();
		if("12303".equals(Base.xxdm)){
			tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm||'!!luojw!!'||a.xymc||'!!luojw!!'||a.zymc||'!!luojw!!'||rs||'!!luojw!!'||fdyxm||'!!luojw!!'||bzrxm pk, ");
		}else{
			tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm pk, ");
		}
		tableSql.append(" a.nj,t2.symc,t2.sydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm,a.xymc,a.xydm, ");
		
		// 带班辅导员简易信息
		tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
		// 带班班主任简易信息
		tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
		tableSql.append(" fdyzgh,bzrzgh, ");
		tableSql.append(" case when b.fdyxm is null then '否' else '是' end sfyszfdy, ");
		tableSql.append(" case when bzrxm is null then '否' else '是' end sfyszbzr ");
		tableSql.append(" from view_njxyzybj_all a ");
		tableSql.append(" join xsxxb xsxxb on a.bjdm = xsxxb.bjdm");
		tableSql.append(" left join XG_XTWH_SYBJGLB t1 on xsxxb.bjdm=t1.bjdm ");
		tableSql.append(" left join XG_XTWH_SYDMB t2 on t2.sydm = t1.sydm ");
		tableSql.append(" left join ");
		tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm||' '||b.lxdh) fdyxm from fdybjb a ");
		tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		tableSql.append(" on a.bjdm = b.bjdm ");

		tableSql.append(" left join ");
		tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) bzrzgh, WM_CONCAT(b.xm || ' ' || b.lxdh) bzrxm from bzrbbb a ");
		tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c ");		
		tableSql.append(" on a.bjdm = c.bjdm ");
		
		tableSql.append(" left join (select bjdm,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by bjdm)d on a.bjdm=d.bjdm ");
		tableSql.append(" left join (select bjdm,count(xh) fzxrs from xsxxb where sfzx = '不在校' group by bjdm)e on a.bjdm=e.bjdm ");
		
		tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
		tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
		tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
		tableSql.append(query);
	
		// ====================SQL拼装 end================================
		String[] colList=null;
		if("12303".equals(Base.xxdm)){
			colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs",
					"fdy","fdyxm","bzr","bzrxm","qqqh"};
		}else{
			colList = new String[] { "pk", "nj", "symc", "zymc", "bjmc","rs",
				"fdy","fdyxm","bzr","bzrxm"};
		}
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
						dao.unionArray(input, inputV), colList);
		logger.info("思政编班 end:" + DateUtils.getCurrTime());
		return list;
	}

	/**
	 * 获得思政队伍思政班主任编班
	 *
	 * @date 2018-09-15
	 * @author qlj
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public ArrayList<String[]> getSzBzrbbList(SzdwGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		logger.info("思政编班 start:" + DateUtils.getCurrTime());
		DAO dao=DAO.getInstance();
		// ====================过滤条件===================================

		SearchModel searchModel=myForm.getSearchModel();

		String search_lx =searchModel.getMhcx_lx();
		String input_mhcx=searchModel.getInput_mhcx();

		StringBuilder query=new StringBuilder();
		String []input=null;
		if(!Base.isNull(input_mhcx)){

			input=getMhcxInfo(query, input, search_lx, input_mhcx);

			searchModel.setMhcx_lx(null);
			searchModel.setInput_mhcx(null);
		}
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);

		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		// 高级查询条件
		query.append(searchTj+searchTjByUser);

		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();
		if("12303".equals(Base.xxdm)){
			tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm||'!!luojw!!'||a.xymc||'!!luojw!!'||a.zymc||'!!luojw!!'||rs||'!!luojw!!'||fdyxm||'!!luojw!!'||bzrxm pk, ");
		}else{
			tableSql.append(" select rownum r,a.* from (select distinct a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm pk, ");
		}
		tableSql.append(" a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,f.qqqh,a.bjmc,nvl(d.rs,0) rs,b.fdyxm,bzrxm, ");

		// 带班辅导员简易信息
		tableSql.append(" (case when length(b.fdyxm)>10 then substr(b.fdyxm,1,10)||'...' else b.fdyxm end )fdy, ");
		// 带班班主任简易信息
		tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
		tableSql.append(" fdyzgh,bzrzgh, ");
		tableSql.append(" case when b.fdyxm is null then '否' else '是' end sfyszfdy, ");
		tableSql.append(" case when bzrxm is null then '否' else '是' end sfyszbzr ");
		tableSql.append(" from view_njxyzybj_all a ");
		tableSql.append(" join xsxxb xsxxb on a.bjdm = xsxxb.zybj");
		tableSql.append(" left join ");
		tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) fdyzgh, WM_CONCAT(b.xm||' '||b.lxdh) fdyxm from fdybjb a ");
		tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) b ");
		tableSql.append(" on a.bjdm = b.bjdm ");

		tableSql.append(" left join ");
		tableSql.append("  (select a.bjdm, WM_CONCAT(a.zgh) bzrzgh, WM_CONCAT(b.xm || ' ' || b.lxdh) bzrxm from bzrbbb a ");
		tableSql.append("  left join fdyxxb b on a.zgh = b.zgh group by a.bjdm) c ");
		tableSql.append(" on a.bjdm = c.bjdm ");

		tableSql.append(" left join (select zybj,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by zybj)d on a.bjdm=d.zybj ");
		tableSql.append(" left join (select zybj,count(xh) fzxrs from xsxxb where sfzx = '不在校' group by zybj)e on a.bjdm=e.zybj ");

		tableSql.append(" left join (select bjdm , qqqh from XG_bjxxb_12303) f on a.bjdm = f.bjdm ");
		tableSql.append(" where (nvl(e.fzxrs,0)=0 and nvl(d.rs,0)=0) or nvl(d.rs,0)>0 ");
		tableSql.append(" order by nj desc,xydm,zydm,bjdm) a where 1=1 ");
		tableSql.append(query);

		// ====================SQL拼装 end================================
		String[] colList=null;
		if("12303".equals(Base.xxdm)){
			colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs",
					"fdy","fdyxm","bzr","bzrxm","qqqh"};
		}else{
			colList = new String[] { "pk", "nj", "xymc", "zymc", "bjmc","rs",
					"fdy","fdyxm","bzr","bzrxm"};
		}
		ArrayList<String[]> list = (ArrayList<String[]>) CommonQueryDAO
				.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
						dao.unionArray(input, inputV), colList);
		logger.info("思政编班 end:" + DateUtils.getCurrTime());
		return list;
	}
	
	public String[] getMhcxInfo(StringBuilder query,String[]input,String mhcx_lx,String input_mhcx){
		
		String []cxtj=input_mhcx.split(" ");
		
		List<String>inputV=new ArrayList<String>();
		// 全部模糊查询条件

		query.append(" and ( ");
		
		if("all".equalsIgnoreCase(mhcx_lx)){
			
			String[]queryArray={"zgh","xm","bjmc"};
			
			for(int i=0;i<queryArray.length;i++){
				
				if(i!=0){
				query.append(" or ");
				}
				inputV.addAll(getCxtj(query ,cxtj, queryArray[i]));
			}
			
		}else {
			
			inputV.addAll(getCxtj(query ,cxtj, mhcx_lx));
		
		}
		query.append(" ) ");
		
		return inputV.toArray(new String[]{});
		
	}
	
	public List<String> getCxtj(StringBuilder queryStr,String[]cxtj,String mhcx_lx){
		
		List<String>tjList=new ArrayList<String>();
		
		StringBuilder query=new StringBuilder();
		
		for(int i=0;i<cxtj.length;i++){
			
			if(i!=0){
				query.append("  or ");
			}
			
			mhcx_lx = mhcx_lx.replace("mhcx_", "");
			if("zgh".equalsIgnoreCase(mhcx_lx)){
				
				query.append("  bzrzgh like '%'||?||'%' or fdyzgh like '%'||?||'%'  ");
				tjList.add(cxtj[i]);
				tjList.add(cxtj[i]);
			}
			
			if("xm".equalsIgnoreCase(mhcx_lx)){
				
				query.append("   bzrxm like '%'||?||'%' or fdyxm like '%'||?||'%'  ");
				tjList.add(cxtj[i]);
				tjList.add(cxtj[i]);
			}	
			
			if("bjmc".equalsIgnoreCase(mhcx_lx)){

				query.append("  bjmc like '%'||?||'%'  ");
				tjList.add(cxtj[i]);
			}
			
			
		}
	
		
		queryStr.append(query.toString());
		return   tjList;

		
	}	
	
	/**
	 * 根据班级获取已分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpFdy(String bjdm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.*,case when length(bmmc)>10 then substr(bmmc,1,10)||'...' else bmmc end showbmmc from( ");
		sql.append(" select a.zgh||'luojw'||b.bjdm pk,a.zgh,a.xm,a.lxdh, ");
		sql.append(" case when a.xb='1' then '男' when a.xb='2' then '女' else a.xb end xb, ");
		sql.append(" (select bmmc from zxbz_xxbmdm c where a.bmdm = c.bmdm) bmmc ");
		sql.append(" from fdyxxb a, fdybjb b where  a.zgh = b.zgh and b.bjdm=? ) a ");
		
		return dao.getList(sql.toString(), new String[]{bjdm}, new String[]{"pk","zgh","xm","xb","bmmc","lxdh","showbmmc"});
	}

	/**
	 * 根据班级获取已分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getYfpBzr(String bjdm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.*,case when length(bmmc)>10 then substr(bmmc,1,10)||'...' else bmmc end showbmmc from( ");
		sql.append(" select a.zgh||'luojw'||b.bjdm pk,a.zgh,a.xm,a.lxdh, ");
		sql.append(" case when a.xb='1' then '男' when a.xb='2' then '女' else a.xb end xb, ");
		sql.append(" (select bmmc from zxbz_xxbmdm c where a.bmdm = c.bmdm) bmmc ");
		sql.append(" from fdyxxb a, bzrbbb b where  a.zgh = b.zgh and b.bjdm=?  ) a");
		
		return dao.getList(sql.toString(), new String[]{bjdm}, new String[]{"pk","zgh","xm","xb","bmmc","lxdh","showbmmc"});
	}
	
	/**
	 * 根据班级获取未分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpFdy(String bjdm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.zgh||'luojw'||b.bjdm pk,a.zgh,a.xm,a.xb,a.lxdh, ");
		sql.append(" (select bmmc from zxbz_xxbmdm c where a.bmdm = c.bmdm) bmmc ");
		sql.append(" from fdyxxb a, fdybjb b where  a.zgh = b.zgh and ( b.bjdm> ? or b.bjdm< ? ) ");
		
		return dao.getList(sql.toString(), new String[]{bjdm,bjdm}, new String[]{"pk","xm","xb","bmmc","lxdh"});
	}
	
	/**
	 * 获得思政队伍思政编班结果集
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> getWfpFdyList(SzdwGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {

		BasicDAO basicDAO=new BasicDAO();
		// ====================过滤条件===================================
		MakeQuery mQuery=new MakeQuery();
		
		String[]queryArr={"xb","bmdm","sfdb"};
		String[]likeQueryArr={"zgh","xm"};
		
		String bjdm=myForm.getBjdm();
		
		String bmlb=myForm.getBmlb();
		
		mQuery.makeQuery(queryArr, likeQueryArr, myForm);
		String[]inputV=mQuery.getInputList();

		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();
		
		tableSql.append(" select rownum r, a.* from (select a.*, a.bmdm xydm ");
		tableSql.append(" from (select t.zgh,t.xm,t.xb,t.sfdb,t.bmdm,t.bmmc, t.dbs ");
		if("12303".equals(Base.xxdm)){
			tableSql.append(" , case when t.dbrs is null then 0 else t.dbrs end dbrs ");
		}
		tableSql.append(" from (select a.*  from (select zgh, bmdm,dbs, ");
		tableSql.append(" bmmc, xb,xm,sfdb ");
		if("12303".equals(Base.xxdm)){
			tableSql.append(" , dbrs ");
		}
		tableSql.append(" from (select b.zgh, ");
		tableSql.append(" b.xm, b.bmdm,d.bmmc,nvl(dbs,0)dbs, ");
		if("12303".equals(Base.xxdm)){
			tableSql.append(" e.dbrs, ");
		}
		tableSql.append(" case when xb = '1' then '男'  when xb = '2' then  '女'  else xb   end xb, ");
		tableSql.append(" case  when dbs is null then '未带班'   else '已带班'  end sfdb ");
		tableSql.append(" from fdyxxb b  left join (select count(1)dbs,zgh from fdybjb a, view_njxyzybj c ");
		tableSql.append(" where a.bjdm =  c.bjdm group by zgh)a on a.zgh =  b.zgh ");
		tableSql.append(" left join zxbz_xxbmdm d on b.bmdm = d.bmdm ");
		if("12303".equals(Base.xxdm)){
			tableSql.append(" left join (select count(xh) dbrs ,zgh from fdybjb b left join view_njxyzybj_all a on " +
					"a.bjdm = b.bjdm left join xsxxb c on a.bjdm = c.bjdm group by zgh ) e  on b.zgh = e.zgh ");
		}
		tableSql.append(" where not exists (select 1  from fdybjb x  where b.zgh = x.zgh ");
		if(!"".equalsIgnoreCase(bmlb)){
			tableSql.append(" and bjdm = '"+bjdm+"') and d.bmlb = '"+bmlb+"')  ");
		}else{
			tableSql.append(" and bjdm = '"+bjdm+"') )  ");
		}
		
		tableSql.append(" order by zgh) a) t order by t.zgh) a) a ");

		tableSql.append(mQuery.getQueryString());
		//tableSql.append(basicDAO.getUserSql(user));
		// ====================SQL拼装 end================================
		String[] colList ;
		if("12303".equals(Base.xxdm)){
			 colList = new String[] {"zgh", "xm", "bmmc","xb", "dbrs","dbs"  };
		}else{
			 colList = new String[] {"zgh", "xm", "bmmc","xb", "dbs" };
		}

		return  CommonQueryDAO.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
				inputV, colList);
	}
	
	
	
	/**
	 * 根据班级获取未分配给的班主任
	 * @param bjdm
	 * @author qlj
	 * @return
	 */
	public List<HashMap<String,String>>getWfpBzr(String bjdm){
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();
		
		sql.append(" select a.zgh||'luojw'||b.bjdm pk,a.zgh,a.xm,a.xb,a.lxdh, ");
		sql.append(" (select bmmc from zxbz_xxbmdm c where a.bmdm = c.bmdm) bmmc ");
		sql.append(" from fdyxxb a, bzrbbb b where  a.zgh = b.zgh and ( b.bjdm> ? or b.bjdm< ? ) ");
		
		return dao.getList(sql.toString(), new String[]{bjdm,bjdm}, new String[]{"pk","xm","xb","bmmc","lxdh"});
	}
	
	/**
	 * 获得思政队伍思政编班结果集
	 * 
	 * @date 2013-01-09
	 * @author 伟大的骆
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public List<String[]> getWfpBzrList(SzdwGeneralForm myForm, User user)
			throws IllegalArgumentException, SecurityException,
			IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		BasicDAO basicDAO=new BasicDAO();
		// ====================过滤条件===================================
		MakeQuery mQuery=new MakeQuery();
		
		String[]queryArr={"xb","bmdm","sfdb"};
		String[]likeQueryArr={"zgh","xm"};
		
		String bjdm=myForm.getBjdm();
		
		mQuery.makeQuery(queryArr, likeQueryArr, myForm);
		String[]inputV=mQuery.getInputList();

		
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();
		
		tableSql.append(" select rownum r, a.* from (select a.*, a.bmdm xydm ");
		tableSql.append(" from (select t.zgh,t.xm,t.xb,t.sfdb,t.bmdm,t.bmmc, t.dbs ");
		tableSql.append(" from (select a.*  from (select zgh, bmdm,dbs, ");
		tableSql.append(" bmmc, xb,xm,sfdb from (select b.zgh, ");
		tableSql.append(" b.xm, b.bmdm,d.bmmc,nvl(dbs,0)dbs, ");
		tableSql.append(" case when xb = '1' then '男'  when xb = '2' then  '女'  else xb   end xb, ");
		tableSql.append(" case  when dbs is null then '未带班'   else '已带班'  end sfdb ");
		tableSql.append(" from fdyxxb b  left join (select count(1)dbs,zgh from bzrbbb a, view_njxyzybj c ");
		tableSql.append(" where a.bjdm =  c.bjdm group by zgh)a on a.zgh =  b.zgh ");
		tableSql.append(" left join zxbz_xxbmdm d on b.bmdm = d.bmdm ");
		tableSql.append(" where not exists (select 1  from bzrbbb x  where b.zgh = x.zgh ");
		tableSql.append(" and bjdm = '"+bjdm+"'))  ");
		tableSql.append(" order by zgh) a) t order by t.zgh) a) a ");
		
		tableSql.append(mQuery.getQueryString());
		tableSql.append(basicDAO.getUserSql(user));
		// ====================SQL拼装 end================================

		String[] colList = new String[] {"zgh", "xm", "bmmc","xb", "dbs"};

		return  CommonQueryDAO.commonPageByPjQuery(myForm.getPages(), tableSql.toString(),
				inputV, colList);
	}

	/**
	 * 取消班主任编班
	 * author qlj
	 */
	public boolean cancelBzrbb(String zgh, String bjdm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();

		sql.append(" delete from bzrbbb where zgh=? and bjdm=? ");
		
		boolean b = dao.runUpdate(sql.toString(),new String[]{zgh,bjdm});
		if(b){
			b = saveBbls(zgh, bjdm, "update", false);
		}
		return b;
	}

	/**
	 * 取消辅导员编班
	 * author qlj
	 */
	public boolean cancelFdybb(String zgh, String bjdm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();

		sql.append(" delete from fdybjb where zgh=? and bjdm=? ");
		
		boolean b = dao.runUpdate(sql.toString(),new String[]{zgh,bjdm});
		if(b){
			b = saveBbls(zgh, bjdm, "update", true);
		}
		return b;
	}
	
	/**
	 * 班主任 编班
	 * author qlj
	 */
	public boolean setBzrbb(String zgh, String bjdm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();

		sql.append(" insert into bzrbbb(zgh,bjdm)values(?,?) ");
		
		boolean b = dao.runUpdate(sql.toString(),new String[]{zgh,bjdm});
		if(b){
			b = saveBbls(zgh, bjdm, "save", false);
		}
		return b;
	}

	/**
	 * 辅导员编班
	 * author qlj
	 */
	public boolean setFdybb(String zgh, String bjdm) throws Exception {
		
		DAO dao=DAO.getInstance();
		
		StringBuilder sql=new StringBuilder();

		sql.append(" insert into fdybjb(zgh,bjdm)values(?,?) ");
		
		boolean b = dao.runUpdate(sql.toString(),new String[]{zgh,bjdm});
		if(b){
			b = saveBbls(zgh, bjdm, "save", true);
		}
		return b;
	}
	
	/**
	 * 保存编班历史
	 */
	public boolean saveBbls(String zgh, String bjdm, String type, boolean isFdy) throws Exception {
		DAO dao=DAO.getInstance();
		String table = isFdy ? "fdybjblsb" : "bzrbblsb";
		StringBuilder sql=new StringBuilder();
		if("save".equals(type)){
			sql.append(" insert into "+table+" (xn,xq,zgh,bjdm,fbsj,sfzr) values (?,?,?,?,to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),'1') ");
		}else{
			sql.append(" update "+table+" set qxfbsj=to_char(sysdate, 'yyyy-MM-dd hh24:mi:ss'),sfzr='0',xn=?,xq=? where zgh=? and bjdm=? and qxfbsj is null ");
		}
		return dao.runUpdate(sql.toString(),new String[]{Base.currXn,Base.currXq,zgh,bjdm});
	}

	// ==================执行查询操作 end =============================
	
	/**
	 * 通过班级代码获取思政信息
	 */
	public List<HashMap<String, String>> getFdyBzrListByBjdm(String bjdm) {
		DAO dao=DAO.getInstance();
		StringBuilder sql=new StringBuilder();
		sql.append(" select a.zgh,b.xm,b.lxdh,c.bmmc,'bzr' lx ");
		sql.append(" from bzrbbb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm = c.bmdm ");
		sql.append(" where bjdm = ? ");
		sql.append(" union all ");
		sql.append(" select a.zgh,b.xm,b.lxdh,c.bmmc,'fdy' lx ");
		sql.append(" from fdybjb a ");
		sql.append(" left join fdyxxb b on a.zgh = b.zgh ");
		sql.append(" left join zxbz_xxbmdm c on b.bmdm = c.bmdm ");
		sql.append(" where bjdm = ? ");
		
		return dao.getList(sql.toString(), new String[]{bjdm, bjdm}, new String[]{"zgh", "xm", "lxdh", "lx", "bmmc"});
	}
	/**查询辅导员编班增加班级信息QQ群号 根绝班级代码查询QQ群号*/
/*	public List<HashMap<String,String>> getQQqh(String bjdm){
		String sql = "select qqqh from XG_bjxxb_12303 where bjdm = ?";
		return dao.getListNotOut(sql, new String[]{bjdm});
	}*/
/*	*//**修改QQ群号*//*
	public boolean updateQQqh(String qqqh, String bjdm) throws Exception {
		
		StringBuffer sql = null;
		List<String[]> paramList = new ArrayList<String[]>();
		sql = new StringBuffer();
		sql.append("update XG_bjxxb_12303 set qqqh = ? where bjdm = ? ");	
		return true;
	}*/
	/**增加QQ群号
	 * @throws Exception */
	public boolean setQQqh(SzdwGeneralForm model) throws Exception{
		boolean flag = false;
		String sql;
		sql = "select count(qqqh) num from XG_bjxxb_12303 where bjdm=? ";
		String num = dao.getOneRs(sql, new String[] { model.getBjdm() }, "num");
		if ("0".equals(num)) {
			sql = "insert into XG_bjxxb_12303(bjdm,bjmc,qqqh) values(?,?,?)";
			flag = dao.runUpdate(sql, new String[] { model.getBjdm(), model.getBjmc(), model.getQqqh() });
		} else if("1".equals(num)) {
			sql="update XG_bjxxb_12303 set qqqh = ? where bjdm = ?";
			flag = dao.runUpdate(sql, new String[] { model.getQqqh(), model.getBjdm() });
		}else{
			throw new SystemException(MessageKey.AXCS_AXLB_AXLBYCZ);
		}

		return flag;
	}
	/**
	 * 辅导员信息维护自定义导出
	 * @param myForm
	 * @param user
	 * @return
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String,String>> getSzdwbbExportList(SzdwGeneralForm myForm, User user)
	throws IllegalArgumentException, SecurityException,
	IllegalAccessException, InvocationTargetException,
	NoSuchMethodException {
		DAO dao=DAO.getInstance();
		// ====================过滤条件===================================
		
		SearchModel searchModel=myForm.getSearchModel();
		
		String search_lx =searchModel.getMhcx_lx();
		String input_mhcx=searchModel.getInput_mhcx();
		
		StringBuilder query=new StringBuilder();
		String []input=null;
		if(!Base.isNull(input_mhcx)){
			
			input=getMhcxInfo(query, input, search_lx, input_mhcx);
			
			searchModel.setMhcx_lx(null);
			searchModel.setInput_mhcx(null);
		}
		// 高级查询条件
		String searchTj = SearchService.getSearchTj(searchModel);
		
		// 权限过滤
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a",
				"xydm", "bjdm");
		// 高级查询输入值
		String[] inputV = SearchService.getTjInput(searchModel);

		// 高级查询条件
		query.append(searchTj+searchTjByUser);
		
		// ====================过滤条件 end================================

		// ====================SQL拼装================================
		StringBuilder tableSql = new StringBuilder();
		
		/*tableSql.append(" select rownum r,a.* from (select a.nj||'!!luojw!!'||a.xydm||'!!luojw!!'||a.zydm||'!!luojw!!'||a.bjdm pk, ");
		tableSql.append(" a.nj,a.xymc,a.xydm,a.zydm,a.zymc,a.bjdm,a.bjmc,d.rs,fdyxm,bzrxm, ");
		
		// 带班辅导员简易信息
		tableSql.append(" (case when length(fdyxm)>10 then substr(fdyxm,1,10)||'...' else fdyxm end )fdy, ");
		// 带班班主任简易信息
		tableSql.append(" (case when length(bzrxm)>10 then substr(bzrxm,1,10)||'...' else bzrxm end )bzr, ");
		tableSql.append(" fdyzgh,bzrzgh, ");
		tableSql.append(" case when fdyxm is null then '否' else '是' end sfyszfdy, ");
		tableSql.append(" case when bzrxm is null then '否' else '是' end sfyszbzr ");
		tableSql.append(" from view_njxyzybj a ");
		tableSql.append(" left join (select bjdm,max(fdyxm)fdyxm,max(fdyzgh)fdyzgh from ( select bjdm, ");
		tableSql.append(" to_char(WM_CONCAT(xm) over(partition by bjdm order by bjdm))fdyxm, ");
		tableSql.append(" to_char(WM_CONCAT(zgh) over(partition by bjdm order by bjdm))fdyzgh ");
		
		tableSql.append(" from (select a.zgh,a.bjdm,b.xm from fdybjb a left join fdyxxb b on a.zgh=b.zgh))group by bjdm) b on a.bjdm = b.bjdm ");
		tableSql.append(" left join (select bjdm,max(bzrxm)bzrxm,max(bzrzgh)bzrzgh from (select bjdm, ");
		tableSql.append(" to_char(WM_CONCAT(xm)  over(partition by bjdm order by bjdm))bzrxm, ");
		tableSql.append(" to_char(WM_CONCAT(zgh)  over(partition by bjdm order by bjdm))bzrzgh ");
		
		tableSql.append(" from (select a.zgh,a.bjdm,b.xm from bzrbbb a left join fdyxxb b on a.zgh=b.zgh))group by bjdm) c on a.bjdm = c.bjdm left join (select bjdm,count(xh) rs from xsxxb where sfzx = '在校' or sfzx is null group by bjdm)d on a.bjdm=d.bjdm order by nj desc,xydm,zydm,bjdm) a where 1=1  ");*/
		
		tableSql.append("select * from VIEW_NEW_DC_SZDW_SZDWBB a where 1=1 ");
		tableSql.append(query);
	
		// ====================SQL拼装 end================================
		List<HashMap<String,String>> list = (ArrayList<HashMap<String,String>>) CommonQueryDAO
				.commonPageQueryForExportMap(myForm.getPages(), tableSql.toString(),
						dao.unionArray(input, inputV));		
		
		return list;
		}
	
	public String getBjmcForBjdm(String bjdm){
		String sql = "select bjmc from view_njxyzybj_all where bjdm = ?";
		return dao.getOneRs(sql, new String[]{bjdm}, "bjmc");
	}
	public String getCxQQqh(String bjdm){
		String sql = "select qqqh from XG_bjxxb_12303 where bjdm = ?";
		return dao.getOneRs(sql, new String[]{bjdm}, "qqqh");
	}
}
