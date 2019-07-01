/**
 * @部门:学工产品事业部
 * @日期：2014-3-24 下午04:35:45 
 */  
package com.zfsoft.xgxt.gygl.gyyjx;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @作者： 张小彬[工号:1036]
 * @时间： 2014-3-24 下午04:35:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GyyjxStuService extends SuperServiceImpl<GyyjxForm, GyyjxStuDao> {

	private GyyjxStuDao dao = new GyyjxStuDao();
	
	public GyyjxStuService(){
		super.setDao(dao);
	}
	
	public HashMap<String , String> getModelMap(String gyyjid){
		return dao.getModelMap(gyyjid);
	}
	
}
