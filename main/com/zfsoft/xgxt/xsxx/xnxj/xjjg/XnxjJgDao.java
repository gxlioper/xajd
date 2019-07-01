/**
 * @部门:学工产品事业部
 * @日期：2013-12-23 下午03:03:31 
 */  
package com.zfsoft.xgxt.xsxx.xnxj.xjjg;

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
 * @作者： 张小彬[工号:1036]
 * @时间： 2013-12-23 下午03:03:31 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XnxjJgDao extends SuperDAOImpl<XnxjJgForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjJgForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XnxjJgForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		//to_date('2009-12-25 14:23:31','yyyy-mm-dd,hh24:mi:ss')
		sql.append("select t1.* ")
		.append(" from (select a.id, a.xn, a.xjnr, to_char(to_date(a.txsj , 'yyyy-MM-dd,hh24:mi:ss'),'yyyy-MM-dd') txsj , a.sjly, a.sqid, b.* ")
		.append(" from xg_xsxx_xnxjjgb a ")
		.append(" left join view_xsjbxx b ")
		.append(" on a.xh = b.xh) t1 where xh is not null ");
		
		sql.append(searchTjByUser);
		sql.append(" ");
		sql.append(searchTj);
		
		return getPageList(t, sql.toString(), inputV);
	}

	
	public int delXnxjg(String ywid) throws Exception{
		String sql = "delete from xg_xsxx_xnxjjgb where sqid = ? ";
		return dao.runDelete(sql, new String[]{ywid});
	}
	
	public XnxjJgForm getModel(String xh , String xn) throws Exception{
		String sql = "select * from xg_xsxx_xnxjjgb where xh = ? and xn = ?";
		return super.getModel(sql, new String[]{xh , xn});
	}
	
	public List<HashMap<String , String>> getAllXnxjList(String xh) throws Exception{
		String sql = "select xn , xjnr from xg_xsxx_xnxjjgb where xh = ? order by xn desc";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	public List<HashMap<String , String>> getXnxjList(String xh , String xn) throws Exception{
		String sql = "select xn , xjnr from xg_xsxx_xnxjjgb where xh = ? and xn = ? order by xn desc";
		return dao.getListNotOut(sql, new String[]{xh , xn});
	}
	
	public HashMap<String , String> getXnxjJgInfo(String id) throws Exception{
		String sql = "select * from xg_xsxx_xnxjjgb where id = ?";
		return dao.getMapNotOut(sql, new String[]{id});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(XnxjJgForm.class);
		super.setKey("id");
		super.setTableName("xg_xsxx_xnxjjgb");
	}

}
