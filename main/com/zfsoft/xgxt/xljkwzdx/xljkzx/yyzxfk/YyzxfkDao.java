/**
 * @部门:学工产品事业部
 * @日期：2014-4-29 下午03:35:04 
 */  
package com.zfsoft.xgxt.xljkwzdx.xljkzx.yyzxfk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询（温大）-心理健康咨询 -预约咨询反馈
 * @类功能描述: 
 * @作者： 王志刚[工号:1060]
 * @时间： 2014-4-29 下午03:35:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class YyzxfkDao extends SuperDAOImpl<YyzxfkForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setClass(YyzxfkForm.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YyzxfkForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t.sqid,t.xh,t1.xm,t1.xb,t.yyzt,t2.zzaprq,t2.zxs,t2.zxzt,t3.xm zxsmc ")
		   .append("from XG_XLZX_YYSQB_WZDX t ")
		   .append("left join VIEW_XSBFXX t1 ")
		   .append("on t.xh = t1.xh ")
		   .append("left join XG_XLZX_XLZXB_WZDX t2 ")
		   .append("on t.sqid = t2.sqid ")
		   .append("left join VIEW_FDYXX t3 ")
		   .append("on t2.zxs = t3.zgh ")
		   .append("where 1=1 ")
		   .append(searchTj);
		   
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(YyzxfkForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

}
