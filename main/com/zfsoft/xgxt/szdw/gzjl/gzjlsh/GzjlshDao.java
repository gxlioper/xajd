/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午04:35:10 
 */  
package com.zfsoft.xgxt.szdw.gzjl.gzjlsh;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-6-25 下午04:35:10 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GzjlshDao extends SuperDAOImpl<GzjlshForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlshForm t) throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(GzjlshForm t, User user) throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchGzjlTjByUser(user, "t", "xydm", "bjdm");
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (select ");
		sql.append("t1.*,t2.xm,t2.xymc,t2.xydm,t3.lbmc gzlbmc,t7.lksmc,");
		sql.append("t4.guid shid,t4.gwid,t4.shr,t4.shyj,t6.mc || '[' ||");
		sql.append("decode(t4.shzt, '0', '未审核', '1', '通过', '2', '不通过', '3',  '退回', '4', '需重审', '5', '审核中') || ']' shztmc,t6.gwz, ");
		sql.append(" row_number() over(partition by t1.sqid order by t4.shsj desc) rn ");
		sql.append("from xg_gzjlgl_gzjlsqb t1 left join view_jsxx t2 on t1.zgh = t2.zgh left join xg_gzjlgl_gzlbb t3 on t1.lbdm=t3.lbdm ");
		sql.append(" left join xg_xtwh_shztb t4 on t1.sqid = t4.ywid left join XG_SZDWX_LKSDMB t7 on t1.lks = t7.lksdm");
		sql.append(" left join xg_xtwh_spgwyh t5 on  t4.gwid = t5.spgw left join xg_xtwh_spgw t6 on t4.gwid = t6.id where t5.spyh ='" + user.getUserName() + "' ");
		String shlx = t.getShzt();
		if (!shlx.equals("dsh")) {
			sql.append(" and (t4.shzt<>0 and  t4.shzt<>4)");
		} else {
			sql.append(" and (t4.shzt=0  or t4.shzt = 4 )");
		}
		sql.append(" ) t where 1=1 ");
		sql.append(" and  rn = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	/**
	 * 
	 * @描述:获取审核信息
	 * @作者：夏夏[工号：1104]
	 * @日期：2015-7-2 上午08:46:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getGzjlshInfo(GzjlshForm t) {
		StringBuilder sql = new StringBuilder();
		sql.append("select t1.*,t2.xm,t2.xb,t2.xymc,t3.lbmc gzlbmc,t4.lksmc,");
		sql.append(" decode(t1.shzt,  '0', '未审核', '1', '通过', '2', '不通过', '3', ");
		sql.append(" '退回', '4', '需重审', '5', '审核中', '', '无需审核', ");
		sql.append(" t1.shzt) shztmc from xg_gzjlgl_gzjlsqb t1 ");
		sql.append(" left join view_jsxx t2 on t1.zgh = t2.zgh left join xg_gzjlgl_gzlbb t3 on t1.lbdm=t3.lbdm left join xg_szdwx_lksdmb t4 on t1.lks=t4.lksdm");
		sql.append(" where t1.sqid = ? ");
		return dao.getMapNotOut(sql.toString(), new String[] { t.getSqid() });
	}

	@Override
	protected void setTableInfo() {
		super.setClass(GzjlshForm.class);
		super.setKey("sqid");
		super.setTableName("xg_gzjlgl_gzjlsqb");		
		}
}