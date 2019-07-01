/**
 * @部门:学工产品事业部
 * @日期：2014-6-3 下午01:29:18 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwjyjgl.xlwjyjk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-6-3 下午01:29:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class XlwjyjkDao extends SuperDAOImpl<XlwjyjkForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlwjyjkForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(XlwjyjkForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.gzdj , a.rksj , a.bz , b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc, c.lxdm ,c.lxmc, " +
				"(select max(sj) from XG_XLJK_XLWJYJ_XLFDXXB d where d.xh = a.xh) zjfdsj " + 
				"from XG_XLJK_XLWJYJ_XLWJYJK a " +
				"left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWJYJ_XLWTLX c on a.lxdm = c.lxdm) t1 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}
	
	
	//PL submit
	public int batchSubmit(String[] xhs , String lxdm , String gzdj , String bz , String rksj) throws Exception{
	
		String sql = "insert into XG_XLJK_XLWJYJ_XLWJYJK (xh , lxdm , gzdj , rksj , bz) values (?,?,?,?,?)";
		
		List<String[]> param = new ArrayList<String[]>();
		
		for (int i = 0; i < xhs.length; i++) {
			String[] p = new String[]{xhs[i] , lxdm , gzdj , rksj , bz};
			param.add(p);
		}
		
		int[] r = dao.runBatch(sql, param);
		
		return r.length;
	}
	
	/**
	 * 
	 * @描述:获取预警库学生信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-6-5 下午02:16:52
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * HashMap<String,String> 返回类型 
	 * @throws
	 */
	public HashMap<String , String> xlwjyjkxsxx(String xh) throws Exception{
		
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.* from (select a.gzdj , a.rksj , a.bz , b.xh , b.xm , b.nj , b.xydm , b.xymc , b.zydm , b.zymc , b.xb , b.bjdm , b.bjmc, c.lxdm ,c.lxmc, " +
				"(select max(sj) from XG_XLJK_XLWJYJ_XLFDXXB d where d.xh = a.xh) zjfdsj " + 
				"from XG_XLJK_XLWJYJ_XLWJYJK a " +
				"left join view_xsjbxx b on a.xh = b.xh left join XG_XLJK_XLWJYJ_XLWTLX c on a.lxdm = c.lxdm) t1 where xh = ? ");
		
		return dao.getMapNotOut(sql.toString(), new String[]{xh});
		
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		setClass(XlwjyjkForm.class);
		setKey("xh");
		setTableName("XG_XLJK_XLWJYJ_XLWJYJK");
	}

}
