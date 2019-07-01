/**
 * @部门:学工产品事业部
 * @日期：2015-1-26 下午04:01:19 
 */  
package com.zfsoft.xgxt.rcsw.zwzxkqgl.qqlxwh;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： xiaxia [工号:1104]
 * @时间： 2015-1-26 下午04:01:19 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class QqlxwhService extends SuperServiceImpl<QqlxwhForm, QqlxwhDao>{
	
	private QqlxwhDao qqlxDao = new QqlxwhDao();
	
	/**
	 *查询缺勤类型列表
	 */
	public List<HashMap<String, String>> getQqlxList(){
		return qqlxDao.getQqlxList();
	}

}
