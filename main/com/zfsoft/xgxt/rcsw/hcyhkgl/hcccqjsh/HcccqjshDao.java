/**
 * @部门:学工产品事业部
 * @日期：2013-12-18 上午08:52:28
 */  
package com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: 火车车程区间管理模块
 * @类功能描述: TODO(火车车程区间审核) 
 * @作者：Dlq[工号:995]
 * @时间： 2013-12-26 上午09:36:35 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class HcccqjshDao extends SuperDAOImpl<HcccqjshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("ccqjtxid");
		super.setTableName("xg_rcsw_hcyhk_hcccqjtxb");

	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(HcccqjshForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (");
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.bz,t1.shzt, t1.splc, t2.xm, (case t2.xb when '1' then  '男' when '2' then '女' else t2.xb end) xb, t3.bjmc,t2.sfzh, ");
		sql.append(" t2.lxdh,t2.sjhm, t31.xydm, t31.zydm,t31.xymc,t31.zymc,t2.zybj,t31.bjmc zybjmc,t2.bjdm,t2.nj, t4.guid shid,t4.gwid,t4.shr, t4.shyj, t6.mc || '[' || decode(t4.shzt, ");
		sql.append(" '0', '未审核',  '1',  '通过',  '2', '不通过','3', '退回', '4', '需重审',  '5','审核中') || ']' shztmc,t6.gwz,t7.xqmc,t8.lxmc hcyhklxmc, ");
		sql.append(" row_number() over(partition by t1.ccqjtxid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join xsxxb t2 on t1.xh = t2.xh left join view_njxyzybj_all t3 on t2.bjdm = t3.bjdm ");
		sql.append(" left join view_njxyzybj_all t31 on t2.zybj = t31.bjdm ");
		sql.append(" left join xg_xtwh_shztb t4  ");
		sql.append(" on t1.ccqjtxid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id   ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t8 on t1.hcyhklx = t8.lxdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append("  ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:TODO(获取火车车程区间填写详细信息)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-19 下午04:45:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getHcccqjshInfo(HcccqjshForm t){
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select t1.ccqjtxid,t1.xh,t1.txsj, t1.xn, t1.xq,t1.ccqdz,t1.cczdz,(t1.ccqdz || '-' ||t1.cczdz) hcccqjmc, ");
		sql.append(" t1.bz,t1.shzt, t1.splc, t2.xm, t2.xb, t2.bjmc,t2.lxdh, t2.xydm, t2.zydm,t2.bjdm,t3.xqmc,t2.nj,t4.lxmc hcyhklxmc ");
		sql.append(" from xg_rcsw_hcyhk_hcccqjtxb t1 left join view_xsxxb t2 on t1.xh = t2.xh  "); 
		sql.append(" left join  xqdzb t3 on t1.xq = t3.xqdm");
		sql.append(" left join XG_rcsw_hcyhk_hcyhklx t4 on t1.hcyhklx = t4.lxdm ");
		sql.append(" where t1.ccqjtxid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getCcqjtxid()});
	}

	/**
	 * 
	 * @描述:TODO(更新火车乘车区间填写)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午02:24:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccqjtxid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateHcccqjtx(String ccqjtxid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_hcyhk_hcccqjtxb ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where ccqjtxid = ?");
		inputV[0] = shzt;
		inputV[1] = ccqjtxid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(删除火车乘车区间结果)
	 * @作者：Dlq[工号：995]
	 * @日期：2013-12-24 下午02:22:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param ccqjjgid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteHcccqjtx(String ccqjtxid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_rcsw_hcyhk_hcccqjjgb ");
		sql.append(" where ccqjtxid = ? ");
		inputV[0] = ccqjtxid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
}
