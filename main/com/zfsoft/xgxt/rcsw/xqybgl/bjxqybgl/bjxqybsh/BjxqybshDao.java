/**
 * @部门:学工产品事业部
 * @日期：2016-3-31 下午01:46:16 
 */  
package com.zfsoft.xgxt.rcsw.xqybgl.bjxqybgl.bjxqybsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 杜利骑[工号:995]
 * @时间： 2016-3-31 下午01:46:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class BjxqybshDao extends SuperDAOImpl<BjxqybshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybshForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(BjxqybshForm t, User user)
			throws Exception {

		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "a", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();				
		sql.append(" select a.* from ( select t1.sqid,t1.xn,t1.xq,t1.yf ny,substr(t1.yf,1, 4)nd,substr(t1.yf,6,2)yf,t1.bygzkzqk, ");		
		sql.append(" t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj,t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.xydm,t2.nj,t2.bjmc,t2.zymc,t2.zydm,t7.xqmc,t3.xm lrrxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");
		String shlx = t.getShzt();
		if(!shlx.equals("dsh")){
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4) ");
		}else{
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )" );
		}
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t5.spyh = '"+user.getUserName()+"' ");
		sql.append(" ) a where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);		
		
		return getPageList(t, sql.toString(), inputV);
		
	}

	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—班级学情审核填写页面内容)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-12 下午02:18:27
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param user
	 * @param model
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String,String> getBjxqybshInfo(User user,BjxqybshForm model){
		
		StringBuffer sql = new StringBuffer();				
		sql.append(" select a.* from ( select t1.sqid,t1.xn,t1.xq,t1.yf,t1.bygzkzqk,t1.xsgzrd,t1.xssxdt,t1.xstsjgzjy,t1.txsj, ");		
		sql.append(" t1.txr,t1.shzt,t1.splc,t1.bjdm,t2.xymc,t2.bjmc,t2.zymc,t7.xqmc,t3.xm lrrxm, ");
		sql.append(" t4.guid shid, t4.gwid,t4.shr,t4.shyj, ");
		sql.append(" t6.mc || '[' ||decode(t4.shzt, '0', '未审核', '1', '通过','2','不通过','3','退回','4','需重审', '5', '审核中') || ']' shztmc, ");
		sql.append(" t6.gwz,row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append(" from xg_bjzyy_xqyb_bjyb_sq t1 ");
		sql.append(" left join view_njxyzybj_all t2 on t1.bjdm = t2.bjdm ");
		sql.append(" left join fdyxxb t3 on t1.txr = t3.zgh ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid ");		
		sql.append(" left join xg_xtwh_spgwyh t5 on t4.gwid = t5.spgw ");
		sql.append(" left join xg_xtwh_spgw t6 on t4.gwid = t6.id ");
		sql.append(" left join xqdzb t7 on t1.xq = t7.xqdm ");
		sql.append(" where t5.spyh = ? ");
		sql.append(" ) a where sqid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{user.getUserName(),model.getSqid()});
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—更新班级学情申请)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-6 下午04:48:49
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @param shzt
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean updateBjxqybsq(String sqid,String shzt) throws Exception{
		String[] inputV = new String[2];
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE xg_bjzyy_xqyb_bjyb_sq ");
		sql.append(" set ");
		sql.append(" shzt = ?");
		sql.append(" where sqid = ?");
		inputV[0] = shzt;
		inputV[1] = sqid;
		return dao.update(sql.toString(),inputV)>0 ? true:false;
	}
	
	/**
	 * 
	 * @描述:TODO(班级学情月报管理_班级学情审核—删除班级学情月报结果)
	 * @作者：杜利骑[工号：995]
	 * @日期：2016-4-6 下午04:52:00
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqid
	 * @return
	 * @throws Exception
	 * boolean 返回类型 
	 * @throws
	 */
	public boolean deleteBjxqybjg(String sqid) throws Exception{
		String[] inputV = new String[1];
		StringBuilder sql = new StringBuilder();
		sql.append(" delete from  xg_bjzyy_xqyb_bjyb_jg ");
		sql.append(" where lylcywid = ? ");
		inputV[0] = sqid;
		return dao.runDelete(sql.toString(),inputV)>0 ? true:false;
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setKey("sqid");
		super.setTableName("xg_bjzyy_xqyb_bjyb_sq");
		super.setClass(BjxqybshForm.class);
	}

}
