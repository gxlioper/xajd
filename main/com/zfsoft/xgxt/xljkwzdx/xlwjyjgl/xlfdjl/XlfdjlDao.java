/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlfdjl;

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

public class XlfdjlDao extends SuperDAOImpl<XlfdjlForm> {
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlfdjlForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlfdjlForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.zgh, a.fdid , a.sj,a.lx,a.fdlxdm,a.thnrfdcs," +
				"       (select aa.fdlxmc from XG_XLJK_XLWJYJ_FDLX aa where aa.fdlxdm = a.fdlxdm) fdlxmc," + 
				"       decode(a.lx , '0' , '谈话' , '1' , '辅导' , '') xlfdlxmc," + 
				"       b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc," +
				"       c.gzdj , d.lxdm , d.lxmc  , e.xm fdyxm " +
				"  from XG_XLJK_XLWJYJ_XLFDXXB a" +
				"  left join view_xsjbxx b" +
				"    on a.xh = b.xh" +
				"  left join XG_XLJK_XLWJYJ_XLWJYJK c" +
				"    on a.xh = c.xh " +
				"  left join XG_XLJK_XLWJYJ_XLWTLX d " +
				"    on c.lxdm = d.lxdm   left join view_fdyxx e on a.zgh = e.zgh " +
				") t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:根据学号查询学生心理信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午02:12:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xlfxxsxx(String fdid) throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.zgh, a.fdid , a.sj,a.lx,a.fdlxdm,a.thnrfdcs," +
				"       (select aa.fdlxmc from XG_XLJK_XLWJYJ_FDLX aa where aa.fdlxdm = a.fdlxdm) fdlxmc," + 
				"       decode(a.lx , '0' , '谈话' , '1' , '辅导' , '') xlfdlxmc," + 
				"       b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc," +
				"       c.gzdj , d.lxdm , d.lxmc   , e.xm fdyxm " +
				"  from XG_XLJK_XLWJYJ_XLFDXXB a" +
				"  left join view_xsjbxx b" +
				"    on a.xh = b.xh" +
				"  left join XG_XLJK_XLWJYJ_XLWJYJK c" +
				"    on a.xh = c.xh " +
				"  left join XG_XLJK_XLWJYJ_XLWTLX d " +
				"    on c.lxdm = d.lxdm    left join view_fdyxx e on a.zgh = e.zgh " +
				") t1 where fdid = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{fdid});
	}
	
	
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XlfdjlForm.class);
		setKey("fdid");
		setTableName("XG_XLJK_XLWJYJ_XLFDXXB");
	}

}
