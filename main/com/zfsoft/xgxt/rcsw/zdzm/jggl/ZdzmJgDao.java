/**
 * @部门:学工产品事业部
 * @日期：2014-3-7 下午02:47:54 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.jggl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 日常事务管理模块
 * @类功能描述: 在读证明数据库操作层
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-7 下午02:47:54 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmJgDao extends SuperDAOImpl<ZdzmJgForm> {

	public int deleteZdzmJgBySqid(String sqid) throws Exception{
		String sql = "delete from XG_RCSW_ZDZM_ZDZMJGB where ZDZMSQID = ? ";
		return dao.runDelete(sql, new String[]{sqid});
	}
	
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmJgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.*,")
		.append("b.xm,")
		.append("b.xb,")
		.append("b.nj,")
		.append("b.xydm,")
		.append("b.xymc,")
		.append("b.zydm,")
		.append("b.zymc,")
		.append("b.bjdm,")
		.append("b.bjmc,")
		.append("b.pycc,")
		.append("b.mzmc,")
		.append("b.csrq,")
		.append("b.sfzh,")
		.append("b.syd,")
		.append("(select pyccmc from xg_xsxx_pyccdmb where pyccdm = b.pycc ) pyccmc ")
		.append(" from XG_RCSW_ZDZM_ZDZMJGB a")
		.append(" left join view_xsxxb b")
		.append(" on a.xh = b.xh) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_ZDZM_ZDZMJGB");
		setKey("ZDZMJGID");
		setClass(ZdzmJgForm.class);
	}

}
