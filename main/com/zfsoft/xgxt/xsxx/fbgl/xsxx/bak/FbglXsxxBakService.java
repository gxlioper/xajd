/**
 * @部门:学工产品事业部
 * @日期：2014-3-25 下午03:14:08 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-25 下午03:14:08
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglXsxxBakService extends SuperServiceImpl<FbglXsxxForm, FbglXsxxBakDao> {
	FbglXsxxBakDao dao = new FbglXsxxBakDao();
	public FbglXsxxBakService() {
		this.setDao(dao);
	}
}
