/**
 * @部门:学工产品事业部
 * @日期：2014-10-10 下午02:52:58 
 */  
package com.zfsoft.xgxt.zxdk.xyddk;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 违约记录
 * @作者： 黄辰霁
 * @时间： 2015-11-26 上午9:41:41 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WyjlDao extends SuperDAOImpl<WyjlModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	public List<HashMap<String, String>> getWyztList(){
		StringBuilder sql = new StringBuilder();
		sql.append("select wyzt from XG_ZXDK_WYXX_WYZT_ZJDX");
		String[] input = {};
		return dao.getListNotOut(sql.toString(), input);
	}

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zxdk_wyxx_zjdx");
		super.setKey("xh");
		super.setClass(WyjlModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WyjlModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(WyjlModel t, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");	
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (");
		sql.append(" select t1.xh,t1.sjhm,t1.qqhm,t1.wxhm,t1.dzyx,t1.wyxq,t1.wyzt,t1.wysj,t1.bz, ");
		sql.append(" substr(t1.wysj,0,4)nd, substr(t1.wysj,6,2)yf, ");
		sql.append(" t2.xm,t2.xb,t2.mzmc,t2.sfzh,t2.xydm,t2.xymc,t2.zydm,t2.zymc,t2.bjdm,t2.bjmc,t2.nj,t2.xz,t2.zybj,t2.zybjmc,t2.sydm1 sydm,t2.symc1 symc, ");
		sql.append(" from xg_zxdk_wyxx_zjdx t1 ");
		sql.append(" left join view_xsbfxx t2 on t1.xh = t2.xh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	/** 54
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：黄辰霁
	 * @日期：2015-11-26 下午01:59:32
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param t
	 * @return
	 * String 返回类型 
	 * @throws 
	 */
	public String getCountByXh(WyjlModel t){
		
		StringBuilder sql = new StringBuilder("select count(1) c from XG_ZXDK_WYXX_ZJDX where xh = ?");
		String[] params = new String[]{t.getXh()};
		
		return dao.getOneRs(sql.toString(), params, "c");
	}
	
		/*
    	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	public List<HashMap<String, String>> getFkList(WyjlModel t)
			throws Exception {
		String[] inputV = new String[]{t.getXh()};

		StringBuilder sql = new StringBuilder();
	
		sql.append("select t1.htbh,t1.fkje,t1.fksj,t1.dkxn,t2.xh from xg_zxdk_fdxx t1 left join ");
		sql.append("xg_zxdk_xydkjgb t2 on t1.htbh = t2.htbh ");
		sql.append("where 1=1 and t2.xh=?");
	
		return dao.getListNotOut(sql.toString(), inputV);
	}
	
	/*
	描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
 */
	public List<HashMap<String, String>> getDkxxList(WyjlModel t)
			throws Exception {
		String[] inputV = new String[]{t.getXh()};

		StringBuilder sql = new StringBuilder();

		sql.append("select dkje,mnje,xfdks,zsfdks,shfdks from xg_zxdk_xydkjgb ");
		sql.append("where 1=1 and xh=?");

		return dao.getListNotOut(sql.toString(), inputV);
	}

}