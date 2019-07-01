/**
 * @部门:学工产品事业部
 * @日期：2017-3-21 上午09:30:14 
 */  
package com.zfsoft.xgxt.zjly.zhf.dellog;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者：CP[工号:1352]
 * @时间： 2017-3-21 上午09:30:14 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class ZhfdelService extends SuperServiceImpl<ZhfdelForm, ZhfdelDao>{
private ZhfdelDao dao = new ZhfdelDao();
public ZhfdelService() {
	setDao(dao);
}

}
