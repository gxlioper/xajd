/**
 * @部门:学工产品事业部
 * @日期：2014-5-23 上午08:37:37 
 */  
package com.zfsoft.xgxt.xljkwzdx.xlwygl.jcsz;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-5-23 上午08:37:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JcszDao extends SuperDAOImpl<JcszForm> {

	/**
	 * 
	 * @描述:查询配置
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-23 上午08:41:08
	 */
	public HashMap<String , String> getJcsz(){
		
		String sql = "select a.* from XG_XLJK_XLWYGL_JCSZ a ";
		
		return dao.getMapNotOut(sql, new String[]{});
	}
	
	/**
	 * @描述:保存配置
	 * @作者：张小彬[工号：1036]
	 * @日期：2014-5-23 上午08:47:44
	 */
	public boolean saveJcsz(JcszForm model){
		boolean val = true;
		String del = "delete from XG_XLJK_XLWYGL_JCSZ ";
		String inst = "insert into XG_XLJK_XLWYGL_JCSZ (bjzbrc_splcid , gyzbrc_splcid , psxxsb_splcid) values (?,?,?)";
		try {
			dao.runDelete(del, new String[]{});
			dao.runUpdate(inst, new String[]{model.getBjzbrcSplcid() , 
					model.getGyzbrcSplcid() , 
					model.getPsxxsbSplcid()});
			val = true;
		} catch (Exception e) {
			e.printStackTrace();
			val = false;
		}
		
		return val;
	}
	
	//==========================================================//
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */

	@Override
	public List<HashMap<String, String>> getPageList(JcszForm t, User user)
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

	}

}
