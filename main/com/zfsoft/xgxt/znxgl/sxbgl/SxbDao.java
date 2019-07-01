/**
 * @部门:学工产品事业部
 * @日期：2015-8-29 上午09:56:07 
 */  
package com.zfsoft.xgxt.znxgl.sxbgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.wdznx.WdznxForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-29 上午09:56:07 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SxbDao extends SuperDAOImpl<SxbForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(SxbForm t, User user)
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
		super.setClass(SxbForm.class);
		super.setKey("xjbh");
		super.setTableName("XG_ZNX_NEW_SXB");
	}
	
    public boolean runUpate_sxbjl(SxbForm sx) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrydbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
		
	}
    
    public boolean runUpate_sxbscjl(SxbForm sx)throws Exception{
    	StringBuilder sql = new StringBuilder();
		sql.append(" update XG_ZNX_NEW_SXB set jsrscbj='1' where xjbh= ? and jsrbh = ?");
		return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh()});
	}
    
    /**
     * 
     * @描述:保存
     * @作者：喻鑫源[工号：1206]
     * @日期：2015-12-7 下午03:19:04
     * @修改记录: 修改者名字-修改日期-修改内容
     * @param sx
     * @return
     * @throws Exception
     * boolean 返回类型 
     * @throws
     */
    public boolean save(SxbForm sx) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into XG_ZNX_NEW_SXB (xjbh,jsrbh,fprbh) values(?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{sx.getXjbh(),sx.getJsrbh(),sx.getFprbh()});
    }
}
