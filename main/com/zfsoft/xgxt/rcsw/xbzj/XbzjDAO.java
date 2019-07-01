/**
 * @部门:学工产品事业部
 * @日期：2013-8-5 下午05:42:19 
 */  
package com.zfsoft.xgxt.rcsw.xbzj;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: (学生系部支教管理--潍坊学院) 
 * @作者： cmj [工号：913]
 * @时间： 2013-8-5 下午05:42:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XbzjDAO extends SuperDAOImpl<XbzjForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XbzjForm t)
			throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(XbzjForm model, User user)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "a", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		
		/*StringBuilder sql = new StringBuilder("select * from (select a.id,a.xh,a.xn,a.xq,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc,a.zjsj,a.bz,");
		sql.append("b.xm,b.xb,b.nj,b.xymc,b.zymc,b.bjmc,b.xydm,b.zydm,b.bjdm ");
		sql.append("from rcsw_xbzj a left join view_xsjbxx b on a.xh=b.xh) where 1=1 ");*/
		StringBuilder sql = new StringBuilder();
		sql.append("select * from VIEW_NEW_DC_RCSW_XBZJ a where 1=1 ");
		
		sql.append(searchTjByUser);
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}
	
	public XbzjForm getModel(XbzjForm model) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("select a.*,(select xqmc from xqdzb t where a.xq=t.xqdm)xqmc from rcsw_xbzj a where id=?");
		XbzjForm myForm=new XbzjForm();
		HashMap<String, String> map=dao.getMapNotOut(sql.toString(), new String[]{model.getId()});
		BeanUtils.copyProperties(myForm, map);
		return myForm;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setTableName("rcsw_xbzj");
		super.setKey("Id");
		super.setClass(XbzjForm.class);
		
	}

	/** 
	 * @描述:(判断该学年学期该学生是否已是西部支教学生)
	 * @作者：cmj [工号：913]
	 * @日期：2013-8-5 下午06:49:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * boolean 返回类型 
	 * @throws 
	 */
	public boolean isExist(XbzjForm model) {
		StringBuilder sql = new StringBuilder();
		sql.append("select *　from rcsw_xbzj where xh=? and xn=? and xq=?");
		List<HashMap<String, String>> list=dao.getArrayList(sql.toString(), new String[]{model.getXh(),model.getXn(),model.getXq()}, new String[]{"xh"});
		return list.size()!=0;
	}

}
