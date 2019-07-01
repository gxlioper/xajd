/**
 * @部门:学工产品事业部
 * @日期：2016-2-18 下午03:21:15 
 */  
package com.zfsoft.xgxt.zxdk.xnwxjkhk;

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
 * @时间： 2016-2-18 下午03:21:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnwxjkhkDao extends SuperDAOImpl<XnwxjkhkForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxjkhkForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XnwxjkhkForm t, User user)
			throws Exception {
		// 生成高级查询相关条件、条件值
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.* from (");
		sql.append(" select t1.*,t2.xm,t2.xb,t2.yhkh,t2.zzmmmc,t2.mzmc,t3.yhmc,t2.sfzh,t2.xymc,t2.xydm,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.sjhm,t2.xmsj sjdh,t2.dzyx,t4.xqmc");
		sql.append(" from xg_zdgxh_wxjkhk_jgb t1 left join view_xsbfxx t2 on t1.xh = t2.xh");
		sql.append(" left join xqdzb t4 on t1.xq = t4.xqdm");
		sql.append(" left join dmk_yh t3 on t2.yhdm = t3.yhdm");
		sql.append(" ) t where 1 = 1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(XnwxjkhkForm.class);
		super.setKey("jgid");
		super.setTableName("xg_zdgxh_wxjkhk_jgb");
		
	}
	
	public boolean isHaveRecord(XnwxjkhkForm form){
		String sql = "select count(1) num from xg_zdgxh_wxjkhk_jgb where xh = ?";
		String num = dao.getOneRs(sql, new String[]{form.getXh()}, "num");
		return Integer.valueOf(num)>0;
	}
	
	public String getXqmc(String xqdm){
		String sql = "select xqmc from xqdzb where xqdm = ? ";
		return dao.getOneRs(sql, new String[]{xqdm}, "xqmc");
	}
	
}
