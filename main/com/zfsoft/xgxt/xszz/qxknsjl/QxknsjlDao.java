/**
 * @部门:学工产品事业部
 * @日期：2016-4-20 下午06:25:44 
 */  
package com.zfsoft.xgxt.xszz.qxknsjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.extend.SuperDAOImplExtend;


/**
 * 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-4-21 上午08:35:23 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class QxknsjlDao extends SuperDAOImplExtend<QxknsjlForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(QxknsjlForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(QxknsjlForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());		
		StringBuffer sql = new StringBuffer("");				
		sql.append(" select rownum r,a.* from ( select t1.jgguid,t1.czr czrdm,(select xm from yhb t4 where t1.czr=t4.yhm) czrxm, ");
		sql.append(" t1.czsj,t1.qxyy,t2.xh,t2.xn,t2.xq,t2.sqsj,t2.rddc,(select dcmc from xg_xszz_new_kndcdmb t4 where t2.rddc=t4.dcdm) dcmc, ");
		sql.append(" t2.sqly,t2.sjly,t2.lylcywid,t2.sfqxrd,t3.xm,t3.xb,t3.nj,t3.xydm,  "); 		
		sql.append(" t3.xymc,t3.zydm,t3.zymc,t3.bjdm,t3.bjmc,t3.sjhm from xg_xszz_new_knsqxjl "); 
		sql.append(" t1 left join xg_xszz_new_knsjgb  t2 on t1.jgguid = t2.guid left join view_xsbfxx t3 on t2.xh = t3.xh ) "); 
		sql.append(" a where  1=1  "); 	 	
		sql.append(searchTjByUser);		
		sql.append(searchTj);		
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	/**
	 * 
	 * @描述:TODO(获得单个学生认定信息)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-26 下午05:37:10
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param guid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsjgList(String  guid) {
		StringBuilder sql = new StringBuilder();			
		sql.append(" select t1.sqly,t1.sqsj,t1.xn,t2.dcmc from xg_xszz_new_knsjgb t1 ");
		sql.append(" left join xg_xszz_new_kndcdmb t2 on t1.rddc = t2.dcdm where guid=? ");
		return dao.getMapNotOut(sql.toString(),new String[]{guid});
	}   
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-27 上午08:25:36
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jgguid
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String, String> getOneKnsqxjlList(String  jgguid) {
		StringBuilder sql = new StringBuilder(" select czr,(select xm from yhb t4 where t1.czr=t4.yhm)czrxm, czsj,qxyy from xg_xszz_new_knsqxjl t1 where jgguid=? ");			
		return dao.getMapNotOut(sql.toString(),new String[]{jgguid});
	} 
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setTableName("xg_xszz_new_knsqxjl");
		super.setKey("guid");
	}


}
