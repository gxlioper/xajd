/**
 * @部门:学工产品事业部
 * @日期：2017-8-4 上午10:18:51 
 */  
package com.zfsoft.xgxt.rcsw.kqgl.kqjl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2017-8-4 上午10:18:51 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class kqjlDao extends SuperDAOImpl<kqjlForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(kqjlForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(kqjlForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t",
				"xydm", "bjdm");
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append("select * from( ");
		sql.append("select a.*,b.lddm,b.ldmc,b.qsh,c.xm,c.xymc,c.xydm,c.zydm,c.zymc,c.bjdm,c.bjmc,c.nj,c.xb ");
		sql.append("from ( select case when substr(kqsj, 6, 2) >= 9 then substr(kqsj, 0, 4) || '-' || to_char(to_number(substr(kqsj, 0, 4)) + 1) else to_number(substr(kqsj, 0, 4)) - 1 || '-' || substr(kqsj, 0, 4) end xn,");
		sql.append("xh,dkzt,kqsj,kqlb,case when (substr(kqsj, 6, 2) >= 2 and substr(kqsj, 6, 2) < 9) then");
		sql.append(" '第二学期'when substr(kqsj, 6, 2) >= 9 and  substr(kqsj, 9, 2) > 1 then  '第一学期' else '第一学期' end xq");
		sql.append(" from xg_rcsw_kqglb) a  left join view_xg_gygl_new_cwxx b on a.xh = b.xh");
		sql.append(" left join view_xsbfxx c  on a.xh = c.xh");
		sql.append(" ) t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);	
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("xg_rcsw_kqglb");
		super.setKey("xh");
		super.setClass(kqjlForm.class);
		
	}

	/**
	 * @param kqsj  
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张昌路[工号：982]
	 * @日期：2017-8-7 下午05:00:15
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws 
	 */
	public Map<String, String> getKqjl(String xh, String kqsj) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from view_kqjljg where xh=? and kqsj=? ");
		return dao.getMapNotOut(sql.toString(), new String[]{xh,kqsj});
	}

}
