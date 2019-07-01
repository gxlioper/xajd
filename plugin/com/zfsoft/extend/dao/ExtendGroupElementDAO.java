/**
 * @部门:学工产品事业部
 * @日期：2015-6-3 上午10:02:15 
 */  
package com.zfsoft.extend.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import xgxt.form.User;

import com.zfsoft.extend.model.ExtendGroupElement;
import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张小彬[工号:1036]
 * @时间： 2015-6-3 上午10:02:15 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ExtendGroupElementDAO extends SuperDAOImpl<ExtendGroupElement> {

	
	public List<ExtendGroupElement> getExtendGroupElementListByGID(String gid) throws Exception{
		if(StringUtils.isBlank(gid)){
			return null;
		}
		String sql = "select * from ZFXG_EXTEND_GROUP_ELEMENT where gid = ? order by to_number(xssx)";
		List<HashMap<String, String>> listNotOut = dao.getListNotOut(sql, new String[]{gid});
		List<ExtendGroupElement> ges = new ArrayList<ExtendGroupElement>();
		for (HashMap<String, String> hashMap : listNotOut) {
			String zd = hashMap.get("zd");
			String mc = hashMap.get("mc");
			String lx = hashMap.get("lx");
			String sj = hashMap.get("sj");
			String gs = hashMap.get("gs");
			String mrz = hashMap.get("mrz");
			String bt = hashMap.get("bt");
			String cd = hashMap.get("cd");
			String bz = hashMap.get("bz");
			String xssx = hashMap.get("xssx");
			String _gid = hashMap.get("gid");
			String sfbj = hashMap.get("sfbj");
			String elcd = hashMap.get("elcd");
			ExtendGroupElement ge = new ExtendGroupElement(zd, mc, lx, sj, gs, mrz, bt, cd, bz, xssx, _gid, sfbj, elcd);
			ges.add(ge);
		}
		return ges;
	}
	
	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroupElement t){
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(ExtendGroupElement t,
			User user) throws Exception {
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#setTableInfo()
	 */
	
	@Override
	protected void setTableInfo() {
		super.setClass(ExtendGroupElement.class);
		super.setTableName("ZFXG_EXTEND_GROUP_ELEMENT");
	}

}
