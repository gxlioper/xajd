/**
 * @部门:学工产品事业部
 * @日期：2013-5-16 下午01:33:26 
 */  
package com.zfsoft.xgxt.szdw.sdkj;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： cmj 
 * @时间： 2013-5-16 下午01:33:26 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SzdwSdkjZdjskhService extends SuperServiceImpl<ZdlskhForm, SzdwSdkjZdjskhDAO> {
	
	private SzdwSdkjZdjskhDAO dao = new SzdwSdkjZdjskhDAO();
	
	public SzdwSdkjZdjskhService(){
		super.setDao(dao);
	}
	
	public List<HashMap<String,String>> getPageListxx(ZdlskhForm t) throws Exception {
		return dao.getPageListxx(t);
	}

	/** 
	 * @描述:TODO(这里用一句话描述这个方法的作用)
	 * @作者：cmj
	 * @日期：2013-5-23 上午10:03:42
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @return
	 * Object 返回类型 
	 * @throws 
	 */
	public List<HashMap<String,String>> getndList() {
		// TODO 自动生成方法存根
		return dao.getndList();
	}
	

}
