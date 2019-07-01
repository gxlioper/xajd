/**
 * @部门:学工产品事业部
 * @日期：2015-6-23 上午08:52:00 
 */  
package com.zfsoft.xgxt.xsxx.dyxj.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;
import xgxt.utils.String.StringUtils;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.xsxx.dyxj.zgk.zgkForm;

/** 
 * @类功能描述: 德育自评参数设置
 * @作者： 屈朋辉 [工号:445]
 * @时间： 2015-6-23 上午08:52:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszModel> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CsszModel.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_dyxj_cssz");
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	
	public CsszModel getModel() throws Exception{
		String sql = "select * from xg_xsxx_dyxj_cssz where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	@Override
	public List<HashMap<String, String>> getPageList(CsszModel t, User user)
	throws Exception {
// TODO 自动生成方法存根
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*");
		sql.append(" from (select t.id,");
		sql.append(" t.xh,");
		sql.append(" t.xn,");
		sql.append(" t.xq,");
		sql.append(" t1.xm,");
		sql.append(" t1.xb,");
		sql.append(" t1.NJ,");
		sql.append(" t1.XYDM,");
		sql.append(" t1.XYMC,");
		sql.append(" t1.ZYDM,");
		sql.append(" t1.ZYMC,");
		sql.append(" t1.BJDM,");
		sql.append(" t1.BJMC");
		sql.append(" from xg_xsxx_dyxj_zgk t");
		sql.append(" left join view_xsxxb t1");
		sql.append(" on t.xh = t1.XH) t");
		String xn = t.getSqxn();
		String xq = t.getSqxq();
		if(StringUtils.isNotNull(xn) && StringUtils.isNotNull(xq) ){
			sql.append(" where xn = '"+xn+"'");
			sql.append(" and xq = '"+xq+"' ");
		}else if(StringUtils.isNotNull(xn) && StringUtils.isNull(xq)){
			sql.append(" where  xn = '"+xn+"' ");
		}else if(StringUtils.isNull(xn) && StringUtils.isNotNull(xq)){
			sql.append(" where  xq = '"+xq+"'");
		}else{
			sql.append(" where 1 = 1");
		}

		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
    }

}
