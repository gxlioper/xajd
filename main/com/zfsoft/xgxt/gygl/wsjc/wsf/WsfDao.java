/**
 * @部门:学工产品事业部
 * @日期：2015-6-4 上午10:17:27 
 */  
package com.zfsoft.xgxt.gygl.wsjc.wsf;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchModel;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 卫生分录入
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-4 上午10:17:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsfDao extends SuperDAOImpl<WsfModel> {

	private static final String FSLX_FS = "0";
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(WsfModel.class);
		super.setTableName("xg_gygl_wsjc_wsfsb");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WsfModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WsfModel t, User user)
			throws Exception {
		
		
		return null;
	}
	
	
	/******寝室分数查询列表******/
	public List<HashMap<String, String>> getFscxListByQs(WsfModel t, User user)
			throws Exception {
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t4.ldmc,t2.lddm,t2.qsh,t2.wszf,t3.xqmc from xg_gygl_wsjc_jcrcb t1 left join ");
		sql.append("(select sum(t.wsf) wszf,t.rcid,t.lddm,t.qsh from xg_gygl_wsjc_wsfsb t ");
		sql.append("where exists (select 1 from xg_gygl_wsjc_jcxmb t2 where t.xmdm = t2.xmdm and t2.jcdx='0') ");
		sql.append("group by t.rcid,t.lddm,t.qsh) t2 on t1.id = t2.rcid ");
		sql.append("left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append("left join VIEW_XG_GYGL_NEW_QSXX t4 on t4.lddm = t2.lddm and t4.qsh = t2.qsh");
		
		//公寓辅导员权限
		if("yes".equalsIgnoreCase(user.getGyglyQx())){
			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '").append(user.getUserName()).append("') ");
		}
		
		sql.append(") where tjzt='1' ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	/******按床位分数查询列表******/
	public List<HashMap<String, String>> getFscxListByCw(WsfModel t, User user)
		throws Exception {
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t4.ldmc,t2.lddm,t2.qsh,t2.cwh,t2.wszf,t3.xqmc from xg_gygl_wsjc_jcrcb t1 left join ");
		sql.append("(select sum(t.wsf) wszf,t.rcid,t.lddm,t.qsh,t.cwh from xg_gygl_wsjc_wsfsb t ");
		sql.append("where exists (select 1 from xg_gygl_wsjc_jcxmb t2 where t.xmdm = t2.xmdm and t2.jcdx='1') ");
		sql.append("group by t.rcid,t.lddm,t.qsh,t.cwh) t2 on t1.id = t2.rcid ");
		sql.append("left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append("left join VIEW_XG_GYGL_NEW_QSXX t4 on t4.lddm = t2.lddm and t4.qsh = t2.qsh");
		
		//公寓辅导员权限
		if("yes".equalsIgnoreCase(user.getGyglyQx())){
			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '").append(user.getUserName()).append("') ");
		}
		
		sql.append(") where tjzt='1' ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/**按寝室录入列表***/
	public List<HashMap<String, String>> getQslrList(WsfModel t, User user)
			throws Exception {
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.jcdx,t4.xqmc from xg_gygl_wsjc_jcrcb t1 "); 
		sql.append("left join (");
		sql.append("     select distinct t1.rcid,t2.jcdx from xg_gygl_wsjc_rcxmb t1 ");
		sql.append("     left join xg_gygl_wsjc_jcxmb t2 on t1.xmdm = t2.xmdm");
		sql.append(") t2 on t1.id = t2.rcid ");
//		sql.append("left join (");
//		sql.append("     select id,jcdx,sum(lrs) lrs,sum(wlrs) wlrs from (");
//		sql.append("      select t1.id,nvl(jcdx,'0') jcdx,");
//		sql.append("             case when t3.lrr is null then 0 else 1 end lrs,");
//		sql.append("             case when t3.lrr is null and (t3.jcdx='0' or t3.jcdx is null) then 1 else 0 end wlrs");
//		sql.append("      from xg_gygl_wsjc_jcrcb t1");
//		sql.append("      left join xg_gygl_new_qsxxb t2 on 1=1");
//		sql.append("      left join xg_gygl_wsjc_wsfsb t3 on t1.id = t3.rcid and t2.lddm = t3.lddm and t2.qsh = t3.qsh and (t3.jcdx='0' or t3.jcdx is null) ");
//		//公寓辅导员权限
//		if("yes".equalsIgnoreCase(user.getGyglyQx())){
//			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = ?) ");
//		}
//
//		sql.append("    ) group by id,jcdx");
//		sql.append(") t3 on t1.id = t3.id and t2.jcdx = t3.jcdx ");
		sql.append("left join xqdzb t4 on t1.xq = t4.xqdm ");
		sql.append("where jcdx = '0' ");
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
		
	}
	
	
	/**按床位录入列表***/
	public List<HashMap<String, String>> getCwlrList(WsfModel t, User user)
			throws Exception {
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.jcdx,t4.xqmc from xg_gygl_wsjc_jcrcb t1 "); 
		sql.append("left join (");
		sql.append("     select distinct t1.rcid,t2.jcdx from xg_gygl_wsjc_rcxmb t1 ");
		sql.append("     left join xg_gygl_wsjc_jcxmb t2 on t1.xmdm = t2.xmdm");
		sql.append(") t2 on t1.id = t2.rcid ");
//		sql.append("left join (");
//		sql.append("     select id,jcdx,sum(lrs) lrs,sum(wlrs) wlrs from (");
//		sql.append("      select t1.id,'1' jcdx,");
//		sql.append("             case when t3.lrr is null then 0 else 1 end lrs,");
//		sql.append("             case when t3.lrr is null then 1 else 0 end wlrs");
//		sql.append("      from xg_gygl_wsjc_jcrcb t1");
//		sql.append("      left join xg_gygl_new_cwxxb t2 on 1=1");
//		sql.append("      left join xg_gygl_wsjc_wsfsb t3 on t1.id = t3.rcid and t2.lddm = t3.lddm and t2.qsh = t3.qsh and t2.cwh = t3.cwh and t3.jcdx='1'");
//		//公寓辅导员权限
//		if("yes".equalsIgnoreCase(user.getGyglyQx())){
//			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = ?) ");
//		}
//
//		sql.append("    ) group by id,jcdx");
//		sql.append(") t3 on t1.id = t3.id and t2.jcdx = t3.jcdx ");
		sql.append("left join xqdzb t4 on t1.xq = t4.xqdm ");
		sql.append("where jcdx = '1' ");
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}
	
	
	
	/***寝室卫生分录入列表**/
	public List<HashMap<String,String>> getQsflrList(WsfModel t, User user,List<HashMap<String,String>> rcxmList) throws Exception{
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.ldmc,case when t2.yzcws>0 then '是' else '否' end sfrz from (");
		sql.append("select lddm,qsh,");
		
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			sql.append("WM_CONCAT(fs").append(i).append(") fs").append(i);
			if (i+1 != j){
				sql.append(",");
			}
		}
		
		sql.append(" from (select t2.lddm,t2.qsh,");
		
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			sql.append("case when t.xmdm = '").append(rcxmList.get(i).get("xmdm"));
			
			if (FSLX_FS.equals(t.getFslx())){
				sql.append("' then t3.wsf end fs");
			} else {
				sql.append("' then t3.wsdj end fs");
			}
			
			sql.append(i);
			if (i+1 != j){
				sql.append(",");
			}
		}
		sql.append(" from xg_gygl_wsjc_jcrcb t1");
		sql.append(" left join xg_gygl_wsjc_rcxmb t on t1.id = t.rcid");
		sql.append(" left join xg_gygl_new_qsxxb t2 on 1=1");
		sql.append(" left join xg_gygl_wsjc_wsfsb t3 on t1.id = t3.rcid ");
		sql.append(" and t.xmdm=t3.xmdm and t2.lddm = t3.lddm and t2.qsh = t3.qsh ");
//		sql.append(" left join xg_gygl_new_ldxxb t4 on t2.lddm = t4.lddm ");
		sql.append(" where t1.id=?");
		sql.append(" ) group by lddm,qsh");
		sql.append(" ) t1 left join VIEW_XG_GYGL_NEW_QSXX t2 on t1.lddm = t2.lddm and t1.qsh = t2.qsh ");
		//公寓辅导员权限
		if("yes".equalsIgnoreCase(user.getGyglyQx())){
			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '").append(user.getUserName()).append("') ");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		return super.getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getRcid()},inputV));
	}
	
	
	/***床位卫生分录入列表**/
	public List<HashMap<String,String>> getCwflrList(WsfModel t, User user,List<HashMap<String,String>> rcxmList) throws Exception{
		
		SearchModel searchModel = t.getSearchModel();
		String searchTj = SearchService.getSearchTj(searchModel);
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.*,t2.ldmc,case when t2.xh is not null then '是' else '否' end sfrz from (");
		sql.append("select lddm,qsh,cwh,");
		
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			sql.append("WM_CONCAT(fs").append(i).append(") fs").append(i);
			if (i+1 != j){
				sql.append(",");
			}
		}
		
		sql.append(" from (select t2.lddm,t2.qsh,t2.cwh,");
		
		for (int i = 0 , j = rcxmList.size() ; i < j ; i++){
			sql.append("case when t.xmdm = '").append(rcxmList.get(i).get("xmdm"));
			
			if (FSLX_FS.equals(t.getFslx())){
				sql.append("' then t3.wsf end fs");
			} else {
				sql.append("' then t3.wsdj end fs");
			}
			
			sql.append(i);
			if (i+1 != j){
				sql.append(",");
			}
		}
		
		sql.append(" from xg_gygl_wsjc_jcrcb t1");
		sql.append(" left join xg_gygl_wsjc_rcxmb t on t1.id = t.rcid");
		sql.append(" left join xg_gygl_new_cwxxb t2 on 1=1");
		sql.append(" left join xg_gygl_wsjc_wsfsb t3 on t1.id = t3.rcid ");
		sql.append(" and t.xmdm=t3.xmdm and t2.lddm = t3.lddm and t2.qsh = t3.qsh and t2.cwh=t3.cwh");
		sql.append(" where t1.id=?");
		sql.append(" ) group by lddm,qsh,cwh");
		sql.append(" ) t1 left join VIEW_XG_GYGL_NEW_CWXX t2 on t1.lddm = t2.lddm and t1.qsh = t2.qsh and t1.cwh = t2.cwh ");
		//公寓辅导员权限
		if("yes".equalsIgnoreCase(user.getGyglyQx())){
			sql.append("  where t2.lddm in (select lddm from xg_gygl_new_gyfdyb where yhm = '").append(user.getUserName()).append("') ");
		}
		sql.append(") where 1=1 ");
		sql.append(searchTj);
		return super.getPageList(t, sql.toString(), StringUtils.joinStrArr(new String[]{t.getRcid()},inputV));
	}
	
	
	/**分数录入--等级**/
	public List<String> getFslrDj() throws Exception {
		String sql = " select dj from XG_GYGL_NEW_WSJC_DJFSB where lx='0'";
		return dao.getList(sql, new String[]{}, "dj");
	}
	
	
	/**分数录入--星级**/
	public List<String> getFslrXj() throws Exception {
		String sql = " select dj from XG_GYGL_NEW_WSJC_DJFSB where lx='1'";
		return dao.getList(sql, new String[]{}, "dj");
	}
	
	
	/**按日程项目删除卫生分**/
	public boolean delWsfByCwh(WsfModel t) throws Exception{
		String sql = "delete from xg_gygl_wsjc_wsfsb where rcid = ? and xmdm = ? and lddm=? and qsh=? and cwh=?";
		return dao.runDelete(sql, new String[]{t.getRcid(),t.getXmdm(),t.getLddm(),t.getQsh(),t.getCwh()}) > 0;
	}
	
	/**按日程项目删除卫生分**/
	public boolean delWsfByQsh(WsfModel t) throws Exception{
		String sql = "delete from xg_gygl_wsjc_wsfsb where rcid = ? and xmdm = ? and lddm=? and qsh=? ";
		return dao.runDelete(sql, new String[]{t.getRcid(),t.getXmdm(),t.getLddm(),t.getQsh()}) > 0;
	}
	

	/**批量保存卫生分**/
	public boolean saveQsWsf(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_gygl_wsjc_wsfsb t1 ");
		sql.append(" USING ");
		sql.append("(select ? rcid,? lddm,? qsh,? lrr,? xmdm,? wsf from dual) t2 ");
		sql.append(" ON (t1.rcid=t2.rcid and t1.lddm=t2.lddm and t1.qsh=t2.qsh and t1.xmdm=t2.xmdm) ");
		sql.append(" when matched then ");
		sql.append("update set t1.wsf = t2.wsf ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("  insert (rcid,lddm,qsh,lrr,xmdm,wsf) ");
		sql.append("  values (t2.rcid,t2.lddm,t2.qsh,t2.lrr,t2.xmdm,t2.wsf)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	/**批量保存卫生分**/
	public boolean saveQsWsdj(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_gygl_wsjc_wsfsb t1 ");
		sql.append(" USING ");
		sql.append("(select ? rcid,? lddm,? qsh,? lrr,? xmdm,? wsdj from dual) t2 ");
		sql.append(" ON (t1.rcid=t2.rcid and t1.lddm=t2.lddm and t1.qsh=t2.qsh and t1.xmdm=t2.xmdm) ");
		sql.append(" when matched then ");
		sql.append("update set t1.wsdj = t2.wsdj ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("  insert (rcid,lddm,qsh,lrr,xmdm,wsdj) ");
		sql.append("  values (t2.rcid,t2.lddm,t2.qsh,t2.lrr,t2.xmdm,t2.wsdj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}	
	
	/**批量保存卫生分**/
	public boolean saveWsf(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_gygl_wsjc_wsfsb t1 ");
		sql.append(" USING ");
		sql.append("(select ? rcid,? lddm,? qsh,? cwh,? lrr,? xmdm,? wsf from dual) t2 ");
		sql.append(" ON (t1.rcid=t2.rcid and t1.lddm=t2.lddm and t1.qsh=t2.qsh and t1.cwh=t2.cwh and t1.xmdm=t2.xmdm) ");
		sql.append(" when matched then ");
		sql.append("update set t1.wsf = t2.wsf ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("  insert (rcid,lddm,qsh,cwh,lrr,xmdm,wsf) ");
		sql.append("  values (t2.rcid,t2.lddm,t2.qsh,t2.cwh,t2.lrr,t2.xmdm,t2.wsf)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
	
	/**批量保存卫生分**/
	public boolean saveWsdj(List<String[]> params) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("MERGE INTO xg_gygl_wsjc_wsfsb t1 ");
		sql.append(" USING ");
		sql.append("(select ? rcid,? lddm,? qsh,? cwh,? lrr,? xmdm,? wsdj from dual) t2 ");
		sql.append(" ON (t1.rcid=t2.rcid and t1.lddm=t2.lddm and t1.qsh=t2.qsh and t1.cwh=t2.cwh and t1.xmdm=t2.xmdm) ");
		sql.append(" when matched then ");
		sql.append("update set t1.wsdj = t2.wsdj ");
		sql.append("WHEN NOT MATCHED THEN ");
		sql.append("  insert (rcid,lddm,qsh,cwh,lrr,xmdm,wsdj) ");
		sql.append("  values (t2.rcid,t2.lddm,t2.qsh,t2.cwh,t2.lrr,t2.xmdm,t2.wsdj)");
		
		int[] result = dao.runBatch(sql.toString(), params);
		return dao.checkBatchResult(result);
	}
}
