/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:15:17 
 */  
package com.zfsoft.xgxt.szdw.fdypx;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:辅导员培训审核
 * @作者： zhangjw
 * @时间： 2013-7-25 下午4:14:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FdypxXmshDAO extends SuperDAOImpl<FdypxXmshForm> {

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_fdypxsq");
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmshForm model)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		/*StringBuilder sql = new StringBuilder("select * from (");
		sql.append(" select m.sqid,m.sqr,c.xm,c.bmmc,c.bmdm,m.sqsj,n.xmdm,n.xmmc,n.pxdd,n.pxsj,n.fbsj,n.fbr,max(d.shsj)shsj from xg_szdw_fdypxsq m ,xg_szdw_fdypxxm n,view_fdyxx c,xg_xtwh_shztb d ");
		sql.append(" where m.xmdm = n.xmdm and m.shzt =1 and m.sqr = c.zgh ");
		sql.append(" and m.sqid = d.ywid ");
		
		sql.append(" group by m.sqid,m.sqr,c.xm,c.bmmc,c.bmdm,m.sqsj,n.xmdm,n.xmmc,n.pxdd,n.pxsj,n.fbsj,n.fbr ) where 1=1");
		*/
		sql.append("select * from VIEW_NEW_DC_SZDW_FDYPXJG where 1=1 ");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FdypxXmshForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "bmdm", "bjdm");	
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select * from ( ");
		sql.append("  select e.bmmc,e.bmdm,a.sqr,a.sqly,e.xm,a.sqsj,a.sqid,t.xmmc,b.gwid,b.shsj, b.guid shid,c.mc ||'['|| decode(b.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')||']' shzt ,a.splc    ");
		sql.append(" ,row_number()over(partition by a.sqid order by b.shsj desc) rn ");
		sql.append(" from xg_szdw_fdypxsq a ");
		sql.append("  left join xg_szdw_fdypxxm t on a.xmdm = t.xmdm ");
		sql.append("  join xg_xtwh_shztb b on a.sqid = b.ywid   ");
		sql.append("  join xg_xtwh_spgw c  on b.gwid = c.id  ");
		sql.append("  join xg_xtwh_spgwyh d on c.id = d.spgw join view_fdyxx e on a.sqr = e.zgh");
		sql.append("  where d.spyh = '"+user.getUserName()+"' and a.shzt<>9 and b.shzt<>9 ");
		String shlx = model.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		if(!user.getUserStatus().equals("xx")){
			sql.append(" and (e.bmdm = '"+ user.getUserDep() +"')");			
		}
		
		sql.append(" order by b.shsj desc )a ");
		sql.append(" where rn = 1 )a where 1=1");
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

	public int updateFdyrzsq(FdypxXmshForm model) throws Exception{
		String sql = " update xg_szdw_fdypxsq b set b.shzt=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSqid()});
	}
	
	/**
	 * 
	 * @描述:获得结果信息，不包含自己
	 * @作者：cq [工号：785]
	 * @日期：2015-4-24 下午05:02:39
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param sqr
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getSqjg(String sqid){
		StringBuilder sql = new StringBuilder();
		sql.append("select b.xmmc,b.pxsj,b.pxdd from xg_szdw_fdypxsq a left join xg_szdw_fdypxxm b on a.xmdm=b.xmdm");
		sql.append(" where a.shzt='1' and a.sqr=(select sqr from xg_szdw_fdypxsq where sqid = ? ) ");
		return dao.getListNotOut(sql.toString(), new String[]{sqid});
	}
}
