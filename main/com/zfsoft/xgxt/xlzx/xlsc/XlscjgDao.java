/**
 * @部门:学工产品事业部
 * @日期：2016-12-21 上午11:52:21 
 */  
package com.zfsoft.xgxt.xlzx.xlsc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import xgxt.comm.search.SearchService;
import xgxt.form.User;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 心理咨询管理模块
 * @类功能描述: 心理筛查方法类
 * @作者： Meng.Wei[工号:1186]
 * @时间： 2016-12-21 上午11:51:34 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlscjgDao extends SuperDAOImpl<XlscjgForm>{
	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		super.setClass(XlscjgForm.class);
		super.setKey("id");
		super.setTableName("xg_xlzx_xlscjgb");
	}

	/*
		描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(XlscjgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	@Override
	public List<HashMap<String, String>> getPageList(XlscjgForm t, User user)
			throws Exception {
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		String searchTjByUser = SearchService.getSearchTjByUser(user, "t", "xydm", "bjdm");
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from ( ");
		sql.append(" select a.id,a.xh,a.scrq,a.scl,a.sds,a.sas,a.bkyy,a.bkjl, ");
		sql.append(" decode(a.sfxyyt,'0','否','1','是')sfxyyt, ");
		sql.append(" decode(a.sfyyt,'0','否','1','是')sfyyt,a.sfxyyt sfxyytdm,a.sfyyt sfyytdm, ");
		sql.append(" b.xm,b.xb,b.nj,b.xydm,b.xymc,b.zydm,b.zymc,b.bjdm,b.bjmc ");
		sql.append(" from xg_xlzx_xlscjgb a ");
		sql.append(" left join view_xsbfxx b on a.xh = b.xh ");
		sql.append(" )t where 1=1 ");
		sql.append(searchTjByUser);
		sql.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	/**
	 * @描述: 保存唯一性判断
	 * @作者： Meng.Wei[工号：1186]
	 * @日期： 2016-12-21 下午04:50:30
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param model
	 * @return
	 * String 返回类型 
	 * @throws
	 */
	public String checkForSave(XlscjgForm model){
		StringBuilder sql = new StringBuilder();
		List<String> params = new ArrayList<String>();
		String id =  model.getId();
		sql.append(" select count(1) num from xg_xlzx_xlscjgb ");
		sql.append(" where xh = ? and scrq = ? ");
		params.add(model.getXh());
		params.add(model.getScrq());
		if(!StringUtils.isEmpty(id)){
			sql.append(" and id <> ? ");
			params.add(id);
		}
		String num = dao.getOneRs(sql.toString(), params.toArray(new String[params.size()]), "num");
		return num;
	}
}
