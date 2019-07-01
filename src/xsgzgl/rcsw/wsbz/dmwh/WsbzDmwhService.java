/**
 * @部门:学工产品事业部
 * @日期：2016-5-5 上午09:56:16 
 */  
package xsgzgl.rcsw.wsbz.dmwh;

import java.util.HashMap;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 张昌路[工号:982]
 * @时间： 2016-5-5 上午09:56:16 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class WsbzDmwhService extends SuperServiceImpl<WsbzDmwhForm, WsbzDmwhDao> {
	public HashMap<String, String> getWsbzCk(String fddm){
		return dao.getWsbzCk(fddm);
	}
	
	//检验是否该项目代码或该项目名称是否存在
	public boolean checkIsExists(WsbzDmwhForm t){
		return dao.checkIsExists(t);
	}
	
	//删除时检查倍删除的项目是否在使用中
	public boolean checkIsUsingNow(String[]para){
	   return dao.checkIsUsingNow(para);
	}

	public HashMap<String, String> getQjcs() {
		// TODO Auto-generated method stub
		return dao.getQjcs();
	}

	public boolean runUpdateQjcs(String bmcs, String jzts, String jzsj) throws Exception {
		// TODO Auto-generated method stub
		return dao.runUpdateQjcs( bmcs,  jzts,  jzsj);
	}
	

}
