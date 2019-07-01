/**
 * @部门:学工产品事业部
 * @日期：2014-8-21 下午04:25:14 
 */  
package com.zfsoft.xgxt.jjgl.cssz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: 家教模块-基础设置-参数设置 DAO
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-21 下午04:25:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class CsszDao extends SuperDAOImpl<CsszForm> {

	/**
	 * 
	 * @描述: 获取参数配置信息
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-21 下午05:06:46
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Map<String,String> 返回类型 
	 * @throws
	 */
	public Map<String , String> getConfigMap(){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from XSGGFW_JJFW_XTSZ a  where rownum = 1");
		return dao.getMapNotOut(sql.toString(), new String[]{});
	}
	
	
	
	/*
	    描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#runDelete(java.lang.String[])
	*/
	
	@Override
	public int runDelete(String[] values) throws Exception {
		String sql = "delete from XSGGFW_JJFW_XTSZ";
		return dao.runDelete(sql, new String[]{});
	}


	/**
	 * 获取发布资格列表
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-22 下午01:50:45
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * List<HashMap<String,String>> 返回类型 
	 * @throws
	 */
	public List<HashMap<String , String>> getFbzgList(){
		StringBuffer sql = new StringBuffer();
		sql.append("select a.* from XSGGFW_JJFW_FBZGDYB a");
		return dao.getListNotOut(sql.toString(), new String[]{});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(CsszForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */

	@Override
	protected void setTableInfo() {
		super.setClass(CsszForm.class);
		super.setTableName("XSGGFW_JJFW_XTSZ");
	}

}
