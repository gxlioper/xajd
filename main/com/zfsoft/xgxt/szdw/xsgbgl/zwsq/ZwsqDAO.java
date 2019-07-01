/**
 * @部门:学工产品事业部
 * @日期：2013-7-24 下午4:18:18 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwsq;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务申请
 * @作者： zhangjw
 * @时间： 2013-8-8 下午2:30:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class ZwsqDAO extends SuperDAOImpl<ZwsqForm> {

	/*
	      描述:
	 */
	@Override
	protected void setTableInfo() {
		super.setKey("sqid");
		super.setTableName("xg_szdw_xsgb_zwsqb");
		super.setClass(ZwsqForm.class);
	}
	/*
	      描述:
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ZwsqForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/*
	      描述:查询培训项目申请列表
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZwsqForm model, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder(" select * from (");
		sql.append(" select d.xh,d.xydm,d.zydm,d.bjdm,d.xymc,d.zymc,d.xm,");
		sql.append("d.xb,d.nj,d.bjmc,d.zzmmmc,d.mz,d.yhmc,d.yhkh,e.tc,e.XJLBMC, ");
		sql.append("sqid, sqsj, sqly,  b.*, a.shzt as shztdm," );
		sql.append("decode(shzt,0,'未提交',1,'通过',2,'不通过',3,'退回',4,'需重审',5,'审核中','其它')shzt,  ");
		sql.append("a.splc,c.lxmc ");
		sql.append(" from xg_szdw_xsgb_zwsqb a " );
		sql.append("left join xg_szdw_xsgb_zwb b on a.zwid = b.zwid " );
		sql.append("left join xg_szdw_xsgb_zwlxb c on b.lxdm = c.lxdm " );
		sql.append("left join view_xsjbxx d on a.xh = d.xh " );
		sql.append("left join view_xsxxb e on a.xh = e.xh " );
		sql.append(" where a.shzt <>9 ");
		sql.append(" order by a.sqsj desc)t where 1=1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}
	/**
	 * @描述:查询已经申请的数量
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:35:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zgh
	 * @param xmdm
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCount(String xh,String zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(1) from xg_szdw_xsgb_zwsqb b where b.xh = '"+xh+"' and b.zwid ='"+zwid+"' and b.shzt not in(9,2)");
		return dao.getOneRsint(sql.toString());
	}
	/**
	 * @描述:取消申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:35:37
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param spids
	 * @return
	 * @throws SQLException
	 * int[] 返回类型
	 */
	public int[] updateSq(String[] spids ) throws SQLException{
		String sql = " update xg_szdw_xsgb_zwsqb b set b.shzt='9' where b.sqid = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (int i = 0; i < spids.length; i++) {
			params.add(new String[]{spids[i]});
		}
		return dao.runBatch(sql, params);
	}
	/**
	 * @描述:根据职务代码是否被申请
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午2:36:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param zwid
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getSqCountByZwid(String zwid) throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select sum(sl) from (");
		sql.append(" select count(1)sl from xg_szdw_xsgb_zwsqb b where b.shzt<>9 and  b.zwid in( ");
		sql.append(zwid);
		sql.append(" )   union all ");
		sql.append(" select count(1)sl from xg_szdw_xsgb_dwb b where b.zwid in(");
		sql.append(zwid);
		sql.append(" ))");
		return dao.getOneRsint(sql.toString());
	}
	
	public int updateZwsq(ZwsqForm model) throws Exception{
		String sql = " update xg_szdw_xsgb_zwsqb b set b.shzt=? ,splc=? where b.sqid = ?";
		return dao.update(sql, new String[]{model.getShzt(),model.getSplc(),model.getSqid()});
	}
}
