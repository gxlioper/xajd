/**
 * @部门:学工产品事业部
 * @日期：2018-5-16 下午04:33:11 
 */  
package com.zfsoft.xgxt.zxdk.xsdkqf;

import java.util.HashMap;
import java.util.List;

import xgxt.DAO.DAO;
import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.zxdk.xsdkqf.XsdkqfForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia[工号:1104]
 * @时间： 2018-5-16 下午04:33:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XsdkqfDao extends SuperDAOImpl<XsdkqfForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsdkqfForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XsdkqfForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select b.xh,b.xm,b.bjdm,b.bjmc,b.zydm,b.zymc,b.xydm,b.xymc,a.dkxm,a.sfjq from XG_ZXDK_DRXX a left join view_xsxxb b on a.sfzh = b.sfzh ");
		sql.append("  )t where 1 = 1");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		
	}
	public boolean Fsznx() throws Exception {
		DAO dao = DAO.getInstance();
		return dao.runProcuder("{call pro_xg_wjcf_cfdqts_10346()}", new String[] {});
	}

}
