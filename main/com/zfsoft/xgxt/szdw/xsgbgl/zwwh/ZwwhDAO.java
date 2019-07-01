/**
 * @部门:学工产品事业部
 * @日期：2013-8-7 上午10:28:57 
 */  
package com.zfsoft.xgxt.szdw.xsgbgl.zwwh;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.utils.StringUtil;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 思政队伍管理模块
 * @类功能描述:学生干部职务DAO
 * @作者： zhangjw
 * @时间： 2013-8-7 上午10:28:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZwwhDAO extends SuperDAOImpl<ZwwhForm> {
	
	@Override
	protected void setTableInfo() {
		super.setKey("zwid");
		super.setTableName("xg_szdw_xsgb_zwb");
		super.setClass(ZwwhForm.class);
	}
	@Override
	public List<HashMap<String, String>> getPageList(ZwwhForm model)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(model.getSearchModel());
		String[] inputV = SearchService.getTjInput(model.getSearchModel());
		StringBuilder sql = new StringBuilder("");
		sql.append("  select * from (select a.*,b.lxmc from xg_szdw_xsgb_zwb a left join xg_szdw_xsgb_zwlxb b on a.lxdm = b.lxdm  where 1=1 order by zwzz) ");
		sql.append(searchTj);
		
		return getPageList(model, sql.toString(), inputV);
	}

	@Override
	public List<HashMap<String, String>> getPageList(ZwwhForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}
	/**
	 * @描述:获取职务列表
	 * @作者：zhangjw
	 * @日期：2013-8-8 下午3:30:06
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型
	 */
	public List<HashMap<String,String>> getZwList(String zwid){
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.zwid,t.zwmc from xg_szdw_xsgb_zwb t ");
		if(!StringUtil.isNull(zwid)){
			sql.append("  where t.lxdm = '"+zwid+"'"); 
		}
		return dao.getArrayList(sql.toString(), new String[]{}, new String[]{"zwid","zwmc"});
	}
	/**
	 * @描述:根据职务类型查询职务数量
	 * @作者：zhangjw
	 * @日期：2013-8-12 下午6:20:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param lxdm
	 * @return
	 * @throws SQLException
	 * int 返回类型
	 */
	public int getZwCount(String lxdm) throws SQLException{
		String sql = " select count(1) from xg_szdw_xsgb_zwb e where e.lxdm in( "+lxdm+")";
		return dao.getOneRsint(sql);
	}
}
