/**
 * @部门:学工产品事业部
 * @日期：2016-1-21 下午02:44:27 
 */  
package com.zfsoft.xgxt.rcsw.sjgl.hqsj;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.rcsw.hcyhkgl.hcccqjtx.HcccqjtxForm;
import com.zfsoft.xgxt.rcsw.sjgl.kqsj.KqsjForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 柳俊[工号:1282]
 * @时间： 2016-1-21 下午02:44:27 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class HqsjDao extends SuperDAOImpl<HqsjForm> {
	
	@Override
	public List<HashMap<String, String>> getPageList(HqsjForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	
	//高级查询
	@Override
	public List<HashMap<String, String>> getPageList(HqsjForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t1.*,t2.xydm,t2.xymc,t2.xm,t2.xb,t2.sfzh,t2.nj,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,t2.zzmmmc,t2.mzmc,t3.xqmc ");
		sql.append(" from xg_rcswx_sjgl_hqsj t1");
		sql.append(" left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append(" left join xqdzb t3 on t1.xq = t3.xqdm ");
		sql.append(" ) a where 1 = 1 ");	
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	@Override
	protected void setTableInfo() {
		this.setKey("id");
		this.setTableName("xg_rcswx_sjgl_hqsj");
		this.setClass(HqsjForm.class);
	}
	
	
	/** 
	 * @描述:新增保存的时候判断该学年学期是否存在数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-21 下午03:53:23
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForSave(HqsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_hqsj t where t.xh = ? and t.xn = ? and t.xq = ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getXq()}, "num");
	}
	
	
	
	/** 
	 * @描述:修改保存的时候判断该学年学期是否存在数据
	 * @作者：柳俊[工号：1282]
	 * @日期：2016-1-21 下午03:55:13
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param form
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String checkExistForUpdate(HqsjForm form) {
		String sql = "select count(1) num from xg_rcswx_sjgl_hqsj t where t.xh = ? and t.xn = ? and t.xq = ? and t.id <> ?";
		return dao.getOneRs(sql, new String[]{form.getXh(),form.getXn(),form.getXq(),form.getId()}, "num");
	}
	
	public HqsjForm getModel(HqsjForm t) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select a.*,c.xqmc from xg_rcswx_sjgl_hqsj a ");
		sql.append(" left join xqdzb c on a.xq=c.xqdm");
		sql.append(" where a.id=? ");
		return super.getModel(t, sql.toString(), new String[]{ t.getId() });
	}
	
}
