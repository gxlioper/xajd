/**
 * @部门:学工产品事业部
 * @日期：2015-7-9 上午10:27:57 
 */  
package com.zfsoft.xgxt.xsztz.xmsbgl.glygl;

import java.util.HashMap;
import java.util.List;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 夏夏[工号:1104]
 * @时间： 2015-7-9 上午10:27:57 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class GlyglService extends SuperServiceImpl<GlyglForm, GlyglDao> {
	
	public List<HashMap<String, String>> getYhList(GlyglForm t) throws Exception {
		return dao.getYhList(t);
	}

}
