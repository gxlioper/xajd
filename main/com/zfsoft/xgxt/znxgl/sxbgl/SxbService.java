/**
 * @部门:学工产品事业部
 * @日期：2015-8-29 上午09:55:45 
 */  
package com.zfsoft.xgxt.znxgl.sxbgl;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： yxy[工号:1206]
 * @时间： 2015-8-29 上午09:55:45 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class SxbService extends SuperServiceImpl<SxbForm, SxbDao> {
	SxbDao dao = new SxbDao();
	//更新收信表查看标志
	public boolean runUpate_sxbckjl(SxbForm sx)throws Exception{
		return dao.runUpate_sxbjl(sx);
	}
	//更新收信表删除标志
	public boolean runUpate_sxbscjl(SxbForm sx)throws Exception{
		return dao.runUpate_sxbscjl(sx);
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
    public boolean save(SxbForm sx) throws Exception{
    	return dao.save(sx);
    }
	
}
