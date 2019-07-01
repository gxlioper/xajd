/**
 * @部门:学工产品事业部
 * @日期：2015-7-21 上午11:51:39 
 */  
package com.zfsoft.xgxt.xsztz.xwtzxmjg;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsztz.tzxmjg.XsXmJgForm;
import com.zfsoft.xgxt.xsztz.tzxmsq.XsXmSqForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2015-7-21 上午11:51:39 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XwTzXmJgDao extends SuperDAOImpl<XwTzXmJgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XwTzXmJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XwTzXmJgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append(" select t.*,t1.xmjbmc,t2.sskmmc,t3.xm,t3.bjdm,t3.bjmc,t3.zydm,t3.zymc,t3.xb,");
		sql.append(" t3.nj,t3.xydm,t3.xymc,t4.xqmc ");
		sql.append(" from XG_SZTZ_XS_XWSQJG t ");
		sql.append(" left join xg_sztz_xmjb t1 ");
		sql.append(" on t.xmjbdm = t1.xmjbdm ");
		sql.append(" left join xg_sztz_sskm t2 ");
		sql.append(" on t.sskmdm = t2.sskmdm ");
		sql.append(" left join view_xsjbxx t3 ");
		sql.append(" on t.xh = t3.xh ");
		sql.append(" left join xqdzb t4 ");
		sql.append("  on t.xq = t4.xqdm ");
		sql.append(" )t where 1= 1  ");
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
		super.setClass(XwTzXmJgForm.class);
		super.setKey("sqid");
		super.setTableName("XG_SZTZ_XS_XWSQJG");
		
	}
	
	//重复判断
	public boolean checkExistForSave(XwTzXmJgForm model) {
		boolean flag = false;
		StringBuilder sql = new StringBuilder();
		sql.append("  select count(sqid) sqid from XG_SZTZ_XS_XWSQJG t where t.xh = ? and t.xn = ? and t.xmmc = ? and t.xq = ? ");
		String num = dao.getOneRs(sql.toString(), new String[] {model.getXh(),model.getXn(),model.getXmmc(),model.getXq()}, "sqid");
		if (!num.equals("0")){
			flag = true;
		}
		return flag;
	}
	
	public HashMap<String, String> getHdMap(XwTzXmJgForm model){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,t1.xmjbmc,t2.sskmmc,t3.xqmc from  ");
		sql.append(" XG_SZTZ_XS_XWSQJG t join xg_sztz_xmjb t1 ");
		sql.append(" on t.xmjbdm = t1.xmjbdm ");
		sql.append(" join xg_sztz_sskm t2 ");
		sql.append(" on t.sskmdm = t2.sskmdm ");
		sql.append(" join xqdzb t3 ");
		sql.append(" on t.xq = t3.xqdm ");
		sql.append(" where t.sqid= ? ");
		return dao.getMapNotOut(sql.toString(), new String[]{model.getSqid()});
	}
	
	public List<HashMap<String, String>> getSsKmList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ' ' sskmdm,' ' sskmmc from dual union ");
		sql.append(" select t.sskmdm,t.sskmmc from  xg_sztz_sskm t ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	public List<HashMap<String, String>> getXmJbList(){
		StringBuffer sql = new StringBuffer();
		sql.append(" select ' ' xmjbdm,' ' xmjbmc from dual union ");
		sql.append(" select t.xmjbdm,t.xmjbmc from  xg_sztz_xmjb t ");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
}
