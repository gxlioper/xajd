/**
 * @部门:学工产品事业部
 * @日期：2016-5-6 上午09:45:01 
 */  
package com.zfsoft.xgxt.zxdk.byhkgl.cssz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 毕业还款管理
 * @类功能描述: 参数设置 
 * @作者： 沈晓波[工号:1123]
 * @时间： 2016-5-6 上午09:45:01 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<ByhkglCsszForm>{

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglCsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ByhkglCsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		// TODO 自动生成方法存根
		super.setClass(ByhkglCsszForm.class);
		super.setKey("id");
		super.setTableName("XG_ZXDK_HZSF_BYHKCSSZ");		
	}
	
	public ByhkglCsszForm getModel() throws Exception{
		String sql = "select * from XG_ZXDK_HZSF_BYHKCSSZ where rownum=1";
		return super.getModel(sql, new String[]{});
	}
	
	public boolean deleteJcsz() throws Exception{
		String sql = "delete from XG_ZXDK_HZSF_BYHKCSSZ";
		return dao.runUpdate(sql, new String[]{});
	}
	
	/**
	 * 
	 * @描述:获取申请审核开关
	 * @作者：沈晓波[工号:1123]
	 * @日期：2016-5-6 上午10:06:59
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * @throws Exception
	 * String[] 返回类型 
	 * @throws
	 */
	public String[] getSqShKg() throws Exception{
		StringBuffer sql = new StringBuffer();
		sql.append("select case when t.sqkg = 1 and sysdate between to_date(nvl(t.sqkssj,'1990-01-01 00:00'),'yyyy-mm-dd hh24:mi') ");
		sql.append(" and to_date(nvl(t.sqjssj,'9999-01-01 00:00'),'yyyy-mm-dd hh24:mi') + 1 then '1' else '0' end sqkg");
		sql.append(" from XG_ZXDK_HZSF_BYHKCSSZ t where 1=1");
		return dao.getOneRs(sql.toString(),new String[]{},new String[]{"sqkg"});
	}

}
