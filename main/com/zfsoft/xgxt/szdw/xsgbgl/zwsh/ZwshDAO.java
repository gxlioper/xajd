/**
 * @部门:学工产品事业部
 * @日期：2013-7-25 下午4:15:17 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsh;

import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部管理职务审核
 * @作者： zhangjw
 * @时间： 2013-8-9 下午5:00:58 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */
public class ZwshDAO extends SuperDAOImpl<ZwshForm> {

	/*
	      描述:
	 */
	
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_xsgb_zwsqb");
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwshForm model)
			throws Exception {
		return null;
	}

	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwshForm model, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");	
		String shgwzByUser = SearchService.getShgwzByUser(user, "a",
				"xydm", "bjdm");
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select * from ( ");
		sql.append("  select m.xymc,m.xydm,m.zydm,m.bjdm,m.zymc,m.bjmc,m.zybj,m.zybjmc,m.symc1 symc,m.sydm1 sydm,m.xh,a.sqly,m.xm,a.sqsj,a.sqid,a.zwid,t.zwmc,b.gwid,b.shsj, b.guid shid,c.mc ||'['|| decode(b.shzt,0,'待审核',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')||']' shzt ,a.splc,t.zwzz,p.lxmc,b.shzt shztb    ");
		sql.append("  ,c.gwz,row_number()over(partition by a.sqid order by b.shsj desc) rn ");
		sql.append("  from xg_szdw_xsgb_zwsqb a");
		sql.append("  left join xg_szdw_xsgb_zwb t on a.zwid = t.zwid left join xg_szdw_xsgb_zwlxb p on t.lxdm = p.lxdm left join view_xsjbxx m on a.xh = m.xh ");
		sql.append("  left join xg_xtwh_shztb b on a.sqid = b.ywid   ");
		sql.append("  left join xg_xtwh_spgw c  on b.gwid = c.id  ");
		sql.append("  left join xg_xtwh_spgwyh d on c.id = d.spgw left join view_fdyxx e on a.sqr = e.zgh");
		sql.append("  where d.spyh = '"+user.getUserName()+"' and a.shzt<>9 and b.shzt<>9 ");
		String shlx = model.getShlx();
		if(!shlx.equals("dsh")){
			sql.append(" and (b.shzt<>0 and b.shzt<>4 )  ");
		}else{
			sql.append(" and ( b.shzt=0 or b.shzt=4 )  ");
		}
		
		sql.append(" order by b.shsj desc )a ");
		sql.append(" where rn = 1 )a where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		sql.append(shgwzByUser);
		return getPageList(model, sql.toString(), inputV);
	}
	
	public int updateZwsq(ZwshForm model) throws Exception{
		String sql = " update xg_szdw_xsgb_zwsqb b set b.shzt=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSqid()});
	}
	
	public boolean deleteZwsqjg(String zwsqid) throws Exception{
		String[] inputV = new String[1];
		String sql = " delete from  xg_szdw_xsgb_dwb b  where b.zwsqid = ?";
		//return dao.update(sql, new String[]{model.getSqid()});
		inputV[0] = zwsqid;
		return dao.runDelete(sql,inputV)>0 ? true:false;
	}
	
	public boolean updateSqjl(String ywid, String shzt) throws Exception{
		String sql = "update xg_szdw_xsgb_zwsqb set shzt=?  where sqid = ?";
		
		return dao.runUpdate(sql, new String[]{shzt,ywid});
		
	}

}
