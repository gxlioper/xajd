/**
 * @部门:学工产品事业部
 * @日期：2016-1-18 下午05:20:46 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.twsj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-18 下午05:20:46 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class TwsjDao extends SuperDAOImpl<TwsjForm>{
	
	@Override
	public List<HashMap<String, String>> getPageList(TwsjForm t)
			throws Exception {
		return null;
	}
	
	//高级查询
	public List<HashMap<String, String>> getPageList(TwsjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t1.*,t2.xydm,t2.xymc,t2.xm,t2.xb,t2.sfzh,t2.nj,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,t2.zzmmmc,t2.mzmc ");
		sql.append(" from xg_rcswx_sjgl_twsj t1");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" ) a where 1 = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_rcswx_sjgl_twsj");
		this.setClass(TwsjForm.class);
	}
	
	
	/** 
	 * @描述:增加保存验证
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-20 下午03:31:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForSave(TwsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_twsj t where t.xh = ? and t.xn = ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn()}, "num");
	}
	
	/** 
	 * @描述:修改保存验证
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-20 下午03:31:47
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForUpdate(TwsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_twsj t where t.xh = ? and t.xn = ? and t.id <> ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getId()}, "num");
	}
	
	
}
