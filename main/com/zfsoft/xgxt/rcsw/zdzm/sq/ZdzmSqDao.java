/**
 * @部门:学工产品事业部
 * @日期：2014-3-4 下午04:20:00 
 */  
package com.zfsoft.xgxt.rcsw.zdzm.sq;

import java.util.HashMap;
import java.util.List;

import xgxt.comm.search.SearchService;
import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-4 下午04:20:00 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZdzmSqDao extends SuperDAOImpl<ZdzmSqForm> {
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ZdzmSqForm t)
			throws Exception {
		
		return null;
	}

	/**
	 * 分页查询
	 */
	@Override public List<HashMap<String, String>> getPageList(ZdzmSqForm t, User user)
			throws Exception {
		//生成高级查询相关条件、条件值 
		String searchTj = SearchService.getSearchTj(t.getSearchModel());
 		String searchTjByUser = SearchService.getSearchTjByUser(user, "t1", "xydm", "bjdm");		
		String[] inputV = SearchService.getTjInput(t.getSearchModel());
		StringBuffer sql = new StringBuffer();
		sql.append("select t1.*,t2.splcid splcidnew from (select a.*,")
		.append("b.xm,")
		.append("b.xb,")
		.append("b.nj,")
		.append("b.xydm,")
		.append("b.xymc,")
		.append("b.zydm,")
		.append("b.zymc,")
		.append("b.bjdm,")
		.append("b.bjmc,")
		.append("b.pycc,")
		.append("b.mzmc,")
		.append("b.sfzh,")
		.append("b.syd,")
		.append("b.csrq,")
		.append("(select pyccmc from xg_xsxx_pyccdmb where pyccdm = b.pycc ) pyccmc,")
		.append(" decode(a.shzt ,'0' ,'未提交' , '1' , '通过' , '2' , '不通过' , '3' , '退回' , '5' , '审核中') shztmc")
		.append(" from XG_RCSW_ZDZM_ZDZMSQB a")
		.append(" left join view_xsxxb b")
		.append(" on a.xh = b.xh) t1 , XG_RCSW_ZDZM_JBSZ t2 where 1=1 ")
		.append(searchTjByUser)
		.append(" ")
		.append(searchTj);
		return getPageList(t, sql.toString(), inputV);
	}

	/**
	 * 
	 * @描述:根据学号获取在读证明信息
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午10:23:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmListByXh(String xh) throws Exception{
		String sql = "select a.* from XG_RCSW_ZDZM_ZDZMSQB a where a.xh = ? ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/**
	 * 
	 * @描述:根据学号获取正在审核中的数据 
	 * @作者：张小彬[工号:1036]
	 * @日期：2014-3-31 上午10:24:09
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xh
	 * @return
	 * @throws Exception
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getZdzmInShlcByXh(String xh) throws Exception{
		String sql = "select a.* from XG_RCSW_ZDZM_ZDZMSQB a where a.xh = ? and shzt = '5' ";
		return dao.getListNotOut(sql, new String[]{xh});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	protected void setTableInfo() {
		setTableName("XG_RCSW_ZDZM_ZDZMSQB");
		setKey("ZDZMSQID");
		setClass(ZdzmSqForm.class);
	}

}
