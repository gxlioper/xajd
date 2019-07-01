/**
 * @部门:学工产品事业部
 * @日期：2014-10-29 上午09:43:18 
 */  
package com.zfsoft.xgxt.xsxx.zbgl.zbjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 技术等级鉴定情况
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2014-10-29 上午09:43:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZbjgDao extends SuperDAOImpl<ZbjgModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(ZbjgModel.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_zbgl_jgb");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbjgModel t)
			throws Exception {
		
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(ZbjgModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t2.xm,t2.xydm,t2.xymc,t2.zydm,");
		sql.append("t2.zymc,t2.bjdm,t2.bjmc,t2.nj ");
		sql.append("from xg_xsxx_zbgl_jgb t1 left join view_xsjbxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	/**
	 * 
	 * @描述: 导出数据列表
	 * @作者：屈朋辉[工号：445]
	 * @日期：2015-3-20 下午04:23:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @param user
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getExportList(ZbjgModel t, User user) throws Exception{
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");		
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.*,t1.ylzd1||'至'||t1.ylzd2 jdqzrq,t2.nj,t2.xm,t2.cym,t2.xb,t2.csrq,t2.zzmmmc,t2.mzmc,");
		sql.append("t2.zd5,t2.sjhm,t2.sfzh,t2.zjmc,t2.xlmc,t2.rxrq||'-'||t2.bysj jdsj,");
		sql.append("t2.xxxs,t2.sfzx,t2.zymc,t2.zydm,t2.xymc,t2.xydm,t2.bjdm,");
		sql.append("t2.bjmc,t2.xxszd,t2.xz,t2.jtdz,t2.jtyb,t2.hkxz,t2.lxdh,");
		sql.append("t2.hkszdmc,t2.sydmc,t2.jgmc,t3.xxmc,t4.pyccmc ");
		sql.append("from xg_xsxx_zbgl_jgb t1 ");
		sql.append("left join view_xsxxb t2 on t1.xh = t2.xh ");
		sql.append("left join xtszb t3 on 1=1 ");
		sql.append("left join xg_xsxx_pyccdmb t4 on t2.pycc = t4.pyccdm ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		Pages pages = t.getPages();
		pages.setPageSize(Integer.MAX_VALUE);
		t.setPages(pages);
		
		return getPageList(t, sql.toString(), inputV);
	}
	
}
