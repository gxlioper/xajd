/**
 * @部门:学工产品事业部
 * @日期：2015-8-29 上午09:57:37 
 */  
package com.zfsoft.xgxt.znxgl.fxbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-29 上午09:57:37 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class FxbService extends SuperServiceImpl<FxbForm, FxbDao> {
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
    	return dao.save(t);
    } 
}
