/**
 * @部门:学工产品事业部
 * @日期：2014-11-25 上午09:38:51 
 */  
package com.zfsoft.xgxt.rcsw.lstd.lstdsh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务	
 * @类功能描述: 绿色通道
 * @作者： cq [工号:785]
 * @时间： 2014-11-25 上午09:38:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class LstdshDao extends SuperDAOImpl<LstdshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdshForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(LstdshForm t, User user)
			throws Exception {
		
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String shgwzByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.* from (select t1.sqid,t1.xh,t1.xn,t1.xq,(select xqmc from xqdzb b where b.xqdm=t1.xq) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm,t2.xymc,t2.zymc,t2.bjdm, t2.nj, ");
		sql.append(" t3.lxmc,t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||   ");
		sql.append(" decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_rcsw_lstd_sqb t1  left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append("left join xg_rcsw_lstd_lxwhb t3  on t1.lxdm = t3.lxdm  left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh ='"+user.getUserName()+"' ");

		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");					
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		
		super.setKey("sqid");
		super.setTableName("xg_rcsw_lstd_sqb");

	}
	
	/**
	 * 
	 * @描述:单个查询
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:05:19
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String>  getLstdshInfo(LstdshForm t){

		StringBuilder sql = new StringBuilder();
		sql.append(" select t1.sqid,t1.xh,t1.xn,(select xqmc from xqdzb b where b.xqdm=t1.xq) xqmc,t1.sqsj,t1.lxdm, ");
		sql.append(" t1.sqly,t1.shzt,t1.splc,t2.xm, t2.xb,t2.bjmc,t2.lxdh, t2.xydm,t2.zydm, t2.bjdm, t2.nj, ");
		sql.append(" decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc,t3.lxmc from xg_rcsw_lstd_sqb t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh left join xg_rcsw_lstd_lxwhb t3 ");
		sql.append(" on t1.lxdm = t3.lxdm   where t1.sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{t.getSqid()});
	}
	
	
	/**
	 * 
	 * @描述:更新绿色通道申请
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:09:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateLstdsq(String sqid,String shzt) throws Exception{
		
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_rcsw_lstd_sqb ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	
	/**
	 * 
	 * @描述:删除申请结果表
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:11:58
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param bbsqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteLstdsq(String sqid) throws Exception{
		
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_rcsw_lstd_jgb ");
		sql.append(" where sqid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
	
	
	/**
	 * 
	 * @描述:根据ID获取申请信息
	 * @作者：cq [工号：785]
	 * @日期：2014-11-26 上午09:28:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param id
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	
	
	public Map<String, String> getOneLstdsqInfo(String id){
	
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from xg_rcsw_lstd_sqb where sqid = ? ");
	
		return dao.getMapNotOut(sql.toString(), new String[]{id});
	}
	
	

	
	

}
