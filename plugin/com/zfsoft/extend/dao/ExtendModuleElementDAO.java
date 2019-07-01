/**
 * @部门:学工产品事业部
 * @日期：2015-6-3 上午09:56:17 
 */  
package com.zfsoft.extend.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendModuleElement;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-3 上午09:56:17 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendModuleElementDAO extends SuperDAOImpl<ExtendModuleElement> {
	
	public boolean updateExtendModuleElement(List<ExtendModuleElement> extendModuleElements){
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH,SHLC)=(?,?) WHERE ID = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (ExtendModuleElement ele : extendModuleElements) {
			params.add(new String[]{ele.getSfsh(),ele.getShlc(),ele.getId()});
		}
		boolean r = Boolean.TRUE;
		try {
			dao.runBatch(sql, params);
		} catch (SQLException e) {
			r = Boolean.FALSE;
			e.printStackTrace();
		}
		return r;
	}
	
	/**
	 * 
	 * @描述:根据模块ID获取子元素
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-4 上午11:23:34
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param moduleID
	 * @return
	 * @throws Exception
	 * List<ExtendModuleElement> 返回类型 
	 * @throws
	 */
	public List<ExtendModuleElement> getExtendModuleElements(String moduleID)throws Exception{
		String sql = "SELECT * FROM ZFXG_EXTEND_MODULE_ELEMENT WHERE MID = ? ORDER BY to_number(XSSX)";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{moduleID});
		if(listNotOut == null || listNotOut.size() == 0){
			return null;
		}
		List<ExtendModuleElement> list = new ArrayList<ExtendModuleElement>();
		
		for (HashMap<String, String> extendModuleElement : listNotOut) {
			String id = extendModuleElement.get("id");
			String mc = extendModuleElement.get("mc");
			String sfsh = extendModuleElement.get("sfsh");
			String shlc = extendModuleElement.get("shlc");
			String sm = extendModuleElement.get("sm");
			String mid = extendModuleElement.get("mid");
			String xssx = extendModuleElement.get("xssx");
			list.add(new ExtendModuleElement(id, mc, sfsh, shlc, sm, mid, xssx));
		}
		return list;
	}
	
	/**
	 * 
	 * @描述:设置审核流程
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-4 上午09:35:40
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param splcid
	 * @return
	 * @throws Exception
	 * int 返回类型 
	 * @throws
	 */
	public boolean updateModuleElementShlc(ExtendModuleElement target) throws Exception {
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH, SHLC)=(?, ?) WHERE ID = ?";
		return dao.runUpdate(sql, new String[]{target.getSfsh(),target.getShlc(),target.getId()});
	}
	
	/**
	 * 
	 * @描述:置审核流程(批量方式)
	 * @作者：张小彬[工号:1036]
	 * @日期：2015-6-4 上午09:43:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param targetList
	 * @return
	 * @throws Exception
	 * int[] 返回类型 
	 * @throws
	 */
	public int[] updateModuleElementShlcBatch(List<ExtendModuleElement> targetList) throws Exception {
		String sql = "UPDATE ZFXG_EXTEND_MODULE_ELEMENT SET (SFSH, SHLC)=(?, ?) WHERE ID = ?";
		List<String[]> params = new ArrayList<String[]>();
		for (ExtendModuleElement target : targetList) {
			params.add(new String[]{target.getSfsh(),target.getShlc(),target.getId()});
		}
		return dao.runBatch(sql, params);
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModuleElement t,
			User user) throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendModuleElement t)
			throws Exception {
		return null;
	}

	@Override
	protected void setTableInfo() {
		super.setClass(ExtendModuleElement.class);
		super.setKey("ID");
		super.setTableName("ZFXG_EXTEND_MODULE_ELEMENT");
	}

}
