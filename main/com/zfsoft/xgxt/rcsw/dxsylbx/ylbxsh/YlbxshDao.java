/**
 * @部门:学工产品事业部
 * @时间： 2014-1-10 下午03:02:24 
 */
package com.zfsoft.xgxt.rcsw.dxsylbx.ylbxsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 医疗保险审核管理模块
 * @类功能描述: TODO(医疗保险审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2014-1-10 下午03:02:24 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class YlbxshDao extends SuperDAOImpl<YlbxshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("ylsqid");
		super.setTableName("xg_rcsw_ylbx_ylbxsqb");

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(YlbxshForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.ylsqid,t1.xh,t1.xn,t1.xq,t1.cbsj, ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" ( case when t1.cbzkdm is not null  then '已参保' else '未参保' end ) cbzkmc, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm,t2.xb,t2.bjmc,t2.lxdh, "); 
		sql.append(" t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm,t2.nj,t4.guid shid, ");
		sql.append(" t4.gwid,t4.shr,t4.shyj,t7.xqmc, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', ");
		sql.append(" '通过','2','不通过','3','退回','4','需重审', '5', ");
		sql.append(" '审核中') || ']' shztmc,t6.gwz,row_number() over(partition by t1.ylsqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.ylsqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh = '"+user.getUserName()+"'   ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:TODO(得到医疗保险审核详细信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午02:59:56
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getYlbxshInfo(YlbxshForm t){
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.xh,t1.xn,t1.xq,t1.cbsj, ");
		sql.append(" t1.czqebzdm,t1.zjsyrxm,t1.zjh,t1.qtcbzkval, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xqmc "); 
		sql.append(" from xg_rcsw_ylbx_ylbxsqb t1 left join xqdzb t2 on t1.xq = t2.xqdm where t1.ylsqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{t.getYlsqid()});
	}
	
	/**
	 * 
	 * @描述:TODO(财政全额补助人员身份)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 上午08:59:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>>  getCzqebzrymcList(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		
		sql.append(" select czqebzmc  from xg_rcsw_ylbx_czqebzlxb  where czqebzdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.getList(sql.toString(),params.toArray(new String[]{}),new String[] { "czqebzmc" });
	}

	/**
	 * 
	 * @描述:TODO(参保状况名称)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 上午09:35:26
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param values
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getCbzkdmcsList(String[] values) throws Exception {

		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		sql.append(" select cbzkmc from xg_rcsw_ylbx_cbzklxb  where cbzkdm in  ");
		sql.append(" ( ");
		for (int i = 0 , n = values.length ; i < n ; i++){
			sql.append("?");
			params.add(values[i]);
			if(i != n-1){
				sql.append(",");
			}
		}
		sql.append(" ) ");
		return dao.getList(sql.toString(),params.toArray(new String[]{}),new String[] { "cbzkmc" });
	}
	
	
	/**
	 * 
	 * @描述:TODO(更新医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午02:55:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateYlbxsq(String bbsqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_ylbx_ylbxsqb ");
		sql.append(" set ");
		sql.append(" shzt = ? ");
		sql.append(" where ylsqid = ? ");
		inputV[0] = shzt;
		inputV[1] = bbsqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(删除医疗保险申请)
	 * @作者：Dlq[工号：995]
	 * @日期：2014-1-10 下午02:56:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteYlbxjg(String ylsqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from xg_rcsw_ylbx_ylbxjgb ");
		sql.append(" where ylsqid = ? ");
		inputV[0] = ylsqid;
		return dao.runDelete(sql.toString(),inputV)>=0 ? true:false;
	}
}
