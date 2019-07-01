/**
 * @部门:学工产品事业部
 * @日期：2016-3-8 下午02:27:47 
 */  
package xsgzgl.szdw.jtff.jtmdwh;

import xsgzgl.szdw.jtff.jtff.JtffDao;
import xsgzgl.szdw.jtff.jtff.JtffForm;

import com.zfsoft.xgxt.base.service.impl.SuperServiceImpl;

/** 
 * @系统名称: 学生工作管理系统
 * @模块名称: XXXX管理模块
 * @类功能描述: TODO(这里用一句话描述这个类的作用) 
 * @作者： 喻鑫源[工号:1206]
 * @时间： 2016-3-8 下午02:27:47 
 * @版本： V1.0
 * @修改记录: 类修改者-修改日期-修改说明  
 */

public class JtmdwhService extends SuperServiceImpl<JtmdwhForm, JtmdwhDao> {
	public int runDeletegdjtmd(String[] para) throws Exception{
		return dao.runDeletegdjtmd(para);
	}
}
