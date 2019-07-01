/**
 * @部门:学工产品事业部
 * @日期：2015-8-29 上午09:57:55 
 */  
package com.zfsoft.xgxt.znxgl.fxbgl;

import java.util.HashMap;
import java.util.List;

import xgxt.form.User;

import com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl;
import com.zfsoft.xgxt.znxgl.sxbgl.SxbForm;
import com.zfsoft.xgxt.znxgl.znxgl.ZnxglForm;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-29 上午09:57:55 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FxbDao extends SuperDAOImpl<FxbForm> {

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FxbForm t)
			throws Exception {
		// TODO 自动生成方法存根
		return null;
	}

	/*
	      描述: @see com.zfsoft.xgxt.base.dao.impl.SuperDAOImpl#getPageList(java.lang.Object, xgxt.form.User)
	 */
	
	@Override
	public List<HashMap<String, String>> getPageList(FxbForm t, User user)
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
		super.setClass(FxbForm.class);
		super.setKey("xjbh");
		super.setTableName("xg_znx_new_fxb");
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
    public boolean save(FxbForm t) throws Exception{
    	StringBuilder sql = new StringBuilder();
    	sql.append(" insert into xg_znx_new_fxb (xjbh,fsrbh,fssj,fsnr,ztlb,xjzt) values(?,?,?,?,?,?)");
    	return dao.runUpdate(sql.toString(), new String[]{t.getXjbh(),t.getFsrbh(),t.getFssj(),t.getFsnr(),t.getZtlb(),t.getXjzt()});
    } 

}
