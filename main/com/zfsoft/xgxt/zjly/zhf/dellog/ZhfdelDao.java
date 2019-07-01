/**
 * @部门:学工产品事业部
 * @日期：2017-3-21 上午09:29:53 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CP[工号:1352]
 * @时间： 2017-3-21 上午09:29:53 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfdelDao extends SuperDAOImpl<ZhfdelForm>{
	@Override
	public List<HashMap<String, String>> getPageList(ZhfdelForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(ZhfdelForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select logid,scrzgh,scrxm,scsj,xh,(select xm from view_xsxxb where a.xh=xh)xm,");
		sql.append(" (select jfxmmc from xg_zjly_zhszf_jfxmb b where a.jfxmdm = b.jfxmdm) jfxmmc,");
		sql.append(" (select xmmkmc from xg_zjly_zhszf_mkxmb c where a.xmmkdm = c.xmmkdm) xmmkmc,");
		sql.append(" sxsm,decode(shzt,'1','已审核','0','未审定','2','退回') shzt,cysj,");
		sql.append(" case when length(lrr)>10 then (select xm from view_xsxxb where a.lrr = xh )");
		sql.append(" else (select xm from view_fdyxx where a.lrr = zgh ) end lrrxm,lrr,lrsj");
		sql.append(" from xg_zjly_zhfsclog a ");
		sql.append(" where 1= 1  ");
		sql.append(searchTj);
		sql.append(" order by scsj desc");
		return getPageList(t, sql.toString(),inputV);
	}
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}

}
