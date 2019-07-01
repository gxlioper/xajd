/**
 * @部门:学工产品事业部
 * @日期：2015-6-25 下午02:10:42 
 */  
package com.zfsoft.xgxt.dtjs.dtxxgl.zjsysxhb;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2015-6-25 下午02:10:42 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZjsySxhbService extends SuperServiceImpl<ZjsySxhbForm, ZjsySxhbDAO> {

    private ZjsySxhbDAO dao = new ZjsySxhbDAO();
    
    @SuppressWarnings({"deprecation" })
	public ZjsySxhbService(){
    	this.setDao(dao);
    }
    
	
	public HashMap<String,String> getXsdtxxMore(String xh){
		return dao.getXsdtxxMore(xh);
	}
	
	public boolean isHasExists(ZjsySxhbForm model){
		return dao.isHasExists(model);
	}
}
