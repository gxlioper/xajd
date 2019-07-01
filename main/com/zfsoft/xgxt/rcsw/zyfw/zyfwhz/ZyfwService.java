/**
 * @部门:学工产品事业部
 * @日期：2016-12-28 上午11:55:18 
 */  
package com.zfsoft.xgxt.rcsw.zyfw.zyfwhz;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：cp[工号：1352]
 * @时间： 2016-12-28 上午11:55:18 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZyfwService extends SuperServiceImpl<ZyfwForm,ZyfwDao>{

	public HashMap<String,String> getXsxx(String xh){
		return dao.getXsxx(xh);		
	}

	public List<HashMap<String, String>> getZyfwList(String xh, ZyfwForm model) {
		return dao.getZyfwList(xh,model);
	}

	public HashMap<String, String> getZsc(String xh, ZyfwForm model) {
		return dao.getZsc(xh,model);
	}

}
