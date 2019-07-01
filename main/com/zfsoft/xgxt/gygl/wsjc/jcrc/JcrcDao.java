/**
 * @部门:学工产品事业部
 * @日期：2015-6-1 上午09:02:24 
 */  
package com.zfsoft.xgxt.gygl.wsjc.jcrc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述:检查日程
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-1 上午09:02:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcrcDao extends SuperDAOImpl<JcrcModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(JcrcModel.class);
		super.setTableName("xg_gygl_wsjc_jcrcb");
		super.setKey("id");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcrcModel t)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xqmc from xg_gygl_wsjc_jcrcb t1 left join xqdzb t2 on t1.xq = t2.xqdm");
		sql.append(") t where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcrcModel t, User user)
			throws Exception {
		return null;
	}
	
	
	/**保存日程项目*/
	public boolean saveRcxm(String id,String[] xmdm) throws SQLException{
		
		String sql = "insert into xg_gygl_wsjc_rcxmb(rcid,xmdm) values (?,?)";
		List<String[]> params = new ArrayList<String[]>();
		
		for (String str : xmdm){
			params.add(new String[]{id,str});
		}
		int[] result = dao.runBatch(sql, params);
		return dao.checkBatchResult(result);
	}
	
	/**删除日程项目**/
	public boolean delRcxm(String id) throws Exception{
		return delRcxm(new String[]{id});
	}
	
	
	/**按日程查询检查项目*/
	public String[] getRcxmArr(String rcid) throws SQLException{
		String sql = "select xmdm from xg_gygl_wsjc_rcxmb where rcid = ?";
		return dao.getArray(sql, new String[]{rcid}, "xmdm");
	}
	
	
	
	/**删除日程项目**/
	public boolean delRcxm(String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from xg_gygl_wsjc_rcxmb t1 where (");
		
		for (int i = 0,j = ids.length ; i < j ; i++){
			sql.append(" rcid = ?");
			
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		sql.append(") and not exists (select 1 from xg_gygl_wsjc_wsfsb t2 where t1.rcid = t2.rcid)");
		return dao.runDelete(sql.toString(), ids) > 0;
	}
	
	
	/**更新提交状态***/
	public boolean updateTjzt(String tjzt, String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append("update xg_gygl_wsjc_jcrcb set tjzt=? where ");
		
		for (int i = 0,j = ids.length ; i < j ; i++){
			sql.append(" id = ?");
			
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), StringUtils.joinStrArr(new String[]{tjzt},ids)) > 0;
	}
	
	/**
	 * 
	 * @描述: 提交同步更新到XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-30 下午02:58:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean saveWsf(String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" insert into XG_QSFMX(xh,xn,xq,rcid,lddm,qsh,cwh,qsfs,cwfs,zfs) ");
		sql.append(" select a.xh, a.xn, a.xq, rcid, lddm, qsh, cwh, sum(a.qsfs) qsfs, sum(cwfs) as cwfs, sum(zfs) as zfs ");		
		sql.append(" from view_new_gygl_wsffs a ");
		sql.append(" where ");
		for (int i = 0,j = ids.length ; i < j ; i++){
			sql.append(" rcid = ?");
			
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		sql.append(" group by a.xh, a.xn, a.xq, rcid, lddm, qsh, cwh ");
		return dao.runDelete(sql.toString(), StringUtils.joinStrArr(ids)) > 0 ;
	}
	
	
	/**
	 * 
	 * @描述: 取消提交同步删除对应数据XG_QSFMX表（上海戏剧个性化）
	 * @作者：沈晓波[工号：1123]
	 * @日期：2015-11-30 下午04:52:04
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ids
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean delWsf(String[] ids) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from XG_QSFMX where ");
		for (int i = 0,j = ids.length ; i < j ; i++){
			sql.append(" rcid = ?");
			
			if (i+1 != j){
				sql.append(" or ");
			}
		}
		return dao.runDelete(sql.toString(), StringUtils.joinStrArr(ids)) > 0 ;
	}
	
	
	
	/**删除检查日程 **/
	public int runDelete(String[] values) throws Exception {
		if (values == null || values.length == 0){
			logger.error("删除操作不能进行!");
			throw new NullPointerException();
		}
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("delete from xg_gygl_wsjc_jcrcb t1");
		sql.append(" where ");
		sql.append("(");
		
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("id=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		
		sql.append(") and not exists (select 1 from xg_gygl_wsjc_wsfsb t2 where t1.id = t2.rcid)");
		
		return dao.runDelete(sql.toString(), values);
	}
	
}
