/**
 * @部门:学工产品事业部
 * @日期：2014-8-25 下午03:15:18 
 */  
package com.zfsoft.xgxt.jjgl.cssz.dmwh;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-8-25 下午03:15:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SfbzDao extends SuperDAOImpl<SfbzForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SfbzForm t)
			throws Exception {
		String sql = "select a.* , b.jjxkmc , c.jjnjmc from XSGGFW_JJFW_SFBZB a left join " +
				"	XSGGFW_JJFW_JJXKDMB b on a.jjxkdm = b.jjxkdm left join " +
				"	XSGGFW_JJFW_JJNJDMB c on a.jjnjdm = c.jjnjdm order by a.jjxkdm";
		return super.getPageList(t, sql, null);
	}

	/**
	 * 
	 * @描述:根据条件获取记录
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 上午09:23:24
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param xkdm
	 * @param njdm
	 * @return
	 * @throws Exception
	 * SfbzForm 返回类型 
	 * @throws
	 */
	public SfbzForm getModelByXkdmAndNjdm(String xkdm , String njdm) throws Exception{
		String sql = "select a.* from XSGGFW_JJFW_SFBZB a where a.jjxkdm = ? and a.jjnjdm = ?";
		return getModel(sql, new String[]{xkdm , njdm});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午04:46:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jjnjdm
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int deleteSfbzByJJnjdm(String jjnjdm) throws Exception{
		String sql = "delete from XSGGFW_JJFW_SFBZB a where a.jjnjdm = ? ";
		
		return dao.update(sql, new String[]{jjnjdm});
	}
	
	/**
	 * 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-8-26 下午04:46:12
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param jjnjdm
	 * @return
	 * int 返回类型 
	 * @throws
	 */
	public int deleteSfbzByJJxkdm(String jjxkdm) throws Exception{
		String sql = "delete from XSGGFW_JJFW_SFBZB a where a.jjxkdm = ? ";
		
		return dao.update(sql, new String[]{jjxkdm});
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SfbzForm t, User user)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(SfbzForm.class);
		super.setKey("ID");
		super.setTableName("XSGGFW_JJFW_SFBZB");
	}

}
