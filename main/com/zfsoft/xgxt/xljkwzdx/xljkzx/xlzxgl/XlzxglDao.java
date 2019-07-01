/**
 * @部门:学工产品事业部
 * @日期：2014-5-7 下午01:56:26 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.xlzxgl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.Pages;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -心理咨询管理
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-5-7 下午01:56:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlzxglDao  extends SuperDAOImpl<XlzxglForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(XlzxglForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxglForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XlzxglForm t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		
		sql.append("select pkid,sqid,zxid,xh,xm,nj,xymc,xydm,zymc,zydm,bjmc,bjdm,xb,sjhm,yyzxsj,yyzxzt,yyzt,zxsxm,zzaprq,zxsd,zxslxdh,zxdz,zxzt,xspjzt ")
		   .append("from (select t.sqid || t1.zxid pkid,t.sqid,t1.zxid,t.xh,t2.xm,t2.nj,t2.xymc,t2.xydm,t2.zymc,t2.zydm,t2.bjmc,t2.bjdm,t2.xb,t2.sjhm,t.yyzxsj,")
		   .append("t.yyzxzt,t.yyzt,t3.xm zxsxm,t1.zzaprq,")
		   .append("(case when t1.zxsdkssj is null then '' else t1.zxsdkssj || '至' end) || t1.zxsdjssj zxsd,")
		   .append("t1.zxslxdh,t1.zxdz,t1.zxzt,")
		   .append("(case when t1.xszxpj is null then '未评价' else '已评价' end) xspjzt ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join XG_XLZX_XLZXB_WZDX t1 on t.sqid = t1.sqid ")
		   .append("left join VIEW_XSBFXX t2 on t.xh = t2.xh ")
		   .append("left join VIEW_FDYXX t3 on t1.zxs = t3.zgh ")
		   .append("union ")
		   .append("select a1.sqid || a.zxid pkid,a1.sqid,a.zxid,a.xh,a2.xm,a2.nj,a2.xymc,a2.xydm,a2.zymc,a2.zydm,a2.bjmc,a2.bjdm,a2.xb,a2.sjhm,a1.yyzxsj,")
		   .append("a1.yyzxzt,a1.yyzt,a3.xm zxsxm,a.zzaprq,")
		   .append("(case when a.zxsdkssj is null then '' else a.zxsdkssj || '至' end) || a.zxsdjssj zxsd,")
		   .append("a.zxslxdh,a.zxdz,a.zxzt,")
		   .append("(case when a.xszxpj is null then '未评价' else '已评价' end) xspjzt ")
		   .append("from XG_XLZX_XLZXB_WZDX a ")
		   .append("left join XG_XLZX_YYSQB_WZDX a1 ")
		   .append("on a.sqid = a1.sqid ")
		   .append("left join VIEW_XSBFXX a2 ")
		   .append("on a.xh = a2.xh ")
		   .append("left join VIEW_FDYXX a3 ")
		   .append("on a.zxs = a3.zgh) t ")
		   .append("where 1=1 ")
		   .append(searchTjByUser)
		   .append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * 
	 * @描述:查询导出数据
	 * @作者：王志刚
	 * @日期：2014-5-7 下午05:33:02
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 */
	public List<HashMap<String, String>> getAllXlzxglList(XlzxglForm model, User user)
			throws Exception {
		Pages pages = (Pages) model.getClass().getMethod("getPages").invoke(model);
		pages.setPageSize(Integer.MAX_VALUE);
		
		model.getClass().getMethod("setPages",Pages.class).invoke(model, pages);
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from view_xlzx_xlzxgl t ");
		sql.append("where 1=1 ")
		   .append(searchTjByUser)
		   .append(searchTj);
		return getPageList(model, sql.toString(), inputV);
	}

}
