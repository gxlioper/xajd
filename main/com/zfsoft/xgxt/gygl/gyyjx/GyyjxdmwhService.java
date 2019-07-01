/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 上午11:50:44 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.sql.SQLException;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 上午11:50:44 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxdmwhService extends SuperServiceImpl<GyyjxForm, GyyjxdmwhDao> {

	private GyyjxdmwhDao dao = new GyyjxdmwhDao();
	
	/**
	 * @描述 ：TODO描述下当前构造方法
	 */
	public GyyjxdmwhService() {
		super.setDao(dao);
	}

	/**
	 * 获取 最大代码
	 * @return
	 * @throws Exception 
	 * @throws SQLException
	 */
	public int getMaxdm() throws Exception {
		return dao.getMaxdm()  + 1;
	}

	//检查唯一性
	public boolean checkExist(String dmmc) throws Exception {
		return dao.checkExist(dmmc);
	}
	
	//检查唯一性修改
	public boolean checkExist(String dmmc , String dm) throws Exception {
		return dao.checkExist(dmmc , dm);
	}
}
