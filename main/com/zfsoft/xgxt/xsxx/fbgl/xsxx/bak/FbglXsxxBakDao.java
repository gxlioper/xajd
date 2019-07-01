/**
 * @部门:学工产品事业部
 * @日期：2014-3-25 下午03:13:50 
 */
package com.zfsoft.xgxt.xsxx.fbgl.xsxx.bak;

import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxDao;
import com.zfsoft.xgxt.xsxx.fbgl.xsxx.FbglXsxxForm;

/**
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用)
 * @作者： 张昌路[工号:982]
 * @时间： 2014-3-25 下午03:13:50
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明
 */

public class FbglXsxxBakDao extends FbglXsxxDao {
	protected void setTableInfo() {
		this.setKey("xh");
		this.setTableName("XG_XSXX_FBGL_XSXX_LSB_BAK");
		this.setClass(FbglXsxxForm.class);
	}
}
