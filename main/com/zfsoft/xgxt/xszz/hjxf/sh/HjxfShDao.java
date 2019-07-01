/**
 * @部门:学工产品事业部
 * @日期：2016-3-15 上午11:50:37 
 */  
package com.zfsoft.xgxt.xszz.hjxf.sh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xszz.hjxf.sq.HjxfSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-3-15 上午11:50:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HjxfShDao extends SuperDAOImpl<HjxfShForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfShForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(HjxfShForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String shgwzByUser = SearchService.getShgwzByUser(user, "t", "xydm",
				"bjdm");
		// String qxfw = SearchService.getQxfw(user, "t.gwid", "t.qxfw",
		// "t.yrdw",searchTjByUser);
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append(" select t.*,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.nj,");
		sql.append(" t1.xymc,");
		sql.append(" t1.xydm,");
		sql.append(" t1.bjdm,");
		sql.append(" t1.bjmc,");
		sql.append(" t1.zydm,");
		sql.append(" t1.zymc,");
		sql.append(" t2.guid shid,");
		sql.append(" t2.gwid,");
		sql.append(" t2.shr,");
		sql.append(" t2.shyj,");
		sql.append(" t4.mc || '[' ||");
		sql.append(" decode(t2.shzt,");
		sql.append(" '0',");
		sql.append(" '未审核',");
		sql.append(" '1',");
		sql.append(" '通过',");
		sql.append(" '2',");
		sql.append(" '不通过',");
		sql.append(" '3',");
		sql.append(" '退回',");
		sql.append(" '4',");
		sql.append(" '需重审',");
		sql.append(" '5',");
		sql.append(" '审核中') || ']' shztmc,");
		sql.append(" t4.gwz,");
		sql.append(" t.xn || t5.xqmc xnxq,");
		sql.append(" row_number() over(partition by t.hjxfid order by t2.shsj desc) rn");
		sql.append(" from XG_XSZZ_NEW_HJXFSQB t");
		sql.append(" left join view_xsjbxx t1");
		sql.append(" on t.xh = t1.xh");
		sql.append(" left join xg_xtwh_shztb t2");
		sql.append(" on t.hjxfid = t2.ywid");
		sql.append(" left join xg_xtwh_spgwyh t3");
		sql.append(" on t2.gwid = t3.spgw");
		sql.append(" left join xg_xtwh_spgw t4");
		sql.append(" on t2.gwid = t4.id");
		sql.append(" left join xqdzb t5");
		sql.append(" on t.xq = t5.xqdm");
		sql.append("  where t3.spyh = '"+user.getUserName()+"' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t2.shzt<>0 and  t2.shzt<>4)");
		} else {
			sql.append(" and (t2.shzt=0  or t2.shzt = 4 )");
		}
		sql.append(" ");
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTj);
		// sql.append(qxfw);
		sql.append(shgwzByUser);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(HjxfShForm.class);
		super.setKey("hjxfid");
		super.setTableName("XG_XSZZ_NEW_HJXFSQB");
	}
	

	public boolean updateSqjl(String ywid, String shzt) throws Exception {
		String sql = "update XG_XSZZ_NEW_HJXFSQB set shzt=?  where hjxfid = ?";
		return dao.runUpdate(sql, new String[] { shzt, ywid });
	}
}
