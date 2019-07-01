/**
 * @部门:学工产品事业部
 * @日期：2015-2-11 上午09:12:18 
 */  
package com.zfsoft.xgxt.zdxljk.ecmm;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @类功能描述: 浙大心理健康--特别关心学生 
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-2-11 上午09:11:04 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class EcmmDao extends SuperDAOImpl<EcmmModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setTableName("xg_zdxljk_ecmmb");
		super.setKey("zgh");
		super.setClass(EcmmModel.class);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(EcmmModel t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select * from (");
		sql.append("select t1.zgh,t1.cjsj,t2.xm,t2.bmmc,t2.bmdm,t2.xb from xg_zdxljk_ecmmb t1 ");
		sql.append("left join view_fdyxx t2 on t1.zgh = t2.zgh ");
		sql.append(") t where 1=1 ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(EcmmModel t, User user)
			throws Exception {
		return null;
	}
	
	
	public EcmmModel getModelByLogin(EcmmModel t) throws Exception{
		
		String sql = "select * from xg_zdxljk_ecmmb where zgh=? and ecmm=?";
		return super.getModel(sql, new String[]{t.getZgh(),t.getEcmm()});
	}
	
	
	public boolean initEcmm(String[] zgh,String ecmm) throws Exception{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("update xg_zdxljk_ecmmb set ecmm=? where ");
		sql.append("(");
		
		for (int i = 0 , n = zgh.length ; i < n ; i++){
			sql.append("zgh=?");
			
			if (i != n-1){
				sql.append(" or ");
			}
		}
		sql.append(")");
		
		return dao.runUpdate(sql.toString(), StringUtils.joinStrArr(new String[]{ecmm},zgh));
	}

}
