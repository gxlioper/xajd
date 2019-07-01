/**
 * @部门:学工产品事业部
 * @日期：2014-2-10 下午05:25:11 
 */  
package com.zfsoft.xgxt.dagl.qdcl;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 档案管理模块
 * @类功能描述: (这里用一句话描述这个类的作用) 
 * @作者：  wanghj [工号：1004]
 * @时间： 2014-2-10 下午05:25:11 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class DaqdclDao extends SuperDAOImpl<DaqdclForm> {

	protected void setTableInfo() {
		super.setTableName("xg_xsxx_dagl_daqdclb");
		super.setKey("daqdcl_id");
		super.setClass(DaqdclForm.class);
	}

	public List<HashMap<String, String>> getPageList(DaqdclForm t)
			throws Exception {
		
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		
		StringBuilder sql = new StringBuilder();
		
		sql.append(" select * from (");
		sql.append(" select t.daqdcl_id,t.daqdcl_mc,");
		sql.append("(select count(*) from XG_XSXX_DAGL_DAMBCLBDB where daqdcl_id=t.daqdcl_id) ybdmbs," );
		sql.append("(select count(*) from XG_XSXX_DAGL_XSDACLBDB where daqdcl_id=t.daqdcl_id) ybdxss " );
		sql.append(" from XG_XSXX_DAGL_DAQDCLB t) where 1=1 ");
		sql.append(searchTj);
		
		return super.getPageList(t, sql.toString(), inputV);
	}

	public List<HashMap<String, String>> getPageList(DaqdclForm t, User user) throws Exception {
		return null;
	}
	
	/**
	 * 
	 * @描述:判断是否已有档案材料名称存在
	 * @日期：2014-4-23 下午03:24:00
	 * @param myForm
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String, String> getDaqdclByName(DaqdclForm myForm) throws Exception {
		StringBuilder sql = new StringBuilder();
		HashMap<String, String> lst = new HashMap<String, String>();
		String[] inputV =  new String[1];
		sql.append(" select * from XG_XSXX_DAGL_DAQDCLB t where t.daqdcl_mc=? ");
		
		if(myForm.getDaqdcl_id() !=null && !"".equals(myForm.getDaqdcl_id())){
			sql.append(" and t.daqdcl_id != ?");
			lst = dao.getMapNotOut(sql.toString(), new String[]{myForm.getDaqdcl_mc(), myForm.getDaqdcl_id()});
		}else{
			lst = dao.getMapNotOut(sql.toString(), new String[]{myForm.getDaqdcl_mc()});
		}

		return lst;
	}

	/**
	 * 
	 * @描述:材料列表
	 * @日期：2014-4-24 上午09:51:32
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String, String>> getDaqdclAllList() throws Exception {
		String sql ="select daqdcl_id clid,daqdcl_mc clmc from XG_XSXX_DAGL_DAQDCLB ";
		return dao.getListNotOut(sql, new String[]{});
	}
	
	public List<HashMap<String, String>> getMbwclList() throws Exception {
		
		String sql ="select * from XG_XSXX_DAGL_DAQDCLB ";
		return dao.getListNotOut(sql, new String[]{});
	}
}
	