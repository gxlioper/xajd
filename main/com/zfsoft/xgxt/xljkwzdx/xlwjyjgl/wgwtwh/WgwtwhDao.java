/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.wgwtwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 下午01:29:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WgwtwhDao extends SuperDAOImpl<WgwtwhForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WgwtwhForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WgwtwhForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t.* from (");
		sql.append("select x.* , to_number(x.zf) zf_num , decode(x.yjkcount , '0' , '否' , '是') yjkcx, decode(x.yjkcount , '0' , 'n' , 'y') yjkdm from  (select a.*,  b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc ,  " +
				"(select count(1) from XG_XLJK_XLWJYJ_XLWJYJK aa where aa.xh = a.xh) yjkcount from  XG_XLJK_XLWJYJ_WGWT a left join view_xsjbxx b on a.xh = b.xh ) x ")
		.append(" ) t where 1 = 1 ") 
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	/**
	 * @throws SQLException 
	 * 
	 * @描述:检查数据库是否存在
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-26 上午10:54:21
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @param lx
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public int checkExist(String xh) throws SQLException{
		String sql = "select count(1) rs from XG_XLJK_XLWJYJ_WGWT a where a.xh = ?";
		return Integer.valueOf(dao.getOneRs(sql, new String[]{xh}, "rs"));
	}
	
	@Override
	protected void setTableInfo() {
		setClass(WgwtwhForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWJYJ_WGWT");
	}

}
